package pizzaorderingsystemnetbeans;

import java.awt.*;

/**
 * Class to manage the pizza order.
 *
 * @author UP837518
 */

//Inheriting input method from userInput class
//class userInput extends input{

public class OrderingSystem {

    private Canvas canvas;
    private int numOfPizza;
    private String pizzaSize;
    private String crustType;
    private String pizzaSauce;
    private int noTopping;
    private String toppingOne;
    private String toppingTwo;
    private int topLeftX;
    private int topLeftY;
    private int count;
    private double totalCost;

    /**
     * Constructor for the ordering system.
     */
    public OrderingSystem() 
    {
        canvas = new Canvas("Pizza Ordering", 900, 650);
    }

    /**
     * Method to draw the outline of the order screen.
     */
    public void drawOrderScreen() 
    {
        canvas.setForegroundColor(Color.BLACK);
        // vertical dividers
        canvas.drawLine(300, 0, 300, 600);
        canvas.drawLine(600, 0, 600, 600);

        // halfway divider
        canvas.drawLine(0, 300, 900, 300);

        // total price line
        canvas.drawLine(0, 600, 900, 600);

    }

    /**
     * Method to draw the total cost of all pizzas to the order screen.
     */
    public void drawTotalCost() 
    {
        canvas.setFontSize(25);
        //price is set to 2 decimal places
        canvas.drawString("Total Price of the Order: " + "£" + String.format("%.2f",totalCost), 10, 640);
    }

    /**
     * Method to manage the ordering of the pizzas. Calculate the total cost of 
     * an individual pizza and all pizzas. Displays pizzas based on user inputs.
     */
    public void startOrdering() 
    {
        double pizzaArea;
        count = 0;
        //calls userInput to use input method
        userInput input = new userInput();
        //asks user for the number of pizzas they want.
        numOfPizza = input.enterNumOfPizza();
        for (int index = 0; index < numOfPizza; index++) 
        {
            count++; //increase pizza 1 by 1 (pizza 2) till final pizza.
            System.out.print("\nPizza: " + (index + 1));
            //asks user for the size of the pizzas they want.
            pizzaSize = input.enterSizeOfPizza();
            //Specifys the area of the pizza based on the size.
            if (pizzaSize.contains("small")) 
            {
                pizzaArea = 78.5;
            } 
            else if (pizzaSize.contains("medium")) 
            {
                pizzaArea = 113.1;
            } 
            else 
            {
                pizzaArea = 153.9;

            }
            //asks the user to input the crust they want.
            crustType = input.enterCrustType();
            //performs a calculation of the size and crust of the pizza based on
            //user input
            if (crustType.contains("deep pan")) 
            {
                totalCost += 0.11 * pizzaArea;
            } 
            else if (crustType.contains("thin crust")) 
            {
                totalCost += 0.08 * pizzaArea;
            } 
            else 
            {
                totalCost += 0.014 * pizzaArea;
            }
            //asks the user for the sauce they want on the pizza
            pizzaSauce = input.enterPizzaSauce();
            //if BBQ is selected, adds 50p to totalCost.
            if (pizzaSauce.contains("BBQ")) 
            {
                totalCost += 0.50;
            }
            //asks users to input zero toppings to display pizza
            noTopping = input.enterNoTopping();
            //Display the pizza on the top 3 rows based on user input.
            if (index < 3) 
            {
                topLeftX = index * 300;
                topLeftY = 0;
            } 
            //Display the pizza on the bottom 3 rows based on user input.
            else 
            {
                topLeftX = (index - 3) * 300;
                topLeftY = 300;
            }
            //If no toppins are selected by user, based on user input, empty 
            //strings will be presented
            if (noTopping == 0) 
            {
                Pizza pizza = new Pizza(canvas, topLeftX, topLeftY, pizzaSize, crustType, pizzaSauce, " ", " ", count);
                pizza.displayPizza();
            } 
            //If one topping is selected by user, based on user input, only 
            //specified topping will show.
            else if (noTopping == 1) 
            {
                toppingOne = input.getToppingOne();
                if (toppingOne == "pepperoni") 
                {
                    totalCost += 0.20;
                } 
                else 
                {
                    totalCost += 0.15;
                }
                Pizza pizza = new Pizza(canvas, topLeftX, topLeftY, pizzaSize, crustType, pizzaSauce, toppingOne, " ", count);
                pizza.displayPizza();
            } 
                //If two toppings are selected by user, based on user input, 
                //both specified topping will show.
                else if (noTopping == 2) 
                {
                toppingOne = input.getToppingOne();
                if (toppingOne == "pepperoni") 
                {
                    totalCost += 0.20;
                } 
                else 
                {
                    totalCost += (0.15);
                }
                //asks the user to input two toppings and calculates price based
                //on the toppings chosen
                toppingTwo = input.getToppingTwo();
                if (toppingTwo == "pepperoni") 
                {
                    totalCost += 0.16;
                } 
                else 
                {
                    totalCost += 0.12;
                }
                Pizza pizza = new Pizza(canvas, topLeftX, topLeftY, pizzaSize, crustType, pizzaSauce, toppingOne, toppingTwo, count);
                pizza.displayPizza();
            }
        }
    }

}
//}
