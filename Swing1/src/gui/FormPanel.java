package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class FormPanel extends JPanel {

	private JLabel label1;
	private JLabel occupation;
	private JTextField nameField;
	private JTextField occupatiofield;
	private JButton okbut;
	private FormListener formlist;
	private JList<AgeCategory> ageList;
	private JComboBox<String> empCombo;
	private JCheckBox citizenCheck;
	private JTextField textField;
	private JLabel taxLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;

	public FormPanel() {

		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		okbut = new JButton("Ok");
		okbut.setMnemonic(KeyEvent.VK_O);

		label1 = new JLabel("Name: ");
		
		label1.setDisplayedMnemonic(KeyEvent.VK_N);
		label1.setLabelFor(nameField);
		
		occupation = new JLabel("Occupation: ");
		occupatiofield = new JTextField(10);
		nameField = new JTextField(10);
		ageList = new JList<>();
		empCombo = new JComboBox<>();
		citizenCheck = new JCheckBox();
		textField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		genderGroup = new ButtonGroup();
		

		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);
		
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");

		taxLabel.setEnabled(false);
		textField.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isticked = citizenCheck.isSelected();
				taxLabel.setEnabled(isticked);
				textField.setEnabled(isticked);

			}
		});

		DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();

		ageModel.addElement(new AgeCategory(0, "under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over "));
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
		comboModel.addElement("employed");
		comboModel.addElement("self-employed");
		comboModel.addElement("unemployed");
		empCombo.setModel(comboModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);
		
		okbut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupatiofield.getText();
				AgeCategory ageCat = ageList.getSelectedValue();
				String empCat = (String) empCombo.getSelectedItem();
				String taxId = textField.getText();
				boolean isUsCitizen = citizenCheck.isSelected();
				String gendercommand= genderGroup.getSelection().getActionCommand();

				

				FormEvent event = new FormEvent(this, name, occupation, ageCat.getId(), empCat, taxId, isUsCitizen, gendercommand);
				if (formlist != null) {
					formlist.formEventOccurred(event);
				}

			}
		});
		
		Border innerBord = BorderFactory.createTitledBorder("add Person");

		Border outerBord = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBord, innerBord));


		layoutComponent();

	}

	public void setFormListener(FormListener formListener) {
		this.formlist = formListener;

	}

	public void layoutComponent() {

		
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

//	first row/////
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(label1, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

//	next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupation, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupatiofield, gc);

//	next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age: "), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employment: "), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCombo, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("US Citizen: "), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("taxLabel: "), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(textField, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridy++;

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);

//next row/////

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridy++;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okbut, gc);

	}
}

class AgeCategory {

	@Override
	public String toString() {
		return text;
	}

	private String text;
	private int id;

	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}
}
