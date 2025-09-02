package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L05_Facade_Pattern.BadCode;

/*
🎯 What is Facade Pattern?
Definition:
    The Facade Pattern provides a simplified interface to a larger, more complex subsystem.

Use Case:
    When you have a system with multiple classes, methods, and dependencies, but you want clients to interact with it in a clean and simple way.

Real-life Analogy:
    Think about a universal remote – instead of learning how to control each device (TV, speakers, AC, DVD), you just use one remote that internally manages everything.
/*



/*
❌ Problem Statement (Bad Code Example)
    - Imagine you want to build a Home Theater System where a user wants to:
    - Turn on the TV
    - Start the Sound System
    - Turn on the DVD Player
    - Play a Movie
 */


// Subsystems
class TV {
    void turnOn() { System.out.println("TV is ON"); }
    void setInput(String input) { System.out.println("TV input set to " + input); }
}

class SoundSystem {
    void turnOn() { System.out.println("Sound System is ON"); }
    void setVolume(int level) { System.out.println("Volume set to " + level); }
}

class DVDPlayer {
    void turnOn() { System.out.println("DVD Player is ON"); }
    void play(String movie) { System.out.println("Playing movie: " + movie); }
}

// Client
public class Client {
    public static void main(String[] args) {
        TV tv = new TV();
        SoundSystem sound = new SoundSystem();
        DVDPlayer dvd = new DVDPlayer();

        // Too many steps for the client!
        tv.turnOn();
        tv.setInput("HDMI");
        sound.turnOn();
        sound.setVolume(10);
        dvd.turnOn();
        dvd.play("Inception");
    }
}

/*
❌ Problem with this code
    - Tight coupling: Client must know about all subsystems (TV, SoundSystem, DVDPlayer).
    - Complexity: Too many method calls just to “watch a movie.”
    - Difficult maintenance: If internal logic changes (e.g., order of initialization), every client needs updating.
 */
