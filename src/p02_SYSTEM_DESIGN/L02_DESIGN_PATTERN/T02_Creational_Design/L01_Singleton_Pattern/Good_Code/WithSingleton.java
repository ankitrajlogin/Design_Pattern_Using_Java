package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T02_Creational_Design.L01_Singleton_Pattern.Good_Code;

/*
🧩 Components of Singleton Pattern
Singleton Class → the class itself (e.g., Logger).
Private Constructor → prevents multiple objects.
Static Instance → stores the only object.
Global Access Method → getInstance() method.
 */

/*
🔄 Steps to Implement Singleton
1. Private static instance of the class.
2. Private constructor (to stop new Class() outside).
3. Public static method (getInstance()) to provide access.
4. Optional: Make it Thread-Safe if in multithreaded environment.
 */

class AppSettings{
    private String databaseUrl ;
    private String apikey ;

    // step 1 : private static instance
    private static AppSettings instance ;

    // step 2 : private constructor
    private AppSettings(){
        this.databaseUrl = "jdbc:mysql://localhost:3306/mydb" ;
        this.apikey = "12345-ABCDE" ;
    }

    // step 3 : public static method to get instance
    public static AppSettings getInstance(){
        if(instance  == null){
            instance = new AppSettings() ;
        }

        return instance ;
    }

    public String getDatabaseUrl(){
        return databaseUrl ;
    }

    public String getApikey(){
        return apikey ;
    }

    //    To prevent cloning of a Singleton object, you need to override the clone() method inside your Singleton class and throw an exception.
    protected AppSettings clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this singleton is not allowed");
    }
}

public class WithSingleton {
    public static void main(String[] args) {
        AppSettings settings1 = AppSettings.getInstance();
        AppSettings settings2 = AppSettings.getInstance();

        System.out.println(settings1 == settings2);  // true (same object!)
        System.out.println(settings1.getApikey());
        System.out.println(settings2.getApikey());
    }
}

// Singleton Pattern Goal

/*

🔑 Singleton Pattern Goal
A Singleton ensures that only one object of a class is created in the entire application and provides a global point of access to it.


📌 Step 1: Private Static Instance
```  private static AppSettings instance; ```

🔹 What it does:
    - Declares a class-level variable (static) that will hold the one and only instance of AppSettings.
    - Since it’s private, it cannot be accessed directly from outside the class. Only the class itself can control it.

🔹 Why needed:
    - Without this, we would have no way of storing the single object of the class once it’s created.
    - It ensures shared storage — no matter how many times you call getInstance(), you will always get the same object stored in this variable.

🔹 Related terms:
Static variable → belongs to the class, not objects. Shared across all instances.
Encapsulation → keeping it private enforces that only the class can manage its lifecycle.


📌 Step 2: Private Constructor
```
    private AppSettings() {
        this.databaseUrl = "jdbc:mysql://localhost:3306/mydb";
        this.apiKey = "12345-ABCDE";
    }
```
🔹 What it does:
    - Stops anyone from using new AppSettings() outside the class.
    - Initializes default settings (database URL, API key).

🔹 Why needed:
    - If the constructor were public, other classes could freely create new AppSettings(), which would break the Singleton principle by allowing multiple objects.

🔹 Related terms:
Constructor → special method that creates an object of the class.
Access modifier (private) → restricts access only to the class itself.
Encapsulation → ensures controlled object creation.


📌 Step 3: Public Static Method (getInstance)
```
    public static AppSettings getInstance() {
        if (instance == null) {
            instance = new AppSettings();
        }
        return instance;
    }
```
🔹 What it does:
Checks if the instance already exists:
    - If not created yet (null), it creates a new one.
    - If already exists, it simply returns the existing one.

🔹 Why needed:
    - This provides a global point of access to the Singleton object.
    - All other classes call AppSettings.getInstance() to use the object.
    - Guarantees lazy initialization → the object is created only when needed, not before.

🔹 Related terms:
Lazy Initialization → object is created only when it’s first needed.
Global Access Point → same method gives the same object everywhere.
Thread Safety Concern → in multithreaded environments, you may need synchronization here to prevent multiple objects being created.



🎯 Putting It All Together
step1 : Private static instance → stores the one object.
step2 : Private constructor → prevents outside code from creating new objects.
step3 : Public static method → controls access, ensures only one object is created and reused.


✅ End Result:
Only one AppSettings object exists in the application, and all parts of the program use the same configuration settings consistently.
 */


/*
✅ Valid way to prevent cloning in Singleton:
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Cloning of this singleton is not allowed");
}

🔎 Why this works?
    - By default, Object.clone() creates a new copy of the object, which breaks Singleton.
    - Overriding it and throwing CloneNotSupportedException ensures no new copy is created.
    - This way, the Singleton object is preserved.

 */
