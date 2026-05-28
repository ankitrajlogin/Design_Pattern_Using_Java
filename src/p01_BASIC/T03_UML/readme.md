# UML (Unified Modeling Language) – Complete Notes

## 1. What is UML?

**UML (Unified Modeling Language)** is a **standard visual language** used to **design, visualize, construct, and document software systems**.

It helps developers, architects, managers, and clients understand:

* How the system works
* Structure of the system
* Flow of data
* Interaction between objects/classes
* Architecture and deployment

UML is mainly used in:

* Object-Oriented Programming (OOP)
* System Design
* Software Architecture
* Documentation
* Low Level Design (LLD)
* High Level Design (HLD)

---

# Why UML is Important

Without UML:

* Code becomes difficult to understand
* Team communication becomes hard
* Large systems become messy
* Difficult to maintain projects

With UML:

* Better planning
* Better communication
* Easier debugging
* Easier scaling
* Proper documentation
* Clear architecture

---

# Real Life Example

Suppose you are building:

* Parking Lot System
* Food Delivery App
* Banking System
* E-Commerce Website

Before coding, companies first create UML diagrams to:

* Understand requirements
* Plan classes
* Plan interactions
* Identify dependencies
* Reduce future issues

---

# 2. UML History

UML was developed by:

* Grady Booch
* James Rumbaugh
* Ivar Jacobson

These are called:

## “Three Amigos”

Later UML became standard under:

* Object Management Group

---

# 3. Goals of UML

UML aims to:

### 1. Visualize the System

Represent system graphically.

### 2. Specify the System

Define structure and behavior clearly.

### 3. Construct the System

Help developers during implementation.

### 4. Document the System

Provide long-term documentation.

---

# 4. UML is NOT a Programming Language

UML is:

* NOT Java
* NOT Python
* NOT executable code

It is a:

## Modeling Language

Meaning:

You design first → implement later.

---

# 5. Types of UML Diagrams

UML diagrams are divided into:

# A. Structural Diagrams

Show:

## “How system is organized”

They represent:

* Classes
* Objects
* Components
* Relationships
* Architecture

---

# B. Behavioral Diagrams

Show:

## “How system behaves”

They represent:

* Flow
* Interaction
* Activities
* State changes

---

# 6. UML Diagram Classification

# Structural Diagrams

| Diagram                     | Purpose               |
| --------------------------- | --------------------- |
| Class Diagram               | Structure of classes  |
| Object Diagram              | Snapshot of objects   |
| Component Diagram           | Software components   |
| Deployment Diagram          | Physical deployment   |
| Package Diagram             | Package organization  |
| Composite Structure Diagram | Internal structure    |
| Profile Diagram             | Custom UML extensions |

---

# Behavioral Diagrams

| Diagram                      | Purpose              |
| ---------------------------- | -------------------- |
| Use Case Diagram             | User interactions    |
| Sequence Diagram             | Order of execution   |
| Activity Diagram             | Workflow             |
| State Machine Diagram        | State transitions    |
| Communication Diagram        | Object communication |
| Timing Diagram               | Timing behavior      |
| Interaction Overview Diagram | Interaction flow     |

---

# Most Important UML Diagrams for Interviews

Usually asked in SDE interviews:

1. Class Diagram
2. Sequence Diagram
3. Use Case Diagram
4. Activity Diagram
5. Deployment Diagram

---

# 7. Class Diagram (Most Important)

A Class Diagram represents:

* Classes
* Attributes
* Methods
* Relationships

It is the backbone of OOP design.

---

# Example

Suppose:

## Parking Lot System

Classes:

* Vehicle
* Car
* Bike
* ParkingSpot
* Ticket
* Payment

---

# UML Class Representation

```text
+-------------------+
|      Car          |
+-------------------+
| - carNumber       |
| - ownerName       |
+-------------------+
| + park()          |
| + remove()        |
+-------------------+
```

---

# Symbols in UML

| Symbol | Meaning   |
| ------ | --------- |
| +      | Public    |
| -      | Private   |
| #      | Protected |
| ~      | Default   |

