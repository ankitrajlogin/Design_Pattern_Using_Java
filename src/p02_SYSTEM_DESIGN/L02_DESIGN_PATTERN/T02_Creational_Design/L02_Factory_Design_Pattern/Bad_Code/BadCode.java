package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T02_Creational_Design.L02_Factory_Design_Pattern.Bad_Code;


/*
🏭 Factory Pattern (Creational Pattern)
📘 Definition
Factory Pattern is a creational design pattern that provides an interface for creating objects without exposing the object creation logic to the client.

- Instead of creating objects using new, you delegate the responsibility to a factory class.
- 👉 It is like a "factory" in real life: you don’t care how a product is manufactured, you just order one from the factory and get it.



It is mostly used when:
    - You want to hide object creation logic.
    - You need to create objects dynamically based on input conditions.
    - You want loose coupling between client code and object classes.

 */


// Shape interface
interface Shape {
    void draw();
}

// Concrete classes
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// ❌ Client code (BAD)
public class BadCode {
    public static void main(String[] args) {
        // If we need a Circle
        Shape shape1 = new Circle();

        // If we need a Square
        Shape shape2 = new Square();

        // If we need a Rectangle
        Shape shape3 = new Rectangle();

        shape1.draw();
        shape2.draw();
        shape3.draw();
    }
}

/*
🚨 Problems in this code :
1. Tight Coupling – Client code directly depends on concrete classes (new Circle() etc.).
2. Difficult to Extend – If we add a new shape (e.g., Triangle), we must change client code everywhere.
3. Scattered Object Creation Logic – Every place that needs a shape has to know how to create it.
4. Violates Open/Closed Principle – Adding new shapes requires modifying existing code.


 */