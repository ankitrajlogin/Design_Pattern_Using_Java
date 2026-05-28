# Template Method Design Pattern

The **Template Method Pattern** is a **Behavioral Design Pattern**.

It defines:

> **the skeleton (fixed steps) of an algorithm in a parent class**,
> while allowing child classes to change specific steps without changing the overall flow.

---

# Real Life Example

Imagine making tea and coffee.

Common steps:

1. Boil water
2. Add main ingredient
3. Pour into cup
4. Add extras

But:

* Tea adds tea leaves
* Coffee adds coffee powder

The overall process is same.
Only some steps change.

That is exactly Template Method Pattern.

---

# Problem Without Template Method (Bad Code)

Suppose we are creating a data parser system.

We have:

* CSV Parser
* JSON Parser

Both follow same flow:

1. Open file
2. Read data
3. Parse data
4. Close file

---

# ❌ Bad Code (Code Duplication)

```java
class CSVParser {

    public void parseCSV() {

        System.out.println("Opening CSV file");

        System.out.println("Reading CSV data");

        System.out.println("Parsing CSV data");

        System.out.println("Closing CSV file");
    }
}

class JSONParser {

    public void parseJSON() {

        System.out.println("Opening JSON file");

        System.out.println("Reading JSON data");

        System.out.println("Parsing JSON data");

        System.out.println("Closing JSON file");
    }
}
```

---

# Problems in Bad Code

## 1. Code Duplication

Opening and closing logic repeated.

---

## 2. Hard to Maintain

If tomorrow:

```java
System.out.println("Validate file");
```

needs to be added before parsing,

you must update every class.

---

## 3. Inconsistent Flow

One developer may forget a step.

Example:

```java
class XMLParser {
    // forgot to close file
}
```

Now bugs happen.

---

# Solution → Template Method Pattern

We move common workflow into parent class.

Child classes only implement varying steps.

---

# ✅ Good Code Using Template Method

---

# Step 1: Create Abstract Parent Class

```java
abstract class DataParser {

    // TEMPLATE METHOD
    public final void parseData() {

        openFile();

        readData();

        parse();

        closeFile();
    }

    private void openFile() {
        System.out.println("Opening file");
    }

    private void readData() {
        System.out.println("Reading data");
    }

    private void closeFile() {
        System.out.println("Closing file");
    }

    // changing step
    protected abstract void parse();
}
```

---

# Step 2: CSV Parser

```java
class CSVParser extends DataParser {

    @Override
    protected void parse() {
        System.out.println("Parsing CSV data");
    }
}
```

---

# Step 3: JSON Parser

```java
class JSONParser extends DataParser {

    @Override
    protected void parse() {
        System.out.println("Parsing JSON data");
    }
}
```

---

# Step 4: Main Class

```java
public class Main {

    public static void main(String[] args) {

        DataParser csv = new CSVParser();
        csv.parseData();

        System.out.println();

        DataParser json = new JSONParser();
        json.parseData();
    }
}
```

---

# Output

```text
Opening file
Reading data
Parsing CSV data
Closing file

Opening file
Reading data
Parsing JSON data
Closing file
```

---

# Important Observation

This method:

```java
public final void parseData()
```

is called the:

# TEMPLATE METHOD

It defines the complete algorithm flow.

---

# Why `final`?

```java
public final void parseData()
```

Because we do NOT want child classes to change workflow order.

Otherwise child class may do:

```java
closeFile();
openFile();
```

which breaks logic.

So:

* structure remains fixed
* only specific steps customizable

---

# Structure of Template Method Pattern

```text
AbstractClass
    templateMethod()
        step1()
        step2()
        step3()

ConcreteClassA
    implement step2()

ConcreteClassB
    implement step2()
```

---

# Core Idea

## Parent Class Controls:

* order of execution
* common logic
* workflow

## Child Class Controls:

* customizable steps
* varying behavior