---

# Example

```java
private int age;
```

In UML:

```text
- age : int
```

---

# Method Representation

```java
public void park()
```

In UML:

```text
+ park() : void
```

---

# 8. Relationships in UML

VERY IMPORTANT.

---

# A. Association

Simple relationship between two classes.

Example:

* Customer uses Bank

```text
Customer -------- Bank
```

Meaning:

Both can exist independently.

---

# Example in Java

```java
class Customer {
    Bank bank;
}
```

---

# B. Aggregation

Weak HAS-A relationship.

Example:

* Department has Teachers

Teacher can exist without department.

Notation:

```text
Department ◇------ Teacher
```

(Empty diamond)

---

# C. Composition

Strong HAS-A relationship.

Example:

* House has Rooms

If House destroyed → Rooms destroyed.

Notation:

```text
House ◆------ Room
```

(Filled diamond)

---

# D. Inheritance

IS-A relationship.

Example:

* Car IS-A Vehicle

Notation:

```text
Car --------▷ Vehicle
```

---

# E. Dependency

Temporary usage relationship.

Example:

* PaymentService uses NotificationService

Notation:

```text
ClassA -------> ClassB
```

(Dashed arrow)

---

# F. Realization

Used with interfaces.

Example:

```java
interface Animal {}
class Dog implements Animal {}
```

Notation:

```text
Dog -----▷ Animal
```

(Dashed triangle)

---

# 9. Use Case Diagram

Represents:

## Interaction between User and System

---

# Components

| Component       | Meaning       |
| --------------- | ------------- |
| Actor           | User/System   |
| Use Case        | Functionality |
| System Boundary | System scope  |

---

# Example

ATM System:

Actors:

* Customer
* Bank Server

Use Cases:

* Withdraw Cash
* Check Balance
* Deposit Money

---

# Diagram Idea

```text
Customer ---> Withdraw Cash
Customer ---> Check Balance
```

---

# 10. Sequence Diagram

Shows:

## Order of execution over time

Important in:

* API flow
* Backend systems
* Microservices

---

# Example

Food Order:

```text
User -> App -> Payment -> Restaurant
```

Execution order matters.

---

# Components

| Component      | Meaning          |
| -------------- | ---------------- |
| Lifeline       | Object existence |
| Activation Bar | Execution        |
| Message Arrow  | Communication    |

---

# Example Flow

```text
User -> LoginService : login()
LoginService -> DB : validate()
DB --> LoginService : success
LoginService --> User : token
```

---

# 11. Activity Diagram

Represents:

## Workflow or business process

Similar to flowchart.

---

# Used For

* Algorithms
* User flows
* Business logic

---

# Example

```text
Start
  ↓
Login
  ↓
Validate User
  ↓
Success? ---- No ---> Error
  |
 Yes
  ↓
Dashboard
  ↓
End
```

---

# 12. State Machine Diagram

Represents:

## State changes of an object

---

# Example

Order states:

```text
Placed → Packed → Shipped → Delivered
```

---

# Used In

* E-commerce
* ATM
* Gaming
* Real-time systems

---

# 13. Deployment Diagram

Represents:

## Physical deployment architecture

Shows:

* Servers
* Databases
* Network
* Cloud
* Containers

---

# Example

```text
Client Browser
      |
Web Server
      |
Application Server
      |
Database Server
```

---

# Used In

* DevOps
* Cloud systems
* Distributed systems

---

# 14. Component Diagram

Represents:

## High-level software modules/components

Example:

```text
Frontend
Backend
Auth Service
Payment Service
Database
```

Useful in:

* Microservices
* Enterprise systems

---

# 15. Object Diagram

Snapshot of objects at runtime.

Example:

```text
car1 : Car
car2 : Car
```

Class diagram shows blueprint.

Object diagram shows actual objects.

---

# 16. Package Diagram

Represents package organization.

Example:

```text
com.project.auth
com.project.payment
com.project.user
```

Used for:

* Modular systems
* Large codebases

