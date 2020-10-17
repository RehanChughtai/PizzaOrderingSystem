
package pizzaorderingsystemnetbeans;

import java.io.*;

/**
 * Class to manage keyboard input from the console
 * @author UP837518
 */
public class KeyboardInput 
{
    BufferedReader br; 
    
    /**
     * Constructor for Input Class
     */
    public KeyboardInput()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    /**
     * Method to get a string which has been input on the console
     * @return The string input from the console
     */
    public String getInputString()
    {
        String str;
        
        try
        {
            str = br.readLine(); 
        }
        catch(Exception e)
        {
            str = "Invalid String " + e.getMessage();
        }
               
        return str;
    }
    
    /**
     * Method to get an integer which has been input on the console
     * @return Integer value which has been entered in the console.  It will return 999999 if the integer is not valid.
     */
    public int getInputInteger()
    {
        int outInt = 99999999;
        String inStr = getInputString();
        
        try
        {
            outInt = Integer.parseInt(inStr);
        }
        catch(Exception e)
        {
            System.out.println("That is not an integer");
        }
        
        return outInt;
    }
    
    /**
     * Method to get an decimal number which has been input on the console
     * @return Double value which has been entered in the console.  It will return 999999.999999 if the integer is not valid.
     */
    public double getInputDouble()
    {
        double outDouble = 999999.999999;
        String inStr = getInputString();
        
        try
        {
            outDouble = Double.parseDouble(inStr);
        }
        catch(Exception e)
        {
            System.out.println("That is not an decimal number");
        }
        
        return outDouble;
    }
}   
