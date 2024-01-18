import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;        
import java.awt.*;
import java.awt.event.*;
class TreeFrame extends JFrame implements ActionListener {
    JCheckBox box;
    JTree tree;
    DefaultMutableTreeNode root;
    DefaultTreeModel treeModel;
    JPanel controls;
    static final String closeString = " Close ";
    static final String showString = " Show Details ";

    TreeFrame() {
		initTree(); // initTree() should create root, treeModel and tree.

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (box.isSelected())
					showDetails(tree.getPathForLocation(e.getX(), e.getY()));
			}
		});
			
		controls = new JPanel();
		box = new JCheckBox(showString);
		initGUI();
		add(controls, BorderLayout.NORTH);
		add(tree, BorderLayout.CENTER);   
		setVisible(true);
    } 

    public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(closeString))
			dispose();
    }

    void initGUI() {
		tree.setFont(new Font("Dialog", Font.BOLD, 12));
		controls.add(box);
		addButton(closeString);
		controls.setBackground(Color.lightGray);
		controls.setLayout(new FlowLayout());    
		setSize(400, 400);
    }
 
    void addButton(String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.addActionListener(this);
		controls.add(button);
    }

    // ***** Override method initTree in your subclass
    // ***** create root, treeModel and tree in the new initTree
    void initTree(){
		root = new DefaultMutableTreeNode("TestString");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
    }

    // ***** showDetails can also be overridden in a subclass *****
    void showDetails(TreePath path){
		if (path == null) // The case when no button is pressed
			return;
		String info = path.getLastPathComponent().toString();
		JOptionPane.showMessageDialog(this, info);
    }

    public static void main(String[] u) {
		new TreeFrame();
    }
}