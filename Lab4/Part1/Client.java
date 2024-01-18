import java.util.Iterator;

public class Client {
    public static void main(String args[]){
        
        Container plasticBag = new Container("Plastpåse", 0.01);
        plasticBag.add(new Item("Munskölj", 0.5));

        Container computerBag = new Container("Datorväska", 0.2);
        computerBag.add(new Item("Dator", 1.3));
        computerBag.add(new Item("Laddare", 0.2));

        Container toiletryBag = new Container("Necessär",0.05);
        toiletryBag.add(new Item("Tandborste", 0.02));
        toiletryBag.add(new Item("Tandkräm", 0.1));
        toiletryBag.add(new Item("Schampo", 0.1));
        toiletryBag.add(new Item("Balsam", 0.1));
        toiletryBag.add(plasticBag);

        Container suitcase = new Container("Resväska", 3.0);
        suitcase.add(new Item("Jeans", 0.8));
        suitcase.add(toiletryBag);
        suitcase.add(computerBag);
        suitcase.add(new Item("Tröja", 0.3));
        suitcase.add(new Item("Skjorta", 0.5));


        int count = 0;
        System.out.println("Depth-First Search: Printing suitcase contents using a for-loop, calling iterator()-method implicitly");
        for(Component component : suitcase){
            System.out.println(component.getName());
            count ++;
        }
        System.out.println("Item count: " + count + "\n");

        count = 0;
        Iterator<Component> iterator = suitcase.breadthFirstIterator();
        System.out.println("Breadth-First Search: Printing suitcase contents using a while-loop, calling the breadthFirstIterator()-method");
        while(iterator.hasNext()){
            System.out.println(iterator.next().getName());
            count ++;
        }
        System.out.println("Item count: " + count + "\n");
    
        System.out.println("Printing suitcase and its contents calling the toString()-method implicitly (System.out.println(suitcase))");
        System.out.println(suitcase);
        System.out.println("Total vikt: " + suitcase.getWeight() + " kg" + "\n");
        
        suitcase.getContainer("Necessär").getContainer("Plastpåse").remove("Balsam");
        suitcase.getContainer("Necessär").getContainer("Plastpåse").add(new Item("Inpackning", 0.3));
        System.out.println("Printing suitcase and its updated contents calling the toString()-method implicitly (System.out.println(suitcase))");
        System.out.println(suitcase);
        System.out.println("Ny vikt: " + suitcase.getWeight() + " kg" + "\n");
    }
}