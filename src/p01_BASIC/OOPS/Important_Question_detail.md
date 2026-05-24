# Dependency Injection (DI) in Simple Words

Dependency Injection is a design pattern where an object gets the things it needs (**dependencies**) from outside instead of creating them itself.

It is mainly used to achieve:

* Loose coupling
* Better code maintainability
* Easy testing
* Better scalability

---

# Real-Life Example

Imagine a person who needs a car to travel.

### Without Dependency Injection

The person creates the car himself.

```java
class Person {
    Car car = new Car();
}
```

Problem:

* Person is tightly connected to `Car`
* If you want Bike instead of Car, you must change Person class

---

### With Dependency Injection

Someone else provides the vehicle.

```java
class Person {
    Vehicle vehicle;

    Person(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
```

Now:

* Person does not care whether it is Car or Bike
* Easy to change implementation
* Flexible design

---

# Technical Definition

Dependency Injection means:

> Injecting required objects (dependencies) into a class from outside rather than the class creating them internally.

---

# Important Terms

| Term                       | Meaning                                                 |
| -------------------------- | ------------------------------------------------------- |
| Dependency                 | Object required by another object                       |
| Injection                  | Providing dependency from outside                       |
| IoC (Inversion of Control) | Control of object creation moves to framework/container |

---

# Example Without Dependency Injection

```java
class Engine {
    void start() {
        System.out.println("Engine Started");
    }
}

class Car {
    Engine engine = new Engine();

    void drive() {
        engine.start();
        System.out.println("Car Running");
    }
}
```

## Problem

`Car` is tightly coupled with `Engine`.

If engine changes:

* ElectricEngine
* DieselEngine
* HybridEngine

You must modify `Car`.

---

# Example With Dependency Injection

```java
class Engine {
    void start() {
        System.out.println("Engine Started");
    }
}

class Car {

    private Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }

    void drive() {
        engine.start();
        System.out.println("Car Running");
    }
}

public class Main {
    public static void main(String[] args) {

        Engine engine = new Engine();

        Car car = new Car(engine);

        car.drive();
    }
}
```

---

# Flow of Dependency Injection

```text
Main Class
    ↓
Creates Engine Object
    ↓
Passes Engine to Car
    ↓
Car uses Engine
```

Car does not create Engine itself.

---

# Types of Dependency Injection

## 1. Constructor Injection (Most Common)

Dependency provided through constructor.

```java
class Student {

    private Laptop laptop;

    Student(Laptop laptop) {
        this.laptop = laptop;
    }
}
```

### Advantages

* Immutable dependency
* Mandatory dependency
* Easy testing

---

## 2. Setter Injection

Dependency provided using setter method.

```java
class Student {

    private Laptop laptop;

    void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
```

### Advantages

* Optional dependency
* Can change dependency later

---

## 3. Field Injection

Dependency injected directly into field.

Example in Spring:

```java
@Autowired
private Laptop laptop;
```

### Problem

* Hard to test
* Less recommended

---

# Why Dependency Injection is Important

## 1. Loose Coupling

Classes become independent.

Without DI:

```text
Car → Specific Engine
```

With DI:

```text
Car → Any Engine
```

---

## 2. Easy Unit Testing

You can inject mock objects.

```java
Car car = new Car(mockEngine);
```

Very useful in backend development.

---

## 3. Better Maintainability

Changing implementation becomes easy.

Example:

* MySQL → PostgreSQL
* Stripe → Razorpay
* Kafka → RabbitMQ

---

## 4. Better Reusability

Components can be reused easily.

---

# Dependency Injection in Spring Boot

In Spring Boot, Spring container automatically creates and injects objects.

---

## Example

```java
@Component
class Engine {
}

@Component
class Car {

    private Engine engine;

    @Autowired
    Car(Engine engine) {
        this.engine = engine;
    }
}
```

Spring automatically:

* Creates Engine object
* Creates Car object
* Injects Engine into Car

---

# What is IoC Container?

In frameworks like Spring Framework:

The container:

* Creates objects
* Manages lifecycle
* Injects dependencies

This is called:

* IoC Container
* Spring Container

---

# Dependency Injection vs Tight Coupling

| Tight Coupling            | Dependency Injection           |
| ------------------------- | ------------------------------ |
| Object creates dependency | Dependency provided externally |
| Hard to change            | Easy to change                 |
| Difficult testing         | Easy testing                   |
| Less flexible             | Highly flexible                |

---

# Real Backend Example

Suppose you have:

```text
UserService
    ↓
UserRepository
    ↓
Database
```

With DI:

```java
class UserService {

    private UserRepository repo;

    UserService(UserRepository repo) {
        this.repo = repo;
    }
}
```

Now:

* You can change database
* Add mock repository
* Improve scalability

---

# Interview Definition (SDE Perspective)

> Dependency Injection is a design pattern in which dependencies are supplied to a class externally rather than the class creating them itself. It helps achieve loose coupling, easier testing, maintainability, and scalability.

---

# Common Interview Questions

## Why Constructor Injection is Preferred?

Because:

* Dependency becomes mandatory
* Object remains immutable
* Easier unit testing
* Prevents NullPointerException

---

## Difference Between IoC and DI

| IoC                          | DI                       |
| ---------------------------- | ------------------------ |
| Principle                    | Implementation technique |
| Control shifted to framework | Dependencies injected    |
| Broad concept                | Specific pattern         |

---

# Simple Diagram

```text
Without DI

Car
 └── creates Engine itself


With DI

Engine created outside
        ↓
      Car uses it
```

---

# SDE-2 Perspective

In large-scale backend systems:

