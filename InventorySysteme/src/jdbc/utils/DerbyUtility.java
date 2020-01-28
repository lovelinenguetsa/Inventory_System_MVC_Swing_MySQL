package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DerbyUtility {

	public static Connection getConnectionInMemoryDatabase() throws SQLException {
		String url = "jdbc:derby:memory:myDerby;create=true";
		return DriverManager.getConnection(url);
	}

	 
	public static void createTestTable(Connection c) throws SQLException {
		
	
			try (Statement stm = c.createStatement()) {
				
				stm.execute("CREATE SCHEMA AUTHORIZATION loveline");
				
				String sql = "CREATE TABLE users ("
	                    + "UID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
	                    + "NAME VARCHAR(45) NOT NULL,"
	                    + "PASSWORD VARCHAR(45) NOT NULL,"
	                    + "EMAIL VARCHAR(45) NOT NULL,"
	                    + "COUNTRY VARCHAR(45) NOT NULL,"
	                    + "STATE VARCHAR(45) NOT NULL,"
	                    + "PRIMARY KEY (UID))";

		      stm.executeUpdate(sql);


				System.out.println("Tabelle wurde erzeugt");
			}
	
	}


	public static void createloginTable(Connection c) {
		// TODO Auto-generated method stub
		
	}
	
	
	}

	