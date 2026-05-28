# Strategy Design Pattern — Complete Notes

# 1. What is Strategy Pattern?

The **Strategy Pattern** is a **Behavioral Design Pattern** that allows us to:

> Define multiple algorithms/behaviors separately and switch between them at runtime.

---

# Simple Definition

> Strategy Pattern encapsulates different behaviors into separate classes and allows dynamic behavior selection.

---

# Main Idea

Instead of writing:

```java id="r6rfwy"
if(type == "CreditCard")
else if(type == "UPI")
else if(type == "PayPal")
```

we create:

* separate strategy classes
* interchangeable behaviors

---

# Real-Life Analogy

# Google Maps Route Selection

Suppose Google Maps provides:

* Car Route
* Bike Route
* Walking Route
* Metro Route

Same destination.

Different algorithms.

Google Maps dynamically chooses strategy.

---

# Here

| Real World      | Strategy Pattern  |
| --------------- | ----------------- |
| Google Maps     | Context           |
| Car Route       | Concrete Strategy |
| Walking Route   | Concrete Strategy |
| Route Algorithm | Strategy          |

---

# Another Real-Life Examples

* Payment methods
* Sorting algorithms
* Compression algorithms
* Authentication methods
* Notification sending
* Tax calculation
* Discount systems

---

# Why We Need Strategy Pattern

Suppose we build payment system.

---

# BAD DESIGN (Without Strategy Pattern)

```java id="6d0ojj"
class PaymentService {

    public void pay(String type) {

        if(type.equals("CARD")) {

            System.out.println("Paid using card");

        } else if(type.equals("UPI")) {

            System.out.println("Paid using UPI");

        } else if(type.equals("PAYPAL")) {

            System.out.println("Paid using PayPal");
        }
    }
}
```

---

# Problems

---

# 1. Violates Open Closed Principle

Adding new payment requires modifying class.

---

# 2. Large if-else Chains

Code becomes ugly.

---

# 3. Difficult Maintenance

Business logic mixed together.

---

# 4. Difficult Testing

Cannot test algorithms independently.

---

# 5. Tight Coupling

PaymentService tightly coupled with all payment types.

---

# Solution → Strategy Pattern

We separate each behavior into independent classes.

---

# Structure of Strategy Pattern

---

# 1. Strategy Interface

Common behavior contract.

---

# 2. Concrete Strategies

Different implementations.

---

# 3. Context

Uses strategy dynamically.

---

# UML Structure

```text id="49e3k5"
+-------------------+
|     Context       |
| PaymentService    |
+-------------------+
| Strategy strategy |
+-------------------+
         |
         v

+-------------------+
|     Strategy      |
+-------------------+
| execute()         |
+-------------------+
      /     \
     /       \
    v         v

+-------------+   +-------------+
| CardPayment |   | UPIPayment  |
+-------------+   +-------------+
```

---

# Step-by-Step Implementation

---

# Step 1: Create Strategy Interface

```java id="q2nn2g"
interface PaymentStrategy {

    void pay(int amount);
}
```

---

# Why Interface?

Because context should depend on abstraction, not concrete classes.

---

# Step 2: Concrete Strategies

---

# Credit Card Payment

```java id="m1otyr"
class CardPayment implements PaymentStrategy {

    @Override
    public void pay(int amount) {

        System.out.println(
            "Paid " + amount + " using Card"
        );
    }
}
```

---

# UPI Payment

```java id="j9i07s"
class UPIPayment implements PaymentStrategy {

    @Override
    public void pay(int amount) {

        System.out.println(
            "Paid " + amount + " using UPI"
        );
    }
}
```

---

# PayPal Payment

```java id="r98g8t"
class PayPalPayment implements PaymentStrategy {

    @Override
    public void pay(int amount) {

        System.out.println(
            "Paid " + amount + " using PayPal"
        );
    }
}
```

---

# Step 3: Context Class

```java id="cvv91r"
class PaymentService {

    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void makePayment(int amount) {

        strategy.pay(amount);
    }
}
```

---

# Important Point

PaymentService does NOT know:

* CardPayment
* UPIPayment
* PayPalPayment

It only knows:

```java id="dhj9mn"
PaymentStrategy
```

This creates loose coupling.

---

# Step 4: Main Class

```java id="5s0vji"
public class Main {

    public static void main(String[] args) {

        PaymentService service = new PaymentService();

        service.setStrategy(new CardPayment());
        service.makePayment(1000);

        service.setStrategy(new UPIPayment());
        service.makePayment(500);

        service.setStrategy(new PayPalPayment());
        service.makePayment(2000);
    }
}
```

---

# Output

```text id="8y7xal"
Paid 1000 using Card
Paid 500 using UPI
Paid 2000 using PayPal
```

---

# Internal Flow

---

# Step 1

Context receives strategy.

```java id="3j8mjc"
service.setStrategy(new UPIPayment());
```

---

# Step 2

Context delegates behavior.

```java id="y4mnxz"
strategy.pay(amount);
```

---

# Step 3

Concrete strategy executes algorithm.

---

