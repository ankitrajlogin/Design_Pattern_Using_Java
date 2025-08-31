package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L02_Factory_Design_Pattern.Good_Code;


/*

🧩 Components of Factory Pattern
    - Product (Interface/Abstract Class) → e.g., Shape
    - Concrete Products → Circle, Square, Rectangle
    - Factory (Creator) → ShapeFactory
    - Client → Uses the factory to get objects

🛠 Steps to Implement Factory Pattern
    - Define an Interface / Abstract Class (e.g., Shape).
    - Create Concrete Implementations (e.g., Circle, Square).
    - Create a Factory Class with a method that returns objects of the interface.
    - Client Uses Factory instead of new.

 */

// Step 1: Shape Interface
interface Shape {
    void draw();
}

// Step 2: Concrete Classes
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

// Step 3: Factory Class
class ShapeFactory {
    // Factory method to create objects
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        return null;
    }
}

// Step 4: Client code uses Factory
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape1 = factory.getShape("CIRCLE");
        Shape shape2 = factory.getShape("SQUARE");
        Shape shape3 = factory.getShape("RECTANGLE");

        shape1.draw();
        shape2.draw();
        shape3.draw();
    }
}


/*
//explanation
1. The client doesn’t use new keyword → It asks the ShapeFactory to create the object.

2. The creation logic is in one place (factory), so extending the code (adding Triangle) doesn’t break the client code.

3. This follows Open/Closed Principle – we can add new shapes without modifying client logic.

 */


/*
Benefits of Factory Pattern
    - Loose Coupling → Client doesn’t depend on concrete classes.
    - Centralized Object Creation → Easy to manage and maintain.
    - Easy to Extend → Add new product classes without touching client code.
    - Cleaner Code → Reduces repetitive new object creation.



🚀 Real-World Examples of Factory Pattern
1. Java Collections Framework
    - List<Integer> list = new ArrayList<>();
    - But often you use Collections.synchronizedList(list) → Factory method.

2. Logging Frameworks
    - Logger logger = LoggerFactory.getLogger(MyClass.class);

3. Database Connections
    - JDBC DriverManager.getConnection(url, user, password)

4. UI Toolkit
    - Button factory creates buttons for different OS (Windows/Mac/Linux).
 */