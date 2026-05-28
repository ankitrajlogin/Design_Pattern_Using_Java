# Factory Design Pattern

The **Factory Pattern** is a **Creational Design Pattern**.

It is used when:

> We want to create objects without exposing the object creation logic to the client.

Instead of using:

```java id="sh5c9j"
new Object()
```

everywhere,
we delegate object creation to a special class called:

# Factory

---

# Real-Life Example

Think about ordering food in a restaurant.

You say:

```text id="q0e46q"
"I want Pizza"
```

You do NOT care:

* how dough prepared
* how cheese added
* how oven works

Restaurant kitchen creates object for you.

Here:

* Customer = Client
* Kitchen = Factory
* Pizza = Product

This is Factory Pattern.

---

# Main Problem Without Factory Pattern

Suppose we are building a Notification System.

Types:

* Email Notification
* SMS Notification
* Push Notification

---

# ❌ BAD CODE (Without Factory)

```java id="0p3zsf"
class EmailNotification {

    public void send() {

        System.out.println("Sending Email");
    }
}

class SMSNotification {

    public void send() {

        System.out.println("Sending SMS");
    }
}
```

Main:

```java id="sp0f9d"
public class Main {

    public static void main(String[] args) {

        EmailNotification email =
                new EmailNotification();

        email.send();

        SMSNotification sms =
                new SMSNotification();

        sms.send();
    }
}
```

---

# Problems in This Code

---

# 1. Tight Coupling

Main class directly depends on concrete classes.

```java id="xt6bfi"
new EmailNotification()
```

Bad.

---

# 2. Difficult to Extend

Suppose new type added:

```text id="w7aqpz"
WhatsAppNotification
```

Need to modify client code everywhere.

---

# 3. Object Creation Logic Spread Everywhere

`new` keyword scattered in many places.

Hard to manage.

---

# 4. Violates Open Closed Principle

Adding new types requires modifying existing code.

---

# Why Factory Pattern?

Factory Pattern centralizes object creation.

Instead of:

```java id="m4n3vv"
new EmailNotification()
```

we do:

```java id="n9tp4x"
factory.createNotification()
```

Cleaner.

---

# Core Idea

Client should ask factory for object.

Factory decides:

* which object
* how object created
* which configuration used

---

# Structure of Factory Pattern

---

# 1. Product Interface

Common interface for all products.

Example:

* Notification
* Vehicle
* Shape

---

# 2. Concrete Products

Actual implementations.

Example:

* EmailNotification
* SMSNotification

---

# 3. Factory Class

Creates and returns objects.

---

# Step-by-Step Implementation

---

# Step 1: Create Product Interface

```java id="j7m8ot"
interface Notification {

    void send();
}
```

---

# Step 2: Create Concrete Products

---

# EmailNotification

```java id="j82gcv"
class EmailNotification implements Notification {

    @Override
    public void send() {

        System.out.println("Sending Email Notification");
    }
}
```

---

# SMSNotification

```java id="d57kh3"
class SMSNotification implements Notification {

    @Override
    public void send() {

        System.out.println("Sending SMS Notification");
    }
}
```

---

# PushNotification

```java id="c06lws"
class PushNotification implements Notification {

    @Override
    public void send() {

        System.out.println("Sending Push Notification");
    }
}
```

---

# Step 3: Create Factory Class

```java id="e53brn"
class NotificationFactory {

    public Notification createNotification(String type) {

        if(type.equalsIgnoreCase("EMAIL")) {

            return new EmailNotification();
        }

        else if(type.equalsIgnoreCase("SMS")) {

            return new SMSNotification();
        }

        else if(type.equalsIgnoreCase("PUSH")) {

            return new PushNotification();
        }

        return null;
    }
}
```

---

# Step 4: Client Code