* Services should not depend on concrete implementations
* Components should be replaceable
* Microservices should remain loosely coupled
* Testing should be easy using mocks

Dependency Injection helps achieve all these goals efficiently.


# Functional Interface in Java

A **Functional Interface** is an interface that contains:

* **Only one abstract method**
* But it can have:

    * multiple `default` methods
    * multiple `static` methods
    * methods from `Object` class

Functional Interfaces are mainly used with:

* Lambda Expressions
* Stream API
* Method References

Introduced in Java 8.

---

# Simple Definition

> A Functional Interface is an interface having exactly one abstract method, used to represent a single behavior or action.

---

# Basic Example

```java id="0w1m3x"
@FunctionalInterface
interface Greeting {

    void sayHello();
}
```

This is valid because:

* only one abstract method exists

---

# Using Functional Interface

## Before Java 8

```java id="bjg3k9"
interface Greeting {
    void sayHello();
}

class MyGreeting implements Greeting {

    public void sayHello() {
        System.out.println("Hello");
    }
}

public class Main {
    public static void main(String[] args) {

        Greeting g = new MyGreeting();

        g.sayHello();
    }
}
```

Problem:

* Too much boilerplate code

---

# Using Lambda Expression (Java 8)

```java id="c6n8to"
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {

    public static void main(String[] args) {

        Greeting g = () -> {
            System.out.println("Hello");
        };

        g.sayHello();
    }
}
```

---

# Why Functional Interface is Needed?

Because Lambda Expressions need a target type.

Example:

```java id="18vk6y"
() -> System.out.println("Hello")
```

This lambda alone has no meaning.

But:

```java id="2h0up7"
Greeting g = () -> System.out.println("Hello");
```

Now Java knows:

* lambda should implement `sayHello()`

---

# Important Rules of Functional Interface

## Rule 1: Only One Abstract Method

Valid:

```java id="j89n7r"
@FunctionalInterface
interface A {

    void show();
}
```

Invalid:

```java id="lf4xv0"
@FunctionalInterface
interface A {

    void show();

    void print();
}
```

Compile-time error occurs.

---

# Rule 2: Default Methods Allowed

```java id="1eq8i3"
@FunctionalInterface
interface A {

    void show();

    default void display() {
        System.out.println("Display");
    }
}
```

Valid because:

* only one abstract method

---

# Rule 3: Static Methods Allowed

```java id="xv3q3v"
@FunctionalInterface
interface A {

    void show();

    static void test() {
        System.out.println("Static");
    }
}
```

Still valid.

---

# Rule 4: Object Class Methods Do Not Count

```java id="h58t7m"
@FunctionalInterface
interface A {

    void show();

    String toString();
}
```

Still valid because:

* `toString()` already belongs to `Object`

---

# What is @FunctionalInterface Annotation?

It tells compiler:

> "This interface must remain functional."

Example:

```java id="40m4bf"
@FunctionalInterface
interface Calculator {

    int add(int a, int b);
}
```

If another abstract method is added:

* compiler gives error

---

# Real-World Example

Suppose you want payment processing.

---

## Traditional Way

```java id="5m9k4x"
interface Payment {
    void pay(int amount);
}

class CreditCardPayment implements Payment {

    public void pay(int amount) {
        System.out.println("Paid using Credit Card");
    }
}
```

---

# Using Functional Interface + Lambda

```java id="o0q6p9"
@FunctionalInterface
interface Payment {

    void pay(int amount);
}

public class Main {

    public static void main(String[] args) {

        Payment phonePe = amount ->
                System.out.println("Paid " + amount + " using PhonePe");

        Payment gpay = amount ->
                System.out.println("Paid " + amount + " using GPay");

        phonePe.pay(500);
        gpay.pay(1000);
    }
}
```

---

# Functional Interface with Parameters

```java id="8u3f7d"
@FunctionalInterface
interface Calculator {

    int operate(int a, int b);
}

public class Main {

    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;

        Calculator multiply = (a, b) -> a * b;

        System.out.println(add.operate(5, 3));

        System.out.println(multiply.operate(5, 3));
    }
}
```

---

# Functional Interface with Return Type

```java id="p1r59v"
@FunctionalInterface
interface Square {

    int square(int x);
}

public class Main {

    public static void main(String[] args) {

        Square s = x -> x * x;

        System.out.println(s.square(5));
    }
}
```

---

# Built-in Functional Interfaces in Java

Java provides many predefined Functional Interfaces in package:

```text id="pnr6d5"
java.util.function
```

---

# 1. Predicate<T>

Used for:

* condition checking
* returns boolean

```java id="u4kq8r"
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Predicate<Integer> isEven =
                num -> num % 2 == 0;

        System.out.println(isEven.test(4));
    }
}
```

---

# 2. Function<T, R>

Takes input and returns output.

```java id="7llr18"
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Function<Integer, Integer> square =
                x -> x * x;

        System.out.println(square.apply(5));
    }
}
```

---

# 3. Consumer<T>

Consumes data but returns nothing.

```java id="1w6b0n"
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        Consumer<String> print =
                name -> System.out.println(name);

        print.accept("Ankit");
    }
}
```

---

# 4. Supplier<T>

Supplies data without input.

```java id="v5l6ti"
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        Supplier<Double> random =
                () -> Math.random();

        System.out.println(random.get());
    }
}
```

---

# Functional Interface + Stream API

Functional Interfaces are heavily used in Stream API.

Example:

```java id="rqcbx5"
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> nums =
                Arrays.asList(1,2,3,4,5,6);

        nums.stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);
    }
}
```

Here:

* `filter()` uses Predicate
* `forEach()` uses Consumer

---

# Functional Interface vs Normal Interface

