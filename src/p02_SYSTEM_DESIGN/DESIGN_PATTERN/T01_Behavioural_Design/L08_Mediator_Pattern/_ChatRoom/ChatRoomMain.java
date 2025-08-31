package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L08_Mediator_Pattern._ChatRoom;


import java.util.ArrayList;
import java.util.List;

// 1. Mediator Interface
interface ChatMediator{
    void sendMessage(String message , ChatUser sender) ;
    void addUser(ChatUser user) ;

}

//Defines what a mediator must do:
//    - sendMessage() → broadcast messages
//    - addUser() → allow users to join the room


// 2. Concrete Mediator(ChatRoom)
class ChatRoom implements ChatMediator{
    private List<ChatUser> users = new ArrayList<>() ;

    @Override
    public void addUser(ChatUser user){
        users.add(user) ;
    }

    @Override
    public void sendMessage(String message , ChatUser sender){
        for(ChatUser u : users){
            u.receiveMessage(message , sender) ;
        }
    }
}

// 3. User class ( Colleague)
class ChatUser{
    private String name ;
    private ChatMediator mediator ;

    public ChatUser(String name , ChatMediator mediator){
        this.name = name ;
        this.mediator = mediator ;
    }

    public void sendMessage(String message){
        System.out.println(this.name + " is sending: " + message) ;
        mediator.sendMessage(message , this);
    }

    public void receiveMessage(String message , ChatUser sender){
        System.out.println(this.name + " received " + message + " from " + sender.name) ;
    }
}

//Each user:
//    - Has a name.
//    - Knows the mediator, not other users.
//    - Sends messages through the mediator.
//    - Receives messages from the mediator.

public class ChatRoomMain {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        ChatUser rahul = new ChatUser("Rahul", chatRoom);
        ChatUser amit = new ChatUser("Amit", chatRoom);
        ChatUser neha = new ChatUser("Neha", chatRoom);

        chatRoom.addUser(rahul);
        chatRoom.addUser(amit);
        chatRoom.addUser(neha);

        amit.sendMessage("Hi everyone!");
    }
}
