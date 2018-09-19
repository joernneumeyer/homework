public class VanillaDecorator extends CoffeeComponentDecorator {
  public VanillaDecorator(CoffeeComponent c) {
    super(c);
  }

  @Override
  public String name() {
    return "Vanilla";
  }

  @Override
  public float cost() {
    return super.cost() + .25f;
  }

  @Override
  public StringBuilder ingredients() {
    return super.ingredients().append(", ").append(name());
  }
}
