package p01_BASIC.UML;

/*
What is Aggregation?
Aggregation is a type of association in OOP (Object-Oriented Programming).
It represents a "Has-A" relationship between two classes.
One class is a container (whole), and another is a part (contained).
In aggregation, the contained object can exist independently of the container object.
👉 In UML, Aggregation is shown with a hollow diamond (◇).


Department ◇──────── Teacher
Department (whole) aggregates Teacher (part).
But Teacher can exist independently of Department.


Real-Life Example
A Department has Teachers.
Even if the Department is deleted, Teachers can still exist.
So, the relationship is aggregation.

     +-----------------+ ◇────────────────+----------------+
     |   Department    |                  |    Teacher     |
     +-----------------+                  +----------------+
     | - deptName      |                  | - name         |
     | - teacher       |                  | - subject      |
     +-----------------+                  +----------------+
     | + display()     |                  | + display()    |
     +-----------------+                  +----------------+

Here:
The hollow diamond (◇) is on the Department side.
The arrow line goes towards Teacher.
This means: Department HAS-A Teacher (aggregation).
*/

class Teacher{
    String name ;
    String subject ;

    Teacher(String name , String subject){
        this.name = name ;
        this.subject = subject ;
    }

    void display() {
        System.out.println("Teacher: " + name + ", Subject: " + subject);
    }
}


// Class Department (Aggregation with Teacher)
class Department {
    String deptName;
    // Department HAS-A Teacher (aggregation)
    Teacher teacher;

    Department(String deptName, Teacher teacher) {
        this.deptName = deptName;
        this.teacher = teacher;
    }

    void display() {
        System.out.println("Department: " + deptName);
        teacher.display();
    }
}

public class AggregationExample {
    public static void main(String[] args) {
        // Teacher exists independently
        Teacher t1 = new Teacher("Ankit", "Math");
        Teacher t2 = new Teacher("Raj", "Physics");

        // Departments aggregate teachers
        Department d1 = new Department("Science", t1);
        Department d2 = new Department("Arts", t2);

        // Display info
        d1.display();
        d2.display();
    }
}




/*
Comparison with Composition:
Aggregation (◇ hollow diamond) → independent life cycle.
Composition (◆ filled diamond) → dependent life cycle (e.g., House HAS-A Room, if House destroyed → Room destroyed).

 */