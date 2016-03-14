package mancala;

import java.util.Stack;

public class Cup {

	// private Stack<Piece> pieces;
	protected int count;

	public Cup() {
		reset();
	}

	// public void addPiece(Piece piece) {
	public void addPiece() {
		// pieces.push(piece);
		count++;

	}

	public int getCount() {
		return count;
	}

	/*
	 * public Piece removePiece() { count--; return pieces.pop(); }
	 */

	public void removePieces() {
		count = 0;
	}

	public void reset() {
		count = 4;
		/*
		 * pieces.push(new Piece("fakeName")); pieces.push(new Piece("soIt"));
		 * pieces.push(new Piece("will")); pieces.push(new Piece("compile"));
		 */
	}
}
