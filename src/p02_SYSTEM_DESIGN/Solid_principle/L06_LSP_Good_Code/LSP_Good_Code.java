package p02_SYSTEM_DESIGN.Solid_principle.L06_LSP_Good_Code;

/*
Correct way to design this
We should not force all birds to have fly() if some don’t fly. Instead, separate abilities from general categories.
 */

interface Flyable {
    void fly();
}

class Bird {
    public void eat() {
        System.out.println("I can eat!");
    }
}

class Sparrow extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Sparrow flying...") ;
    }

    public void eat(){
        System.out.println("Sparrow Eating...") ;
    }
}

class Ostrich extends Bird {
    // Ostrich does not implement Flyable
    public void eat(){
        System.out.println("Ostrich eating...") ;
    }
}

public class LSP_Good_Code {
    public static void main(String[] args) {
        Sparrow sparrow = new Sparrow() ;
        Ostrich ostrich = new Ostrich() ;

        sparrow.fly() ;
        sparrow.eat() ;

        ostrich.eat() ;
        // ((Flyable) ostrich).fly(); ❌ Compile error (good, because ostrich can't fly)


    }
}

/*
Takeaway
Original code violates Liskov Substitution Principle (LSP).

Correct design:
Don’t put a method (fly) in the parent if it doesn’t apply to all subclasses.
Use interfaces or more abstract design (move instead of fly).

👉 So, Liskov essentially means “subclasses must behave like their parent, without breaking expectations.”
 */