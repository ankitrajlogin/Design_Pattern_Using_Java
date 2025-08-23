package p02_SYSTEM_DESIGN.Solid_principle.L07_ISP_Bad_Code;

/*
📌 What is the Interface Segregation Principle (ISP)?

👉 The Interface Segregation Principle states:
 "No client should be forced to depend on methods it does not use."

In simpler words:
Don’t make fat interfaces (large interfaces with too many methods).
Instead, break them down into smaller, specific interfaces, so that classes only implement what they actually need.

 */

import java.awt.*;

// A "fat" interface
interface Worker {
    void work();
    void eat();
    void sleep();
}

// Robot also needs to be a "Worker", but problem arises
class HumanWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Human is working...");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating...");
    }

    @Override
    public void sleep() {
        System.out.println("Human is sleeping...");
    }
}

class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Robot is working...");
    }

    @Override
    public void eat() {
        // ❌ Problem: Robots don’t eat, but still forced to implement
        throw new UnsupportedOperationException("Robots don’t eat!");
    }

    @Override
    public void sleep() {
        // ❌ Problem: Robots don’t sleep
        throw new UnsupportedOperationException("Robots don’t sleep!");
    }
}



public class ISP_Bad_Code {
    public static void main(String []args){
        HumanWorker ankit = new HumanWorker() ;
        RobotWorker alisa = new RobotWorker() ;

        ankit.work() ;
        ankit.eat() ;
        ankit.sleep() ;

        alisa.work() ;
//        alisa.eat() ; // Robots are forced to implement eat() and sleep() even though it doesn’t make sense.


    }
}

/*
❌ Why this code gives an error:

- The `RobotWorker` class is forced to implement `eat()` and `sleep()` because it implements the `Worker` interface.
- In `RobotWorker`, both `eat()` and `sleep()` throw `UnsupportedOperationException`.
- If you call `alisa.eat()` or `alisa.sleep()`, it will throw a runtime exception (`UnsupportedOperationException`), causing your program to crash.
- This happens because the interface is too broad ("fat"), violating the Interface Segregation Principle (ISP).

*/
