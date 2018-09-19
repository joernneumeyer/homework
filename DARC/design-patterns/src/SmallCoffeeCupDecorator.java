public class SmallCoffeeCupDecorator extends CoffeeComponentDecorator {
  public SmallCoffeeCupDecorator(CoffeeComponent c) {
    super(c);
  }

  @Override
  public String name() {
    return "Small Cup";
  }

  @Override
  public float cost() {
    return super.cost() - .30f;
  }

  @Override
  public StringBuilder ingredients() {
    return super.ingredients().append(", ").append(name());
  }
}
