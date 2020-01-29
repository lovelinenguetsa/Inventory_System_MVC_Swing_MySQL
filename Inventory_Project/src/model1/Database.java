package model1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;



public class Database {

	
private Connection con;	
private List<Product> product;

public Database() {
	product= new LinkedList<>();
}

public void addPerson(Product product1) {
	product.add(product1);
}

public List<Product> getPeople(){
	return Collections.unmodifiableList(product);
}

public void saveToFile(File file) throws IOException{
	FileOutputStream fos= new FileOutputStream(file);
	ObjectOutputStream oos= new ObjectOutputStream(fos);
	
	Product[] person= product.toArray(new Product[product.size()]);
	
	oos.writeObject(person);
	
	oos.close();
}

//////SERIALISATION/////

public void loadFromFile(File file) throws IOException {
	FileInputStream fis= new FileInputStream(file);
	ObjectInputStream ois= new ObjectInputStream(fis);
	
	try {
		Product[] persons= (Product[]) ois.readObject();
		
		product.clear();
		product.addAll(Arrays.asList(persons));
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ois.close();
}

public void removePerson(int row) {
	product.remove(row);
	
}

//// Connect To database////

public void connect() throws Exception {
	
	if(con != null) return;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		throw new Exception("Driver not found");
	}
	
	String url = "jdbc:mysql://localhost:3306/swingtest";
	
	con = DriverManager.getConnection(url, "root", "g+64vethU8%hfg");
}

public void disconnect() {
	if(con != null) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Can't close connection");
		}
	}
}

//public void connect() {
//	
//	if (con!= null) {
//		return;
//	}
//	try {
//		DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	
//	String url= "jdbc:mysql://localhost:3306/swingtest";;
//	try{
//		con = DriverManager.getConnection(url, "root", "");
//		System.out.println("connected:  "+con);
//	} catch (SQLException e) {
//		
//		e.printStackTrace();
//	}
//}
//
//public void disconnect() {
//	if (con != null) {
//		try {
//			con.close();
//		} catch (SQLException e) {
//			
//			System.out.println("can't close");
//		}
//	}
//}
}
