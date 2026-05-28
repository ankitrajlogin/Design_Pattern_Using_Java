package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T01_Behavioural_Design.L08_Mediator_Pattern.Good_Code;

/*
✅ With a mediator (ATC tower):
- Airplanes don’t talk to each other directly.
- They communicate only with the ATC tower, which acts as the mediator.
- ATC decides which plane can take off or land.


🔑 Components of Mediator Pattern in this Example:
1. Mediator (Interface) → Defines communication methods.
    Example: AirTrafficControl

2. Concrete Mediator → Implements the logic of coordination.
    Example: ATCMediator

3. Colleague (Component) → Objects that want to communicate.
    Example: Airplane

4. Concrete Colleagues → Specific implementations of colleagues.
    Example: Flight101, Flight202

 */

/*
⚙ Steps to Implement Mediator Pattern:
1. Identify communication chaos (too many objects talking directly).
2. Create Mediator Interface → defines communication methods.
3. Create Concrete Mediator → centralizes communication logic.
4. Update Colleagues (components) → only talk to mediator, not each other.
5. Use mediator in client → keeps system loosely coupled.
 */


// Mediator Interface
interface AirTrafficeControl{
    void requestTakeOff(Airplane airplane) ;
    void notifyRunwayFree() ;
}

// Concrete Mediator
class ATCMediator implements AirTrafficeControl{
    private boolean runwayFree = true ;

    @Override
    public void requestTakeOff(Airplane airplane){
        if(runwayFree){
            System.out.println(airplane.getName() + " is taking off");
            runwayFree = false ;
        }
        else{
            System.out.println(airplane.getName() + " waiting , runway is busy");
        }
    }

    @Override
    public void notifyRunwayFree(){
        System.out.println("Runway is now free.");
        runwayFree = true ;
    }

}

// Collegaue
class Airplane{
    private String name ;
    private AirTrafficeControl atcMediator ;

    public Airplane(String name , AirTrafficeControl mediator){
        this.name = name ;
        this.atcMediator = mediator ;
    }

    public String getName(){
        return name ;
    }

    public void requestTakeOff(){
        atcMediator.requestTakeOff(this) ;
    }

    public void finishedTakeOff(){
        System.out.println(name + " has finished taking off");
        atcMediator.notifyRunwayFree();
    }
}



public class GoodExample {
    public static void main(String[] args){
        ATCMediator atc = new ATCMediator() ;

        Airplane plane1 = new Airplane("Flight 101" , atc) ;
        Airplane plane2 = new Airplane("Flight 202" , atc) ;

        plane1.requestTakeOff();
        plane2.requestTakeOff();
        plane1.finishedTakeOff();
        plane2.requestTakeOff();
    }
}


/*
✨ Why This is Better?
    - Loose coupling: Airplanes don’t know about each other.
    - Centralized control: ATC manages rules for takeoff/landing.
    - Easier to extend: Add 10 more airplanes → no changes in existing ones.
    - Real-world analogy: Works exactly like real air traffic control.
 */


/*
Difference between Observer and Mediator
| Aspect                  | Observer                                | Mediator                                   |
| ----------------------- | --------------------------------------- | ------------------------------------------ |
| **Communication Style** | One-to-many (Publisher → Subscribers)   | Many-to-many, but centralized via Mediator |
| **Focus**               | Automatic state update across observers | Centralizing communication logic           |
| **Coupling**            | Decouples subject from observers        | Decouples colleagues by using mediator     |
| **Example**             | YouTube channel & subscribers           | Air traffic control tower                  |



🔑 In short:
    - Use Observer when you want dependent objects to automatically update on state change.
    - Use Mediator when you want to simplify complex many-to-many communication into a single hub.
 */