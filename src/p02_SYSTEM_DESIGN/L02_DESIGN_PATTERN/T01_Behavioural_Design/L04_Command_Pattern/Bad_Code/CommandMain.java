package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T01_Behavioural_Design.L04_Command_Pattern.Bad_Code;



/*
🔹 Command Pattern
📌 Definition:
Command Pattern is a behavioral design pattern that turns a request (operation) into a standalone object containing all the details of the request.
This lets you parameterize objects with operations, delay execution, and support undo/redo.

🧑‍🏫 Simple Words
Imagine a remote control for your TV.
- When you press Power ON, a signal (command) is sent to the TV.
- remote doesn’t know how the TV works internally, it just issues the command.
- Tomorrow, if the same button controls a projector instead of a TV, you don’t need to change the remote—just change the command object.

👉 Command Pattern = Remote Control for actions.

 */


// Receiver classes
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

class RemoteControl {
    private Light light;

    public RemoteControl(Light light) {
        this.light = light;
    }

    public void pressButton(String action) {
        if (action.equals("ON")) {
            light.turnOn();
        } else if (action.equals("OFF")) {
            light.turnOff();
        }
    }
}

public class CommandMain {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl(light);

        remote.pressButton("ON");
        remote.pressButton("OFF");
    }
}


/*
❌ Problems:
Remote is tightly coupled with Light.
Adding a Fan or TV means modifying RemoteControl → violates Open/Closed Principle.
Hard to implement Undo/Redo.

 */
