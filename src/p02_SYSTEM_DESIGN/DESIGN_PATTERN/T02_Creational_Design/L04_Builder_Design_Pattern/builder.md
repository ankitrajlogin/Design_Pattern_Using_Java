# Builder Design Pattern

The **Builder Pattern** is a **Creational Design Pattern**.

It is used when:

> An object has too many fields/options and creating it becomes complicated.

Builder Pattern helps create objects:

✅ Step by step
✅ Readably
✅ Flexibly
✅ Without huge constructors

---

# Real-Life Example

Think about ordering a Burger.

A burger can have:

* Bread
* Cheese
* Paneer
* Mayo
* Onion
* Extra Cheese
* Sauce

Different combinations possible.

Without Builder:

You may need:

```java id="g9e6l5"
new Burger(true, false, true, false, true, ...)
```

Very confusing.

Instead:

```java id="g0mbu0"
Burger burger = new BurgerBuilder()
                    .addCheese()
                    .addMayo()
                    .build();
```

Much cleaner.

This is Builder Pattern.

---

# Main Problem Without Builder

Suppose we create a `User` object.

Fields:

* name
* age
* email
* phone
* address
* company
* country

---

# ❌ BAD CODE (Telescoping Constructor Problem)

```java id="h3yr7p"
class User {

    String name;
    int age;
    String email;
    String phone;
    String address;

    public User(
            String name,
            int age,
            String email,
            String phone,
            String address
    ) {

        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
```

Usage:

```java id="rwjlwm"
User user = new User(
        "Ankit",
        22,
        "abc@gmail.com",
        "9999999999",
        "Bangalore"
);
```

---

# Problems

---

# 1. Constructor Becomes Huge

Too many parameters.

Hard to manage.

---

# 2. Hard to Read

```java id="zc5s8d"
new User("A",22,"x","y","z")
```

Which parameter is which?

Confusing.

---

# 3. Optional Parameters Problem

What if:

* address optional
* company optional

Need many constructors.

---

# 4. Error-Prone

Parameter order mistakes easy.

Example:

```java id="jjlwm9"
new User("Ankit", "Bangalore", 22)
```

Wrong ordering.

---

# Why Builder Pattern?

Builder Pattern solves:

✅ Complex object creation
✅ Optional parameters
✅ Readability issues
✅ Step-by-step construction

---

# Core Idea

Instead of:

```java id="0nfjgo"
new User(...)
```

we do:

```java id="a6jlwm"
new UserBuilder()
    .setName(...)
    .setAge(...)
    .build();
```

---

# Structure of Builder Pattern

---

# 1. Product Class

Actual object to create.

Example:

* User
* Burger
* House

---

# 2. Builder Class

Builds object step by step.

---

# 3. build() Method

Returns final object.

---

# How to Make Builder Class Properly

Builder Pattern becomes very easy once you understand:

> Builder is just a helper object that collects data step-by-step and finally creates the main object.

---

# Main Goal of Builder

Instead of:

```java id="g7mwxt"
new User("Ankit", 22, "abc@gmail.com", ...)
```

we want:

```java id="23xlbp"
new UserBuilder()
    .setName("Ankit")
    .setAge(22)
    .build();
```

Cleaner and readable.

---

# Step-by-Step Process to Create Builder

We will create:

# User Object

with fields:

* name
* age
* email
* address

---

# STEP 1 → Create Product Class

This is the actual object we want.

```java id="nslrr5"
class User {

    private String name;
    private int age;
    private String email;
    private String address;

    public User(
            String name,
            int age,
            String email,
            String address
    ) {

        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public void display() {

        System.out.println(
                name + " " +
                age + " " +
                email + " " +
                address
        );
    }
}
```

---

# Important Understanding

Builder will eventually call:

```java id="8y2hh6"
new User(...)
```

internally.

Client should NOT call constructor directly.

---

# STEP 2 → Create Builder Class

Now create helper class:

```java id="vzxg3g"
class UserBuilder {

}
```

---

# STEP 3 → Copy Same Fields Into Builder

This is VERY important.

Builder stores temporary values.

```java id="ltspui"
class UserBuilder {

    private String name;
    private int age;
    private String email;
    private String address;
}
```

