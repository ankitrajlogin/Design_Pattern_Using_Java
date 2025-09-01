package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L02_Decorator_Pattern.BadCode;


/*
🔹 What is the Decorator Pattern?
    - The Decorator Pattern is a structural design pattern that lets you attach new behaviors to objects dynamically (at runtime) without modifying their original class.
    - 👉 Think of it like adding layers of wrapping around a gift box—each wrapper adds new "features" but still keeps the original gift intact.


🔹 When do we use it?
    - When you want to add extra functionality to an object without modifying its class.
    - When subclassing leads to class explosion (too many subclasses just to add small variations).
    - When behavior should be extensible at runtime.
 */



/*
Problem Statement (Real-Life Example)
Suppose we’re building a coffee shop ordering system:
    - Customers can order different types of coffee.
    - They can also add extra features like milk, sugar, caramel, whipped cream, etc.
 */


// Coffee interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Simple coffee
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double getCost() { return 5.0; }
}

// ❌ Adding milk means creating a new class
class MilkCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee + Milk"; }
    public double getCost() { return 7.0; }
}

// ❌ Adding sugar means another class
class SugarCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee + Sugar"; }
    public double getCost() { return 6.0; }
}

// ❌ Milk + Sugar → another class
class MilkSugarCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee + Milk + Sugar"; }
    public double getCost() { return 8.0; }
}

// Main
public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new MilkSugarCoffee();
        System.out.println(coffee.getDescription() + " = $" + coffee.getCost());
    }
}

/*
❌ Problems in Bad Code:
    - Class Explosion → every new combination of add-ons requires a new class.
    - Rigid → if tomorrow we add Caramel or Whipped Cream, we must create even more classes.
    - Hard to extend at runtime → can’t add/remove toppings dynamically.

 */
