package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L07_State_Pattern.Good_Code;

/*
🔑 Components of State Pattern:
    - State (Interface) → Declares behavior methods.
    - Concrete States → Implement different behaviors for each state.
    - Context (the main class) → Maintains current state and delegates actions to it.
 */


/*
⚙️ Steps to Implement State Pattern
    1. Identify states of the object.
    2. Example: NoCoin, HasCoin, SoldOut.
    3. Create a State interface with common methods.
    4. Implement Concrete State classes for each state.
    5. Create Context class that has a reference to the current state.
    6. Delegate behavior to the current state instead of using if-else.
    7. Allow state transitions by changing the state reference in Context.
 */


// 1. define the abstraction
interface State{
    void pressButton(Fan fan) ;
}

// step 2 : Define the context
class Fan{
    private State currentState ;

    public Fan(){
        currentState = new OffState() ; // Default
    }

    public void setState(State state){
        this.currentState = state ;
    }
    public void pressButton(){
        currentState.pressButton(this);
    }
}


// Step 3: Define concrete states
class OffState implements State {
    public void pressButton(Fan fan) {
        System.out.println("Fan is now ON at Low Speed");
        fan.setState(new LowState());
    }
}
class LowState implements State {
    public void pressButton(Fan fan) {
        System.out.println("Fan is now at Medium Speed");
        fan.setState(new MediumState());
    }
}

class MediumState implements State {
    public void pressButton(Fan fan) {
        System.out.println("Fan is now at High Speed");
        fan.setState(new HighState());
    }
}

class HighState implements State {
    public void pressButton(Fan fan) {
        System.out.println("Fan is now OFF");
        fan.setState(new OffState());
    }
}




public class FanStateMain {
    public static void main(String[] args) {
        Fan fan = new Fan();

        fan.pressButton(); // OFF -> LOW
        fan.pressButton(); // LOW -> MEDIUM
        fan.pressButton(); // MEDIUM -> HIGH
        fan.pressButton(); // HIGH -> OFF
    }
}

/*
✨ Advantages
    - Removes complex if-else chains.
    - Easy to add new states (just create a new class).
    - Clean separation of concerns (each state handles its own logic).
    - Makes system more maintainable & scalable.

🔗 Related Terms
Context → Object whose behavior changes (Fan).
State → Interface/abstract class representing behavior.
Concrete States → Implementations (OffState, LowState…).

 */