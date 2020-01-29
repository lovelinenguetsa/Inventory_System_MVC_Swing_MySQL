import model1.Database;

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
		db.disconnect();
	}

}
