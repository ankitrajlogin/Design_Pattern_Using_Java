# Observer Design Pattern — Complete Notes

# 1. What is Observer Pattern?

The **Observer Pattern** is a **Behavioral Design Pattern** in which:

> One object (Subject) automatically notifies multiple dependent objects (Observers) whenever its state changes.

---

# Simple Definition

> Observer Pattern creates a **one-to-many dependency** between objects.

Meaning:

```text id="kg7s8g"
One object changes
→ Many objects get notified automatically
```

---

# Real-Life Analogy

# YouTube Channel Subscription

Suppose:

* You subscribe to a YouTube channel

When creator uploads video:

* all subscribers get notification automatically

---

# Here

| Real World      | Observer Pattern |
| --------------- | ---------------- |
| YouTube Channel | Subject          |
| Subscribers     | Observers        |
| Notification    | update()         |
| Subscribe       | addObserver()    |
| Unsubscribe     | removeObserver() |

---

# Another Real-Life Examples

* Instagram followers notifications
* Weather app updates
* Stock market alerts
* WhatsApp group messages
* Cricket score updates
* Event listeners in UI
* Kafka consumers
* RabbitMQ subscribers

---

# Why We Need Observer Pattern

Suppose we build a weather station.

When temperature changes:

* mobile app should update
* TV display should update
* website should update

Without observer pattern:

```java id="ndm9ck"
mobile.update(temp);
tv.update(temp);
website.update(temp);
```

Problems:

* tight coupling
* hardcoded dependencies
* difficult to add/remove clients

Observer pattern solves this.

---

# Core Idea

Instead of subject manually handling each dependent:

```text id="2ah6ah"
Observers register themselves
```

When state changes:

```text id="v5l6m0"
Subject automatically notifies all observers
```

---

# Main Components

---

# 1. Subject (Publisher)

Main object being observed.

Responsibilities:

* store observers
* add/remove observers
* notify observers

Examples:

* weather station
* YouTube channel
* stock market

---

# 2. Observer (Subscriber)

Objects interested in updates.

Responsibilities:

* receive updates

Examples:

* mobile app
* subscriber
* display screen

---

# UML Structure

```text id="fcl9ha"
+-------------------+
|      Subject      |
+-------------------+
| addObserver()     |
| removeObserver()  |
| notifyObservers() |
+-------------------+
          |
          | notifies
          v

+-------------------+
|     Observer      |
+-------------------+
| update()          |
+-------------------+
```

---

# BAD DESIGN (Without Observer Pattern)

---

# Problem Example

```java id="t9qz4h"
class WeatherStation {

    private int temperature;

    MobileDisplay mobile;
    TVDisplay tv;

    public void setTemperature(int temp) {

        this.temperature = temp;

        mobile.update(temp);
        tv.update(temp);
    }
}
```

---

# Problems

---

# 1. Tight Coupling

WeatherStation directly depends on:

* MobileDisplay
* TVDisplay

---

# 2. Difficult to Extend

Adding new display requires modifying WeatherStation.

Violates:

# Open Closed Principle

---

# 3. Hardcoded Dependencies

Not flexible.

---

# 4. Reusability Poor

WeatherStation tied to specific classes.

---

# SOLUTION → Observer Pattern

Now observers register dynamically.

---

# Step 1: Create Observer Interface

```java id="8e8n7n"
interface Observer {

    void update(int temperature);
}
```

---

# Why Interface?

Because subject should not depend on concrete classes.

It should depend on abstraction.

---

# Step 2: Create Subject Interface

```java id="xw0f38"
interface Subject {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
```

---

# Step 3: Concrete Subject

```java id="h65tq0"
import java.util.ArrayList;
import java.util.List;

class WeatherStation implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int temperature;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature;

        notifyObservers();
    }
}
```

---

# Important Point

WeatherStation does NOT know:

* MobileDisplay
* TVDisplay

It only knows:

```java id="kr7vyq"
Observer
```

This reduces coupling.

---

# Step 4: Concrete Observers

---

# Mobile Display

```java id="6n5qv5"
class MobileDisplay implements Observer {

    @Override
    public void update(int temperature) {

        System.out.println(
            "Mobile Display Updated: " + temperature
        );
    }
}
```

---

# TV Display

```java id="hjf97w"
class TVDisplay implements Observer {

    @Override
    public void update(int temperature) {

        System.out.println(
            "TV Display Updated: " + temperature
        );
    }
}
```

---

# Step 5: Main Class

```java id="j3z8vw"
public class Main {

    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();

        Observer mobile = new MobileDisplay();
        Observer tv = new TVDisplay();

        weatherStation.addObserver(mobile);
        weatherStation.addObserver(tv);

        weatherStation.setTemperature(30);

        weatherStation.setTemperature(40);
    }
}
```

---

# Output

```text id="yn8w93"
Mobile Display Updated: 30
TV Display Updated: 30

Mobile Display Updated: 40
TV Display Updated: 40
```

---

# Internal Flow

---

# Step 1

Observers subscribe:

```text id="54gb7m"
Mobile subscribes
TV subscribes
```

---

# Step 2

Temperature changes:

```text id="rmdmt9"
setTemperature(30)
```

---

# Step 3

Subject calls:

```text id="40m7ei"
notifyObservers()
```

---

# Step 4

Each observer gets update:

```text id="ol4jgr"
observer.update(30)
```

