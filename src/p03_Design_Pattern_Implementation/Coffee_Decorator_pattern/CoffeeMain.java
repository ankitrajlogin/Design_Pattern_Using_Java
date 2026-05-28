package p03_Design_Pattern_Implementation.Coffee_Decorator_pattern;


interface Coffee {
    String getDescription();
    int cost();
}

class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public int cost() {
        return 100;
    }
}

abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee ;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public int cost() {
        return coffee.cost() + 20;
    }
}

class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public int cost() {
        return coffee.cost() + 10;
    }
}

class CreamDecorator extends CoffeeDecorator {

    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Cream";
    }

    @Override
    public int cost() {
        return coffee.cost() + 30;
    }
}


public class CoffeeMain{
    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new CreamDecorator(coffee);
        coffee = new CreamDecorator(coffee);

        System.out.println(coffee.getDescription());

        System.out.println("Total Cost: " + coffee.cost());
    }
}