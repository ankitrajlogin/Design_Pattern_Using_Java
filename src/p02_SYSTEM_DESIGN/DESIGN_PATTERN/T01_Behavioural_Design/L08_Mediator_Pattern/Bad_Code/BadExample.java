package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L08_Mediator_Pattern.Bad_Code;


/*
🎯 Mediator Pattern
📘 Definition:
Mediator Pattern is a behavioral design pattern that reduces the chaotic dependencies between multiple objects.
Instead of objects talking directly to each other, they communicate through a Mediator (a central controller).



📝 Problem Statement:
In an Air Traffic Control System (ATC), multiple airplanes need to coordinate when taking off and landing.

❌ Without a mediator:
    - Each airplane talks directly with every other airplane to check if the runway is free.
    - This creates complex dependencies (every airplane needs to know about all others).

✅ With a mediator (ATC tower):
    - Airplanes don’t talk to each other directly.
    - They communicate only with the ATC tower, which acts as the mediator.
    - ATC decides which plane can take off or land.
 */

class Airplane {
    private String name;
    private boolean runwayFree = true;

    public Airplane(String name) {
        this.name = name;
    }

    public void requestTakeOff(Airplane otherPlane) {
        if (otherPlane.isRunwayFree()) {
            System.out.println(name + " is taking off.");
            otherPlane.setRunwayFree(false);
        } else {
            System.out.println(name + " waiting, runway is busy.");
        }
    }

    public boolean isRunwayFree() {
        return runwayFree;
    }

    public void setRunwayFree(boolean free) {
        this.runwayFree = free;
    }
}

public class BadExample {
    public static void main(String[] args) {
        Airplane plane1 = new Airplane("Flight 101");
        Airplane plane2 = new Airplane("Flight 202");

        plane1.requestTakeOff(plane2);
        plane2.requestTakeOff(plane1); // tightly coupled, messy
    }
}



/*
🔴 Problems:
    - Airplanes need to know about each other directly.
    - Adding a new airplane = updating all others.
    - Hard to manage complex coordination (landing + takeoff).
 */
