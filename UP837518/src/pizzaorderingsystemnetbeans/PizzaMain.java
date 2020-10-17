package pizzaorderingsystemnetbeans;

/**
 * Class to start the running of the Pizza Ordering System.  You should not 
 * need to change this class
 * @author UP837518
 */
public class PizzaMain 
{

    /**
     *
     * @param args draws the order screen, everthing in the ordering system and
     * the total cost of all pizza's ordered.
     */
    public static void main(String[] args) 
    {
        OrderingSystem orders = new OrderingSystem();
        orders.drawOrderScreen();
        orders.startOrdering();
        orders.drawTotalCost();
    }
    
}
