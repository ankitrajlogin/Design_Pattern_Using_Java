package p02_SYSTEM_DESIGN.Solid_principle.L09_LSP_vs_ISP;

/*
🔹 Liskov Substitution Principle (LSP)
👉 “Subtypes must be substitutable for their base types.”
- Focus: Correctness of subclass behavior.
- A subclass should not violate the expectations of its parent class/interface.
- Clients using the parent type should not need to know if they’re dealing with a subclass.
- Violation Example: A subclass overrides methods but changes the meaning/contract (e.g., a Square misbehaves when used as a Rectangle).

✅ LSP ensures behavioral correctness of inheritance.
 */





/*
🔹 Interface Segregation Principle (ISP)
👉 “Clients should not be forced to depend on methods they do not use.”
- Focus: Correctness of interface design.
- Instead of having one large, “fat” interface, we split it into smaller, more specific ones.
- Subclasses (implementations) then only implement what they actually need.
- Violation Example: A Bird interface with fly() + swim(). If Penguin implements it, it’s forced to implement fly(), which is irrelevant.

✅ ISP ensures clean, focused contracts for implementations.
 */


/*
Key Difference in Motive

LSP:
- Ensures a subclass behaves correctly when used in place of its parent.
- It’s about substitution and behavior consistency.

ISP:
- Ensures interfaces are well-designed so that subclasses aren’t forced into irrelevant implementations.
- It’s about not forcing responsibilities that don’t belong.

 */



// General Bird class
class Bird {
    void fly() {
        System.out.println("Bird is flying...");
    }

    void swim() {
        System.out.println("Bird is swimming...");
    }
}







public class LSP_vs_ISP {
}

/*
Difference Summarized

LSP (Correctness of Subclassing):
- Ensures subclasses truly behave like their parent without breaking expectations. (Focus: behavior consistency).
- Fixed by restructuring hierarchy.

ISP (Design of Interfaces):
- Ensures classes are not forced to implement methods they don’t need. (Focus: interface design).
- Fixed by splitting interfaces.


 */