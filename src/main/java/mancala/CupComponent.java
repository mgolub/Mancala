package mancala;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CupComponent extends JComponent {

	private ArrayList<Piece> pieces;
	private int xCoordinate, yCoordinate;
	
	public CupComponent(int cupNumber, String color) {
		pieces = new ArrayList<Piece>();
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
		
		for(int i = 0; i < pieces.size(); i++){
			
		}
		
		repaint();
	}

}
