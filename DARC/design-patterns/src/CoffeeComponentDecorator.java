import java.util.List;

public class CoffeeComponentDecorator implements CoffeeComponent {
  protected CoffeeComponent coffeeToBeMade;

  public CoffeeComponentDecorator(CoffeeComponent c) {
    this.coffeeToBeMade = c;
  }

  @Override
  public float cost() {
    return this.coffeeToBeMade.cost();
  }

  @Override
  public StringBuilder ingredients() {
    return this.coffeeToBeMade.ingredients();
  }

  @Override
  public String name() {
    return this.coffeeToBeMade.name();
  }
}
