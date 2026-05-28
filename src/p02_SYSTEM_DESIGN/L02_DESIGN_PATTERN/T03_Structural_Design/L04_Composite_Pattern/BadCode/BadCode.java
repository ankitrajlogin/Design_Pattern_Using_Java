package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T03_Structural_Design.L04_Composite_Pattern.BadCode;


/*
🔹 What is Composite Pattern?

The Composite Design Pattern is a Structural Design Pattern that lets you treat individual objects and groups of objects in the same way.
It’s useful when you have to represent hierarchical structures (tree structures) such as:
    - File systems (Files & Folders)
    - UI elements (Buttons, Panels, Windows)
    - Organization charts (Employee, Manager, Department)

👉 Key idea:
    - Define a common interface for both simple and complex objects.
    - Then treat them uniformly (a file and a folder are both "FileSystemItem").
*/


/*
🔹 Problem Statement
Imagine you are building a file explorer system.
We have two types of items:
    1. File → a single document.
    2. Folder → a collection of files or subfolders.
 */

import java.util.ArrayList;
import java.util.List;

class File {
    private String name;
    File(String name) { this.name = name; }
    public void show() { System.out.println("File: " + name); }
}

class Folder {
    private String name;
    private List<File> files = new ArrayList<>();
    private List<Folder> folders = new ArrayList<>();

    Folder(String name) { this.name = name; }

    public void addFile(File file) { files.add(file); }
    public void addFolder(Folder folder) { folders.add(folder); }

    public void show() {
        System.out.println("Folder: " + name + "contains : ");
        for (File file : files) file.show();
        for (Folder folder : folders) folder.show();
    }
}


public class BadCode {
    public static void main(String[] args){
        File f1 = new File("ankit_marksheet") ;
        File f2 = new File("ankit_pancard") ;

        Folder F1 = new Folder("ankit_document") ;
        Folder F2 = new Folder("anjani_document") ;

        Folder F = new Folder("Document") ;

        F.addFile(f1) ;
        F.addFile(f2) ;
        F.addFolder(F1);

        F.show() ;

    }
}

/*
❌ Problems with Bad Code
    - Not flexible – You have to separately manage File and Folder.
    - Scalability issues – If later you want to add Shortcut, you must duplicate logic.
    - No common interface – Client code needs to know whether it is handling a File or Folder.
 */
