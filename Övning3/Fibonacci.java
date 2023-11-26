import java.util.Iterator;
public class Fibonacci implements Iterator<Double>{
    private long n = 0;
    private double a = 0;
    private double b = 1;

    @Override
    public boolean hasNext() {
        return n<100;
    }

    @Override
    public Double next() {
        double c= a+b;
        a=b;
        b=c;
        n++;
        return c;
    }
    
}
