package loginPage;

import java.awt.EventQueue;

public class LogApp {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame01 = new RegisterForm();
					frame01.setVisible(true);
					LoginForm frame02 = new LoginForm();
					frame02.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	}


