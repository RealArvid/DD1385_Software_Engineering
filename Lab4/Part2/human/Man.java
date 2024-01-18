package human;

public class Man extends Human{

    Man(String name, String pnr){
        super(name, pnr);
    }

    @Override
    public String toString(){
        return "Jag är man och heter " + getName();
    }
}