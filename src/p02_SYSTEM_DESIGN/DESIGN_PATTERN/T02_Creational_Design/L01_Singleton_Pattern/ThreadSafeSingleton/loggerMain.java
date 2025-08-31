package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L01_Singleton_Pattern.ThreadSafeSingleton;


class Logger{
    private static Logger instance ;

    private Logger(){

    }

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger() ;
        }

        return instance ;
    }

    public void log(String msg){
        System.out.println("LOG: " + msg);
    }

//    To prevent cloning of a Singleton object, you need to override the clone() method inside your Singleton class and throw an exception.
    protected Logger clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this singleton is not allowed");
    }
}


class ThreadSafeLogger{
    // step1 : volatile ThreadSafeLogger instance
    private static volatile ThreadSafeLogger instance ;

    // step2 : private constuctor
    private ThreadSafeLogger(){}

    // step3 : double-checked locking in getInstance() ;
    public static ThreadSafeLogger getInstance(){
        if(instance == null){                              // check 1
            synchronized (ThreadSafeLogger.class){         // creating synchronized
                if(instance == null){                      // check already previous threaded created the instance or not
                    instance = new ThreadSafeLogger() ;
                }
            }
        }

        return instance ;
    }

    public void log(String msg){
        System.out.println("LOG: " + msg) ;
    }
}

public class loggerMain {
    public static void main(String[] args){
        ThreadSafeLogger l1 = ThreadSafeLogger.getInstance();
        ThreadSafeLogger l2 = ThreadSafeLogger.getInstance();

        l1.log("User logged in");
        l2.log("Error occurred");

        System.out.println(l1 == l2); // true (same object)

    }
}


/*
 🔹 Explanation of Steps
 Step 1: ``` private static volatile ThreadSafeLogger instance; ```
 What it is:
     - private → No outside class can directly access it.
     - static → Belongs to the class itself (not objects), so only one copy exists in JVM.
     - volatile → Ensures that when multiple threads read/write this variable, they always see the latest value.Without volatile, one thread might see a half-initialized object due to instruction reordering.

 Why needed?
    - Prevents memory consistency issues in multithreaded environments.

 Related term:
    - Volatile Keyword → Guarantees visibility of changes across threads.
 Shared Memory Problem → Without volatile, threads might read stale values from CPU cache.



 Step 2: ``` private ThreadSafeLogger() {} ```

 What it is:
    - Private constructor ensures that no external class can instantiate the logger using new.

 Why needed?
    - Enforces that the only way to create/get an instance is via getInstance() method.

 Related term:
    - Encapsulation → Restricting direct creation.
    - Singleton Principle → Ensures one object only.




 Step 3: public static ThreadSafeLogger getInstance()
 What it is:
    - Provides a global access point to get the unique instance.

 Key Part → Double-Checked Locking
 ```
 if (instance == null) {              // Check 1 (fast, avoids unnecessary locking)
     synchronized (ThreadSafeLogger.class) {   // Lock for thread safety
         if (instance == null) {      // Check 2 (ensures only one thread creates it)
             instance = new ThreadSafeLogger();
         }
     }
 }
 ```

    - First Check: Quick performance boost (most calls skip synchronization once instance is created).
 Synchronization: Only one thread can enter at a time, preventing multiple creations.
 Second Check: Even if two threads reach the outer check, only one creates the instance.

 Why needed?
    - Guarantees both lazy initialization (created only when needed) and thread safety.

 Related terms:
    - Double-Checked Locking → Optimization for multi-threaded singletons.
    - Lazy Initialization → Object created only when requested (not at class load time).
    - Critical Section → The block of code inside synchronized.

 */
