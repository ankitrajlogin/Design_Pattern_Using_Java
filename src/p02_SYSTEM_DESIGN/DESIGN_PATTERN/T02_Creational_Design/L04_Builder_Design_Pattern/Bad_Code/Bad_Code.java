package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L04_Builder_Design_Pattern.Bad_Code;

/*
What is Builder Design Pattern?
👉 The Builder Pattern is a creational design pattern that helps us construct complex objects step by step.
Instead of having a huge constructor with many parameters (a telescoping constructor problem), Builder provides a flexible and readable way to build objects.
 */

/*
🔹 Problem Statement (What it tries to solve?)
Imagine we want to create a User Profile object in an application.
    - A User has many fields: firstName, lastName, age, email, phone, address, etc.
    - Some fields are mandatory (firstName, lastName), others are optional.

How do we design this object creation without making it messy?
 */

class User {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String address;

    // Telescoping constructors
    public User(String firstName, String lastName) {
        this(firstName, lastName, 0, null, null, null);
    }

    public User(String firstName, String lastName, int age) {
        this(firstName, lastName, age, null, null, null);
    }

    public User(String firstName, String lastName, int age, String email) {
        this(firstName, lastName, age, email, null, null);
    }

    public User(String firstName, String lastName, int age, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName +
                ", age=" + age + ", email=" + email + ", phone=" + phone +
                ", address=" + address + "]";
    }
}

public class Bad_Code {
    public static void main(String[] args) {
        // ❌ Problematic
        User user = new User("John", "Doe", 25, "john@gmail.com", "1234567890", "New York");
        System.out.println(user);
    }
}


/*
❌ Problems with Bad Code
1. Telescoping constructors – Too many constructor overloads, confusing.
2. Hard to maintain – Adding new fields means more constructors.
3. Readability issue – Hard to know which parameter means what.
4. Error-prone – Easy to mix parameter order (e.g., phone vs email).
 */