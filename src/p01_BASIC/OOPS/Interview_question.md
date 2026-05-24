# 1. What is Object-Oriented Programming (OOP)?

## Short Definition

Object-Oriented Programming (OOP) is a programming paradigm where software is designed using:

* **Classes**
* **Objects**

It organizes code around:

* Data (properties)
* Behavior (methods)

instead of just functions.

---

# Why OOP Matters

OOP helps in building:

* Scalable systems
* Maintainable code
* Reusable components
* Modular applications

This is heavily used in:

* Spring Boot applications
* Microservices
* Backend APIs
* Enterprise systems

---

# Real-World Example

A **Car** has:

* Properties → color, speed, brand
* Behaviors → start(), brake(), accelerate()

---

# Java Example

```java id="b4d8ec"
class Car {

    String color;
    int speed;

    void accelerate() {
        speed += 10;
    }
}

public class Main {
    public static void main(String[] args) {

        Car car = new Car();

        car.color = "Red";
        car.accelerate();

        System.out.println(car.speed);
    }
}
```

---

# Key Points

* Class → Blueprint
* Object → Real instance
* Methods define behavior
* OOP improves code organization

---

# Real-World Backend Example

In a food delivery application:

* `User`
* `Restaurant`
* `Order`
* `Payment`

all are modeled as objects.

Each object has:

* State
* Behavior

---

# Interview Follow-Up Questions

* Why is OOP preferred in enterprise applications?
* Difference between procedural and OOP?
* What are the four pillars of OOP?

---

# SDE-2 Perspective

At SDE-2 level, interviewers expect:

* Clean architecture understanding
* Object relationships
* Extensible design
* Maintainability discussion

They care less about textbook definition and more about:

> “How OOP helps build scalable systems.”

---

# 2. Explain the Four Pillars of OOP

The four pillars are:

1. Encapsulation
2. Abstraction
3. Inheritance
4. Polymorphism

---

# 2.1 Encapsulation

## Definition

Encapsulation means:

> Hiding internal data and controlling access through methods.

---

## Example

```java id="q6u7l9"
class BankAccount {

    private double balance;

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

---

## Real-World Example

ATM machine:

* Users cannot directly modify bank balance
* They interact through controlled methods

---

## Benefits

* Data protection
* Validation support
* Better maintainability

---

# 2.2 Abstraction

## Definition

Abstraction means:

> Hiding implementation complexity and exposing only essential features.

---

## Example

```java id="qj70e9"
abstract class Payment {
    abstract void pay();
}

class UpiPayment extends Payment {

    void pay() {
        System.out.println("UPI Payment");
    }
}
```

---

## Real-World Example

You drive a car using:

* Steering
* Brake
* Accelerator

You don’t need engine internals.

---

## Benefits

* Reduces complexity
* Improves flexibility
* Loose coupling

---

# 2.3 Inheritance

## Definition

Inheritance allows one class to acquire properties and methods of another class.

---

## Example

```java id="0twj6m"
class Animal {

    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {

    void bark() {
        System.out.println("Dog barks");
    }
}
```

---

## Real-World Example

Dog IS-A Animal.

---

## Benefits

* Code reuse
* Extensibility
* Hierarchical relationships

---

# 2.4 Polymorphism

## Definition

Polymorphism means:

> One interface, multiple behaviors.

---

## Example

```java id="d5u6bw"
class Payment {

    void pay() {
        System.out.println("Generic payment");
    }
}

class CardPayment extends Payment {

    @Override
    void pay() {
        System.out.println("Card payment");
    }
}
```

```java id="lzww4w"
Payment p = new CardPayment();
p.pay();
```

---

## Benefits

* Flexible systems
* Extensible architecture
* Runtime behavior selection

---

# Real Backend Example

Notification system:

* EmailNotification
* SMSNotification
* PushNotification

All implement same interface.

---

# Interview Follow-Up Questions

* Which OOP pillar is most important?
* Difference between abstraction and encapsulation?
* Real-world use of polymorphism?

---

# SDE-2 Perspective

Interviewers expect you to explain:

* Tradeoffs
* Loose coupling
* Extensible architecture
* SOLID principle connections

Especially:

> “Favor composition over inheritance.”

---

# 3. Difference Between Class and Object

| Class               | Object            |
| ------------------- | ----------------- |
| Blueprint/template  | Real instance     |
| Logical entity      | Physical entity   |
| No memory allocated | Memory allocated  |
| Defines structure   | Holds actual data |

---

# Example

```java id="rn9o2r"
class Student {
    String name;
}
```

`Student` is a class.

```java id="hwnk8y"
Student s1 = new Student();
```

`s1` is an object.

---

# Why It Matters

Classes define behavior.

Objects execute behavior at runtime.

---

# Real Backend Example

Class:

```java id="7t4wkg"
UserService
```

Object:

```java id="ks9nq7"
userService
```

Spring Boot internally creates objects using Dependency Injection.

---

# Key Points

* One class can create many objects
* Objects occupy memory
* Class acts as blueprint

---

# Interview Follow-Up Questions

* Where are objects stored in memory?
* Difference between object reference and object?
* How does JVM create objects?

---

# SDE-2 Perspective

At SDE-2 level:

* Understand object lifecycle
* Dependency Injection
* Singleton beans
* Memory management

Interviewers may connect this to:

* Heap memory
* Garbage collection
* Spring bean lifecycle

---

# 4. What is Encapsulation? How Do You Achieve It in Java?

## Short Definition

Encapsulation means:

> Binding data and methods together while restricting direct access to internal data.

---

# How to Achieve Encapsulation

1. Make fields `private`
2. Use getters/setters
3. Add validations

---

# Example

```java id="h8zjml"
class Employee {

    private int salary;

    public void setSalary(int salary) {

        if(salary > 0) {
            this.salary = salary;
        }
    }

    public int getSalary() {
        return salary;
    }
}
```

---

# Why It Matters

Encapsulation prevents:

* Invalid state
* Unauthorized access
* Data corruption

---

# Benefits

* Better security
* Better maintainability
* Flexible implementation
* Easier debugging

---

# Real Backend Example

DTOs and entities in Spring Boot:

```java id="a6zkup"
private String patientId;
```

Access happens through:

* getters
* setters
* validations

---

# Key Points

* Encapsulation ≠ abstraction
* It focuses on data protection
* Core principle in enterprise applications

---

# Interview Follow-Up Questions

* Why private fields?
* Can encapsulation exist without getters/setters?
* Difference between encapsulation and data hiding?

---

# SDE-2 Perspective

In large backend systems:

* Encapsulation protects domain integrity
* Prevents invalid business states
* Supports validation layers

Very important in:

* Banking
* Healthcare
* Payment systems

---

# 5. What is Abstraction? Why is it Important?

## Short Definition

Abstraction means:

> Hiding implementation details and exposing only necessary behavior.

---

# Achieved Using

* Abstract classes
* Interfaces

---

# Example

```java id="35m24o"
interface Notification {
    void send();
}

class EmailNotification implements Notification {

    public void send() {
        System.out.println("Email sent");
    }
}
```

---

# Why Abstraction Matters

It reduces complexity.

Users focus on:

* WHAT system does

instead of:

* HOW system works internally

---

# Real-World Example

Using Google Maps:

* You search location
* Internal algorithms remain hidden

---

# Real Backend Example

Kafka Producer API:

```java id="z12d6n"
producer.send(message);
```

Internally Kafka handles:

* Serialization
* Networking
* Retry
* Partitioning

---

# Benefits

* Loose coupling
* Easier maintenance
* Better scalability
* Cleaner architecture

---

# Key Points

* Abstraction hides complexity
* Interface defines contract
* Implementation can change independently

---

# Interview Follow-Up Questions

* Difference between abstract class and interface?
* Can abstraction improve scalability?
* Real-world abstraction example?

---

# SDE-2 Perspective

Good abstraction:

* Improves extensibility
* Reduces dependency
* Enables plugin architecture

Bad abstraction:

* Leaks implementation details
* Creates rigid design

---

# 6. Difference Between Abstraction and Encapsulation

| Abstraction                                | Encapsulation                 |
| ------------------------------------------ | ----------------------------- |
| Hides implementation                       | Hides data                    |
| Focus on WHAT                              | Focus on HOW                  |
| Achieved using interfaces/abstract classes | Achieved using private fields |
| Reduces complexity                         | Protects data                 |

---

# Real-World Example

## Abstraction

Car driving:

* User only sees controls

## Encapsulation

Engine internals are protected.

---

# Example

```java id="4djrbj"
class Employee {

