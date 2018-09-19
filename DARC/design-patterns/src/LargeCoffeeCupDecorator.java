public class LargeCoffeeDecorator extends CoffeeComponentDecorator {
  public LargeCoffeeDecorator(CoffeeComponent c) {
    super(c);
  }

  @Override
  public float cost() {
    return super.cost() + .50f;
  }

  @Override
  public StringBuilder ingredients() {
    return super.ingredients().append(", ").append("[large]");
  }
}
