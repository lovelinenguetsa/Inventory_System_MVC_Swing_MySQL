package gui1;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import javax.swing.JComponent;

public class FormPanel extends JPanel {

	private JLabel productLabel;
	private JLabel quantitylabel;
	private JLabel originPricelabel;
	private JLabel profitLabel;
	private JLabel sellingPriceLabel;
	private JLabel supplierLabel;
	private JLabel supContactLabel;

	private JTextField quantityField;
	private JFormattedTextField originPriceField;
	private JFormattedTextField profitfield;
	private JFormattedTextField sellingPriceField;
	private JFormattedTextField supContactField;

	private JButton okbut;
	private FormListener formlist;
	private JList<ProductCategory> productList;
	private JComboBox<String> supplierCombo;

	public FormPanel() {

		Dimension dim = getPreferredSize();
		dim.width = 260;
		setPreferredSize(dim);

		okbut = new JButton("Ok");
		okbut.setMnemonic(KeyEvent.VK_O);

		productLabel = new JLabel("Product Name: ");
		originPricelabel = new JLabel("Original Price: ");
		quantitylabel = new JLabel("Quantity: ");
		profitLabel = new JLabel("Profit: ");
		sellingPriceLabel = new JLabel("Selling Price: ");
		supplierLabel = new JLabel("Supplier: ");
		supContactLabel = new JLabel("Supplier Contact: ");

		productLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		productLabel.setLabelFor(productList);
		
		NumberFormat format= NumberFormat.getCurrencyInstance();

		quantityField = new JTextField(10);
		
		originPriceField = new JFormattedTextField(format);
		profitfield = new JFormattedTextField(format);
		sellingPriceField =new JFormattedTextField(format);
		profitfield.setValue(000000);
		originPriceField.setValue(000000);
		sellingPriceField.setValue(000000);
		
		originPriceField.setPreferredSize(new Dimension(110, 20));
		sellingPriceField.setPreferredSize(new Dimension(110, 20));
		profitfield.setPreferredSize(new Dimension(110, 20));
		
		

		supContactField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		supContactField.setPreferredSize(new Dimension(110, 20));

		productList = new JList<>();
		supplierCombo = new JComboBox<>();

		supplierLabel.setEnabled(false);
		supContactLabel.setEnabled(false);

		DefaultListModel<ProductCategory> productModel = new DefaultListModel<>();

		productModel.addElement(new ProductCategory(0, "Cercueil"));
		productModel.addElement(new ProductCategory(1, "Location"));
		productModel.addElement(new ProductCategory(2, "Decoration "));
		productList.setModel(productModel);

		productList.setPreferredSize(new Dimension(110, 80));
		productList.setBorder(BorderFactory.createEtchedBorder());
		productList.setSelectedIndex(1);

		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
		comboModel.addElement("Ml Distributor");
		comboModel.addElement("Smart Sarl");
		comboModel.addElement("Denver");

		supplierCombo.setModel(comboModel);
		supplierCombo.setSelectedIndex(0);
		supplierCombo.setEditable(true);
		supplierCombo.setPreferredSize(new Dimension(110, 25));

		okbut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = Integer.parseInt(quantityField.getText());

				String orpreis = originPriceField.getText();
				String sellpreis = sellingPriceField.getText();
				String profit = profitfield.getText();
				String contact = supContactField.getText();

				ProductCategory productCat = productList.getSelectedValue();
				String supplierCat = (String) supplierCombo.getSelectedItem();

				FormEvent event = new FormEvent(this, productCat.getId(), quantity, orpreis, profit, sellpreis,
						supplierCat, contact);
				if (formlist != null) {
					formlist.formEventOccurred(event);
				}

			}
		});

		Border innerBord = BorderFactory.createTitledBorder("add Product");

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
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(productLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(productList, gc);

//	next row/////
		gc.gridy++;
		gc.gridx = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(quantitylabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(quantityField, gc);

//	next row/////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(originPricelabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(originPriceField, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(sellingPriceLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(sellingPriceField, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(profitLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(profitfield, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(supplierLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(supplierCombo, gc);

		// next row/////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(supContactLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(supContactField, gc);

//next row/////

		gc.weightx = 1;
		gc.weighty = 0.5;

		gc.gridy++;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okbut, gc);

	}
}

class ProductCategory {

	@Override
	public String toString() {
		return text;
	}

	private String text;
	private int id;

	public ProductCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}
}
