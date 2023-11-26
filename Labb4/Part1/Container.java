import java.util.ArrayList;
import java.util.Iterator;

public class Container extends Component implements Iterable<Component>{
    ArrayList<Component> contents = new ArrayList<>();

    Container(String name, Double weight){
        super(name, weight);
    }

    /**Method for adding a component to a container. As a restriction, it is impossible to add two components with the same name to a container.
    @param component Component-class object to be added*/
    public void add(Component component){
        if(!contents.contains(component))
            contents.add(component);
    }
    
    /**Method for removing a component to a container, using the name of the component.
    @param componentName Component-class object to be added*/
    public void remove(String componentName){
        for(Component component : contents){
            if(component.getName().equals(componentName)){
                contents.remove(component);
                break; // Optional row
            }
        }
    }

    // Unused method
    public Item getItem(String componentName){
        for(Component component : contents){
            if(component.getName().equals(componentName)){
                return (Item) component;
            }
        }
        return null;
    }

    /**Method for returning sub-containers of a container, using the name of the container to be returned.
    If there exists no sub-container with the specified name, null is returned.
    @param componentName String with the name of the component to be returned*/
    public Container getContainer(String componentName){
        for(Component component : contents){
            if(component.getName().equals(componentName)){
                return (Container) component;
            }
        }
        return null;
    }

    @Override
    public Double getWeight(){
        Double totalWeight = super.getWeight(); // Initialized to the weight of the container
        for(Component component : contents){
            totalWeight += component.getWeight();
        }
        return totalWeight;
    }

    @Override
    public String toString(){
        String totalString = super.toString();
        String padding = "\n   ";
        for(Component component : contents){
            totalString += component.toString(padding);
        }
        return totalString;
    }

    
    /**Called on recursively so that each new level in the composite-structure is indented by an increasing amount when printed
    @param padding String specifying the existing padding*/
    @Override
    public String toString(String padding){
        String totalString = padding + super.toString();
        padding += "   "; // New padding is adde to the next level
        for(Component component : contents){
            totalString += component.toString(padding);
        }
        return totalString;
    }

    @Override
    public Iterator<Component> iterator() {
        //return new BreadthFirstIterator(contents.iterator());
        return new DepthFirstIterator(contents.iterator());
	}

    /**Returns a breadth-first search iterator*/
    public Iterator<Component> breadthFirstIterator() {
        return new BreadthFirstIterator(contents.iterator());
	}
    
    /**Returns a depth-first search iterator*/
    public Iterator<Component> depthFirstIterator() {
        return new DepthFirstIterator(contents.iterator());
	}
}