package gui1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

	private JButton savebut;
	private JButton refreshbut;
	

	private ToolBarListener strlist;

	public Toolbar() {

		setBorder(BorderFactory.createEtchedBorder());
		savebut = new JButton("Save");
		refreshbut = new JButton("Refresh");
		savebut.addActionListener(this);
		refreshbut.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(savebut);
		add(refreshbut);
	}

	public void setToolbarListener(ToolBarListener listener) {
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
