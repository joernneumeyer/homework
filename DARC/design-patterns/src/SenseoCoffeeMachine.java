public class SenseoCoffeeMachine implements CoffeeMachine {
  private static CoffeeMachine _instance = null;

  private SenseoCoffeeMachine() { }

  public static CoffeeMachine getInstance() {
    if (SenseoCoffeeMachine._instance == null) {
      SenseoCoffeeMachine._instance = new SenseoCoffeeMachine();
    }

    return SenseoCoffeeMachine._instance;
  }

  @Override
  public CoffeeComponent brewCoffee(CoffeeSize size) {
    CoffeeComponent coffee = new SenseoCoffee();
    switch (size) {
      case LARGE:
        coffee = new LargeCoffeeCupDecorator(coffee);
        break;

      case SMALL:
        coffee = new SmallCoffeeCupDecorator(coffee);
        break;
    }
    return coffee;
  }

  private class SenseoCoffee implements Coffee {
    @Override
    public float cost() {
      return 1f;
    }
  }
}
