package gui1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;

import model1.Product;


public class TablePanel extends JPanel {
	
	private JTable table;
	private ProductTableModel tableModel;
	private JPopupMenu popup;
	private ProductTableListener personTableListener;
	
	public TablePanel() {
		
		tableModel = new ProductTableModel();
		table = new JTable(tableModel);
		popup= new JPopupMenu();
		
		JMenuItem removeItem= new JMenuItem("Delete row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mousePressed(MouseEvent e) {
				
				int row= table.rowAtPoint(e.getPoint());

table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton()== MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row= table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
				
			}
		});
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Product> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setPersonTableListener(ProductTableListener listener) {
		this.personTableListener= listener;
		
	}
}