| Functional Interface    | Normal Interface             |
| ----------------------- | ---------------------------- |
| One abstract method     | Multiple abstract methods    |
| Used with lambda        | Cannot directly use lambda   |
| Represents one behavior | Represents complete contract |

---

# Advantages of Functional Interface

## 1. Cleaner Code

Less boilerplate.

---

## 2. Supports Functional Programming

Java becomes more expressive.

---

## 3. Better Readability

Especially in Streams.

---

## 4. Easier Event Handling

Widely used in:

* Spring Boot
* Multithreading
* Stream processing
* Collections

---

# Internal Working

When lambda is written:

```java id="1l6xb8"
Calculator add = (a, b) -> a + b;
```

Java internally:

* creates anonymous implementation
* overrides abstract method

Equivalent to:

```java id="hhrkfc"
Calculator add = new Calculator() {

    public int operate(int a, int b) {
        return a + b;
    }
};
```

---

# Interview Perspective (SDE-2)

## Why Functional Interface is Important?

Because modern Java frameworks heavily use it:

* Streams
* CompletableFuture
* Reactive Programming
* Spring APIs

---

# Common Interview Questions

## Can Functional Interface have multiple default methods?

Yes.

---

## Can it extend another interface?

Yes, but total abstract methods must remain one.

---

## Is Runnable a Functional Interface?

Yes.

```java id="0q9x4l"
@FunctionalInterface
public interface Runnable {
    void run();
}
```

---

# Runnable Example

```java id="mqo0k9"
Thread t = new Thread(() -> {
    System.out.println("Thread Running");
});

t.start();
```

---

# Important Point

Functional Interface ≠ Lambda Expression

| Functional Interface | Lambda            |
| -------------------- | ----------------- |
| Type/Contract        | Implementation    |
| Defines behavior     | Provides behavior |

---

# Final Interview Definition

> A Functional Interface in Java is an interface containing exactly one abstract method. It is mainly used to enable lambda expressions and functional programming features introduced in Java 8.


# Immutable in Java

The word **Immutable** means:

> "Cannot be changed after creation."

In Java, immutability can apply to:

1. Immutable Variables
2. Immutable Methods
3. Immutable Classes
4. Immutable Objects

Immutability is very important in:

* Multithreading
* Security
* Caching
* Functional Programming
* String handling
* Large-scale backend systems

---

# 1. Immutable Variable

An immutable variable is a variable whose value cannot be changed after initialization.

Usually created using:

```java
final
```

---

# Example of Immutable Variable

```java id="8bxujz"
public class Main {

    public static void main(String[] args) {

        final int age = 25;

        // age = 30;  ERROR

        System.out.println(age);
    }
}
```

---

# Why?

Because:

```java
final int age = 25;
```

means:

* value assigned once only
* reassignment not allowed

---

# Important Point

## Primitive Variable

```java id="fhbq87"
final int x = 10;
```

Value cannot change.

---

## Reference Variable

```java id="mckiz6"
final List<String> list = new ArrayList<>();
```

Reference cannot change, but object data may still change.

Example:

```java id="dqarfq"
final List<String> list = new ArrayList<>();

list.add("Java"); // Allowed

// list = new ArrayList<>(); ERROR
```

---

# Key Difference

| Type                     | What Cannot Change |
| ------------------------ | ------------------ |
| Primitive final variable | Value              |
| Object final reference   | Reference          |

---

# 2. Immutable Method

Java does not officially have "immutable methods", but generally it refers to:

> Methods that do not modify object state.

These methods:

* only read data
* return new objects instead of changing existing ones

Such methods are also called:

* Pure methods
* Non-mutating methods

---

# Example

```java id="uvfjlwm"
class Calculator {

    int square(int x) {
        return x * x;
    }
}
```

This method:

* does not change any object state
* produces same output for same input

Hence behaves immutably.

---

# Mutable Method Example

```java id="hby7rp"
class Counter {

    int count = 0;

    void increment() {
        count++;
    }
}
```

This modifies state.

So it is mutable behavior.

---

# Immutable Style Method Example

```java id="h7rtkt"
class Student {

    private final String name;

    Student(String name) {
        this.name = name;
    }

    Student changeName(String newName) {
        return new Student(newName);
    }

    String getName() {
        return name;
    }
}
```

---

# Working

```java id="uqjlwm"
Student s1 = new Student("Ankit");

Student s2 = s1.changeName("Rahul");
```

Instead of changing old object:

* new object created

This is immutable style programming.

---

# 3. Immutable Class

An immutable class is a class whose objects cannot be modified after creation.

---

# Rules to Make Immutable Class

## Rule 1: Make class final

Prevents inheritance.

```java
final class Student
```

---

## Rule 2: Make fields private and final

```java
private final String name;
```

---

## Rule 3: No setter methods

Do not allow modification.

---

## Rule 4: Initialize fields using constructor

---

## Rule 5: Return copies of mutable objects

To prevent external modification.

---

# Complete Immutable Class Example

```java id="s2u7nc"
final class Student {

    private final int id;
    private final String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
```

---

# Usage

```java id="g6u2n6"
public class Main {

    public static void main(String[] args) {

        Student s =
            new Student(1, "Ankit");

        System.out.println(s.getName());

        // Cannot modify object
    }
}
```

---

# Why Immutable?

Because:

* no setters
* fields are final
* class is final

Object state can never change.

---

# Real Java Example: String Class

Java `String` is immutable.

Example:

```java id="zicpdw"
String s = "Hello";

s.concat(" World");

System.out.println(s);
```

Output:

```text
Hello
```

Why?

Because:

* `concat()` creates new object
* original object unchanged

---

# Correct Way

