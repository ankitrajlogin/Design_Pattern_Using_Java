package p01_BASIC.UML;

/*
What is Dependency in UML?
Definition: Dependency is a “uses-a” relationship.
It means one class depends on another to perform some task.
It’s a weaker relationship than association, aggregation, or composition because:
Objects are not stored as attributes.
It usually appears as a parameter, local variable, or method call.
👉 In short: If class A uses class B (temporarily) inside a method, then A depends on B.



 UML Notation
Dependency is shown as a dashed arrow -----> from the dependent class to the class it depends on.
Class A  ----->  Class B
(A depends on B)

+-------------+           +-----------+
|     Car     | --------> |   Fuel    |
+-------------+   uses    +-----------+
| drive(Fuel) |           | consume() |
+-------------+           +-----------+




 */


class Fuel {
    void consume() {
        System.out.println("Fuel is being consumed...");
    }
}

class Fourvechile {
    void drive(Fuel fuel) {   // Car DEPENDS on Fuel (method parameter)
        System.out.println("Car is driving...");
        fuel.consume();
    }
}

public class DependencyExample {
    public static void main(String[] args) {
        Fourvechile car = new Fourvechile();
        Fuel fuel = new Fuel();
        car.drive(fuel); // Car temporarily uses Fuel
    }
}

/*
Explanation:
Car does not permanently own Fuel.
It only uses Fuel when driving.
Hence, Car → Fuel is a Dependency.



 Key Points about Dependency
Temporary relationship – exists only during method execution.
No ownership – dependent class does not store the other class as an attribute.
Not as strong as association/aggregation/composition.
Notation – Dashed arrow with label <<use>> sometimes.

✅ So Dependency = Temporary “uses-a” relationship, shown with a dashed arrow.
 */


