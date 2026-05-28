# Prototype Design Pattern — Complete Proper Notes

---

# What is Prototype Pattern?

The **Prototype Pattern** is a:

# Creational Design Pattern

It is used when:

> Creating objects from scratch is expensive or complicated, so instead of creating new objects repeatedly, we create new objects by cloning existing objects.

---

# Simple One-Line Definition

> Prototype Pattern creates new objects by copying existing objects instead of creating them from scratch.

---

# Real-Life Analogy

Think about:

# Photocopy Machine

Instead of writing a document again and again:

✅ Create one original document
✅ Make copies from it

Prototype Pattern works exactly like this.

---

# Main Goal of Prototype Pattern

Instead of:

```java id="rjlwm2"
new Object()
```

again and again,

we do:

```java id="mjlwm8"
existingObject.clone()
```

---

# Main Idea

Prototype Pattern says:

```text id="jlwmx5"
"Reuse already prepared object state"
```

instead of rebuilding everything from scratch repeatedly.

---

# Why We Need Prototype Pattern

Suppose object creation contains:

* Database loading
* AI model loading
* Huge file parsing
* Image loading
* Network setup
* Cache creation
* Heavy object graph creation

Creating such objects repeatedly becomes expensive.

Prototype Pattern helps avoid repeating this work.

---

# MOST IMPORTANT UNDERSTANDING

Prototype Pattern optimization happens because:

✅ Heavy initialization happens once
✅ Existing prepared object reused
✅ Constructor heavy logic usually skipped during cloning

---

# IMPORTANT

Prototype Pattern is NOT mainly about:

```text id="4jlwm1"
deep copy vs shallow copy
```

It is mainly about:

```text id="7jlwm3"
copying existing objects efficiently
```

---

# Core Internal Difference

---

# Normal Object Creation

```java id="0jlwm9"
new Employee()
```

usually does:

```text id="9jlwm0"
1. Allocate memory
2. Call constructor
3. Run heavy initialization
4. Configure object
5. Load resources
```

---

# Prototype Cloning

```java id="xjlwm4"
clone()
```

usually does:

```text id="3jlwm5"
1. Allocate memory
2. Copy existing values
3. Return copied object
```

Heavy initialization skipped.

---

# Important Understanding

Prototype does NOT skip:

❌ object memory creation

Java still creates object memory.

Prototype skips:

✅ repeated expensive setup logic

---

# What is Heavy Initialization?

Heavy initialization means expensive operations like:

---

# 1. Database Loading

```java id="wjlwm5"
loadEmployeeDataFromDatabase();
```

---

# 2. Huge File Parsing

```java id="2jlwm0"
readHugeCSVFile();
```

---

# 3. AI/ML Model Loading

```java id="5jlwm6"
loadAIModel();
```

---

# 4. Texture/Image Loading

```java id="hjlwm1"
loadTextures();
```

---

# 5. Network Connections

```java id="8jlwm2"
connectToServer();
```

---

# 6. Cache Creation

```java id="6jlwm7"
buildHugeCache();
```

---

# 7. Complex Object Graph Construction

Creating thousands of nested objects.

---

# Important Point

These operations are:

```text id="djlwm7"
Business setup logic
```

NOT basic Java object creation.

---

# Java Object Creation Is Cheap

This:

```java id="qjlwm0"
new Employee()
```

normally only:

✅ allocates memory
✅ assigns default values

Very cheap.

---

# Constructor May Add Heavy Logic

Example:

```java id="9jlwm1"
public Employee() {

    loadDatabase();

    loadImages();

    loadAIModel();
}
```

These operations are expensive.

---

# Prototype Optimization

Prototype says:

```text id="4jlwm0"
"Do expensive work once, then reuse it"
```

---

# Example

Create one fully initialized object:

```java id="xjlwm2"
Employee original =
        new Employee();
```

Heavy loading happens once.

Then clone:

```java id="7jlwm0"
Employee clone =
        original.cloneObject();
```

Only copying happens.

Much faster.

---

# Important Realization

Prototype works ONLY IF:

```text id="2jlwm5"
Some initialized object state can be reused safely
```

---

# If Fresh Loading Is Always Required?

Then Prototype gives little or no benefit.

---

# Example Where Prototype Helps

Suppose game character loads:

* textures
* animations
* AI behavior
* sounds

These are same for many enemies.

So:

✅ Load once
✅ Reuse for clones

Prototype excellent here.

---

# Example Where Prototype Does NOT Help Much

Suppose every employee requires:

```java id="3jlwm8"
loadLatestEmployeeData(employeeId);
```

