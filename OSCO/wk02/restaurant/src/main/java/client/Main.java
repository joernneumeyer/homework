package client;

import foo.ValueWrapper;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.Restaurant;
import restaurant.RestaurantException;
import static restaurant.Utils.*;

/**
 * Restaurant driver class. This implements Donalds orginal 'algorithm', take
 * two orders, cook and serve them, all in a loop until the customer is
 * satisfied.
 *
 * @author ode
 * @author hom
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    Restaurant china = new Restaurant("Mei Ling");
    china.printMenu();
    china.openRestaurant();
    int rounds = 1;
    if (args.length > 0) {
      try {
        rounds = Integer.parseInt(args[0]);
      } catch (Exception whateverItakethatasas1) {
      }
    }
    MrBigAppetite mrBig = new MrBigAppetite(china.getMenu(), rounds);
    ValueWrapper<Boolean> ordering = new ValueWrapper<>(true);
    ValueWrapper<Boolean> processing = new ValueWrapper<>(true);
    ValueWrapper<Boolean> serving = new ValueWrapper<>(true);

    Thread processingThread = new Thread(() -> {
      while(ordering.getValue() || china.hasOrders()) {
        china.procesOrders();
      }
      processing.setValue(false);
    });
    Thread servingThread = new Thread(() -> {
      while(processing.getValue() || china.hasMeals()) {
        china.serveReadyMeals();
      }
      serving.setValue(false);
    });
    
    processingThread.start();
    servingThread.start();
    
    china.setCustomer(mrBig);
    for (int r = 0; r < rounds; r++) {
      for (int i = 0; i < 4; ++i) {
        try {
          china.submitOrder(mrBig.wouldLike());
        } catch (RestaurantException e) {
          System.out.println(e.getMessage());
        }
      }
      
      /*try {
                china.submitOrder( mrBig.wouldLike() );
            } catch ( RestaurantException e ) {
                System.out.println( e.getMessage() );
            }
            //china.procesOrders();
            //china.serveReadyMeals();
            try {
                china.submitOrder( mrBig.wouldLike() );
            } catch ( RestaurantException e ) {
                System.out.println( e.getMessage() );
            }
            try {
                china.submitOrder( mrBig.wouldLike() );
            } catch ( RestaurantException e ) {
                System.out.println( e.getMessage() );
            }*/
      //china.procesOrders();
      //china.serveReadyMeals();
    }
    ordering.setValue(false);
    processingThread.join();
    servingThread.join();
    System.out.println(mrBig.areYouSattisfied("Are you sattisfied?"));
    china.closeRestaurant();
    System.exit(0);
  }
}
