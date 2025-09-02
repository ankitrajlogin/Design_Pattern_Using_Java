package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L05_Facade_Pattern.GoodCode;

/*
📦 Components of Facade Pattern
    - Subsystem Classes: The actual complex classes (TV, SoundSystem, DVDPlayer).
    - Facade Class: Simplifies and unifies calls to subsystems.
    - Client: Uses only the Facade instead of subsystem directly.


🛠️ Steps to Implement Facade Pattern
    1. Identify the complex subsystem that is hard for clients to use.
    2. Create a Facade class that provides a simplified interface.
    3. Inside the Facade, call the necessary subsystem methods in proper order.
    4. Clients interact only with the Facade, not subsystems.

 */


// Subsystems (same as before)
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

// Facade
class HomeTheaterFacade{
    private TV tv ;
    private SoundSystem sound ;
    private DVDPlayer dvd ;

    public HomeTheaterFacade(TV tv , SoundSystem sound , DVDPlayer dvd){
        this.tv = tv ;
        this.sound = sound ;
        this.dvd = dvd ;
    }

    public void watchMovie(String movie){
        System.out.println("Get ready to watch a movie...");
        tv.turnOn();
        tv.setInput("HDMI");
        sound.turnOn();
        sound.setVolume(10);
        dvd.turnOn();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        System.out.println("Goodbye!");
    }
}



// Client
public class Client {
    public static void main(String[] args) {
        TV tv = new TV();
        SoundSystem sound = new SoundSystem();
        DVDPlayer dvd = new DVDPlayer();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, sound, dvd);

        // Simple call from client
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();

    }
}



/*
🔑 Explanation of Good Code
    - HomeTheaterFacade is the Facade Class.
    - It provides one simple method: watchMovie() that internally manages multiple subsystem calls.
    - Client does not need to know about subsystems (TV, SoundSystem, DVDPlayer).


🔗 Related Terms
    - Encapsulation: Hides subsystem complexity.
    - Loose Coupling: Client depends only on Facade, not subsystems.
    - Simplicity: Reduces learning curve for clients.

🌟 Benefits of Facade Pattern
    - ✔ Simplifies usage for client code.
    - ✔ Reduces dependencies between client and subsystems.
    - ✔ Easier maintenance (change subsystem without affecting clients).
    - ✔ Improves readability and reduces clutter.

⚡ Another Example Where to Use Facade
    - Banking System: Instead of client calling methods like checkBalance(), withdraw(), updateLedger(), you provide a BankFacade with withdrawMoney(accountId, amount).
    - Spring Framework / Hibernate: They often use Facade classes to simplify database or service interactions.
 */



// *********** IMPORTANT ************************************
/*
** 🎯 Can the Facade create the objects itself?
    - Yes ✅.
    - In fact, there are two ways to design a Facade:

Option 1: Client creates subsystem objects (Common way)
    - Client has control of subsystem instances.
    - Facade only coordinates them.
    - More flexible (can swap implementations).
    - Client code knows about subsystems → little more complex.

Option 2: Facade creates subsystem objects (More hidden way)
    - Facade internally creates objects for TV, SoundSystem, DVDPlayer.
    - Client is super simple – no subsystem knowledge.
    - Less flexible – client cannot decide which TV/SoundSystem/DVDPlayer to use.




⚖️ Which one should you choose?

    - If flexibility is important (swap implementations, unit testing, reuse components) → let client create subsystem objects.

    - If simplicity is important (client should know nothing about subsystems) → let Facade create them internally.

Both are valid, depends on the use case.
 */