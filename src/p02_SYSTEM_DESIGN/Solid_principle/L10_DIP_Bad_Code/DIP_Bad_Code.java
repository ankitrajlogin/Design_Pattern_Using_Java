package p02_SYSTEM_DESIGN.Solid_principle.L10_DIP_Bad_Code;

/*
What is DIP?
Dependency Inversion Principle (DIP) states:
High-level modules should not depend on low-level modules. Both should depend on abstractions (interfaces or abstract classes).
Abstractions should not depend on details. Details should depend on abstractions.

In simple terms:
Your business logic (high-level code) should not directly depend on specific implementations (low-level code).
Instead, both should depend on an interface/abstraction.
This makes code flexible, testable, and easier to extend.
 */




/*
Real-life Analogy
Imagine a music player app:
Bad Design 🎧: The app directly uses Spotify API only. If Spotify shuts down or you want to switch to YouTube Music, you must rewrite everything.

Good Design 🎶: The app uses a MusicService interface. Spotify, YouTube Music, Apple Music → all implement that. Now you can swap services easily without touching the app logic.
 */



/*
Real-life Analogy
Imagine a music player app:
- Bad Design 🎧: The app directly uses Spotify API only. If Spotify shuts down or you want to switch to YouTube Music, you must rewrite everything.

- Good Design 🎶: The app uses a MusicService interface. Spotify, YouTube Music, Apple Music → all implement that. Now you can swap services easily without touching the app logic.

 */



// Low-level module
class MySQLDatabase {
    public void saveData(String data) {
        System.out.println("Saving data in MySQL: " + data);
    }
}

// High-level module directly depends on MySQLDatabase
class UserService {
    private MySQLDatabase database = new MySQLDatabase();

    public void addUser(String user) {
        database.saveData(user); // tightly coupled
    }
}


public class DIP_Bad_Code {
    public static void main(String[] args){
        UserService service = new UserService() ;
        service.addUser("Ankit Raj") ;
    }
}


/*
❌ Problems:
- Tightly coupled → UserService depends directly on MySQLDatabase.
- If you want to switch to PostgreSQL or MongoDB, you must modify UserService code.
- Violates Open/Closed Principle (OCP) too, because adding new DB types requires modification.

 */