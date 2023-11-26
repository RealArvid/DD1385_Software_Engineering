import java.util.*;
// Inspired/modified from https://fjp.at/design-patterns/composite#:~:text=The%20Iterator%20Pattern%20is%20commonly,is%20to%20throw%20an%20exception.

public class DepthFirstIterator implements Iterator<Component> {
	Stack<Iterator<Component>> stack = new Stack<>();
   
	public DepthFirstIterator(Iterator<Component> iterator) {
		stack.push(iterator);
	}
	
	@Override
	public Component next() {
        Iterator<Component> iterator = stack.peek();
		// When calling iterator.next(), the iterator stored in the stack is simultaneously updated so that is pointer is pushed forward one step
        Component component = iterator.next(); 
		if(component instanceof Container){
			Container container = (Container) component;
        	stack.push(container.contents.iterator()); // Calls the built-in .iterator()-method
		}
        return component;
    }
	
	@Override
	public boolean hasNext() {
		if (stack.isEmpty())
			return false;
        else {
			Iterator<Component> iterator = stack.peek();
			if (!iterator.hasNext()) {
				stack.pop(); // Pops empty iterators from the stack
				return hasNext();
			}
            else
				return true;
		}
	}
}