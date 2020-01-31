package gui1;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler1.Controller;

public class SignUpDialog extends JDialog implements ActionListener {
	private JLabel pwd, nameLabel, pwdlabel, confirmepwdlabel, birthdateLabel, genderLabel;
	private JTextField nameField;
	private JPasswordField pwdField, confpwdField;
	private JButton submitbtn, clearbtn;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	private JDatePanelImpl birtdatefield;
	private JDatePickerImpl datePicker;
	private Controller controller;
	private ToolBarListener strlist;
	private FormListener formlist;

	/**
	 * Create the frame.
	 */
	public SignUpDialog() {

		setSize(500, 500);
		setMinimumSize(new Dimension(500, 400));
		setTitle("Registration");

		pwd = new JLabel("Sign Up");
		pwd.setForeground(Color.blue);
		pwd.setFont(new Font("serif", Font.BOLD, 20));

		nameLabel = new JLabel("Name:");

		pwdlabel = new JLabel("Create Password:");
		confirmepwdlabel = new JLabel("Confirm password:");

		birthdateLabel = new JLabel("Birthdate:");
		genderLabel = new JLabel("Gender:");

		nameField = new JTextField(20);

		pwdField = new JPasswordField(20);
		confpwdField = new JPasswordField(20);
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		genderGroup = new ButtonGroup();

		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);

		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");

		UtilDateModel model = new UtilDateModel();
		birtdatefield = new JDatePanelImpl(model, new Properties());
		datePicker = new JDatePickerImpl(birtdatefield, new DateComponentFormatter());
		datePicker.setSize(new Dimension(10, 2));
		submitbtn = new JButton("Submit");
		clearbtn = new JButton("Cancel");
		controller = new Controller();

		submitbtn.addActionListener(this);
		clearbtn.addActionListener(this);

		layoutComponent();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitbtn) {
			
				
			int x = 0;
			String s1 = nameField.getText();
			

			char[] s3 = pwdField.getPassword();
			char[] s4 = confpwdField.getPassword();
			Date s5 =  (Date) datePicker.getModel().getValue();
			String s7 = genderGroup.getSelection().getActionCommand();
			
			ActionEventUser event= new ActionEventUser(this, s1, s3, s5, new String(s7));

			if (formlist != null) {
				//formlist.formEventOccurred(event);
			}

				try {
					controller.connect();
//					controller.createRegisterTable();
					controller.insertlogdata();
					System.out.println("submit");
//					if (controller.checkResulset()) {
//						JOptionPane.showMessageDialog(SignUpDialog.this, "name elready exist.",
//								"Database connection Problem", JOptionPane.ERROR_MESSAGE);
//					} else
//						JOptionPane.showMessageDialog(SignUpDialog.this, "Data succesfully added.","yes",
//								JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(SignUpDialog.this, "unable to insert Data to Database.",
							"Database connection Problem", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
				else JOptionPane.showMessageDialog(SignUpDialog.this, "Password and confirm Password not correspond");
				try {
					controller.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		

	if(e.getSource()==clearbtn)

	{
		setVisible(false);
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
		add(pwd, gc);

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
		add(birthdateLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(datePicker, gc);

//			next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(pwdlabel, gc);

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
		add(confirmepwdlabel, gc);

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
		add(maleRadio, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(femaleRadio, gc);

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
