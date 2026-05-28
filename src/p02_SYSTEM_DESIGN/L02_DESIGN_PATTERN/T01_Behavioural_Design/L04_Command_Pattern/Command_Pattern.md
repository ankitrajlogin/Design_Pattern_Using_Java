# Command Design Pattern — Complete Proper Notes (Real-World Understanding)

# 1. What is Command Pattern?

The **Command Pattern** is a **Behavioral Design Pattern** where:

> A request/action is converted into an object.

Instead of directly calling methods:

```java id="ah3m9r"
editor.write("Hello");
```

we wrap operation inside a command object:

```java id="2v9ps1"
Command cmd =
    new WriteCommand(editor, "Hello");
```

and later execute:

```java id="34d1qb"
cmd.execute();
```

---

# Simple Definition

> Command Pattern encapsulates requests/actions into objects.

---

# Main Goal

The main purpose is:

# Decouple

```text id="fg6a0d"
Who sends request
```

from

```text id="cpg4bt"
Who performs request
```

---

# Real Meaning of Command Pattern

Command Pattern is NOT mainly about:

* remote controls
* buttons

Those are beginner examples.

The REAL meaning is:

# "Represent user/system actions as objects."

Examples:

* write text
* delete text
* crop image
* rotate image
* play song
* pause video
* rollback transaction

---

# Why We Need Command Pattern

Suppose we are building:

* VS Code
* Photoshop
* IntelliJ
* Figma

User actions should support:

* undo
* redo
* history
* replay
* scheduling

Without Command Pattern this becomes messy.

---

# Core Components

There are 5 important participants.

---

# 1. Command Interface

Common contract.

Usually contains:

```java id="nq9b6m"
execute()
undo()
```

---

# 2. Concrete Commands

Actual operations.

Examples:

* WriteCommand
* DeleteCommand
* RotateCommand
* CropCommand

---

# 3. Receiver

Actual business object.

Examples:

* TextEditor
* ImageEditor
* MusicPlayer

---

# 4. Invoker

Triggers command execution.

Examples:

* toolbar
* menu
* keyboard shortcut handler

---

# 5. Client

Creates and wires objects.

Usually:

* UI
* Main class
* framework

---

# UML Structure

```text id="twm9s9"
+-------------------+
|      Invoker      |
|   Toolbar/Menu    |
+-------------------+
| executeCommand()  |
| undo()            |
+-------------------+
          |
          v

+-------------------+
|      Command      |
+-------------------+
| execute()         |
| undo()            |
+-------------------+
      /        \
     /          \
    v            v

+---------------+  +----------------+
| WriteCommand  |  | RotateCommand  |
+---------------+  +----------------+

          |
          v

+-------------------+
|     Receiver      |
| TextEditor/Image  |
+-------------------+
```

---

# REAL-WORLD APPLICATION 1 → VS CODE

# Text Editor Example

This is one of the BEST examples of Command Pattern.

---

# Problem

User can:

* write text
* delete text
* copy
* paste
* undo
* redo

---

# Receiver

```text id="0vjlwm"
TextEditor
```

because actual work happens there.

---

# Example Commands

| User Action | Command       |
| ----------- | ------------- |
| Type text   | WriteCommand  |
| Delete text | DeleteCommand |
| Paste text  | PasteCommand  |

---

# FLOW

---

# User Types "Hello"

UI creates:

```java id="jlwm15"
new WriteCommand(editor, "Hello")
```

---

# Invoker Executes

```java id="jlwm0t"
command.execute()
```

---

# Internally

```java id="jlwm1y"
editor.write("Hello")
```

---

# Undo

```java id="jlwmzr"
editor.delete(5)
```

---

# IMPORTANT UNDERSTANDING

Here command stores:

# Text operations

---

# COMPLETE CODE

---

# Receiver

```java id="jlwmym"
class TextEditor {

    private StringBuilder text =
            new StringBuilder();

    public void write(String str) {

        text.append(str);
    }

    public void delete(int count) {

        int start =
                text.length() - count;

        text.delete(start, text.length());
    }

    public String getText() {

        return text.toString();
    }
}
```

---

# Command Interface

```java id="jlwmx5"
interface Command {

    void execute();

    void undo();
}
```

---

# Write Command

