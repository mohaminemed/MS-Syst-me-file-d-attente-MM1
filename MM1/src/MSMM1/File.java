package MSMM1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class File extends JPanel{
	private int nb_piece;
		
	public File(int nb_piece) {
		this.nb_piece=nb_piece;
	}
	
	public void setNb_piece(int nb_piece) {
		this.nb_piece = nb_piece;
	}
	
	public int getNb_piece() {
		return nb_piece;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = this.getWidth()-50;
		for(int i=0; i<nb_piece;i++) {
			g.setColor(Color.blue);
			g.fillOval(x, 20, 40, 40);
			x=x-50;
		}
	}

}

