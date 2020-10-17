package pizzaorderingsystemnetbeans;

import java.awt.*;

/**
 * Class to represent a single pizza.
 *
 * @author UP837518
 */
public class Pizza {

    private Canvas canvas;
    private double topLeftX;
    private double topLeftY;
    private String pizzaSize;
    private String crustType;
    private String pizzaSauce;
    private String topping1;
    private String topping2;
    private int count;

    /**
     * Constructor for pizza.
     *
     * @param win the window to draw the pizza on
     * @param startX the top-left x coordinate for the section of screen to draw
     * pizza on
     * @param startY the top-left y coordinate for the section of screen to draw
     * pizza on
     * @param Size the size of the pizza drawn, small, medium, large.
     * @param Crust the type of crust on the drawn pizza, deep, thin and stuffed
     * crust.
     * @param Topping2 the position for both pizza toppings.
     * @param Topping1 the position for one topping to be drawn on the pizza
     * @param Sauce the sauce to be used on the pizza, tomato or bbq.
     * @param Count the top left of the windows showing what pizza it is.
     */
    public Pizza(Canvas win, double startX, double startY, String Size, String Crust, String Sauce, String Topping1, String Topping2, int Count) {
        canvas = win;
        topLeftX = startX;
        topLeftY = startY;
        pizzaSize = Size;
        crustType = Crust;
        pizzaSauce = Sauce;
        topping1 = Topping1;
        topping2 = Topping2;
        count = Count;
    }

    /**
     * Method to display the pizza information on the screen.
     */
    public void displayPizza() 
    {
        drawPizza();
        drawTopLine();
        drawBottomLine();
    }

    /**
     * Method to display the pizza on the screen and draw the positions of each
     * topping selection.
     */
    private void drawPizza() 
    {
        //Draws the Pizza and the Base depending on users input.
        canvas.setForegroundColor(Color.YELLOW);
        canvas.fillCircle(topLeftX + 150, topLeftY + 150, 200);
        if (pizzaSauce.contains("Tomato")) 
        {
            canvas.setForegroundColor(Color.RED);
            canvas.fillCircle(topLeftX + 150, topLeftY + 150, 175);
        } 
        else 
        {
            canvas.setForegroundColor(Color.ORANGE);
            canvas.fillCircle(topLeftX + 150, topLeftY + 150, 175);
        }
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillCircle(topLeftX + 150, topLeftY + 150, 150);
        
        //Draws Pepperoni's and Red Onions's on the required position based on
        //the users input.
        if (topping1.contains("pepperoni")) 
        {
            //Draw 5 pepperoni's
            drawPepperoni(topLeftX + 150, topLeftY + 150);
            drawPepperoni((topLeftX - 35) + 150, (topLeftY - 35) + 150);
            drawPepperoni((topLeftX + 35) + 150, (topLeftY + 35) + 150);
            drawPepperoni((topLeftX + 35) + 150, (topLeftY - 35) + 150);
            drawPepperoni((topLeftX - 35) + 150, (topLeftY + 35) + 150);
        }
        if (topping2.contains("pepperoni")) 
        {
            //Draw 4 pepperoni's
            drawPepperoni((topLeftX - 50) + 150, topLeftY + 150);
            drawPepperoni(topLeftX + 150, (topLeftY - 50) + 150);
            drawPepperoni((topLeftX + 50) + 150, topLeftY + 150);
            drawPepperoni(topLeftX + 150, (topLeftY + 50) + 150);
        }
        if (topping1.contains("redOnion")) 
        {
            //Draw 5 pepperoni
            drawRedOnion(topLeftX + 150, topLeftY + 150);
            drawRedOnion((topLeftX - 35) + 150, (topLeftY - 35) + 150);
            drawRedOnion((topLeftX + 35) + 150, (topLeftY + 35) + 150);
            drawRedOnion((topLeftX + 35) + 150, (topLeftY - 35) + 150);
            drawRedOnion((topLeftX - 35) + 150, (topLeftY + 35) + 150);
        }
        if (topping2.contains("redOnion")) 
        {
            //Draw 4 pepperoni
            drawRedOnion((topLeftX - 50) + 150, topLeftY + 150);
            drawRedOnion(topLeftX + 150, (topLeftY - 50) + 150);
            drawRedOnion((topLeftX + 50) + 150, topLeftY + 150);
            drawRedOnion(topLeftX + 150, (topLeftY + 50) + 150);
        }

    }

