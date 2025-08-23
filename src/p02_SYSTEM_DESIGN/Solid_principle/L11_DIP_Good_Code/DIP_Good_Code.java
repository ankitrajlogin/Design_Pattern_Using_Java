package p02_SYSTEM_DESIGN.Solid_principle.L11_DIP_Good_Code;


//We introduce an abstraction (interface) between UserService (high-level) and Database (low-level).


// Abstraction (interface)
interface Database {
    void saveData(String data);
}

// Low-level module 1
class MySQLDatabase implements Database {
    public void saveData(String data) {
        System.out.println("Saving data in MySQL: " + data);
    }
}

// Low-level module 2
class PostgreSQLDatabase implements Database {
    public void saveData(String data) {
        System.out.println("Saving data in PostgreSQL: " + data);
    }
}

// High-level module depends on abstraction, not on concrete class
class UserService {

    private Database database; // variable declaration (just a box, currently empty)

    // Constructor Injection
    public UserService(Database database) {
        this.database = database; // box now holds reference to MySQLDatabase or PostgreSQLDatabase
    }

    public void addUser(String user) {
        database.saveData(user);
    }
}

public class DIP_Good_Code {
    public static void main(String[] args) {
        // Inject MySQL
        Database mysql = new MySQLDatabase();
        UserService service1 = new UserService(mysql);
        service1.addUser("Ankit Raj");

        // Inject PostgreSQL
        Database postgres = new PostgreSQLDatabase();
        UserService service2 = new UserService(postgres);
        service2.addUser("Rajeev Kumar");
    }
}

/*
. Benefits of DIP 🎯
Loose Coupling → UserService does not care about which database is used.
Easily extendable → Adding MongoDBDatabase does not require touching UserService.
Testability → You can inject a fake/mock database for testing.
Works perfectly with Dependency Injection frameworks (like Spring).

 */
