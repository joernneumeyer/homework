package restaurant;

//import client.Customer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static restaurant.Utils.*;

/**
 * Naive restaurant for concurrency lab.
 *
 * @author ode
 * @author hom
 */
public class Restaurant {

  /**
   * Name of the joint.
   */
  private String name;
  /**
   * Last order number to keep track of the orders.
   */
  private int orderCount;
  /**
   * The cookbook a.k.a. preparation rules.
   */
  private Map<Integer, Recipe> recipes;
  /**
   * What can be ordered here
   */
  //private ArrayList<String> menu;
  /**
   * Queue of orders.
   */
  private Queue<Order> orderQueue;
  /**
   * Queue of meals.
   */
  private Queue<Meal> mealsReadyQueue;
  /**
   * Format for our price list and bills
   */
  private final DecimalFormat df = new DecimalFormat("##.00");
  /**
   * The one to feed and let order.
   */
  private Customer customer;
  private int mealsPreparedCount = 0;
  private long startTime = 0;
  private long totalCookTime = 0;
  private double turnOver = 0.0D;

  public Customer getCustomer() {
    return customer;
  }

  public Restaurant setCustomer(Customer customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Construct a named restaurant.
   *
   * @param name
   */
  public Restaurant(String name) {
    this.name = name;
    orderCount = 0;
    orderQueue = new Queue<Order>();
    mealsReadyQueue = new Queue<Meal>();
    recipes = new LinkedHashMap<Integer, Recipe>();
    //menu = new ArrayList<String>();
    importMenu();
  }

  public double howMuchIs(int servings, int dishNumber) {
    return servings * recipes.get(dishNumber).getPrice();
  }

  /**
   * Import the meals. Putting meals in a separate text file avoid hard coding
   * of meal properties into this source code. It would acquire a bad smell over
   * time ;-)).
   */
  final void importMenu() {
    Collection<String> mealInfo = Input.getLines("meals.txt");
    for (String s : mealInfo) {
      String[] ss = s.split("\\s*;\\s*");
      // test for parsability
      if (ss[0].matches("\\d+")) {
        int mealNR = Integer.parseInt(ss[0]);
        String mname = ss[1];
        double price = Float.parseFloat(ss[2]);
        int prepTime = Integer.parseInt(ss[3]);
        recipes.put(mealNR, new Recipe(mname, prepTime, price));
//                menu.add( mealNR + ":\t" + mname + "\tprice:\t" + df.format(
//                                price ) );
      }
    }
  }

  /**
   * Place order. An order is a list of strings, each string interpreted as an
   * integer pair, separated by a comma and possibly white space. The first
   * integer is the meal number from the menu, the second value is the number of
   * servings.
   *
   * @param ordered array of strings
   * @return the next order number
   * @throws RestaurantException
   */
  public int submitOrder(String... ordered) throws RestaurantException {
    orderCount++;
    Order order = new Order(orderCount);
    for (int i = 0; i < ordered.length; i++) {
      String[] lineParts = ordered[i].split("\\s*,\\s*", 2);
      int mealNR = 0;
      int servings = 0;
      try {
        mealNR = Integer.parseInt(lineParts[0].trim());
        servings = Integer.parseInt(lineParts[1].trim());
      } catch (NumberFormatException nfe) {
        throw new RestaurantException(nfe);
      }
      if (!recipes.containsKey(mealNR)) {
        // prepare a 401, with as many servings asked.
        Meal notAvailable = new Meal(orderCount, 404, String.format(
                "Menu item %d does not exist.", mealNR));
        for (int s = 0; s < servings; s++) {
          System.out.println(customer.serveTo(notAvailable));
        }
        //                throw new RestaurantException( "Order nr. " + orderCount
        //                        + ": a non existing meal (nr.=" + mealNR + ") ordered!" );
      } else {
        order.addMeal(mealNR, servings);
      }
    }
    System.out.println(order.toString());
    orderQueue.put(order);
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Logger.getLogger(Restaurant.class.getName()).
              log(Level.SEVERE, null, ex);
    }
    return orderCount;
  }

