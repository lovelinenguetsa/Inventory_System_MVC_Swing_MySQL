package gui1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar implements ActionListener {

	private JButton savebut;
	private JButton refreshbut;
	

	private ToolListener strlist;

	public Toolbar() {
		setFloatable(false);

		setBorder(BorderFactory.createEtchedBorder());
		savebut = new JButton();
		savebut.setIcon(Utils.createIcon("/image/Save16.gif"));
		savebut.setToolTipText("save");
		refreshbut = new JButton();
		refreshbut.setIcon(Utils.createIcon("/image/Refresh16.gif"));
		refreshbut.setToolTipText("Refresh");
		savebut.addActionListener(this);
		refreshbut.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(savebut);
//		addSeparator();
		add(refreshbut);
	}
	

	public void setToolbarListener(ToolListener listener) {
		this.strlist = listener;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton click = (JButton) e.getSource();

		if (click == savebut) {
			if (strlist != null) {
				strlist.saveEventOccured();
			}
		} else
			strlist.refreshEventOccured();
	}
}
