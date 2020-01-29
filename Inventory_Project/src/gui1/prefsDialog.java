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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class prefsDialog extends JDialog {
	
	private JButton okbut;
	private JButton cancelbut;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField textField;
	private JPasswordField password;
	private PrefsListener prefsListener;
	
	
public prefsDialog(JFrame parent) {
	super(parent, "Preferences", false);
	okbut= new JButton("OK");
	cancelbut= new JButton("Cancel");
	spinnerModel= new SpinnerNumberModel(3306, 0, 9999, 1);
	portSpinner= new JSpinner(spinnerModel);
	textField= new JTextField(10);
	password= new JPasswordField(10);
	
	//password.setEchoChar(*);
	
	 layoutComponent();

	okbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Integer port= (Integer) portSpinner.getValue();
			
			String user= textField.getText();
			char[] pwd= password.getPassword();
			
			
			if (prefsListener!= null) {
				prefsListener.preferenceSet(user, new String(pwd), port);
			}
			setVisible(false);
		}
	});
	
cancelbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			setVisible(false);
		}
	});
	
	setSize(270, 200);
	setLocationRelativeTo(parent);
}

public void layoutComponent() {
	
	JPanel controPanel= new JPanel();
	JPanel buttonPanel= new JPanel();
	int space = 15;
	Border titleBorder= BorderFactory.createTitledBorder("Database Preferences");
	Border spaceBorder= BorderFactory.createEmptyBorder(space, space, space, space);
	
	controPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
	buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	controPanel.setLayout(new GridBagLayout());
	
	GridBagConstraints gc= new GridBagConstraints();
	
	Insets rightPadding=  new Insets(0, 0, 0, 15);
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
	
	gc.gridx= 0;
	gc.gridy++;
	gc.weighty= 1;
	gc.weightx= 1;
	gc.fill = GridBagConstraints.NONE;
	gc.anchor= GridBagConstraints.EAST;
	gc.insets= rightPadding;
	controPanel.add( new JLabel("Port: "), gc);
	
	gc.gridx++;
	gc.anchor= GridBagConstraints.WEST;
	gc.insets=noPadding;
	controPanel.add(portSpinner, gc);
	
	
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

public void setDefaults(String user, String passwort, int port) {
	textField.setText(user);
	password.setText(passwort);
	portSpinner.setValue(port);
}

public void setPrefsListener(PrefsListener prefsListener) {
	this.prefsListener= prefsListener;
	
}
}
