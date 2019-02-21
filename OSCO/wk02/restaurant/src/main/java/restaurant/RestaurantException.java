package restaurant;

/**
 * Models the shouting, yelling, grumbling, moaning
 * and complaining in restaurants.
 * References: Have a look at the Gordon Ramsay shows.
 * @author hom
 */
public class RestaurantException extends Exception {

    /**
     * Serialisable classes need a serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception with string cause. 
     * @param message
     */
    public RestaurantException(String message) {
        super(message);
    }

    /**
     * Exception caused by other exception.
     * @param t
     */
    public RestaurantException(Throwable t){
        super(t);
    }
}
