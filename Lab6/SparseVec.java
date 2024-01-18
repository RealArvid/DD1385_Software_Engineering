import java.util.*;
public interface SparseVec<E extends Comparable<E>>{
    
    /**Add at lowest index >= 0 and not it use*/
    public void add(E elem);
    
    /**Add elem at pos, if pos is occupied replace with elem, if pos<0 use index = 0*/
    public void add(int pos, E elem);
    
    /**Find first (lowest index) occurence of elem, return its index. If not found, return -1*/
    public int indexOf(E elem);
    
    /**If index pos is not used, nothing happens*/
    public void removeAt(int pos);
    
    /**Remove first occurence (lowest index) of elem*/
    public void removeElem(E elem);
    
    public int size();
    
    /**Lowest index in use, if vector is empty return -1*/
    public int minIndex();
    
    /**Highest index in use, if vector is empty return -1*/
    public int maxIndex();
    
    /**One line per element with index and value, sorted by index*/
    public String toString();         
    
    /**Return null if not available*/
    public E get(int pos);
    
    /**Return the SparseVector as a regular array with value null at unused indexes*/
    public Object[] toArray();
    
    /**Return a List of the values of the Vector in sorted order*/
    public List<E> sortedValues();
}