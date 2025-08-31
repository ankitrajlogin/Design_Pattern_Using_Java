package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L03_Abstract_Factory_Pattern.Bad_Code;


/*
🔹 Abstract Factory Pattern
✅ Definition:

Abstract Factory is a creational design pattern that provides an interface to create families of related or dependent objects, without specifying their concrete classes.

👉 Think of it as a “factory of factories.”


🎯 Use of Abstract Factory
    - When you want to create related objects together (e.g., Button + Checkbox for Windows or Mac).
    - To ensure compatibility between products.
    - To keep the code open for extension but closed for modification (OCP).
 */


// BAD CODE
class Button {
    public void paint() {
        System.out.println("Default Button");
    }
}

class WindowsButton extends Button {
    public void paint() {
        System.out.println("Windows Button");
    }
}

class MacButton extends Button {
    public void paint() {
        System.out.println("Mac Button");
    }
}

class Checkbox {
    public void paint() {
        System.out.println("Default Checkbox");
    }
}

class WindowsCheckbox extends Checkbox {
    public void paint() {
        System.out.println("Windows Checkbox");
    }
}

class MacCheckbox extends Checkbox {
    public void paint() {
        System.out.println("Mac Checkbox");
    }
}

// ❌ Bad Client Code
class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(String osType) {
        if (osType.equalsIgnoreCase("windows")) {
            button = new WindowsButton();
            checkbox = new WindowsCheckbox();
        } else if (osType.equalsIgnoreCase("mac")) {
            button = new MacButton();
            checkbox = new MacCheckbox();
        }
    }

    public void renderUI() {
        button.paint();
        checkbox.paint();
    }
}


public class ApplicationRun {
    public static void main(String[] args) {
       String osType = "mac" ;

       Application app = new Application(osType) ;
       app.renderUI();

       app = new Application("windows") ;
       app.renderUI();



    }
}


/*

❌ Problems with Bad Code

1. Tight coupling – Client code (Application) knows about all concrete classes (WindowsButton, MacButton, etc).

2. Difficult to scale – If we add LinuxButton and LinuxCheckbox, we must change Application.

3. Violates Open/Closed Principle – Must modify code when new platform comes.

4. Hard to manage families of objects – Button & Checkbox should be created consistently (Windows button with Windows checkbox), but mistakes can happen.

 */
