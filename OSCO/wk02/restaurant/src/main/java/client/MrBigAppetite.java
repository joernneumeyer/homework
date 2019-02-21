/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.google.common.util.concurrent.AtomicDouble;
import restaurant.Customer;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nl.fontys.sebivenlo.tallymap.TallyMap;
import nl.fontys.sebivenlo.tallymap.TallyMap2;
import restaurant.Meal;
import restaurant.Recipe;

/**
 * A simple eater.
 *
 * @author hom
 */
class MrBigAppetite implements Iterable<int[][]>, Customer {

    private final int[][][] orders = new int[][][]{
        { { 11, 7 }, { 13, 4 } },
        { { 44, 4 }, { 47, 8 }, { 13, 9 }, { 11, 5 } },
        { { 45, 1 }, { 47, 2 }, { 10, 2 } },
        { { 10, 2 }, { 33, 2 }, { 19, 2 } }
    };
    
    private final TallyMap<Integer> orderedMap;
    private final TallyMap<Integer> servedMap;

    private final Map<Integer, Recipe> pricesTable;
    private int rounds = 1;
    
    private Iterator<int[][]> itr = iterator();
    private static final String[] EMPTY_ORDER = new String[ 0 ];
    private final AtomicDouble expectedSpendings = new AtomicDouble();

    @Override
    public Iterator<int[][]> iterator() {
        return new Iterator<int[][]>() {
            private volatile int idx = -1; // start below

            @Override
            public boolean hasNext() {
                return idx < orders.length - 1;
            }

            @Override
            public int[][] next() {
                return orders[ ++idx ];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException( "Not supported yet." );
            }
        };
    }

    /**
     * Get next order, if Any.
     *
     * @return array of string, representing the order or empty array when
     * satisfied.
     *
     */
    @Override
    public String[] wouldLike() {
        if ( itr.hasNext() ) {
            int[][] r = itr.next();
            String[] result = new String[ r.length ];
            for ( int i = 0; i < r.length; i++ ) {
                int mealNr = r[ i ][ 0 ];
                int servings = r[ i ][ 1 ];
                orderedMap.addTallyForKey( mealNr, servings );
                double spending = servings * pricesTable.getOrDefault( mealNr, Recipe.WE_DO_NOT_SERVE_THAT ).getPrice();
                expectedSpendings.addAndGet( spending );
                result[ i ] = String.format( "%d,%d", r[ i ][ 0 ], r[ i ][ 1 ] );
            }
            return result;
        } else if ( --rounds >= 0 ) {
            System.err.println( "new round" );
            itr=iterator();
            return wouldLike();
        } else {
            itr = iterator();
            return EMPTY_ORDER;
        }
    }

    /**
     * Serve a meal to this customer.
     *
     * @param meal
     *
     * @return the answer/payment of the customer.
     */
    public String serveTo( Meal meal ) {
        servedMap.addTallyForKey( meal.getNumber(), 1 );
        if ( meal.getNumber() == 404 ) {
            return "Sorry to see that " + meal.getName();
        } else {
            return "Ha, my order, mjamy " + meal + ", looks good";
        }
    }
    

    /**
     * Create a customer, handing the menu items.
     *
     * @param menu the items from the menu that can be ordered.
     */
    MrBigAppetite( Map<Integer, Recipe> menu ) {
        this.pricesTable = menu;
        orderedMap = new TallyMap2<>( menu.keySet() );
        servedMap = new TallyMap2<>( menu.keySet() );
    }

    /**
     * Create a customer, handing the menu items., configurable appetite.
     *
     * @param menu the items from the menu that can be ordered.
     * @param rounds appetite
     */
    MrBigAppetite( Map<Integer, Recipe> menu, int rounds ) {

        this.pricesTable = menu;
        this.rounds = rounds;
        orderedMap = new TallyMap2<>( menu.keySet() );
        servedMap = new TallyMap2<>( menu.keySet() );
    }

    /**
     * End of the transactions. Ask if customer's expectation was met.
     *
     * @param question to the customer, possibly spoken by the waiter.
     *
     * @return
     */
    @Override
    public String areYouSattisfied( String question ) {
        System.out.println( "ordered:" + orderedMap.toString() );
        System.out.println( "received:" + servedMap.toString() );

        System.out.printf( "Client says: Expected to spend € %8.2f %n", expectedSpendings.get() );
        return "You asked \"" + question + "\" Client says: "
                + ( orderedMap.snapShotEquals( servedMap )
                ? "Yes, very. Thank you"
                : "No I missed some: "
                + orderedMap.snapShotDiff( servedMap ) );
    }
}
