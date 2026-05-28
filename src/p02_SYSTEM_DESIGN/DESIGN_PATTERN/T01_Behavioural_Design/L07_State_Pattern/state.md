# State Design Pattern

The **State Design Pattern** is a **Behavioral Design Pattern**.

It is used when:

> An object changes its behavior based on its internal state.

Instead of writing large `if-else` or `switch` conditions everywhere, we move each state behavior into separate classes.

---

# Real-Life Example

Think about a **Mobile Phone**.

A phone can be in different states:

* Silent Mode
* Vibrate Mode
* Ringing Mode
* Power Off Mode

When you receive a call:

* In Silent → no sound
* In Vibrate → vibration
* In Ringing → ringtone
* In Power Off → nothing

Same action (`incomingCall()`), different behavior based on state.

That is exactly what State Pattern solves.

---

# Main Problem Without State Pattern

Suppose we are creating a Music Player.

States:

* Playing
* Paused
* Stopped

Actions:

* play()
* pause()
* stop()

---

# ❌ BAD CODE (Without State Pattern)

```java
class MusicPlayer {

    private String state = "STOPPED";

    public void play() {

        if(state.equals("STOPPED")) {
            System.out.println("Starting music");
            state = "PLAYING";
        }
        else if(state.equals("PAUSED")) {
            System.out.println("Resuming music");
            state = "PLAYING";
        }
        else if(state.equals("PLAYING")) {
            System.out.println("Already playing");
        }
    }

    public void pause() {

        if(state.equals("PLAYING")) {
            System.out.println("Pausing music");
            state = "PAUSED";
        }
        else {
            System.out.println("Cannot pause");
        }
    }

    public void stop() {

        if(state.equals("PLAYING") || state.equals("PAUSED")) {
            System.out.println("Stopping music");
            state = "STOPPED";
        }
        else {
            System.out.println("Already stopped");
        }
    }
}
```

---

# Problems in This Code

## 1. Too Many if-else Conditions

Every method checks state manually.

```java
if(state.equals(...))
```

This becomes huge later.

---

## 2. Violates Open Closed Principle

If new state comes:

* BUFFERING
* FAST_FORWARD
* LOCKED

You must modify existing code.

Dangerous.

---

## 3. Hard to Maintain

Logic of all states is mixed together.

---

## 4. State Transition Logic Everywhere

Transitions are scattered.

Very difficult in large systems.

---

# Why State Pattern?

State Pattern helps:

✅ Remove huge if-else blocks
✅ Separate behavior by state
✅ Make code clean and maintainable
✅ Easily add new states
✅ Follow SOLID principles
✅ Make transitions controlled and clean

---

# Core Idea

Instead of:

```java
if(state == PLAYING)
```

We do:

```java
currentState.play();
```

Each state object decides behavior itself.

---

# Structure of State Pattern

## 1. Context

Main object whose behavior changes.

Example:

* MusicPlayer
* VendingMachine
* ATM

---

## 2. State Interface

Defines all actions.

Example:

```java
play()
pause()
stop()
```

---

## 3. Concrete States

Actual implementations:

* PlayingState
* PausedState
* StoppedState

Each state handles behavior differently.

---

# Step-by-Step Implementation

---

# Step 1: Create State Interface

```java
interface State {

    void play(MusicPlayer player);

    void pause(MusicPlayer player);

    void stop(MusicPlayer player);
}
```

Notice:

```java
MusicPlayer player
```

We pass context object so state can change it.

---

# Step 2: Create Concrete States

---

# PlayingState

```java
class PlayingState implements State {

    @Override
    public void play(MusicPlayer player) {

        System.out.println("Already playing");
    }

    @Override
    public void pause(MusicPlayer player) {

        System.out.println("Pausing music");

        player.setState(new PausedState());
    }

    @Override
    public void stop(MusicPlayer player) {

        System.out.println("Stopping music");

        player.setState(new StoppedState());
    }
}
```

---

# PausedState

```java
class PausedState implements State {

    @Override
    public void play(MusicPlayer player) {

        System.out.println("Resuming music");

        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player) {

        System.out.println("Already paused");
    }

    @Override
    public void stop(MusicPlayer player) {

        System.out.println("Stopping music");

        player.setState(new StoppedState());
    }
}
```

---

# StoppedState

