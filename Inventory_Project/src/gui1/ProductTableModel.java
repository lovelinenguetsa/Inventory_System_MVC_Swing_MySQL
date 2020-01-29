package gui1;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model1.Product;


public class ProductTableModel extends AbstractTableModel {
	
	private List<Product> db;
	
	private String[] colNames = {"ID", "Product Name", "Quantity", "Original Price", "Selling Price", "Profit", "Supplier", "Supplier Contact"};
	
	public ProductTableModel() {
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}



	public void setData(List<Product> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Product product = db.get(row);
		
		switch(col) {
		case 0:
			return product.getId();
		case 1:
			return product.getProductCategory();
		case 2:
			return product.getQuantity();
		case 3:
			return product.getOriginalPrice();
		case 4:
			return product.getSellingPrice();
		case 5:
			return product.getProfit();
		case 6:
			return product.getSupCat();
		case 7:
			return product.getSupplierContact();
		}
		
		return null;
	}

}