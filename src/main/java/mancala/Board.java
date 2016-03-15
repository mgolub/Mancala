package mancala;

public class Board {

	private Cup[] board;
	private int player1;
	private int player2;
	private int currentPlayer;
	private int start;
	private int peicesWon;// by both combined
	private boolean fin;

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

	public boolean distribute(int startPosit) {
		start = startPosit;
		int amount = board[start].getCount();
		board[start].removePieces();
		// for loop tracks the amount of peices to distrib. and the cup to put
		// into
		for (int i = 0, position = start + 1; i < amount; i++, position++) {
			// Piece piece = board[start].removePiece();

			// put in goal if its the correct player
			// otherwise it decrements the i to skip and continues with the for
			// loop
			if (position == 6) {
				if (currentPlayer == player1) {
					peicesWon++;
					board[position].addPiece();
				} else {
					i--;
				}
			} else if (position == 13) {
				if (currentPlayer == player2) {
					peicesWon++;
					board[position].addPiece();
				} else {
					i--;
				}
				// if not a goal it puts the piece in
			} else {
				board[position].addPiece();
			}

			// save final position
			this.start = position;
			// to continue with the beginning of the array
			if (position == 13) {
				position = -1;// 0 after increment
			}
		} // end for loop - fin distributing pieces

		// if ended by a goal returns true;
		if (board[start].getCount() != 0 && (start != 6 && start != 13)) {
			distribute(start);
		}
		if (start == 6) {
			if (currentPlayer == 1) {
				fin = true;
				return true;
			}
		}
		if (start == 13) {
			if (currentPlayer == 2) {
				fin = true;
				return true;
			}
		}

		return false;

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
