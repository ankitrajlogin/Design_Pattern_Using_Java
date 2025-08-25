package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L05_Template_Method_Pattern.Bad_Code;



/*
1. Definition
The Template Method Pattern defines the skeleton of an algorithm in a base class (abstract class), but lets the subclasses override some steps of the algorithm without changing its overall structure.

👉 Think of it like a recipe: The recipe tells you the steps (template), but you can change ingredients or specific steps depending on the dish.

2. Real-life Example
Imagine you are making tea and coffee.
Both follow almost the same steps:
1. Boil water
2. Brew (tea leaves or coffee powder)
3. Pour into a cup
4. Add condiments (milk/sugar/lemon, etc.)

The sequence is fixed (skeleton), but "brewing" and "adding condiments" are different.
So the template (recipe) is in the base class, and the variation is in the subclasses.

 */

class Tea {
    public void prepareTea() {
        System.out.println("Boiling water");
        System.out.println("Steeping the tea");
        System.out.println("Pouring into cup");
        System.out.println("Adding lemon");
    }
}

class Coffee {
    public void prepareCoffee() {
        System.out.println("Boiling water");
        System.out.println("Brewing coffee grinds");
        System.out.println("Pouring into cup");
        System.out.println("Adding sugar and milk");
    }
}

public class BadExample {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareTea();

        Coffee coffee = new Coffee();
        coffee.prepareCoffee();
    }
}

/*
🚫 Problem:
Lots of duplicate code (boil water, pour into cup).
If we change one step (like "Boiling water"), we must update in all classes.
Not flexible.

 */
