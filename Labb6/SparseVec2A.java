import java.util.*;

public class SparseVec2A<E extends Comparable<E>> implements SparseVec<E>{
    private TreeMap<Integer, E> treeMap = new TreeMap<>();

    @Override
    public void add(E elem) {
        Set<Integer> set = treeMap.keySet();
        for(Integer i = 0; i < Integer.MAX_VALUE; i++){
            if(!set.contains(i)){ // Putting elem at the first empty position >= 0
                treeMap.put(i, elem);
                break;
            }
        }
    }

    @Override
    public void add(int pos, E elem) {
        if(pos < 0)
            pos = 0;
        treeMap.put(pos, elem);
    }

    @Override
    public int indexOf(E elem) {
        Set<Integer> set = treeMap.keySet();
        for(Integer i : set){
            if(treeMap.get(i).equals(elem))
                return i;
        }
        return -1;
    }

    @Override
    public void removeAt(int pos) {
        try{
            treeMap.remove(pos);
        }
        catch(Exception e){;}
    }

    @Override
    public void removeElem(E elem) {
        Set<Integer> set = treeMap.keySet();
        for(Integer i : set){
            if(treeMap.get(i).equals(elem)){
                treeMap.remove(i);
                break;
            }
        }
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public int minIndex() {
        try{
            return treeMap.firstKey();
        }
        catch(NoSuchElementException e){
            return -1;
        }
    }

    @Override
    public int maxIndex() {
        try{
            return treeMap.lastKey();
        }
        catch(NoSuchElementException e){
            return -1;
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Set<Integer> set = treeMap.keySet();
        for(Integer idx : set){
            stringBuilder.append("\n" + idx + ": " + treeMap.get(idx));
        }
        stringBuilder.deleteCharAt(0); // Removing the first newline-character
        return stringBuilder.toString();
    }

    @Override
    public E get(int pos) {
        return treeMap.get(pos);
    }

    @Override
    public Object[] toArray() {
        int lastKey = maxIndex();
        Object[] array = new Object[lastKey+1];
        for(int i = 0; i <= lastKey; i++){
            array[i] = treeMap.get(i);
        }
        return array;
    }

    @Override
    public List<E> sortedValues() {
        List<E> list = new ArrayList<>(treeMap.values());
        Collections.sort(list);
        return list;
    }

    public static void main(String args[]){
        SparseVec2A<String> vec = new SparseVec2A<>();
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