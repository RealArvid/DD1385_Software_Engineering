public class NumberedItem<T> implements Comparable<NumberedItem<?>>{
    int idx;
    T value;

    NumberedItem(int idx, T value){
        if(idx < 0)
            this.idx = 0;
        else
            this.idx = idx;
        this.value = value;
    }

    @Override
    public int compareTo(NumberedItem<?> other){ // The wildcard <?>, as opposed to <T>, is necessary if we want to be able to compare instances of the class with different generic attributes
        return this.idx - other.idx;
    }

    @Override
    public String toString(){
        return "Index: " + this.idx + ", Value: " + this.value;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof NumberedItem<?>){
            NumberedItem<?> numberedItem = (NumberedItem<?>) object;
            if(numberedItem.idx == this.idx)
                return true;
        }
        return false;
    }
}