and every employee has completely unique data.

Then:

```text id="5jlwm8"
Fresh DB call required every time
```

Prototype cannot avoid this.

---

# Most Important Principle

Prototype is useful when:

```text id="0jlwm8"
Large portion of object setup is reusable
```

---

# Good Prototype Scenario

```text id="8jlwm5"
90% same
10% different
```

---

# Bad Prototype Scenario

```text id="cjlwm9"
Every object completely unique
```

---

# Structure of Prototype Pattern

---

# 1. Prototype Interface

Defines cloning method.

---

# 2. Concrete Prototype

Actual object that supports cloning.

---

# 3. Client

Uses clone instead of `new`.

---

# Step-by-Step Deep Copy Example

---

# STEP 1 → Create Prototype Interface

```java id="jlwmu6"
interface Prototype {

    Prototype cloneObject();
}
```

---

# STEP 2 → Create Nested Object

```java id="9jlwm2"
class Address {

    String city;

    String state;

    public Address(String city, String state) {

        this.city = city;

        this.state = state;
    }

    public void showAddress() {

        System.out.println(
                city + ", " + state
        );
    }
}
```

---

# STEP 3 → Create Prototype Class

```java id="2jlwm9"
class Employee implements Prototype {

    String name;

    int salary;

    Address address;
}
```

---

# STEP 4 → Create Heavy Constructor

```java id="0jlwm1"
public Employee(
        String name,
        int salary,
        Address address
) {

    this.name = name;

    this.salary = salary;

    this.address = address;

    loadHeavyData();
}
```

---

# STEP 5 → Simulate Heavy Loading

```java id="5jlwm0"
private void loadHeavyData() {

    System.out.println(
            "Loading heavy resources..."
    );
}
```

---

# STEP 6 → Create Empty Constructor

VERY IMPORTANT.

```java id="1jlwm5"
private Employee() {
}
```

---

# Why Empty Constructor?

Because during cloning:

```text id="8jlwm9"
We do NOT want heavy initialization again
```

---

# STEP 7 → Implement Deep Copy Clone

MOST IMPORTANT PART.

```java id="jlwmh5"
@Override
public Prototype cloneObject() {

    Employee clone =
            new Employee();

    clone.name = this.name;

    clone.salary = this.salary;

    // DEEP COPY
    clone.address =
            new Address(
                    this.address.city,
                    this.address.state
            );

    return clone;
}
```

---

# Important Understanding

This line:

```java id="3jlwm0"
new Address(...)
```

creates separate nested object.

So original and clone become independent.

This is:

# Deep Copy

---

# Complete Final Code

```java id="xjlwm6"
interface Prototype {

    Prototype cloneObject();
}

class Address {

    String city;

    String state;

    public Address(String city, String state) {

        this.city = city;

        this.state = state;
    }

    public void showAddress() {

        System.out.println(
                city + ", " + state
        );
    }
}

class Employee implements Prototype {

    String name;

    int salary;

    Address address;

    public Employee(
            String name,
            int salary,
            Address address
    ) {

        this.name = name;

        this.salary = salary;

        this.address = address;

        loadHeavyData();
    }

    private Employee() {
    }

    private void loadHeavyData() {

        System.out.println(
                "Loading heavy resources..."
        );
    }

    @Override
    public Prototype cloneObject() {

        Employee clone =
                new Employee();

        clone.name = this.name;

        clone.salary = this.salary;

        clone.address =
                new Address(
                        this.address.city,
                        this.address.state
                );

        return clone;
    }

    public void showEmployee() {

        System.out.println(
                "Name: " + name
        );

        System.out.println(
                "Salary: " + salary
        );

        System.out.print(
                "Address: "
        );

        address.showAddress();

        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {

        Address address =
                new Address(
                        "Bangalore",
                        "Karnataka"
                );

        Employee original =
                new Employee(
                        "Ankit",
                        50000,
                        address
                );

        Employee clone =
                (Employee)
                original.cloneObject();

        clone.address.city = "Delhi";

        System.out.println(
                "ORIGINAL OBJECT"
        );

        original.showEmployee();

        System.out.println(
                "CLONED OBJECT"
        );

        clone.showEmployee();
    }
}
```

---

# Output

```text id="4jlwm6"
Loading heavy resources...

ORIGINAL OBJECT
Name: Ankit
Salary: 50000
Address: Bangalore, Karnataka

CLONED OBJECT
Name: Ankit
Salary: 50000
Address: Delhi, Karnataka
```

---

# Most Important Observation

Original object remains:

```text id="6jlwm6"
Bangalore
```

Clone becomes:

