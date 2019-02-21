package restaurant;

import restaurant.Meal;

/**
 *
 * @author Pieter van den Hombergh (879417) {@code p.vandenhombergh@fontys.nl}
 */
public interface Customer {

    /**
     * End of the transactions. Ask if customer's expectation was met.
     *
     * @param question to the customer, possibly spoken by the waiter.
     *
     * @return
     */
    String areYouSattisfied( String question );

    /**
     * Serve a meal to this customer.
     *
     * @param meal
     *
     * @return the answer/payment of the customer.
     */
    String serveTo( Meal meal );

    /**
     * Get next order, if Any.
     *
     * @return array of string, representing the order or empty array when
     *         satisfied.
     *
     */
    String[] wouldLike();
    
}
