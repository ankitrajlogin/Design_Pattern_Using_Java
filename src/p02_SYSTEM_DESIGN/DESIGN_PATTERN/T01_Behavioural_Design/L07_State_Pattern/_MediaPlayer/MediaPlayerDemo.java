package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L07_State_Pattern._MediaPlayer;


// Step 1: Define the State interface
interface State {
    void play(MediaPlayer player);
    void pause(MediaPlayer player);
    void stop(MediaPlayer player);
}

// Step 2: Concrete States

class PlayingState implements State {
    @Override
    public void play(MediaPlayer player) {
        System.out.println("Already playing the track.");
    }

    @Override
    public void pause(MediaPlayer player) {
        System.out.println("Pausing the track.");
        player.setState(new PausedState());
    }

    @Override
    public void stop(MediaPlayer player) {
        System.out.println("Stopping the track.");
        player.setState(new StoppedState());
    }
}

class PausedState implements State {
    @Override
    public void play(MediaPlayer player) {
        System.out.println("Resuming the track.");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayer player) {
        System.out.println("Track is already paused.");
    }

    @Override
    public void stop(MediaPlayer player) {
        System.out.println("Stopping the track from paused state.");
        player.setState(new StoppedState());
    }
}

class StoppedState implements State {
    @Override
    public void play(MediaPlayer player) {
        System.out.println("Starting the track from the beginning.");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayer player) {
        System.out.println("Track is stopped. Cannot pause.");
    }

    @Override
    public void stop(MediaPlayer player) {
        System.out.println("Track is already stopped.");
    }
}

// Step 3: Context (Media Player)
class MediaPlayer {
    private State state;

    public MediaPlayer() {
        state = new StoppedState(); // initial state
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressPlay() {
        state.play(this);
    }

    public void pressPause() {
        state.pause(this);
    }

    public void pressStop() {
        state.stop(this);
    }
}

// Step 4: Main class to simulate transitions
public class MediaPlayerDemo {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        // Sequence: Play → Pause → Stop
        player.pressPlay();   // Start playing the track
        player.pressPause();  // Pause the track
        player.pressStop();   // Stop the track
    }
}
