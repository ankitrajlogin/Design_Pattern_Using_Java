package p03_Design_Pattern_Implementation.EmployeeBook_Composition_Pattern;

import java.util.ArrayList;
import java.util.List;

interface Employee {
    void showDetails();
    void ShowCompleteDetails() ;
}

class Developer implements Employee {
    private String name;

    public Developer(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Developer: " + name);
    }

    @Override
    public void ShowCompleteDetails(){
        System.out.println("Developer: " + name);
        System.out.println("There is no one under this Developer " + name );
    }
}

class Tester implements Employee {
    private String name;

    public Tester(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Tester: " + name);
    }

    @Override
    public void ShowCompleteDetails(){
        System.out.println("Tester: " + name);
        System.out.println("There is no Employee under this Tester " + name);
    }
}


class Manager implements Employee {

    private String name;

    private List<Employee> employees =
            new ArrayList<>();

    public Manager(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void showDetails() {
        System.out.println("Manager: " + name);

        for(Employee employee : employees) {
            employee.showDetails();
        }
    }

    @Override
    public void ShowCompleteDetails(){
        System.out.println("Manager: " + name);
        System.out.println("Employee Under " + name + " Manager is :");

        for(Employee employee : employees) {
            employee.ShowCompleteDetails();
        }
    }
}

public class EmployeeBook {
    public static void main(String[] args) {

        Developer dev1 = new Developer("Ankit");
        Developer dev2 = new Developer("Rahul");
        Tester tester1 = new Tester("Priya");

        Manager manager1 = new Manager("shivam");

        manager1.addEmployee(dev1);
        manager1.addEmployee(dev2);

        Manager manager2 =
                new Manager("shubham");

        manager2.addEmployee(manager1);


        Manager generalManager =
                new Manager("kayamat");

        generalManager.addEmployee(manager1);
        generalManager.addEmployee(tester1);
        generalManager.addEmployee(manager2);

        generalManager.showDetails();

        System.out.println("-----------------------------------------");
        generalManager.ShowCompleteDetails();

    }
}