    /**
     * Method to write the information shown in the bottom line of the
     * individual pizza on the screen. This method will display the pizza number
     * and size at the top of the screen.
     */
    private void drawTopLine() 
    {
        String topLine = "Pizza";

        double stringX = topLeftX + 10;
        double stringY = topLeftY + 25;

        canvas.setForegroundColor(Color.BLACK);
        canvas.setFontSize(15);
        canvas.drawString(topLine + " " + count + "(" + pizzaSize + ")", stringX, stringY);
    }
    //find out how each one has a number next to it

    /**
     * Method to write the information shown in the bottom line of the
     * individual pizza on the screen. This method will display the type of
     * crust and sauce ordered.
     */
    private void drawBottomLine() 
    {
        String bottomLine = "Crust: ";

        double stringX = topLeftX + 10;
        double stringY = topLeftY + 290;

        canvas.setForegroundColor(Color.BLACK);
        canvas.setFontSize(15);
        canvas.drawString(bottomLine + crustType + "," + pizzaSauce, stringX, stringY);
    }
    
    /**
     * Method to draw the Pepperoni using the x and y positions so that it can 
     * be placed anywhere on the pizza, Positions of the pizza is drawn above.
     * @param xPos is the x position of the centre point of the topping to be
     * drawn on the pizza
     * @param yPos is the y position of the centre point of the topping to be
     * drawn on the pizza
     */
    public void drawPepperoni(double xPos, double yPos) 
    {
        canvas.setForegroundColor(Color.RED);
        canvas.fillCircle(xPos, yPos, 30);
        //sets the outside circle centre point of pepperoni

        canvas.setForegroundColor(Color.PINK);
        canvas.fillCircle(xPos, yPos, 4);
        canvas.fillCircle(xPos - 6, yPos - 6, 4);
        canvas.fillCircle(xPos + 6, yPos + 6, 4);
        canvas.fillCircle(xPos + 6, yPos - 6, 4);
        canvas.fillCircle(xPos - 6, yPos + 6, 4);
        //draws all the pink circles in the bigger circle

        canvas.setForegroundColor(Color.WHITE);
        canvas.fillCircle(xPos - 6, yPos, 4);
        canvas.fillCircle(xPos + 6, yPos, 4);
        canvas.fillCircle(xPos, yPos + 6, 4);
        canvas.fillCircle(xPos, yPos - 6, 4);
        //draws all the white circles in the bigger circle
    }

    /**
     * Method to draw the Red Onion using the x and y positions so that it can 
     * be placed anywhere on the pizza, Positions of the pizza is drawn above.
     * @param xPos is the x position of the centre point of the topping to be
     * drawn on the pizza
     * @param yPos is the y position of the centre point of the topping to be
     * drawn on the pizza
     */
    public void drawRedOnion(double xPos, double yPos) 
    {
        canvas.setForegroundColor(Color.RED);
        canvas.fillCircle(xPos, yPos, 30);
        //Sets the outside circle centre point of Red Onion

        canvas.setForegroundColor(Color.WHITE);
        canvas.fillCircle(xPos, yPos, 24);
        canvas.setForegroundColor(Color.RED);
        canvas.fillCircle(xPos, yPos, 18);
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillCircle(xPos, yPos, 12);
        canvas.setForegroundColor(Color.RED);
        canvas.fillCircle(xPos, yPos, 6);
        // Draws each circle reducing in size as 5 circles are there  
        // co-ordinating in a Red and White Colour sequence. Diameter decreases
        // by 6 each time til we reach the smallest circle.
    }
}