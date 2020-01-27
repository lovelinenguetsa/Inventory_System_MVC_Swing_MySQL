import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import org.w3c.dom.events.Event;

import com.sun.glass.events.KeyEvent;

public class MainFrame extends JFrame {
	private TextPanel textpanel;
	private JButton btn;
	private Toolbar tb;
	private FormPanel fP;
	private JFileChooser filechooser;

	public MainFrame() {

		super("Hello");
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(600, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		textpanel = new TextPanel();
		btn = new JButton("click me");
		tb = new Toolbar();
		fP = new FormPanel();
		filechooser= new JFileChooser();
		tb.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				textpanel.appenText(text);

			}
		});

		fP.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empcat = e.getEmpCat();
				String taxId = e.getTaxId();
				boolean isUscitizen = e.isUsCitizen();
				String gender = e.getGender();

				textpanel.appenText(name + " : " + occupation + ":  " + ageCat + ": " + empcat + taxId + ". "
						+ isUscitizen + gender + "\n");
			}

			@Override
			public void handleEvent(Event evt) {
				// TODO Auto-generated method stub

			}
		});
		add(textpanel, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		add(tb, BorderLayout.NORTH);
		add(fP, BorderLayout.WEST);
		setJMenuBar(createMenuBar());

	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu windowMenu = new JMenu("Window");

		JMenu fileMenu = new JMenu("file");
		JMenuItem exportData = new JMenuItem("Export Data...");
		JMenuItem importData = new JMenuItem("Import Data...");
		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exportData);
		fileMenu.add(importData);
		fileMenu.add(exit);

		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		showFormItem.setSelected(true);

		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();

				fP.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exit.setMnemonic(KeyEvent.VK_X);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		importData.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (filechooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION) {
					System.out.println(filechooser.getSelectedFile());
				};
				
			}
		});
		
exportData.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (filechooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION) {
					System.out.println(filechooser.getSelectedFile());
				};
				
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				JOptionPane.showInputDialog(MainFrame.this, "Enter your User name",
				"Enter Username", JOptionPane.OK_OPTION | JOptionPane.WARNING_MESSAGE);
				int action= JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action== JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			

			}
		});

		menuBar.add(windowMenu);
		menuBar.add(fileMenu);
		return menuBar;
	}

}
