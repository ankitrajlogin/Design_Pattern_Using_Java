# Object-Oriented Programming (OOP) in Java

Java is a **purely object-oriented** programming language (with some procedural features) that follows the OOP paradigm. OOP helps in **modular, reusable, and maintainable** code.

---

## 📜 Table of Contents
1. [Introduction to OOP](#introduction-to-oop)
2. [Key Features of OOP](#key-features-of-oop)
3. [Class and Object](#class-and-object)
4. [Constructors](#constructors)
5. [this Keyword](#this-keyword)
6. [Static Keyword](#static-keyword)
7. [Encapsulation](#encapsulation)
8. [Inheritance](#inheritance)
9. [Polymorphism](#polymorphism)
10. [Abstraction](#abstraction)
11. [Interfaces](#interfaces)
12. [Method Overloading](#method-overloading)
13. [Method Overriding](#method-overriding)
14. [final Keyword](#final-keyword)
15. [super Keyword](#super-keyword)
16. [Access Modifiers](#access-modifiers)
17. [Inner Classes](#inner-classes)
18. [Object Class Methods](#object-class-methods)
19. [OOP Best Practices](#oop-best-practices)

---

## Introduction to OOP

**OOP** is a programming paradigm based on the concept of **objects**, which have:
- **State** (fields/attributes)
- **Behavior** (methods)

Java implements OOP concepts to make applications **modular, reusable, and easier to maintain**.

---

## Key Features of OOP

1. **Encapsulation** — Wrapping data and methods in a single unit.
2. **Inheritance** — Acquiring properties and methods from another class.
3. **Polymorphism** — Same method name but different behavior.
4. **Abstraction** — Hiding implementation details and showing only necessary information.

---

## Class and Object

**Class** — Blueprint for objects.  
**Object** — Instance of a class.

```java
class Car {
    String brand;
    int speed;

    void drive() {
        System.out.println(brand + " is driving at " + speed + " km/h");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.brand = "Tesla";
        myCar.speed = 120;
        myCar.drive();
    }
}
```

## Constructors
Constructors are special methods called when an object is created.  
A constructor in Java is a special method that:
- Has the same name as the class.
- Has no return type (not even void).
- Is called automatically when an object is created using new.

```java
class Person {
    String name;
    int age;

    // Default Constructor
    Person() {
        name = "Unknown";
        age = 0;
    }

    // Parameterized Constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Ankit", 22);
    }
}
```

## this Keyword
- this refers to the current object.
- this is a reference variable in Java.
- It always points to the current object (the object whose method or constructor is being executed).
- Automatically available in non-static methods and constructors.

```java
class Student {
    String name;

    Student(String name) {
        this.name = name; // distinguishes between field and parameter
    }
}
```

## Static Keyword
- The variable/method belongs to the class, not to any specific object.
- All objects of that class share the same copy of the static variable.
- Static members are created when the class is loaded in memory, before any object is created.
- We can access them without creating an object (using ClassName.member).

```java 
class Counter {
    static int count = 0;

    Counter() {
        count++;
        System.out.println("Count: " + count);
    }
}

public class Main {
    public static void main(String[] args) {
        new Counter(); // count = 1
        new Counter(); // count = 2
    }
}
```

## 1. Encapsulation

Hiding fields and providing controlled access using getters and setters.
- Encapsulation means bundling the data (fields) and the methods that operate on that data into a single unit (class).
- It also means restricting direct access to that data by making it private and providing controlled access via getter and setter methods.

```java 
class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
}
```

## 2. Inheritance
- Inheritance is a mechanism where one class (child/subclass) acquires properties and behaviors (fields & methods) from another class (parent/superclass).
- Purpose: Code reusability and to represent "is-a" relationships.

```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();
        d.bark();
    }
}

```


## 3. Polymorphism
**Polymorphism** = “many forms”.  
In Java, it means the same method name can perform different actions depending on:
- **Compile-time**: Method Overloading
- **Runtime**: Method Overriding


### Types of Polymorphism

### A) Compile-time Polymorphism (Static Binding)

- Achieved through **Method Overloading**.
- Decision of which method to call is made at **compile time**.
- Same method name, but different parameter list.

#### Example:
```java
class MathUtil {
    int sum(int a, int b) {
        return a + b;
    }
    double sum(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        MathUtil m = new MathUtil();
        System.out.println(m.sum(5, 3));       // calls sum(int, int)
        System.out.println(m.sum(5.5, 3.2));   // calls sum(double, double)
    }
}
```
#### Output:

```text
8
8.7
```





## Abstraction

### Using abstract classes.

```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() { System.out.println("Drawing Circle"); }
}

```

### Interfaces
```java
interface Vehicle {
    void start();
}

class Bike implements Vehicle {
    public void start() { System.out.println("Bike starting..."); }
}

```

## Method Overriding

Same method signature in subclass.

```java
class Parent {
    void greet() { System.out.println("Hello from Parent"); }
}

class Child extends Parent {
    void greet() { System.out.println("Hello from Child"); }
}
```

---

## final Keyword

- **final variable** — constant.
- **final method** — cannot be overridden.
- **final class** — cannot be inherited.

---

## super Keyword

Refers to parent class members.

```java
class Animal {
    String color = "white";
}

class Dog extends Animal {
    String color = "black";
    void printColor() {
        System.out.println(super.color);
    }
}
```

---

## Access Modifiers

| Modifier  | Within Class | Same Package | Subclass | World |
|-----------|--------------|--------------|----------|-------|
| private   | ✅           | ❌           | ❌       | ❌    |
| default   | ✅           | ✅           | ❌       | ❌    |
| protected | ✅           | ✅           | ✅       | ❌    |
| public    | ✅           | ✅           | ✅       | ✅    |

---

## Inner Classes

```java
class Outer {
    class Inner {
        void show() { System.out.println("Inner class"); }
    }
}
```

---

## Object Class Methods

- `toString()`
- `equals()`
- `hashCode()`
- `clone()`
- `getClass()`

---

## OOP Best Practices

- Use meaningful class names.
- Keep fields private, use getters/setters.
- Prefer composition over inheritance.
- Keep methods small and focused.
- Follow **SOLID** principles.



# Inheritance in Java – Complete Types 

Inheritance allows one class to acquire (reuse) the properties and methods of another class.

## 1️⃣ Why Inheritance?

- **Code reusability** – Avoid rewriting same code.
- **Extensibility** – Extend existing code for new needs.
- **Organization** – Natural hierarchy in classes.
- **Polymorphism support** – Enables runtime method overriding.

## 2️⃣ Basic Syntax
```java
class Parent {
    void greet() {
        System.out.println("Hello from Parent");
    }
}

class Child extends Parent {
    void display() {
        System.out.println("I am Child");
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.greet();   // inherited from Parent
        c.display(); // from Child
    }
}
```

## 3️⃣ Types of Inheritance in Java

Java supports:

- Single Inheritance
- Multilevel Inheritance
- Hierarchical Inheritance
- Hybrid Inheritance (via interfaces only)

Java does **not** support multiple inheritance with classes (to avoid ambiguity, aka diamond problem).  
But multiple inheritance is possible with interfaces.

### A. Single Inheritance

One child inherits from one parent.

```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();
        d.bark();
    }
}
```

**Output:**
```
Eating...
Barking...
```

📌 Diagram:
```
Animal
   ↑
  Dog
```

### B. Multilevel Inheritance

A class inherits from a class, which inherits from another class.

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

class Puppy extends Dog {
    void weep() { System.out.println("Weeping..."); }
}

public class Main {
    public static void main(String[] args) {
        Puppy p = new Puppy();
        p.eat();
        p.bark();
        p.weep();
    }
}
```

📌 Diagram:
```
Animal
   ↑
  Dog
   ↑
 Puppy
```

### C. Hierarchical Inheritance

Multiple classes inherit from the same parent.

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

class Cat extends Animal {
    void meow() { System.out.println("Meowing..."); }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();
        d.bark();

        Cat c = new Cat();
        c.eat();
        c.meow();
    }
}
```

📌 Diagram:
```
       Animal
      /      \
   Dog       Cat
```

### D. Multiple Inheritance (via Interfaces)

Java doesn’t allow multiple inheritance with classes, but you can use interfaces.

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Flying..."); }
    public void swim() { System.out.println("Swimming..."); }
}

public class Main {
    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();
        d.swim();
    }
}
```

📌 Diagram:
```
Flyable    Swimmable
   \         /
     \     /
      Duck
```

### E. Hybrid Inheritance

Combination of more than one type of inheritance, possible only with interfaces in Java.

```java
interface Engine {
    void start();
}

interface Wheels {
    void rotate();
}

interface Vehicle extends Engine, Wheels {
    void honk();
}

class Car implements Vehicle {
    public void start() { System.out.println("Engine started"); }
    public void rotate() { System.out.println("Wheels rotating"); }
    public void honk() { System.out.println("Beep Beep!"); }
}

public class Main {
    public static void main(String[] args) {
        Car c = new Car();
        c.start();
        c.rotate();
        c.honk();
    }
}
```

📌 Diagram:
```
Engine   Wheels
    \     /
     Vehicle
        |
       Car
```

## 4️⃣ Important Keywords in Inheritance

**super Keyword**  
Used to call parent’s constructor or methods.

```java
class Animal {
    Animal() {
        System.out.println("Animal created");
    }
}

class Dog extends Animal {
    Dog() {
        super(); // calls Animal()
        System.out.println("Dog created");
    }
}
```

**final in Inheritance**
- `final class` → cannot be inherited.
- `final method` → cannot be overridden.

```java
final class Animal {} // can't be extended
```

## 5️⃣ Method Overriding (Runtime Polymorphism)

```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Bark"); }
}
```

## 6️⃣ Constructors and Inheritance

When a child object is created:

1. Parent constructor runs first.
2. Then child’s constructor runs.

```java
class Parent {
    Parent() { System.out.println("Parent constructor"); }
}

class Child extends Parent {
    Child() { System.out.println("Child constructor"); }
}
```

## 7️⃣ Object Class in Inheritance

Every class in Java implicitly inherits from Object class.

```java
class MyClass { }
public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        System.out.println(obj.toString()); // from Object class
    }
}
```

## ✅ Summary Table of Inheritance Patterns in Java

| Type        | Supported by Classes? | Supported by Interfaces? |
|-------------|----------------------|--------------------------|
| Single      | ✅                    | ✅                        |
| Multilevel  | ✅                    | ✅                        |
| Hierarchical| ✅                    | ✅                        |
| Multiple    | ❌                    | ✅                        |
| Hybrid      | ❌                    | ✅                        |






# `super`

In Java, `super` is a reference to the **immediate parent class** of the current object.  
It is used in three main ways:

---

## **A) Call the Parent’s Constructor**
When a subclass is created, its parent’s constructor **always runs first**.

- Use `super(arguments...)` to explicitly call a specific constructor in the parent class.
- If you don’t call `super(...)` yourself, Java automatically calls the parent’s **no-argument constructor** (`super()`).

**Example:**
```java
class Card {
    String cardNo, name;

    Card(String cardNo, String name) {
        this.cardNo = cardNo;
        this.name = name;
        System.out.println("Card constructor called");
    }
}

class CreditCard extends Card {
    CreditCard(String cardNo, String name) {
        super(cardNo, name); // calls parent constructor
        System.out.println("CreditCard constructor called");
    }
}

public class Main {
    public static void main(String[] args) {
        CreditCard cc = new CreditCard("1234", "John Doe");
    }
}
```

**Output:**
```
Card constructor called
CreditCard constructor called
```

---

## **B) Access Parent Class Methods**
If the child class **overrides** a method from the parent, you can still call the parent’s version using `super.methodName()`.

```java
class Card {
    void printType() {
        System.out.println("Generic Card");
    }
}

class CreditCard extends Card {
    @Override
    void printType() {
        super.printType(); // calls parent method
        System.out.println("Credit Card");
    }
}
```

---

## **C) Access Parent Class Fields**
If the child class **hides** a field (same variable name in both), you can use `super.fieldName` to get the parent’s version.

```java
class Card {
    String type = "Generic Card";
}

class CreditCard extends Card {
    String type = "Credit Card";

    void printType() {
        System.out.println(super.type); // parent field
        System.out.println(this.type);  // child field
    }
}
```

---

## **Key Rules**
1. `super()` must be the **first statement** in a constructor if you use it.
2. If you don’t call `super(...)`, Java automatically inserts `super()` (no args) — but **only if** the parent has a no-argument constructor.
3. You **can’t** use `super` in a static context (it’s tied to an object instance).
4. `super` always refers to the **immediate parent**, not grandparents.

---



# The Diamond Problem in Java

## 1. What is the Diamond Problem?

**Definition:**  
The diamond problem occurs in multiple inheritance when a class inherits from two classes that both inherit from the same superclass.

**Why “diamond”?**  
The inheritance diagram forms a diamond shape:

```
    A
   / \
  B   C
   \ /
    D
```

**The issue:**  
If both B and C override a method from A, and D inherits from both,  
→ Which version should D use?

**Example (Hypothetical Java with multiple inheritance):**
```java
class A {
    void hello() { System.out.println("Hello from A"); }
}

class B extends A {
    @Override
    void hello() { System.out.println("Hello from B"); }
}

class C extends A {
    @Override
    void hello() { System.out.println("Hello from C"); }
}

// ❌ Hypothetical multiple inheritance
class D extends B, C {
    // Which hello() should D use?  B's or C's?
}
```

**Ambiguity:**  
Calling `D.hello()` is unclear — should it run B’s version or C’s version?

---

## 2. Problems Caused by Multiple Inheritance

- **Ambiguity in method resolution**  
  The compiler can’t decide which parent’s method to call if both define the same method.

- **State conflicts**  
  If both parents have a field with the same name, which one does the child inherit?

- **Increased complexity**  
  Compilers and the runtime need complex rules to handle method resolution.

- **Maintenance issues**  
  Changing one parent class can cause unexpected effects in unrelated subclasses.

---

## 3. How Java Solves the Problem

**Java’s rule:**  
✅ Only single class inheritance (`extends`)  
✅ Multiple interface inheritance (`implements`)

**Why interfaces are safe:**

- Interfaces don’t store state → no field conflicts.
- Interfaces only declare methods → if multiple interfaces have the same method, the implementing class must override it, removing ambiguity.

---

## 4. Safe Multiple Inheritance via Interfaces

**Example:**
```java
interface B {
    void hello();
}

interface C {
    void hello();
}

class D implements B, C {
    @Override
    public void hello() {
        System.out.println("Hello from D");
    }
}
```

Even though both B and C declare `hello()`, D must provide its own implementation.  
No confusion about which method to call.

---

## 💡 Key Takeaways

- Multiple class inheritance → ❌ causes method and state conflicts.
- Multiple interface inheritance → ✅ safe because:
    - No shared state.
    - Conflicts must be explicitly resolved by the implementing class.

**Java avoids the diamond problem by:**
1. Allowing only one superclass.
2. Allowing multiple interfaces for flexibility without ambiguity.



# Interfaces in Java

## 1. What is an Interface?

**Definition:**  
An interface in Java is a contract that specifies what a class must do, but not how to do it.

- It contains abstract methods (without body) and constants.
- Think of it as a blueprint for a class.

---

## 2. Why Use Interfaces?

- **Achieve abstraction** → Hide implementation details.
- **Multiple inheritance of type** → A class can implement multiple interfaces.
- **Loose coupling** → Code depends on abstractions, not concrete implementations.
- **Standardization** → Different classes can guarantee they provide certain methods.

---

## 3. Syntax

```java
interface Animal {
    void sound();  // implicitly public and abstract
    int LEGS = 4;  // implicitly public, static, final
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}
```

**Notes:**
- Methods in an interface are public and abstract by default (Java 7 and earlier).
- Fields are public static final (constants) by default.
- From **Java 8**, interfaces can also have:
    - **default methods** (with a body).
    - **static methods**.
- From **Java 9**, interfaces can have **private methods** (used inside the interface only).

---

## 4. Key Points / Rules

- Cannot create objects of an interface directly.
- A class that implements an interface must override all abstract methods (unless it’s an abstract class).
- A class can implement multiple interfaces.
- Interface methods are always public, even if you don’t write `public`.
- Interface variables are always `public static final`.

---

## 5. Example — Multiple Interfaces

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }
    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }
}

public class Main {
    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();
        d.swim();
    }
}
```

**Output:**
```
Duck is flying
Duck is swimming
```

Here, Duck inherits from both `Flyable` and `Swimmable` without ambiguity.

---

## 6. Default Methods in Interfaces (Java 8+)

Default methods allow adding new functionality to interfaces without breaking existing implementations.

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car implements Vehicle {}

public class Main {
    public static void main(String[] args) {
        Car c = new Car();
        c.start(); // Uses default method from Vehicle
    }
}
```

---

## 7. Static Methods in Interfaces

Static methods belong to the interface itself, not to the implementing class.

```java
interface MathUtil {
    static int add(int a, int b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(MathUtil.add(5, 3)); // 8
    }
}
```

---

## 8. Interfaces vs Abstract Classes

| Feature              | Interface                                | Abstract Class                              |
|----------------------|------------------------------------------|---------------------------------------------|
| Methods              | Abstract (default), default, static      | Abstract + Concrete                         |
| Fields               | public static final only                 | Any type (instance/static, final/non-final) |
| Multiple Inheritance | Yes                                      | No                                          |
| Constructors         | Not allowed                              | Allowed                                     |
| When to use          | “Can do” behavior                        | “Is a” relationship with partial implementation |

---

## 9. Real-World Analogy

- **Interface** = Job description (“must know Java, must write reports”).
- **Class** = Employee who fulfills the job description by implementing the required skills.

---

## 💡 Key Takeaways

- Interfaces define **what** a class should do, not **how**.
- They are the solution to the **diamond problem** in Java.
- Multiple interface inheritance is safe and common.
- Default and static methods in Java 8+ make interfaces more powerful.


## What Happens When You Declare a Variable in an Interface

### All variables in an interface are:
- **public**
- **static**
- **final**

### This means:
- One copy of the variable exists for the entire program (because it’s static).
- Its value cannot change (because it’s final).
- It must be initialized at the time of declaration.

### Example
```java
interface Config {
    int VERSION = 1;  // public static final by default
}

class App implements Config {
    void showVersion() {
        System.out.println("Version: " + VERSION);
    }
}

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.showVersion();  // Output: Version: 1

        // VERSION = 2; ❌ Error: cannot assign a value to final variable
    }
}

```

---

# Re-declaring Interface Methods in an Abstract Class

## Scenario

We have an interface with a method `pay()`.

An abstract class implements that interface.

The abstract class can re-declare the method as abstract — but this is optional.

---

### Example

#### Version 1 — Without re-declaration in the abstract class

```java
interface Payment {
    void pay(double amount); // declared in interface
}

abstract class Card implements Payment {
    // No implementation here — allowed because Card is abstract
}

class CreditCard extends Card {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
```

✅ Works fine — the `pay()` method is inherited from `Payment` and must be implemented in the first non-abstract subclass (`CreditCard`).

---

#### Version 2 — With re-declaration in the abstract class

```java
interface Payment {
    void pay(double amount);
}

abstract class Card implements Payment {
    // Optional re-declaration for clarity
    @Override
    public abstract void pay(double amount);
}

class CreditCard extends Card {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
```

✅ Also works fine — here, we explicitly say in `Card`:

> “I know pay() exists, but I’m leaving it abstract for my subclasses.”

---

## Key Points

- **Not mandatory to re-declare** —  
  The compiler already knows the method exists via the interface.

- **Good for readability** —  
  Re-declaring in the abstract class is like a reminder to subclasses:  
  “You must implement this method.”

- **No change in behavior** —  
  Whether you re-declare or not, the rule is the same:
    - If a class is concrete, it must implement all abstract methods from its superclasses and interfaces.
    - If it doesn’t, it must also be declared abstract.

---

### In short

- **Without re-declaration** → Works, but the requirement is hidden in the interface.
- **With re-declaration** → Works the same, but makes your abstract class’s “contract” more explicit to readers.
