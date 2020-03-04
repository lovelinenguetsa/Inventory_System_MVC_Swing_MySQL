package gui1;

import java.awt.event.MouseEvent;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;



public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {
private ServerTreecellRenderer renderer;
private ServerInfo info;
private JCheckBox checbox;
public ServerTreeCellEditor() {
	renderer= new ServerTreecellRenderer();
}
	@Override
	public boolean isCellEditable(EventObject e) {
		if (!(e instanceof MouseEvent)) return false;
		
			MouseEvent mouseevent= (MouseEvent) e;
			JTree tree= (JTree) e.getSource();
			TreePath treepath= tree.getPathForLocation(mouseevent.getX(), mouseevent.getY());
	if (treepath== null) {
		return false;
	}
	
	Object lastComponent= treepath.getLastPathComponent();
	if (lastComponent== null) {
		return false;
	}
	DefaultMutableTreeNode treenode= (DefaultMutableTreeNode) lastComponent;
	return treenode.isLeaf();
	
	
	}

	@Override
	public Object getCellEditorValue() {
		info.setChecked(checbox.isSelected());
		return info;
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		Component component=  renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
		if (leaf) {
			DefaultMutableTreeNode treenode= (DefaultMutableTreeNode) value;
			 info= (ServerInfo) treenode.getUserObject();
			 checbox= (JCheckBox) component;
			ItemListener itemlistener= new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
				fireEditingStopped();
					checbox.removeItemListener(this);
				}
			};
			checbox.addItemListener(itemlistener);
		}
		return component;
	}

}
