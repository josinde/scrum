package scrum.exercices.commandline;

import java.util.HashMap;
import java.util.Map;

public class Register {

    public enum Type{
        Apples,
        Bananas,
        Cherries
    }
    
    Map<String, Type> translations;
    
    
    private double total = 0.0;
    private Map<Type, Double> prices;
    
    private int cherriesCounter;
    private int bananasCounter;
    
    public Register(){
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
    }
    
    public void add(Type type){
        Double d = prices.get(type);
        
        double discount = checkDiscount(type);
        total += (d - discount);
    }
    
    public Type getTypeFromString(String value) {
        return translations.get(value);
    }
    
    private double checkDiscount(Type type) {
        
        switch (type) {
        case Cherries:
            ++ cherriesCounter;
            if ((cherriesCounter % 2) == 0) {
                return 0.20;
            }else{
                return 0.0;
            }
        case Bananas:
            ++ bananasCounter;
            if ((bananasCounter % 2) == 0) {
                return prices.get(type);
            }else{
                return 0.0;
            }
        default:
            return 0.0; 
        }
    }
    
    public double getTotal(){
        return total;
    }
    
}