    private int salary;

    public int getSalary() {
        return salary;
    }
}
```

Encapsulation protects salary field.

---

# Why It Matters

Both improve:

* Maintainability
* Scalability
* Clean architecture

but solve different problems.

---

# Interview Follow-Up Questions

* Can abstraction exist without encapsulation?
* Which one improves security?
* Which one improves flexibility?

---

# SDE-2 Perspective

In backend systems:

Abstraction:

* APIs
* Interfaces
* Microservices contracts

Encapsulation:

* Entities
* DTOs
* Domain models

Both are heavily used together.

---

# 7. What is Inheritance? What Problems Does It Solve?

## Short Definition

Inheritance allows one class to acquire properties and methods from another class.

---

# Example

```java id="pbj0yf"
class Vehicle {

    void start() {
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
}
```

---

# Problems It Solves

## 1. Code Reusability

Avoid duplicate code.

---

## 2. Extensibility

New classes can extend existing behavior.

---

## 3. Hierarchical Relationships

Represents IS-A relationships.

Example:

* Dog IS-A Animal

---

# Types of Inheritance in Java

1. Single
2. Multilevel
3. Hierarchical

(Java does not support multiple inheritance with classes.)

---

# Real Backend Example

```java id="iys1ck"
BaseController
BaseEntity
BaseException
```

Common logic shared across subclasses.

---

# Key Points

* Promotes reuse
* Supports polymorphism
* Can create tight coupling if overused

---

# Interview Follow-Up Questions

* Why multiple inheritance not supported?
* Difference between inheritance and composition?
* What is IS-A relationship?

---

# SDE-2 Perspective

Modern backend systems prefer:

> Composition over inheritance

because deep inheritance hierarchies become:

* Hard to maintain
* Fragile
* Tightly coupled

This is a very common product-company discussion.

---

# 8. What is Polymorphism? Explain Compile-Time and Runtime Polymorphism

## Short Definition

Polymorphism means:

> One entity behaving differently in different situations.

---

# Types of Polymorphism

1. Compile-time polymorphism
2. Runtime polymorphism

---

# 1. Compile-Time Polymorphism

Achieved using:

* Method overloading

---

# Example

```java id="sn0gdn"
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

Compiler decides method at compile time.

---

# 2. Runtime Polymorphism

Achieved using:

* Method overriding

---

# Example

```java id="z5n7gx"
class Animal {

    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

```java id="n9z8im"
Animal a = new Dog();
a.sound();
```

---

# Why Polymorphism Matters

It enables:

* Flexible architecture
* Extensible systems
* Loose coupling

---

# Real Backend Example

```java id="y7k13r"
Notification notification;
```

Possible implementations:

* EmailNotification
* SMSNotification
* PushNotification

---

# Key Points

* Overloading → compile time
* Overriding → runtime
* Core concept in scalable systems

---

# Interview Follow-Up Questions

* How does JVM achieve runtime polymorphism?
* What is dynamic method dispatch?
* Can static methods be overridden?

---

# SDE-2 Perspective

Polymorphism is heavily used in:

* Strategy pattern
* Spring dependency injection
* Payment systems
* Notification systems

Interviewers expect real architecture examples.

---

# 9. What is Method Overloading?

## Short Definition

Method overloading means:

> Multiple methods with same name but different parameters.

---

# Example

```java id="j50ak6"
class MathUtils {

    int sum(int a, int b) {
        return a + b;
    }

    int sum(int a, int b, int c) {
        return a + b + c;
    }
}
```

---

# Rules

Methods must differ by:

* Number of parameters
  OR
* Type of parameters

Return type alone cannot overload methods.

---

# Why It Matters

Improves:

* Readability
* Flexibility
* API usability

---

# Real Backend Example

Logging frameworks:

```java id="6m7ydk"
log.info(String message)
log.info(String message, Object data)
```

---

# Key Points

* Achieves compile-time polymorphism
* Compiler resolves method call
* Common in utility APIs

---

# Interview Follow-Up Questions

* Can constructors be overloaded?
* Can return type alone overload methods?
* Difference between overloading and overriding?

---

# SDE-2 Perspective

Used heavily in:

* Builder patterns
* Utility libraries
* Framework APIs
* Service layers

Good API design often uses overloading smartly.

---

# 10. What is Method Overriding?

## Short Definition

Method overriding means:

> Child class provides its own implementation of parent class method.

---

# Example

```java id="kc2zh6"
class Payment {

    void process() {
        System.out.println("Generic payment");
    }
}

class UpiPayment extends Payment {

    @Override
    void process() {
        System.out.println("UPI payment");
    }
}
```

---

# Why It Matters

Overriding enables:

* Runtime polymorphism
* Flexible behavior
* Extensible systems

---

# Rules for Overriding

1. Same method name
2. Same parameters
3. IS-A relationship required
4. Cannot reduce access modifier

---

# Real Backend Example

```java id="8tq5lh"
interface PaymentService {
    void pay();
}
```

Implementations:

* RazorpayPaymentService
* StripePaymentService
* PaypalPaymentService

Each overrides behavior differently.

---

# Key Points

* Runtime method resolution
* Supports loose coupling
* Core concept in enterprise systems

---

# Interview Follow-Up Questions

* Can static methods be overridden?
* Can private methods be overridden?
* Why use `@Override` annotation?
* Difference between overriding and hiding?

---

# SDE-2 Perspective

Method overriding is heavily used in:

* Spring Boot services
* Strategy pattern
* Template method pattern
* Plugin architecture

Interviewers often expect:

* Runtime polymorphism explanation
* JVM method dispatch understanding
* Real backend examples


# 11. How Does Java Achieve Runtime Polymorphism?

## Short Definition

Java achieves runtime polymorphism using:

* Method Overriding
* Parent class reference pointing to child class object

The method call is resolved at runtime by JVM.

---

# Example

```java id="v4n8tz"
class Animal {

    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal a = new Dog();
        a.sound();
    }
}
```

Output:

```text id="yfwjke"
Dog barks
```

---

# How It Works Internally

At compile time:

```java id="9odq7d"
Animal a
```

At runtime:

```java id="n5h3f0"
new Dog()
```

JVM checks actual object type and calls overridden method.

---

# Why It Matters

Enables:

* Flexible architecture
* Loose coupling
* Extensible systems

---

# Real Backend Example

```java id="ewx3h0"
NotificationService notification;
```

Possible runtime objects:

* EmailNotificationService
* SMSNotificationService
* PushNotificationService

---

# Key Points

* Achieved through overriding
* Runtime decision by JVM
* Core concept behind strategy pattern

---

# Interview Follow-Up Questions

* What is dynamic method dispatch?
* Can constructors participate in runtime polymorphism?
* Can static methods achieve runtime polymorphism?

---

# SDE-2 Perspective

Runtime polymorphism is heavily used in:

* Spring Dependency Injection
* Payment gateways
* Notification systems
* Kafka event handlers

Interviewers expect:

* Real-world architecture explanation
* JVM behavior understanding

---

# 12. What is Dynamic Method Dispatch?

## Short Definition

Dynamic Method Dispatch means:

> JVM decides which overridden method to call at runtime.

It is the mechanism behind runtime polymorphism.

---

# Example

```java id="1x9kr4"
class Payment {

    void pay() {
        System.out.println("Generic payment");
    }
}

class CardPayment extends Payment {

