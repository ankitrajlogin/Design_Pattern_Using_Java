package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L07_State_Pattern.ATM_system_Design;


// State Interface
interface ATMState {
    void insertCard(ATM atm);
    void enterPin(ATM atm);
    void withdrawCash(ATM atm);
}


// Context
class ATM {
    private ATMState state;

    public ATM() {
        state = new NoCardState();  // initial state
    }

    public void setState(ATMState state) { this.state = state; }

    public void insertCard() {
        state.insertCard(this);
    }

    public void enterPin() {
        state.enterPin(this);
    }

    public void withdrawCash() {
        state.withdrawCash(this);
    }
}



// Concrete States
class NoCardState implements ATMState {
    public void insertCard(ATM atm) {
        System.out.println("Card Inserted.");
        atm.setState(new HasCardState());
    }
    public void enterPin(ATM atm) { System.out.println("Insert card first!"); }
    public void withdrawCash(ATM atm) { System.out.println("Insert card first!"); }
}

class HasCardState implements ATMState {
    public void insertCard(ATM atm) { System.out.println("Card already inserted!"); }
    public void enterPin(ATM atm) {
        System.out.println("PIN entered successfully.");
        atm.setState(new AuthenticatedState());
    }
    public void withdrawCash(ATM atm) { System.out.println("Enter PIN first!"); }
}

class AuthenticatedState implements ATMState {
    public void insertCard(ATM atm) { System.out.println("Card already inserted!"); }
    public void enterPin(ATM atm) { System.out.println("PIN already entered."); }
    public void withdrawCash(ATM atm) {
        System.out.println("Cash Withdrawn.");
        atm.setState(new NoCardState());
    }
}




public class ATMMain {
    public static void main(String[] args) {
        ATM atm = new ATM();

        atm.insertCard();   // Card Inserted.
        atm.enterPin();     // PIN entered successfully.
        atm.withdrawCash(); // Cash Withdrawn.

    }
}