```java id="jlwm8k"
s = s.concat(" World");

System.out.println(s);
```

Output:

```text
Hello World
```

---

# Internal Working of String

```text
Original:
s → "Hello"

After concat:
New object → "Hello World"

Old object still exists
```

---

# Immutable Object

An immutable object is:

> an object whose internal state cannot change after creation.

Example:

* String
* Integer
* LocalDate

---

# Benefits of Immutable Classes

---

# 1. Thread Safety

Immutable objects are naturally thread-safe.

No synchronization needed.

---

# Example

Multiple threads reading same String:

```java id="63e3be"
String name = "Ankit";
```

Safe because:

* no thread can modify it

---

# 2. Security

Data cannot be altered unexpectedly.

Useful in:

* Banking
* Authentication
* Tokens

---

# 3. Easier Caching

Because state never changes.

---

# 4. Easier Debugging

No accidental modifications.

---

# 5. Better Hashing

Immutable objects work well in:

* HashMap
* HashSet

---

# Why String is Immutable?

Interview favorite question.

Reasons:

---

## 1. Security

Used in:

* URLs
* Database connections
* File paths

If mutable:

* hackers could modify them

---

## 2. Thread Safety

Shared across threads safely.

---

## 3. String Pool Optimization

Java reuses String objects.

```java id="x4pukv"
String a = "Java";
String b = "Java";
```

Both point to same object.

Possible only because immutable.

---

# Mutable vs Immutable

| Mutable                         | Immutable        |
| ------------------------------- | ---------------- |
| Can change after creation       | Cannot change    |
| Thread unsafe                   | Thread safe      |
| More memory efficient sometimes | More secure      |
| Harder debugging                | Easier debugging |

---

# Mutable Class Example

```java id="ywmtq3"
class Employee {

    private String name;

    void setName(String name) {
        this.name = name;
    }
}
```

State changes possible.

---

# Immutable Class Example

```java id="f1q8qi"
final class Employee {

    private final String name;

    Employee(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
```

No state modification possible.

---

# Important Interview Question

# Is final class always immutable?

No.

Example:

```java id="od4zz9"
final class Test {

    List<String> list =
        new ArrayList<>();
}
```

Still mutable because:

* list contents can change

---

# How to Make Mutable Fields Safe

Use defensive copying.

---

# Example

```java id="qarfza"
final class Company {

    private final List<String> employees;

    Company(List<String> employees) {
        this.employees =
            new ArrayList<>(employees);
    }

    List<String> getEmployees() {
        return new ArrayList<>(employees);
    }
}
```

Now external code cannot modify internal list.

---

# SDE-2 Perspective

Immutable objects are heavily used in:

* Microservices
* Distributed systems
* Concurrent programming
* Event-driven systems
* Functional programming

Because:

* easier to reason about
* avoids race conditions
* safer shared state

---

# Interview Definitions

## Immutable Variable

> A variable whose value/reference cannot be reassigned after initialization.

---

## Immutable Method

> A method that does not modify object state and usually returns new values or objects.

---

## Immutable Class

> A class whose objects cannot be modified after creation.

---

# Quick Revision

| Concept          | Meaning                         |
| ---------------- | ------------------------------- |
| final variable   | Reference/value cannot change   |
| Immutable object | State cannot change             |
| Immutable class  | Objects always remain unchanged |
| String           | Most common immutable class     |


# Wrapper Class in Java

A **Wrapper Class** is a class that converts primitive data types into objects.

Java provides wrapper classes for all primitive types.

---

# Why Wrapper Classes Exist

Java has two worlds:

| Primitive Types | Objects   |
| --------------- | --------- |
| int             | Integer   |
| double          | Double    |
| char            | Character |
| boolean         | Boolean   |

Many Java features work only with objects:

* Collections
* Generics
* Streams
* Frameworks
* Synchronization

So Java created Wrapper Classes.

---

# Primitive vs Wrapper

| Primitive | Wrapper Class |
| --------- | ------------- |
| byte      | Byte          |
| short     | Short         |
| int       | Integer       |
| long      | Long          |
| float     | Float         |
| double    | Double        |
| char      | Character     |
| boolean   | Boolean       |

---

# Simple Example

```java id="r4xlg7"
int num = 10;
```

Primitive value.

---

# Wrapper Version

```java id="f9xwo4"
Integer num = Integer.valueOf(10);
```

Now `num` is an object.

---

# Real Meaning

Wrapper class literally "wraps" primitive inside an object.

---

# Internal Working

Example:

```java id="syqkgn"
Integer x = 10;
```

Internally behaves like:

```java id="fj2wdh"
Integer x = Integer.valueOf(10);
```

Java creates an Integer object containing value `10`.

---

# Why Java Needed Wrapper Classes

Because primitive types:

* are not objects
* cannot use methods
* cannot work with collections directly

---

# Example Problem

```java id="jvdrja"
ArrayList<int> list = new ArrayList<>();
```

INVALID.

Collections only accept objects.

---

# Correct

```java id="n7v4b3"
ArrayList<Integer> list = new ArrayList<>();
```

---

# Wrapper Class Object Structure

Conceptually:

```text id="n06u5u"
Integer Object
----------------
value = 10
methods:
- parseInt()
- compareTo()
- toString()
- etc.
```

---

# Converting Primitive → Wrapper

Called:

> Boxing

---

# 1. Manual Boxing

```java id="0x20h0"
int num = 5;

Integer obj = Integer.valueOf(num);
```

---

# 2. Autoboxing (Automatic)

Java automatically converts primitive to wrapper.

```java id="tqvk7v"
int num = 5;

Integer obj = num;
```

Internally:

