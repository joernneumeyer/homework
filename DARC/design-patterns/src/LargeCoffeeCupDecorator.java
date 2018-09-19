public class LargeCoffeeCupDecorator extends CoffeeComponentDecorator {
  public LargeCoffeeCupDecorator(CoffeeComponent c) {
    super(c);
  }

  @Override
  public String name() {
    return "Large Cup";
  }

  @Override
  public float cost() {
    return super.cost() + .50f;
  }

  @Override
  public StringBuilder ingredients() {
    return super.ingredients().append(", ").append(name());
  }
}
