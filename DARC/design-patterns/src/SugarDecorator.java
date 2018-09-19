import java.util.List;

public class SugarDecorator extends CoffeeComponentDecorator {
  public SugarDecorator(CoffeeComponent c) {
    super(c);
  }

  @Override
  public float cost() {
    return super.cost() + .15f;
  }

  @Override
  public String name() {
    return "Sugar";
  }

  @Override
  public StringBuilder ingredients() {
    return super.ingredients().append(", ").append(this.name());
  }
}
