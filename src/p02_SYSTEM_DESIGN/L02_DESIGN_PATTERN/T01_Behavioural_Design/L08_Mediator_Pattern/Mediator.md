# Mediator Design Pattern

The **Mediator Pattern** is a **Behavioral Design Pattern**.

It is used when:

> Multiple objects communicate with each other in a complex way.

Instead of objects directly talking to each other,
they communicate through a central object called:

# Mediator

---

# Real-Life Example

## Air Traffic Control (ATC)

Imagine many airplanes.

Without ATC:

* Plane A talks to Plane B
* Plane B talks to Plane C
* Plane C talks to Plane D

Very chaotic.

Instead:

✅ Every airplane talks only to ATC
✅ ATC coordinates everything

Here:

* Airplanes = Colleagues
* ATC = Mediator

This is Mediator Pattern.

---

# Main Problem Without Mediator Pattern

Suppose we create a Chat Application.

Users:

* Alice
* Bob
* Charlie

Without mediator:

* Alice must know Bob
* Alice must know Charlie
* Bob must know everyone

This creates:

❌ Tight coupling
❌ Complex communication
❌ Difficult maintenance

---

# ❌ BAD CODE (Without Mediator Pattern)

```java id="2vvzv0"
class User {

    String name;

    User(String name) {
        this.name = name;
    }

    public void sendMessage(User user, String message) {

        System.out.println(
                this.name +
                " sends to " +
                user.name +
                ": " +
                message
        );
    }
}
```

---

# Main Class

```java id="apwwpi"
public class Main {

    public static void main(String[] args) {

        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        alice.sendMessage(bob, "Hello Bob");

        bob.sendMessage(charlie, "Hello Charlie");
    }
}
```

---

# Problems in This Code

---

# 1. Tight Coupling

Alice must know Bob directly.

```java id="2t9d2z"
alice.sendMessage(bob, ...)
```

Objects become dependent on each other.

---

# 2. Complex Network

If 100 users exist:

Every object may need references to many others.

Huge complexity.

---

# 3. Hard to Change Communication Logic

Suppose:

* add logging
* filtering
* moderation
* encryption

You must modify all objects.

---

# 4. Violates Single Responsibility Principle

User object now:

* stores user data
* handles communication logic

Too many responsibilities.

---

# Why Mediator Pattern?

Mediator Pattern centralizes communication.

Instead of:

```text id="3yvfoq"
User <--> User
```

we do:

```text id="9omkrt"
User --> Mediator <-- User
```

Now all communication goes through mediator.

---

# Core Idea

Objects should not communicate directly.

They should communicate through a mediator.

---

# Structure of Mediator Pattern

---

# 1. Mediator Interface

Defines communication methods.

Example:

```java id="2pr92y"
sendMessage()
```

---

# 2. Concrete Mediator

Actual mediator implementation.

Example:

* ChatRoom
* AirTrafficControl
* AuctionMediator

---

# 3. Colleague Classes

Objects that communicate.

Example:

* Users
* Airplanes
* Components

---

# Step-by-Step Implementation

---

# Step 1: Create Mediator Interface

```java id="oqm3ol"
interface ChatMediator {

    void sendMessage(String message, User sender);
}
```

---

# Step 2: Create Concrete Mediator

```java id="odujqs"
import java.util.ArrayList;
import java.util.List;

class ChatRoom implements ChatMediator {

    private List<User> users = new ArrayList<>();

    public void addUser(User user) {

        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {

        for(User user : users) {

            if(user != sender) {

                user.receiveMessage(message, sender);
            }
        }
    }
}
```

---

# Step 3: Create Colleague Class

```java id="5omrjg"
class User {

    private String name;

    private ChatMediator mediator;

    public User(String name, ChatMediator mediator) {

        this.name = name;

        this.mediator = mediator;
    }

    public String getName() {

        return name;
    }

    public void send(String message) {

        System.out.println(
                name + " sends: " + message
        );

        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message, User sender) {

        System.out.println(
                name +
                " received from " +
                sender.getName() +
                ": " +
                message
        );
    }
}
```

---

# Step 4: Main Class

```java id="wdl8go"
public class Main {

    public static void main(String[] args) {

        ChatRoom chatRoom = new ChatRoom();

        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.send("Hello everyone");

        bob.send("Hi Alice");
    }
}
```

---

# Output

```text id="j4n9t2"
Alice sends: Hello everyone

Bob received from Alice: Hello everyone

Charlie received from Alice: Hello everyone

Bob sends: Hi Alice

Alice received from Bob: Hi Alice

Charlie received from Bob: Hi Alice
```

---

# Internal Flow

When:

```java id="l3twfp"
alice.send("Hello")
```

it calls:

```java id="itvf5t"
mediator.sendMessage(...)
```

Mediator decides:

* who receives
* how message routed
* filtering
* broadcasting

Users don't know each other directly.

---

# Visual Flow

Without Mediator:

```text id="5m90eq"
Alice <--> Bob
Alice <--> Charlie
Bob <--> Charlie
```

Complex network.

---

With Mediator:

```text id="g0z4yq"
       ChatRoom
       /   |   \
      /    |    \
   Alice  Bob  Charlie
```

Cleaner.

---

# Important Understanding

Users are now:

✅ Loosely coupled
✅ Independent
✅ Easier to manage

---

# Real-World Examples

---

# 1. Air Traffic Control

Planes communicate through ATC.

---

# 2. Chat Application

Users communicate through server/chatroom.

---

# 3. GUI Frameworks

Button click updates:

* textbox
* label
* checkbox

through mediator/controller.

---

# 4. Auction System

Bidders communicate through auction mediator.

---

# 5. Smart Home System

Devices communicate through central hub.

---

# When to Use Mediator Pattern

Use it when:

✅ Many objects communicate
✅ Dependencies becoming complex
✅ Objects tightly coupled
✅ Centralized communication needed

---

# When NOT to Use

Do not use if:

❌ Communication is very simple
❌ Only 2 objects interact
❌ Mediator becomes unnecessarily huge

---

# Advantages

---

# 1. Reduces Coupling

Objects don't depend directly on each other.

---

# 2. Centralized Communication

All communication logic in one place.

---

# 3. Easier Maintenance

Changes only in mediator.

---

# 4. Better Reusability

Colleague objects reusable.

---

# 5. Cleaner Code

No complex object network.

---

# Disadvantages

---

# 1. Mediator Can Become God Object

Too much logic may accumulate.

---

# 2. Single Point of Complexity

Mediator may become very large.

---

# State Pattern vs Mediator Pattern

| State Pattern                          | Mediator Pattern                 |
| -------------------------------------- | -------------------------------- |
| Changes object behavior based on state | Centralizes object communication |
| Focus on state transitions             | Focus on interaction management  |
| Objects change internally              | Objects communicate externally   |
| Example: ATM states                    | Example: Chat room               |

---

# Internal Architecture

```text id="wdj95o"
      Mediator
          |
   ----------------
   |      |       |
 User1  User2  User3
```

---

# Very Important Interview Point

## Goal of Mediator Pattern

Reduce direct dependencies between objects.

---

# Simple One-Line Definition

> Mediator Pattern defines a central object that manages communication between multiple objects, reducing direct coupling between them.

---

# Quick Revision Notes

## Category

Behavioral Pattern

---

## Main Purpose

Centralize communication.

---

## Key Components

* Mediator Interface
* Concrete Mediator
* Colleague Classes

---

## Main Benefit

Loose coupling between communicating objects.

---

# Simple Analogy

Think of:

* Employees do not directly contact CEO of every department.
* They communicate through manager/coordinator.

Coordinator = Mediator.
