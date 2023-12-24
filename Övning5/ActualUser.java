public class ActualUser extends User{

    public ActualUser(Mediator mediator, String name){
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " receives: " + message);
    }
}
