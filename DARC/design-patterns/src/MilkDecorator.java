import java.util.List;

public class MilkDecorator extends CoffeeComponentDecorator {
  public MilkDecorator(CoffeeComponent c) {
    super(c);
  }

  @Override
  public float cost() {
    return super.cost() + .20f;
  }

  @Override
  public String name() {
    return "Milk";
  }

  @Override
  public StringBuilder ingredients() {
    return super.ingredients().append(", ").append(this.name());
  }
}
