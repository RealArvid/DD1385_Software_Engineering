public class Item extends Component{

    Item(String name, Double weight){
        super(name, weight);
    }

    @Override
    public String toString(String padding){
        return padding + super.toString();
    }
}