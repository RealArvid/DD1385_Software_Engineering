import java.util.Iterator;

public class MyIterable implements Iterable<Double>{

    @Override
    public Iterator<Double> iterator() {
        return new Fibonacci();
    }
}