public interface Coffee extends CoffeeComponent {
  @Override
  default String name() {
    return "Coffee";
  }

  @Override
  default StringBuilder ingredients() {
    return (new StringBuilder()).append(name());
  }
}