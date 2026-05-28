package p03_Design_Pattern_Implementation.UISystem_Abstract_pattern;

interface Button {
    void render();
}

interface Checkbox {
    void check();
}

class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("Windows Checkbox");
    }
}

class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("Mac Checkbox");
    }
}


// creating abstract factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}


public class UiSystemMain {
    public static void main(String[] args) {

        GUIFactory factory;

        String os = "WINDOWS";

        if(os.equals("WINDOWS")) {

            factory = new WindowsFactory();
        }
        else {

            factory = new MacFactory();
        }

        Button button =
                factory.createButton();

        Checkbox checkbox =
                factory.createCheckbox();

        button.render();

        checkbox.check();
    }
}