```java id="rrx7ma"
Integer obj = Integer.valueOf(num);
```

---

# Converting Wrapper → Primitive

Called:

> Unboxing

---

# 1. Manual Unboxing

```java id="a9q88d"
Integer obj = Integer.valueOf(10);

int num = obj.intValue();
```

---

# 2. Auto-Unboxing

```java id="sxjlwm"
Integer obj = 10;

int num = obj;
```

Internally:

```java id="egm2s4"
int num = obj.intValue();
```

---

# Complete Example

```java id="u1g4zh"
public class Main {

    public static void main(String[] args) {

        // Primitive
        int a = 10;

        // Boxing
        Integer obj = a;

        // Unboxing
        int b = obj;

        System.out.println(a);
        System.out.println(obj);
        System.out.println(b);
    }
}
```

---

# Before Java 5

Autoboxing did not exist.

You had to write:

```java id="khmbh9"
Integer obj = Integer.valueOf(10);

int num = obj.intValue();
```

---

# After Java 5

Automatic conversion introduced.

```java id="yzlb55"
Integer obj = 10;

int num = obj;
```

---

# Important Wrapper Methods

---

# Integer Class Methods

## 1. parseInt()

Converts String → int

```java id="hluw4r"
String s = "123";

int num = Integer.parseInt(s);

System.out.println(num);
```

---

# 2. valueOf()

Converts primitive/String → Integer object

```java id="m1qjtb"
Integer obj = Integer.valueOf("100");
```

---

# 3. toString()

Converts number → String

```java id="7z8xhz"
Integer num = 50;

String s = num.toString();
```

---

# Common Conversions

---

# String → int

```java id="0q0cv2"
String s = "200";

int num = Integer.parseInt(s);
```

---

# String → Integer

```java id="nq1i4m"
Integer obj = Integer.valueOf("200");
```

---

# int → String

```java id="as01ft"
int num = 100;

String s = String.valueOf(num);
```

---

# Integer → String

```java id="o4oq1z"
Integer obj = 100;

String s = obj.toString();
```

---

# int → double

```java id="4qmfg5"
int x = 10;

double y = (double)x;
```

Primitive casting, not wrapper conversion.

---

# Real-World Example

---

# Input from User

```java id="kjk3kp"
Scanner sc = new Scanner(System.in);

String age = sc.nextLine();

int actualAge = Integer.parseInt(age);
```

User input always comes as String.

Wrapper classes help convert it.

---

# Collections Use Wrapper Classes

```java id="9nnqyb"
ArrayList<Integer> list =
        new ArrayList<>();

list.add(10);
list.add(20);
```

Internally:

```java id="x46xum"
list.add(Integer.valueOf(10));
```

---

# Why Generics Cannot Use Primitive Types

Generics work only with objects.

So:

```java id="1q6wv4"
List<int>   // INVALID
```

But:

```java id="lymj5k"
List<Integer> // VALID
```

---

# Wrapper Class Caching

Very important interview topic.

---

# Example

```java id="9o8fjd"
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
```

Output:

```text id="4v5rvx"
true
```

Why?

Because Java caches Integer objects from:

```text id="0t64t4"
-128 to 127
```

---

# But

```java id="tcl0yk"
Integer a = 200;
Integer b = 200;

System.out.println(a == b);
```

Output:

```text id="xw3cf7"
false
```

Different objects created.

---

# Correct Comparison

Always use:

```java id="l1r91z"
a.equals(b)
```

NOT:

```java id="xjrzl7"
a == b
```

---

# Internal Working of Integer Cache

Conceptually:

```text id="3a9qgb"
IntegerCache:
-128 → 127
```

Frequently used numbers reused for memory optimization.

---

# Mutable or Immutable?

Wrapper classes are immutable.

Example:

```java id="ugynx8"
Integer x = 10;

x = 20;
```

Actually:

* new object created
* old object unchanged

Just like String.

---

# Performance Issue

Wrapper classes are slower than primitives because:

* object creation
* heap memory
* boxing/unboxing overhead

---

# Example

```java id="j6m2x4"
Integer sum = 0;

for(int i=0; i<100000; i++) {
    sum += i;
}
```

This causes:

* repeated boxing/unboxing

Less efficient.

---

# Better

```java id="06qbm6"
int sum = 0;
```

Use primitives for performance-critical code.

---

# When to Use Wrapper Classes

---

# Use Wrapper Classes When:

| Situation          | Use Wrapper? |
| ------------------ | ------------ |
| Collections        | ✅            |
| Generics           | ✅            |
| Frameworks         | ✅            |
| Null values needed | ✅            |
| Database mapping   | ✅            |
| Streams API        | ✅            |

---

# Use Primitive Types When:

| Situation                 | Use Primitive? |
| ------------------------- | -------------- |
| Performance critical code | ✅              |
| Large loops               | ✅              |
| Math calculations         | ✅              |

---

# Null Support

Primitive cannot store null.

```java id="kwn1w6"
int x = null; // ERROR
```

Wrapper can:

```java id="85shvi"
Integer x = null;
```

Very useful in:

* databases
* APIs
* optional values

---

# Real Backend Example

Suppose database field:

```text id="7ljwwn"
age = NULL
```

Then:

```java id="qkcrwi"
Integer age;
```

works.

But:

```java id="2r4jlwm"
int age;
```

cannot represent null.

---

# Wrapper Classes in Stream API

```java id="xywq0m"
List<Integer> nums =
    Arrays.asList(1,2,3,4);

nums.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);
```

Streams require objects.

---

# Interview Questions

---

# Difference Between int and Integer

