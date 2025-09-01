package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L04_Builder_Design_Pattern.Good_Code;



/*
🔹 Steps to Implement Builder Pattern
1. Define the class with required and optional fields.
2. Make constructor private, accept a builder object.
3. Create a static nested Builder class with same fields.
4. Provide setter-like methods in Builder that return this.
5. Provide a build() method that creates the actual object.
6. Use builder in client code with method chaining.

🔹 Components of Builder Pattern
    - Product → The complex object being built (User).
    - Builder → Inner static class that constructs the object step by step.
    - Director (optional) → Sometimes used to enforce a specific sequence of building.
    - Client → Calls the builder to create the product.
 */


// Step1 : User class with static inner Builder
class User{
    private final String firstName;  // required
    private final String lastName;   // required
    private final int age;           // optional
    private final String email;      // optional
    private final String phone;      // optional
    private final String address;    // optional

    // private constructor
    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }


    // static builder class
    public static class Builder{
        private final String firstName;
        private final String lastName;
        private int age;
        private String email;
        private String phone;
        private String address;

        public Builder(String firstName , String lastName){
            this.firstName = firstName ;
            this.lastName = lastName ;
        }

        public Builder age(int age){
            this.age = age ;
            return this ;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName +
                ", age=" + age + ", email=" + email + ", phone=" + phone +
                ", address=" + address + "]";
    }
}

// Add a simple UserProfile class and its Builder for demonstration
class UserProfile {
    private final String name;
    private final int age;
    private final String email;
    private final String phone;
    private final String address;

    private UserProfile(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;
        private String phone;
        private String address;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    @Override
    public String toString() {
        return "UserProfile [name=" + name + ", age=" + age + ", email=" + email +
                ", phone=" + phone + ", address=" + address + "]";
    }
}

// step2 : client code
public class UserConstruct{
    public static void main(String[] args){

        // Fluent Interface - Method chaining in builder.
        User user = new User.Builder("John" , "Doe")
                .age(24)
                .email("john@gmail.com")
                .phone("123234231")
                .address("Patna , India")
                .build() ;

        System.out.println(user) ;

    }
}


/*
🔹 Benefits of Builder Pattern
    - Handles complex object creation cleanly.
    - Improves readability with method chaining.
    - Easy to maintain and extend.
    - Separates construction logic from representation.
    - Reduces risk of invalid object states.

🔹 Other Examples Where Builder Can Be Used
1. SQL Query Builder (SELECT name, age FROM users WHERE age > 18 ORDER BY name;) → Can be built step by step with a builder.
2. StringBuilder in Java (append(), insert(), etc).
3. HTTP Request Builders (e.g., OkHttpClient Request.Builder).
4. GUI Builders → Step-by-step construction of UI components.

Report/Document Builders → Constructing PDF/Word reports piece by piece.
 */



//****************** IMPORTANT NOTES ****************************
/*
Why We Make Builder Class static?
    - The Builder is an inner class of User.
    - If we did not make it static, then every Builder object would require a reference to an existing User object (because non-static inner classes always need an enclosing instance).
    - But we want to create a new User from scratch — so we don’t have a User yet!

👉 Making it static means:
    - The Builder can be created independently of a User.
    - It acts as a helper class inside User, without requiring an existing User object.




🔹 Visualization of Flow
    - new User.Builder("John","Doe") → creates Builder object
    - .age(25) → modifies Builder’s age, returns Builder
    - .email("john@gmail.com") → modifies Builder’s email, returns Builder
    - .phone("1234567890") → modifies Builder’s phone, returns Builder
    - .address("New York") → modifies Builder’s address, returns Builder
    - .build() → converts Builder → User (immutable object)

. works because each setter method returns the Builder itself (return this).
✅ build() returns the final User object constructed from builder values.
 */