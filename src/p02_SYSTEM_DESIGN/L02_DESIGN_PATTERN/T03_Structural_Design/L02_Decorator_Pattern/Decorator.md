Decorator Pattern is one of the most important structural design patterns.
Here are complete detailed notes including:

* theory
* object flow
* memory understanding
* `super()` explanation
* wrapping mechanism
* advantages/disadvantages
* real-world examples
* beginner confusion clarification

---

# Decorator Design Pattern — Complete Notes

# 1. What is Decorator Pattern?

Decorator Pattern is a Structural Design Pattern used to:

> Dynamically add extra behavior/features to an object without modifying its original class.

It wraps an existing object inside another object that adds new functionality.

---

# 2. Real World Example

Suppose we have coffee.

Base coffee:

* Simple Coffee → ₹100

Extra add-ons:

* Milk → +₹20
* Sugar → +₹10
* Cream → +₹30

Instead of creating many subclasses:

* MilkCoffee
* SugarCoffee
* MilkSugarCoffee
* MilkCreamCoffee
* MilkSugarCreamCoffee

Decorator Pattern allows dynamic wrapping:

```text
SimpleCoffee
    -> MilkDecorator
        -> SugarDecorator
            -> CreamDecorator
```

Each wrapper adds extra behavior.

---

# 3. Problem Without Decorator Pattern

## Bad Design Using Inheritance

```java
class Coffee {

    public int cost() {
        return 100;
    }
}

class MilkCoffee extends Coffee {

    public int cost() {
        return 120;
    }
}

class SugarCoffee extends Coffee {

    public int cost() {
        return 110;
    }
}

class MilkSugarCoffee extends Coffee {

    public int cost() {
        return 130;
    }
}
```

---

# 4. Problems With This Approach

## 1. Class Explosion

Too many combinations:

```text
MilkCoffee
SugarCoffee
MilkSugarCoffee
MilkCreamCoffee
MilkSugarCreamCoffee
...
```

---

## 2. Hard Maintenance

Adding a new topping creates many new classes.

---

## 3. Violates Open Closed Principle

Need to constantly modify system.

---

# 5. Decorator Pattern Solution

Instead of creating subclasses:

* Wrap objects dynamically.
* Add behavior at runtime.

---

# 6. Structure of Decorator Pattern

Decorator Pattern has four parts:

| Component            | Purpose         |
| -------------------- | --------------- |
| Component Interface  | Common contract |
| Concrete Component   | Original object |
| Decorator Base Class | Wrapper base    |
| Concrete Decorators  | Extra features  |

---

# 7. Complete Code

---

# Step 1 — Component Interface

```java
interface Coffee {

    String getDescription();

    int cost();
}
```

This is common contract for all coffee objects.

---

# Step 2 — Concrete Component

```java
class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public int cost() {
        return 100;
    }
}
```

This is base object.

---

# Step 3 — Decorator Base Class

```java
abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {

        System.out.println(
                "CoffeeDecorator constructor call"
        );

        this.coffee = coffee;
    }
}
```

VERY IMPORTANT:

```java
this.coffee = coffee;
```

This stores reference to wrapped object.

Decorator does NOT replace object.

It stores reference to another object.

---

# Step 4 — Concrete Decorators

## Milk Decorator

```java
class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {

        super(coffee);
    }

    @Override
    public String getDescription() {

        return coffee.getDescription() + ", Milk";
    }

    @Override
    public int cost() {

        return coffee.cost() + 20;
    }
}
```

---

## Sugar Decorator

```java
class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {

        super(coffee);
    }

    @Override
    public String getDescription() {

        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public int cost() {

        return coffee.cost() + 10;
    }
}
```

---

# Step 5 — Client Code

```java
public class Main {

    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee);

        coffee = new SugarDecorator(coffee);

        System.out.println(
                coffee.getDescription()
        );

        System.out.println(
                coffee.cost()
        );
    }
}
```

---

# 8. Output

```text
Simple Coffee, Milk, Sugar
130
```

---

# 9. MOST IMPORTANT CONCEPT

Decorator Pattern works because:

```text
Decorator object stores reference
to another object of same interface type.
```

---

# 10. Memory Understanding

This is the MOST IMPORTANT PART.

---

# STEP 1

```java
Coffee coffee = new SimpleCoffee();
```

Suppose memory:

```text
100A -> SimpleCoffee Object
```

Variable:

```text
coffee = 100A
```

IMPORTANT:

Variable stores reference/address.

Not actual object.

---

# STEP 2

```java
coffee = new MilkDecorator(coffee);
```

Current value:

```text
coffee = 100A
```

So constructor becomes:

```java
new MilkDecorator(100A)
```

---

# STEP 3

Java creates MilkDecorator object.

Suppose address:

```text
200B -> MilkDecorator Object
```

Object structure:

```text
MilkDecorator Object
--------------------------

coffee = null

methods:
    cost()
    getDescription()

--------------------------
```

---

# STEP 4

Constructor Executes

