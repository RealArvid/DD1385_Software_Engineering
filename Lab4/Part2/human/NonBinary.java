package human;

public class NonBinary extends Human{

    NonBinary(String name, String pnr){
        super(name, pnr);
    }

    @Override
    public String toString(){
        return "Jag är icke-binär och heter " + getName();
    }
}