package model1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gui1.MainFrame;

import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;

public class Database {

	private Connection con;
	private List<Product> product;
	private List<Users> user;

	public Database() {
		product = new LinkedList<>();
		user = new LinkedList<>();
	}

////Connect To database////

	public void connect() throws SQLException {

		if (con != null) {
			return;
		}

		DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

//	String  url = "jdbc:mysql://localhost/product?serverTimezone=UTC";
		String url = "jdbc:mysql://localhost:3306/inventory";

		con = DriverManager.getConnection(url, "root", "");
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
/////Save login data into Database/////

	public void savelogdata() throws SQLException {

		String checkSql = "select count(*) as count from people where id=?";
		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		String insertSql = "insert into users (id, name, pwd, birthdate, gender) values (?,?,?,?,?)";
		PreparedStatement insertstm = con.prepareStatement(insertSql);

		for (Users user : user) {

			int id = user.getId();
			String name = user.getName();
			char[] pwd = user.getPwd();
			java.util.Date birthdate = user.getBirthdate();
			Gender gender = user.getGender();

			checkStmt.setInt(1, id);

			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);

			if (count == 0) {

				System.out.println("inserting user to database");
				int col = 1;
				insertstm.setInt(col++, id);
				insertstm.setString(col++, name);
				insertstm.setString(col++, new String(pwd));

				if (birthdate != null) {
					insertstm.setObject(col++, birthdate);
				} else
					insertstm.setNull(col, 0);

				insertstm.setString(col++, gender.name());

				insertstm.executeUpdate();
			} else {
				System.out.println("name already exist please insert name");

			}
			checkStmt.close();
			insertstm.close();
		}
	}

/////Save product into Database/////

	public void saveProducts() throws SQLException {

		String sql = "select count(*) as count from products where id=?";

		PreparedStatement checkstm = con.prepareStatement(sql);

		String insertSql = "insert into products (id, productname, quantity, originalprice, sellingprice, profit, suppliername, suppliercontact) values (?,?,?,?,?,?,?,?);";

		PreparedStatement insertstm = con.prepareStatement(insertSql);

		String updateSql = "update products set productname=?, quantity=?, originalprice=?, sellingprice=?, profit=?, suppliername=?, suppliercontact=? where id=?;";
		PreparedStatement updatestm = con.prepareStatement(updateSql);
		for (Product product : product) {

			int id = product.getId();
			ProductCategory prod = product.getProductCategory();
			int qtity = product.getQuantity();
			double orprice = product.getOriginalPrice();
			double sellprice = product.getSellingPrice();
			double profit = product.getProfit();
			SupplierCategory supcat = product.getSupCat();
			String contact = product.getSupplierContact();

			checkstm.setInt(1, id);

			ResultSet rs = checkstm.executeQuery();
			rs.next();

			int count = rs.getInt(1);

			if (count == 0) {
				System.out.println("inserting person to " + id);

				int col = 1;

				insertstm.setInt(col++, id);
				insertstm.setString(col++, prod.name());
				insertstm.setInt(col++, qtity);
				insertstm.setDouble(col++, orprice);
				insertstm.setDouble(col++, sellprice);
				insertstm.setDouble(col++, profit);
				insertstm.setString(col++, supcat.name());
				insertstm.setString(col++, contact);

				insertstm.executeUpdate();
			} else
				System.out.println("updating data to " + id);

			int col = 1;

			updatestm.setString(col++, prod.name());
			updatestm.setInt(col++, qtity);
			updatestm.setDouble(col++, orprice);
			updatestm.setDouble(col++, sellprice);
			updatestm.setDouble(col++, profit);
			updatestm.setString(col++, supcat.name());
			updatestm.setString(col++, contact);
			updatestm.setInt(col++, id);

			updatestm.executeUpdate();

			System.out.println("count for product with id " + id + " is" + count);
		}

	}
	public void load() throws SQLException {
		product.clear();

		Statement selectstm = con.createStatement();

		String sql = "select id, productname, quantity, originalprice, sellingprice, profit, suppliername, suppliercontact from products order by productname";
		ResultSet rs = selectstm.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("productname");
			int qtity = rs.getInt("quantity");
			double name1 = rs.getDouble("originalprice");
			double name2 = rs.getDouble("sellingprice");
			double name3 = rs.getDouble("profit");
			String name4 = rs.getString("suppliername");
			String name5 = rs.getString("suppliercontact");
			System.out.println("name = " + id + " " + name1 + " " + name2);

			Product product2 = new Product(id, ProductCategory.valueOf(name), qtity, name1, name2, name3,
					SupplierCategory.valueOf(name4), name5);
			product.add(product2);
			System.out.println(product2);
		}
		rs.close();
		selectstm.close();
	}

	

	public void loadlogdata() throws SQLException {
		user.clear();
		String sql = "select id, name, pwd, birthdate, gender from users order by name";

		Statement selectstm = con.createStatement();
		
		ResultSet rs= selectstm.executeQuery(sql);
		while(rs.next()) {
			int id= rs.getInt("id");
			String name= rs.getString("name");
			String pwd= rs.getString("pwd");
			Date birthdate= rs.getDate("birthdate");
			String gender= rs.getString("gender");
			
			Users userx= new Users(id, name, pwd.toCharArray(), birthdate, Gender.valueOf(gender));
user.add(userx);
		}
		rs.close();
		selectstm.close();
		
	}

	
	

	public void addProduct(Product product1) {
		product.add(product1);
	}

	public void removeProduct(int row) {
		product.remove(row);

	}

	public List<Product> getProduct() {
		return Collections.unmodifiableList(product);
	}

	public List<Users> getUsers() {
		return Collections.unmodifiableList(user);
	}

	public void addUsers(Users users1) {
		user.add(users1);
	}

	public void removeUser(int index) {
		user.remove(index);
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Product[] products = product.toArray(new Product[product.size()]);
		Users[] users = user.toArray(new Users[user.size()]);

		oos.writeObject(products);
		oos.writeObject(users);

		oos.close();
	}

//////SERIALISATION/////

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Product[] products = (Product[]) ois.readObject();
			Users[] users = (Users[]) ois.readObject();
			product.clear();
			user.clear();
			product.addAll(Arrays.asList(products));
			user.addAll(Arrays.asList(users));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}

	
}
