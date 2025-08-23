package p01_BASIC.UML;


/*
 1. What is Association?
Association is the simplest relationship between two classes in OOP.
It represents a situation where objects of one class are connected to objects of another class.
It is a structural relationship (unlike dependency which is temporary).
Association does not imply ownership (unlike aggregation/composition).
👉 In UML, it’s shown as a plain solid line (────────) between two classes.

2. Real-Life Example
A Student is associated with a Course.
A student can enroll in a course, and a course can have multiple students.
Both can exist independently.

3. Types of Association
One-to-One → A person has a passport.
One-to-Many → A department has many teachers.
Many-to-One → Many employees work in one company.
Many-to-Many → Students enroll in many courses, and courses have many students.


Important Points
Association just means "linked", not ownership.
It can be one-to-one, one-to-many, many-to-one, or many-to-many.
Both classes can exist independently.
Association is often used in database modeling (ER diagrams → later translated into UML).
 */



import java.util.*;

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }

    void display() {
        System.out.println("Student: " + name);
    }
}

class Course {
    String title;
    List<Student> students;  // Association → Course linked to Students

    Course(String title) {
        this.title = title;
        this.students = new ArrayList<>();
    }

    void addStudent(Student s) {
        students.add(s);
    }

    void display() {
        System.out.println("Course: " + title);
        System.out.println("Enrolled Students:");
        for (Student s : students) {
            s.display();
        }
        System.out.println("---------------");
    }
}

public class AssociationExample {
    public static void main(String[] args) {
        // Students exist independently
        Student s1 = new Student("Ankit");
        Student s2 = new Student("Anjani");

        // Course also exists independently
        Course c1 = new Course("Java Programming");
        Course c2 = new Course("Spring Boot") ;

        // Establishing association
        c1.addStudent(s1);
        c1.addStudent(s2);

        c2.addStudent(s1) ;
        c2.addStudent(s2)  ;

        // Display
        c1.display() ;
        c2.display() ;
    }
}




/*
 ✅ Summary:
Association = a general connection between classes.
Represented by solid line in UML.
Example: Student–Course.
Independent lifecycles.
Can represent different multiplicities (1..1, 1..*, ..).

 */