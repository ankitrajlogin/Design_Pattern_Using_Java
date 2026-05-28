package p03_Design_Pattern_Implementation.Text_Edtior_Momento;


import java.util.Stack;

// editor -> this state how the editor at current stage look like
class EditorMemento {
    private final String content ;

    public EditorMemento(String content){
        this.content = content ;
    }

    public String getSavedContent(){
        return content ;
    }
}

// TextEditor -> how state is saved and restored ;
class TextEditor{
    private String content = "" ;

    public void write(String text){
        content += text ;
    }

    public void show(){
        System.out.println(content);
    }

    // Create snapshot
    public EditorMemento save(){
        return new EditorMemento(content) ;
    }

    // restore snapshot
    public void restore(EditorMemento memento){
        content = memento.getSavedContent() ;
    }
}

// create caretaker
class HistoryManager {
    private Stack<EditorMemento> undoStack = new Stack<>();
    private Stack<EditorMemento> redoStack = new Stack<>();

    private TextEditor editor ;

    public HistoryManager(TextEditor editor){
        this.editor = editor ;
    }


    public void save() {
        undoStack.push(editor.save());

        // New action clears redo history
        redoStack.clear();
    }


    public void undo(){
        if(undoStack.isEmpty()){
            System.out.println("Nothing to undo");
            return ;
        }

        redoStack.push(editor.save()) ;
        EditorMemento previousState = undoStack.pop() ;
        editor.restore(previousState) ;
    }

    public void redo(){
        if(redoStack.isEmpty()){
            System.out.println("Nothing to redo");
            return ;
        }

        undoStack.push(editor.save()) ;
        EditorMemento nextState = redoStack.pop() ;
        editor.restore(nextState);
    }
}

public class Main {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();

        HistoryManager history = new HistoryManager(editor);

        editor.write("Hello ");
        history.save();

        editor.write("World ");
        history.save();

        editor.write("Java ");

        System.out.println("Current:");
        editor.show();

        history.undo();

        System.out.println("\nAfter Undo:");
        editor.show();

        history.redo();

        System.out.println("\nAfter Redo:");
        editor.show();
    }
}







