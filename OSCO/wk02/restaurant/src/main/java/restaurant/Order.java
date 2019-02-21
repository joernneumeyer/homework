package restaurant;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of meals times servings. Supports iteration.
 *
 * @author ode
 */
public class Order implements Iterable<OrderLine> {

    /**
     * The sequence number of the orders.
     */
    private final int number;
    /**
     * The lines in the order. Any collection implementation will do.
     */
    private final List<OrderLine> lines = new LinkedList<OrderLine>();

    /**
     * Create an empty order with a sequence number.
     *
     * @param number order number
     */
    Order( int number ) {
        super();
        this.number = number;
    }

    /**
     * Add a meal with specified portions or servings.
     *
     * @param mealNR meal number from the menu
     * @param servings
     */
    void addMeal( int mealNR, int servings ) {
        lines.add( new OrderLine( number, mealNR, servings ) );
    }

    /**
     * Get the order number.
     *
     * @return
     */
    int getNumber() {
        return number;
    }

    /**
     * Return an iterator for this collection of order lines.
     *
     * @return
     */
    @Override
    public Iterator<OrderLine> iterator() {
        return lines.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( Utils.createSeparator( "order number " + getNumber(),'%' ) )
                .append( "\n" );
        for ( OrderLine line : this ) {
            sb.append( line.toString() )
                    .append( "\n" );
        }
        sb.append( Utils.createSeparator( "end of order number " + getNumber(),'%' ) );
        return sb.toString();
    }
}
