# Facade Design Pattern

The **Facade Pattern** is a **Structural Design Pattern**.

It provides a:

```text id="x9h5nl"
Simple interface to a complex system
```

Instead of client talking to many complicated classes directly,
client talks to:

```text id="gx8p38"
Facade
```

and facade internally manages everything.

---

# Main Idea

Suppose starting a computer requires:

* CPU start
* Memory load
* Hard disk initialize
* OS boot

Without facade:

Client must know everything.

Facade hides complexity.

---

# Real Life Analogy

# Restaurant Waiter

In restaurant:

Do you directly go to:

* chef
* cashier
* kitchen
* cleaning staff

No.

You talk to:

```text id="trksn3"
Waiter
```

Waiter is the Facade.

Waiter:

* accepts request
* communicates internally
* returns final result

You see only simple interface.

---

# Why We Need Facade Pattern

Without facade:

Client handles:

* multiple objects
* complex sequence
* dependencies
* initialization order

This creates:

* tightly coupled code
* difficult usage
* repetitive logic

Facade solves this by:

```text id="uq6mcl"
Providing one simplified entry point
```

---

# Main Goal

Hide system complexity behind a simple interface.

---

# BAD CODE Example (Without Facade)

Suppose we build a Home Theater System.

To watch movie:

Need:

* projector ON
* speakers ON
* lights OFF
* streaming device ON

---

# Bad Code

```java id="j2m6gs"
class Projector {

    public void on() {
        System.out.println("Projector ON");
    }
}
```

---

```java id="c4u5u7"
class Speakers {

    public void on() {
        System.out.println("Speakers ON");
    }
}
```

---

```java id="r9rwtt"
class Lights {

    public void dim() {
        System.out.println("Lights DIM");
    }
}
```

---

```java id="p4j9w7"
class StreamingDevice {

    public void on() {
        System.out.println("Streaming Device ON");
    }

    public void playMovie(String movie) {
        System.out.println("Playing: " + movie);
    }
}
```

---

# Client Code

```java id="1kct5o"
public class Main {

    public static void main(String[] args) {

        Projector projector =
                new Projector();

        Speakers speakers =
                new Speakers();

        Lights lights =
                new Lights();

        StreamingDevice device =
                new StreamingDevice();

        projector.on();

        speakers.on();

        lights.dim();

        device.on();

        device.playMovie("Avengers");
    }
}
```

---

# Problems

# 1. Client Knows Too Much

Client knows:

* all classes
* order of execution
* dependencies

---

# 2. Complex Usage

Every user must repeat same setup.

---

# 3. Tight Coupling

Client tightly coupled with subsystem.

---

# 4. Difficult Maintenance

If system changes:

* many client files affected

---

# Solution → Facade Pattern

Create one class:

```text id="t9s9kr"
HomeTheaterFacade
```

Client talks only to facade.

---

# GOOD CODE Using Facade Pattern

# Step 1 — Subsystem Classes

---

```java id="j0h12r"
class Projector {

    public void on() {
        System.out.println("Projector ON");
    }
}
```

---

```java id="nnj0qm"
class Speakers {

    public void on() {
        System.out.println("Speakers ON");
    }
}
```

---

```java id="s1qxdx"
class Lights {

    public void dim() {
        System.out.println("Lights DIM");
    }
}
```

---

```java id="7h91q3"
class StreamingDevice {

    public void on() {
        System.out.println("Streaming Device ON");
    }

    public void playMovie(String movie) {
        System.out.println("Playing movie: " + movie);
    }
}
```

---

# Step 2 — Create Facade

```java id="03zwy0"
class HomeTheaterFacade {

    private Projector projector;

    private Speakers speakers;

    private Lights lights;

    private StreamingDevice device;

    public HomeTheaterFacade(
            Projector projector,
            Speakers speakers,
            Lights lights,
            StreamingDevice device) {

        this.projector = projector;
        this.speakers = speakers;
        this.lights = lights;
        this.device = device;
    }

    public void watchMovie(String movie) {

        System.out.println("Starting Home Theater");

        projector.on();

        speakers.on();

        lights.dim();

        device.on();

        device.playMovie(movie);
    }
}
```

---

# Step 3 — Client Code

```java id="6n4bn9"
public class Main {

    public static void main(String[] args) {

        Projector projector =
                new Projector();

        Speakers speakers =
                new Speakers();

        Lights lights =
                new Lights();

        StreamingDevice device =
                new StreamingDevice();

        HomeTheaterFacade facade =
                new HomeTheaterFacade(
                        projector,
                        speakers,
                        lights,
                        device
                );

        facade.watchMovie("Avengers");
    }
}
```

