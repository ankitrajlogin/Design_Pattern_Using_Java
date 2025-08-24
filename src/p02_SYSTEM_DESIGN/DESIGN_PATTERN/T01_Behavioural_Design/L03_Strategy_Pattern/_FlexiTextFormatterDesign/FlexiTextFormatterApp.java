package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L03_Strategy_Pattern._FlexiTextFormatterDesign;

/*
problem Statement :
FlexiText Formatter Application Exercise
You are designing a text editor application that enables users to format their documents using different styles, such as Plain Text, HTML, and Markdown. Each formatting style transforms the document’s content in its own unique way. The application should allow users to switch between these formatting styles at runtime, with the ability to easily incorporate new formatting styles in the future.
 */

/*
🔑 Strategy Pattern Components in this Problem
1. Strategy (Interface/Abstract Class)
-> Defines a common interface for all text formatting strategies.
-> Example: TextFormatter with format(String text) method.

2. Concrete Strategies (Implementations)
-> Implement the formatting in different ways:
-> PlainTextFormatter
-> HtmlFormatter
-> MarkdownFormatter

3. Context (The Editor/Document)
-> Maintains a reference to a TextFormatter strategy.
-> Allows the client to change the strategy at runtime.
 */

interface TextFormatter{
    String format(String text) ;
}

class PlainTextFormatter implements TextFormatter{
    @Override
    public String format(String text) {
        return text ;
    }
}

class HtmlFormatter implements TextFormatter{
    @Override
    public String format(String text) {
        return "<html><body>" + text + "</body></html>" ;
    }
}

class MarkdownFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        return "**" + text + "**";
    }
}

class TextEditor{
    private TextFormatter formatter ;

    public void setFormatter(TextFormatter formatter){
        this.formatter = formatter ;
    }

    public void publishText(String text){
        System.out.println(formatter.format(text)) ;
    }
}


public class FlexiTextFormatterApp  {
    public static void main(String[] args) {
        String content = "Hello, this is Strategy Pattern Example!";

        TextEditor editor = new TextEditor();

        // Plain text
        editor.setFormatter(new PlainTextFormatter());
        editor.publishText(content);

        // HTML
        editor.setFormatter(new HtmlFormatter());
        editor.publishText(content);

        // Markdown
        editor.setFormatter(new MarkdownFormatter());
        editor.publishText(content);
    }
}
