# Flyweight Design Pattern

The **Flyweight Pattern** is a **Structural Design Pattern**.

It is used to:

```text id="pr26ga"
Reduce memory usage
```

by sharing common objects instead of creating duplicate objects repeatedly.

---

# Main Idea

Instead of creating:

```text id="ntb16l"
1000 same objects
```

we create:

```text id="1bujv8"
1 shared object
```

and reuse it everywhere.

---

# Real Life Analogy

# Text Editor Characters

Suppose a document contains:

```text id="d2yb5l"
1 million characters
```

Do we create:

* font object
* size object
* color object

for every character?

No.

Instead:

* common font styles are shared

Example:

```text id="6myhfh"
All 'A' characters using Arial 12 Black
share same style object
```

This saves huge memory.

---

# Why We Need Flyweight Pattern

Suppose game has:

```text id="p50d87"
10 lakh trees
```

Each tree has:

* texture
* color
* mesh
* coordinates

If every tree stores everything separately:

Huge memory usage.

But:

* texture same
* color same
* mesh same

Only position changes.

So we:

* share common data
* store changing data separately

---

# Main Goal

```text id="l5qgq2"
Share common state between many objects
```

to optimize memory.

---

# Core Concept

Flyweight separates object data into:

| Type            | Meaning              |
| --------------- | -------------------- |
| Intrinsic State | Shared common data   |
| Extrinsic State | Unique changing data |

---

# Important Understanding

# Intrinsic State

Shared.

Stored inside flyweight object.

Example:

* image texture
* font style
* tree type

---

# Extrinsic State

Unique per object.

Passed from outside.

Example:

* x coordinate
* y coordinate
* size
* position

---

# BAD CODE Example (Without Flyweight)

Suppose game has many trees.

---

# Bad Code

```java id="7xlbxq"
class Tree {

    private String texture;

    private String color;

    private int x;

    private int y;

    public Tree(String texture,
                String color,
                int x,
                int y) {

        this.texture = texture;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void display() {

        System.out.println(
            "Tree: "
            + texture
            + " "
            + color
            + " at "
            + x
            + ","
            + y
        );
    }
}
```

---

# Problem

If 1 million trees:

```text id="4jlwm3"
1 million texture objects
1 million color objects
```

Huge memory waste.

Even though:

* texture same
* color same

---

# Solution → Flyweight Pattern

Separate:

* shared data
* unique data

---

# GOOD CODE Using Flyweight Pattern

# Step 1 — Flyweight Class

Shared object.

```java id="wyf4vn"
class TreeType {

    private String texture;

    private String color;

    public TreeType(String texture,
                    String color) {

        this.texture = texture;
        this.color = color;
    }

    public void display(int x, int y) {

        System.out.println(
            "Tree "
            + texture
            + " "
            + color
            + " at "
            + x
            + ","
            + y
        );
    }
}
```

---

# Important Observation

```java id="t9khbd"
texture
color
```

stored once.

But:

```java id="p2mbcd"
x
y
```

passed from outside.

This is Flyweight concept.

---

# Step 2 — Flyweight Factory

Factory manages shared objects.

```java id="x3d8g6"
import java.util.HashMap;
import java.util.Map;

class TreeFactory {

    private static Map<String, TreeType>
            treeTypes = new HashMap<>();

    public static TreeType getTreeType(
            String texture,
            String color) {

        String key =
                texture + color;

        if(!treeTypes.containsKey(key)) {

            treeTypes.put(
                    key,
                    new TreeType(texture, color)
            );
        }

        return treeTypes.get(key);
    }
}
```

---

# Why Factory Needed?

Factory ensures:

```text id="4p1x3t"
same object reused
```

instead of new object creation.

---

# Step 3 — Context Object

Stores unique state.

```java id="z0v43p"
class Tree {

    private int x;

    private int y;

    private TreeType treeType;

    public Tree(int x,
                int y,
                TreeType treeType) {

        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void display() {
        treeType.display(x, y);
    }
}
```

---

# Important Observation

Each Tree stores only:

```java id="wktljw"
x
y
reference to shared object
```

Memory saved.

---

# Step 4 — Main

```java id="1htlhf"
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Tree> forest =
                new ArrayList<>();

        TreeType greenTree =
                TreeFactory.getTreeType(
                        "OakTexture",
                        "Green"
                );

        for(int i = 0; i < 5; i++) {

            forest.add(
                    new Tree(i, i+1, greenTree)
            );
        }

        for(Tree tree : forest) {
            tree.display();
        }
    }
}
```

---

# Output

