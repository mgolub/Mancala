package mancala;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CupComponent extends JComponent {

	private ArrayList<Piece> pieces;
	private int xCoordinate, yCoordinate;
	private String color;
	
	public CupComponent(int cupNumber, int colorVal) {
		pieces = new ArrayList<Piece>();
		switch(colorVal){
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
		}
		
		for(int i = 0; i < 4; i++){
			pieces.add(new Piece(color));
		}		
	}
	
	public void addPiece(Piece piece){
		pieces.add(piece);
	}
	
	public void emptyCup(){
		pieces.clear();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.gray);
		g.drawOval(1, 1, 30, 30);
		
		for(int i = 0; i < pieces.size(); i++){
			
		}
		
		repaint();
	}

}
