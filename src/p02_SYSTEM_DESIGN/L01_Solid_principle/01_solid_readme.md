# SOLID Principles in Java

SOLID is a set of 5 design principles in object-oriented programming, introduced by Robert C. Martin (Uncle Bob).  
They help write clean, maintainable, scalable, and testable code.

---

## ✅ 1. Single Responsibility Principle (SRP)

A class should have only one reason to change.

**❌ Bad Code (Violates SRP)**
```java
class Report {
    public String generateReport() {
        return "Report data";
    }

    public void saveToFile(String report) {
        // Handles file writing
        System.out.println("Report saved to file.");
    }
}
```
Here, Report is doing two jobs: generating data and saving to a file.

**✅ Good Code (Follows SRP)**
```java
class Report {
    public String generateReport() {
        return "Report data";
    }
}

class ReportSaver {
    public void saveToFile(String report) {
        System.out.println("Report saved to file.");
    }
}
```
👉 Now, Report is only about reports, and ReportSaver is about saving.

---

## ✅ 2. Open/Closed Principle (OCP)

Classes should be open for extension but closed for modification.

**❌ Bad Code (Violates OCP)**
```java
class AreaCalculator {
    public double calculate(Object shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius * c.radius;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.length * r.breadth;
        }
        return 0;
    }
}
```
If we add a new shape (like Triangle), we must modify this class → violates OCP.

**✅ Good Code (Follows OCP)**
```java
interface Shape {
    double area();
}

class Circle implements Shape {
    double radius;
    Circle(double r) { this.radius = r; }

    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    double length, breadth;
    Rectangle(double l, double b) { this.length = l; this.breadth = b; }

    public double area() {
        return length * breadth;
    }
}

class AreaCalculator {
    public double calculate(Shape shape) {
        return shape.area();
    }
}
```
👉 Now we can add Triangle implements Shape without modifying existing code.

---

## ✅ 3. Liskov Substitution Principle (LSP)

Subclasses should be substitutable for their base class.

**❌ Bad Code (Violates LSP)**
```java
class Bird {
    public void fly() {
        System.out.println("I can fly!");
    }
}

class Sparrow extends Bird {
    public void fly() {
        System.out.println("Sparrow flying...");
    }
}

class Ostrich extends Bird {
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly!");
    }
}
```
👉 Ostrich breaks the expectation that all Birds can fly.

**✅ Good Code (Follows LSP)**
```java
abstract class Bird {
    public abstract void move();
}

class Sparrow extends Bird {
    public void move() {
        System.out.println("Sparrow flying...");
    }
}

class Ostrich extends Bird {
    public void move() {
        System.out.println("Ostrich running...");
    }
}
```
👉 Now, all subclasses respect the parent’s contract.

---

## ✅ 4. Interface Segregation Principle (ISP)

Don’t force a class to implement methods it doesn’t need.

**❌ Bad Code (Violates ISP)**
```java
interface Bird {
    void fly();
    void swim();
}

class Sparrow implements Bird {
    public void fly() { System.out.println("Sparrow flying..."); }
    public void swim() { throw new UnsupportedOperationException("Sparrow can't swim!"); }
}
```
👉 Sparrow is forced to implement swim(), which makes no sense.

**✅ Good Code (Follows ISP)**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Sparrow implements Flyable {
    public void fly() {
        System.out.println("Sparrow flying...");
    }
}

class Duck implements Flyable, Swimmable {
    public void fly() {
        System.out.println("Duck flying...");
    }

    public void swim() {
        System.out.println("Duck swimming...");
    }
}
```
👉 Now each class implements only what it actually needs.

---

## ✅ 5. Dependency Inversion Principle (DIP)

High-level modules should not depend on low-level modules. Both should depend on abstractions.

**❌ Bad Code (Violates DIP)**
```java
class LightBulb {
    public void turnOn() { System.out.println("LightBulb ON"); }
    public void turnOff() { System.out.println("LightBulb OFF"); }
}

class Switch {
    private LightBulb bulb;

    public Switch(LightBulb b) {
        this.bulb = b;
    }

    public void operate(boolean on) {
        if (on) bulb.turnOn(); else bulb.turnOff();
    }
}
```
👉 Switch is tightly coupled to LightBulb. If we want a Fan, we must rewrite code.

**✅ Good Code (Follows DIP)**
```java
interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable {
    public void turnOn() { System.out.println("LightBulb ON"); }
    public void turnOff() { System.out.println("LightBulb OFF"); }
}

