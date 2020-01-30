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

public class LoginDialog extends JFrame {
	private JButton okbut;
	private JButton cancelbut;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField textField;
	private JPasswordField password;
	private LoginListener logsListener;
	private MainFrame mainframe;
	private Controller controller;
	private SignUpDialog  signup;

	
	
public LoginDialog() {
	
	super("Sign up");
	okbut= new JButton("OK");
	cancelbut= new JButton("Cancel");
	
	
	textField= new JTextField(10);
	password= new JPasswordField(10);
	mainframe= new MainFrame(this);
	controller= new Controller();
	
	signup= new SignUpDialog();
	
	
	//password.setEchoChar(*);
	
	 layoutComponent();

	okbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		
		try {
			controller.connect();
			controller.loadlogdata();
			mainframe.setVisible(true);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(LoginDialog.this, "unable to save Data to Database.",
					"Database connection Problem", JOptionPane.ERROR_MESSAGE);	
		}
		
			
		}
	});
	
cancelbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			setVisible(false);
		}
	});
	
	setSize(400, 400);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

private JMenu createSignUpMenu() {
	JMenu signupMenu = new JMenu("click here to Sign Up");
	signupMenu.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			signup.setVisible(true);
			
		}
	});
	return signupMenu;
	
}

public void layoutComponent() {
	
	JPanel controPanel= new JPanel();
	JPanel buttonPanel= new JPanel();
	int space = 15;
	Border titleBorder= BorderFactory.createTitledBorder("Login Data");
	Border spaceBorder= BorderFactory.createEmptyBorder(space, space, space, space);
	
	controPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
	buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	controPanel.setLayout(new GridBagLayout());
	
	GridBagConstraints gc= new GridBagConstraints();
	
	Insets rightPadding=  new Insets(0, 0, 0, 75);
	Insets noPadding= new Insets(0, 0, 0, 0);
	
////next row//
	
	gc.gridx= 0;
	gc.gridy=0;
	gc.weighty= 1;
	gc.weightx=1;
	gc.fill = GridBagConstraints.NONE;
	
	gc.anchor= GridBagConstraints.EAST;
	gc.insets= rightPadding;
	controPanel.add( new JLabel("User: "), gc);
	
	gc.anchor= GridBagConstraints.WEST;
	gc.gridx++;
	gc.insets=noPadding;
	controPanel.add(textField, gc);
	
	///next row//
	gc.gridx= 0;
	gc.gridy++;
	gc.weighty= 1;
	gc.weightx=1;
	gc.fill = GridBagConstraints.NONE;
	gc.anchor= GridBagConstraints.EAST;
	gc.insets= rightPadding;
	controPanel.add( new JLabel("Password: "), gc);
	gc.anchor= GridBagConstraints.WEST;
	gc.gridx++;
	gc.insets=noPadding;
	controPanel.add(password, gc);
	
	gc.gridx= 1;
	gc.gridy++;
	gc.weighty= 1;
	gc.weightx= 1;
	gc.fill = GridBagConstraints.CENTER;
	gc.anchor= GridBagConstraints.CENTER;
	
	controPanel.add( createSignUpMenu(), gc);
	
	
	
	/// Button row///
	
	buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
	
	buttonPanel.add(okbut);
	buttonPanel.add(cancelbut);
	
	Dimension buttsize= cancelbut.getPreferredSize();
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
	this.logsListener= logsListener;
	
}
}
