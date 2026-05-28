# Singleton Design Pattern

The **Singleton Pattern** is a **Creational Design Pattern**.

It ensures:

> Only ONE object of a class exists in the entire application.

and provides:

> A global access point to that object.

---

# Real-Life Example

Think about:

## Printer Spooler

In a computer system:

* Multiple applications want to print
* But printer control should be centralized

You should not create:

```text id="17pt6n"
10 Printer Managers
```

Otherwise:

* duplicate jobs
* inconsistent queue
* hardware conflicts

So system keeps:

✅ Only ONE Printer Manager object

This is Singleton Pattern.

---

# Main Goal of Singleton

Two things:

## 1. Restrict Object Creation

Only one object allowed.

---

## 2. Provide Global Access

Anyone can access same instance.

---

# Where Singleton is Used

Very common in:

* Database Connection Manager
* Logger
* Cache
* Configuration Manager
* Thread Pool
* Driver Objects
* Runtime Environment

---

# Problem Without Singleton

Suppose we create a Logger.

---

# ❌ BAD CODE

```java id="a5o9wp"
class Logger {

    public void log(String message) {

        System.out.println(message);
    }
}
```

Main:

```java id="w3f56j"
public class Main {

    public static void main(String[] args) {

        Logger l1 = new Logger();

        Logger l2 = new Logger();

        Logger l3 = new Logger();
    }
}
```

---

# Problems

---

# 1. Multiple Objects Created

```text id="4yyr9l"
l1 != l2 != l3
```

Different logger objects.

Waste of memory.

---

# 2. Shared Resources Become Inconsistent

Suppose logger maintains:

* file stream
* buffer
* configurations

Multiple objects create issues.

---

# 3. No Centralized Control

Any class can create unlimited objects.

Dangerous.

---

# Why Singleton Pattern?

We want:

✅ Only one object
✅ Controlled creation
✅ Global access

---

# Core Idea

Prevent:

```java id="9tp0du"
new Singleton()
```

from outside class.

---

# How?

We do:

✅ Constructor → private
✅ Static instance variable
✅ Static getter method

---

# Structure of Singleton

---

# 1. Private Constructor

Prevents external object creation.

---

# 2. Static Instance Variable

Stores single object.

---

# 3. Public Static Method

Provides access to object.

---

# Step-by-Step Implementation

---

# Step 1: Create Class

```java id="a9q8ep"
class Logger {
}
```

---

# Step 2: Make Constructor Private

```java id="l5n3sk"
private Logger() {
}
```

Now this is illegal:

```java id="m3d3tm"
new Logger();
```

outside class.

---

# Step 3: Create Static Instance Variable

```java id="x9f5t1"
private static Logger instance;
```

Static means:

Belongs to class itself.

Only one copy exists.

---

# Step 4: Create Public Getter Method

```java id="o1e6s4"
public static Logger getInstance() {

    if(instance == null) {

        instance = new Logger();
    }

    return instance;
}
```

---

# Complete Singleton Code

```java id="8n29d8"
class Logger {

    private static Logger instance;

    private Logger() {

        System.out.println("Logger Created");
    }

    public static Logger getInstance() {

        if(instance == null) {

            instance = new Logger();
        }

        return instance;
    }

    public void log(String message) {

        System.out.println(message);
    }
}
```

---

# Main Class

```java id="6s0d7o"
public class Main {

    public static void main(String[] args) {

        Logger l1 = Logger.getInstance();

        Logger l2 = Logger.getInstance();

        Logger l3 = Logger.getInstance();

        System.out.println(l1 == l2);

        System.out.println(l2 == l3);
    }
}
```

---

# Output

```text id="f1c6x9"
Logger Created

true

true
```

---

# Very Important Understanding

This line:

```java id="5osfkl"
Logger l1 = Logger.getInstance();
```

does NOT create new object every time.

It only returns same object reference.

---

# Memory Visualization

First call:

```java id="7t3myl"
Logger.getInstance()
```

creates:

```text id="yuokmu"
instance -----> Logger Object
```

Second call:

```java id="nsg06f"
Logger.getInstance()
```

returns SAME reference:

```text id="ieq5ie"
l1 ------\
          \
l2 --------> SAME Logger Object
          /
l3 ------/
```

---

# Important Interview Question

## Why Constructor Private?

To prevent:

```java id="bkmpru"
new Logger()
```

outside class.

Without private constructor,
Singleton fails.

---

# Lazy Initialization

This approach:

```java id="1s2bji"
if(instance == null)
```

creates object only when needed.

Called:

# Lazy Initialization

---

# Eager Initialization