---

# 17. UML in Software Development Lifecycle

# Step 1: Requirement Gathering

Use Case Diagram

# Step 2: System Design

Class Diagram
Component Diagram

# Step 3: Flow Design

Sequence Diagram
Activity Diagram

# Step 4: Infrastructure

Deployment Diagram

# Step 5: Development

Convert UML → Code

---

# 18. UML and OOP Connection

UML is heavily connected to OOP.

| OOP Concept   | UML Representation |
| ------------- | ------------------ |
| Class         | Class Diagram      |
| Object        | Object Diagram     |
| Inheritance   | Generalization     |
| Encapsulation | Access Modifiers   |
| Abstraction   | Interfaces         |
| Polymorphism  | Dynamic behavior   |

---

# 19. UML Example — Parking Lot System

---

# Main Classes

```text
Vehicle
Car
Bike
Truck
ParkingSpot
Ticket
Payment
ParkingFloor
ParkingLot
```

---

# Relationships

| Relation    | Example                       |
| ----------- | ----------------------------- |
| Inheritance | Car → Vehicle                 |
| Composition | ParkingLot → Floor            |
| Aggregation | Floor → Spot                  |
| Association | Ticket ↔ Vehicle              |
| Dependency  | PaymentService → Notification |

---

# Sequence Example

```text
User -> EntryGate
EntryGate -> ParkingLot
ParkingLot -> SpotManager
SpotManager --> Spot
EntryGate --> Ticket
```

---

# 20. Advantages of UML

| Advantage          | Description                |
| ------------------ | -------------------------- |
| Better Design      | Structured planning        |
| Easy Communication | Teams understand visually  |
| Documentation      | Helpful for future         |
| Reusability        | Better architecture        |
| Scalability        | Easier expansion           |
| Reduced Bugs       | Detect design issues early |

---

# 21. Disadvantages of UML

| Disadvantage     | Description               |
| ---------------- | ------------------------- |
| Time Consuming   | Large diagrams take time  |
| Maintenance Cost | Updating diagrams         |
| Overengineering  | Too much design sometimes |
| Learning Curve   | Beginners struggle        |

---

# 22. UML Tools

Popular UML tools:

| Tool            | Usage            |
| --------------- | ---------------- |
| StarUML         | Professional UML |
| Draw.io         | Free diagrams    |
| Lucidchart      | Collaboration    |
| PlantUML        | Code-based UML   |
| Visual Paradigm | Enterprise UML   |

---

# 23. UML vs Flowchart

| UML               | Flowchart            |
| ----------------- | -------------------- |
| Software modeling | General process flow |
| Object-oriented   | Procedural           |
| Multiple diagrams | Single style         |
| Industry standard | Simpler              |

---

# 24. UML vs ER Diagram

| UML Class Diagram | ER Diagram        |
| ----------------- | ----------------- |
| Software classes  | Database entities |
| Methods included  | No methods        |
| OOP focused       | Database focused  |

---

# 25. UML Implementation in Real Projects

# Example Flow in Companies

## Requirement Phase

Business team creates:

* Use Case Diagram

---

## Design Phase

Architect creates:

* Class Diagram
* Component Diagram
* Sequence Diagram

---

## Development Phase

Developers:

* Implement classes
* Follow relationships
* Create APIs

---

## Deployment Phase

DevOps:

* Uses Deployment Diagram

---

# 26. Important UML Terms

| Term         | Meaning           |
| ------------ | ----------------- |
| Actor        | External user     |
| Artifact     | Deployable file   |
| Node         | Physical machine  |
| Lifeline     | Object existence  |
| Multiplicity | Number of objects |
| Interface    | Contract          |
| Stereotype   | UML extension     |

---

# 27. Multiplicity in UML

Represents quantity.

Examples:

| Symbol | Meaning     |
| ------ | ----------- |
| 1      | Exactly one |
| 0..1   | Optional    |
| *      | Many        |
| 1..*   | One or more |

---

# Example

```text
Customer 1 ---- * Orders
```

