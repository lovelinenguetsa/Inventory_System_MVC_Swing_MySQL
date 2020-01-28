package loginPage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import jdbc.utils.DerbyUtility;

import java.awt.Color;

public class LoginForm extends JFrame implements ActionListener {

	private JLabel loginLabel, emailLabel, pwdLabel;
	private JTextField emailField;
	private JButton submitBtn;
	private JPasswordField pwdField;

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");

		loginLabel = new JLabel("Login");
		loginLabel.setForeground(Color.blue);
		loginLabel.setFont(new Font("Serif", Font.BOLD, 20));

		emailLabel = new JLabel("Enter Email:");
		pwdLabel = new JLabel("Enter Password:");
		emailField = new JTextField(15);
		pwdField = new JPasswordField(15);
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);

		layoutComponent();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			showData();
		}
	}

	public void showData() {

		System.out.println("data");
		
		JFrame f1 = new JFrame();
		JLabel l1, l2;
		String str1 = emailField.getText();
		char[] p = pwdField.getPassword();
		String str2 = new String(p);

		try (Connection c = DerbyUtility.getConnectionInMemoryDatabase()) {
			
			DerbyUtility.createTestTable(c);

			String prep_sql = "SELECT NAME  FROM users WHERE EMAIL=? and PASSWORD=?";

			try (PreparedStatement stm = c.prepareStatement(prep_sql)) {
				stm.setString(1, str1);
				stm.setString(2, str2);
				
				

				try (ResultSet result = stm.executeQuery();) {
					
					ResultSetMetaData rswd = result.getMetaData();

					System.out.println(rswd.getColumnName(1));
					
					while (result.next()) {
						System.out.printf("%3d | %12s | %12s | %12d %n", result.getString(1), result.getString(2),
								result.getString(3));
					}
					if (result.next()) {
						
						
						f1.setVisible(true);
						f1.setSize(500, 500);
						f1.setLayout(null);

						l1 = new JLabel();
						l2 = new JLabel("you are succefully logged in..");
						l2.setForeground(Color.blue);
						l2.setFont(new Font("Serif", Font.BOLD, 30));
						l1.setBounds(60, 50, 400, 30);
						l2.setBounds(60, 100, 400, 40);

						f1.add(l1);
						f1.add(l2);
						l1.setText("Welcome " + result.getString(1));
						l1.setForeground(Color.red);
						l1.setFont(new Font("Serif", Font.BOLD, 30));
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Incorrect email-Id or password..Try Again with correct detail");
					}

				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	private void layoutComponent() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

//		first row/////
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(loginLabel, gc);

//		next row/////
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy++;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(emailLabel, gc);

		gc.gridx = 1;

		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(emailField, gc);

//		next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(pwdLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(pwdField, gc);

//			next row/////

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(submitBtn, gc);

	}

}
