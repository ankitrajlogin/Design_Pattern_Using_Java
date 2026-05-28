package p02_SYSTEM_DESIGN.L01_Solid_principle.L01_SRP_Bad_Code;

/*
Single Responsibility Principle (SRP)
- A class should have only one reason to change.
- That means each class/module/function should focus on only one responsibility (one job or concern).
- If a class has more than one responsibility, it becomes tightly coupled. A change in one responsibility may break another.

🔴 Why SRP is Important?
Maintainability → Each class is smaller and easier to understand.
Reusability → Classes can be reused in different contexts.
Testability → Easier to test focused classes.
Loose Coupling → One change won’t break unrelated functionality.
 */


class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    // Responsibility 1: Business logic
    public double calculateTotal() {
        double tax = amount * 0.18;
        return amount + tax;
    }

    // Responsibility 2: Printing
    public void printInvoice() {
        System.out.println("Customer: " + customer);
        System.out.println("Amount: " + amount);
        System.out.println("Total: " + calculateTotal());
    }

    // Responsibility 3: Database persistence
    public void saveToDatabase() {
        System.out.println("Saving invoice to Database...");
    }
}


public class SRP_Bad_code {
    public static void main(String[] args) {
        Invoice customer1 = new Invoice("Ankit Raj", 1000);
        customer1.saveToDatabase();
        customer1.printInvoice() ;
    }
}

/*
Problems:
Class has 3 responsibilities:
Calculating totals
Printing
Saving to database

- If tomorrow tax rules change → modify Invoice
- If printing format changes → modify Invoice
- If DB changes (e.g., from MySQL to MongoDB) → modify Invoice

👉 This class will keep changing for unrelated reasons → violates SRP.

 */