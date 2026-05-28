package p03_Design_Pattern_Implementation.UserCreation_Builder_Pattern;


class User {

    private String name;
    private int age;
    private String email;
    private String address;

    public User(
            String name,
            int age,
            String email,
            String address
    ) {

        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public void display() {

        System.out.println(
                name + " " +
                        age + " " +
                        email + " " +
                        address
        );
    }
}

class UserBuilder{
    private String name ;
    private int age ;
    private String email ;
    private String address ;

    public UserBuilder setName(String name) {

        this.name = name;

        return this;
    }

    public UserBuilder setAge(int age) {

        this.age = age;

        return this;
    }

    public UserBuilder setEmail(String email) {

        this.email = email;

        return this;
    }

    public UserBuilder setAddress(String address){
        this.address = address ;

        return this ;
    }

    public User build(){
        return new User(
                name ,
                age ,
                email,
                address
        ) ;
    }
}


public class UserCreationMain {
    public static void main(String[] args){
        User user = new UserBuilder()
                .setName("Ankit")
                .setAddress("Patna")
                .setAge(22)
                .setEmail("ankitraj")
                .build() ;

        user.display();
    }
}