```text id="vl84s0"
Tree OakTexture Green at 0,1
Tree OakTexture Green at 1,2
Tree OakTexture Green at 2,3
Tree OakTexture Green at 3,4
Tree OakTexture Green at 4,5
```

---

# Most Important Point

All trees share:

```java id="l3s7j8"
same TreeType object
```

Only coordinates differ.

---

# Internal Memory View

Without Flyweight:

```text id="uv5yj1"
Tree1 → texture,color,x,y
Tree2 → texture,color,x,y
Tree3 → texture,color,x,y
```

Huge duplication.

---

# With Flyweight

```text id="jlwmjf"
            Shared TreeType
           (texture,color)
                 ▲
        ┌────────┼────────┐
        │        │        │
      Tree1    Tree2    Tree3
      (x,y)    (x,y)    (x,y)
```

Memory optimized.

---

# Real World Examples

| Example             | Shared Object       |
| ------------------- | ------------------- |
| Text Editor         | Font styles         |
| Game Trees          | Tree textures       |
| Chess Game          | Piece design        |
| Web Browser         | Character rendering |
| Icons               | Shared icon images  |
| String Pool in Java | Shared strings      |

---

# Very Important Example — Java String Pool

```java id="vll7q8"
String s1 = "hello";
String s2 = "hello";
```

Both point to same object.

Java internally uses Flyweight.

---

# Memory Visualization

Without Flyweight:

```text id="j0bhsl"
"hello" → object1
"hello" → object2
```

With Flyweight:

```text id="kkqrz8"
"hello"
   ▲
   │
s1 s2
```

Shared object.

---

# Step-by-Step Internal Flow

When:

```java id="r9vxjg"
TreeFactory.getTreeType()
```

called:

Factory checks:

```java id="gr4wov"
already exists?
```

If YES:

* return old object

If NO:

* create new object

This caching is heart of Flyweight.

---

# Structure Diagram

```text id="n6o0x3"
Client
   ↓
Flyweight Factory
   ↓
Shared Flyweight Objects
```

---

# Key Difference Between States

| Intrinsic               | Extrinsic          |
| ----------------------- | ------------------ |
| Shared                  | Unique             |
| Immutable mostly        | Changes frequently |
| Stored inside flyweight | Passed externally  |
| Memory optimized        | User specific      |

---

# When Flyweight Pattern is Helpful

# 1. Huge Number of Objects

Example:

* particles
* trees
* bullets
* characters

---

# 2. Many Duplicate States

Objects share common data.

---

# 3. Memory Optimization Critical

Games
Graphics
Rendering engines

---

# 4. Object Creation Expensive

Reuse improves performance.

---

# When Flyweight Pattern is NOT Helpful

# 1. Small Number of Objects

Optimization unnecessary.

---

# 2. No Shared State

Every object unique.

---

# 3. Extrinsic State Too Large

Sharing benefit becomes small.

---

# 4. Code Complexity Not Worth It

Sometimes memory savings tiny.

---

# Advantages

| Advantage               | Explanation    |
| ----------------------- | -------------- |
| Memory Optimization     | Huge savings   |
| Better Performance      | Fewer objects  |
| Reusability             | Shared objects |
| Reduced Object Creation | Factory reuse  |

---

# Disadvantages

| Disadvantage              | Explanation          |
| ------------------------- | -------------------- |
| Complex Design            | Harder understanding |
| External State Management | More responsibility  |
| Thread Safety Issues      | Shared objects       |
| Debugging Difficulty      | Shared references    |

---

# Flyweight vs Singleton

| Flyweight           | Singleton               |
| ------------------- | ----------------------- |
| Many shared objects | Only one object         |
| Optimizes memory    | Ensures single instance |
| Factory based       | Global access           |

---

# Flyweight vs Proxy

| Flyweight           | Proxy             |
| ------------------- | ----------------- |
| Shares objects      | Controls access   |
| Memory optimization | Access management |
| Reuse common state  | Wrapper object    |

---

# Flyweight vs Factory

| Flyweight           | Factory                    |
| ------------------- | -------------------------- |
| Memory optimization | Object creation            |
| Shared instances    | New object generation      |
| Often uses factory  | Factory not always sharing |

---

# Mental Model

Flyweight means:

```text id="mjlqlz"
"Don't create duplicate objects
if same data can be shared."
```

---

# Interview Definition

> Flyweight Pattern minimizes memory usage by sharing as much common object data as possible among multiple objects.

---

# Final Core Understanding

Instead of:

```text id="5ln07r"
Millions of duplicate objects
```

Flyweight creates:

```text id="k5yxxw"
Few shared objects
+ unique external state
```

That is the complete essence of the Flyweight Pattern.
