package law.bs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class MainPanel extends JPanel {
	
	public MainPanel() {
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(Data.n >= Data.max)
					return;
				Data.pos[Data.n][0] = e.getX();
				Data.pos[Data.n++][1] = e.getY();
				repaint();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<Data.n;i++){
			g.setColor(Color.black);
			g.fillOval(Data.pos[i][0]-5, Data.pos[i][1]-5, 10, 10);
		}
		
		if(Data.path[0] != -1){
			g.setColor(Color.red);
			int i=0;
			while(i<Data.n && Data.path[i] != -1){
				if(i+1<Data.n && Data.path[i+1] != -1){
					int x1 = Data.pos[Data.path[i]][0];
					int y1 = Data.pos[Data.path[i]][1];
					int x2 = Data.pos[Data.path[i+1]][0];
					int y2 = Data.pos[Data.path[i+1]][1];
					g.drawLine(x1, y1, x2, y2);
				}
				i++;
			}
		}
	}
}
