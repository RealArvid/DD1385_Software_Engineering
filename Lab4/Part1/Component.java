abstract class Component{
    private String name;
    private Double weight;

    Component(String name, Double weight){
        this.name = name;
        this.weight = weight;
    }

    public String getName(){
        return name;
    }

    public Double getWeight(){
        return weight;
    }

    // This method is never used directly, but overridden in the Item and Container classes
    public String toString(String padding){
        return "dummyText";
    }

    @Override
    public String toString(){
        return name + ": " + weight + " kg";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Component) {
            Component component = (Component) obj;
            if(this.name.equals(component.name) && this.weight.equals(component.weight)){
                return true;
            }
        }
        return false;
    }
}