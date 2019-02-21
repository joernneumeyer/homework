package restaurant;

/**
 * Simple recipe with name and preparation time only. We just boil some water.
 *
 * @author hom
 * @author ode
 */
public class Recipe {

    public static final Recipe WE_DO_NOT_SERVE_THAT = new Recipe( "no go", Integer.MAX_VALUE, 0.0d );
    /**
     * Name.
     */
    private final String name;
    /**
     * Time to "cook" it.
     */
    private final int preparationTime;
    /**
     * Price, to keep the chefs attention.
     */
    private final double price;

    /**
     * A recipe with name and cook time.
     *
     * @param name
     * @param preparationTime
     * @param price
     */
    public Recipe( String name, int preparationTime, double price ) {
        super();
        this.name = name;
        this.preparationTime = preparationTime;
        this.price = price;
    }

    /**
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Get time it takes to prepare this.
     *
     * @return
     */
    public int getPreparationTime() {
        return preparationTime;
    }
}