```java
public MilkDecorator(Coffee coffee){

    super(coffee);
}
```

Parameter:

```text
coffee = 100A
```

---

# STEP 5

Parent constructor runs:

```java
public CoffeeDecorator(Coffee coffee){

    this.coffee = coffee;
}
```

Assignment:

```text
this.coffee = 100A
```

---

# FINAL MEMORY

```text
200B -> MilkDecorator Object
--------------------------------

coffee ----> 100A

--------------------------------


100A -> SimpleCoffee Object
```

Decorator still exists.

It only STORES reference.

Nothing gets replaced.

---

# 11. Biggest Beginner Confusion

Many beginners think:

```java
this.coffee = coffee;
```

means:

```text
Decorator becomes SimpleCoffee
```

WRONG.

Actually:

```text
Decorator object contains a field
which stores reference to another object.
```

Just like:

```java
Person friend;
```

does not replace Person object.

It only stores another object reference.

---

# 12. How Method Calling Works

Suppose:

```java
coffee.cost();
```

Current object:

```text
MilkDecorator
```

So Java executes:

```java
return coffee.cost() + 20;
```

Here:

* `coffee` field points to SimpleCoffee.

So:

```text
SimpleCoffee.cost()
```

returns:

```text
100
```

Then decorator adds:

```text
100 + 20
```

returns:

```text
120
```

---

# 13. Multiple Decorators

Suppose:

```java
coffee = new SugarDecorator(coffee);
```

Now memory:

```text
SugarDecorator
      |
      v
MilkDecorator
      |
      v
SimpleCoffee
```

Calling:

```java
coffee.cost();
```

Execution flow:

```text
SugarDecorator.cost()

    -> MilkDecorator.cost()

            -> SimpleCoffee.cost()
                    = 100

            +20
            = 120

    +10
    = 130
```

---

# 14. Understanding super(coffee)

Suppose:

```java
public MilkDecorator(Coffee coffee){

    super(coffee);
}
```

This means:

```text
Call parent constructor
CoffeeDecorator(Coffee coffee)
```

which executes:

```java
this.coffee = coffee;
```

IMPORTANT:

`super()` does NOT:

* add cost
* add description
* create recursion

It ONLY initializes parent fields.

---

# 15. Why Use Parent Decorator Class?

Without parent class:

Every decorator repeats:

```java
private Coffee coffee;

this.coffee = coffee;
```

again and again.

So common logic moved into:

```java
CoffeeDecorator
```

This is code reuse.

---

# 16. Simpler Beginner-Friendly Version

Decorator can also be written WITHOUT inheritance.

```java
class MilkDecorator implements Coffee {

    private Coffee coffee;

    public MilkDecorator(Coffee coffee){

        this.coffee = coffee;
    }

    @Override
    public int cost(){

        return coffee.cost() + 20;
    }
}
```

This version is easier to understand initially.

Later parent decorator class is introduced for reuse.

---

# 17. Why Same Interface Matters

Decorator:

* implements Coffee
* stores Coffee

This allows:

```text
Decorator inside Decorator inside Decorator
```

Because every object is still a Coffee.

---

# 18. Key Principle

Decorator Pattern follows:

## Open Closed Principle

Open for:

* extension

Closed for:

* modification

New features added without changing old classes.

---

# 19. Real World Examples

## Java I/O Streams

```java
FileInputStream file =
        new FileInputStream("data.txt");

BufferedInputStream buffered =
        new BufferedInputStream(file);

DataInputStream data =
        new DataInputStream(buffered);
```

Each stream wraps another stream.

Decorator Pattern.

---

## Spring Framework

Used in:

* request wrappers
* response wrappers
* security filters

---

## Logging Systems

Decorators can add:

* timestamps
* encryption
* formatting

---

# 20. Difference Between Adapter and Decorator

| Adapter              | Decorator               |
| -------------------- | ----------------------- |
| Changes interface    | Keeps same interface    |
| Solves compatibility | Adds behavior           |
| Usually wraps once   | Can wrap multiple times |
| Focus = conversion   | Focus = enhancement     |

---

# 21. Advantages

## 1. Dynamic Feature Addition

Features added runtime.

---

## 2. Avoids Class Explosion

No need for many subclasses.

---

## 3. Flexible Combination

Any combination possible.

---

## 4. Follows Open Closed Principle

Existing code unchanged.

---

# 22. Disadvantages

## 1. Deep Object Chains

Too many decorators:

```text
A -> B -> C -> D -> E
```

Hard to debug.

---

## 2. More Objects Created

Each decorator creates new object.

---

## 3. Order Matters

```text
Milk + Sugar
```

may behave differently from:

```text
Sugar + Milk
```

depending on implementation.

---

# 23. Core Mental Model

Decorator Pattern is simply:

```text
Object wrapping another object
using reference storage
and forwarding method calls.
```

---

# 24. Final One-Line Definition

> Decorator Pattern dynamically adds new behavior to objects by wrapping them inside other objects implementing the same interface.