```text id="wjlwm2"
Delhi
```

Meaning:

✅ Separate Address objects exist
✅ Deep copy successful

---

# Shallow Copy vs Deep Copy

VERY IMPORTANT.

---

# Shallow Copy

Copies references only.

Example:

```java id="xjlwm8"
clone.address = this.address;
```

Result:

```text id="3jlwm4"
Both objects share SAME Address object
```

Dangerous.

---

# Deep Copy

Creates separate nested objects.

Example:

```java id="yjlwm4"
clone.address =
        new Address(...)
```

Result:

```text id="hjlwm5"
Separate Address objects
```

Safe.

---

# Visual Memory Diagram

---

# Shallow Copy

```text id="9jlwm9"
Original.address ----\
                      \
Clone.address ---------> SAME Address
```

---

# Deep Copy

```text id="7jlwm5"
Original.address ---> Address A

Clone.address ------> Address B
```

Independent.

---

# Does Prototype Always Use Deep Copy?

NO.

Prototype may use:

✅ Shallow copy
✅ Deep copy
✅ Partial copy
✅ Hybrid copy

depending on system needs.

---

# Real Enterprise Systems Often Use Hybrid Copy

| Field Type              | Handling    |
| ----------------------- | ----------- |
| Primitive values        | copied      |
| Immutable objects       | shared      |
| Mutable objects         | deep copied |
| Huge readonly resources | shared      |

---

# Important Understanding

Prototype Pattern is:

```text id="4jlwm4"
Object duplication pattern
```

Deep copy/shallow copy are:

```text id="9jlwm7"
Techniques used during duplication
```

---

# Java Built-in Cloneable

Java provides:

```java id="2jlwm1"
Cloneable
```

interface.

---

# Example

```java id="0jlwm7"
class User implements Cloneable {

    @Override
    protected Object clone()
            throws CloneNotSupportedException {

        return super.clone();
    }
}
```

---

# Important Point About super.clone()

```java id="8jlwm8"
super.clone()
```

usually:

✅ copies object memory directly
❌ does NOT call constructor

This is very important.

---

# Why Prototype Is Fast

Because:

```text id="1jlwm4"
Constructor heavy logic skipped
```

and:

```text id="5jlwm7"
Already initialized state reused
```

---

# Real-World Examples

---

# 1. Game Development

Clone enemies/characters quickly.

---

# 2. Document Templates

Copy existing templates.

---

# 3. Graphic Editors

Duplicate shapes/images.

---

# 4. Spring Framework

Prototype bean scope.

---

# 5. Configuration Systems

Clone pre-configured objects.

---

# When to Use Prototype Pattern

Use when:

✅ Object creation expensive
✅ Similar objects repeatedly needed
✅ Heavy initialization exists
✅ Object setup reusable
✅ Cloning easier than reconstruction

---

# When NOT to Use

Do not use if:

❌ Objects simple
❌ Every object completely unique
❌ Deep copy too complex
❌ Fresh initialization always required

---

# Advantages

---

# 1. Faster Object Creation

Avoids repeated heavy initialization.

---

# 2. Reuses Existing State

Prepared data reused efficiently.

---

# 3. Reduces Expensive Setup

Heavy work done once.

---

# 4. Flexible Copying

Can choose deep/shallow/custom copying.

---

# Disadvantages

---

# 1. Deep Copy Complexity

Nested objects difficult.

---

# 2. Shared Reference Bugs

Improper shallow copy dangerous.

---

# 3. Complex Object Graphs

Circular references difficult.

---

# Prototype vs Factory

| Prototype                      | Factory                     |
| ------------------------------ | --------------------------- |
| Clones existing object         | Creates fresh object        |
| Avoids repeated initialization | Centralized object creation |
| Uses copying                   | Uses constructors           |

---

# Prototype vs Builder

| Prototype                  | Builder                |
| -------------------------- | ---------------------- |
| Duplicates existing object | Creates step-by-step   |
| Focus on cloning           | Focus on configuration |

---

# Internal Architecture

```text id="xjlwm0"
Client
   |
   v
Prototype Interface
   |
Concrete Prototype
   |
cloneObject()
```

---

# MOST IMPORTANT INTERVIEW POINT

> Prototype Pattern improves performance by cloning already initialized reusable object state instead of repeatedly rebuilding objects from scratch.

> #### Prototype Pattern works ONLY when some initialized data/state can be reused. If every object truly requires completely fresh loading,then Prototype gives little or no benefit.
---

# Final One-Line Definition

> Prototype Pattern creates new objects by copying existing objects, avoiding repeated expensive initialization and object construction.
