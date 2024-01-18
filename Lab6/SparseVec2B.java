import java.util.*;

public class SparseVec2B<E extends Comparable<E>> extends TreeMap<Integer, E> implements SparseVec<E>{

    @Override
    public void add(E elem) {
        Set<Integer> set = keySet();
        for(Integer i = 0; i < Integer.MAX_VALUE; i++){
            if(!set.contains(i)){ // Putting elem at the first empty position >= 0
                put(i, elem);
                break;
            }
        }
    }

    @Override
    public void add(int pos, E elem) {
        if(pos < 0)
            pos = 0;
        put(pos, elem);
    }

    @Override
    public int indexOf(E elem) {
        Set<Integer> set = keySet();
        for(Integer i : set){
            if(get(i).equals(elem))
                return i;
        }
        return -1;
    }

    @Override
    public void removeAt(int pos) {
        try{
            remove(pos);
        }
        catch(Exception e){;}
    }

    @Override
    public void removeElem(E elem) {
        Set<Integer> set = keySet();
        for(Integer i : set){
            if(get(i).equals(elem)){
                remove(i);
                break;
            }
        }
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public int minIndex() {
        try{
            return firstKey();
        }
        catch(NoSuchElementException e){
            return -1;
        }
    }

    @Override
    public int maxIndex() {
        try{
            return lastKey();
        }
        catch(NoSuchElementException e){
            return -1;
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Set<Integer> set = keySet();
        for(Integer idx : set){
            stringBuilder.append("\n" + idx + ": " + get(idx));
        }
        stringBuilder.deleteCharAt(0); // Removing the first newline-character
        return stringBuilder.toString();
    }

    @Override
    public E get(int pos) {
        return super.get(pos);
    }

    @Override
    public Object[] toArray() {
        int lastKey = maxIndex();
        Object[] array = new Object[lastKey+1];
        for(int i = 0; i <= lastKey; i++){
            array[i] = get(i);
        }
        return array;
    }

    @Override
    public List<E> sortedValues() {
        List<E> list = new ArrayList<>(values());
        Collections.sort(list);
        return list;
    }

    public static void main(String args[]){
        SparseVec2B<String> vec = new SparseVec2B<>();
        vec.add(46,"Biden");
        vec.add(45, "Trump");
        vec.add(44, "Obama");
        vec.add(43,"Bush");
        vec.add(42,"Clinton");
        System.out.println(vec.toString());
        System.out.println(Arrays.toString(vec.toArray()));
        System.out.println(vec.sortedValues());
    }
}