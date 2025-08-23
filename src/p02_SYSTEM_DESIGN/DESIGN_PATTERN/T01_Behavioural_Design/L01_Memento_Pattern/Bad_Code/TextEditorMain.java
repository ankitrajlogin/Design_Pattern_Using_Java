package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L01_Memento_Pattern.Bad_Code;

/*
Memento Design Pattern
📘 Definition:
- Memento Pattern is used to save and restore an object’s state without exposing its internal details.
- It provides undo/redo functionality, but instead of commands, it focuses on storing snapshots (checkpoints) of state.

✅ Key Components of Memento Pattern
Memento → The snapshot object that stores state.
Originator → The object whose state we want to save/restore (e.g., TextEditor).
Caretaker → Manages mementos (saves & restores states).

 */


/*
🔄 Undo/Redo Problem Statement
📘 What it is:
- In many applications (text editors, Photoshop, MS Word, IDEs), users want the ability to undo their last action and possibly redo it later.
- This requires keeping track of past actions so we can reverse them, and also reapply them if needed.

👶 In Simple Words:
Think of it like writing with a pencil ✏️:
Undo → Erase what you wrote.
Redo → Write it back again.

So, your app needs a system to:
Remember all actions taken.
Roll back (undo) the last action.
Reapply (redo) the action if the user requests.
 */



class TextEditor{

    private String content ;

    public void write(String text){
        this.content = text ;
    }

    public String getContent(){
        return content ;
    }

    public void undo(){
        // manually remove last entries or something hardcoded ;
    }

    public void redo(){
        // re-add last removed text ;
    }
}



// problem :
// 1. Undo the last write
// 1. one class have only 1 responsibility ( this violate SRP principle) ;




public class TextEditorMain{
    public static void main(String[] args){
        TextEditor editor = new TextEditor() ;
        editor.write("Hello World !") ;
        editor.write("Hello Everyone") ;

        System.out.println(editor.getContent()) ;
    }

}
