import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	
	private JButton helloBut;
	private JButton byeBut;
	
	private  StringListener strlist;
	
	
public Toolbar() {
	
	setBorder(BorderFactory.createEtchedBorder());
helloBut = new JButton("Hello");
byeBut = new JButton("Goodbye");
helloBut.addActionListener(this);
byeBut.addActionListener(this);

setLayout(new FlowLayout(FlowLayout.LEFT));



add(helloBut);
add(byeBut);
}
public void setStringListener(StringListener listener) {
	this.strlist= listener;
	
}
@Override
public void actionPerformed(ActionEvent e) {
	JButton click= (JButton) e.getSource();
	
	if (click==helloBut) {
		if (strlist != null) {
			strlist.textEmitted("GoodBye\n");
		}
	}else strlist.textEmitted("hello\n");
}
}
