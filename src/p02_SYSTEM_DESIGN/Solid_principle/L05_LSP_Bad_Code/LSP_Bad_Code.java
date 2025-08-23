package p02_SYSTEM_DESIGN.Solid_principle.L05_LSP_Bad_Code;

/*
🔵 Liskov Substitution Principle (LSP)
👉 Definition (by Barbara Liskov):
Objects of a superclass should be replaceable with objects of its subclasses without breaking the application’s behavior.

Or in simple terms:
✅ If B is a subclass of A, then anywhere A is expected, we should be able to use B without weird side effects.

🔴 Why LSP is Important?
Ensures polymorphism works correctly.
Prevents broken inheritance hierarchies.
Promotes reliable substitution of subtypes.
Avoids runtime surprises (like exceptions or wrong behavior).
*/

class Bird {
    public void fly() {
        System.out.println("I can fly!");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow flying...");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly!");
    }
}



public class LSP_Bad_Code {
    public static void main(String[] args) {
        // Both are birds
        Bird sparrow = new Sparrow();
        Bird ostrich = new Ostrich();

        sparrow.fly() ;
        // ostrich.fly() ;
        // Ostrich is not Flyable, so we never expect it to fly
    }
}

/*
⚠ Problem:
Ostrich is a Bird, but it cannot behave like one.
If some method expects a Bird and calls fly(), passing an Ostrich will break.


Why LSP is violated here?

- LSP definition: Subclasses should be substitutable for their parent classes without altering the correctness of the program.

In your code:
- Bird promises that every bird can fly().
- But when you substitute Bird with Ostrich, the method fly() breaks (it throws an exception).
- So, Ostrich is not a proper subtype of Bird.
This breaks LSP.
 */