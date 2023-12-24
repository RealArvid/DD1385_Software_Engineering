import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
class DirTree2 extends TreeFrame {

    static String directory=".";

    // Overrides method in TreeFrame
    void initTree() {
		root = new DefaultMutableTreeNode(directory);
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		buildTree();
    }

    // New method
    private void buildTree() {
		File file = new File(directory);
		String[] list = file.list(); 
		for(int i=0; i<list.length; i++ )
			buildTree(new File(file, list[i]), root);
    }

    // New method
    private void buildTree(File file, DefaultMutableTreeNode parent){
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.toString());
		parent.add(child);
		if(file.isDirectory()){
			String[] list = file.list();
			for(int i = 0; i < list.length; i++)
				buildTree(new File(file, list[i]), child);
		}
    }

    // Overrides method in TreeFrame
    void showDetails(TreePath treePath){
		if (treePath == null) // The case when no button is pressed
			return;
		File file = new File(treePath.getLastPathComponent().toString());
		JOptionPane.showMessageDialog(this, file.getPath() + "\n   " + getAttributes(file));
    }

    // New method
    private String getAttributes(File file) {
		String text = "";
		
		if(file.isDirectory())
			text += "Directory\n   ";
		else
			text += "Nondirectory file\n   ";
		
		if(file.canRead())
			text += "Readable\n   ";
		else
			text += "not Readable\n   ";
		
		if(file.canWrite())
			text += "Writeable\n  ";
		else
			text += "not Writeable\n  ";
		
		if(file.isDirectory()){
			text += "Contains files: \n     ";
			String[] contents = file.list();
			for(int i = 0; i < contents.length; i++ )
				text += contents[i] + ", ";
			text += "\n";
		}
		else
			text += "Size in bytes: " + file.length() + "\n   ";

		return text;
    }

    public static void main(String[] args) {
		if(args.length>0)
			directory=args[0];
		new DirTree2();
    }
}