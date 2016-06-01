package scrum.exercices.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import scrum.exercices.commandline.Register.Type;

public class Echo {
    
    private Register register;
    
    public Echo (){
        register = new Register();
    }
    
    public void process(InputStream in, OutputStream out){
        try{
            BufferedReader br = 
                    new BufferedReader(new InputStreamReader(in));

            String input;
            String msg;
            while ((input = br.readLine()) != null) {
                msg = addCount(input);
                if (msg != null) {
                    println(msg, out);
                }

            }

        }catch(IOException io){
            io.printStackTrace();
        }   
    }
    public void processCsv(InputStream in, OutputStream out){
        try{
            BufferedReader br = 
                    new BufferedReader(new InputStreamReader(in));

            String input;
            String msg;
            while((input=br.readLine())!=null){
                
                String[] items = input.split(",");
                for (String it : items) {
                    
                    String value = it.trim();
                    msg = addCount(value);
                    if(msg != null){
                        println(msg, out);
                    }
                }
                
            }

        }catch(IOException io){
            io.printStackTrace();
        }   
    }
    
    private void println(String msg, OutputStream out) throws IOException {
        out.write(msg.getBytes());
        out.write('\n');        
    }

    private String addCount(String input){
        Register.Type type = parseType(input);
        if(type != null){
            register.add(type);
            return Long.toString(Math.round(100 * register.getTotal()));
        }else{
            return "Unknown fruit '"+input+"'. Please type a valid type";
        }
    }
    
    private Type parseType(String value){
        return register.getTypeFromString(value);
    }

    public static void main(String[] args) {
        Echo echo = new Echo();
        echo.process(System.in, System.out);
    }
}
