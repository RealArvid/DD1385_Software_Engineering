package human;

public abstract class Human {
    private String name;
    private String pnr;

    protected Human(String name, String pnr){
        this.name = name;
        this.pnr = pnr;
    }

    protected String getName(){
        return name;
    }

    protected String getPnr(){
        return pnr;
    }

    public static Human create(String name, String pnr){
        int digit = pnr.charAt(pnr.length()-2) - '0';
        if(digit == 0)
            return new NonBinary(name, pnr);
        else if(digit % 2 == 1)
            return new Man(name, pnr);
        else
            return new Woman(name, pnr);
    }

    public static void test(){
        System.out.println("test");
    }
}