One customer can have many orders.

---

# 28. UML Best Practices

## 1. Keep diagrams simple

## 2. Avoid unnecessary details

## 3. Use meaningful names

## 4. Maintain consistency

## 5. Separate high-level and low-level diagrams

---

# 29. UML in Interviews

Interviewers ask UML to test:

* System design skills
* OOP understanding
* Architecture thinking
* Real-world design ability

---

# Common Interview Questions

1. Design UML for Parking Lot
2. UML for Food Delivery App
3. UML for Cab Booking
4. Difference between Aggregation and Composition
5. Explain Sequence Diagram
6. Explain Use Case Diagram
7. Draw ATM UML

---

# 30. Final Summary

# UML is:

A standard visual modeling language used to design and document software systems.

---

# Main Categories

## Structural Diagrams

Represent structure.

## Behavioral Diagrams

Represent behavior.

---

# Most Important Diagrams

* Class Diagram
* Sequence Diagram
* Use Case Diagram
* Activity Diagram
* Deployment Diagram

---

# Most Important Relationships

* Association
* Aggregation
* Composition
* Inheritance
* Dependency
* Realization

---

# Most Important Use

Before coding large systems.

# UML Relationships in Detail

These are the **most important relationships** in UML and OOP.

They define:

* How classes are connected
* Ownership
* Dependency
* Reusability
* Lifetime management
* Communication between objects

---

# Complete Relationship Hierarchy

```text id="owby7i"
Relationship
│
├── Association
│     ├── Aggregation
│     └── Composition
│
├── Inheritance
├── Dependency
└── Realization
```

---

# 1. Association

# Definition

Association represents:

## “One class uses or is connected to another class.”

It is the **most basic relationship** in UML.

---

# Real-Life Example

* Student attends College
* Customer uses Bank
* Driver drives Car

Both objects can exist independently.

---

# Key Idea

Association means:

## “HAS-A connection”

but:

## NO ownership

and:

## NO lifetime dependency

---

# Example

A customer can exist without a bank.

A bank can exist without a specific customer.

---

# UML Representation

```text id="2nrr6j"
Customer -------- Bank
```

Simple line.

---

# Java Example

```java id="ckg1je"
class Bank {
    String bankName;
}

class Customer {
    String customerName;
    Bank bank; // association
}
```

---

# How It Works

```java id="m7rkzh"
Bank hdfc = new Bank();
Customer c1 = new Customer();

c1.bank = hdfc;
```

Customer is associated with bank.

---

# Types of Association

---

# A. One-to-One

```text id="wk9k5z"
Person ---- Passport
```

One person → one passport.

---

# B. One-to-Many

```text id="0j13up"
Teacher ---- Students
```

One teacher → many students.

---

# C. Many-to-Many

```text id="0rc91f"
Students ---- Courses
```

Many students take many courses.

---

# D. Bidirectional Association

Both know each other.

```java id="0pp1c6"
class Student {
    College college;
}

class College {
    List<Student> students;
}
```

---

# E. Unidirectional Association

Only one side knows another.

```java id="v4n18j"
class Student {
    College college;
}
```

College doesn't know students.

---

# Key Characteristics

| Feature             | Association    |
| ------------------- | -------------- |
| Ownership           | No             |
| Lifetime Dependency | No             |
| Strength            | Weak           |
| Reusability         | High           |
| Relationship Type   | Uses/Connected |

---

# Real-Life Analogy

```text id="dy7yzj"
Person ↔ Mobile Network
```

You can change network.

Network still exists.

---

# Important Interview Point

Aggregation and Composition are special types of Association.

---

# 2. Aggregation

# Definition

Aggregation represents:

# “Weak HAS-A relationship”

One object contains another object.

BUT:

Contained object can exist independently.

---

# Real-Life Example

* Department has Teachers
* Team has Players
* Library has Books

If department is destroyed:

Teachers still exist.

---

# Key Idea

## Ownership exists

BUT:

## Lifetime dependency does NOT exist

---

# UML Symbol