---

# Output

```text id="lkv7r9"
Starting Home Theater

Projector ON
Speakers ON
Lights DIM
Streaming Device ON
Playing movie: Avengers
```

---

# Important Observation

Client now uses:

```java id="y2zv19"
facade.watchMovie()
```

instead of:

```text id="6yte1g"
10 different subsystem calls
```

---

# Internal Flow

```text id="xll4f0"
Client
   ↓
Facade
   ↓
Subsystem Classes
```

---

# Structure Diagram

```text id="czvlpw"
             Client
                ↓
            Facade
        ↙    ↓     ↘
    ClassA ClassB ClassC
```

---

# Key Understanding

Facade does NOT add:

* new behavior
* access control
* hierarchy

It only:

```text id="3ly87y"
simplifies usage
```

---

# Real World Examples

| Example               | Facade                            |
| --------------------- | --------------------------------- |
| Restaurant waiter     | Handles all operations            |
| Spring Boot Starter   | Simplifies configurations         |
| Computer Power Button | Starts many internal components   |
| Banking App           | One API for many services         |
| Travel Booking        | Handles hotel + flight + cab      |
| Compiler              | Simple compile() hides many steps |

---

# Another Example — Computer Startup

Without facade:

```java id="1n7pdg"
cpu.start();
memory.load();
hardDrive.read();
os.boot();
```

With facade:

```java id="g2oq5h"
computer.start();
```

Simple.

---

# Step-by-Step Implementation Guide

# Step 1

Identify complex subsystem.

---

# Step 2

Create subsystem classes.

---

# Step 3

Create facade class.

---

# Step 4

Facade internally manages subsystem calls.

---

# Step 5

Client talks only to facade.

---

# Important Difference

Facade does NOT hide subsystem completely.

Advanced users can still directly use subsystem classes if needed.

Facade only provides:

* simpler interface

---

# Facade vs Adapter

| Facade               | Adapter                               |
| -------------------- | ------------------------------------- |
| Simplifies interface | Converts interface                    |
| Works with subsystem | Makes incompatible classes compatible |
| Focus on usability   | Focus on compatibility                |

---

# Facade vs Proxy

| Facade             | Proxy                     |
| ------------------ | ------------------------- |
| Simplifies usage   | Controls access           |
| Client convenience | Access management         |
| Hides complexity   | Adds middle control layer |

---

# Facade vs Decorator

| Facade               | Decorator           |
| -------------------- | ------------------- |
| Simplifies subsystem | Adds behavior       |
| One entry point      | Dynamic enhancement |

---

# When Facade Pattern is Helpful

# 1. Complex System Exists

Many classes working together.

---

# 2. Repeated Setup Logic

Same sequence repeated everywhere.

---

# 3. Want Loose Coupling

Client should not know subsystem details.

---

# 4. Simplified API Needed

Good for libraries/frameworks.

---

# When Facade Pattern is NOT Helpful

# 1. Very Small System

Facade unnecessary overhead.

---

# 2. Client Needs Full Control

Facade may hide too much flexibility.

---

# 3. Too Many Responsibilities

Huge facade may become:

```text id="xsm0s8"
God Class
```

---

# Advantages

| Advantage              | Explanation                 |
| ---------------------- | --------------------------- |
| Simpler Usage          | Easy API                    |
| Loose Coupling         | Client independent          |
| Cleaner Code           | Less repetition             |
| Better Maintainability | Centralized logic           |
| Easier Learning        | User sees simplified system |

---

# Disadvantages

| Disadvantage          | Explanation                 |
| --------------------- | --------------------------- |
| Extra Layer           | More abstraction            |
| Can Become God Object | Too much logic              |
| Reduced Flexibility   | May hide subsystem features |

---

# Mental Model

Facade means:

```text id="b7ubjt"
"Don't talk to entire system directly.
Talk to me.
I will handle everything."
```

---

# Interview Definition

> Facade Pattern provides a unified and simplified interface to a set of interfaces in a subsystem.

---

# Final Core Understanding

Without Facade:

```text id="abwwkp"
Client → Many Complex Classes
```

With Facade:

```text id="q7bmrk"
Client → Facade → Complex System
```

Facade's job is:

```text id="a4j1fx"
Reduce complexity for client
```

That is the complete essence of the Facade Pattern.
