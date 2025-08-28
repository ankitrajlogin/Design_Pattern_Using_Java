package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L06_Iterator_Pattern.SongDifferentIterator;

import java.util.* ;


class Song{
    String name ;
    String singer ;
    String genre ;

    public Song(String name , String singer , String genre){
        this.name = name ;
        this.singer = singer ;
        this.genre = genre ;
    }

    @Override
    public String toString(){
        return name + " by " + singer + " (" + genre + ")" ;
    }
}

interface SongIterator{
    boolean hasNext() ;
    Song next() ;
}

class ForwardIterator implements SongIterator{
    private List<Song> songs ;
    private int index = 0 ;

    public ForwardIterator(List<Song> songs){
        this.songs = songs ;
    }

    public boolean hasNext(){
        return index < songs.size() ;
    }

    public Song next(){
        return songs.get(index++) ;
    }
}

class BackwardIterator implements SongIterator{
    private List<Song> songs ;
    private int index ;

    public BackwardIterator(List<Song> songs){
        this.songs = songs ;
        this.index = songs.size() -1 ;
    }

    public boolean hasNext(){
        return index >=0 ;
    }

    public Song next(){
        return songs.get(index--) ;
    }
}

class FilterIterator implements SongIterator{
    private List<Song> songs ;
    private int index = 0 ;
    private String singerFilter ;

    public FilterIterator(List<Song> songs, String singerFilter){
        this.songs = songs ;
        this.singerFilter = singerFilter ;
    }

    public boolean hasNext(){
        while(index < songs.size()){
            if(songs.get(index).singer.equals(singerFilter)){
                return true ;
            }
            index++ ;
        }

        return false ;
    }

    public Song next(){
        return songs.get(index++) ;
    }
}

class SongCollection{
    private List<Song> songs = new ArrayList<>() ;

    public void addSong(Song song){
        songs.add(song) ;
    }

    public SongIterator createForwardIterator(){
        return new ForwardIterator(songs) ;
    }

    public SongIterator createBackwardIterator(){
        return new BackwardIterator(songs) ;
    }

    public SongIterator createFilterIterator(String singer){
        return new FilterIterator(songs , singer) ;
    }
}





public class SongIteratorMain {
    public static void main(String[] args) {
        SongCollection collection = new SongCollection();
        collection.addSong(new Song("Song1", "SingerA", "Pop"));
        collection.addSong(new Song("Song2", "SingerB", "Rock"));
        collection.addSong(new Song("Song3", "SingerA", "Pop"));

        System.out.println("Forward Iterator:");
        SongIterator forward = collection.createForwardIterator();
        while (forward.hasNext()) {
            System.out.println(forward.next());
        }

        System.out.println("\nBackward Iterator:");
        SongIterator backward = collection.createBackwardIterator();
        while (backward.hasNext()) {
            System.out.println(backward.next());
        }

        System.out.println("\nFilter Iterator (SingerA):");
        SongIterator filter = collection.createFilterIterator("SingerA");
        while (filter.hasNext()) {
            System.out.println(filter.next());
        }
    }
}