  /**
   * Are there orders in work?
   *
   * @return
   */
  public boolean hasOrders() {
    return !orderQueue.empty();
  }

  /**
   * Removes one order from the order list.
   *
   * @return info for the next order
   *
   */
  public String getNextOrder() {
    return orderQueue.get().toString();
  }

  /**
   * Process the orders in FIFO order.
   */
  public void procesOrders() {
    while (true) {
      if (this.orderQueue.empty()) {
        return;
      }

      Order order = orderQueue.get();

      int orderNR = order.getNumber();
      for (OrderLine ol : order) { //while (order.hasOrderLines()) {
        //OrderLine ol = order.getOrderLine();
        int mealNR = ol.getMealNR();
        int persons = ol.getPersons();
        for (int p = 0; p < persons; p++) {
          this.mealsReadyQueue.put(this.
                  prepareMeal(orderNR, mealNR));
        }
      }

    }
  }

  /**
   * Prepares a meal according to recipe (preparation time).
   *
   * @param orderNR
   * @param mealNR
   * @return the prepared meal.
   */
  private Meal prepareMeal(int orderNR, int mealNR) {
    Recipe recipe = recipes.get(mealNR);
    String mealName = recipe.getName();
    int procTime = recipe.getPreparationTime();
    try {
      Thread.sleep(procTime);
      totalCookTime += procTime;
      mealsPreparedCount++;
    } catch (InterruptedException ex) {
      Logger.getLogger(Restaurant.class.getName()).
              log(Level.SEVERE, null, ex);
    }
    return new Meal(orderNR, mealNR, mealName);
  }

  /**
   * Is there anything to serve?
   *
   * @return true if there are meals that can be obtained via the getLines
   * method
   */
  public boolean hasMeals() {
    return !mealsReadyQueue.empty();
  }

  /**
   * Get the next meal to serve.
   *
   * @return information of the next meal belonging to a certain order
   */
  public Meal getNextMeal() {
    return mealsReadyQueue.get();
  }

  /**
   * Print the menu of the restaurant. Each meal has a number and this number
   * should be used to order a meal.
   */
  public void printMenu() {
    printSeparator("Menu of restaurant " + name, '+');
    for (Integer k : recipes.keySet()) {
      Recipe get = recipes.get(k);
      System.out.printf("%2d %20s  %6.2f%n", k, get.getName(), recipes.get(k).getPrice());
    }
    printSeparator("We welcome you at restaurant " + name, '+');
  }

  public void serveReadyMeals() {
    if (this.hasMeals()) {
      printSeparator("Pleased to serve your meals");

      while (this.hasMeals()) {
        Meal meal = this.getNextMeal();
        turnOver += recipes.get(meal.getNumber()).getPrice();
        System.out.println(customer.serveTo(meal));
      }

      printSeparator("Bon appetit");
    }
  }

  /**
   * String representation of the restaurant.
   *
   * @return a string.
   */
  @Override
  public String toString() {
    return "Restaurant " + name;
  }

  public void closeRestaurant() {
    printSeparator(this.toString() + "is closed", '#');
    System.out.printf("total cookingTime = %d milliseconds on %d meals.\n",
            this.totalCookTime,
            this.mealsPreparedCount);
    System.out.printf("restaurant %s was open for %d miliseconds\n",
            this.name, (System.currentTimeMillis() - startTime));
    System.out.printf("Turnover this round: € %10.2f\n", turnOver);
  }

  public void openRestaurant() {
    startTime = System.currentTimeMillis();
    printSeparator("Welcome, " + this.toString() + " is now open", '$');
  }

  public Set<Integer> getMenuNumbers() {
    return recipes.keySet();
  }

  public Map<Integer, Recipe> getMenu() {

    return recipes;
  }
}
