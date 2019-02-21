package restaurant;

/**
 * One line on a order bill.
 *
 * @author ode
 * @author hom
 */
public class OrderLine {

    /**
     * The meal number from the menu.
     */
    private final int mealNR;
    /**
     * The number of servings.
     */
    private int persons;
    /**
     * The order this line belongs to.
     */
    private final int orderNr;

    /**
     * Get the recipe number.
     *
     * @return The recipe number
     */
    public int getMealNR() {
        return mealNR;
    }

    /**
     * Get the number of servings.
     *
     * @return
     */
    public int getPersons() {

        return persons;
    }

    /**
     * Create an order line for orderNr , meal and servings.
     *
     * @param orderNr the order
     * @param mealNR the meal number
     * @param persons the number of servings
     */
    public OrderLine( int orderNr, int mealNR, int persons ) {
        super();
        this.orderNr = orderNr;
        this.mealNR = mealNR;
        this.persons = persons;
    }

    /**
     * Change the number of servings.
     *
     * @param nr
     */
    void addPersons( int nr ) {
        this.persons += nr;
    }

    /**
     * Change the number of servings.
     *
     * @param nr
     */
    void delPersons( int nr ) {
        this.persons -= nr;
    }

    @Override
    public String toString() {
        return String.format( "Order: %3d \tMenu Number: %3d \tServings: %3d",
                orderNr, mealNR, +persons );
    }
}