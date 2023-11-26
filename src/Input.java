import java.util.*;
public class Input{
    public Input(){

    }
    public char acceptCharInput(String string, int index){
        return string.charAt(index);
    }
    public double acceptDoubleInput(String string){
        return Double.parseDouble(string);
    }

    public int acceptIntegerInput(String string){
        return Integer.parseInt(string);
    }
    public String acceptStringInput(String string){
        return string;
    }

}