---

# Why Same Fields Needed?

Because builder collects values step-by-step.

Example:

```java id="z9jlwm"
builder.setName("Ankit");
```

Builder must store `"Ankit"` somewhere.

So builder has same fields.

---

# STEP 4 → Create Setter-like Methods

Now create methods:

```java id="a5jlwm"
public UserBuilder setName(String name)
```

---

# Complete Example

```java id="1jlwm8"
public UserBuilder setName(String name) {

    this.name = name;

    return this;
}
```

---

# Why Return this?

VERY IMPORTANT.

This enables:

# Method Chaining

Without:

```java id="n6jlwm"
return this;
```

you cannot do:

```java id="k0jlwm"
builder.setName()
       .setAge()
```

---

# Internal Understanding of return this

Suppose:

```java id="9mjlwm"
UserBuilder builder =
        new UserBuilder();
```

Inside:

```java id="6tjlwm"
return this;
```

means:

```text id="vwjlwm"
return current builder object
```

So same builder continues.

---

# Complete Builder Methods

```java id="98jlwm"
class UserBuilder {

    private String name;
    private int age;
    private String email;
    private String address;

    public UserBuilder setName(String name) {

        this.name = name;

        return this;
    }

    public UserBuilder setAge(int age) {

        this.age = age;

        return this;
    }

    public UserBuilder setEmail(String email) {

        this.email = email;

        return this;
    }

    public UserBuilder setAddress(String address) {

        this.address = address;

        return this;
    }
}
```

---

# STEP 5 → Create build() Method

This is MOST IMPORTANT part.

Builder finally creates object.

```java id="qzjlwm"
public User build()
```

---

# Complete build()

```java id="4xjlwm"
public User build() {

    return new User(
            name,
            age,
            email,
            address
    );
}
```

---

# What Happens Here?

Builder collected values:

```text id="f0jlwm"
name = "Ankit"
age = 22
```

Now build() creates final object:

```java id="9cjlwm"
new User(...)
```

---

# Complete Final Builder

```java id="jlwmd0"
class UserBuilder {

    private String name;
    private int age;
    private String email;
    private String address;

    public UserBuilder setName(String name) {

        this.name = name;

        return this;
    }

    public UserBuilder setAge(int age) {

        this.age = age;

        return this;
    }

    public UserBuilder setEmail(String email) {

        this.email = email;

        return this;
    }

    public UserBuilder setAddress(String address) {

        this.address = address;

        return this;
    }

    public User build() {

        return new User(
                name,
                age,
                email,
                address
        );
    }
}
```

---

# STEP 6 → Client Code

```java id="t6jlwm"
public class Main {

    public static void main(String[] args) {

        User user = new UserBuilder()

                .setName("Ankit")

                .setAge(22)

                .setEmail("ankit@gmail.com")

                .setAddress("Bangalore")

                .build();

        user.display();
    }
}
```

---

# Internal Flow Step-by-Step

---

# Step 1

```java id="h1jlwm"
new UserBuilder()
```

Creates builder object.

---

# Step 2

```java id="0fjlwm"
.setName("Ankit")
```

Stores:

```text id="p6jlwm"
name = "Ankit"
```

inside builder.

Returns SAME builder.

---

# Step 3

```java id="y7jlwm"
.setAge(22)
```

Stores:

```text id="1rjlwm"
age = 22
```

Returns SAME builder.

---

# Step 4

```java id="l3jlwm"
.build()
```

Finally creates:

```java id="s4jlwm"
new User(...)
```

---

# Visual Memory Diagram

```text id="0bjlwm"
UserBuilder Object
-------------------
name = "Ankit"
age = 22
email = "abc@gmail.com"

        |
        v

build()

        |
        v

User Object Created
```

---

# Most Important Understanding

Builder itself is NOT final object.

Builder only HELPS create final object.

---

# Common Interview Question

## Why Builder Has Same Fields?

Because builder temporarily stores values before creating final object.

---

# Professional Enterprise-Level Builder

Usually:

```java id="jlwmt5"
static class Builder
```

inside product class.

---

# Example

