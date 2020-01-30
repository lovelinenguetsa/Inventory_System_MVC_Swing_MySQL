import java.sql.SQLException;

import model1.Database;
import model1.Product;
import model1.ProductCategory;
import model1.SupplierCategory;

public class Test_Database {

	public static void main(String[] args) {
		System.out.println("running...");
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		db.addProduct(new Product(ProductCategory.decoration, 2, "2", "3", "1", SupplierCategory.Denver, "12345678"));
		db.addProduct(
				new Product(ProductCategory.location, 2, "23", "34", "12", SupplierCategory.Ml_Distributor, "123456890"));

		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.load();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			db.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
