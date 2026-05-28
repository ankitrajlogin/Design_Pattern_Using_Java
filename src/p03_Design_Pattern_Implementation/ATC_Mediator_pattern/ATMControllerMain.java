package p03_Design_Pattern_Implementation.ATC_Mediator_pattern;


import java.util.ArrayList;
import java.util.List;

interface ChatMediator{
    void sendMessage(String message , User sender) ;
}

class ChatRoom implements ChatMediator{
    private List<User> users = new ArrayList<>() ;

    public void addUser(User user){
        users.add(user) ;
    }

    @Override
    public void sendMessage(String message , User sender){
        for(User user: users){
            if(user != sender){
                user.receiveMessage(message , sender) ;
            }
        }
    }
}

class User {

    private String name;

    private ChatMediator mediator;

    public User(String name, ChatMediator mediator) {

        this.name = name;

        this.mediator = mediator;
    }

    public String getName() {

        return name;
    }

    public void send(String message) {

        System.out.println(
                name + " sends: " + message
        );

        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message, User sender) {

        System.out.println(
                name +
                        " received from " +
                        sender.getName() +
                        ": " +
                        message
        );
    }
}


public class ATMControllerMain {
    public static void main(String[] args) {

        ChatRoom chatRoom = new ChatRoom();

        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.send("Hello everyone");

        bob.send("Its a good days");
    }
}
