package controler1;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui1.FormEvent;
import gui1.FormEventUser;
import model1.Database;
import model1.Gender;
import model1.Product;
import model1.ProductCategory;
import model1.SupplierCategory;
import model1.Users;

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
	
	
	/*
	 * Imprt export files
	 */
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void removePerson(int row) {
		db.removeProduct(row);
		
	}
	
	
	//// for User
	
	
	public void addUsers(FormEventUser ev) {
		String name= ev.getName();
		char[] pwd= ev.getPwd();
		String birthdate= ev.getBirthdate();
		String gender = ev.getGender();
		
Gender genderCat;
		
		if(gender.equals("male")) {
			genderCat = Gender.male;
		}
		else {
			genderCat = Gender.female;
		}
		
		Users user= new Users(name, pwd, birthdate, genderCat);
		
		db.addUsers(user);
	}
	
	public List<Users> getUsers() {
		return db.getUsers();
	}
	
	/*
	 * Database
	 */
	public void connect() throws SQLException {
	db.connect();
	}
	public void load() throws SQLException {
		db.load();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void close() throws SQLException {
		db.disconnect();
	}
	
	public void loadlogdata() throws SQLException {
		db.loadlogdata();;
	}
	
	public void insertlogdata() throws SQLException{
		db.insertlogdata();
	}
}