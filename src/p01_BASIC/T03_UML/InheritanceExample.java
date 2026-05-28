package p01_BASIC.T03_UML;

/*
Inheritance (Generalization in UML)
🔹 Definition:
Inheritance is an “is-a” relationship where one class (child/subclass) acquires the properties and behaviors (fields and methods) of another class (parent/superclass).
It allows code reusability.
It supports polymorphism (overriding methods).

1. UML Notation for Inheritance
Represented by a solid line with a closed, hollow arrowhead pointing from the child class → parent class.

Example:
        [Vehicle]
        ▲
        |
        [Car]


 */


// Parent class (Superclass)
class Vehicle {
    String brand;
    int year;

    Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}

// Child class (Subclass)
class Car extends Vehicle {
    int doors;

    Car(String brand, int year, int doors) {
        super(brand, year);  // call parent constructor
        this.doors = doors;
    }

    // Overriding method
    @Override
    void displayInfo() {
        System.out.println("Car -> Brand: " + brand + ", Year: " + year + ", Doors: " + doors);
    }
}

// Main class
public class InheritanceExample {
    public static void main(String[] args) {
        Car car = new Car("Tesla", 2023, 4);
        car.displayInfo();
    }
}



/*

UML Diagram of Example

            +------------------+
            |    Vehicle       |
            +------------------+
            | - brand: String  |
            | - year: int      |
            +------------------+
            | + displayInfo()  |
            +------------------+
                    ▲
                    |
            +------------------+
            |      Car         |
            +------------------+
            | - doors: int     |
            +------------------+
            | + displayInfo()  |
            +------------------+



Key Points about Inheritance
- “IS-A” Relationship
Example: Car is a Vehicle.

Code Reusability
Reuse parent methods & attributes.

Method Overriding
Child class can redefine parent methods.

Access Modifiers:
protected → accessible in subclasses.
private → not inherited.
public → inherited and accessible.

Constructor Chaining
Child constructor calls super() to initialize parent properties.
 */