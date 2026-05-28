# Memento Design Pattern — Complete Notes

# 1. What is Memento Pattern?

The **Memento Pattern** is a **Behavioral Design Pattern** used to:

> Save and restore an object's previous state without exposing its internal implementation details.

It allows us to:

* capture object snapshot
* store snapshot
* restore old snapshot later

without violating encapsulation.

---

# Simple Definition

> Memento Pattern stores object history so we can restore previous states later.

---

# Real-Life Analogy

---

# Example: Video Game Save

Suppose a game contains:

* level
* health
* weapons
* map position

You save the game.

Later:

* player dies
* loses progress

You press:

```text id="1skf6o"
Load Game
```

Game restores:

* previous health
* previous level
* previous weapons

That saved checkpoint is a:

# Memento

---

# Where Memento Pattern is Used

---

# Common Real-World Uses

| Application  | Usage            |
| ------------ | ---------------- |
| VS Code      | Undo/Redo        |
| IntelliJ     | Undo history     |
| Photoshop    | Image history    |
| Browser      | Session restore  |
| Games        | Save checkpoints |
| Database     | Rollback         |
| Transactions | Recovery         |
| Figma        | History tracking |

---

# Why We Need It

Suppose we have:

```java id="hrh15n"
class TextEditor {
    String content;
}
```

Without memento:

```java id="qpk55n"
String backup = editor.content;
```

Problems:

* breaks encapsulation
* exposes internal state
* difficult for complex objects
* unsafe

Memento solves this cleanly.

---

# Core Idea

Instead of external classes managing internal state:

```text id="n2lf3d"
Object itself creates snapshot
```

and later restores it.

---

# Main Participants

Memento pattern contains 3 important components.

---

# 1. Originator

The main object whose state we want to save.

Example:

* editor
* game
* bank account

Responsibilities:

* create memento
* restore memento

---

# 2. Memento

Stores snapshot/state.

Usually immutable.

Responsibilities:

* hold saved state

---

# 3. Caretaker

Manages history of mementos.

Responsibilities:

* store history
* manage undo/redo

Does NOT modify state directly.

---

# UML Structure

```text id="o9z88q"
+-------------------+
|    Originator     |
|    TextEditor     |
+-------------------+
| save()            |
| restore()         |
+-------------------+
         |
creates  |
         v
+-------------------+
|     Memento       |
|  EditorMemento    |
+-------------------+
| saved state       |
+-------------------+

+-------------------+
|    Caretaker      |
|  HistoryManager   |
+-------------------+
| stores snapshots  |
+-------------------+
```

---

# BAD DESIGN (Without Memento)

---

# Problem Example

```java id="5v2k2d"
class TextEditor {

    public String content = "";

    public void write(String text) {
        content += text;
    }
}
```

Main:

```java id="3z11mb"
String backup = editor.content;
```

---

# Problems

---

## 1. Encapsulation Breaks

Internal state exposed.

---

## 2. Security Problem

Anyone can modify:

```java id="8q90cs"
editor.content = "Hacked";
```

---

## 3. Difficult State Management

Complex objects become difficult to copy.

---

## 4. Tight Coupling

External code depends on internals.

---

# GOOD DESIGN (Using Memento)

---

# Step 1: Memento Class

```java id="ifom7j"
class EditorMemento {

    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
```

---

# Why final?

Memento should be immutable.

Saved history must not change.

---

# Step 2: Originator

```java id="0c0c1n"
class TextEditor {

    private String content = "";

    public void write(String text) {
        content += text;
    }

    public void show() {
        System.out.println(content);
    }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public void restore(EditorMemento memento) {
        content = memento.getContent();
    }
}
```

---

# Important Point

Only editor knows:

* how to save state
* how to restore state

Encapsulation maintained.

---

# Step 3: Caretaker

```java id="a5gajf"
import java.util.Stack;

class HistoryManager {

    private Stack<EditorMemento> undoStack = new Stack<>();
    private Stack<EditorMemento> redoStack = new Stack<>();

    private TextEditor editor;

    public HistoryManager(TextEditor editor) {
        this.editor = editor;
    }

    public void save() {
        undoStack.push(editor.save());

        // New changes invalidate redo
        redoStack.clear();
    }

    public void undo() {

        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }

        redoStack.push(editor.save());

        EditorMemento previousState = undoStack.pop();

        editor.restore(previousState);
    }

    public void redo() {

        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }

        undoStack.push(editor.save());

        EditorMemento nextState = redoStack.pop();

        editor.restore(nextState);
    }
}
```

---

# Step 4: Main Class

```java id="75nl9l"
public class Main {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();

        HistoryManager history = new HistoryManager(editor);

        history.save();

        editor.write("Hello ");
        history.save();

        editor.write("World ");
        history.save();

        editor.write("Java ");

        System.out.println("Current:");
        editor.show();

        history.undo();

        System.out.println("\nAfter Undo:");
        editor.show();

        history.undo();

        System.out.println("\nAfter Second Undo:");
        editor.show();

        history.redo();

        System.out.println("\nAfter Redo:");
        editor.show();
    }
}
```

---

# Output

```text id="kp9m2u"
Current:
Hello World Java

After Undo:
Hello World

After Second Undo:
Hello

After Redo:
Hello World
```

---

# Internal Undo/Redo Logic

---

# Undo Flow

When undo happens:

---

## Step 1

Current state goes to redo stack.

```text id="s1yrqd"
redo.push(current)
```

---

## Step 2