    @Override
    void pay() {
        System.out.println("Card payment");
    }
}

public class Main {

    public static void main(String[] args) {

        Payment p = new CardPayment();
        p.pay();
    }
}
```

Output:

```text id="fy5xv5"
Card payment
```

---

# Why It Happens

Reference type:

```java id="5y9n4u"
Payment
```

Actual object:

```java id="u9d4m1"
CardPayment
```

JVM chooses actual object’s method during execution.

---

# Why It Matters

Makes systems:

* Extensible
* Flexible
* Open for new implementations

without modifying existing code.

---

# Real Backend Example

Payment processing system:

* UPI
* Card
* Wallet

All implement same interface.

At runtime correct implementation executes.

---

# Key Points

* Happens only for overridden methods
* Static methods do not participate
* Constructor calls are not polymorphic

---

# Interview Follow-Up Questions

* Difference between overloading and dispatch?
* How does JVM resolve methods internally?
* What is vtable in JVM?

---

# SDE-2 Perspective

Dynamic dispatch is core to:

* Strategy pattern
* Factory pattern
* Spring Beans
* Event-driven systems

Very important for scalable architectures.

---

# 13. Can Static Methods Be Overridden?

## Short Answer

No.

Static methods belong to class, not object.

They are:

> Hidden, not overridden.

---

# Example

```java id="ysyz2w"
class Parent {

    static void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {

    static void show() {
        System.out.println("Child");
    }
}
```

```java id="z5f3jr"
Parent p = new Child();
p.show();
```

Output:

```text id="9n91k8"
Parent
```

---

# Why?

Static methods are resolved at:

* Compile time

not runtime.

So runtime polymorphism does not apply.

---

# Method Hiding

This is called:

> Method Hiding

because child method hides parent method.

---

# Key Points

| Feature      | Static Method |
| ------------ | ------------- |
| Belongs to   | Class         |
| Polymorphism | No            |
| Overridden?  | No            |
| Resolved at  | Compile time  |

---

# Real Backend Example

Utility classes:

```java id="yup5d6"
MathUtils.calculate()
```

These are static because behavior does not depend on object state.

---

# Interview Follow-Up Questions

* Why static methods are not polymorphic?
* Difference between method hiding and overriding?
* Can static methods access instance variables?

---

# SDE-2 Perspective

In backend systems:

* Business logic should rarely be static
* Static methods reduce testability
* Dependency Injection becomes difficult

Interviewers may discuss:

* Mocking challenges
* Tight coupling

---

# 14. Why Does Java Not Support Multiple Inheritance Using Classes?

## Short Definition

Java does not allow:

```java id="a4a1pj"
class C extends A, B
```

because it creates ambiguity issues.

---

# Diamond Problem

Consider:

```text id="2mkl85"
      A
     / \
    B   C
     \ /
      D
```

If both B and C override same method:

* Which method should D inherit?

Ambiguity occurs.

---

# Example Problem

```java id="02d8y4"
class A {
    void show() {}
}

class B extends A {
    void show() {}
}

class C extends A {
    void show() {}
}
```

Now:

```java id="xw5l0n"
class D extends B, C
```

Which `show()` should execute?

---

# Why Java Avoided It

To keep:

* Simplicity
* Predictability
* Maintainability

---

# How Java Solves This

Using:

* Interfaces
* Default method rules

---

# Key Points

* Java supports multiple inheritance through interfaces
* Avoids ambiguity problems
* Simplifies JVM method resolution

---

# Real Backend Example

Instead of multiple inheritance:

```java id="4r3sl5"
class PaymentService 
    implements Loggable, Auditable
```

---

# Interview Follow-Up Questions

* What is Diamond Problem?
* How do interfaces solve this?
* Does C++ support multiple inheritance?

---

# SDE-2 Perspective

Deep inheritance hierarchies become:

* Hard to maintain
* Tightly coupled
* Fragile

Modern architectures prefer:

* Interfaces
* Composition
* Dependency Injection

---

# 15. How Does Java Solve the Diamond Problem?

## Short Definition

Java solves Diamond Problem using:

* Interfaces
* Explicit method overriding rules

---

# Problem Scenario

```text id="h4y9k0"
     A
    / \
   B   C
    \ /
     D
```

Both parents define same method.

Ambiguity occurs.

---

# Java Solution Using Interfaces

```java id="d9z2iw"
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}
```

```java id="9ewr0o"
class Test implements A, B {

    @Override
    public void show() {
        System.out.println("Resolved");
    }
}
```

---

# Rule in Java

If multiple interfaces contain same default method:

> Child class MUST override it.

---

# Why This Works

Removes ambiguity explicitly.

Developer decides behavior.

---

# Key Points

* Java avoids class-level multiple inheritance
* Interfaces provide controlled multiple inheritance
* Explicit overriding resolves conflicts

---

# Interview Follow-Up Questions

* What happens if child does not override?
* Why interfaces are safer?
* Can abstract classes create diamond problem?

---

# SDE-2 Perspective

Very important in:

* Plugin systems
* Shared contracts
* Multi-capability services

Common in Spring Boot architectures.

---

# 16. Difference Between IS-A and HAS-A Relationship

| Relationship | Meaning     |
| ------------ | ----------- |
| IS-A         | Inheritance |
| HAS-A        | Composition |

---

# IS-A Relationship

Represents inheritance.

Example:

```text id="txhhyy"
Dog IS-A Animal
```

---

# Example

```java id="9qf7rj"
class Animal {
}

class Dog extends Animal {
}
```

---

# HAS-A Relationship

Represents composition.

Example:

```text id="o4vrx9"
Car HAS-A Engine
```

---

# Example

```java id="6c9q3m"
class Engine {
}

class Car {

    private Engine engine;
}
```

---

# Why It Matters

Choosing wrong relationship creates:

* Poor design
* Tight coupling
* Rigid systems

---

# Key Points

| IS-A                | HAS-A                 |
| ------------------- | --------------------- |
| Inheritance         | Composition           |
| Tight coupling      | Loose coupling        |
| Strong relationship | Flexible relationship |

---

# Real Backend Example

```java id="3byi7u"
Order HAS-A PaymentService
```

Better than:

```java id="2akv9o"
Order IS-A PaymentService
```

---

# Interview Follow-Up Questions

* Which is better?
* When should inheritance be avoided?
* How does HAS-A improve flexibility?

---

# SDE-2 Perspective

Modern systems strongly prefer:

> HAS-A over IS-A

because composition:

* Improves extensibility
* Reduces coupling
* Easier testing

---

# 17. What is Composition? How is It Better Than Inheritance?

## Short Definition

Composition means:

> One class contains another class as member variable.

Represents:

```text id="t5h3r1"
HAS-A relationship
```

---

# Example

```java id="x7m1vp"
class Engine {

    void start() {
        System.out.println("Engine started");
    }
}

class Car {

    private Engine engine = new Engine();

    void startCar() {
        engine.start();
    }
}
```

---

# Why Composition is Better

## 1. Loose Coupling

Components are independent.

---

## 2. Better Flexibility

Can replace implementation easily.

---

## 3. Easier Testing

Dependencies can be mocked.

---

## 4. Better Maintainability

Changes affect fewer components.

---

# Real Backend Example

```java id="gg0nyu"
OrderService HAS-A PaymentService
```

Different payment services can be injected.

---

# Key Points

* Composition promotes reuse
* Avoids deep inheritance trees
* Supports Dependency Injection

---

# Interview Follow-Up Questions

* Composition vs inheritance?
* Why modern systems prefer composition?
* What is dependency injection?

---

# SDE-2 Perspective

This is one of the most important design discussions.

Spring Boot internally heavily uses:

* Composition
* Dependency Injection

instead of inheritance.

---

# 18. Difference Between Aggregation and Composition

| Aggregation           | Composition         |
| --------------------- | ------------------- |
| Weak relationship     | Strong relationship |
| Independent lifecycle | Dependent lifecycle |
| HAS-A                 | Strong HAS-A        |

---

# Aggregation Example

```java id="jlwm3t"
class Employee {
}

class Company {

