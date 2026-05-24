# Diamond Problem in Java — Complete Notes

# 1. What is the Diamond Problem?

The **Diamond Problem** is a problem that occurs in **multiple inheritance** when a class inherits from two parent classes that already inherit from the same superclass.

This creates ambiguity:

> Which method or property should the child class inherit?

---

# Structure of Diamond Problem

```text id="3okp2k"
        A
       / \
      B   C
       \ /
        D
```

* `B` inherits from `A`
* `C` inherits from `A`
* `D` inherits from both `B` and `C`

Now class `D` receives two copies/paths of class `A`.

This creates confusion.

---

# 2. Why is it Called Diamond Problem?

Because the inheritance structure forms a diamond shape.

```text id="t0mjlwm"
        A
       / \
      B   C
       \ /
        D
```

---

# 3. Problem in Simple Words

Suppose:

* `A` contains method `show()`
* `B` overrides `show()`
* `C` also overrides `show()`
* `D` inherits both `B` and `C`

Now:

```java id="djlwmg"
D d = new D();
d.show();
```

Question:

```text id="kt6t13"
Should Java call B's show() or C's show() ?
```

This confusion is the Diamond Problem.

---

# 4. Diamond Problem Example

Suppose Java allowed multiple inheritance using classes.

---

## Step 1: Parent Class

```java id="3mqlpa"
class A {

    void show() {
        System.out.println("A class");
    }
}
```

---

## Step 2: Two Child Classes

```java id="5t8v0k"
class B extends A {

    void show() {
        System.out.println("B class");
    }
}
```

```java id="buw8bz"
class C extends A {

    void show() {
        System.out.println("C class");
    }
}
```

---

## Step 3: Multiple Inheritance

```java id="o1rjlwm"
// Assume Java allowed this
class D extends B, C {

}
```

---

## Problem

```java id="c8jshd"
D d = new D();
d.show();
```

Now Java gets confused:

```text id="jbjlwm"
Should show() come from:
1. B class ?
2. C class ?
```

This ambiguity causes the Diamond Problem.

---

# 5. How Java Solves the Diamond Problem

Java solves it in TWO major ways.

---

# Solution 1: Java Does NOT Support Multiple Inheritance Using Classes

Java simply avoids the problem.

Java allows:

```java id="u7sptk"
class B extends A
```

But NOT:

```java id="i14jlwm"
class D extends B, C
```

Compiler Error ❌

---

# Why Java Chose This?

Because:

* it avoids ambiguity
* simplifies inheritance
* improves readability
* prevents complex memory structure

---

# Java Rule

```text id="jlwmm3"
One class can extend only one class
```

---

# 6. Then How Does Java Support Multiple Inheritance?

Java supports multiple inheritance using:

# Interfaces

---

# 7. What is an Interface?

An interface contains:

* abstract methods
* constants
* default/static methods (after Java 8)

---

# Multiple Inheritance Using Interfaces

```java id="e2j0b6"
interface Animal {
    void eat();
}

interface Pet {
    void play();
}

class Dog implements Animal, Pet {

    public void eat() {
        System.out.println("Dog eats");
    }

    public void play() {
        System.out.println("Dog plays");
    }
}
```

---

# Why No Diamond Problem Initially?

Before Java 8:

* interfaces only had abstract methods
* no method body existed

So no ambiguity.

Example:

```text id="o2jlwm"
Animal says: "Someone must implement eat()"

Pet says: "Someone must implement play()"
```

No conflict.

---

# 8. Diamond Problem Returned in Java 8

Java 8 introduced:

```text id="v0r07z"
default methods
```

Now interfaces can contain method implementation.

This reintroduced the Diamond Problem.

---

# 9. Diamond Problem with Interfaces

---

## Interface A

```java id="vjlwmu"
interface A {

    default void show() {
        System.out.println("A show()");
    }
}
```

---

## Interface B

```java id="0z0s4n"
interface B {

    default void show() {
        System.out.println("B show()");
    }
}
```

---

## Child Class

```java id="jlwm3f"
class Test implements A, B {

}
```

Compiler Error ❌

---

# Why Error Happens?

Because:

```text id="lg3epx"
Both interfaces provide same method:
show()
```

Java becomes confused:

```text id="jlwmms"
Which show() should be used?
```

---

# 10. How Java Solves This Interface Ambiguity

Java forces the child class to override the method.

---

# Correct Solution

```java id="jlwm7y"
interface A {

    default void show() {
        System.out.println("A show()");
    }
}

interface B {

    default void show() {
        System.out.println("B show()");
    }
}

class Test implements A, B {

    @Override
    public void show() {
        System.out.println("Ambiguity resolved");
    }
}
```

