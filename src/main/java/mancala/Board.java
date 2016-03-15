package mancala;

public class Board {

	private Cup[] board;
	private int player1;
	private int player2;
	private int currentPlayer;
	private int peicesWon;// by both combined

	public Board(String name1, String name2) {
		board = new Cup[14];
		// player1 = new Player(name1);
		// player2 = new Player(name2);
		player1 = 1;
		player2 = 2;
		currentPlayer = player1;

		peicesWon = 0;
		for (int i = 0; i < board.length; i++) {

			if (i == 6 || i == 13) {
				board[i] = new Goal();
			} else {
				board[i] = new Cup();
			}
		}
	}

	public void resetBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i].reset();
		}
		currentPlayer = player1;
	}

	public void distribute(int start) {
		int amount = board[start].getCount();
		board[start].removePieces();
		for (int i = 0, position = start + 1; i < amount; i++, position++) {
			// Piece piece = board[start].removePiece();
			if (position == 6) {
				if (currentPlayer == player1) {
					peicesWon++;
					board[position].addPiece();
				}
			} else if (position == 13) {
				if (currentPlayer == player2) {
					peicesWon++;
					board[position].addPiece();
				}
			} else {
				board[position].addPiece();
			}
			if (position == 13) {
				position = -1;// 0 after increment
			}
			
		}

	}

	public int switchPlayer() {
		if (currentPlayer == player2) {
			currentPlayer = player1;
		} else {
			currentPlayer = player2;
		}
		return currentPlayer;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean checkGame() {
		if (peicesWon == 48) {
			return true;
		} else {
			return false;
		}
	}

	public int calculateWinner() {
		if (board[7].getCount() > board[13].getCount()) {
			return 1;
		} else {
			return 2;
		}
	}

	public int getContent(int i) {
		return board[i].getCount();
	}
}
