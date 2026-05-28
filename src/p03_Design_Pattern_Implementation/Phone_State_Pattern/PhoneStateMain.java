package p03_Design_Pattern_Implementation.Phone_State_Pattern;

interface State{
    void play(MusicPlayer player) ;

    void pause(MusicPlayer player) ;

    void stop(MusicPlayer player) ;
}

class PlayingState implements State{

    @Override
    public void play(MusicPlayer player){
        System.out.println("Already playing");
    }

    @Override
    public void pause(MusicPlayer player) {

        System.out.println("Pausing music");

        player.setState(new PausedState());
    }

    @Override
    public void stop(MusicPlayer player) {

        System.out.println("Stopping music");

        player.setState(new StoppedState());
    }

    @Override
    public String toString() {
        return "PLAYING";
    }
}

class PausedState implements State {

    @Override
    public void play(MusicPlayer player) {

        System.out.println("Resuming music");

        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player) {

        System.out.println("Already paused");
    }

    @Override
    public void stop(MusicPlayer player) {

        System.out.println("Stopping music");

        player.setState(new StoppedState());
    }

    @Override
    public String toString() {
        return "PAUSED";
    }
}

class StoppedState implements State {

    @Override
    public void play(MusicPlayer player) {

        System.out.println("Starting music");

        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player) {

        System.out.println("Cannot pause. Music already stopped");
    }

    @Override
    public void stop(MusicPlayer player) {

        System.out.println("Already stopped");
    }

    @Override
    public String toString() {
        return "STOPPED";
    }
}


// create context class
class MusicPlayer {

    private State currentState;

    public MusicPlayer() {

        currentState = new StoppedState();
    }

    public void setState(State state) {

        this.currentState = state;
    }

    public State getState() {

        return currentState;
    }

    public void play() {

        currentState.play(this);
    }

    public void pause() {

        currentState.pause(this);
    }

    public void stop() {

        currentState.stop(this);
    }
}


public class PhoneStateMain {

    public static void main(String[] args) {

        MusicPlayer player = new MusicPlayer();

        System.out.println(player.getState());
        player.play();
        player.play();

        System.out.println(player.getState());
        player.pause();

        System.out.println(player.getState());
        player.play();

        System.out.println(player.getState());
        player.stop();
        player.stop();

        System.out.println(player.getState());
        player.stop();
    }

}
