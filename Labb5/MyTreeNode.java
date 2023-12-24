import javax.swing.tree.DefaultMutableTreeNode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTreeNode extends DefaultMutableTreeNode{
    static Pattern pattern = Pattern.compile("<([\\wåäöÅÄÖ]+) namn=\"([\\wåäöÅÄÖ ]+)\"> ([\\wåäöÅÄÖ ]+)");
    String levelName;
    String name;
    String description;

    // Unused in final implementation
    MyTreeNode(String levelName, String name, String description){
        this.levelName = levelName;
        this.name = name;
        this.description = description;
    }

    MyTreeNode(String row){ // row Should be of the type: <Biosfär namn="Liv"> är allt som fortplantar sej
        Matcher matcher = pattern.matcher(row);
        if(matcher.find()){
            this.levelName = matcher.group(1);
            this.name = matcher.group(2);
            this.description = matcher.group(3);
        }
        else{
            System.out.println("Error when parsing row:\n" + row + "\nCould not extract information from row using the defined regular expression");
            System.exit(1);					
        }
    }

    @Override
    public String toString(){
        return name;
    }
}