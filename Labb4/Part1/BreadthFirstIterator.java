import java.util.*;
// Inspired/modified from https://fjp.at/design-patterns/composite#:~:text=The%20Iterator%20Pattern%20is%20commonly,is%20to%20throw%20an%20exception.

public class BreadthFirstIterator implements Iterator<Component> {
	Queue<Iterator<Component>> queue = new LinkedList<>();
   
	public BreadthFirstIterator(Iterator<Component> iterator) {
		queue.add(iterator);
	}
	
	@Override
	public Component next() {
        Iterator<Component> iterator = queue.peek();
		// When calling iterator.next(), the iterator stored in the queue is simultaneously updated so that is pointer is pushed forward one step
        Component component = iterator.next();
		if(component instanceof Container){
			Container container = (Container) component;
        	queue.add(container.contents.iterator()); // Calls the built-in .iterator()-method
		}
        return component;
    }
	
	@Override
	public boolean hasNext() {
		if (queue.isEmpty())
			return false;
        else {
			Iterator<Component> iterator = queue.peek();
			if (!iterator.hasNext()) {
				queue.remove(); // Removes empty iterators from the queue
				return hasNext();
			}
            else
				return true;
		}
	}
}