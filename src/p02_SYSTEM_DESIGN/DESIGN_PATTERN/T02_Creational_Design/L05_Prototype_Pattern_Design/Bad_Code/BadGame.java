package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L05_Prototype_Pattern_Design.Bad_Code;


/*
🔹 What is the Prototype Design Pattern?
    - Prototype Pattern is a creational design pattern.
    - It allows you to create new objects by copying (cloning) an existing object (prototype) instead of creating them from scratch.
    - This is useful when the cost of creating a new object is high (e.g., complex initialization, expensive DB calls, network-heavy operations).
 */

/*
🔹 Problem Statement
    - Imagine we’re building a Game with Characters (Warrior, Mage, Archer).
    - Each character has weapons, health, skills, and experience level.
    - Creating a character involves loading assets from a server, setting up abilities, initializing AI data → all of this is costly.
 */




// Character class
class GameCharacter {
    String type;
    int health;
    String weapon;
    String ability;

    public GameCharacter(String type, int health, String weapon, String ability) {
        // Imagine this constructor loads from DB or server (expensive)
        System.out.println("Loading resources for: " + type);
        this.type = type;
        this.health = health;
        this.weapon = weapon;
        this.ability = ability;
    }

    public void showInfo() {
        System.out.println(type + " with " + weapon + " and ability: " + ability);
    }
}

// Main
public class BadGame {
    public static void main(String[] args) {
        GameCharacter warrior1 = new GameCharacter("Warrior", 100, "Sword", "Shield Block");
        GameCharacter warrior2 = new GameCharacter("Warrior", 100, "Sword", "Shield Block");
        GameCharacter mage1 = new GameCharacter("Mage", 80, "Staff", "Fireball");

        warrior1.showInfo();
        warrior2.showInfo();
        mage1.showInfo();
    }
}


/*
⚠️ Problem in Bad Code:
    - Every time we create a new Warrior or Mage, it re-runs heavy initialization.
    - Duplicate code → Hard to maintain.
    - Performance overhead → Loading resources again and again.
 */