---

# Main Class

```java id="jlwm6x"
public class Main {

    public static void main(String[] args) {

        Test t = new Test();
        t.show();
    }
}
```

---

# Output

```text id="f4w0rd"
Ambiguity resolved
```

---

# 11. How This Works Internally

Java says:

```text id="jlwm24"
"If two interfaces provide same default method,
the implementing class MUST explicitly decide."
```

So Java:

* stops automatic inheritance
* asks programmer to resolve ambiguity manually

This removes confusion.

---

# 12. Calling Specific Interface Method

You can explicitly call a particular interface method.

Syntax:

```java id="jlwm4k"
InterfaceName.super.methodName()
```

---

# Example

```java id="jlwm8d"
interface A {

    default void show() {
        System.out.println("A show()");
    }
}

interface B {

    default void show() {
        System.out.println("B show()");
    }
}

class Test implements A, B {

    @Override
    public void show() {

        A.super.show();
        B.super.show();

        System.out.println("Own method");
    }
}
```

---

# Output

```text id="6zjlwm"
A show()
B show()
Own method
```

---

# How It Works

```text id="jlwm4z"
A.super.show()
```

means:

> call show() from interface A

Similarly:

```text id="jlwmr6"
B.super.show()
```

calls method from interface B.

---

# 13. Different Ways Java Achieves Multiple Inheritance

Java does NOT support multiple inheritance using classes.

But it supports it using:

---

# Method 1: Multiple Interfaces

```java id="zjlwm0"
interface A {
    void display();
}

interface B {
    void show();
}

class Test implements A, B {

    public void display() {
        System.out.println("Display");
    }

    public void show() {
        System.out.println("Show");
    }
}
```

---

# How It Works

Class `Test`:

* inherits behavior contract from A
* inherits behavior contract from B

So:

```text id="jlwm0u"
One class implements multiple interfaces
```

---

# Method 2: Interface Extending Interface

Interfaces can extend multiple interfaces.

---

## Example

```java id="jlwm6l"
interface A {
    void methodA();
}

interface B {
    void methodB();
}

interface C extends A, B {
    void methodC();
}
```

---

# How It Works

`C` now contains:

* methodA()
* methodB()
* methodC()

Any class implementing `C` must implement all methods.

---

# Example

```java id="jlwm3v"
class Test implements C {

    public void methodA() {
        System.out.println("A");
    }

    public void methodB() {
        System.out.println("B");
    }

    public void methodC() {
        System.out.println("C");
    }
}
```

---

# 14. Java Rules for Resolving Diamond Problem

---

# Rule 1: Class Has Higher Priority Than Interface

If superclass and interface both contain same method:

```text id="8tjlwm"
Class method wins
```

---

## Example

```java id="wjlwmz"
class A {

    void show() {
        System.out.println("Class A");
    }
}

interface B {

    default void show() {
        System.out.println("Interface B");
    }
}

class Test extends A implements B {
}
```

---

# Output

```text id="jlwm72"
Class A
```

---

# Why?

Because:

```text id="g7jlwm"
Concrete class methods are more specific than interface methods
```

---

# Rule 2: Most Specific Interface Wins

If one interface extends another:

---

## Example

```java id="9jlwm9"
interface A {

    default void show() {
        System.out.println("A");
    }
}

interface B extends A {

    default void show() {
        System.out.println("B");
    }
}

class Test implements B {
}
```

---

# Output

```text id="jlwmk1"
B
```

---

# Why?

Because B is more specific.

---

# Rule 3: Child Must Override If Ambiguity Exists

If Java still cannot decide:

```text id="jlwmn2"
Child class must override the method
```

---

# 15. Memory Understanding

Suppose:

```text id="njlwm4"
A → show()
B → show()
```

When class implements both:

```text id="vjlwm9"
class Test implements A, B
```

Java sees:

* two same methods
* same signature
* same return type

So JVM cannot decide automatically.

Hence compiler forces override.

---

# 16. Final Summary Table

| Situation                          | Java Solution                |
| ---------------------------------- | ---------------------------- |
| Multiple inheritance via class     | Not allowed                  |
| Same default methods in interfaces | Child must override          |
| Class + interface same method      | Class method wins            |
| Interface hierarchy conflict       | Most specific interface wins |

---

# 17. Interview Definition

> The Diamond Problem occurs in multiple inheritance when a child class receives the same method from multiple parent classes, causing ambiguity. Java solves this by not supporting multiple inheritance through classes and by forcing explicit method overriding when conflicting default methods occur in interfaces.
