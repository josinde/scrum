package scrum.exercices.commandline;

import java.util.HashMap;
import java.util.Map;

public class Register {

    public enum Type{
        Apples,
        Bananas,
        Cherries
    }
    
    public enum Country {
        UK,
        FR,
        GR,
    }
    
    Map<String, Type> translations;
    Map<String, Country> countries;    
    
    private double total = 0.0;
    private Map<Type, Double> prices;
    
    private Map<Country, Map<Type, Integer>> counters;    
    
    public Register(){
        // 
        counters = new HashMap<>();
        for (Country country : Country.values()) {
            Map<Type, Integer> m = new HashMap<>();
            for (Type t : Type.values()) {
                m.put(t, new Integer(0));
            }
            counters.put(country, m);
        }
        //
        prices = new HashMap<Type,Double>();
        prices.put(Type.Apples, 1.0);
        prices.put(Type.Bananas, 1.5);
        prices.put(Type.Cherries, 0.75);
        // 
        translations = new HashMap<String, Type>();
        // MOther tongue
        for (Type type : Type.values()) {
            translations.put(type.name(), type);
        }
        // i18n
        translations.put("Pommes", Type.Apples);
        translations.put("Mele", Type.Apples);
        // Countries
        countries = new HashMap<String, Country>();
        for (Type type : Type.values()) {
            countries.put(type.name(), Country.UK);
        }   
        // i18n
        countries.put("Pommes", Country.FR);
        countries.put("Mele", Country.GR);        
    }
    
    public void add(Type type, Country country){
        Double d = prices.get(type);
        
        double discount = checkDiscount(type, country);
        total += (d - discount);
    }
    
    public Type getTypeFromString(String value) {
        return translations.get(value);
    }
    
    public Country getCountryFromString(String value) {
        return countries.get(value);
    }
    
    private double checkDiscount(Type type, Country country) {
        
        Map<Type, Integer> m = counters.get(country);
        Integer intCounter = m.get(type);
        m.put(type, intCounter++);
        counters.put(country, m);
        switch (type) {
        case Cherries:
            if ((intCounter % 2) == 0) {
                return 0.20;
            }else{
                return 0.0;
            }
        case Bananas:
            if ((intCounter % 2) == 0) {
                return prices.get(type);
            }else{
                return 0.0;
            }
        case Apples:
            switch (country) {
            case UK:
                if ((intCounter % 3) == 0) {
                    return 1.0;
                } else {
                    return 0.0;
                }
            case FR:
                if ((intCounter % 3) == 0) {
                    return 1.0;
                } else {
                    return 0.0;
                }
            case GR:
                if ((intCounter % 2) == 0) {
                    return 0.5;
                } else {
                    return 0.0;
                }
            default:
                return 0.0; 
            }
        }
        return 0.0; 
    }
    
    public double getTotal(){
        return total;
    }
    
}