```java id="jlwmf7"
class User {

    private String name;
    private int age;

    private User(Builder builder) {

        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder {

        private String name;
        private int age;

        public Builder setName(String name) {

            this.name = name;

            return this;
        }

        public Builder setAge(int age) {

            this.age = age;

            return this;
        }

        public User build() {

            return new User(this);
        }
    }
}
```

Usage:

```java id="jlwmw9"
User user = new User.Builder()
                    .setName("Ankit")
                    .setAge(22)
                    .build();
```

This is MOST common real-world approach.

---

# Why Nested Builder Better?

Because:

✅ Builder tied to object
✅ Cleaner encapsulation
✅ Easy maintenance
✅ Used heavily in enterprise systems

---

# Final Mental Model

Think Builder like:

# Online Form Filling

You fill:

* name
* email
* age

step-by-step.

Then press:

```text id="jlwmv1"
SUBMIT
```

That submit is:

```java id="9jjlwm"
build()
```

which creates final object.


# Visual Flow

```text id="1jlwm2"
Client
   |
   v
Builder
   |
   v
Final Object
```

---

# Why Builder Pattern Is Powerful

Suppose object has:

```text id="rjlwm5"
20 optional fields
```

Without Builder:

Constructor nightmare.

With Builder:

Only needed fields used.

---

# Better Real-World Example

---

# House Construction

House may contain:

* doors
* windows
* swimming pool
* garage
* garden

Different houses different combinations.

Builder creates step by step.

---

# Immutable Objects + Builder

Builder often used with immutable classes.

Meaning:

```text id="vjlwm1"
Object cannot change after creation
```

Very common in enterprise systems.

---

# Example (Immutable User)

```java id="2jlwm7"
private final String name;
```

No setters.

Only Builder constructs object.

---

# Real-World Examples

---

# 1. StringBuilder

```java id="j4jlwm"
StringBuilder sb = new StringBuilder();

sb.append("Hello")
  .append(" World");
```

---

# 2. Lombok @Builder

Very common in Spring Boot.

```java id="h9jlwm"
@Builder
class User {
}
```

---

# 3. HTTP Request Builders

```java id="c7jlwm"
HttpRequest.newBuilder()
```

---

# 4. SQL Query Builders

ORM frameworks use Builder heavily.

---

# 5. Pizza/Burger Systems

Customizable products.

---

# When to Use Builder Pattern

Use when:

✅ Too many constructor parameters
✅ Many optional fields
✅ Step-by-step object creation needed
✅ Immutable objects desired

---

# When NOT to Use

Do not use if:

❌ Object very simple
❌ Only 2-3 fields
❌ No optional configuration

---

# Advantages

---

# 1. Readable Code

Much cleaner than huge constructors.

---

# 2. Avoids Constructor Explosion

No need many overloaded constructors.

---

# 3. Flexible Object Creation

Optional fields easy.

---

# 4. Immutable Objects Support

Builder works perfectly.

---

# 5. Method Chaining

Elegant syntax.

---

# Disadvantages

---

# 1. More Classes

Need Builder class.

---

# 2. Slightly More Code

Extra boilerplate.

---

# Builder vs Factory

Very important interview question.

| Builder                            | Factory                       |
| ---------------------------------- | ----------------------------- |
| Builds complex object step-by-step | Creates object directly       |
| Focus on configuration             | Focus on object type          |
| Used for large objects             | Used for polymorphic creation |
| Example: House builder             | Example: Vehicle factory      |

---

# Builder vs Abstract Factory

| Builder                       | Abstract Factory             |
| ----------------------------- | ---------------------------- |
| Creates one complex object    | Creates families of objects  |
| Step-by-step construction     | Related product creation     |
| Focus on object configuration | Focus on compatible families |

---

# Internal Architecture

```text id="jjlwm1"
        Client
           |
           v
        Builder
           |
           v
        Product
```

---

# Important Interview Point

## Main Goal

Separate object construction from object representation.

---

# Quick Revision Notes

## Category

Creational Pattern

---

## Purpose

Create complex objects step by step.

---

## Key Components

* Product
* Builder
* build() method

---

## Main Benefit

Readable flexible object creation.

---

# One-Line Definition

> Builder Pattern constructs complex objects step by step while separating the construction process from the final representation.
