package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T03_Structural_Design.L04_Composite_Pattern.GoodCode;

import java.util.ArrayList;
import java.util.List;


/*
🔹 Components of Composite Pattern
    - Component → common interface (FileSystemComponent).
    - Leaf → individual item (File).
    - Composite → container that holds other components (Folder).
    - Client → uses Component interface without worrying about whether it’s File or Folder.


🔹 Steps to Implement Composite Pattern
    1. Component (interface) – common operations for both File and Folder.
    2. Leaf (File) – represents a single object.
    3. Composite (Folder) – represents group of objects, delegates tasks to children.
    4. Client – treats both File and Folder uniformly.
 */



interface FileSystemComponent{
    void show() ; // common operation for both File and Folder
}

class File implements FileSystemComponent {
    private String name;

    File(String name) { this.name = name; }

    @Override
    public void show() {
        System.out.println("File: " + name);
    }
}

class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    Folder(String name) { this.name = name; }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void show() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : children) {
            component.show();
        }
    }
}


public class CompositePatternDemo {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("Resume.docx");
        FileSystemComponent file2 = new File("Photo.png");

        Folder folder1 = new Folder("Documents");
        folder1.add(file1);
        folder1.add(file2);

        FileSystemComponent file3 = new File("Song.mp3");

        Folder rootFolder = new Folder("Root");
        rootFolder.add(folder1);
        rootFolder.add(file3);

        rootFolder.show();
    }

}




/*
🔹 Benefits
    - Treat individual and group objects uniformly.
    - Avoids duplicate code for managing Files/Folders.
    - Makes adding new types (like Shortcuts, Links) easy.
    - Perfect for tree-like structures.

🔹 Real-life Examples
    - GUI Systems: Buttons, Panels, Windows.
    - File Explorer: Files & Folders.
    - Company Structure: Employees and Departments.
    - Menus in Apps/Websites: Menu → Submenu → Items.

🔹 Related Terms
    - Leaf – final object, no children.
    - Composite – can contain multiple children.
    - Transparency – whether add/remove should be part of the Component interface or only in Composite.
 */