---

# Push vs Pull Model

VERY IMPORTANT INTERVIEW TOPIC.

---

# 1. Push Model

Subject sends data directly.

```java id="tw0w6m"
update(int temp)
```

Observer receives changed data.

---

# 2. Pull Model

Observer fetches data from subject.

```java id="9b8jmg"
update()
```

Then:

```java id="g8d3y5"
subject.getTemperature()
```

---

# Which is Better?

---

# Push Model

Advantages:

* simple
* efficient

Disadvantages:

* unnecessary data may be pushed

---

# Pull Model

Advantages:

* observer fetches only required data

Disadvantages:

* observer tightly coupled with subject

---

# Real-World Examples

---

# 1. YouTube Notifications

Channel uploads:

* subscribers notified

---

# 2. Stock Market App

Stock price changes:

* investors notified

---

# 3. Event Listener in JavaScript

```javascript id="s2g2hb"
button.addEventListener("click", handler)
```

Observer pattern.

---

# 4. Spring Event System

Events published:

* listeners notified

---

# 5. Kafka / RabbitMQ

Producer publishes:

* subscribers consume

---

# Coupling Discussion

---

# BAD Coupling

```text id="h0m38k"
WeatherStation -> MobileDisplay
WeatherStation -> TVDisplay
```

Direct dependency.

---

# GOOD Coupling

```text id="t9qu4n"
WeatherStation -> Observer Interface
```

Loose coupling.

---

# Why Loose Coupling Matters

Now we can add:

```java id="mhvqgf"
LaptopDisplay
WatchDisplay
LEDDisplay
```

WITHOUT changing WeatherStation.

---

# SOLID Principles

---

# 1. Open Closed Principle

Add new observers without modifying subject.

---

# 2. Dependency Inversion Principle

Subject depends on abstraction:

```java id="jlwmqb"
Observer
```

not concrete classes.

---

# 3. Single Responsibility Principle

Subject:

* manages state

Observer:

* handles updates

---

# Dynamic Subscription

Observers can join/leave anytime.

Example:

```java id="3hk7of"
weatherStation.removeObserver(tv);
```

Now TV stops receiving updates.

---

# Advantages

---

# 1. Loose Coupling

Subject independent of concrete observers.

---

# 2. Dynamic Relationships

Observers added/removed runtime.

---

# 3. Extensible

Easy to add new observers.

---

# 4. Reusable

Subject reusable across systems.

---

# 5. Event-Driven Architecture

Foundation of modern event systems.

---

# Disadvantages

---

# 1. Memory Leak Risk

Forgotten observers may remain subscribed.

---

# 2. Unexpected Updates

Too many notifications may create bugs.

---

# 3. Performance Issues

Large number of observers:

* expensive notifications

---

# 4. Difficult Debugging

Chain of updates hard to trace.

---

# Observer vs Pub-Sub

INTERVIEW IMPORTANT.

---

# Observer Pattern

Usually:

* direct dependency
* same application memory

Example:

* Java UI listeners

---

# Publish Subscribe

Uses:

* message broker
* Kafka
* RabbitMQ

Publisher and subscriber fully decoupled.

---

# Observer vs Strategy

| Feature  | Observer     | Strategy            |
| -------- | ------------ | ------------------- |
| Purpose  | Notification | Algorithm selection |
| Relation | One-to-many  | One-to-one          |
| Trigger  | State change | Runtime behavior    |
| Example  | Subscribers  | Payment strategy    |

---

# Observer vs Mediator

| Observer                              | Mediator                             |
| ------------------------------------- | ------------------------------------ |
| Observers communicate through subject | Objects communicate through mediator |
| Event notification                    | Centralized communication            |

---

# Java Built-In Observer

Older Java had:

```java id="t0d70o"
Observable
Observer
```

But deprecated now.

Modern systems use:

* Event listeners
* Reactive streams
* Spring events

---

# Advanced Real-World Concepts

---

# 1. Asynchronous Notification

Observers updated asynchronously using:

* threads
* queues
* executors

---

# 2. Event Filtering

Observer listens only for specific events.

---

# 3. Priority Observers

Some observers notified first.

---

# 4. Weak References

Avoid memory leaks.

---

# Interview Questions

---

# Q1. What problem does Observer solve?

Automatic notification to multiple dependent objects when state changes.

---

# Q2. Why is Observer loosely coupled?

Because subject depends only on observer abstraction.

---

# Q3. Difference between push and pull model?

Push sends data directly.
Pull allows observer to fetch data.

---

# Q4. Biggest disadvantage?

Unexpected updates and memory leaks.

---

# Q5. Real-world use cases?

* notifications
* event systems
* stock market
* messaging systems

---

# Q6. Which SOLID principles used?

* OCP
* DIP
* SRP

---

# Best Use Cases

Use Observer Pattern when:

✅ Multiple objects depend on one object
✅ Need automatic notifications
✅ Need event-driven systems
✅ Need loose coupling
✅ Runtime subscriptions needed

---

# When NOT To Use

Avoid when:

* only one dependent object exists
* notification overhead expensive
* updates extremely frequent

---

# Final Interview Summary

> Observer Pattern is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object changes state, all dependent objects are notified automatically. It consists of Subject and Observer. It promotes loose coupling and is widely used in event systems, notifications, UI listeners, stock market systems, and messaging architectures.