```java id="jlwmrm"
class WriteCommand implements Command {

    private TextEditor editor;

    private String text;

    public WriteCommand(
            TextEditor editor,
            String text
    ) {
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {

        editor.write(text);
    }

    @Override
    public void undo() {

        editor.delete(text.length());
    }
}
```

---

# Delete Command

```java id="jlwmf0"
class DeleteCommand implements Command {

    private TextEditor editor;

    private int count;

    private String deletedText;

    public DeleteCommand(
            TextEditor editor,
            int count
    ) {
        this.editor = editor;
        this.count = count;
    }

    @Override
    public void execute() {

        String current =
                editor.getText();

        deletedText =
                current.substring(
                        current.length() - count
                );

        editor.delete(count);
    }

    @Override
    public void undo() {

        editor.write(deletedText);
    }
}
```

---

# Invoker

```java id="jlwmk2"
import java.util.Stack;

class EditorInvoker {

    private Stack<Command> history =
            new Stack<>();

    public void executeCommand(
            Command command
    ) {

        command.execute();

        history.push(command);
    }

    public void undo() {

        if(history.isEmpty()) {

            System.out.println(
                    "Nothing to undo"
            );

            return;
        }

        history.pop().undo();
    }
}
```

---

# Main

```java id="jlwmr9"
public class Main {

    public static void main(String[] args) {

        TextEditor editor =
                new TextEditor();

        EditorInvoker invoker =
                new EditorInvoker();

        Command write =
                new WriteCommand(
                        editor,
                        "Hello"
                );

        invoker.executeCommand(write);

        System.out.println(
                editor.getText()
        );

        invoker.undo();

        System.out.println(
                editor.getText()
        );
    }
}
```

---

# OUTPUT

```text id="jlwmj4"
Hello
(empty after undo)
```

---

# REAL-WORLD APPLICATION 2 → PHOTOSHOP

Now SAME pattern.

But completely different business logic.

---

# Problem

User can:

* rotate image
* crop image
* apply filters
* brush strokes
* undo/redo

---

# Receiver

```text id="jlwmwi"
ImageEditor
```

---

# Commands

| User Action  | Command           |
| ------------ | ----------------- |
| Rotate image | RotateCommand     |
| Crop image   | CropCommand       |
| Blur image   | BlurFilterCommand |

---

# Example Flow

---

# User Rotates Image

UI creates:

```java id="jlwmme"
new RotateCommand(imageEditor, 90)
```

---

# Execute

```java id="wjglm6"
command.execute()
```

---

# Internally

```java id="jlwmw0"
image.rotate(90)
```

---

# Undo

```java id="jlwm3r"
image.rotate(-90)
```

---

# COMPLETE CODE

---

# Receiver

```java id="jlwmv9"
class ImageEditor {

    public void rotate(int degree) {

        System.out.println(
                "Rotating image by "
                + degree
        );
    }
}
```

---

# Rotate Command

```java id="wjglm4"
class RotateCommand implements Command {

    private ImageEditor editor;

    private int degree;

    public RotateCommand(
            ImageEditor editor,
            int degree
    ) {
        this.editor = editor;
        this.degree = degree;
    }

    @Override
    public void execute() {

        editor.rotate(degree);
    }

    @Override
    public void undo() {

        editor.rotate(-degree);
    }
}
```

---

# Main

```java id="jlwmus"
public class Main {

    public static void main(String[] args) {

        ImageEditor editor =
                new ImageEditor();

        Command rotate =
                new RotateCommand(
                        editor,
                        90
                );

        rotate.execute();

        rotate.undo();
    }
}
```

---

# OUTPUT

```text id="jlwmr8"
Rotating image by 90
Rotating image by -90
```

---

# IMPORTANT OBSERVATION

Architecture is SAME.

Only:

* receiver changes
* business logic changes

---

# COMPLETE COMPARISON

| Component | VS Code      | Photoshop      |
| --------- | ------------ | -------------- |
| Receiver  | TextEditor   | ImageEditor    |
| Command   | WriteCommand | RotateCommand  |
| execute() | write text   | rotate image   |
| undo()    | delete text  | reverse rotate |
| Invoker   | Toolbar      | Toolbar        |

---

# THIS IS THE BEAUTY OF COMMAND PATTERN

The architecture stays:

# Generic

while business logic changes.

---

# IMPORTANT UNDERSTANDING

Command object contains:

