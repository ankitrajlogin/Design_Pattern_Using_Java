package p03_Design_Pattern_Implementation.EmployeeCloning_Prototype_pattern;

interface Prototype{
    Prototype cloneObject() ;
}

class Address{
    String city ;
    String state ;

    public Address(String city , String state){
        this.city = city ;
        this.state = state ;
    }

    public void showAddress(){
        System.out.println(city + " , " + state);
    };
}

class Employee implements Prototype{
    String name ;
    int salary ;
    Address address ;

    public Employee(String name, int salary, Address address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
        loadHeavyData();
    }

    public Employee(){

    }

    private void loadHeavyData() {
        System.out.println("Loading heavy resources... such as loadDatabase();\n" +
                "    loadProfilePicture();\n" +
                "    loadMachineLearningModel();\n" +
                "    parseHugeFile();\n" +
                "    createNetworkConnection();");
    }

    @Override
    public Prototype cloneObject(){
        Employee clone = new Employee() ;
        clone.name = this.name ;
        clone.salary = this.salary ;

        clone.address = new Address(this.address.city , this.address.state) ;

        return clone ;
    }

    public void showEmployee() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.print("Address: ");

        address.showAddress();
        System.out.println();
    }
}

public class EmployeePrototypeMain {
    public static void main(String[] args) {

        Address address =
                new Address(
                        "Bangalore",
                        "Karnataka"
                );

        Employee original =
                new Employee(
                        "Ankit",
                        50000,
                        address
                );

        Employee clone =
                (Employee)
                        original.cloneObject();

        clone.address.city = "Delhi";

        System.out.println(
                "ORIGINAL OBJECT"
        );

        original.showEmployee();

        System.out.println(
                "CLONED OBJECT"
        );

        clone.showEmployee();
    }
}
