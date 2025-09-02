package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L04_Composite_Pattern.ManagerEmployeeManagement;


import java.util.ArrayList;
import java.util.List;

// component
interface Employee{
    void showDetails() ;
}

// Leaf(Developer)
class Developer implements Employee{
    private String name ;
    private String role ;

    public Developer(String name , String role){
        this.name = name ;
        this.role = role ;
    }

    @Override
    public void showDetails(){
        System.out.println(name + " - " + role);
    }
}


// Composite (Manager)
class Manager implements Employee {
    private String name;
    private String role;
    private List<Employee> subordinates = new ArrayList<>();

    public Manager(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public void addEmployee(Employee emp) {
        subordinates.add(emp);
    }

    public void removeEmployee(Employee emp) {
        subordinates.remove(emp);
    }

    @Override
    public void showDetails() {
        System.out.println(name + " - " + role);
        for (Employee emp : subordinates) {
            emp.showDetails();
        }
    }
}

public class Company {
    public static void main(String[] args) {
        Employee dev1 = new Developer("Alice", "Frontend Dev");
        Employee dev2 = new Developer("Bob", "Backend Dev");
        Employee dev3 = new Developer("Charlie", "Mobile Dev");

        Manager manager1 = new Manager("David", "Engineering Manager");
        Manager manager2 = new Manager("Eve", "Project Manager");

        manager1.addEmployee(dev1);
        manager1.addEmployee(dev2);

        manager2.addEmployee(dev3);
        manager2.addEmployee(manager1); // Manager inside Manager!

        System.out.println("Company Structure:");
        manager2.showDetails();
    }
}
