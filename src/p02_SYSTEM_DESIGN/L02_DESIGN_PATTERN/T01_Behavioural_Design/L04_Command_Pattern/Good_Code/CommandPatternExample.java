package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T01_Behavioural_Design.L04_Command_Pattern.Good_Code;


/*
🏗️ Components of Command Pattern
Command Interface – declares method like execute().
ConcreteCommand – implements the command, binds it with a receiver.
Receiver – the actual object that performs the action (Light, Fan, TV).
Invoker – asks the command to carry out the request (RemoteControl).
Client – creates commands and assigns them.



⚒️ What we’re doing in the Command Pattern:
Encapsulating Actions → Instead of directly calling methods, we wrap them inside a Command class.
Decoupling → The caller (Invoker) doesn’t need to know the actual implementation.
Flexibility → We can log, undo, or queue commands.

 */


// Step 1 : Command Interface
interface Command{
    void execute() ;
}

// step 2 : Receiver
class Light{
    public void turnOn(){
        System.out.println("Light is ON");
    }

    public void turnOff(){
        System.out.println("Light is OFF") ;
    }
}

// Step 3 : Concrete Commands
class LightOnCommand implements Command{
    private Light light ;

    public LightOnCommand(Light light){
        this.light = light ;
    }

    public void execute(){
        light.turnOn() ;
    }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOff();
    }
}


// Step 4 : Invoker
class RemoteControl{
    private Command command ;

    public void setCommand(Command command){
        this.command = command ;
    }

    public void pressButton(){
        command.execute();
    }
}


// step 5 : Client
public class CommandPatternExample {
    public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        // ON
        remote.setCommand(lightOn);
        remote.pressButton();

        // OFF
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}


/*
🌀 Steps & Working of Command Pattern
1. Client creates command objects (LightOnCommand, LightOffCommand) and links them to receivers (Light).
2. Invoker (RemoteControl) holds the command and triggers it.
3. Command calls the receiver's method (turnOn, turnOff).
4. Receiver executes the action.

👉 So the remote doesn’t know if it’s controlling a Light, Fan, or TV—it just triggers a command.

 */