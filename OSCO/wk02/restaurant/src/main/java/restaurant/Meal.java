package restaurant;

/**
 * A meal representation.
 * @author ode
 */
public final class Meal {

    /** The number in the menu. */
    private final int number;
    /** Name of the meal. */
    private final String name;
    /** The order this meal belongs to. */
    private final int orderNR;

    /**
     * Prepare a meal for orderNr for a customer.
     * @param orderNR for order
     * @param number meal number in menu.
     * @param name of the meal
     */
    public Meal(int orderNR, int number, String name) {
        this.orderNR = orderNR;
        this.number = number;
        this.name = name;
    }

    /**
     * Get the menu number for this meal.
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * String rep of the meal.
     * @return
     */
    @Override
    public String toString() {
        return orderNR + ": \t" + name + " (" + number + ")";
    }

    /**
     * Get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the order number this meal belongs to.
     * @return
     */
    public int getOrderNR() {
        return orderNR;
    }
}
