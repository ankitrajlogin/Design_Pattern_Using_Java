# Proxy Design Pattern

The **Proxy Pattern** is a **Structural Design Pattern**.

It provides a **placeholder or middle object** that controls access to the real object.

Instead of directly calling the real object, the client talks to the **Proxy**, and the proxy decides:

* whether access should be allowed
* whether extra logic should run
* whether object creation should be delayed
* whether caching/logging/security should happen

---

# Real Life Analogy

Think about a **bank ATM**.

You do not directly access the bank server.

You interact with the ATM (Proxy).

The ATM:

* checks your card
* verifies PIN
* controls access
* then talks to the real bank system

So:

```text
You → ATM (Proxy) → Bank Server (Real Object)
```

---

# Why Do We Need Proxy Pattern?

Without proxy:

```text
Client → Real Object
```

Problem:

* No control
* No security
* No caching
* Expensive object may load immediately
* Logging becomes messy
* Duplicate code everywhere

Proxy solves this by inserting:

```text
Client → Proxy → Real Object
```

---

# Main Purpose of Proxy

Proxy acts as a:

* controller
* gatekeeper
* wrapper
* middle layer

---

# Types of Proxy

| Type             | Purpose                    |
| ---------------- | -------------------------- |
| Virtual Proxy    | Lazy loading heavy objects |
| Protection Proxy | Access control/security    |
| Remote Proxy     | Access remote object       |
| Caching Proxy    | Store repeated results     |
| Smart Proxy      | Logging/counting/tracking  |

---

# BAD CODE Example (Without Proxy)

Suppose we have a video streaming application.

Loading a video from server is expensive.

---

# Problem

Every time object creates:

* video loads immediately
* heavy memory usage
* no access control

---

# Bad Code

```java
interface Video {
    void play();
}

class RealVideo implements Video {

    private String fileName;

    public RealVideo(String fileName) {
        this.fileName = fileName;
        loadVideoFromServer();
    }

    private void loadVideoFromServer() {
        System.out.println("Loading video from server: " + fileName);
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + fileName);
    }
}

public class Main {
    public static void main(String[] args) {

        Video video1 = new RealVideo("movie.mp4");

        System.out.println("Object created");

        video1.play();
    }
}
```

---

# Output

```text
Loading video from server: movie.mp4
Object created
Playing video: movie.mp4
```

---

# Problem in This Code

Even if user never plays video:

```java
new RealVideo("movie.mp4");
```

still loads huge video.

This is wasteful.

---

# Solution → Proxy Pattern

We create:

```text
VideoProxy
```

which delays creation of real object until actually needed.

---

# GOOD CODE Using Proxy Pattern

# Step 1 — Common Interface

Both Real Object and Proxy must follow same interface.

```java
interface Video {
    void play();
}
```

---

# Step 2 — Real Object

Heavy object.

```java
class RealVideo implements Video {

    private String fileName;

    public RealVideo(String fileName) {
        this.fileName = fileName;
        loadVideoFromServer();
    }

    private void loadVideoFromServer() {
        System.out.println("Loading video from server: " + fileName);
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + fileName);
    }
}
```

---

# Step 3 — Proxy Object

This controls access.

```java
class VideoProxy implements Video {

    private String fileName;

    private RealVideo realVideo;

    public VideoProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void play() {

        // Lazy Loading
        if(realVideo == null) {
            realVideo = new RealVideo(fileName);
        }

        realVideo.play();
    }
}
```

---

# Step 4 — Client

```java
public class Main {

    public static void main(String[] args) {

        Video video = new VideoProxy("movie.mp4");

        System.out.println("Proxy created");

        System.out.println();

        video.play();

        System.out.println();

        video.play();
    }
}
```

---

# Output

```text
Proxy created

Loading video from server: movie.mp4
Playing video: movie.mp4

Playing video: movie.mp4
```

---

# Important Observation

Video loaded only once.

```java
if(realVideo == null)
```

This is called:

# Lazy Initialization

Object created only when actually needed.

---

# Internal Working Step-by-Step

# First Call

```java
video.play();
```

Flow:

```text
Client
   ↓
VideoProxy.play()
   ↓
realVideo == null
   ↓
new RealVideo()
   ↓
load video
   ↓
play video
```

---

# Second Call

```java
video.play();
```

Flow:

```text
Client
   ↓
VideoProxy.play()
   ↓
realVideo already exists
   ↓
directly play()
```

No reload happens.

---

# Structure of Proxy Pattern

```text
                ┌──────────────┐
                │   Client     │
                └──────┬───────┘
                       │
                       ▼
                ┌──────────────┐
                │    Proxy     │
                └──────┬───────┘
                       │
                       ▼
                ┌──────────────┐
                │ Real Object  │
                └──────────────┘
```

---

# Another Example — Security Proxy

Suppose:

* Admin can delete users
* Normal user cannot

---

# Without Proxy

```java
class DatabaseService {

    public void deleteUser() {
        System.out.println("User deleted");
    }
}
```

No protection.

Anyone can call it.

---

# With Proxy

```java
interface Database {
    void deleteUser();
}
```

---

# Real Service

```java
class DatabaseService implements Database {

    @Override
    public void deleteUser() {
        System.out.println("User deleted");
    }
}
```

---

# Proxy

```java
class DatabaseProxy implements Database {

    private String role;

    private DatabaseService databaseService;

    public DatabaseProxy(String role) {
        this.role = role;
        this.databaseService = new DatabaseService();
    }

    @Override
    public void deleteUser() {

        if(role.equals("ADMIN")) {
            databaseService.deleteUser();
        }
        else {
            System.out.println("Access Denied");
        }
    }
}
```

---

# Main

```java
public class Main {

    public static void main(String[] args) {

        Database db1 = new DatabaseProxy("USER");

        db1.deleteUser();

        System.out.println();

        Database db2 = new DatabaseProxy("ADMIN");

        db2.deleteUser();
    }
}
```

---

# Output

```text
Access Denied

User deleted
```

---

# Key Idea

Proxy:

* intercepts request
* decides what to do
* then forwards request

---

# Difference Between Proxy and Decorator

Students commonly confuse them.

| Proxy                       | Decorator                      |
| --------------------------- | ------------------------------ |
| Controls access             | Adds new behavior              |
| Focus on protection/control | Focus on feature enhancement   |
| Usually same behavior       | Usually enhanced behavior      |
| Example: security           | Example: adding milk to coffee |

---

# Difference Between Adapter and Proxy

| Adapter                         | Proxy                     |
| ------------------------------- | ------------------------- |
| Converts interface              | Controls access           |
| Makes incompatible classes work | Adds middle control layer |
| Focus on compatibility          | Focus on management       |

---

# When Proxy Pattern is Helpful

# 1. Lazy Loading

Heavy object creation delayed.

Example:

* images
* videos
* PDFs
* database connections

---

# 2. Security

Restrict access.

Example:

* admin panel
* payment APIs

---

# 3. Logging

Track calls.

Example:

```java
System.out.println("API called");
```

before forwarding request.

---

# 4. Caching

Store previous result.

Avoid repeated computation.

---

# 5. Remote Access

Object exists on another server.

Proxy handles networking.

---

# When Proxy Pattern is NOT Helpful

# 1. Very Simple System

Adding proxy may overcomplicate code.

---

# 2. No Access Control Needed

Direct object usage may be simpler.

---

# 3. Performance Critical Systems

Extra proxy layer may slightly increase overhead.

---

# 4. Too Many Proxies

Can make debugging difficult.

Flow becomes:

```text
Client → Proxy → Proxy → Proxy → Real Object
```

Hard to track.

---

# Advantages of Proxy Pattern

| Advantage              | Explanation               |
| ---------------------- | ------------------------- |
| Security               | Controls access           |
| Lazy Loading           | Saves memory/resources    |
| Better Performance     | Avoid unnecessary loading |
| Caching                | Faster repeated requests  |
| Logging                | Easier monitoring         |
| Separation of Concerns | Client remains clean      |

---

# Disadvantages

| Disadvantage     | Explanation         |
| ---------------- | ------------------- |
| Extra Complexity | More classes        |
| Extra Layer      | Slight overhead     |
| Harder Debugging | Indirect calls      |
| Maintenance Cost | More code to manage |

---

# Step-by-Step Implementation Guide

# Step 1

Create common interface.

```java
interface Service
```

---

# Step 2

Create Real Object.

```java
class RealService implements Service
```

---

# Step 3

Create Proxy implementing same interface.

```java
class ServiceProxy implements Service
```

---

# Step 4

Proxy holds reference to Real Object.

```java
private RealService realService;
```

---

# Step 5

Proxy controls access before forwarding call.

```java
realService.method();
```

---

# Full Mental Model

Proxy means:

```text
"Before reaching actual object,
go through me first."
```

The proxy may:

* block request
* delay request
* log request
* cache request
* secure request
* optimize request

then finally:

```text
forward to real object
```

---

# Interview Definition

> Proxy Pattern provides a surrogate or placeholder object that controls access to another object.

---

# Short Revision Notes

| Concept      | Meaning                         |
| ------------ | ------------------------------- |
| Proxy        | Middle layer                    |
| Real Object  | Actual implementation           |
| Client       | Uses proxy                      |
| Main Goal    | Control access                  |
| Common Use   | Lazy loading, security, caching |
| Pattern Type | Structural Pattern              |

---

# Final Understanding

Decorator:

```text
Adds new features
```

Adapter:

```text
Converts interface
```

Proxy:

```text
Controls access
```

That is the easiest way to remember all three patterns.
