package p03_Design_Pattern_Implementation.Application_Command_Pattern;

/*
        | Component | VS Code       | Photoshop      |
        | --------- | ------------- | -------------- |
        | Command   | WriteCommand  | RotateCommand  |
        | Receiver  | TextEditor    | ImageEditor    |
        | execute() | write text    | rotate image   |
        | undo()    | delete text   | reverse rotate |
        | Invoker   | EditorInvoker | ToolbarInvoker |

 */

import java.util.Stack;

/*
 * RECEIVER 1
 * Actual business logic for text editor
 */
class TextEditor {

    private StringBuilder content =
            new StringBuilder();

    public void write(String text) {

        content.append(text);

        System.out.println(
                "Writing: " + text
        );
    }

    public void delete(int count) {

        if(count > content.length()) {
            count = content.length();
        }

        int start =
                content.length() - count;

        content.delete(start, content.length());

        System.out.println(
                "Deleting " + count + " chars"
        );
    }

    public String getContent() {
        return content.toString();
    }
}

/*
 * RECEIVER 2
 * Actual business logic for image editor
 */
class ImageEditor {

    private int currentRotation = 0;

    public void rotate(int degree) {

        currentRotation += degree;

        System.out.println(
                "Rotating image by "
                        + degree
                        + " degree"
        );

        System.out.println(
                "Current Rotation: "
                        + currentRotation
        );
    }
}

/*
 * COMMAND INTERFACE
 */
interface Command {

    void execute();

    void undo();
}

/*
 * CONCRETE COMMAND 1
 * Text write operation
 */
class WriteCommand implements Command {

    private TextEditor editor;

    private String text;

    public WriteCommand(
            TextEditor editor,
            String text
    ) {
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {

        editor.write(text);
    }

    @Override
    public void undo() {

        editor.delete(text.length());
    }
}

/*
 * CONCRETE COMMAND 2
 * Image rotate operation
 */
class RotateCommand implements Command {

    private ImageEditor editor;

    private int degree;

    public RotateCommand(
            ImageEditor editor,
            int degree
    ) {
        this.editor = editor;
        this.degree = degree;
    }

    @Override
    public void execute() {

        editor.rotate(degree);
    }

    @Override
    public void undo() {

        editor.rotate(-degree);
    }
}

/*
 * INVOKER
 * Executes commands and stores history
 */
class CommandManager {

    private Stack<Command> history =
            new Stack<>();

    public void executeCommand(
            Command command
    ) {

        command.execute();

        history.push(command);
    }

    public void undo() {

        if(history.isEmpty()) {

            System.out.println(
                    "Nothing to undo"
            );

            return;
        }

        Command command =
                history.pop();

        command.undo();
    }
}

/*
 * CLIENT
 */
public class ApplicationMain {

    public static void main(String[] args) {

        /*
         * =========================
         * TEXT EDITOR EXAMPLE
         * =========================
         */

        System.out.println(
                "===== TEXT EDITOR ====="
        );

        TextEditor textEditor =
                new TextEditor();

        CommandManager manager =
                new CommandManager();

        Command writeHello =
                new WriteCommand(
                        textEditor,
                        "Hello "
                );

        Command writeWorld =
                new WriteCommand(
                        textEditor,
                        "World"
                );

        manager.executeCommand(writeHello);

        manager.executeCommand(writeWorld);

        System.out.println(
                "Editor Content: "
                        + textEditor.getContent()
        );

        /*
         * Undo last command
         */
        manager.undo();

        System.out.println(
                "After Undo: "
                        + textEditor.getContent()
        );

        /*
         * =========================
         * IMAGE EDITOR EXAMPLE
         * =========================
         */

        System.out.println(
                "\n===== IMAGE EDITOR ====="
        );

        ImageEditor imageEditor =
                new ImageEditor();

        Command rotate90 =
                new RotateCommand(
                        imageEditor,
                        90
                );

        Command rotate45 =
                new RotateCommand(
                        imageEditor,
                        45
                );

        manager.executeCommand(rotate90);

        manager.executeCommand(rotate45);

        /*
         * Undo last rotate
         */
        manager.undo();
    }
}