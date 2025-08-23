package p02_SYSTEM_DESIGN.Solid_principle.L08_ISP_Good_Code;



interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

// Humans can work, eat, and sleep
class HumanWorker implements Workable, Eatable, Sleepable {
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

// Robots only work
class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working...");
    }
}



public class ISP_Good_Code {
    public static void main(String []args){
        HumanWorker ankit = new HumanWorker() ;
        RobotWorker alisa = new RobotWorker() ;

        ankit.work() ;
        ankit.eat() ;
        ankit.sleep() ;

        alisa.work() ;
    }
}

/*
🎯 Key Takeaways
- Fat Interface Problem → Clients forced to implement methods they don’t need.
- Solution → Split into smaller, role-specific interfaces.
Benefits:
- Cleaner, more understandable code.
- No meaningless/empty/unsupported methods.
- Classes only depend on what they truly need.


🔧 How to Use ISP in Real Projects
In Java, instead of creating one big interface (e.g., Animal with fly(), swim(), run()), break it into specific capability interfaces (Flyable, Swimmable, Runnable).
In Spring Framework, you’ll often see multiple small interfaces like CrudRepository, JpaRepository, etc., instead of one giant interface.

 */


