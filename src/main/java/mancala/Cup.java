package mancala;

import java.util.Stack;

public class Cup {
	
	private Stack<Piece> pieces;
	private int count;
	
	
	public Cup(){
		//every cup starts off with 4 pieces
		pieces.push(new Piece());
		pieces.push(new Piece());
		pieces.push(new Piece());
		pieces.push(new Piece());
		
		count = 4;
		
	}
	
	public void addPeice(Piece piece){
		pieces.push(piece);
		count++;
		
	}
	
	public int getCount(){
		return count;
	}
	
	public Piece removePiece(){
		return pieces.pop();
	}
	
	
}
