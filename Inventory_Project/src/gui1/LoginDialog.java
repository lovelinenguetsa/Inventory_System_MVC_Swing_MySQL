package gui1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import controler1.Controller;


public class LoginDialog extends JFrame implements ActionListener {
	private JButton okbut;
	private JButton cancelbut;

	private JTextField textField;
	private JPasswordField password;
	private LoginListener logsListener;
	private MainFrame mainframe;
	private Controller controller;
	private JButton signup;
	private SignUpDialog signupdialog;

	public LoginDialog() {

		super("Sign up");
		okbut = new JButton("OK");
		cancelbut = new JButton("Cancel");

		textField = new JTextField(10);
		password = new JPasswordField(10);
		mainframe = new MainFrame(this);
		controller = new Controller();

		signup = new JButton("click here to Sign Up");
		signupdialog = new SignUpDialog();

		// password.setEchoChar(*);

		layoutComponent();

		signup.setBorder(BorderFactory.createEmptyBorder());
		// signupbut.setContentAreaFilled(false);
		signup.setForeground(Color.BLUE);

		okbut.addActionListener(this);
		cancelbut.addActionListener(this);
		signup.addActionListener(this);

		setSize(400, 380);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		
		String userName = textField.getText();
        char[]  p= password.getPassword();
        String pwd= new String(p);
        
		if (e.getSource() == okbut) {
			mainframe.setVisible(true);
			if(logsListener != null) {
				logsListener.loginSet(userName, pwd);
		
			
			try {
				controller.connect();
				
				System.out.println("but");
//				;
//			new MainFrame(loginDialog);
				System.out.println("loaded");
				
				if (controller.loadlogdata()) {
					//dispose();
					controller.addUsers((ActionEventUser) e);
					//mainframe.setVisible(true);
				
				} else
					 JOptionPane.showMessageDialog(LoginDialog.this, "Wrong Username & Password");

				

			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(LoginDialog.this, "unable to save Data to Database.",
						"Database connection Problem", JOptionPane.ERROR_MESSAGE);
			}
			}
			try {
				controller.close();
			
				System.gc();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == cancelbut) {

			setVisible(false);
		}

		if (e.getSource() == signup) {
			signupdialog.setVisible(true);

		}
	}

	public void layoutComponent() {

		JPanel controPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		int space = 15;
		Border titleBorder = BorderFactory.createTitledBorder("Login Data");
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);

		controPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		controPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		Insets rightPadding = new Insets(0, 0, 0, 75);
		Insets noPadding = new Insets(0, 0, 0, 0);

////next row//

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weighty = 1;
		gc.weightx = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controPanel.add(new JLabel("User: "), gc);

		gc.anchor = GridBagConstraints.WEST;
		gc.gridx++;
		gc.insets = noPadding;
		controPanel.add(textField, gc);

		/// next row//
		gc.gridx = 0;
		gc.gridy++;
		gc.weighty = 1;
		gc.weightx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controPanel.add(new JLabel("Password: "), gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx++;
		gc.insets = noPadding;
		controPanel.add(password, gc);

		gc.gridx = 1;
		gc.gridy++;
		gc.weighty = 1;
		gc.weightx = 1;
		gc.insets = noPadding;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.WEST;

		controPanel.add(signup, gc);

		/// Button row///

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.add(okbut);
		buttonPanel.add(cancelbut);

		Dimension buttsize = cancelbut.getPreferredSize();
		okbut.setPreferredSize(buttsize);

		// Add sub panel to dialog///
		setLayout(new BorderLayout());
		add(controPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	public void setDefaults(String user, String passwort) {
		textField.setText(user);
		password.setText(passwort);

	}

	public void setlogsListener(LoginListener logsListener) {
		this.logsListener = logsListener;

	}


}
