package gui1;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

class ServerInfo{
	@Override
	public String toString() {
		return id+ ":"+name;
	}
	private String name;
	private int id;
	
	private boolean checked;
	public ServerInfo(String name, int id, boolean checked) {
		this.name= name;
		this.id= id;
		this.checked= checked;
	}
	
	
	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
 class MessagePanel extends JPanel {
	
	
	private  JTree tree;
	private ServerTreecellRenderer treerenderer;
	private ServerTreeCellEditor treeeditor;
	
	
public MessagePanel() {
	setLayout(new BorderLayout());
	tree= new JTree(createTree());
	treerenderer= new ServerTreecellRenderer();
	
	tree.setCellRenderer(treerenderer);
	
	tree.setEditable(true);
	tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	tree.addTreeSelectionListener(new TreeSelectionListener() {
		
		@Override
		public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node= (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			Object userObject= node.getUserObject();
//			if (userObject instanceof ServerInfo) {
//				int id= ((ServerInfo) userObject).getId();
//				System.out.println(" got User with id "+ id);
//			}
			System.out.println(userObject);
		}
	});
	add(new JScrollPane(tree), BorderLayout.CENTER);
}

private DefaultMutableTreeNode createTree() {
	DefaultMutableTreeNode top= new DefaultMutableTreeNode("Servers");
	DefaultMutableTreeNode branch1= new DefaultMutableTreeNode("USA");
	DefaultMutableTreeNode server1= new DefaultMutableTreeNode(new ServerInfo("NY",0, true));
	DefaultMutableTreeNode server2= new DefaultMutableTreeNode(new ServerInfo("LA",1, false));
	DefaultMutableTreeNode server3= new DefaultMutableTreeNode(new ServerInfo("Boston",2, false));
	
	branch1.add(server1);
	branch1.add(server2);
	branch1.add(server3);
	
	DefaultMutableTreeNode branch2= new DefaultMutableTreeNode("UK");
	DefaultMutableTreeNode server4= new DefaultMutableTreeNode(new ServerInfo("London",3, true));
	DefaultMutableTreeNode server5= new DefaultMutableTreeNode(new ServerInfo("Manchester",4, false));
	DefaultMutableTreeNode server6= new DefaultMutableTreeNode(new ServerInfo("Birmingham",5, false));
	
	branch2.add(server4);
	branch2.add(server5);
	branch2.add(server6);
	
	top.add(branch1);
	top.add(branch2);
	
	return top;
}
}
