package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L06_FlyWeight_Pattern.BadCode;

/*
Flyweight Pattern Design
    - The Flyweight pattern is a structural design pattern that is used to minimize memory usage by sharing as much data as possible with similar objects.
    - It separates intrinsic state (shared/common data) from extrinsic state (unique per object) to reduce object creation.

*/

/*
Problem Statement
Suppose you’re making a game with 1 million trees in a forest.
Each tree has:
    - type (e.g., Oak, Pine, Mango 🌳)
    - color
    - texture
    - x, y position (location on the map)
 */

// BAD CODE - No Flyweight
class Tree {
    private String type;     // Intrinsic
    private String color;    // Intrinsic
    private String texture;  // Intrinsic
    private int x;           // Extrinsic
    private int y;           // Extrinsic

    public Tree(String type, String color, String texture, int x, int y) {
        this.type = type;
        this.color = color;
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        System.out.println("Drawing " + type + " tree at (" + x + ", " + y + ")");
    }
}


public class BadCode {
    public static void main(String[] args){

        Tree tr = new Tree("tall" , "red" , "dark" , 23 , 12) ;

        tr.draw() ;


    }
}
