package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L06_Iterator_Pattern.SongPlaylistDesign;


import java.util.*;

/*
 📌 Problem Statement:
 You are designing a Music Library Application 🎵.
 The library should store songs in different data structures depending on the playlist type:
    - Array-based Playlist → Songs stored in an array.
    - List-based Playlist → Songs stored in a linked list.
    - Set-based Playlist → Songs stored in a set (unique songs).

 The application must provide a unified way to iterate over songs, regardless of how they are stored internally.

 👉 Requirement:
        - Implement the Iterator Pattern so that users can traverse songs in any playlist without worrying about whether songs are stored in an array, list, or set.
        - The system should be extensible — if we add a new data structure later (say, a Queue), existing code should not break.

 */


class Song{
    private String title ;

    public Song(String title){
        this.title = title ;
    }

    public String getTitle(){
        return title ;
    }
}

interface SongIterator{
    boolean hasNext() ;
    Song next() ;
}

interface Playlist{
    SongIterator createIterator() ;
}


//***************************************************
//Array-based Playlist
//***************************************************
class ArrayPlaylistIterator implements SongIterator{
    private Song[] songs ;
    private int position = 0 ;

    public ArrayPlaylistIterator(Song[] songs){
        this.songs = songs ;
    }

    @Override
    public boolean hasNext(){
        return position < songs.length && songs[position] != null ;
    }

    @Override
    public Song next(){
        return songs[position++] ;
    }
}


class ArrayPlaylist implements  Playlist{
    private Song[] songs ;
    private int index = 0 ;

    public ArrayPlaylist(int size){
        songs = new Song[size] ;
    }

    public void addSong(Song song){
        if(index < songs.length){
            songs[index++] = song ;
        }
    }

    @Override
    public SongIterator createIterator(){
        return new ArrayPlaylistIterator(songs) ;
    }
}



//***************************************************
// List-based Playlist
//***************************************************

class ListPlaylistIterator implements SongIterator{
    private List<Song> songs ;
    private int position = 0 ;

    public ListPlaylistIterator(List<Song> songs){
        this.songs = songs ;
    }

    @Override
    public boolean hasNext(){
        return position < songs.size() ;
    }

    @Override
    public Song next(){
        return songs.get(position++) ;
    }
}

class ListPlaylist implements Playlist{
    private List<Song> songs ;

    public ListPlaylist(List<Song> songs){
        this.songs = songs ;
    }

    @Override
    public SongIterator createIterator(){
        return new ListPlaylistIterator(songs) ;
    }
}


//***************************************************
// set-based Playlist
//***************************************************

class SetPlaylistIterator implements SongIterator {
    private Iterator<Song> iterator;

    public SetPlaylistIterator(Iterator<Song> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Song next() {
        return iterator.next();
    }
}


class SetPlaylist implements Playlist {
    private Set<Song> songs;

    public SetPlaylist(Set<Song> songs) {
        this.songs = songs;
    }

    @Override
    public SongIterator createIterator() {
        return new SetPlaylistIterator(songs.iterator());
    }
}

public class SongPlaylistMain {
    public static void main(String[] args){
        // Array Playlist
        ArrayPlaylist arrayPlaylist = new ArrayPlaylist(3) ;
        arrayPlaylist.addSong(new Song("Song A"));
        arrayPlaylist.addSong(new Song("Song B")) ;
        arrayPlaylist.addSong(new Song("Song C"));

        // List Playlist
        List<Song>listSongs = new ArrayList<>() ;
        listSongs.add(new Song("Song D")) ;
        listSongs.add(new Song("Song E")) ;
        Playlist listPlaylist = new ListPlaylist(listSongs) ;

        // set playlist
        Set<Song> setSongs = new HashSet<>() ;
        setSongs.add(new Song("Song F")) ;
        setSongs.add(new Song("Song G")) ;
        Playlist setPlaylist  = new SetPlaylist(setSongs) ;


        // Iterate over all playlists
        printSongs("Array Playlist", arrayPlaylist.createIterator());
        printSongs("List Playlist", listPlaylist.createIterator());
        printSongs("Set Playlist", setPlaylist.createIterator());

    }


    private static void printSongs(String playlistName, SongIterator iterator) {
        System.out.println("🎶 " + playlistName + ":");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
        System.out.println();
    }

}


/*
🔑 Key Points:
- Iterator Pattern lets us traverse collections without exposing underlying data structures.
- We can easily add new types of playlists (e.g., QueuePlaylist, StackPlaylist) without changing client code.
- Each playlist provides its own iterator, but the client only depends on the common SongIterator interface.
 */
