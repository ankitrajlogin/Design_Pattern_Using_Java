# Iterator Design Pattern

The **Iterator Pattern** is a **Behavioral Design Pattern**.

It provides a way to access elements of a collection **one by one** without exposing the internal structure of the collection.

---

# Real-Life Example

Think about:

* TV Remote → next channel
* Spotify playlist → next song
* Browser history → next page
* Book pages → next page

You do not care:

* how songs are stored
* how channels are stored
* whether data is in array, list, tree, database

You only need:

```java
hasNext()
next()
```

That is Iterator Pattern.

---

# Why Do We Need Iterator Pattern?

Suppose we have:

```java
ArrayList<Book>
LinkedList<Book>
HashSet<Book>
Book[]
```

Each collection has different traversal logic.

Without Iterator Pattern:

* client code becomes tightly coupled
* traversal logic repeats everywhere
* changing collection type breaks code

Iterator Pattern solves this.

---

# Problem Without Iterator Pattern (Bad Code)

---

# BAD DESIGN

```java
import java.util.ArrayList;

class Book {
    String name;

    Book(String name) {
        this.name = name;
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }
}

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Java"));
        library.addBook(new Book("System Design"));
        library.addBook(new Book("Spring Boot"));

        // Client directly accessing internal structure

        for (int i = 0; i < library.books.size(); i++) {
            System.out.println(
                    library.books.get(i).name
            );
        }
    }
}
```

---

# Problems in This Code

---

## 1. Encapsulation Broken

Client directly accesses:

```java
library.books
```

Internal data structure exposed.

---

## 2. Tight Coupling

Client knows:

```java
ArrayList
```

If tomorrow:

```java
ArrayList → LinkedList
```

client code may need changes.

---

## 3. Traversal Logic Everywhere

Every client writes:

```java
for loop
```

again and again.

---

## 4. Multiple Traversal Types Become Hard

What if we want:

* reverse traversal
* skip traversal
* filtered traversal

Code becomes messy.

---

# Iterator Pattern Solution

Iterator Pattern separates:

* collection storage
* traversal logic

---

# Structure

---

## 1. Iterator

Defines traversal methods.

```java
hasNext()
next()
```

---

## 2. Concrete Iterator

Actual traversal implementation.

---

## 3. Aggregate / Collection

Creates iterator.

---

## 4. Concrete Collection

Stores data.

---

# Proper Example

---

# Step 1: Create Book Class

```java
class Book {

    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

---

# Step 2: Create Iterator Interface

```java
interface Iterator {

    boolean hasNext();

    Book next();
}
```

---

# Step 3: Create Collection Interface

```java
interface BookCollection {

    Iterator createIterator();
}
```

---

# Step 4: Concrete Collection

```java
import java.util.ArrayList;
import java.util.List;

class Library implements BookCollection {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Iterator createIterator() {
        return new LibraryIterator(this);
    }
}
```

---

# Step 5: Concrete Iterator

```java
class LibraryIterator implements Iterator {

    private Library library;

    private int index = 0;

    public LibraryIterator(Library library) {
        this.library = library;
    }

    @Override
    public boolean hasNext() {

        return index < library.getBooks().size();
    }

    @Override
    public Book next() {

        if (hasNext()) {

            return library.getBooks().get(index++);
        }

        return null;
    }
}
```

---

# Step 6: Main Class

```java
public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Java"));
        library.addBook(new Book("Spring Boot"));
        library.addBook(new Book("System Design"));

        Iterator iterator = library.createIterator();

        while (iterator.hasNext()) {

            Book book = iterator.next();

            System.out.println(
                    book.getName()
            );
        }
    }
}
```

---

# Output

```java
Java
Spring Boot
System Design
```

---

# Flow of Execution

---

## Step 1

Client asks collection:

```java
Iterator iterator = library.createIterator();
```

---

## Step 2

Library returns iterator object.

```java
return new LibraryIterator(this);
```

---

## Step 3

Client traverses using:

```java
hasNext()
next()
```

Client does NOT know:

* ArrayList
* indexing
* internal storage

---

# Major Advantage

Client code becomes:

```java
while(iterator.hasNext())
```

instead of:

```java
for(int i=0; ...)
```

---

# Reverse Iterator Example

One huge benefit:

You can create multiple iterators.

---

## Reverse Iterator

```java
class ReverseLibraryIterator implements Iterator {

    private Library library;

    private int index;

    public ReverseLibraryIterator(Library library) {

        this.library = library;

        this.index =
                library.getBooks().size() - 1;
    }

    @Override
    public boolean hasNext() {

        return index >= 0;
    }

    @Override
    public Book next() {

        return library.getBooks().get(index--);
    }
}
```

---

# Usage

```java
Iterator iterator =
        new ReverseLibraryIterator(library);

while(iterator.hasNext()) {

    System.out.println(
            iterator.next().getName()
    );
}
```

---

# Output

```java
System Design
Spring Boot
Java
```

---

# Where Iterator Pattern Is Used in Java

Java already heavily uses Iterator Pattern.

---

# Example

```java
ArrayList<String> list = new ArrayList<>();

Iterator<String> iterator =
        list.iterator();

while(iterator.hasNext()) {

    System.out.println(
            iterator.next()
    );
}
```

---

# Java Internal Classes

Java provides:

* Iterator
* ListIterator
* Iterable

---

# Iterable Example

```java
class Library implements Iterable<Book>
```

Then Java allows:

```java
for(Book book : library)
```

internally using iterator.

---

# Real-World Use Cases

---

## 1. Collections

* ArrayList
* HashSet
* LinkedList

---

## 2. File Traversal

```java
nextLine()
```

---

## 3. Database Records

Fetch row by row.

---

## 4. Tree Traversal

* DFS Iterator
* BFS Iterator

---

## 5. Pagination

Next page / previous page.

---

# When To Use Iterator Pattern

Use Iterator Pattern when:

---

## 1. You Want To Hide Internal Structure

Client should not know:

* array
* linked list
* tree

---

## 2. Multiple Traversal Methods Needed

* forward
* backward
* filtered
* sorted

---

## 3. Traversal Logic Is Complex

Avoid duplicate loops everywhere.

---

## 4. Encapsulation Is Important

Prevent direct access to collection internals.

---

# Iterator Pattern vs For Loop

| Feature                  | For Loop | Iterator Pattern |
| ------------------------ | -------- | ---------------- |
| Encapsulation            | ❌        | ✅                |
| Flexible Traversal       | ❌        | ✅                |
| Reusable Traversal Logic | ❌        | ✅                |
| Works Across Collections | ❌        | ✅                |
| Clean Client Code        | ❌        | ✅                |

---

# Important Interview Points

---

# 1. Iterator Pattern is Behavioral Pattern

Because it defines behavior of traversal.

---

# 2. Client Does Not Know Internal Structure

This is the main idea.

---

# 3. Open/Closed Principle

You can add new iterators without changing client code.

---

# 4. Single Responsibility Principle

Collection stores data.

Iterator traverses data.

Responsibilities separated.

---

# 5. Java Uses It Everywhere

Very commonly asked in interviews.

---

# Final Understanding

Without Iterator:

```java
client handles traversal
```

With Iterator:

```java
collection provides traversal mechanism
```

---

# Very Short Definition

> Iterator Pattern provides a way to traverse a collection sequentially without exposing its internal implementation.