| int               | Integer        |
| ----------------- | -------------- |
| Primitive         | Object         |
| Faster            | Slower         |
| Cannot store null | Can store null |
| Less memory       | More memory    |
| No methods        | Has methods    |

---

# What is Autoboxing?

Automatic conversion:

* primitive → wrapper

Example:

```java id="yvf5q8"
Integer x = 10;
```

---

# What is Unboxing?

Automatic conversion:

* wrapper → primitive

Example:

```java id="6hbhwl"
int x = Integer.valueOf(10);
```

---

# Why Wrapper Classes are Immutable?

Because:

* safer
* thread-safe
* cacheable
* reusable

---

# Final Interview Definition

> Wrapper Classes in Java are object representations of primitive data types. They allow primitives to be used where objects are required, such as collections, generics, streams, and frameworks. Java supports automatic conversion between primitives and wrapper objects through autoboxing and unboxing.


# Serialization in Java

Serialization is the process of:

> Converting an object into a byte stream so that it can be:

* saved into a file
* sent over a network
* stored in database/cache
* transferred between systems

---

# Opposite Process

The reverse process is called:

> Deserialization

Which means:

* converting byte stream back into object

---

# Simple Real-Life Analogy

Suppose you have a toy car.

To ship it:

* you pack it into a box

Packing = Serialization
Unpacking = Deserialization

---

# Why Serialization is Needed

Objects exist in memory (RAM).

When application stops:

* memory data disappears

Serialization helps:

* preserve object state permanently

---

# Common Uses

Serialization is used in:

* File storage
* Network communication
* Distributed systems
* Microservices
* Caching
* Session management
* APIs
* Messaging systems

---

# Example Flow

```text id="4d9m9f"
Java Object
     ↓
Serialization
     ↓
Byte Stream
     ↓
Stored/Sent
     ↓
Deserialization
     ↓
Original Object
```

---

# Serializable Interface

In Java, to serialize an object:

Class must implement:

```java id="07ubgr"
Serializable
```

Marker interface.

---

# What is Marker Interface?

An interface with:

* no methods
* no fields

Used only to give information to JVM.

Example:

```java id="wxhv2r"
import java.io.Serializable;

class Student implements Serializable {
}
```

---

# Basic Serialization Example

---

# Step 1: Create Serializable Class

```java id="zjmu71"
import java.io.Serializable;

class Student implements Serializable {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

---

# Step 2: Serialize Object

```java id="n44r83"
import java.io.*;

public class Main {

    public static void main(String[] args)
            throws Exception {

        Student s =
            new Student(1, "Ankit");

        FileOutputStream file =
            new FileOutputStream("student.txt");

        ObjectOutputStream out =
            new ObjectOutputStream(file);

        out.writeObject(s);

        out.close();
        file.close();

        System.out.println("Object Serialized");
    }
}
```

---

# What Happens Internally

```text id="d17vvs"
Student Object
      ↓
JVM converts into bytes
      ↓
Bytes stored in file
```

---

# Deserialization Example

---

# Reading Object Back

```java id="sok9ud"
import java.io.*;

public class Main {

    public static void main(String[] args)
            throws Exception {

        FileInputStream file =
            new FileInputStream("student.txt");

        ObjectInputStream in =
            new ObjectInputStream(file);

        Student s =
            (Student) in.readObject();

        in.close();
        file.close();

        System.out.println(s.id);
        System.out.println(s.name);
    }
}
```

---

# Internal Working

```text id="vtjlwm"
File Bytes
     ↓
JVM reconstructs object
     ↓
Student object restored
```

---

# Important Classes Used

| Class              | Purpose                  |
| ------------------ | ------------------------ |
| Serializable       | Marks class serializable |
| ObjectOutputStream | Writes object            |
| ObjectInputStream  | Reads object             |
| FileOutputStream   | Writes bytes to file     |
| FileInputStream    | Reads bytes from file    |

---

# Why Serialization Works

Because JVM:

* converts object state into byte stream
* stores metadata of object
* reconstructs same object later

---

# What Gets Serialized?

Only:

* object state
* instance variables

NOT:

* methods
* constructors
* static variables
* transient variables

---

# Static Variable Example

```java id="jlwmzq"
class Student implements Serializable {

    static String college = "NIT";

    int id;
}
```

`college` is NOT serialized.

Because:

* static belongs to class
* not object

---

# Transient Keyword

Used to:

> exclude variables from serialization

---

# Example

```java id="lbdj7m"
import java.io.Serializable;

class User implements Serializable {

    String username;

    transient String password;
}
```

---

# Why?

Sensitive data should not be serialized.

Example:

* passwords
* OTPs
* tokens
* secret keys

---

# Example

```java id="3u5p0g"
User u = new User();

u.username = "ankit";
u.password = "1234";
```

After deserialization:

```text id="58u65k"
username = ankit
password = null
```

Because:

* transient skipped

---

# serialVersionUID

Very important interview topic.

---

# What is serialVersionUID?

A unique ID used to verify:

* serialized object version compatibility

---

# Example

```java id="w2e4yw"
class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    int id;
}
```

---

# Why Needed?

Suppose:

1. Object serialized
2. Class structure changed later
3. Deserialization attempted

JVM checks:

* version compatibility

If mismatch:

* InvalidClassException occurs

---

# Example Problem

Old class:

```java id="jlwmjp"
class Student {
    int id;
}
```

Later changed:

```java id="sy7lym"
class Student {
    int id;
    String name;
}
```

Deserialization may fail.

---

# serialVersionUID Solves This

By maintaining version consistency.

---

# Serialization in Memory

Objects are serialized into:

```text id="qm8qu4"
Binary format
```

NOT human-readable text.

---

# Deep Internal Working

When `writeObject()` runs:

JVM stores:

* class metadata
* object fields
* object graph
* references

---

# Object Graph

If object contains another object:

```java id="i20e9j"
class Address implements Serializable {
}

