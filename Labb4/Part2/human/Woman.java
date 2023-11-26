package human;

public class Woman extends Human{

    Woman(String name, String pnr){
        super(name, pnr);
    }

    @Override
    public String toString(){
        return "Jag är kvinna och heter " + getName();
    }
}