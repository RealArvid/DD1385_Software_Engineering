import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Stack;

class MyTree extends TreeFrame {
    static String fileName="liv.xml";

    // Overrides method in TreeFrame
    void initTree() {
		try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());
            root = buildTree(scanner);
            scanner.close();
			treeModel = new DefaultTreeModel(root);
			tree = new JTree(treeModel);
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not find the file \"" + fileName + "\"");
            e.printStackTrace();
			System.exit(-1); // Adding this line of code prevents the other methods in the TreeFrame constructor to throwing errors (beceause now they're never called)
        }
    }


    public static MyTreeNode buildTree(Scanner scanner){
		MyTreeNode root = new MyTreeNode("Testart", "Testnamn", "Testtext"); // Created as a dummy, which is later removed
		Stack<MyTreeNode> stack = new Stack<>();
        stack.add(root);
		scanner.nextLine(); // Skips the first line: <?xml version="1.0" encoding="UTF-8"?>
        while(scanner.hasNext()){
            String row = scanner.nextLine();
            if(row.charAt(1) != '/'){ // Adding another level to the hierarchy
				MyTreeNode newNode = new MyTreeNode(row);
				stack.peek().add(newNode); // Adding connection between parent node and child node
				stack.add(newNode);
            }
            else // Removing one level from the hierarchy
                stack.pop();
        }
		root = (MyTreeNode) root.children().nextElement(); // Removing the dummy root
		root.removeFromParent();
		return root;
    }


    // Overrides method in TreeFrame
    void showDetails(TreePath treePath){
		if (treePath == null) // The case when no button is pressed
			return;
		MyTreeNode node = (MyTreeNode) treePath.getLastPathComponent();
		String returnString = node.levelName + ": " + node.name + " " + node.description; // Example: Art: Mås gillar Vaxholmsbåtar
		if(node.getLevel() > 0){ // Prevents any extra printing for the root object
			returnString += "\nMen allt som";
			while(node != null){
				returnString += " är " + node.toString();
				node = (MyTreeNode) node.getParent();
			}			
		} 
		JOptionPane.showMessageDialog(this, returnString);
    }


    public static void main(String[] args) {
		if(args.length>0)
			fileName=args[0];
		new MyTree();
    }
}