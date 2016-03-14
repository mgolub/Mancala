package mancala;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GoalComponent extends JComponent{

	private ArrayList<Piece> pieces;
	
	public GoalComponent() {
		pieces = new ArrayList<Piece>();	
	}
	
	public void addPiece(Piece piece){
		pieces.add(piece);
	}
	
	public void emptyGoal(){
		pieces.clear();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.YELLOW);
		g.drawOval(1, 1, 20, 50);
		
		for(int i = 0; i < pieces.size(); i++){
			
		}
		
		repaint();
	}
}
