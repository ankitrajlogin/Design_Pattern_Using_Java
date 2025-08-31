package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L03_Abstract_Factory_Pattern.Good_Code;


// step 1 : abstract product interfaces
interface Button{
    void paint() ;
}

interface Checkbox{
    void paint() ;
}


// step 2 : Concrete products (windows family)
class WindowsButton implements Button{
    public  void paint(){
        System.out.println("Rendering Windows Button") ;
    }
}
class WindowsCheckbox implements Checkbox{
    public void paint(){
        System.out.println("Rendering Windows Checkbox");
    }
}

// step 3 : Concrete Products (Mac family)
class MacButton implements Button{
    public void paint(){
        System.out.println("Rendering Mac Button");
    }
}
class MacCheckbox implements Checkbox{
    public void paint(){
        System.out.println("Rendering Mac Checkbox");
    }
}


// step 4 : Abstract Factory
interface GUIFactory{
    Button createButton() ;
    Checkbox createCheckbox() ;
}


// Step 5 : Concrete Factories
class WindowsFactory implements GUIFactory{
    public Button createButton(){
        return new WindowsButton() ;
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory{
    public Button createButton(){
        return new MacButton() ;
    }
    public Checkbox createCheckbox(){
        return new MacCheckbox() ;
    }
}


// step 6 : Client code ( works with abstract factory)
class Application{
    private Button button ;
    private Checkbox checkbox ;

    public Application(GUIFactory factory){
        button = factory.createButton() ;
        checkbox = factory.createCheckbox() ;
    }

    public void renderUI(){
        button.paint() ;
        checkbox.paint() ;
    }
}


public class ApplicationRuntime {
    public static void main(String[] args) {
        // Windows
        GUIFactory windowsFactory = new WindowsFactory();
        Application winApp = new Application(windowsFactory);
        winApp.renderUI();

        // Mac
        GUIFactory macFactory = new MacFactory();
        Application macApp = new Application(macFactory);
        macApp.renderUI();
    }

}