class Fan implements Switchable {
    public void turnOn() { System.out.println("Fan ON"); }
    public void turnOff() { System.out.println("Fan OFF"); }
}

class Switch {
    private Switchable device;

    public Switch(Switchable d) {
        this.device = d;
    }

    public void operate(boolean on) {
        if (on) device.turnOn(); else device.turnOff();
    }
}
```
👉 Now Switch works with any device that implements Switchable.

---

## 📌 Summary Table

| Principle | Bad Code Problem | Good Code Solution |
|-----------|------------------|-------------------|
| SRP | Class does multiple jobs | Split into separate classes |
| OCP | Modifying existing code for new behavior | Use abstractions to extend |
| LSP | Subclass breaks parent contract | Ensure subclass can replace parent |
| ISP | Forcing classes to implement unwanted methods | Use smaller, specific interfaces |
| DIP | High-level depends on low-level | Depend on abstractions (interfaces) |

---

## 🎯 Takeaway

SOLID helps write flexible, maintainable, and scalable OOP code.

Always think: “Will adding new functionality break my old code?” If yes → probably violating SOLID.


-----------------------------------------------

-----------------------------------------------

# LSP vs ISP

👉 **Layman Language:**

**LSP (Liskov Substitution Principle):**  
"If class B is a child of class A, then replacing A with B should not break the system."  
(Focus = correctness of subclassing)

**ISP (Interface Segregation Principle):**  
"Don’t force a class to implement things it doesn’t need. Split big interfaces into smaller ones."  
(Focus = design of interfaces)

---

## 🚫 Example Violating Both LSP and ISP

```java
// Big interface (bad design - violates ISP)
interface Bird {
    void fly();
    void swim();
}

// Duck can fly & swim ✅
class Duck implements Bird {
    public void fly() { System.out.println("Duck flying"); }
    public void swim() { System.out.println("Duck swimming"); }
}

// Penguin cannot fly ❌ violates LSP
class Penguin implements Bird {
    public void fly() { 
        throw new UnsupportedOperationException("Penguin can't fly!");
    }
    public void swim() { System.out.println("Penguin swimming"); }
}
```

### 🔴 Problems:
- **ISP violation:** Bird interface forces all birds to `fly()`, but Penguins can’t.
- **LSP violation:** Replacing `Bird b = new Duck()` with `Bird b = new Penguin()` will crash if we call `b.fly()`.

---

## ✅ Fixing ISP (Split Interfaces)

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Duck flying"); }
    public void swim() { System.out.println("Duck swimming"); }
}

class Penguin implements Swimmable {
    public void swim() { System.out.println("Penguin swimming"); }
}
```

👉 Now, Penguin is not forced to implement `fly()`.  
✅ ISP is fixed.

---

## ✅ Fixing LSP (Substitute safely)

```java
// Abstract base class
abstract class Bird {
    String name;
    Bird(String name) { this.name = name; }
}

// Specialize capabilities
class FlyingBird extends Bird {
    FlyingBird(String name) { super(name); }
    void fly() { System.out.println(name + " flying"); }
}

class SwimmingBird extends Bird {
    SwimmingBird(String name) { super(name); }
    void swim() { System.out.println(name + " swimming"); }
}

// Duck is both flying + swimming
class Duck extends FlyingBird {
    Duck(String name) { super(name); }
    void swim() { System.out.println(name + " swimming"); }
}

// Penguin only swims (doesn't break LSP now)
class Penguin extends SwimmingBird {
    Penguin(String name) { super(name); }
}
```

👉 Now:
- Duck behaves like a flying + swimming bird.
- Penguin behaves like a swimming bird only.

You can safely substitute them in correct contexts without breaking behavior.

---

## 🎯 Key Difference Recap (LSP vs ISP)

| Aspect   | LSP (Liskov Substitution Principle) | ISP (Interface Segregation Principle) |
|----------|-------------------------------------|---------------------------------------|
| **Focus** | Correctness of subclassing          | Design of interfaces                  |
| **Problem** | Subclass breaks expected behavior  | Classes forced to implement unused methods |
| **Symptom** | Runtime errors, broken polymorphism | Large "fat" interfaces with unnecessary methods |
| **Fix**   | Ensure subclass honors parent contract | Split interfaces into smaller, specific ones |
| **Example** | Penguin can't substitute Bird      | Bird interface forces fly() on Penguin |

---

👉 **Layman Summary:**
- **LSP:** If you say "a penguin is a bird," then it should behave like a bird in your code (no surprises).
- **ISP:** Don’t create an "everything-bird" interface (fly, swim, sing, dance) and force penguins to dance when they don’t.  

