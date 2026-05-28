package p01_BASIC.T02_Solid_principle.L09_LSP_vs_ISP;


/*
1. Purpose
Class → Blueprint to create objects. It can hold state (fields/variables) and behavior (methods).
Interface → Contract/blueprint for behavior only. It says “any class implementing me must provide these methods”.
*/

/*
2. Fields
Class → Can have instance variables, constructors, static variables, etc.
Interface → Can only have public static final (constants). Every variable in an interface is by default:
 */

class Car {
    int speed = 0;  // instance variable
}
interface Vehicle {
    int MAX_SPEED = 120;  // constant
}



/*
3. Methods
Class
- Can have concrete (implemented) methods.
- Can also have abstract methods (if class is abstract).

Interface
- Traditionally (before Java 8): only abstract methods.
- From Java 8: can also have default and static methods.
- From Java 9: can have private methods (to reuse code inside interface).
 */




/*
4. Inheritance
class
- Can extend only one class (single inheritance).
- But can implement multiple interfaces.

Interface
- Can extend multiple interfaces.
- Cannot extend a class.

 */




public class Interface_vs_Class {

}

/*
Abstract class → Used when classes share common state (fields) + common behavior (methods).
Interface → Used to define a contract/capability (what a class can do), without enforcing how.


SUMMARY TABLE

| Feature              | Abstract Class                                 | Interface                                          |
| -------------------- | ---------------------------------------------- | -------------------------------------------------- |
| **Variables**        | Instance + static allowed                      | Only `public static final`                         |
| **Methods**          | Abstract + concrete + constructors             | Abstract + default + static (+private from Java 9) |
| **Inheritance**      | Single inheritance only                        | Multiple inheritance allowed                       |
| **Access Modifiers** | Can be public/protected/private                | Always public (for methods/fields)                 |
| **Use Case**         | “Is-a” relationship with shared state/behavior | “Can-do” capability (contract)                     |


👉 In practice:
Abstract class = “what it is” (base with state + partial behavior).
Interface = “what it can do” (contract for behavior).

 */
