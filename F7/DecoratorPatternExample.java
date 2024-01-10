// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// ConcreteComponent class
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}

// Decorator abstract class
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// ConcreteDecorator class - MilkDecorator
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.0;
    }
}

// ConcreteDecorator class - SugarDecorator
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}

// Client code
public class DecoratorPatternExample {
    public static void main(String[] args) {
        // Creating a simple coffee
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Simple Coffee: " + simpleCoffee.getDescription() + ", Cost: $" + simpleCoffee.getCost());

        // Adding milk to the coffee
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Milk Coffee: " + milkCoffee.getDescription() + ", Cost: $" + milkCoffee.getCost());

        // Adding sugar to the coffee
        Coffee sweetCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Sweet Coffee: " + sweetCoffee.getDescription() + ", Cost: $" + sweetCoffee.getCost());
    }
}