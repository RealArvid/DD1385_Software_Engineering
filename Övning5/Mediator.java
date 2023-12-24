import java.util.*;

public class Mediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }

    public void sendMessage(String message, User user){
        for(User u : users){
            if(u != user){
                u.receiveMessage(message);
            }
        }
    }
}
