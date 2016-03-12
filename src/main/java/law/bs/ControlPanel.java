package law.bs;

import law.bs.Brain;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ControlPanel extends JPanel{
	
	public ControlPanel(final JPanel p) {
		JButton bt1 = new JButton("求解");
		this.add(bt1);
		bt1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//計算
				Brain brain = new Brain();
				brain.makeIt();
				//刷新主板
				p.repaint();
			}
		});
	}
}
