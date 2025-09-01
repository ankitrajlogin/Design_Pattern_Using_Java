package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L05_Prototype_Pattern_Design.Good_Code;


/*
🔹 Explanation of Good Code
    - Prototype Interface (Prototype) → defines clone() method.
    - Concrete Class (GameCharacter) → implements cloning logic.
    - Prototype Registry (CharacterRegistry) → keeps predefined objects (prototypes).
    - Client (Main) → requests new objects by cloning prototypes.

🔹 Steps to Implement Prototype Pattern
    - Define a Prototype interface with clone().
    - Implement concrete classes that support cloning.
    - Create a registry (optional) to manage prototypes.
    - Client clones objects instead of creating them with new.

🔹 Components of Prototype Pattern
    - Prototype → interface declaring clone().
    - Concrete Prototype → implements clone().
    - Client → uses clone() to create new objects.
    - Registry (optional) → stores prebuilt prototypes.

🔹 Related Terms
    - Shallow Copy: Copies object but references same nested objects.
    - Deep Copy: Copies object along with all nested objects.
    - Prototype Registry: Storage of common prototypes for reuse.
 */

import java.util.HashMap;
import java.util.Map;

// Prototype interface
interface Prototype{
    Prototype clone() ;
}

// GameCharacter class Implements prototype
class GameCharacter implements Prototype{
    String type ;
    int health ;
    String weapon ;
    String ability ;

    public GameCharacter(String type , int health , String weapon , String ability){
        System.out.println("Loading resources for: " + type) ;
        this.type = type ;
        this.health = health ;
        this.weapon = weapon ;
        this.ability = ability ;
    }

    @Override
    public Prototype clone(){
        return new GameCharacter(type , health , weapon , ability) ;
    }

    public void showInfo(){
        System.out.println(type + " with " + weapon + " and ability: " + ability);
    }
}

// Prototype Registry (Factory + Prototype)
class CharacterRegistry{
    private Map<String , GameCharacter> prototypes = new HashMap<>() ;

    public void addPrototype(String key , GameCharacter character){
        prototypes.put(key,character) ;
    }

    public GameCharacter getClone(String key){
        return (GameCharacter) prototypes.get(key).clone() ;
    }
}


public class PrototypePatternGame {
    public static void main(String[] args) {
        CharacterRegistry registry = new CharacterRegistry();

        // Load expensive characters once
        GameCharacter warriorPrototype = new GameCharacter("Warrior", 100, "Sword", "Shield Block");
        GameCharacter magePrototype = new GameCharacter("Mage", 80, "Staff", "Fireball");

        registry.addPrototype("warrior", warriorPrototype);
        registry.addPrototype("mage", magePrototype);

        // Create new objects by cloning
        GameCharacter warrior1 = registry.getClone("warrior");
        GameCharacter warrior2 = registry.getClone("warrior");
        GameCharacter mage1 = registry.getClone("mage");

        warrior1.showInfo();
        warrior2.showInfo();
        mage1.showInfo();
    }
}


/*

🔹 Benefits of Prototype Pattern

✔ Avoids costly object creation.
✔ Reduces duplicate initialization code.
✔ Provides flexibility (easy to create variations).
✔ Easy to add new object types without changing client code.

🔹 Other Use Cases
    - Document Editors → duplicate formatting/style objects.
    - Game Development → clone enemies, bullets, characters.
    - GUI Frameworks → clone buttons, menus with styles.
    - Machine Learning Models → copy model configurations.
    - Database Connections → clone configurations instead of reinitializing.
 */



// ***** IMPORTANT QUESTION **************

/*
1. What if GameCharacter does not have an entry in the prototypes map?
prototypes.get(key) will return null if the key is not found.
Then the code becomes:
        return (GameCharacter) null.clone();

→ This will throw a NullPointerException at runtime, because you’re calling .clone() on null.




2. Why do we need (GameCharacter) casting?
The prototypes map is declared like this:
 -    private Map<String, Prototype> prototypes = new HashMap<>();

So prototypes.get(key) returns a Prototype object, not specifically a GameCharacter.

The Prototype interface defines:
  -   Prototype clone();

So .clone() also returns a Prototype, not a GameCharacter.
But in this factory, we want an actual GameCharacter.
Example: Warrior or Mage (which implement GameCharacter).

So we do a downcast:
  -   (GameCharacter) prototype.clone();

This tells the compiler:
👉 “Trust me, the object returned here is really a GameCharacter, not just a generic Prototype.”

If we don’t cast, the return type would be Prototype, which may not have all the methods of GameCharacter.
 */


/*
🔑 Key Takeaways
- If key is missing → NullPointerException. Better to throw a custom exception.
- Cast (GameCharacter) is needed because:
    - The interface return type is Prototype
    - But we need the concrete type (GameCharacter)

- Alternative: You could use generics to avoid explicit casting.
 */