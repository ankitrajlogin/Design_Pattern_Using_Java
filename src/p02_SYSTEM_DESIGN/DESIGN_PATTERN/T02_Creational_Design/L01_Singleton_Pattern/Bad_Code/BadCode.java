package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L01_Singleton_Pattern.Bad_Code;

/*
🔑 Singleton Pattern
📘 Definition:

The Singleton Pattern ensures that a class has only one instance throughout the program and provides a global point of access to it.

 */


/*
In many applications, some classes must have only one instance throughout the lifecycle.

Example scenarios:
    - Logger: only one logger should write to the log file, otherwise multiple loggers could overwrite each other’s logs.
    - App Settings / Config: API keys, DB URLs, etc. should exist in one place. If multiple copies exist, they may hold different values → inconsistency.
    - Database Connection: Only one connection pool should exist to avoid resource overuse.

⚠️ If we allow multiple instances:
    1. Inconsistent state: different objects may hold different configurations.
    2. Resource conflicts: multiple loggers writing to the same file → corruption.
    3. Performance issues: imagine each object uses 100MB memory; if 5 objects are created → 500MB wasted.

So we need a way to guarantee one single instance.
That’s what the Singleton Pattern solves.
 */

class AppSettings{
    private String databaseUrl ;
    private String apiKey ;

    public AppSettings(){
        // Read settings from a config file
        this.databaseUrl = "jdbc:mysql://localhost:3306/mydatabase" ;
        this.apiKey = "12345-ABCDE" ;
    }

    public String getDatabaseUrl(){
        return databaseUrl ;
    }

    public String getApiKey(){
        return apiKey ;
    }
}

// withoutsingleton
public class BadCode {
    public static void main(String[] args) {
        AppSettings settings1 = new AppSettings();
        AppSettings settings2 = new AppSettings();

        System.out.println(settings1 == settings2);  // false (two different objects!)
        System.out.println(settings1.getApiKey());
        System.out.println(settings2.getApiKey());
    }


}

/*
❌ Problems
    - Two different objects (settings1 and settings2) are created.
    - They consume memory unnecessarily.
    - If setters were allowed, they could hold different values → inconsistent state.

 */


