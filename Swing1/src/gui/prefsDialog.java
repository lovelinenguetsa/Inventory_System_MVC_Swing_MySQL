package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
	setLayout(new GridBagLayout());
	
	GridBagConstraints gc= new GridBagConstraints();
	
////next row//
	
	gc.gridx= 0;
	gc.gridy=0;
	gc.weighty= 1;
	gc.weightx=1;
	gc.fill = GridBagConstraints.NONE;
	
	add( new JLabel("User: "), gc);
	
	gc.gridx++;
	add(textField, gc);
	
	///next row//
	gc.gridx= 0;
	gc.gridy++;
	gc.weighty= 1;
	gc.weightx=1;
	gc.fill = GridBagConstraints.NONE;
	
	add( new JLabel("Password: "), gc);
	
	gc.gridx++;
	add(password, gc);
	
	gc.gridx= 0;
	gc.gridy++;
	gc.weighty= 1;
	gc.weightx= 1;
	gc.fill = GridBagConstraints.NONE;
	
	add( new JLabel("Port: "), gc);
	
	gc.gridx++;
	add(portSpinner, gc);
	
	
	/// next row///
	
	gc.gridy++;
	
	gc.gridx= 0;
	add(okbut, gc);
	
	gc.gridx++;
	
	add(cancelbut, gc);
	
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
	
	setSize(400, 300);
	setLocationRelativeTo(parent);
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