```text id="8xij08"
Department ◇------ Teacher
```

Empty diamond.

Diamond is placed on owner side.

---

# Java Example

```java id="ij9eev"
class Teacher {
    String name;
}

class Department {
    List<Teacher> teachers;

    Department(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
```

---

# How It Works

```java id="uh8txy"
Teacher t1 = new Teacher();
Teacher t2 = new Teacher();

List<Teacher> list = new ArrayList<>();
list.add(t1);
list.add(t2);

Department d = new Department(list);
```

Teachers existed before department.

Even if department deleted:

Teachers remain alive.

---

# Characteristics

| Feature             | Aggregation |
| ------------------- | ----------- |
| Ownership           | Yes         |
| Lifetime Dependency | No          |
| Strength            | Medium      |
| Child Independent   | Yes         |

---

# Real-Life Analogy

```text id="whr4oe"
Team → Players
```

Player can join another team.

---

# Important Point

Aggregation = weak ownership.

---

# 3. Composition

# Definition

Composition represents:

# “Strong HAS-A relationship”

Child object CANNOT exist without parent.

---

# Real-Life Example

* House has Rooms
* Human has Heart
* Car has Engine

If parent destroyed:

Child also destroyed.

---

# Key Idea

## Strong ownership

AND

## Lifetime dependency exists

---

# UML Symbol

```text id="p5e6w7"
House ◆------ Room
```

Filled diamond.

---

# Java Example

```java id="5jvw0j"
class Room {
    Room() {
        System.out.println("Room created");
    }
}

class House {
    private Room room;

    House() {
        room = new Room();
    }
}
```

---

# How It Works

Room created inside House.

Outside object cannot control room.

If House destroyed:

Room destroyed.

---

# Characteristics

| Feature             | Composition |
| ------------------- | ----------- |
| Ownership           | Strong      |
| Lifetime Dependency | Yes         |
| Child Independent   | No          |
| Strength            | Strongest   |

---

# Real-Life Analogy

```text id="d8h35q"
Human → Heart
```

Heart cannot exist independently in system design context.

---

# Aggregation vs Composition

| Feature | Aggregation | Composition |
|---|---|
| Ownership | Weak | Strong |
| Lifetime Dependency | No | Yes |
| Child Exists Alone | Yes | No |
| UML Symbol | Empty Diamond | Filled Diamond |

---

# Interview Trick

Question:

Which is stronger?

Answer:

## Composition

---

# 4. Inheritance

# Definition

Inheritance represents:

# “IS-A relationship”

Child class acquires properties and methods of parent class.

---

# Real-Life Example

* Car IS-A Vehicle
* Dog IS-A Animal
* Manager IS-A Employee

---

# UML Symbol

```text id="vwk1e2"
Car --------▷ Vehicle
```

Solid line with hollow triangle.

Triangle points toward parent.

---

# Java Example

```java id="zt21b6"
class Vehicle {
    void start() {
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
}
```

---

# How It Works

```java id="8o0y0o"
Car c = new Car();
c.start();
```

Car inherited start() method.

---

# Advantages

| Advantage     | Description          |
| ------------- | -------------------- |
| Code Reuse    | Avoid duplicate code |
| Extensibility | Easy modification    |
| Polymorphism  | Runtime behavior     |
| Hierarchy     | Better organization  |

---

# Types of Inheritance

| Type         | Supported in Java Classes |
| ------------ | ------------------------- |
| Single       | Yes                       |
| Multilevel   | Yes                       |
| Hierarchical | Yes                       |
| Multiple     | No (via classes)          |
| Hybrid       | No directly               |

---

# Java Diamond Problem

Java avoids multiple inheritance of classes to avoid ambiguity.

Solved using interfaces.

---

# Real-Life Analogy

```text id="4yyvks"
Animal
   ↑
 Dog
```

Dog is an Animal.

---

# Important Interview Point

Inheritance should model:

## TRUE IS-A relationship

Wrong Example:

```text id="dc2j9g"
Car extends Engine ❌
```

Car is NOT an engine.

Correct:

