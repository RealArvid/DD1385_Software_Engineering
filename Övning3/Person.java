import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
        // if(this.age < other.age){
        //     return 1;
        // }
        // else if(this.age == other.age){
        //     return 0;
        // }
        // else{
        //     return -1;
        // }
    }

    public String toString(){
        return "Person name: " + name + ", age: " + age;
    }
    
    public static void main(String[] args){
        List<Person> people = new ArrayList<>();
        people.add(new Person("Mike", 65));
        people.add(new Person("Theo", 23));
        people.add(new Person("Arvid", 28));

        Collections.sort(people);
        for (Person p : people){
            System.out.println(p);
        }
    }
}
