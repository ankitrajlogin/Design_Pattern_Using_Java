package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T01_Behavioural_Design.L06_Iterator_Pattern.Good_Code;

/*
 ⚡ Components of Iterator Pattern

1. Iterator (Interface)
   - Defines methods for traversal:
     - hasNext() → checks if more elements exist
     - next() → returns next element

2. Concrete Iterator
   - Implements the traversal logic for a specific collection.

3. Aggregate (Collection Interface)
   - Defines a method to create an iterator.

4. Concrete Aggregate (Collection Implementation)
   - Stores elements and creates its iterator.

5. Client
   - Uses the iterator to loop through elements, without knowing how they’re stored


 🛠 Steps to Write Iterator Pattern
 1. Define an Iterator interface (methods: hasNext(), next()).
 2. Create Concrete Iterator that knows how to iterate over the collection.
 3. Define an Aggregate interface with createIterator().
 4. Implement Concrete Aggregate (actual collection).
 5. Client uses the iterator instead of accessing the collection directly.


 📊 When to Use Iterator Pattern
- When you need sequential access to a collection without exposing its structure.
- When collections can have different implementations but you want uniform traversal.
- Example: Implementing custom data structures.
*/


// 1. Iterator Interface
interface Iterator<T>{
    boolean hasNext() ;
    T next() ; // returns the actual type instead of object
}


// 2. Concrete Iterator for Books
class BookShelfIterator implements Iterator<String>{
    private String[] books ;
    private int index = 0 ;

    public BookShelfIterator(String[] books){
        this.books = books ;
    }

    public boolean hasNext(){
        return index < books.length && books[index] != null ;
    }

    public String next(){
        return books[index++] ;
    }
}

// 3. Aggregate (Collection Interface) with Generics
interface Aggregate<T>{
    Iterator<T> createIterator() ;
}


// 4. concrete Aggregate
class BookShelf implements Aggregate<String>{
    private String[] books ;
    private int count = 0 ;

    public BookShelf(int size){
        books = new String[size] ;
    }

    public void addBook(String book){
        books[count++] = book ;
    }

    public Iterator<String> createIterator(){
        return new BookShelfIterator(books) ;
    }
}

// 5. Client
public class BookMain {
    public static void main(String[] args){
        BookShelf shelf = new BookShelf(3) ;
        shelf.addBook("Book A");
        shelf.addBook("Book B");
        shelf.addBook("Book C");

        Iterator<String> iterator = shelf.createIterator() ;

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}

/*
Iterator pattern separates two responsibilities:
1. Aggregate (Collection role) → Manages the data (adding/removing books).
   - Example: BookShelf
   - Its only responsibility = store and manage collection items.

2. Iterator → Provides a standard way to traverse a collection without exposing its internal structure.
    - Example: BookShelfIterator
    - Its only responsibility = traversal (go to next, check if more elements exist).


📦 Benefits of separation
1. Encapsulation → Client doesn’t need to know whether the collection is an array, list, set, or even a database.
    - It just calls iterator.next().

2. Multiple Traversals → Same collection can have different iterators:
    - Forward iterator
    - Reverse iterator
    - Filtered iterator (e.g., only books starting with “A”)
  If you mix everything inside BookShelf, you’ll clutter it with too many traversal logics.

3. Reusability → You can write different Aggregate (like BookShelf, MovieList, UserDirectory) and still use the same Iterator concept.

4. Open/Closed Principle (OCP) →
  If tomorrow you want to change internal storage (String[] → ArrayList<String>),
  you don’t need to change how client code traverses. Iterator interface remains the same.
*/