```java id="2ejjpk"
public class Main {

    public static void main(String[] args) {

        NotificationFactory factory =
                new NotificationFactory();

        Notification notification1 =
                factory.createNotification("EMAIL");

        notification1.send();

        Notification notification2 =
                factory.createNotification("SMS");

        notification2.send();
    }
}
```

---

# Output

```text id="fjthsw"
Sending Email Notification

Sending SMS Notification
```

---

# Important Understanding

Client does NOT know:

```java id="4x1v90"
new EmailNotification()
```

Object creation hidden inside factory.

This is biggest advantage.

---

# Internal Flow

Client:

```java id="y15m8j"
factory.createNotification("EMAIL")
```

Factory decides:

```java id="8r6ob6"
return new EmailNotification();
```

Client only gets:

```java id="bc8krx"
Notification
```

reference.

---

# Visual Flow

```text id="lthg4y"
Client
   |
   v
Factory
   |
   v
Concrete Product
```

---

# Why This Is Better

Suppose tomorrow:

```text id="qlqdz7"
SlackNotification
```

added.

Only modify factory.

Client code unchanged.

---

# Better Version Using Enum

Instead of String:

```java id="kllr8x"
enum NotificationType {

    EMAIL,
    SMS,
    PUSH
}
```

Safer.

Avoid typo mistakes.

---

# Real-World Examples

---

# 1. Database Drivers

```java id="2jlwmq"
DriverManager.getConnection()
```

returns different DB objects.

---

# 2. Spring Framework

Spring creates beans using factory mechanism.

---

# 3. Logger Frameworks

Factory creates logger instance.

---

# 4. UI Components

Factory creates:

* Windows button
* Mac button
* Linux button

---

# 5. Payment Gateway

Factory returns:

* UPI
* CreditCard
* PayPal

processor.

---

# When to Use Factory Pattern

Use when:

✅ Object creation complex
✅ Many subclasses exist
✅ Client should not know exact object type
✅ Want loose coupling

---

# When NOT to Use

Do not use if:

❌ Only one simple object
❌ Object creation trivial
❌ Adds unnecessary complexity

---

# Advantages

---

# 1. Loose Coupling

Client depends on interface,
not concrete class.

---

# 2. Centralized Object Creation

Easy maintenance.

---

# 3. Easier Extension

Add new products easily.

---

# 4. Better Readability

Cleaner client code.

---

# 5. Follows Open Closed Principle

Open for extension.

---

# Disadvantages

---

# 1. More Classes

Need:

* interface
* products
* factory

---

# 2. Factory Can Become Huge

If too many product types added.

---

# Important Interview Question

## Is Factory Pattern Same as Simple Factory?

Many people call this:

```text id="q0tzg2"
Simple Factory
```

because one factory creates multiple objects.

True Factory Method Pattern is slightly different.

---

# Factory vs Singleton

| Factory                   | Singleton                |
| ------------------------- | ------------------------ |
| Creates objects           | Restricts objects        |
| Multiple objects possible | Only one object          |
| Focus on creation logic   | Focus on single instance |
| Creational Pattern        | Creational Pattern       |

---

# Factory vs Strategy

| Factory                | Strategy              |
| ---------------------- | --------------------- |
| Creates object         | Changes behavior      |
| Used during creation   | Used during execution |
| Focus on instantiation | Focus on algorithms   |

---

# Internal Architecture

```text id="j93a5p"
            Product Interface
                 /   |   \
                /    |    \
         Email SMS Push

                 ^
                 |
              Factory
                 ^
                 |
               Client
```

---

# Very Important Interview Point

## Main Goal

Hide object creation logic from client.

---

# Quick Revision Notes

## Category

Creational Pattern

---

## Main Purpose

Centralize object creation.

---

## Key Components

* Product Interface
* Concrete Products
* Factory Class

---

## Main Benefit

Loose coupling.

---

# Simple One-Line Definition

> Factory Pattern provides an interface for creating objects while hiding the actual object creation logic from the client.