class Student implements Serializable {

    Address address;
}
```

Then:

* entire object graph serialized

Provided all objects are Serializable.

---

# Important Rule

Every referenced object must also implement Serializable.

Otherwise:

```text id="1mjlwm"
NotSerializableException
```

---

# Example

```java id="ddw6l2"
class Address {
}

class Student implements Serializable {

    Address address;
}
```

ERROR because:

* Address not Serializable

---

# Advantages of Serialization

---

# 1. Easy File Storage

Save objects directly.

---

# 2. Network Transfer

Send objects over sockets.

---

# 3. Session Management

Used in web servers.

---

# 4. Distributed Systems

Microservices exchange serialized data.

---

# 5. Caching

Redis/Hazelcast often store serialized objects.

---

# Disadvantages

---

# 1. Performance Overhead

Serialization is slower.

---

# 2. Security Risks

Unsafe deserialization can cause attacks.

---

# 3. Versioning Problems

Class changes may break compatibility.

---

# 4. Large Object Size

Serialized data can become large.

---

# Java Serialization vs JSON

| Java Serialization | JSON                 |
| ------------------ | -------------------- |
| Binary format      | Text format          |
| Java-specific      | Language independent |
| Faster sometimes   | More readable        |
| Less portable      | Highly portable      |

---

# Modern Backend Systems

Today many systems prefer:

* JSON
* Protocol Buffers
* Avro

instead of Java native serialization.

---

# Example Using JSON

```json id="jlwm8v"
{
  "id": 1,
  "name": "Ankit"
}
```

Portable across languages.

---

# Important Interview Questions

---

# What is Serialization?

> Converting object into byte stream.

---

# What is Deserialization?

> Reconstructing object from byte stream.

---

# Why Serializable is Marker Interface?

Because:

* it only signals JVM
* no methods needed

---

# Can Constructors be Serialized?

No.

During deserialization:

* constructor is NOT called normally

---

# Are Static Variables Serialized?

No.

Because:

* they belong to class

---

# Why Use transient?

To prevent serialization of sensitive data.

---

# Difference Between Externalizable and Serializable

| Serializable            | Externalizable             |
| ----------------------- | -------------------------- |
| Automatic serialization | Manual control             |
| Easier                  | Faster sometimes           |
| JVM handles process     | Developer controls process |

---

# Real Backend Example

Suppose user logs into website.

Server stores session object:

```java id="5pt9ft"
UserSession session;
```

To persist session:

* object serialized
* saved in Redis/database

Later:

* deserialized
* user session restored

---

# SDE-2 Perspective

Serialization is important in:

* Distributed systems
* Kafka messaging
* Caching systems
* Session replication
* Remote communication
* Event-driven architecture

Understanding serialization helps in:

* performance optimization
* backward compatibility
* secure system design

---

# Final Interview Definition

> Serialization in Java is the process of converting an object into a byte stream so it can be stored, transferred, or persisted. Deserialization is the reverse process of reconstructing the object from the byte stream.



# Stack Memory vs Heap Memory in Java

Memory management is one of the most important concepts in Java.

When a Java program runs, JVM mainly uses:

1. Stack Memory
2. Heap Memory

Both are used for different purposes.

---

# Simple Understanding

| Memory Type  | Purpose                          |
| ------------ | -------------------------------- |
| Stack Memory | Stores method-related data       |
| Heap Memory  | Stores objects and instance data |

---

# Real-Life Analogy

Imagine:

## Stack Memory

Like:

* temporary working desk

Things come and go quickly.

---

## Heap Memory

Like:

* large warehouse

Objects stay there until no longer needed.

---

# Stack Memory

Stack memory stores:

* Method calls
* Local variables
* Function parameters
* References to objects
* Partial execution state

Each thread gets:

* its own stack

---

# Heap Memory

Heap memory stores:

* Objects
* Instance variables
* Arrays
* Actual object data

Shared among:

* all threads

---

# Basic Example

```java id="jlwm0p"
class Student {

    int age = 20;
}

public class Main {

    public static void main(String[] args) {

        int x = 10;

        Student s = new Student();
    }
}
```

---

# Memory Breakdown

---

# Stack Memory Stores

```text id="jlwmq0"
main()
x = 10
s = reference/address
```

---

# Heap Memory Stores

```text id="1qjlwm"
Student Object
---------------
age = 20
```

---

# Visual Diagram

```text id="jlwmq1"
STACK MEMORY                  HEAP MEMORY
--------------               ----------------
main() frame                 Student Object
x = 10                       age = 20
s = 1010   ----------------> address 1010
```

---

# Very Important Concept

## Stack stores REFERENCE

## Heap stores ACTUAL OBJECT

---

# Method Call Example

```java id="jlwmq2"
class Test {

    void display() {

        int a = 5;

        System.out.println(a);
    }
}
```

When `display()` runs:

Stack stores:

```text id="jlwmq3"
display()
a = 5
```

After method completes:

* stack frame removed automatically

---

# Stack Frame

Each method call creates:

> Stack Frame

Containing:

* local variables
* method parameters
* return address

---

# Example

```java id="jlwmq4"
public class Main {

    static void fun() {

        int x = 10;
    }

    public static void main(String[] args) {

        fun();
    }
}
```

---

# Stack Flow

```text id="jlwmq5"
main() pushed
    ↓
fun() pushed
    ↓
fun() removed
    ↓
main() removed
```

LIFO order:

* Last In First Out

Like real stack.

---

# Heap Memory Example

```java id="jlwmq6"
class Car {

