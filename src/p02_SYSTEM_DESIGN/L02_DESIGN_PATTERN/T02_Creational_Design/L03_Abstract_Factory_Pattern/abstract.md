# Abstract Factory Design Pattern

The **Abstract Factory Pattern** is a **Creational Design Pattern**.

It is used when:

> We need to create families of related objects together.

Instead of creating one object,
Abstract Factory creates:

✅ Multiple related objects
✅ Matching object families
✅ Compatible products

---

# Simple Real-Life Example

Think about:

# Furniture Store

Suppose store sells:

* Modern Furniture
* Victorian Furniture

Each style contains:

| Modern Family | Victorian Family |
| ------------- | ---------------- |
| Modern Chair  | Victorian Chair  |
| Modern Sofa   | Victorian Sofa   |
| Modern Table  | Victorian Table  |

Now if customer chooses:

```text id="n7t6uq"
Modern Style
```

all products should be modern.

Not:

```text id="yjlwmu"
Modern Chair + Victorian Sofa
```

That would look weird.

So we need:

✅ A factory that creates complete matching families.

This is Abstract Factory Pattern.

---

# Main Idea

Factory Pattern creates:

```text id="3i1i5d"
ONE product
```

Abstract Factory creates:

```text id="o0ahk8"
FAMILY OF RELATED PRODUCTS
```

---

# Main Problem Without Abstract Factory

Suppose we are creating:

# Cross Platform UI System

For:

* Windows
* Mac

Products:

* Button
* Checkbox

---

# ❌ BAD CODE

```java id="4vwdzw"
class WindowsButton {
}

class MacButton {
}

class WindowsCheckbox {
}

class MacCheckbox {
}
```

Main:

```java id="jdnw32"
WindowsButton button =
        new WindowsButton();

MacCheckbox checkbox =
        new MacCheckbox();
```

---

# Problem

UI becomes inconsistent.

```text id="4xh6v0"
Windows Button + Mac Checkbox
```

Bad design.

---

# Why Abstract Factory?

We want:

✅ Consistent product families
✅ Centralized creation
✅ Loose coupling
✅ Easy platform switching

---

# Core Idea

Instead of:

```java id="cxjlwm"
new WindowsButton()
new MacCheckbox()
```

we use:

```java id="qf4jvr"
GUIFactory
```

which creates matching products.

---

# Structure of Abstract Factory

---

# 1. Abstract Products

Interfaces for product types.

Example:

* Button
* Checkbox

---

# 2. Concrete Products

Platform-specific implementations.

Example:

* WindowsButton
* MacButton

---

# 3. Abstract Factory Interface

Defines methods for creating products.

---

# 4. Concrete Factories

Actual factories:

* WindowsFactory
* MacFactory

---

# Step-by-Step Implementation

---

# Step 1: Create Product Interfaces

---

# Button Interface

```java id="7fdc87"
interface Button {

    void render();
}
```

---

# Checkbox Interface

```java id="o1y5t8"
interface Checkbox {

    void check();
}
```

---

# Step 2: Create Concrete Products

---

# Windows Products

```java id="6fd0gm"
class WindowsButton implements Button {

    @Override
    public void render() {

        System.out.println("Windows Button");
    }
}
```

```java id="r5m1q8"
class WindowsCheckbox implements Checkbox {

    @Override
    public void check() {

        System.out.println("Windows Checkbox");
    }
}
```

---

# Mac Products

```java id="u8jlwm"
class MacButton implements Button {

    @Override
    public void render() {

        System.out.println("Mac Button");
    }
}
```

```java id="yqg6s5"
class MacCheckbox implements Checkbox {

    @Override
    public void check() {

        System.out.println("Mac Checkbox");
    }
}
```

---

# Step 3: Create Abstract Factory

```java id="ndtwsp"
interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
```

---

# Step 4: Create Concrete Factories

---

# Windows Factory

```java id="3kph6n"
class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {

        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {

        return new WindowsCheckbox();
    }
}
```

---

# Mac Factory

```java id="2rbmry"
class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {

        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {

        return new MacCheckbox();
    }
}
```

---

# Step 5: Client Code

