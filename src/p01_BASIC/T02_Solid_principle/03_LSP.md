# Liskov Substitution Principle (LSP) — Complete Notes

The **Liskov Substitution Principle (LSP)** is the **L** in **SOLID Principles**.

It is one of the most important principles in Object-Oriented Design because it teaches:

> How inheritance should be used correctly.

---

# Definition of LSP

Given by:
Barbara Liskov

Formal definition:

> Objects of a superclass should be replaceable with objects of a subclass without affecting correctness of the program.

Simple meaning:

> Child class should behave like the parent class expects.

OR

> If class B extends class A, then we should be able to replace A with B safely.

---

# Simple Real Meaning

Suppose:

```java
Bird bird = new Sparrow();
```

This should work normally.

If replacing `Bird` with `Sparrow` breaks the program,
then inheritance is wrong.

---

# Main Goal of LSP

LSP ensures:

* proper inheritance
* predictable behavior
* reusable code
* extensible systems
* no hidden surprises

---

# Core Idea

Inheritance is NOT only:

```text
IS-A relationship
```

It is also:

```text
Behavior compatibility
```

---

# Incorrect Understanding

Many developers think:

```text
Square IS-A Rectangle
```

So:

```java
Square extends Rectangle
```

But this can break behavior.

LSP says:

> Child should preserve parent behavior contract.

---

# Parent Contract

A parent class creates expectations.

Example:

```java
Rectangle r = new Rectangle();

r.setWidth(5);
r.setHeight(10);

System.out.println(r.getArea());
```

Expected:

```text
Area = 50
```

because:

* width independent
* height independent

This is the parent behavior contract.

---

# Classic Rectangle-Square LSP Violation

---

# BAD DESIGN

```java
class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
```

---

```java
class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
```

---

# Client Code

```java
Rectangle r = new Square();

r.setWidth(5);
r.setHeight(10);

System.out.println(r.getArea());
```

---

# Expected Output

```text
50
```

---

# Actual Output

```text
100
```

Because:

* square forces equal sides

---

# Why LSP Violated?

Parent behavior:

```text
Width and height are independent
```

Child behavior:

```text
Changing width changes height
```

So child changed the expected behavior.

That breaks substitutability.

Hence:

❌ LSP violation

---

# Important Understanding

Mathematically:

```text
Square IS-A Rectangle
```

But behaviorally in OOP:

```text
Square should NOT extend Rectangle
```

because behaviors differ.

---

# Real Problem

The issue is NOT inheritance itself.

The issue is:

```text
Wrong inheritance
```

---

# Signs of LSP Violation

You may have LSP violation when:

* subclass overrides behavior unexpectedly
* subclass throws unsupported exceptions
* subclass changes business rules
* subclass removes functionality
* subclass requires extra conditions
* client code needs `instanceof`
* child class behaves differently from parent expectation

---

# Another Classic Example — Bird

---

# BAD DESIGN

```java
class Bird {
    void fly() {
        System.out.println("Flying");
    }
}
```

---

```java
class Sparrow extends Bird {
}
```

---

```java
class Ostrich extends Bird {

    @Override
    void fly() {
        throw new UnsupportedOperationException();
    }
}
```

---

# Problem

```java
Bird bird = new Ostrich();
bird.fly();
```

Program crashes.

Parent promised:

```text
Every bird can fly
```

Child breaks that promise.

Hence:

❌ LSP violation

---

# Better Design

---

```java
interface Bird {
    void eat();
}
```

---

```java
interface FlyingBird {
    void fly();
}
```

---

```java
class Sparrow implements Bird, FlyingBird {

    public void eat() {
        System.out.println("Eating");
    }

    public void fly() {
        System.out.println("Flying");
    }
}
```

---

```java
class Ostrich implements Bird {

    public void eat() {
        System.out.println("Eating");
    }
}
```

Now no incorrect inheritance.

---

# Real World Example

---

# BAD Example

