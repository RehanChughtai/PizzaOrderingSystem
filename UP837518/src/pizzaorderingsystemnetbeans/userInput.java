/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaorderingsystemnetbeans;
/**
 * Class to manage the User's inputs.
 *
 * @author UP837518
 */
public class userInput {
    //instance variables
    KeyboardInput input;
    private int quantityOfPizza;
    private int numOfPizzas;
    private String sizeOfPizza;
    private String pizzaSauce;
    private String pizzaCrust;
    private boolean check;
    private int noTopping;
    private String oneTopping;
    private String twoTopping;
    
    /**
     * Constructor for objects of class UserInput
     */
    public userInput()
    {
        // initialise instance variables
        input = new KeyboardInput();
    }
    
     /**
     * A method that ask the user input for the number of pizzas they want.
     * @return the integer value between 0 and 7
     */
    public int enterNumOfPizza()
    {
        do{
            // Enter the amount of pizzas that they want using the keyboardInput Class.
            System.out.print("\nEnter the amount of pizzas you want: ");
            numOfPizzas = input.getInputInteger();
            check = false;
            //checks the the user inputs a value between 0 and 7
            if (numOfPizzas > 0 && numOfPizzas < 7) 
            {
                quantityOfPizza = numOfPizzas;
                check = true;
            }
            else 
            {
                //if a value out of the range of 0 to 6 is chosen, a error 
                //message is given and returned to reinput a value.
                System.out.print("\nThe maximum pizzas that you can order are 6.\nPlease try again");
            }
        } 
        while (check == false);
        
        return quantityOfPizza;
    }
    
    /**
     * A method that is filters the user input for pizzaSize
     *
     * @return the integer value between 0 and 7
     */
    public String enterSizeOfPizza()
    {
        //This is for one pizza
        check = false;
        do{
            //ask user for the size of the pizza they want.
            System.out.print("\nPlease enter the pizza size: Small, Medium or Large: ");
            sizeOfPizza = input.getInputString();
            //all characters large or small font accepted and changed to 
            //lowercase.
            sizeOfPizza = sizeOfPizza.toLowerCase();
            if (sizeOfPizza.contains("small")) 
            {
                check = true;
                sizeOfPizza = "small";
            }
            else if (sizeOfPizza.contains("medium")) 
            {
                check = true;
                sizeOfPizza = "medium";
            }
            else if (sizeOfPizza.contains("large")) 
            {
                check = true;
                sizeOfPizza = "large";
            }
            else 
            {
                //if an invalid size is given, error message is printed and 
                //returns to reinput values.
                System.out.print("\nInvalid size\nPlease try again: ");
            }
        } 
        while (check == false);  
        
        return(sizeOfPizza);
    }
    
    /**
     * A method that is filters the user input for type of Crust
     *
     * @return the String value of the crust based on user input
     */
    public String enterCrustType()
    {
        check = false;
        do{
            // Use the keyboard to get the pizza quantity
            System.out.print("\nPlease enter the type of pizza crust you want: Deep Pan, Stuffed Crust or Thin Crust: ");
            pizzaCrust = input.getInputString();
            //all characters large or small font accepted and changed to 
            //lowercase.
            pizzaCrust = pizzaCrust.toLowerCase();
            check = false;

            if(pizzaCrust.contains("deep pan"))
            {
                check = true;
                pizzaCrust = "deep pan";
            }
            else if(pizzaCrust.contains("stuffed crust"))
            {
                check = true;
                pizzaCrust = "stuffed crust";
            }
            else if(pizzaCrust.contains("thin crust"))
            {
                check = true;
                pizzaCrust = "thin crust";
            }
            else
            {
                //if an invalid crust is given, error message is printed and 
                //returns to reinput values.
                System.out.print("\nInvalid crust type\nPlease try again: ");
            }
            
        } 
        while (check == false);
        
        return(pizzaCrust);
    }
    