```text id="wz4q2z"
Car has Engine ✅
```

That is composition.

---

# 5. Dependency

# Definition

Dependency means:

# “One class temporarily depends on another class.”

Weakest relationship.

---

# Key Idea

Used temporarily inside:

* Method parameter
* Local variable
* Method call

NOT stored permanently.

---

# Real-Life Example

* PaymentService uses NotificationService
* Compiler uses Parser

---

# UML Symbol

```text id="ekoqvz"
ClassA - - - -> ClassB
```

Dashed arrow.

---

# Java Example

```java id="4qkr4t"
class NotificationService {
    void send() {
        System.out.println("Notification sent");
    }
}

class PaymentService {

    void pay(NotificationService service) {
        service.send();
    }
}
```

---

# Key Observation

PaymentService does NOT own NotificationService.

Only uses temporarily.

---

# Characteristics

| Feature             | Dependency |
| ------------------- | ---------- |
| Ownership           | No         |
| Lifetime Dependency | No         |
| Strength            | Weakest    |
| Scope               | Temporary  |

---

# Association vs Dependency

| Feature | Association | Dependency |
|---|---|
| Stored as field | Yes |
| Temporary use | No |
| Long-term connection | Yes |

---

# Example

Association:

```java id="v2m66q"
class A {
    B b;
}
```

Dependency:

```java id="lgng0n"
void method(B b)
```

---

# 6. Realization

# Definition

Realization represents:

# “Class implements Interface”

Used in abstraction.

---

# Real-Life Example

* Dog implements Animal behavior
* Car implements Vehicle features

---

# UML Symbol

```text id="96xl2l"
Dog - - -▷ Animal
```

Dashed line + hollow triangle.

---

# Java Example

```java id="jlwmzw"
interface Animal {
    void sound();
}

class Dog implements Animal {

    public void sound() {
        System.out.println("Bark");
    }
}
```

---

# How It Works

Interface provides:

## Contract

Class provides:

## Implementation

---

# Characteristics

| Feature      | Realization |
| ------------ | ----------- |
| Used With    | Interfaces  |
| Purpose      | Abstraction |
| Relationship | Implements  |
| Coupling     | Loose       |

---

# Realization vs Inheritance

| Feature | Inheritance | Realization |
|---|---|
| extends | Yes |
| implements | No |
| Parent Type | Class |
| Interface Used | No |

---

# Example

Inheritance:

```java id="9l4djp"
class Dog extends Animal
```

Realization:

```java id="0z7ec4"
class Dog implements Animal
```

---

# Complete Comparison Table

| Relationship | Meaning              | Ownership    | Lifetime Dependency | Symbol          |
| ------------ | -------------------- | ------------ | ------------------- | --------------- |
| Association  | Connected            | No           | No                  | Simple line     |
| Aggregation  | Weak HAS-A           | Weak         | No                  | Empty diamond   |
| Composition  | Strong HAS-A         | Strong       | Yes                 | Filled diamond  |
| Inheritance  | IS-A                 | Parent-child | Yes                 | Solid triangle  |
| Dependency   | Temporary use        | No           | No                  | Dashed arrow    |
| Realization  | Implements interface | Contract     | No                  | Dashed triangle |

---

# Memory Trick

| Relationship | Keyword          |
| ------------ | ---------------- |
| Association  | Connected        |
| Aggregation  | Weak HAS-A       |
| Composition  | Strong HAS-A     |
| Inheritance  | IS-A             |
| Dependency   | Uses temporarily |
| Realization  | Implements       |

---

# Important Interview Questions

1. Difference between Aggregation and Composition
2. Difference between Association and Dependency
3. Why Composition preferred over Inheritance?
4. Realization vs Inheritance
5. When to use Composition?
6. Explain HAS-A vs IS-A

---

# Most Important Understanding

# HAS-A Relationships

* Association
* Aggregation
* Composition

---

# IS-A Relationship

* Inheritance

---

# IMPLEMENTS Relationship

* Realization

---

# USES Relationship

* Dependency
