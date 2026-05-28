package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T01_Behavioural_Design.L07_State_Pattern.Bad_Code;



/*
🎭 State Design Pattern
📘 Definition
- The State Pattern allows an object to alter its behavior when its internal state changes.

- It looks like the object has changed its class, but in reality, it just delegates behavior to different state objects.
 */

/*
👶 In Simple Words

Think of a fan:
    - When the fan is OFF, pressing the button turns it ON (low speed).
    - When it’s on Low, pressing again moves it to Medium.
    - From Medium → High → Off, and so on.

The button’s action depends on the current state.
Instead of writing messy if-else logic for each button press, we create separate state classes: OffState, LowState, MediumState, HighState.
 */

class Fan {
    private String state = "OFF";

    public void pressButton() {
        if (state.equals("OFF")) {
            System.out.println("Fan is now ON at Low Speed");
            state = "LOW";
        } else if (state.equals("LOW")) {
            System.out.println("Fan is now at Medium Speed");
            state = "MEDIUM";
        } else if (state.equals("MEDIUM")) {
            System.out.println("Fan is now at High Speed");
            state = "HIGH";
        } else if (state.equals("HIGH")) {
            System.out.println("Fan is now OFF");
            state = "OFF";
        }
    }
}


public class BadExample {
    public static void main(String[] args){
        Fan fan = new Fan() ;

        fan.pressButton(); // OFF -> LOW
        fan.pressButton(); // LOW -> MEDIUM
        fan.pressButton(); // MEDIUM -> HIGH
        fan.pressButton(); // HIGH -> OFF
    }
}




//🔴 Problems:
//Lots of if-else.
//Hard to add a new state (e.g., "Turbo").
//State logic mixed inside the Fan class → violates Single Responsibility Principle (SRP).