# Runtime Behavior Change

MOST IMPORTANT FEATURE.

Behavior can change dynamically:

```java id="3k9l6m"
service.setStrategy(new CardPayment());

service.setStrategy(new UPIPayment());
```

WITHOUT modifying context.

---

# Another Example — Sorting Strategy

---

# BAD DESIGN

```java id="mnh82l"
if(size < 100)
    bubbleSort();
else
    quickSort();
```

---

# Strategy Pattern

```text id="04xkjo"
BubbleSortStrategy
QuickSortStrategy
MergeSortStrategy
```

Runtime selection.

---

# Another Example — Navigation App

Strategies:

* CarRoute
* BikeRoute
* WalkingRoute

Context:

* NavigationSystem

---

# Another Example — Compression

Strategies:

* ZIPCompression
* RARCompression
* GZIPCompression

---

# Coupling Discussion

---

# BAD Coupling

```text id="we2h4n"
PaymentService -> CardPayment
PaymentService -> UPI
PaymentService -> PayPal
```

---

# GOOD Coupling

```text id="n0k75f"
PaymentService -> PaymentStrategy
```

Loose coupling.

---

# SOLID Principles

---

# 1. Open Closed Principle

New strategies added without modifying existing code.

---

# 2. Single Responsibility Principle

Each strategy handles only one algorithm.

---

# 3. Dependency Inversion Principle

Context depends on abstraction.

---

# Advantages

---

# 1. Eliminates if-else Chains

Cleaner code.

---

# 2. Runtime Behavior Change

Dynamic strategy switching.

---

# 3. Loose Coupling

Context independent of concrete strategies.

---

# 4. Easy Extension

Add new strategies easily.

---

# 5. Better Testing

Strategies tested independently.

---

# 6. Reusable Algorithms

Strategies reusable across systems.

---

# Disadvantages

---

# 1. Too Many Classes

Every strategy becomes separate class.

---

# 2. Client Must Know Strategies

Client chooses which strategy to use.

---

# 3. Slightly More Complex

For small systems may feel unnecessary.

---

# Strategy vs State Pattern

VERY IMPORTANT INTERVIEW QUESTION.

---

# Strategy Pattern

Behavior chosen by client.

Example:

* payment method chosen manually

---

# State Pattern

Behavior changes automatically based on internal state.

Example:

* vending machine states

---

# Difference Table

| Feature         | Strategy                 | State              |
| --------------- | ------------------------ | ------------------ |
| Behavior change | Manual/client            | Automatic/internal |
| Purpose         | Select algorithm         | Represent state    |
| Focus           | Interchangeable behavior | State transitions  |
| Example         | Payment methods          | ATM states         |

---

# Strategy vs Template Method

| Strategy                 | Template Method           |
| ------------------------ | ------------------------- |
| Uses composition         | Uses inheritance          |
| Runtime flexibility      | Compile-time behavior     |
| Behavior interchangeable | Fixed algorithm structure |

---

# Composition Over Inheritance

Strategy pattern is famous for:

# "Favor Composition Over Inheritance"

Instead of:

```java id="i9aq8z"
class UPIPayment extends PaymentService
```

we use:

```java id="vr6m6q"
PaymentService HAS-A PaymentStrategy
```

Composition is more flexible.

---

# Real-World Framework Examples

---

# 1. Java Comparator

```java id="4pxa9j"
Collections.sort(list, comparator)
```

Comparator is strategy.

---

# 2. Spring Security

Different authentication strategies.

---

# 3. Payment Gateways

Different payment providers.

---

# 4. Logging Frameworks

Different logging strategies.

---

# Lambda + Strategy Pattern

Modern Java often uses lambdas instead of concrete classes.

---

# Example

```java id="fygl5s"
PaymentStrategy upi =
    amount -> System.out.println("UPI " + amount);
```

Because strategy interface usually has:

* single method

making it functional interface.

---

# Interview Questions

---

# Q1. What problem does Strategy solve?

It removes large conditional logic and allows interchangeable behaviors.

---

# Q2. Why is Strategy loosely coupled?

Because context depends only on strategy abstraction.

---

# Q3. Biggest advantage?

Runtime behavior change.

---

# Q4. Biggest disadvantage?

Too many strategy classes.

---

# Q5. Why composition preferred here?

Because behavior can change dynamically.

---

# Q6. Real-world examples?

* payment methods
* sorting
* navigation
* compression

---

# Best Use Cases

Use Strategy Pattern when:

✅ Multiple algorithms exist
✅ Runtime behavior switching needed
✅ Large if-else chains exist
✅ Want loose coupling
✅ Algorithms should vary independently

---

# When NOT To Use

Avoid when:

* only one algorithm exists
* behavior never changes
* system very small

---

# Final Interview Summary

> Strategy Pattern is a behavioral design pattern that defines a family of algorithms, encapsulates each algorithm separately, and makes them interchangeable at runtime. It promotes loose coupling, follows composition over inheritance, and removes large conditional logic. It is widely used in payment systems, sorting algorithms, routing systems, authentication mechanisms, and compression utilities.
