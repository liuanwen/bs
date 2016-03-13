package law.bs;

import law.bs.aco.Aco;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ControlPanel extends JPanel{
	
	public ControlPanel(final JPanel p) {
		JButton bt1 = new JButton("求解");
		this.add(bt1);
		bt1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//計算
//				Brain brain = new Brain();
//				brain.makeIt();
				Aco aco = new Aco();
				aco.makeIt();
				//刷新主板
				p.repaint();
			}
		});
	}
}