```java
class Vehicle {
    void startEngine() {
    }
}
```

---

```java
class Bicycle extends Vehicle {

    @Override
    void startEngine() {
        throw new RuntimeException("No engine");
    }
}
```

Bad inheritance.

---

# Better

```java
interface Vehicle {
    void move();
}
```

---

```java
interface EngineVehicle {
    void startEngine();
}
```

---

```java
class Car implements Vehicle, EngineVehicle {
}
```

---

```java
class Bicycle implements Vehicle {
}
```

Correct design.

---

# Rules for Following LSP

A child class should:

---

## 1. Preserve Parent Behavior

Do not change expected behavior.

---

## 2. Not Throw New Exceptions

Bad:

```java
parent.save();

child.save() throws exception
```

Unexpected behavior.

---

## 3. Not Remove Functionality

If parent supports something,
child should also support it.

---

## 4. Keep Same Meaning

Bad:

```java
withdraw()
```

in parent means money deduction

child changes it to:

* logging
* notification only

Wrong meaning.

---

## 5. Maintain Parent Expectations

If parent says:

```text
setWidth changes only width
```

child should not modify height too.

---

# Easy Interview Definition

> LSP says subclasses should be replaceable for parent classes without changing expected program behavior.

---

# Relationship with OCP

LSP strongly supports:

* Open Closed Principle

Because if child classes behave correctly:

* we can extend safely
* without modifying existing code

---

# LSP vs Inheritance

LSP teaches:

```text
Do not inherit just for code reuse
```

Inheritance should represent:

* compatible behavior
* true substitutability

---

# When NOT to Use Inheritance

Avoid inheritance when:

* behaviors differ
* rules differ
* constraints differ
* child disables parent methods
* child throws unsupported exceptions
* client must check child type

Use:

* interfaces
* composition
* delegation

instead.

---

# Composition Over Inheritance

Instead of:

```java
Square extends Rectangle
```

Use:

```java
interface Shape {
    int getArea();
}
```

Then:

```java
class Rectangle implements Shape
class Square implements Shape
```

No behavior conflict.

---

# Bad vs Good Design

---

# BAD

```text
Rectangle
   ↑
Square
```

Different behavior.

---

# GOOD

```text
        Shape
       /     \
 Rectangle   Square
```

Independent implementations.

---

# Real Benefit of LSP

Following LSP gives:

✅ flexible code
✅ reusable code
✅ maintainable systems
✅ easier testing
✅ safer inheritance
✅ predictable behavior
✅ fewer runtime bugs

---

# Problems When LSP Violated

Violating LSP causes:

❌ fragile systems
❌ runtime errors
❌ unexpected behavior
❌ too many condition checks
❌ broken polymorphism
❌ difficult maintenance

---

# How to Detect LSP Violation

Ask:

---

## Question 1

Can child fully replace parent?

---

## Question 2

Will existing client code still work correctly?

---

## Question 3

Does child preserve parent behavior?

---

## Question 4

Does child throw unsupported exceptions?

---

## Question 5

Do we need:

```java
if(obj instanceof Child)
```

Then design is probably wrong.

---

# Best Practice

Prefer:

```text
Interfaces + Composition
```

over deep inheritance hierarchies.

---

# One-Line Summary

> LSP ensures that subclasses behave correctly and can safely replace their parent classes without breaking application behavior.

---

# Final Important Understanding

LSP is mainly about:

```text
Behavior consistency
```

NOT:

* code reuse
* mathematical relationships
* hierarchy names

---

# Quick Revision Notes

---

## LSP Says

Child should replace parent safely.

---

## Violated When

* behavior changes
* exceptions added
* functionality removed
* contract broken

---

## Classic Example

```text
Square extends Rectangle
```

---

## Best Solution

Use:

* interfaces
* composition
* proper abstraction

---

# Final Interview Line

> LSP is violated when a subclass cannot behave exactly like its parent without breaking existing code expectations.
