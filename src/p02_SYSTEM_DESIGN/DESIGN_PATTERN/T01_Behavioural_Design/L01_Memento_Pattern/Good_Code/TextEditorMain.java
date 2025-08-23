package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L01_Memento_Pattern.Good_Code;
import java.util.Stack;

/*
Memento Pattern = Save and restore an object’s state without exposing details.
Components: Originator, Memento, Caretaker.
Great for undo/redo, checkpoints, save/load functionality.
Different from Command → Command = actions; Memento = state snapshots.
 */

/*
🔄 How they work together (Undo/Redo example):
1. You type something → Originator updates its state.
2. You hit "Save" (internally before each change) → Originator creates Memento → Caretaker stores it.
3. When you press "Undo" → Caretaker fetches the last Memento → Originator restores to that state.
4. For "Redo" → Caretaker can also keep track of future states.
 */


// Memento : Store the state
class TextMemento{
    private final String state ;

    public TextMemento(String state){
        this.state = state ;
    }

    public String getState(){
        return state ;
    }
}

// Originator : the Text editor
class TextEditor{
    private String text = "" ;

    public void type(String newText){
        text += newText ;
    }

    public String getText(){
        return text ;
    }


    // save current state to Memento
    public TextMemento save(){
        return new TextMemento(text) ;
    }

    // Restore state from Memento
    public void restore(TextMemento memento){
        text = memento.getState() ;
    }
}


// CareTaker : Manges undo/redo


class Caretaker {
    private Stack<TextMemento> undoStack = new Stack<>();
    private Stack<TextMemento> redoStack = new Stack<>();

    public void saveState(TextEditor editor) {
        undoStack.push(editor.save());
        redoStack.clear(); // clear redo history when new action occurs
    }

    public void undo(TextEditor editor) {
        if (!undoStack.isEmpty()) {
            redoStack.push(editor.save()); // save current state for redo
            editor.restore(undoStack.pop());
        }
    }

    public void redo(TextEditor editor) {
        if (!redoStack.isEmpty()) {
            undoStack.push(editor.save()); // save current state for undo
            editor.restore(redoStack.pop());
        }
    }
}

public class TextEditorMain {
    public static void main(String[] args){
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        editor.type("Hello ");
        caretaker.saveState(editor);

        editor.type("World!");
        caretaker.saveState(editor);



        System.out.println("Current: " + editor.getText()); // Hello World!

        caretaker.undo(editor);
        System.out.println("After Undo: " + editor.getText()); // Hello

        caretaker.redo(editor);
        System.out.println("After Redo: " + editor.getText()); // Hello World

        editor.type(" Ankit") ;
        caretaker.saveState(editor);

        editor.type(" Raj") ;
        System.out.println("Current value without save: " + editor.getText()) ;


        caretaker.undo(editor);
        System.out.println("After Undo: " + editor.getText());

        caretaker.undo(editor);
        System.out.println("After Undo: " + editor.getText());

        caretaker.undo(editor);
        System.out.println("After Undo: " + editor.getText());
    }
}

/*
NOTE :
Key insight
- The first undo often restores the “current” last saved state, which can look like nothing changed.
- Only when you continue undoing, you go further back in history.
- This is because your undoStack contains snapshots after each save, including the latest state.
- Some implementations don’t push the latest state to undoStack until the next change to avoid this “first undo looks like no change” effect.

 */
