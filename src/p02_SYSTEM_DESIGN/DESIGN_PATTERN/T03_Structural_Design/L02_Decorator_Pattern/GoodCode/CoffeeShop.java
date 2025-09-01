package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L02_Decorator_Pattern.GoodCode;



/*


🔹 Steps to Implement
    1. Define a Component Interface (e.g., Coffee).
    2. Create a Concrete Component (e.g., SimpleCoffee).
    3. Create an Abstract Decorator class that implements the component interface and wraps the component.
    4. Create Concrete Decorators that extend the abstract decorator.
    5. Client wraps objects dynamically to add behavior.

🔹 Components of Decorator Pattern
    - Component → interface (Coffee).
    - ConcreteComponent → base object (SimpleCoffee).
    - Decorator → abstract wrapper (CoffeeDecorator).
    - ConcreteDecorator → add-ons (MilkDecorator, SugarDecorator, etc.).

🔹 Related Terms
    - Wrapper Pattern → Decorator is also known as Wrapper Pattern.
    - Open/Closed Principle → supports extension without modification.
 */

// step 1 : Component interface
interface Coffee{
    String getDescription() ;
    double getCost() ;
}

// step 2 : Concrete component
class SimpleCoffee implements Coffee{
    public String getDescription(){
        return "Simple Coffee" ;
    }

    public double getCost(){
        return 5.0 ;
    }
}

// step 3 : Base Decorator
abstract class CoffeeDecorator implements Coffee{
    protected Coffee decoratedCoffee ;

    public CoffeeDecorator(Coffee coffee){
        this.decoratedCoffee = coffee ;
    }

    public String getDescription(){
        return decoratedCoffee.getDescription() ;
    }

    public double getCost(){
        return decoratedCoffee.getCost() ;
    }
}


// step 4 : Concrete decorators
class MilkDecorator extends CoffeeDecorator{
    public MilkDecorator(Coffee coffee){
        super(coffee) ;
    }

    public String getDescription(){
        return super.getDescription() + " + Milk" ;
    }

    public double getCost(){
        return super.getCost() + 2.0 ;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return super.getDescription() + " + Sugar"; }
    public double getCost() { return super.getCost() + 1.0; }
}

class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return super.getDescription() + " + Caramel"; }
    public double getCost() { return super.getCost() + 3.0; }
}


public class CoffeeShop {
    public static void main(String[] args){
        Coffee coffee = new SimpleCoffee() ;

        // add milk
        coffee = new MilkDecorator(coffee) ;

        // add sugar
        coffee = new SugarDecorator(coffee) ;

        // add caramel
        coffee = new CaramelDecorator(coffee) ;

        System.out.println(coffee.getDescription() + " = Rs" + coffee.getCost());
    }
}

/*
🔹 Why is Good Code Better?
    - No class explosion → only one base decorator and small concrete decorators.
    - Flexible & dynamic → can add/remove features at runtime by wrapping.
    - Open/Closed Principle → can add new decorators without changing existing code.
 */


/*
Other Examples Where You Can Use It
    - Java I/O Streams → BufferedReader, InputStreamReader, FileReader.
            Here, each wrapper adds new functionality.

    - UI Components → Adding borders, shadows, scrollbars around widgets.
    - Logger System → Add timestamp, severity, formatting dynamically to a log message.
 */
