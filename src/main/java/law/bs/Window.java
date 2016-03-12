package law.bs;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * ��������
 * @author ddk
 *
 */
public class Window {
	JFrame frame;
	JPanel mainPanel;
	JPanel conPanel;
	
	public Window(){
		frame = new JFrame();
		frame.setLayout(new GridLayout());
		mainPanel = new MainPanel();
		mainPanel.setBounds(0, 0, 800, 600);
		conPanel = new ControlPanel(mainPanel);
		conPanel.setBounds(0, 0, 200, 600);
		frame.add(mainPanel);
		frame.add(conPanel);
		//����frame
		frame.setTitle("test");
		frame.setVisible(true);
		frame.setBounds(0, 0, 1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
