package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L05_Template_Method_Pattern.Good_Code;

/*
Components of Template Method Pattern

1. Abstract Class (Base Class)
-> Defines the template method (the skeleton of the algorithm).
-> Has some abstract methods (to be implemented by subclasses).

2. Concrete Subclasses
-> Implement the abstract methods.
-> Customize the steps.

3. Template Method
-> The main algorithm defined in the abstract class.
-> Calls abstract methods for steps that vary.
 */


/*
Steps to Write Template Method Pattern
1. Identify the common steps in your algorithm.
2. Put those steps in an abstract base class.
3. Define the template method (skeleton of algorithm).
4. Make abstract methods for the variable parts.
5. Create subclasses that implement those abstract methods.
6. Use the template method in the client to execute the algorithm.
 */


// Abstract Class
abstract class Beverage{
    public final void prepareRecipe(){
        boilWater() ;
        brew() ;
        pourInCup() ;
        addCondiments()  ;
    }

    // hook --> optionally override (as we use default implementation)
    private void boilWater(){
        System.out.println("Boiling water") ;
    }

    private void pourInCup(){
        System.out.println("Pouring into cup") ;
    }

    // Abstract methods ( to be implemented by subclasses )
    protected abstract void brew() ;
    protected abstract void addCondiments() ;
}


// Concrete Class for Tea
class Tea extends Beverage{
    @Override
    protected void brew(){
        System.out.println("Steeping the tea");

    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
}

class Coffee extends Beverage{

    @Override
    protected void brew() {
        System.out.println("Brewing coffee grinds");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

// Client
public class TemplateMethodmain {
    public static void main(String[] args){
        Beverage tea = new Tea() ;
        tea.prepareRecipe();

        System.out.println("-------------------------") ;

        Beverage coffee = new Coffee() ;
        coffee.prepareRecipe();
    }
}


/*
Where to Use
- When you have algorithms with many common steps but some steps vary.

Example use cases:
- Beverage preparation (Tea/Coffee)
- Game flow (start → play → end)
- Data parsers (XML, JSON, CSV)
- Document generation (PDF, HTML, DOC)
 */