    List<Employee> employees;
}
```

Employees can exist without company.

---

# Composition Example

```java id="h6rzcl"
class Engine {
}

class Car {

    private Engine engine = new Engine();
}
```

Engine strongly belongs to car.

---

# Why It Matters

Represents ownership correctly.

---

# Key Points

| Feature              | Aggregation | Composition |
| -------------------- | ----------- | ----------- |
| Ownership            | Weak        | Strong      |
| Lifecycle dependency | No          | Yes         |
| Coupling             | Lower       | Higher      |

---

# Real Backend Example

Aggregation:

```text id="vr0u95"
Department HAS employees
```

Composition:

```text id="x2m93v"
Order HAS orderItems
```

Deleting order deletes items.

---

# Interview Follow-Up Questions

* Real-world aggregation example?
* Real-world composition example?
* Why lifecycle matters?

---

# SDE-2 Perspective

Understanding ownership relationships is important in:

* Database modeling
* Microservices
* Domain-driven design

---

# 19. Why is Composition Often Preferred Over Inheritance?

## Short Answer

Because composition provides:

* Loose coupling
* Better flexibility
* Easier maintenance

---

# Problems with Inheritance

Inheritance creates:

* Tight coupling
* Fragile hierarchy
* Ripple effects on changes

---

# Example Problem

```java id="z7ljvl"
class Bird {
    void fly() {}
}
```

```java id="v0ey71"
class Penguin extends Bird
```

Penguin cannot fly.

Inheritance becomes incorrect.

---

# Composition Solution

```java id="zvw9dw"
class FlyingBehavior {
    void fly() {}
}
```

```java id="xq0h9p"
class Bird {

    FlyingBehavior behavior;
}
```

Flexible behavior assignment.

---

# Why Composition Wins

| Inheritance    | Composition    |
| -------------- | -------------- |
| Tight coupling | Loose coupling |
| Rigid          | Flexible       |
| Hard testing   | Easier mocking |

---

# Real Backend Example

Spring Boot:

* Services depend on interfaces
* Dependencies injected dynamically

This is composition.

---

# Key Points

* Composition supports SOLID principles
* Easier refactoring
* Better scalability

---

# Interview Follow-Up Questions

* Real inheritance failure example?
* How composition improves testing?
* Relation with dependency injection?

---

# SDE-2 Perspective

This is one of the MOST IMPORTANT backend design principles.

Interviewers expect:

> “Favor composition over inheritance”

with real project examples.

---

# 20. Explain Covariant Return Types in Java

## Short Definition

Covariant return type means:

> Overridden method can return subtype of parent method return type.

---

# Example

```java id="gxg6l6"
class Animal {
}

class Dog extends Animal {
}
```

```java id="mrf6zk"
class Parent {

    Animal getAnimal() {
        return new Animal();
    }
}
```

```java id="fx6vta"
class Child extends Parent {

    @Override
    Dog getAnimal() {
        return new Dog();
    }
}
```

---

# Why It Matters

Improves:

* Flexibility
* Readability
* Type safety

---

# Real Backend Example

Builder patterns:

```java id="rjlwmz"
BaseBuilder build()
```

Child builders return:

```java id="1f8m7e"
UserBuilder
```

instead of generic base type.

---

# Key Points

* Only applies to overriding
* Return type must be subtype
* Improves API usability

---

# Interview Follow-Up Questions

* Does it work with primitives?
* Why useful in builders?
* Difference between covariance and contravariance?

---

# SDE-2 Perspective

Commonly used in:

* Framework APIs
* Fluent APIs
* Builder patterns
* ORM libraries

Important for writing clean extensible APIs.


# 21. Difference Between Abstract Class and Interface

| Abstract Class                       | Interface                              |
| ------------------------------------ | -------------------------------------- |
| Can have abstract + concrete methods | Mainly contract definition             |
| Supports constructors                | No constructors                        |
| Can have instance variables          | Only constants (`public static final`) |
| Supports single inheritance          | Multiple interfaces can be implemented |
| Used for common base behavior        | Used for capability/contract           |

---

# When to Use Abstract Class

Use when classes share:

* Common state
* Common logic
* Base implementation

---

# When to Use Interface

Use when you want:

* Loose coupling
* Multiple capabilities
* Extensibility

---

# Example

## Abstract Class

```java id="c6v4pt"
abstract class Vehicle {

    void start() {
        System.out.println("Vehicle started");
    }

    abstract void fuelType();
}
```

---

## Interface

```java id="v4s4ls"
interface PaymentService {
    void pay();
}
```

---

# Real Backend Example

Abstract class:

```java id="nmww0g"
BaseController
BaseEntity
```

Interface:

```java id="8c0h72"
NotificationService
PaymentGateway
```

---

# Key Points

* Interface = behavior contract
* Abstract class = partial implementation
* Interface supports multiple inheritance

---

# Interview Follow-Up Questions

* Can abstract class implement interface?
* Can interface extend interface?
* Why interfaces preferred in Spring?

---

# SDE-2 Perspective

Modern backend systems strongly prefer:

* Interfaces
* Composition
* Dependency Injection

Abstract classes are used only when:

* Shared implementation is significant.

---

# 22. When Would You Choose an Abstract Class Over an Interface?

## Short Answer

Choose abstract class when:

* Classes share common code
* Common state exists
* Default implementation is needed

Choose interface when:

* Only behavior contract is needed
* Multiple inheritance required
* Loose coupling is priority

---

# Example Scenario

Suppose all vehicles share:

* engineNumber
* start()
* stop()

Then abstract class makes sense.

---

# Example

```java id="l2bc5r"
abstract class Vehicle {

    String engineNumber;

    void start() {
        System.out.println("Vehicle started");
    }

    abstract void fuelType();
}
```

---

# Why Not Interface Here?

Interfaces cannot maintain instance state properly.

---

# Real Backend Example

Abstract class:

```java id="6hdy3m"
AbstractAuthenticationProvider
```

Interface:

```java id="0m4wws"
UserDetailsService
```

---

# Key Points

Use abstract class when:

* Code reuse is needed
* Shared lifecycle exists

Use interface when:

* Extensibility matters more

---

# Interview Follow-Up Questions

* Why does Spring prefer interfaces?
* Can abstract class have final methods?
* Multiple inheritance issue?

---

# SDE-2 Perspective

In large systems:

* Interfaces provide flexibility
* Abstract classes reduce duplication

Best practice:

> Use interfaces for contracts and abstract classes for shared implementation.

---

# 23. Can an Abstract Class Have Constructors?

## Short Answer

Yes.

Abstract classes can have constructors.

---

# Why?

Constructors initialize common fields of child classes.

---

# Example

```java id="sqp7kw"
abstract class Animal {

    Animal() {
        System.out.println("Animal constructor");
    }
}
```

```java id="wl7dz6"
class Dog extends Animal {

    Dog() {
        System.out.println("Dog constructor");
    }
}
```

---

# Output

```text id="9d1x5q"
Animal constructor
Dog constructor
```

---

# Important Concept

Abstract class object cannot be created directly.

But constructor executes during child object creation.

---

# Real Backend Example

```java id="v5my4x"
abstract class BaseEntity {

    LocalDateTime createdAt;

    BaseEntity() {
        createdAt = LocalDateTime.now();
    }
}
```

All child entities inherit common initialization.

---

# Key Points

* Abstract classes can have constructors
* Used for shared initialization
* Parent constructor executes first

---

# Interview Follow-Up Questions

* Why constructor needed if object cannot be created?
* Constructor chaining?
* Order of constructor execution?

---

# SDE-2 Perspective

Very common in:

* Base entity classes
* Framework internals
* Shared configuration classes

Useful for:

* Audit fields
* Logging setup
* Shared initialization

---

# 24. Can Interfaces Have Method Implementations?

## Short Answer

Yes.

Since Java 8, interfaces can contain:

* Default methods
* Static methods

Since Java 9:

* Private methods also allowed

---

# Example

```java id="l4mx8d"
interface Payment {