```java id="6c78b2"
public class Main {

    public static void main(String[] args) {

        GUIFactory factory;

        String os = "WINDOWS";

        if(os.equals("WINDOWS")) {

            factory = new WindowsFactory();
        }
        else {

            factory = new MacFactory();
        }

        Button button =
                factory.createButton();

        Checkbox checkbox =
                factory.createCheckbox();

        button.render();

        checkbox.check();
    }
}
```

---

# Output

```text id="zaj2q6"
Windows Button
Windows Checkbox
```

---

# Important Understanding

Client does NOT know:

```java id="w7jlwm"
new WindowsButton()
```

or:

```java id="l6r9m8"
new MacCheckbox()
```

Factory handles everything.

---

# Most Important Point

Factory ensures:

```text id="9gg5q7"
Matching product families
```

If using WindowsFactory:

✅ WindowsButton
✅ WindowsCheckbox

No mismatch.

---

# Visual Flow

```text id="bgjlwm"
             GUIFactory
               /    \
              /      \
     WindowsFactory  MacFactory
         /    \          /    \
        /      \        /      \
WindowsButton WindowsCheckbox
MacButton     MacCheckbox
```

---

# Why "Abstract" Factory?

Because:

```java id="r2jlwm"
interface GUIFactory
```

is abstract.

It defines product creation methods,
but actual factories implement them.

---

# Difference Between Factory and Abstract Factory

This is VERY important.

---

# Factory Pattern

Creates:

```text id="zjlwmm"
ONE product
```

Example:

```java id="y0lw7q"
NotificationFactory
```

creates:

* Email
* SMS

---

# Abstract Factory Pattern

Creates:

```text id="04hjlwm"
FAMILY OF RELATED PRODUCTS
```

Example:

```java id="97f0j2"
WindowsFactory
```

creates:

* WindowsButton
* WindowsCheckbox

---

# Comparison Table

| Factory Pattern        | Abstract Factory Pattern     |
| ---------------------- | ---------------------------- |
| Creates single product | Creates families of products |
| One factory method     | Multiple factory methods     |
| Simpler                | More complex                 |
| Example: Notification  | Example: GUI Toolkit         |

---

# Real-World Examples

---

# 1. Java GUI Libraries

Swing/AWT create platform-specific components.

---

# 2. Database Drivers

Factory creates related DB objects.

---

# 3. Theme Systems

Dark Theme:

* dark button
* dark menu
* dark scrollbar

Light Theme:

* light button
* light menu
* light scrollbar

---

# 4. Cloud Providers

AWS Factory creates:

* AWS Storage
* AWS Queue
* AWS Compute

Azure Factory creates:

* Azure Storage
* Azure Queue
* Azure Compute

---

# When to Use Abstract Factory

Use when:

✅ Multiple related objects needed
✅ Product families must match
✅ Platform/theme dependent objects
✅ Want loose coupling

---

# When NOT to Use

Do not use if:

❌ Only one product type
❌ Product families unnecessary
❌ Too much complexity for small project

---

# Advantages

---

# 1. Ensures Product Compatibility

Matching families guaranteed.

---

# 2. Loose Coupling

Client depends only on interfaces.

---

# 3. Easy Product Family Switching

Switch entire product family easily.

---

# 4. Centralized Creation Logic

Cleaner code.

---

# 5. Follows Open Closed Principle

Add new families easily.

---

# Disadvantages

---

# 1. More Complex

Many interfaces/classes.

---

# 2. Difficult to Add New Product Types

Suppose adding:

```text id="8jlwm2"
Slider
```

Must update:

* abstract factory
* all concrete factories

---

# Internal Architecture

```text id="8w1yqn"
             Abstract Factory
               /         \
              /           \
    WindowsFactory     MacFactory
         |                  |
   ----------------   ----------------
   |              |   |              |
Button         Checkbox
```

---

# Important Interview Point

## Main Goal

Create families of related objects without exposing concrete classes.

---

# Quick Revision Notes

## Category

Creational Pattern

---

## Purpose

Create related product families.

---

## Key Components

* Abstract Products
* Concrete Products
* Abstract Factory
* Concrete Factories

---

## Main Benefit

Ensures compatible product families.

---

# One-Line Definition

> Abstract Factory Pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes.
