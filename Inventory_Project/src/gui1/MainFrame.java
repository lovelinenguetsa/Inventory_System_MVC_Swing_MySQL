package gui1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.w3c.dom.events.Event;


import controler1.Controller;

public class MainFrame extends JFrame {

	
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private prefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitpane;
	private JTabbedPane tabbedpane;
	private TextPanel textpane;
	private MessagePanel messagepane;

	public MainFrame(JFrame parent) {
		
		super("Inventory System");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		prefsDialog = new prefsDialog(this);
		controller = new Controller();
		tabbedpane= new JTabbedPane();
		textpane= new TextPanel();
		messagepane= new MessagePanel();
		splitpane= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabbedpane);
		splitpane.setOneTouchExpandable(true);
		
		tabbedpane.addTab("Product Database", tablePanel);
		tabbedpane.addTab("Message", messagepane);
		prefs = Preferences.userRoot().node("db");

		tablePanel.setData(controller.getProduct());

		tablePanel.setPersonTableListener(new ProductAndUserListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});

		prefsDialog.setPrefsListener(new PrefsListener() {

			@Override
			public void preferenceSet(String user, String pwd, int port) {
				prefs.put("user", user);
				prefs.put("password", pwd);
				prefs.put("port", new Integer(port).toString());

			}

		});

		String user = prefs.get("user", "");
		String pwd = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);

		prefsDialog.setDefaults(user, pwd, port);
		fileChooser = new JFileChooser();

		fileChooser.addChoosableFileFilter(new ProductFileFilter());

		setJMenuBar(createMenuBar());
		
		

		toolbar.setToolbarListener(new ToolListener() {
			
			@Override
			public void saveEventOccured() {
				connect();
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "unable to save Data to Database.",
							"Database connection Problem", JOptionPane.ERROR_MESSAGE);	
				}
				
			}

			@Override
			public void refreshEventOccured() {
				connect();
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "unable to load Data to Database.",
							"Database connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				tablePanel.refresh();
			}

			@Override
			public void cancelEventOccured() {
				connect();
				try {
					controller.loadlogdata();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "unable to cancel Data to Database.",
							"Database connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				
			}

			@Override
			public void saveUserEventOccured() {
				connect();
				try {
					controller.savelogdata();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "unable to save Data to Database.",
							"Database connection Problem", JOptionPane.ERROR_MESSAGE);	
				}
				
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}

			@Override
			public void handleEvent(Event evt) {
				// TODO Auto-generated method stub

			}
		});
		
		addWindowListener( new WindowAdapter() {
		
			
			@Override
			public void windowClosing (WindowEvent e) {
				try {
					controller.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				System.gc();
				}
			
			
			
		});
		
		//setVisible(true);
	
		add(toolbar, BorderLayout.PAGE_START);
		add(splitpane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(600, 500));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(parent);
	}
	
	public void connect() {
		try {
			controller.connect();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to Database.",
					"Database connection Problem", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Product Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		prefsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);

			}
		});

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
if (menuItem.isSelected()) {
	splitpane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
}
				formPanel.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load Data from File", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					;

				}
			}
		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Sorry , cannot save Data to File", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					 WindowListener[] listeners= getWindowListeners();
					 
					 for (WindowListener windowListener : listeners) {
						windowListener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}
			}
		});

		return menuBar;
	}
}