package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CupComponent extends JComponent {

	private ArrayList<Piece> pieces;
	private int xCoordinate, yCoordinate;
	private String color;
	private Image piece;
	private int count;
	
	public CupComponent(int cupIndex, int colorVal) {
		pieces = new ArrayList<Piece>();		
		setLayout(new GridLayout(4, 4));
		setPreferredSize(new Dimension(110, 280));
		piece =  new ImageIcon(getClass().getResource("/red.png")).getImage();
		count = 4;
		
		/*switch(colorVal){
		case 0:
			color = "red";
			break;
		case 1:
			color = "orange";
			break;
		case 2:
			color = "green";
			break;
		case 3:
			color = "dark blue";
			break;
		case 4:
			color = "light blue";
			break;
		case 5:
			color = "purple";
			break;
		}*/
				
			
	}

	public void setCount(int count){
		this.count = count;
		repaint();
	}
	
	public void emptyCup(){
		count = 0;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.black);
		g.fillOval(0, 0, 100, 270);
		
		for(int i = 0, j = -8; i < count; i++, j++){
			if(i < 8){
				g.drawImage(piece, 10, i*20, this);
			} else{
				g.drawImage(piece, 30, j*20, this);

			}
		}

		super.repaint();
	}

}
