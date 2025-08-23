package p01_BASIC.UML;

/*
What is Composition?
Composition is a stronger form of Association.
It also represents a “Has-A” relationship, but with strong ownership.
If the container (whole) is destroyed, the contained objects (parts) are also destroyed.
In UML, it is shown with a filled diamond (◆) at the container side.
👉 Think of it as: Lifetime of the part depends completely on the whole.


Real-Life Example
A House HAS-A Room.
If the House is destroyed, the Rooms are also destroyed.
Rooms cannot exist independently without the House.

        +-------------+ ◆──────────────+-------------+
        |   House     |                |    Room     |
        +-------------+                +-------------+
        | - houseName |                | - name      |
        | - rooms[]   |                +-------------+
        +-------------+                | + display() |
        | + display() |                +-------------+
        +-------------+


The filled diamond (◆) is on the House side.
This shows House strongly owns Rooms.
Rooms cannot exist without House.

 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Part class
class Room {
    String type;   // e.g., Bedroom, Kitchen
    int count;     // number of such rooms

    Room(String type, int count) {
        this.type = type;
        this.count = count;
    }

    void display() {
        System.out.println(count + " " + type + "(s)");
    }
}

// Whole class (Composition)
class House {
    String houseName;
    private List<Room> rooms;  // House strongly owns its Rooms

    // Constructor builds rooms based on input
    House(String houseName, int numBedrooms, int numKitchens, int numBathrooms) {
        this.houseName = houseName;
        rooms = new ArrayList<>();

        if (numBedrooms > 0) rooms.add(new Room("Bedroom", numBedrooms));
        if (numKitchens > 0) rooms.add(new Room("Kitchen", numKitchens));
        if (numBathrooms > 0) rooms.add(new Room("Bathroom", numBathrooms));
    }

    void display() {
        System.out.println("House: " + houseName);
        System.out.println("Contains:");
        for (Room r : rooms) {
            r.display();
        }
    }
}

// Main class
public class CompositionExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input from user
        String houseName = "Ankit's House" ;
        Integer bedrooms = 1 ;
        Integer kitchens = 3 ;
        Integer bathrooms = 2 ;

        // Create House object
        House house = new House(houseName, bedrooms, kitchens, bathrooms);

        // Display details
        house.display();

        sc.close();
    }
}

/*
Why is this Composition?
House creates and owns its Room objects inside its constructor.
If the House object is destroyed, the Room objects will also be destroyed.
Room cannot exist without the House → strong Composition.
 */