Previous state restored from undo stack.

```text id="9ghf54"
restore(undo.pop())
```

---

# Redo Flow

---

## Step 1

Current state pushed to undo stack.

```text id="v8txa5"
undo.push(current)
```

---

## Step 2

Redo state restored.

```text id="jj8ew8"
restore(redo.pop())
```

---

# Why New Action Clears Redo

Example:

```text id="ltukvc"
A -> B -> C
Undo -> B
Undo -> A
```

Now user types:

```text id="mwdj1j"
D
```

Timeline changes:

```text id="wy8uxv"
A -> D
```

So redo to:

* B
* C

must NOT happen.

Therefore:

```java id="smul2z"
redoStack.clear();
```

is mandatory.

---

# Important Design Discussion

---

# BAD Client Code

```java id="a8j2qv"
editor.restore(history.undo(editor.save()));
```

---

# Why Bad?

Client handles:

* saving
* undo logic
* restoring

This increases coupling.

---

# Better Design

```java id="6wrd6y"
history.undo();
```

History manager internally:

* saves state
* restores state
* manages stacks

Cleaner API.

---

# Coupling Discussion

---

# Tight Coupling (Bad)

```text id="0u5w56"
Main -> Editor
Main -> History
Main controls workflow
```

---

# Better Coupling

```text id="8o2m0o"
Main -> HistoryManager
HistoryManager -> Editor
```

Responsibilities centralized.

---

# Can Undo Be Inside Editor?

Possible:

```java id="qq3rxn"
editor.undo();
```

But not ideal.

Why?

Editor should focus on:

* editing

History should focus on:

* history management

Otherwise SRP breaks.

---

# Memento Pattern + SOLID Principles

---

# 1. SRP (Single Responsibility)

✔ Editor:

* manages content

✔ HistoryManager:

* manages history

---

# 2. OCP (Open Closed)

Can add:

* redo
* checkpoints
* autosave

without modifying editor heavily.

---

# 3. Encapsulation

Internal state hidden safely.

---

# Deep Copy vs Shallow Copy

VERY IMPORTANT INTERVIEW TOPIC.

---

# Problem

Suppose state contains:

```java id="73t6qb"
List<String> items;
```

If we do:

```java id="0f1z9f"
this.items = items;
```

then future modifications affect old snapshots.

---

# Example

```java id="jrd4o1"
items.add("New Data");
```

All mementos may change accidentally.

---

# Solution

Need:

# Deep Copy

Example:

```java id="d65qwp"
this.items = new ArrayList<>(items);
```

---

# Time Complexity

| Operation | Complexity |
| --------- | ---------- |
| Save      | O(1)       |
| Undo      | O(1)       |
| Redo      | O(1)       |

But deep copy may increase complexity.

---

# Advantages

---

# 1. Preserves Encapsulation

Internal state hidden.

---

# 2. Easy Undo/Redo

Perfect for editors and games.

---

# 3. Simplifies Rollback

Transaction recovery easier.

---

# 4. Cleaner History Management

Centralized logic.

---

# 5. Safer Design

No direct state access.

---

# Disadvantages

---

# 1. High Memory Usage

Too many snapshots consume memory.

---

# 2. Deep Copy Expensive

Large objects expensive to clone.

---

# 3. Performance Overhead

Frequent snapshots may slow system.

---

# 4. Complex for Huge Systems

Managing many states can become difficult.

---

# Real-World Systems

---

# VS Code

Every typing action:

* creates history snapshot

Undo restores previous snapshot.

---

# Photoshop

Every edit:

* image snapshot stored

Undo restores previous image state.

---

# Browser

Tabs and sessions restored using snapshots.

---

# Banking

Before transaction:

* account snapshot stored

If failure:

* rollback to old state.

---

# Memento vs Command Pattern

| Feature       | Memento          | Command         |
| ------------- | ---------------- | --------------- |
| Stores        | State            | Action          |
| Purpose       | Restore snapshot | Execute request |
| Undo Based On | Previous state   | Reverse action  |
| Example       | Game save        | Remote control  |

---

# Key Difference

---

# Memento

Stores:

```text id="s3lxvv"
WHAT object looked like
```

---

# Command

Stores:

```text id="j0wz0v"
WHAT operation happened
```

---

# Interview Questions

---

# Q1. Why use Memento Pattern?

To save and restore object state without exposing internal details.

---

# Q2. Why immutable memento?

To protect history from accidental modification.

---

# Q3. Why two stacks?

One for:

* undo history

One for:

* redo history

---

# Q4. Why clear redo stack?

Because new actions create a new timeline.

---

# Q5. Biggest disadvantage?

Memory overhead.

---

# Q6. Can Memento violate encapsulation?

No, if implemented correctly.

---

# Q7. What is caretaker role?

Stores and manages snapshots.

---

# Best Use Cases

Use when system requires:

✅ Undo/Redo
✅ Rollback
✅ Checkpoints
✅ Session restore
✅ History tracking
✅ Recovery mechanism

---

# When NOT To Use

Avoid when:

* object state huge
* snapshots expensive
* history unnecessary

---

# Final Interview Summary

> Memento Pattern is a behavioral design pattern used to capture and restore an object's previous state without exposing its internal structure. It consists of Originator, Memento, and Caretaker. It is widely used in undo-redo systems, games, IDEs, transaction rollback, and session recovery systems. Undo-redo is generally implemented using two stacks, where undo restores previous snapshots and redo restores undone snapshots.
