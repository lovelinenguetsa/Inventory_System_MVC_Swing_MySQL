package controler1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui1.FormEvent;

import model1.Database;

import model1.Product;
import model1.ProductCategory;
import model1.SupplierCategory;

public class Controller {
	Database db = new Database();
	
	public List<Product> getProduct() {
		return db.getProduct();
	}
	
	public void addPerson(FormEvent ev) {
		int ProdCatId= ev.getProductname();
		int quantity= ev.getQuantity();
		String originalPrice= ev.getOriginalPrice();
		String sellingPrice= ev.getSellingPrice();
		String profit=ev.getProfit();
		String supCat= ev.getSuppliername();
		String supContact= ev.getSupplierContact();
		
		ProductCategory prodCategory = null;
		
		switch(ProdCatId) {
		case 0:
			prodCategory= ProductCategory.cercueil;
			break;
		case 1:
			prodCategory= ProductCategory.location;
			break;
		case 2:
			prodCategory= ProductCategory.decoration;
			break;
		}
		
		SupplierCategory supplierCategory;
		
		if(supCat.equals("Ml Distributor")) {
			supplierCategory = SupplierCategory.Ml_Distributor;
		}
		else if(supCat.equals("Smart Sarl")) {
			supplierCategory = SupplierCategory.Smart_Sarl;
		}
		else if(supCat.equals("Denver")) {
			supplierCategory = SupplierCategory.Denver;
		}
		else {
			supplierCategory = SupplierCategory.Other;
			System.err.println(supCat);
		}
		
	
		
		Product product = new Product(prodCategory, quantity, originalPrice,sellingPrice,profit, supplierCategory, supContact);
		
		db.addProduct(product);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void removePerson(int row) {
		db.removeProduct(row);
		
	}
}