import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    Barista kelly = new Barista();

    CoffeeComponent coffee = SenseoCoffeeMachine.getInstance().brewCoffee(CoffeeSize.LARGE);
    coffee = new VanillaDecorator(new SugarDecorator(coffee));

    System.out.println("Your coffee is composed of " + coffee.ingredients() + ".");

    System.out.println("A coffee with your desired ingredients will cost " + coffee.cost() + " Euros!");
  }
}