```java
class StoppedState implements State {

    @Override
    public void play(MusicPlayer player) {

        System.out.println("Starting music");

        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player) {

        System.out.println("Cannot pause. Music already stopped");
    }

    @Override
    public void stop(MusicPlayer player) {

        System.out.println("Already stopped");
    }
}
```

---

# Step 3: Create Context Class

```java
class MusicPlayer {

    private State currentState;

    public MusicPlayer() {

        currentState = new StoppedState();
    }

    public void setState(State state) {

        this.currentState = state;
    }

    public void play() {

        currentState.play(this);
    }

    public void pause() {

        currentState.pause(this);
    }

    public void stop() {

        currentState.stop(this);
    }
}
```

---

# Step 4: Main Class

```java
public class Main {

    public static void main(String[] args) {

        MusicPlayer player = new MusicPlayer();

        player.play();

        player.pause();

        player.play();

        player.stop();

        player.stop();
    }
}
```

---

# Output

```text
Starting music
Pausing music
Resuming music
Stopping music
Already stopped
```

---

# What Happened Internally?

Initially:

```text
StoppedState
```

When:

```java
player.play();
```

It calls:

```java
currentState.play(this)
```

Since current state is `StoppedState`,
its play() method executes.

Then state changes:

```java
player.setState(new PlayingState());
```

Now object behavior changes automatically.

---

# Visual Flow

```text
StoppedState
     |
     v
PlayingState
     |
     v
PausedState
     |
     v
PlayingState
     |
     v
StoppedState
```

---

# Important Understanding

The object itself does not contain huge logic.

Instead:

* Each state contains its own behavior
* Each state knows valid transitions

This is the biggest advantage.

---

# State Pattern vs Strategy Pattern

Many students confuse these.

| State Pattern                            | Strategy Pattern                        |
| ---------------------------------------- | --------------------------------------- |
| Changes behavior based on internal state | Changes algorithm based on choice       |
| States can change themselves             | Strategy usually does not change itself |
| Object transitions automatically         | User/client selects strategy            |
| Focus on state transition                | Focus on interchangeable algorithms     |

---

# Real-World Examples

---

# 1. ATM Machine

States:

* NoCardState
* HasCardState
* PinVerifiedState
* OutOfCashState

Behavior changes based on current state.

---

# 2. Vending Machine

States:

* NoCoinState
* HasCoinState
* DispensingState

---

# 3. Traffic Signal

States:

* Red
* Yellow
* Green

---

# 4. Order Management

States:

* Ordered
* Packed
* Shipped
* Delivered
* Cancelled

---

# When to Use State Pattern

Use it when:

✅ Object behavior changes based on state
✅ Too many if-else or switch conditions
✅ State transitions are complex
✅ Many state-specific behaviors exist

---

# When NOT to Use

Do not use if:

❌ Only 2-3 simple conditions exist
❌ State transitions are tiny/simple
❌ Adds unnecessary complexity

---

# Advantages

## 1. Cleaner Code

No giant conditions.

---

## 2. Better Maintainability

Each state isolated.

---

## 3. Easily Extendable

Add new state without touching old code.

---

## 4. Follows Open Closed Principle

Open for extension.

Closed for modification.

---

## 5. Better Readability

State behavior clearly separated.

---

# Disadvantages

## 1. More Classes

Each state becomes separate class.

---

## 2. Can Become Complex

If too many tiny states exist.

---

# Internal Architecture

```text
        Context
           |
           v
      State Interface
       /     |      \
      /      |       \
Playing  Paused   Stopped
```

---

# Very Important Interview Point

## Who changes the state?

Usually:

```text
Current State changes the next state
```

Example:

```java
player.setState(new PlayingState());
```

This makes transitions controlled and encapsulated.

---

# Another Example (ATM)

Without State Pattern:

```java
if(hasCard) {
   if(pinCorrect) {
      withdraw();
   }
}
```

With State Pattern:

```java
currentState.withdraw();
```

Cleaner and scalable.

---

# Quick Revision Notes

## Purpose

Allow object behavior to change when internal state changes.

---

## Category

Behavioral Design Pattern

---

## Key Components

* Context
* State Interface
* Concrete States

---

## Main Benefit

Removes complex conditional logic.

---

# Simple One-Line Definition

> State Pattern allows an object to behave differently when its internal state changes, by representing each state as a separate class.