---

# Another Example (Very Common)

## Online Payment Flow

Common Steps:

1. Validate user
2. Process payment
3. Send receipt

But:

* UPI payment differs
* Card payment differs
* PayPal differs

---

# Template Method Example

```java
abstract class PaymentProcessor {

    public final void pay() {

        validateUser();

        processPayment();

        sendReceipt();
    }

    private void validateUser() {
        System.out.println("User validated");
    }

    private void sendReceipt() {
        System.out.println("Receipt sent");
    }

    protected abstract void processPayment();
}
```

---

# UPI Payment

```java
class UPIPayment extends PaymentProcessor {

    @Override
    protected void processPayment() {
        System.out.println("UPI payment processed");
    }
}
```

---

# Card Payment

```java
class CardPayment extends PaymentProcessor {

    @Override
    protected void processPayment() {
        System.out.println("Card payment processed");
    }
}
```

---

# Hooks in Template Method

Sometimes parent provides optional methods.

Example:

```java
protected void beforePayment() {
    // optional
}
```

Child may override or ignore.

These are called:

# HOOK METHODS

---

# Example

```java
abstract class Game {

    public final void play() {

        start();

        if (shouldPause()) {
            pause();
        }

        end();
    }

    protected abstract void start();

    protected abstract void end();

    protected boolean shouldPause() {
        return false;
    }

    protected void pause() {
        System.out.println("Game paused");
    }
}
```

---

# When To Use Template Method

Use when:

---

## 1. Multiple classes follow same workflow

Example:

* payment systems
* parsers
* report generation
* authentication flow

---

## 2. Only few steps differ

Most algorithm remains same.

---

## 3. You want to enforce order

Example:

```text
connect → query → close
```

must always happen in same order.

---

## 4. Avoid Duplicate Code

Common logic stays in parent class.

---

# When NOT To Use

Do NOT use when:

---

## 1. Flow changes completely

If every subclass has different flow,
Template Method becomes useless.

---

## 2. Too many optional conditions

Then inheritance becomes messy.

Use:

* Strategy Pattern
* Composition

instead.

---

# Template Method vs Strategy Pattern

| Feature              | Template Method | Strategy         |
| -------------------- | --------------- | ---------------- |
| Based on             | Inheritance     | Composition      |
| Algorithm flow       | Fixed           | Can fully change |
| Runtime flexibility  | Less            | High             |
| Code reuse           | High            | Medium           |
| Parent controls flow | Yes             | No               |

---

# Example Difference

## Template Method

```text
Tea Making
Boil → Add Ingredient → Pour
```

Order fixed.

---

## Strategy

```text
Sorting Algorithm
BubbleSort / MergeSort / QuickSort
```

Entire algorithm replaceable.

---

# Advantages

---

## ✅ Code Reuse

Shared logic in parent.

---

## ✅ Consistent Workflow

Flow cannot break.

---

## ✅ Easier Maintenance

Change common logic once.

---

## ✅ Cleaner Design

Separates common and changing behavior.

---

# Disadvantages

---

## ❌ Tight Coupling

Child depends heavily on parent.

---

## ❌ Inheritance Limitations

Java supports single inheritance only.

---

## ❌ Harder if Logic Changes Frequently

Too many subclasses may appear.

---

# Real World Examples in Java

---

## 1. Java IO Classes

Like:

* `InputStream`
* `OutputStream`

---

## 2. Servlet API

```java
doGet()
doPost()
```

inside fixed request lifecycle.

---

## 3. Spring Framework

Spring Boot lifecycle methods.

---

# Complete Understanding in One Line

> Template Method Pattern defines a fixed algorithm structure in parent class while allowing subclasses to customize specific steps.

---

# Easy Interview Definition

> “Template Method Pattern is a behavioral pattern where a parent class defines the overall workflow of an algorithm and subclasses override only the variable parts without changing the structure.”