    String brand;
}
```

```java id="jlwmq7"
Car c1 = new Car();
Car c2 = new Car();
```

Heap contains:

* two separate objects

---

# Heap Diagram

```text id="jlwmq8"
STACK                     HEAP
-----                     -----------------
c1 -----> Object1
c2 -----> Object2
```

---

# String Example

```java id="jlwmq9"
String s = "Java";
```

---

# Stack Stores

```text id="jlwmra"
s = reference
```

---

# Heap Stores

```text id="jlwmrb"
"Java" object
```

Actually stored inside:

* String Pool (special heap area)

---

# Array Example

```java id="jlwmrc"
int[] arr = new int[5];
```

---

# Stack

```text id="jlwmrd"
arr = reference
```

---

# Heap

```text id="jlwmre"
[0,0,0,0,0]
```

Actual array stored in heap.

---

# Primitive Variables

---

# Local Primitive Variables

Stored in:

* Stack

Example:

```java id="jlwmrf"
int x = 10;
```

---

# Instance Primitive Variables

Stored in:

* Heap (inside object)

Example:

```java id="jlwmrg"
class Student {

    int age = 20;
}
```

`age` stored inside object in heap.

---

# Static Variables

Stored in:

> Method Area / MetaSpace

NOT stack or heap directly.

Example:

```java id="jlwmrh"
static int count = 0;
```

Shared across objects.

---

# Important Rule

| Variable Type     | Memory Location |
| ----------------- | --------------- |
| Local variable    | Stack           |
| Object            | Heap            |
| Instance variable | Heap            |
| Object reference  | Stack           |
| Static variable   | Method Area     |
| Methods/bytecode  | Method Area     |

---

# Why Heap Needed?

Because:

* object size may be dynamic
* objects may outlive methods
* objects shared across methods

---

# Why Stack Needed?

Because:

* method execution is temporary
* very fast allocation/deallocation
* automatic cleanup

---

# Garbage Collection

Heap memory is managed by:

> Garbage Collector (GC)

---

# Example

```java id="jlwmri"
Student s = new Student();

s = null;
```

Object becomes:

* unreachable

GC removes it later.

---

# Stack Cleanup

Stack cleanup is automatic.

When method ends:

* stack frame destroyed immediately

No GC required.

---

# Performance Difference

| Stack           | Heap               |
| --------------- | ------------------ |
| Faster          | Slower             |
| Fixed order     | Dynamic allocation |
| Auto cleanup    | GC cleanup         |
| Thread-specific | Shared             |

---

# Stack Overflow

Occurs when:

* too many method calls
* infinite recursion

Example:

```java id="jlwmrj"
void fun() {
    fun();
}
```

Causes:

```text id="jlwmrk"
StackOverflowError
```

Because stack frames keep increasing.

---

# Heap Overflow

Occurs when:

* too many objects created

Example:

```java id="jlwmrl"
while(true) {
    new Student();
}
```

Causes:

```text id="jlwmrm"
OutOfMemoryError
```

---

# Stack vs Heap Comparison

| Feature     | Stack Memory | Heap Memory           |
| ----------- | ------------ | --------------------- |
| Stores      | Method data  | Objects               |
| Allocation  | Automatic    | Dynamic               |
| Speed       | Faster       | Slower                |
| Cleanup     | Automatic    | Garbage Collector     |
| Shared?     | No           | Yes                   |
| Thread-safe | Naturally    | Needs synchronization |
| Size        | Smaller      | Larger                |

---

# Deep Internal Example

```java id="jlwmrn"
class Student {

    int age;

    Student(int age) {
        this.age = age;
    }
}

public class Main {

    public static void main(String[] args) {

        int x = 10;

        Student s =
            new Student(20);
    }
}
```

---

# Step-by-Step Memory Allocation

---

# Step 1

```java id="jlwmro"
int x = 10;
```

Stored in stack.

---

# Step 2

```java id="jlwmrp"
new Student(20)
```

Object created in heap.

---

# Step 3

```java id="jlwmrq"
Student s
```

Reference stored in stack.

---

# Final Structure

```text id="paamik1"
STACK                          HEAP
------                         ----------------
main()
x = 10

s = 1001  ------------------> Student Object
                               age = 20
```

---

# Method Parameter Example

```java id="paamik2"
void test(int a) {

}
```

Parameter `a` stored in:

* stack frame of method

---

# Object Sharing Example

```java id="paamik3"
Student s1 = new Student();

Student s2 = s1;
```

---

# Stack

```text id="paamik4"
s1 = 1010
s2 = 1010
```

---

# Heap

```text id="paamik5"
One Student Object
```

Both references point to same object.

---

# Important Interview Question

# Why Objects Are Stored in Heap?

Because:

* size unknown at compile time
* dynamic lifetime
* shared accessibility

---

# Why References Stored in Stack?

Because:

* references are local to method
* stack access is faster

---

# JVM Memory Areas

Java memory mainly contains:

```text id="paamik6"
1. Stack Area
2. Heap Area
3. Method Area
4. PC Register
5. Native Method Stack
```

---

# SDE-2 Perspective

Understanding stack and heap is important for:

* memory optimization
* performance tuning
* debugging memory leaks
* garbage collection analysis
* concurrency systems

Especially in:

* large backend systems
* microservices
* high-throughput applications

---

# Final Interview Definition

## Stack Memory

> Stack memory stores method execution data such as local variables, method calls, and references. It works in LIFO order and is automatically managed.

---

## Heap Memory

> Heap memory stores dynamically created objects and instance variables. It is shared among threads and managed by the garbage collector.
