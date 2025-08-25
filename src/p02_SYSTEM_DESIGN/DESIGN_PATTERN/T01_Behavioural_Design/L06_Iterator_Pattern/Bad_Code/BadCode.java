package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L06_Iterator_Pattern.Bad_Code;



/*
🔹 Iterator Design Pattern
📘 Definition:
The Iterator Pattern provides a way to access elements of a collection (like lists, arrays, sets, etc.) sequentially without exposing the underlying representation (like whether it’s an array, a linked list, a tree, etc.).



 👉 In short:
It lets you traverse a collection without knowing how it is built internally.

👶 In Simple Words:
Imagine you have a playlist of songs 🎶.
- You don’t care if they are stored in an array, a database, or a linked list.
-You just want a remote control (iterator) that says:
   - next() → play next song
   - hasNext() → check if more songs are left
The Iterator Pattern gives you that remote control 📺.



🎯 Real Life Examples:
1. TV Remote: You press next/previous to change channels, without knowing how the TV internally stores them.
2. Google Photos: You scroll left/right through pictures without knowing how files are stored in memory.
3. YouTube Playlist: You just click next video, not caring how it’s fetched.

 */


class BookShelf{
    private String[] books ;
    private int count = 0 ;

    public BookShelf(int size){
        books = new String[size] ;
    }

    public void addBook(String book){
        books[count++] = book ;
    }

    // BAD : client needs to know array details
    public String[] getBooks(){
        return books ;
    }
}

public class BadCode {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(3);
        shelf.addBook("Book A");
        shelf.addBook("Book B");
        shelf.addBook("Book C");

        // Client directly accesses array (bad!)
        for (String book : shelf.getBooks()) {
            if (book != null) System.out.println(book);
        }
    }
}

/*
👉 Problem:
Client knows internal details (array).
If storage changes to LinkedList, client breaks ❌.
 */