* receiver reference
* execution logic
* reverse logic
* metadata if needed

---

# Example

Delete command stores:

```java id="wjglmy"
deletedText
```

because undo needs it.

---

# WHY COMMAND PATTERN POWERFUL

Because actions become:

# First-Class Objects

Now actions can be:

✅ stored
✅ queued
✅ logged
✅ replayed
✅ undone
✅ scheduled
✅ retried

---

# UNDO / REDO SUPPORT

One of biggest strengths.

---

# Undo

Each command knows reverse operation.

| Command       | execute()  | undo()      |
| ------------- | ---------- | ----------- |
| WriteCommand  | write()    | delete()    |
| RotateCommand | rotate(90) | rotate(-90) |

---

# Redo

Simply execute again.

---

# COMMAND HISTORY

Commands stored in:

```java id="wjglm8"
Stack<Command>
```

Used for:

* undo
* redo
* replay

---

# REAL SYSTEMS OFTEN USE

# Command + Memento Together

VERY IMPORTANT.

---

# Example → Photoshop Crop

Crop undo difficult.

Why?

Because reverse operation cannot recreate lost pixels.

So before crop:

* snapshot stored using Memento Pattern

Undo:

* restore snapshot

---

# Architecture

```text id="wjglm9"
CropCommand
      |
stores image snapshot
      |
undo restores snapshot
```

---

# THIS IS REAL INDUSTRY DESIGN

---

# COUPLING DISCUSSION

---

# BAD COUPLING

```text id="wjglmq"
UI -> TextEditor directly
UI manages undo manually
```

---

# GOOD COUPLING

```text id="wjglm0"
UI -> Command
Invoker -> Command
```

Loose coupling achieved.

---

# SOLID PRINCIPLES

---

# 1. Single Responsibility Principle

Receiver:

* business logic

Command:

* operation logic

Invoker:

* history management

---

# 2. Open Closed Principle

Add new commands without modifying old code.

---

# 3. Dependency Inversion Principle

Invoker depends on:

```java id="wjglmz"
Command
```

abstraction.

---

# COMMAND vs STRATEGY

VERY IMPORTANT.

---

# Strategy Pattern

Focus:

```text id="wjglmx"
Which algorithm should be used?
```

Example:

* payment method

---

# Command Pattern

Focus:

```text id="wjglm3"
Which action should execute?
```

Example:

* delete text
* rotate image

---

# DIFFERENCE TABLE

| Feature      | Strategy           | Command               |
| ------------ | ------------------ | --------------------- |
| Focus        | Algorithm          | Action                |
| Purpose      | Behavior selection | Request encapsulation |
| Undo support | Rare               | Common                |
| Stores       | Logic              | Operation             |

---

# COMMAND vs MEMENTO

| Command                 | Memento                   |
| ----------------------- | ------------------------- |
| Stores action           | Stores state              |
| Undo via reverse action | Undo via snapshot restore |

---

# ADVANTAGES

---

# 1. Loose Coupling

Invoker independent of receiver.

---

# 2. Undo/Redo Support

Natural implementation.

---

# 3. Queueing Support

Commands treated as objects.

---

# 4. Scheduling Support

Execute later.

---

# 5. Logging Support

Store executed commands.

---

# 6. Macro Commands

Combine multiple commands.

---

# 7. Extensible

Add new commands easily.

---

# DISADVANTAGES

---

# 1. Too Many Classes

Every command becomes class.

---

# 2. Increased Complexity

Can feel over-engineered.

---

# 3. Memory Usage

History tracking consumes memory.

---

# BEST USE CASES

Use Command Pattern when:

✅ Need undo/redo        
✅ Need request history      
✅ Need queueing     
✅ Need scheduling       
✅ Need logging          
✅ Need macro operations         
✅ Need loose coupling       

---

# WHEN NOT TO USE

Avoid when:

* operations trivial
* no extensibility needed
* small/simple systems

---

# FINAL INTERVIEW SUMMARY

> Command Pattern is a behavioral design pattern that encapsulates requests/actions as objects. In real systems like VS Code or Photoshop, every user action such as write, delete, rotate, or crop becomes a command object that knows how to execute and undo itself. The invoker executes commands without knowing actual business logic, while receivers perform real operations. This enables undo-redo, history tracking, queueing, scheduling, replayability, and loose coupling in extensible systems.
