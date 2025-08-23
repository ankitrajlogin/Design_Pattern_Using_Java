package p02_SYSTEM_DESIGN.Solid_principle.L02_SRP_Good_Code ;



// Responsibility 1: Business logic
class Invoice {
    private String customer;
    private double amount;

    public Invoice(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public double calculateTotal() {
        double tax = amount * 0.18;
        return amount + tax;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }
}

// Responsibility 2: Printing
class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Customer: " + invoice.getCustomer());
        System.out.println("Amount: " + invoice.getAmount());
        System.out.println("Total: " + invoice.calculateTotal());
    }
}

// Responsibility 3: Persistence
class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Saving invoice to Database...");
    }
}

public class SRP_Good_code {
    public static void main(String[] args) {
        // Create an invoice (business logic)
        Invoice invoice = new Invoice("Ankit Raj", 5000.0);

        // Print the invoice (presentation responsibility)
        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        // Save the invoice (persistence responsibility)
        InvoiceRepository repository = new InvoiceRepository();
        repository.save(invoice);
    }
}

/*
Key Points to Remember
SRP means single reason to change, not just “one function.”
It improves modularity → smaller, independent components.
Makes system easier to extend & maintain.
Works at class level, method level, and module level.
Too much splitting also adds complexity → balance is needed.


 Why is this "SRP applied"?
Invoice → only calculates business logic (total with tax).
InvoicePrinter → only knows how to print invoice.
InvoiceRepository → only knows how to save invoice.
Main → orchestrates everything (creates objects, calls methods).
 */
