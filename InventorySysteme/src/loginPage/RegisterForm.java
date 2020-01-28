package loginPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jdbc.utils.DerbyUtility;

public class RegisterForm extends JFrame implements ActionListener {

	private JLabel nameLabel, registerLabel, emailLabel, pwdLabel, confpwdLabel, countryLabel, stateLabel;
	private JTextField nameField, emailField, countryfield, statefield;
	private JPasswordField pwdField, confpwdField;
	private JButton submitbtn, clearbtn;

	/**
	 * Create the frame.
	 */
	public RegisterForm() {

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registration");

		registerLabel = new JLabel("Sign Up");
		registerLabel.setForeground(Color.blue);
		registerLabel.setFont(new Font("serif", Font.BOLD, 20));

		nameLabel = new JLabel("Name:");
		emailLabel = new JLabel("Email ID:");
		pwdLabel = new JLabel("Create Password:");
		confpwdLabel = new JLabel("Confirm password:");
		countryLabel = new JLabel("Country:");
		stateLabel = new JLabel("State:");

		nameField = new JTextField(15);
		emailField = new JTextField(15);
		countryfield = new JTextField(15);
		statefield = new JTextField(15);
		pwdField = new JPasswordField(15);
		confpwdField = new JPasswordField(15);

		submitbtn = new JButton("Submit");
		clearbtn = new JButton("Clear");
		submitbtn.addActionListener(this);
		clearbtn.addActionListener(this);

		layoutComponent();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == submitbtn) {
			int x = 0;
			String s1 = nameField.getText();
			String s2 = emailField.getText();

			char[] s3 = pwdField.getPassword();
			char[] s4 = confpwdField.getPassword();
			String s8 = new String(s3);
			String s9 = new String(s4);

			String s5 = statefield.getText();
			String s6 = countryfield.getText();

			if (s8.equals(s9)) {
				
				

			try (Connection c = DerbyUtility.getConnectionInMemoryDatabase()) {
				
				
					
					/**
					 *  erzeugen eine Tabelle in der Datenbank
					 */
				DerbyUtility.createTestTable(c);

					/**
					 * Insert Into preparedStatement
					 */

					String prep_sql = "INSERT INTO  users(NAME,PASSWORD,EMAIL,COUNTRY,STATE) VALUES(?,?,?,?,?)";

					try (PreparedStatement stm = c.prepareStatement(prep_sql)) {
						stm.setString(1, s1);
						stm.setString(2, s2);
						stm.setString(3, s8);
						stm.setString(4, s5);
						stm.setString(5, s6);

					int rs = stm.executeUpdate();
								 

						x++;
						if (x > 0) {
							JOptionPane.showMessageDialog(submitbtn, "Data Saved Successfully");
						}

				}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(submitbtn, "Data not Saved");
			}

		} else {
			nameField.setText("");
			emailField.setText("");
			pwdField.setText("");
			confpwdField.setText("");
			countryfield.setText("");
			statefield.setText("");

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
		add(registerLabel, gc);

//		next row/////
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy++;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(nameLabel, gc);

		gc.gridx = 1;

		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

//			next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(emailLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(emailField, gc);

//			next row/////
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
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(confpwdLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(confpwdField, gc);

//			next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(countryLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(countryfield, gc);

//			next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(stateLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(statefield, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(10, 0, 0, 10);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(submitbtn, gc);

		gc.gridx = 1;
		gc.insets = new Insets(10, 10, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(clearbtn, gc);
	}

}
