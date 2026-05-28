package p03_Design_Pattern_Implementation.Book_Iteration_Pattern;

import java.util.ArrayList;
import java.util.List;

// book
class Book{
    private final String name ;
    public Book(String name){
        this.name = name ;
    }

    public String getName(){
        return name ;
    }
}

// interator interface
interface Iterator{
    boolean hasNext() ;
    Book next() ;
}

// create Collection interface
interface BookCollection{
    Iterator createIterator() ;
}

// concrete Collection
class Library implements BookCollection{
    private List<Book> books = new ArrayList<>() ;

    public void addBook(Book book){
        books.add(book) ;
    }

    public List<Book> getBooks(){
        return books ;
    }

    @Override
    public Iterator createIterator(){
        return new LibraryIterator(this) ;
    }
}

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

public class BookIterationMain {

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
