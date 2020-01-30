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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class Database {

	private Connection con;
	private List<Product> product;
	private List<Users>  users;

	public Database() {
		product = new LinkedList<>();
		users = new LinkedList<>();
	}

	public void addProduct(Product product1) {
		product.add(product1);
	}
	
	

	public List<Product> getProduct() {
		return Collections.unmodifiableList(product);
	}
	
	public void load() throws SQLException {
		product.clear();
		
		
		Statement selectstm = con.createStatement();
			
		
		String sql= "select id, productname, quantity, originalprice, sellingprice, profit, suppliername, suppliercontact from products order by productname";
	ResultSet rs= selectstm.executeQuery(sql);
	
	while (rs.next()) {
		int id=rs.getInt("id");
		String name= rs.getString("productname");
		int qtity=rs.getInt("quantity");
		String name1= rs.getString("originalprice");
		String name2= rs.getString("sellingprice");
		String name3= rs.getString("profit");
		String name4= rs.getString("suppliername");
		String name5= rs.getString("suppliercontact");
		System.out.println("name = "+id+" "+name1+" "+name2);
		
		 Product product2=new Product(id,ProductCategory.valueOf(name),qtity, name1, name2, name3, SupplierCategory.valueOf(name4), name5 );
		 product.add(product2);
		 System.out.println(product2);
	}
		
	
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Product[] products = product.toArray(new Product[product.size()]);

		oos.writeObject(products);

		oos.close();
	}

//////SERIALISATION/////

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Product[] products = (Product[]) ois.readObject();

			product.clear();
			product.addAll(Arrays.asList(products));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}

	public void removeProduct(int row) {
		product.remove(row);

	}

//// Connect To database////

	public void connect() throws SQLException {

		if (con != null) {
			return;
		}
		
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		

//	String  url = "jdbc:mysql://localhost/product?serverTimezone=UTC";
		String url = "jdbc:mysql://localhost:3307/inventory";
		
			con = DriverManager.getConnection(url, "root", "12345");
			System.out.println("connected:  " + con);
		
	}

	public void disconnect() throws SQLException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

				System.out.println("can't close");
			}
		}
	}

/////Save product into Database/////

	public void save()  throws SQLException {

		String sql = "select count(*) as count from products where id=?";

		PreparedStatement checkstm = con.prepareStatement(sql) ;

			String insertSql = "insert into products (id, productname, quantity, originalprice, sellingprice, profit, suppliername, suppliercontact) values (?,?,?,?,?,?,?,?);";

			PreparedStatement insertstm = con.prepareStatement(insertSql);
				
				String updateSql = "update products set productname=?, quantity=?, originalprice=?, sellingprice=?, profit=?, suppliername=?, suppliercontact=? where id=?;";
				PreparedStatement updatestm = con.prepareStatement(updateSql); 
				for (Product product : product) {
				
				int id = product.getId();
				ProductCategory prod = product.getProductCategory();
				int qtity = product.getQuantity();
				String orprice = product.getOriginalPrice();
				String sellprice = product.getSellingPrice();
				String profit = product.getProfit();
				SupplierCategory supcat = product.getSupCat();
				String contact = product.getSupplierContact();

				
				
				checkstm.setInt(1, id);

				ResultSet rs = checkstm.executeQuery();
				rs.next();

				int count = rs.getInt(1);

				if (count == 0) {
					System.out.println("inserting data to " + id);
					
					int col=1;
					
					insertstm.setInt(col++, id);
					insertstm.setString(col++, prod.name());
					insertstm.setInt(col++, qtity);
					insertstm.setString(col++, orprice);
					insertstm.setString(col++, sellprice);
					insertstm.setString(col++, profit);
					insertstm.setString(col++, supcat.name());
					insertstm.setString(col++, contact);
					
					insertstm.executeUpdate();
				} else
					System.out.println("updating data to " + id);
				
				int col=1;
				
				
				updatestm.setString(col++, prod.name());
				updatestm.setInt(col++, qtity);
				updatestm.setString(col++, orprice);
				updatestm.setString(col++, sellprice);
				updatestm.setString(col++, profit);
				updatestm.setString(col++, supcat.name());
				updatestm.setString(col++, contact);
				updatestm.setInt(col++, id);
				
				updatestm.executeUpdate();

				System.out.println("count for product with id " + id + " is" + count);
			}
		

	}
	
/////Save login data into Database/////
	
	public void insertlogdata() throws SQLException{
		
			String insertSql = "insert into users (name, pwd, birthdate, gender) values (?,?,?,?)";
			PreparedStatement insertstm = con.prepareStatement(insertSql);
			
			for (Users user : users) {
			
			String name =user.getName();
			char[] pwd= user.getPwd();
			String birthdate = user.getBirthdate();
			Gender gender= user.getGender();

			

			
				int col=1;
				
				insertstm.setString(col++,name);
				insertstm.setString(col++, new String(pwd));
				
				if (birthdate!= null) {
					insertstm.setString(col++, birthdate);
				}else insertstm.setNull(col, 0);
				insertstm.setString(col++, birthdate);
				insertstm.setString(col++, gender.name());
				
				
				insertstm.executeUpdate();
			
		}
	}

	public void loadlogdata() throws SQLException{
		String sql = "select * from users where name=? and pwd=?";

		PreparedStatement checkstm = con.prepareStatement(sql) ;
		
		for (Users user : users) {
		String name =user.getName();
		char[] pwd= user.getPwd();
		
		int col=1;
		
		checkstm.setString(col++,name);
		checkstm.setString(col++, new String(pwd));

		ResultSet rs = checkstm.executeQuery();
		if(rs.next()) {
			
			///insert MainFrame///
		}else {
			///Show messagedialog
		}

		
		}	
		
	}

	public List<Users> getUsers() {
		return Collections.unmodifiableList(users);
	}
	public void addUsers(Users users1) {
		users.add(users1);
	}
}