    default void log() {
        System.out.println("Payment log");
    }
}
```

---

# Why Added?

Before Java 8:
adding new method to interface would break all implementations.

Default methods solved backward compatibility issue.

---

# Static Method Example

```java id="6bbv22"
interface Utility {

    static void print() {
        System.out.println("Utility");
    }
}
```

---

# Real Backend Example

Java Collections Framework:

```java id="8klh4s"
List.sort()
Collection.stream()
```

implemented as default methods.

---

# Key Points

| Method Type | Allowed in Interface |
| ----------- | -------------------- |
| Abstract    | Yes                  |
| Default     | Yes                  |
| Static      | Yes                  |
| Private     | Yes (Java 9+)        |

---

# Interview Follow-Up Questions

* Why default methods introduced?
* Can interfaces have state?
* Difference between default and static methods?

---

# SDE-2 Perspective

Important for:

* Framework evolution
* Backward compatibility
* API extensibility

Very common in enterprise Java frameworks.

---

# 25. What are Default Methods in Interfaces?

## Short Definition

Default methods are:

> Methods inside interface with implementation.

Introduced in Java 8.

---

# Example

```java id="v1fpk6"
interface Notification {

    default void log() {
        System.out.println("Notification log");
    }
}
```

---

# Why Important

Allows adding new methods without breaking old implementations.

---

# Example Problem Before Java 8

If interface changes:
all implementing classes must update.

This caused huge maintenance issues.

---

# Real Backend Example

Java Collection API:

```java id="f5mfx5"
stream()
forEach()
removeIf()
```

are default methods.

---

# Key Points

* Default methods support backward compatibility
* Can be overridden
* Supports interface evolution

---

# Interview Follow-Up Questions

* What if two interfaces have same default method?
* Can default methods access instance variables?
* Difference between abstract and default method?

---

# SDE-2 Perspective

Important in:

* Large-scale frameworks
* Public APIs
* SDK development

Interviewers may ask:

> “Why Java introduced default methods?”

---

# 26. What are Functional Interfaces?

## Short Definition

Functional Interface:

> Interface containing exactly one abstract method.

Used heavily in:

* Lambda expressions
* Stream API
* Functional programming

---

# Example

```java id="48jbyo"
@FunctionalInterface
interface Calculator {

    int add(int a, int b);
}
```

---

# Lambda Example

```java id="uq4d41"
Calculator c = (a, b) -> a + b;
```

---

# Common Functional Interfaces

| Interface | Purpose         |
| --------- | --------------- |
| Predicate | Returns boolean |
| Function  | Input → output  |
| Consumer  | Consumes input  |
| Supplier  | Produces output |

---

# Real Backend Example

Streams API:

```java id="5ls0hm"
users.stream()
     .filter(user -> user.isActive())
```

---

# Why Important

Reduces:

* Boilerplate code
* Anonymous class complexity

---

# Key Points

* Only one abstract method
* Can contain default/static methods
* `@FunctionalInterface` optional but recommended

---

# Interview Follow-Up Questions

* Why introduced in Java 8?
* Difference between lambda and anonymous class?
* Can functional interface have multiple default methods?

---

# SDE-2 Perspective

Very important for:

* Modern Java development
* Reactive programming
* Stream processing
* Event-driven systems

Expected knowledge in product companies.

---

# 27. Why Were Default and Static Methods Added to Interfaces?

## Short Answer

To support:

* Backward compatibility
* Interface evolution
* Utility methods inside interfaces

---

# Main Problem Before Java 8

If new method added to interface:
all implementing classes break.

Huge issue for:

* Java libraries
* Enterprise frameworks

---

# Solution

Default methods provide implementation directly.

---

# Example

```java id="4kzv0z"
interface Vehicle {

    default void start() {
        System.out.println("Vehicle started");
    }
}
```

---

# Static Methods

Added to keep utility methods inside interfaces.

Example:

```java id="dd8z0m"
interface MathUtils {

    static int square(int x) {
        return x * x;
    }
}
```

---

# Real Backend Example

Java Collections:

```java id="d2r5yw"
List.of()
Map.of()
```

---

# Key Points

* Solves backward compatibility issue
* Improves API design
* Reduces utility classes

---

# Interview Follow-Up Questions

* Why not use abstract class instead?
* Can static methods be overridden?
* Why interfaces evolved after Java 8?

---

# SDE-2 Perspective

Very important in:

* Library design
* SDK design
* Framework maintenance

Commonly discussed in senior Java interviews.

---

# 28. Can an Interface Extend Another Interface?

## Short Answer

Yes.

Interfaces can extend one or multiple interfaces.

---

# Example

```java id="ndm3bo"
interface Animal {

    void eat();
}
```

```java id="e6w0wk"
interface Dog extends Animal {

    void bark();
}
```

---

# Multiple Interface Extension

```java id="xv1z9o"
interface A {
}

interface B {
}

interface C extends A, B {
}
```

---

# Why Important

Supports:

* Multiple inheritance of type
* Capability composition

---

# Real Backend Example

```java id="h6tbr6"
interface JpaRepository
    extends CrudRepository, PagingAndSortingRepository
```

Spring Data heavily uses this.

---

# Key Points

* Interface supports multiple inheritance
* No ambiguity if only abstract methods
* Default method conflicts must be resolved

---

# Interview Follow-Up Questions

* Difference between implements and extends?
* Can interface extend class?
* Diamond problem in interfaces?

---

# SDE-2 Perspective

Very important in:

* Framework design
* Modular architectures
* Capability-based systems

Used heavily in Spring ecosystem.

---

# 29. Can a Class Implement Multiple Interfaces?

## Short Answer

Yes.

Java supports:

> Multiple inheritance through interfaces.

---

# Example

```java id="o7t6a5"
interface Camera {
    void click();
}
```

```java id="w9n5gj"
interface MusicPlayer {
    void playMusic();
}
```

```java id="b4q0eg"
class Smartphone implements Camera, MusicPlayer {

    public void click() {
        System.out.println("Photo clicked");
    }

    public void playMusic() {
        System.out.println("Music playing");
    }
}
```

---

# Why Important

Allows combining multiple capabilities without multiple inheritance problems.

---

# Real Backend Example

```java id="5vsz9j"
class PaymentService
    implements Auditable, Loggable
```

---

# Key Points

* Multiple interface implementation supported
* Avoids multiple inheritance ambiguity
* Promotes modular design

---

# Interview Follow-Up Questions

* What if interfaces contain same default method?
* Why multiple inheritance allowed with interfaces?
* Difference between interface inheritance and class inheritance?

---

# SDE-2 Perspective

Heavily used in:

* Spring Boot
* Enterprise services
* Event-driven systems
* Plugin architectures

---

# 30. What Happens if Two Interfaces Have the Same Default Method?

## Short Answer

Compiler throws ambiguity error.

Child class MUST override method explicitly.

---

# Example

```java id="mrrg3t"
interface A {

    default void show() {
        System.out.println("A");
    }
}
```

```java id="x6gmnd"
interface B {

    default void show() {
        System.out.println("B");
    }
}
```

```java id="90v6jh"
class Test implements A, B {

