package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L00_Basic;



/*
📘 Structural Design Patterns in Java (and OOP in general)
✅ What are Structural Design Patterns?

Structural design patterns are concerned with how classes and objects are composed to form larger structures.
They focus on relationships between entities—especially composition and inheritance—to make the system more flexible, reusable, and easy to maintain.

👉 In simple words:
They help you “glue” classes/objects together in an efficient way so you can build bigger systems without messy dependencies.

⚡ Why do we need them?

Imagine building a house 🏠:
    - You don’t reinvent the brick every time.
    - You figure out how to arrange bricks, beams, and walls to form the whole structure.

Similarly, in software:
    - Without structural patterns, you might end up with tightly coupled, hard-to-change code.
    - You may duplicate code, break encapsulation, or struggle to integrate external systems.

Structural patterns help organize classes/objects so changes are easier and code is more reusable.

🎯 Benefits
1. Flexibility – Easier to swap or add new components.
2. Reusability – Common code is abstracted and shared.
3. Maintainability – Clear boundaries between classes.
4. Loose Coupling – Classes depend on abstractions, not specific implementations.
5. Integration – Helps in connecting new/external libraries or legacy code smoothly.

⚠️ What happens without them?
    - Code becomes monolithic (everything mixed up).
    - Hard to extend or scale (adding new features breaks old ones).
    - Too many dependencies (a small change in one class breaks many).
    - Difficult to reuse components in other projects.

📚 The Structural Patterns

There are 7 classic structural design patterns:

1. Adapter Pattern (a.k.a Wrapper)
    - 👉 Converts one interface into another expected by the client.
    - Use Case: Integrating an old library into your new system.
    - Without it: You rewrite existing code → wasteful & error-prone.
    - Benefit: Reuse old classes with incompatible interfaces.

2. Bridge Pattern
    - 👉 Separates abstraction from implementation so both can evolve independently.
    - Use Case: Suppose you have Shape (abstraction) and Color (implementation). Without bridge, you’d need classes like RedCircle, BlueCircle, RedSquare, etc.
    - With Bridge: Shape holds a reference to Color → flexibility.
    - Benefit: Avoids combinatorial explosion of classes.

3. Composite Pattern
    - 👉 Lets you treat individual objects and groups (compositions) uniformly.
    - Use Case: File system (File vs Folder). A folder contains files and other folders, but you treat both as “Nodes”.
    - Benefit: Simplifies client code (doesn’t need to know if it’s a leaf or a composite).

4. Decorator Pattern
    - 👉 Adds new behavior to objects dynamically, without modifying their code.
    - Use Case: Adding scrollbars to a Window or adding toppings to a Pizza.
    - Benefit: More flexible than inheritance (you can add/remove features at runtime).

5. Facade Pattern
    - 👉 Provides a simple unified interface to a complex subsystem.
    - Use Case: Instead of directly calling 10 methods of a library, you call one startComputer() which internally calls checkPower(), loadOS(), etc.
    - Benefit: Hides complexity → easier to use.

6. Flyweight Pattern
    - 👉 Shares common objects to save memory.
    - Use Case: A text editor where every letter a, b, c... is reused instead of creating a new object for every character.
    - Benefit: Huge memory savings when you have millions of similar objects.

7. Proxy Pattern
    - 👉 Provides a placeholder (proxy) object to control access to the real object.
    - Use Case: Lazy loading of images, remote proxy for network calls, or security proxy.
    - Benefit: Adds extra control without changing the actual class.

📊 Quick Table Summary
| Pattern   | Key Idea                              | Example                |
| --------- | ------------------------------------- | ---------------------- |
| Adapter   | Convert interface                     | Mobile charger adapter |
| Bridge    | Separate abstraction & implementation | Shape + Color          |
| Composite | Tree structures                       | File & Folder system   |
| Decorator | Add behavior dynamically              | Window + Scrollbar     |
| Facade    | Simplify subsystem                    | Computer startup       |
| Flyweight | Share objects                         | Text editor characters |
| Proxy     | Control access                        | Image loading proxy    |



🏆 Final Thoughts
Structural patterns are all about designing relationships between classes and objects so:
    - You avoid rigid, hard-coded dependencies.
    - You can scale and extend systems gracefully.
    - You can reuse components efficiently.

Think of them as the engineering glue 🏗️ that holds large software architectures together.
*/



public class StructuralDesignPattern {
}
