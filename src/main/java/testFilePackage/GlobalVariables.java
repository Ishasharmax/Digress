package testFilePackage;

import java.util.*;


public class GlobalVariables {
    public HashMap<String, Object> variables;

    public GlobalVariables(){
        variables = new HashMap<>();
    }

    public boolean blankString(String stringToCheck){
        for (int i = 0; i < stringToCheck.length(); i++){
            if (stringToCheck.charAt(i) != ' '){
                return false;
            }
        }
        return true;
    }
    public void addString(String name, String value){
        if (name.isEmpty()){
            throw new IllegalArgumentException("Variable must have a name");
        }
        if (value.isEmpty()){
            throw new IllegalArgumentException("Variable must have a value");
        }
        if (blankString(name)){
            throw new IllegalArgumentException("Variable name cannot be blank or empty");
        }
        if (blankString(value)){
            throw new IllegalArgumentException("Variable value cannot be blank or empty");
        }
        if (variables.get(name) != null){
            throw new IllegalArgumentException("Variable already exists");
        }
        variables.put(name, value);
    }

    public void addInt(String name, int value){
        if (name.isEmpty()){
            throw new IllegalArgumentException("Variable must have a name");
        }
        if (value < 0){
            throw new IllegalArgumentException("Value cannot be negative");
        }
        if (blankString(name)){
            throw new IllegalArgumentException("Variable name cannot be blank or empty");
        }
        if (variables.get(name) != null){
            throw new IllegalArgumentException("Variable already exists");
        }
        variables.put(name, value);
    }

    public void removeVariable(String name){
        if (variables.get(name) == null){
            throw new IllegalArgumentException("Variable does not exist");
        }
        variables.remove(name);
    }

    public Object getVariable(String name){
        if (variables.get(name) == null){
            throw new IllegalArgumentException("Variable does not exist");
        }
        return variables.get(name);
    }

    public void printVariable(String name){
        System.out.println(name + ": " + getVariable(name));
    }

    public void editVariable(String key, Object newValue){
        if (variables.get(key) == null){
            throw new IllegalArgumentException("Variable does not exist");
        }
        variables.replace(key, newValue);
    }

    public void clearVariables(){
        if (variables.isEmpty()){
            throw new IllegalArgumentException("No variables to remove");
        }
        variables.clear();
    }

    public int getSize(){
        return variables.size();
    }
}