    @Override
    public void show() {
        System.out.println("Resolved");
    }
}
```

---

# Why?

Java forces developer to resolve ambiguity explicitly.

---

# How to Call Specific Interface Method

```java id="1bvlv6"
A.super.show();
B.super.show();
```

---

# Key Points

* Prevents diamond problem ambiguity
* Explicit resolution required
* Default methods can conflict

---

# Real Backend Example

Possible in:

* Shared framework contracts
* Plugin systems
* Multiple capability interfaces

---

# Interview Follow-Up Questions

* Why Java forces overriding?
* How does JVM resolve default methods?
* Can abstract class solve this automatically?

---

# SDE-2 Perspective

Important for:

* Framework integration
* Plugin architecture
* Large enterprise systems

Interviewers may test:

* Diamond problem understanding
* Interface conflict resolution

# 31. What is the `Object` Class in Java?

## Short Definition

`Object` is the root parent class of all classes in Java.

Every class directly or indirectly inherits from:

```java id="l9u7ke"
java.lang.Object
```

---

# Why It Matters

It provides common methods to all objects like:

* `toString()`
* `equals()`
* `hashCode()`
* `clone()`
* `wait()`
* `notify()`

---

# Example

```java id="ev44oq"
class Employee {
}
```

Internally:

```java id="xv6ztf"
class Employee extends Object {
}
```

---

# Common Methods

| Method            | Purpose                |
| ----------------- | ---------------------- |
| `toString()`      | Object representation  |
| `equals()`        | Object comparison      |
| `hashCode()`      | Hash-based collections |
| `clone()`         | Copy object            |
| `wait()/notify()` | Thread communication   |

---

# Real Backend Example

Logging:

```java id="33lcl5"
System.out.println(user);
```

internally calls:

```java id="58haj5"
toString()
```

---

# Key Points

* Parent of every Java class
* Provides universal object behavior
* Core to collections and JVM internals

---

# Interview Follow-Up Questions

* Why Object class important?
* Which methods are most commonly overridden?
* Can Object class be bypassed?

---

# SDE-2 Perspective

Very important for:

* Collections framework
* Caching systems
* ORM entities
* Logging/debugging

Interviewers expect strong understanding of:

* `equals()`
* `hashCode()`
* object identity

---

# 32. Explain `equals()` and `hashCode()`

## Short Definition

### `equals()`

Used to compare object content.

### `hashCode()`

Returns integer hash representation of object.

---

# Default Behavior

By default:

```java id="kq8mkm"
equals()
```

compares memory references.

---

# Example

```java id="ah07ya"
class Employee {

    int id;