Alternative:

```java id="n3s0q8"
private static Logger instance = new Logger();
```

Object created immediately when class loads.

---

# Lazy vs Eager

| Lazy                | Eager               |
| ------------------- | ------------------- |
| Created when needed | Created immediately |
| Saves memory        | Faster access       |
| Slightly complex    | Very simple         |

---

# Problem in Multithreading

This code is NOT thread-safe:

```java id="06bh7k"
if(instance == null)
```

Suppose:

* Thread 1 enters
* Thread 2 enters simultaneously

Both create object.

Singleton breaks.

---

# ❌ Problem Example

```text id="4ypp8u"
Thread1 -> instance == null
Thread2 -> instance == null
```

Both create objects.

Now:

```text id="h3ubd2"
2 Singleton objects
```

BAD.

---

# Thread-Safe Singleton

---

# Solution 1: synchronized Method

```java id="p2xoq0"
public static synchronized Logger getInstance() {

    if(instance == null) {

        instance = new Logger();
    }

    return instance;
}
```

---

# Problem

Safe but slower.

Because synchronized locks method every call.

---

# Best Approach → Double Checked Locking

```java id="xbrp8s"
class Logger {

    private static volatile Logger instance;

    private Logger() {}

    public static Logger getInstance() {

        if(instance == null) {

            synchronized (Logger.class) {

                if(instance == null) {

                    instance = new Logger();
                }
            }
        }

        return instance;
    }
}
```

---

# Why Double Check?

Avoid unnecessary synchronization after object creation.

Better performance.

---

# Why volatile?

Prevents memory inconsistency issues.

Very important in multithreading.

---

# Best Singleton in Java

Using Enum.

---

# Enum Singleton

```java id="6o4jzh"
enum Singleton {

    INSTANCE;

    public void show() {

        System.out.println("Singleton Working");
    }
}
```

Usage:

```java id="ehh85x"
Singleton.INSTANCE.show();
```

---

# Why Enum Singleton Best?

✅ Thread-safe
✅ Prevents reflection attack
✅ Prevents serialization issues
✅ Very simple

Joshua Bloch recommends this.

---

# Real-World Examples

---

# 1. Database Connection Pool

Only one pool manager needed.

---

# 2. Configuration Manager

Entire app shares same configuration.

---

# 3. Logger

Centralized logging system.

---

# 4. Cache Manager

Single cache controller.

---

# 5. Runtime Class

Java Runtime itself is Singleton.

Example:

```java id="nl6q3l"
Runtime runtime = Runtime.getRuntime();
```

---

# When to Use Singleton

Use when:

✅ Exactly one object needed
✅ Shared global resource
✅ Centralized control required

---

# When NOT to Use

Do not use if:

❌ Multiple instances may be needed later
❌ Testing/mocking difficult
❌ Too much global state

---

# Advantages

---

# 1. Controlled Access

Only one object.

---

# 2. Saves Memory

No duplicate objects.

---

# 3. Global Access Point

Accessible everywhere.

---

# 4. Lazy Initialization Possible

Object created only when needed.

---

# Disadvantages

---

# 1. Hard to Test

Global state difficult in unit testing.

---

# 2. Violates Single Responsibility Principle

Manages:

* business logic
* object lifecycle

---

# 3. Hidden Dependencies

Classes silently depend on Singleton.

---

# 4. Multithreading Complexity

Need thread safety.

---

# Internal Architecture

```text id="a2k0zv"
        Singleton Class
             |
     ----------------
     |              |
private instance   private constructor
             |
      getInstance()
```

---

# Singleton vs Static Class

Many students confuse this.

| Singleton                       | Static Class    |
| ------------------------------- | --------------- |
| Object exists                   | No object       |
| Supports interfaces/inheritance | Cannot inherit  |
| Lazy loading possible           | No lazy loading |
| Can implement polymorphism      | Cannot          |
| Better OOP                      | Less flexible   |

---

# Important Interview Questions

---

# Q1: Can Singleton Have Multiple Objects?

Normally no.

But possible using:

* Reflection
* Serialization
* Cloning

unless protected.

---

# Q2: Why Static Method?

Because object may not exist yet.

Need class-level access.

---

# Q3: Why Static Variable?

To store single shared instance.

---

# Quick Revision Notes

## Category

Creational Pattern

---

## Purpose

Ensure only one object exists.

---

## Main Components

* Private constructor
* Static instance
* Static getter

---

## Main Benefit

Controlled single shared instance.

---

# Simple One-Line Definition

> Singleton Pattern ensures that a class has only one instance and provides a global point of access to it.