    /**
     * A method that is filters the user input for the sauce of the pizza.
     *
     * @return the String value of the sauce based on the user input.
     */
    public String enterPizzaSauce()
    {
        //This is for one pizza
        check = false;
        do{
            //asks the user for the sauce they want on the pizza.
            System.out.print("\nPlease enter the pizza sauce: Tomato or BBQ: ");
            pizzaSauce = input.getInputString();
            //all characters large or small font accepted and changed to 
            //lowercase.
            pizzaSauce = pizzaSauce.toLowerCase();
            if (pizzaSauce.isEmpty()) {
                check = true;
                pizzaSauce = "Tomato";
                System.out.print("\nTomato sauce has been selected\n");
            }
            else if (pizzaSauce.contains("Tomato")) {
                check = true;
            }
            else if (pizzaSauce.contains("tomato")) {
                check = true;
                pizzaSauce = "Tomato";
            }
            else if (pizzaSauce.contains("bbq")) {
                check = true;        
                pizzaSauce = "BBQ";
            }
            else 
            {
                System.out.print("\n"+pizzaSauce);
                //if an invalid sauce is given, error message is printed and 
                //returns to reinput values.
                System.out.print("\nInvalid Pizza Sauce\n Please choose Tomato or BBQ Sauce: ");
            } 
        } 
        while (check == false);
            
        return(pizzaSauce);
    }
    
     /**
     * A method that is filters the user input for zero toppings being selected
     *
     * @return the String value of no topping selected
     */
    public int enterNoTopping()
    {
        do{
            //asks the user to input the amount og toppins they want
            System.out.print("\nEnter the number of toppings you would like on your pizza: ");
            noTopping = input.getInputInteger();
            check = false;
            //check that the input can only be 0, 1 and 2.
            if (noTopping >= 0 && noTopping <= 2) {
                check = true;
            }
            else 
            {
                //if an invalid topping amount is given, error message is
                //printed and returns to reinput values.
                System.out.print("\nYou have entered an invalid amount of toppings\n Please enter 0,1 or 2: ");
            }
        } 
        while (check == false);
        
        return noTopping;
    }
    
     /**
     * A method that is filters the user input for one topping.
     *
     * @return the String value of one topping based on user input
     */
    public String getToppingOne(){
        check = false;
        do{
            //asks the user what topping they want out of the options provided.
            System.out.print("\nWhat would you like for one topping: Pepperoni or Red Onion: ");
            oneTopping = input.getInputString();
            //all characters large or small font accepted and changed to 
            //lowercase.
            oneTopping = oneTopping.toLowerCase();
            if(oneTopping.contains("pepperoni")){
                //code to break the loop
                check = true;
                oneTopping = "pepperoni";
            }
            else if (oneTopping.contains("red onion")){
                //code to break the loop
                check = true;
                oneTopping = "redOnion";
            }
            else{
                //if an invalid topping amount is given, error message is
                //printed and returns to reinput values.
                System.out.println("Error\nPlease try again: ");
            }
        }
        while(check == false);
        
        return(oneTopping);
    }
    
     /**
     * A method that is filters the user input for two toppings
     *
     * @return the String value of two toppings based on the users input.
     */
    public String getToppingTwo(){
        check = false;
        do{
            //asks the user what topping they want out of the options provided.
            System.out.print("\nWhat would you like for topping two: Pepperoni or Red Onion: ");
            twoTopping = input.getInputString();
            //all characters large or small font accepted and changed to 
            //lowercase.
            twoTopping = twoTopping.toLowerCase();
            if(twoTopping.contains("pepperoni"))
            {
                //code to break the loop
                check = true;
                twoTopping = "pepperoni";
            }
            else if (twoTopping.contains("red onion"))
            {
                //code to break the loop
                check = true;
                twoTopping = "redOnion";
            }
            else
            {
                //if an invalid topping amount is given, error message is
                //printed and returns to reinput values.
                System.out.println("Error\nPlease try again: ");
            }
        }
        while(check == false);
        
        return(twoTopping);
    }
}