    Employee(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {

        Employee e = (Employee) obj;

        return this.id == e.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
```

---

# Why Both Matter

Collections like:

* HashMap
* HashSet

depend on both methods.

---

# HashMap Flow

1. `hashCode()` finds bucket
2. `equals()` checks exact object

---

# Real Backend Example

Entity comparison in:

* Hibernate
* JPA
* Caching systems

---

# Key Points

* Equal objects must have same hashCode
* Override both together
* Used heavily in collections

---

# Interview Follow-Up Questions

* What happens if only equals overridden?
* Why hash collisions occur?
* How HashMap uses hashCode?

---

# SDE-2 Perspective

This is one of the MOST IMPORTANT Java interview topics.

Very common in:

* Product companies
* Backend interviews
* Performance discussions

---

# 33. Why Must `hashCode()` Be Overridden When `equals()` Is Overridden?

## Short Answer

Because Java contract says:

> Equal objects must have same hashCode.

---

# Problem Without hashCode()

```java id="5j9g5d"
HashSet<Employee> set = new HashSet<>();
```

If:

* `equals()` says objects equal
* but hashCode differs

Then duplicates may appear.

---

# Example

```java id="f2g0kg"
Employee e1 = new Employee(1);
Employee e2 = new Employee(1);
```

```java id="fqtj4n"
e1.equals(e2) == true
```

but different hashCode:

* stored in different buckets

HashSet fails.

---

# Java Contract

If:

```java id="jj4x2n"
a.equals(b) == true
```

Then:

```java id="l1vg6y"
a.hashCode() == b.hashCode()
```

must also be true.

---

# Real Backend Example

Caching systems:

* Redis keys
* Hibernate entities
* HashMap-based lookups

depend heavily on this.

---

# Key Points

* Override both together
* Prevent collection inconsistency
* Critical for hashing performance

---

# Interview Follow-Up Questions

* Can unequal objects have same hashCode?
* What are hash collisions?
* How HashMap handles collisions?

---

# SDE-2 Perspective

Very high-frequency interview topic.

Interviewers expect:

* Contract understanding
* Collection internals knowledge
* Real performance discussion

---

# 34. Difference Between `==` and `equals()`

| `==`                      | `equals()`              |
| ------------------------- | ----------------------- |
| Compares references       | Compares content        |
| Checks memory address     | Checks logical equality |
| Primitive → value compare | Object → custom compare |

---

# Example

```java id="fztm4w"
String s1 = new String("Java");
String s2 = new String("Java");
```

```java id="vnchbb"
s1 == s2
```

Output:

```text id="m1mgb4"
false
```

---

```java id="1c1u5w"
s1.equals(s2)
```

Output:

```text id="3gv18o"
true
```

---

# Why?

`==`
checks:

* memory reference

`equals()`
checks:

* actual content

---

# Primitive Example

```java id="uv2eho"
int a = 5;
int b = 5;

System.out.println(a == b);
```

Output:

```text id="j4h1iy"
true
```

---

# Real Backend Example

Authentication systems:

* username comparison
* token validation

must use:

```java id="0e6mh4"
equals()
```

not `==`.

---

# Key Points

* `==` → reference comparison
* `equals()` → logical comparison
* String overrides equals()

---

# Interview Follow-Up Questions

* Why String overrides equals?
* Difference in String pool?
* Wrapper comparison issue?

---

# SDE-2 Perspective

Very important in:

* Authentication
* Entity comparison
* Caching
* Collections

Incorrect usage causes serious production bugs.

---

# 35. What is Object Cloning?

## Short Definition

Object cloning means:

> Creating copy of existing object.

Java supports cloning using:

```java id="9lqgxu"
Cloneable
```

interface and:

```java id="2aqo9m"
clone()
```

method.

---

# Example

```java id="tjqmrm"
class Employee implements Cloneable {

    int id;

    Employee(int id) {
        this.id = id;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

---

# Usage

```java id="4mqam1"
Employee e1 = new Employee(1);

Employee e2 = (Employee)e1.clone();
```

---

# Why Important

Useful when:

* Copying complex objects
* Avoiding object recreation cost

---

# Problems with Cloning

* Shallow copy issues
* Complex object graphs
* Mutable references

---

# Real Backend Example

Configuration objects:

* Request templates
* Prototype beans

---

# Key Points

* Cloneable is marker interface
* `clone()` creates object copy
* Default cloning is shallow

---

# Interview Follow-Up Questions

* Why clone controversial?
* Difference between shallow/deep copy?
* Better alternatives to cloning?

---

# SDE-2 Perspective

Most modern systems avoid direct cloning.

Prefer:

* Copy constructors
* Builder pattern
* Serialization-based copying

Interviewers may discuss cloning drawbacks.

---

# 36. What is Shallow Copy vs Deep Copy?

| Shallow Copy          | Deep Copy                  |
| --------------------- | -------------------------- |
| Copies references     | Copies actual objects      |
| Shared nested objects | Independent nested objects |
| Faster                | More expensive             |

---

# Shallow Copy Example

```java id="u2obdr"
class Address {
    String city;
}
```

```java id="63w4fu"
class Employee {

    Address address;
}
```

Copy shares same address object.

---

# Problem

Changing copied object's address:
also changes original object.

---

# Deep Copy Example

Create completely independent nested objects.

---

# Why Important

Important in:

* Multithreading
* Immutable systems
* Caching
* Data consistency

---

# Real Backend Example

Order processing:

* copied request should not mutate original request

---

# Key Points

* Shallow copy shares references
* Deep copy duplicates entire graph
* Deep copy safer but costly

---

# Interview Follow-Up Questions

* How to implement deep copy?
* Serialization-based deep copy?
* Why immutable objects help?

---

# SDE-2 Perspective

Very important in:

* Distributed systems
* Kafka events
* Concurrent processing
* Shared cache objects

---

# 37. Explain Immutable Classes with Examples

## Short Definition

Immutable class:

> Object state cannot change after creation.

---

# How to Create Immutable Class

1. Make class `final`
2. Make fields `private final`
3. No setters
4. Initialize through constructor

---

# Example

```java id="jrrwzn"
final class Employee {

    private final int id;

    Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
```

---

# Why Important

Immutable objects are:

* Thread-safe
* Secure
* Easier to cache

---

# Real Backend Example

Common immutable classes:

* String
* LocalDate
* UUID

---

# Benefits

* Safe sharing across threads
* No accidental modification
* Better reliability

---

# Key Points

* Immutable = read-only object
* No setters
* Constructor initialization only

---

# Interview Follow-Up Questions

* Why String immutable?
* Immutable vs final?
* How immutability helps concurrency?

---

# SDE-2 Perspective

Highly important in:

* Concurrent systems
* Event-driven architecture
* Distributed systems
* Kafka messages

Interviewers often connect this with thread safety.

---

# 38. How is `String` Immutable in Java?

## Why String is Immutable

String is immutable because:

* Internal value cannot change after creation

---

# Internal Structure

```java id="p2isr7"
private final char[] value;
```

---

# Example

```java id="rzyl7q"
String s = "Java";
s.concat(" Programming");
```

Original string remains unchanged.

---

# Why Java Made String Immutable

## 1. Security

Used in:

* URLs
* Database connections
* File paths

---

## 2. Thread Safety

Safe for concurrent use.

---

## 3. String Pool Optimization

Immutable strings can be reused safely.

---

# Real Backend Example

Configuration keys:

```java id="t7m6z5"
JWT tokens
API URLs
SQL queries
```

must remain unchanged.

---

# Key Points

* String objects cannot mutate
* New object created on modification
* Enables String Pool

---

# Interview Follow-Up Questions

* What is String Pool?
* Difference between StringBuilder and String?
* Why StringBuilder mutable?

---

# SDE-2 Perspective

Very common interview topic.

Often connected with:

* Performance optimization
* Memory management
* Concurrency

---

# 39. What are Wrapper Classes?

## Short Definition

Wrapper classes convert primitive types into objects.

---

# Primitive → Wrapper

| Primitive | Wrapper   |
| --------- | --------- |
| int       | Integer   |
| double    | Double    |
| char      | Character |
| boolean   | Boolean   |

---

# Example

```java id="1vce4n"
Integer num = 10;
```

---

# Why Important

Collections only store objects.

Example:

```java id="6gxocn"
List<Integer>
```

---

# Autoboxing

Automatic conversion:

```java id="ktb19m"
int → Integer
```

---

# Unboxing

Automatic conversion:

```java id="77ybhm"
Integer → int
```

---

# Real Backend Example

Database entities:

```java id="qvvv0h"
Integer userId
```

instead of primitive:

```java id="r3aj1y"
int
```

because null support needed.

---

# Key Points

* Wrapper = object representation
* Needed for collections/generics
* Supports utility methods

---

# Interview Follow-Up Questions

* Difference between int and Integer?
* Why Integer caching exists?
* Autoboxing performance issues?

---

# SDE-2 Perspective

Important in:

* ORM systems
* Collections
* Nullability handling
* Serialization

---

# 40. Difference Between Stack Memory and Heap Memory

| Stack Memory           | Heap Memory           |
| ---------------------- | --------------------- |
| Stores local variables | Stores objects        |
| Fast access            | Larger memory         |
| Thread-specific        | Shared across threads |
| Automatically managed  | Managed by GC         |

---

# Stack Example

```java id="6ny4d0"
int x = 10;
```

Stored in stack.

---

# Heap Example

```java id="t2wl9k"
Employee e = new Employee();
```

Object stored in heap.

Reference stored in stack.

---

# Why Important

Understanding memory helps in:

* Performance tuning
* GC optimization
* Memory leak debugging

---

# Real Backend Example

Large object creation:

* causes heap pressure
* increases GC pauses

---

# Key Points

* Stack stores method calls/local variables
* Heap stores objects
* Heap managed by Garbage Collector

---

# Interview Follow-Up Questions

* StackOverflowError?
* OutOfMemoryError?
* How GC works?

---

# SDE-2 Perspective

Very important in:

* High-scale backend systems
* JVM tuning
* Performance optimization
* Memory leak debugging

Expected knowledge for senior backend engineers.

# 41. Explain All Access Modifiers in Java

## Short Definition

Access modifiers control:

> Visibility and accessibility of classes, methods, and variables.

---

# Types of Access Modifiers

| Modifier    | Same Class | Same Package | Subclass | Outside Package |
| ----------- | ---------- | ------------ | -------- | --------------- |
| `private`   | ✅          | ❌            | ❌        | ❌               |
| `default`   | ✅          | ✅            | ❌        | ❌               |
| `protected` | ✅          | ✅            | ✅        | ❌*              |
| `public`    | ✅          | ✅            | ✅        | ✅               |

---

# 1. Private

Accessible only inside same class.

---

## Example

```java id="w9t4eg"
class Employee {

    private int salary;
}
```

---

# 2. Default (Package-Private)

No modifier specified.

Accessible only within same package.

---

## Example

```java id="r4tyt8"
class Employee {

    int id;
}
```

---

# 3. Protected

Accessible:

* within package
* outside package via inheritance

---

## Example

```java id="x5s4z7"
class Animal {

    protected void sound() {
    }
}
```

---

# 4. Public

Accessible everywhere.

---

## Example

```java id="z2df0r"
public class Main {
}
```

---

# Why It Matters

Access modifiers help achieve:

* Encapsulation
* Security
* Controlled access

---

# Real Backend Example

```java id="2qv6gq"
private UserRepository repository;
```

Repository should not be directly exposed.

---

# Key Points

* Use least permissive modifier possible
* `private` improves encapsulation
* `public` should be used carefully

---

# Interview Follow-Up Questions

* Difference between protected and default?
* Can top-level class be private?
* Why encapsulation important?

---

# SDE-2 Perspective

Important for:

* API design
* Library development
* Encapsulation
* Secure architecture

Senior engineers are expected to design proper visibility boundaries.

---

# 42. Difference Between `final`, `finally`, and `finalize`

| Keyword/Method | Purpose                        |
| -------------- | ------------------------------ |
| `final`        | Restrict modification          |
| `finally`      | Execute cleanup block          |
| `finalize()`   | Cleanup before GC (deprecated) |

---

# 1. final

Used with:

* variable
* method
* class

---

## Example

```java id="e5e5m8"
final int x = 10;
```

Cannot change value.

---

# Final Method

```java id="m0qsm7"
final void show() {
}
```

Cannot override.

---

# Final Class

```java id="r3c0hm"
final class Utility {
}
```

Cannot inherit.

---

# 2. finally

Always executes after try-catch.

---

## Example

```java id="w3tmc4"
try {
}
finally {
    System.out.println("Cleanup");
}
```

---

# 3. finalize()

Called before object garbage collection.

Deprecated in modern Java.

---

# Why Deprecated?

Unpredictable execution and poor performance.

---

# Real Backend Example

`finally` used for:

* DB connection closing
* Resource cleanup

---

# Key Points

* `final` = restriction
* `finally` = cleanup block
* `finalize()` = deprecated GC hook

---

# Interview Follow-Up Questions

* Why finalize deprecated?
* Difference between final and immutable?
* Can finally block fail?

---

# SDE-2 Perspective

Modern systems use:

* try-with-resources
  instead of finalize.

Interviewers may ask about:

* resource management
* JVM cleanup

---

# 43. What Happens When a Class is Declared `final`?

## Short Definition

A `final` class:

> Cannot be inherited.

---

# Example

```java id="bl9aqj"
final class Utility {
}
```

---

# Invalid

```java id="pb9l2g"
class Test extends Utility {
}
```

Compilation error occurs.

---

# Why Use final Class?

To:

* Prevent modification
* Improve security
* Preserve implementation

---

# Real Example

```java id="byy4jo"
String
```

is final in Java.

---

# Why String is Final?

To ensure:

* immutability
* security
* thread safety

---

# Key Points

* Final class cannot be extended
* Prevents behavior modification
* Often used for immutable classes

---

# Real Backend Example

Security-related utility classes:

```java id="f2t9sq"
JWTUtils
EncryptionUtils
```

may be final.

---

# Interview Follow-Up Questions

* Why String final?
* Can final class have abstract methods?
* Difference between final and immutable?

---

# SDE-2 Perspective

Used in:

* Immutable objects
* Security-sensitive systems
* Utility frameworks

Important in clean API design.

---

# 44. Can We Override a `final` Method?

## Short Answer

No.

A `final` method cannot be overridden.

---

# Example

```java id="4ak08v"
class Parent {

    final void show() {
        System.out.println("Parent");
    }
}
```

---

# Invalid

```java id="qgrk2x"
class Child extends Parent {

    void show() {
    }
}
```

Compilation error.

---

# Why Use final Method?

To:

* Prevent behavior change
* Ensure consistency
* Improve security

---

# Real Backend Example

Framework internal methods:

* transaction handling
* security validation

may be final.

---

# Key Points

* Final method supports controlled behavior
* Prevents accidental overriding
* Used in stable APIs

---

# Interview Follow-Up Questions

* Difference between final class and final method?
* Can final methods be overloaded?
* Why use final in frameworks?

---

# SDE-2 Perspective

Common in:

* Template method pattern
* Security frameworks
* Core business validation flows

Interviewers may ask design tradeoffs.

---

# 45. What is the Use of the `super` Keyword?

## Short Definition

`super` refers to:

> Parent class object.

Used to access:

* parent methods
* parent constructor
* parent variables

---

# Example

```java id="fd1xcu"
class Animal {

    void sound() {
        System.out.println("Animal sound");
    }
}
```

```java id="quj9p9"
class Dog extends Animal {

    void sound() {

        super.sound();

        System.out.println("Dog barks");
    }
}
```

---

# Constructor Example

```java id="6z37jl"
class Parent {

    Parent() {
        System.out.println("Parent");
    }
}
```

```java id="sh2v0w"
class Child extends Parent {

    Child() {
        super();
    }
}
```

---

# Why Important

Allows reuse of parent behavior.

---

# Key Points

* `super()` calls parent constructor
* `super.method()` calls parent method
* Must be first statement in constructor

---

# Real Backend Example

Base service class:

```java id="2s1u9e"
super.validateRequest();
```

---

# Interview Follow-Up Questions

* Difference between this and super?
* Can super access private members?
* Constructor chaining?

---

# SDE-2 Perspective

Important in:

* Framework extension
* Base service implementation
* Shared behavior reuse

---

# 46. What is the Use of the `this` Keyword?

## Short Definition

`this` refers to:

> Current object instance.

---

# Common Uses

1. Access current object fields
2. Constructor chaining
3. Pass current object

---

# Example

```java id="l2vt4h"
class Employee {

    int id;

    Employee(int id) {
        this.id = id;
    }
}
```

---

# Constructor Chaining

```java id="mg0n7d"
class Test {

    Test() {
        this(10);
    }

    Test(int x) {
    }
}
```

---

# Why Important

Resolves ambiguity between:

* local variable
* instance variable

---

# Key Points

* Refers to current object
* Supports constructor chaining
* Cannot be used in static context

---

# Real Backend Example

DTO initialization:

```java id="m2j16v"
this.userId = userId;
```

---

# Interview Follow-Up Questions

* Difference between this and super?
* Why this not allowed in static methods?
* Constructor chaining rules?

---

# SDE-2 Perspective

Important for:

* Clean code
* Object initialization
* Builder patterns

Very common in enterprise codebases.

---

# 47. Explain Static Methods vs Instance Methods

| Static Method                             | Instance Method               |
| ----------------------------------------- | ----------------------------- |
| Belongs to class                          | Belongs to object             |
| No object needed                          | Object required               |
| Cannot access instance variables directly | Can access all members        |
| Compile-time binding                      | Runtime polymorphism possible |

---

# Static Method Example

```java id="km7mry"
class MathUtils {

    static int square(int x) {
        return x * x;
    }
}
```

Usage:

```java id="48mrn1"
MathUtils.square(5);
```

---

# Instance Method Example

```java id="7k8u3u"
class Employee {

    void work() {
        System.out.println("Working");
    }
}
```

---

# Why Static Methods Exist

Used for:

* utility methods
* stateless logic

---

# Real Backend Example

Static:

```java id="c3x8x9"
Collections.sort()
```

Instance:

```java id="l3k8v5"
userService.save()
```

---

# Key Points

* Static methods do not support overriding
* Instance methods support polymorphism
* Static methods are memory efficient

---

# Interview Follow-Up Questions

* Why static methods not polymorphic?
* Can static method access non-static field?
* Utility class design?

---

# SDE-2 Perspective

Overusing static methods:

* reduces testability
* increases coupling

Modern systems prefer:

* dependency injection
* instance-based services

---

# 48. Can Constructors Be Inherited?

## Short Answer

No.

Constructors are not inherited.

---

# Why?

Constructors initialize current class object.

Inheritance does not apply to object creation logic.

---

# Example

```java id="4sm3fd"
class Parent {

    Parent() {
        System.out.println("Parent");
    }
}
```

```java id="84y0jf"
class Child extends Parent {
}
```

Child does not inherit constructor directly.

---

# But Parent Constructor Executes

During child object creation:

```java id="78q31w"
new Child();
```

JVM internally calls:

```java id="dfjqxv"
super();
```

---

# Why Important

Ensures proper parent initialization.

---

# Key Points

* Constructors not inherited
* Parent constructor executes first
* Constructor chaining happens automatically

---

# Interview Follow-Up Questions

* Can constructor be private?
* Constructor chaining order?
* Why constructors cannot be overridden?

---

# SDE-2 Perspective

Important in:

* Framework initialization
* Spring Bean lifecycle
* Entity inheritance

---

# 49. Can Constructors Be Overloaded?

## Short Answer

Yes.

A class can have multiple constructors with different parameters.

---

# Example

```java id="vmtkcd"
class Employee {

    Employee() {
    }

    Employee(int id) {
    }

    Employee(int id, String name) {
    }
}
```

---

# Why Important

Provides multiple ways to initialize objects.

---

# Benefits

* Flexible object creation
* Cleaner APIs
* Better usability

---

# Real Backend Example

DTO creation:

```java id="0x4m4x"
User()
User(id)
User(id, name)
```

---

# Key Points

* Constructor overloading supported
* Parameters must differ
* Supports constructor chaining

---

# Interview Follow-Up Questions

* Difference between overloading and overriding?
* Can constructors be final/static?
* Why constructor chaining useful?

---

# SDE-2 Perspective

Very common in:

* Builder patterns
* DTO creation
* Framework object initialization

---

# 50. What are Singleton Classes? How Do You Design One?

## Short Definition

Singleton class:

> Only one object instance exists in JVM.

---

# Why Singleton?

Used for shared resources:

* configuration
* logging
* caching
* DB connections

---

# Basic Singleton Example

```java id="r8trr7"
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

---

# Problems with Basic Singleton

Not thread-safe.

Multiple threads may create multiple objects.

---

# Thread-Safe Singleton

```java id="wqjlwm"
class Singleton {

    private static final Singleton instance =
            new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```

---

# Best Modern Approach

Enum Singleton:

```java id="jlwmwy"
enum Singleton {

    INSTANCE;
}
```

---

# Real Backend Example

Singleton used in:

* Logger
* Configuration manager
* Cache manager

---

# Key Points

* Single object instance
* Global access point
* Must handle thread safety

---

# Interview Follow-Up Questions

* How to break singleton?
* Why enum singleton best?
* Singleton vs Spring singleton bean?

---

# SDE-2 Perspective

Very common design pattern discussion.

Interviewers expect:

* Thread safety understanding
* Lazy vs eager initialization
* Reflection/serialization attack discussion
* Spring singleton scope knowledge
