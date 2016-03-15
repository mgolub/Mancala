package mancala;

//logic of a computer mancala game 
//preferred

public class Board2 {

	private Cup[] board;
	private int player1;
	private int player2;
	private int currentPlayer;
	private int start;
	private int peicesWon;// by both combined
	private boolean fin;

	public Board2(String name1, String name2) {
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
		peicesWon = 0;
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

	public boolean distribute(int startPosit) {
		start = startPosit;
		int amount = board[start].removePieces();
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
			// to continue with the beginning of the array
			start = position;
			if (position == 13) {
				position = -1;// 0 after increment
			}
		} // end for loop - fin distributing pieces

		if (board[start].getCount() == 1) {
			if (start > -1 && start < 6 && currentPlayer == player1) {
				amount = board[start].removePieces();
				amount += board[start + 7].removePieces();
				peicesWon += amount;
				((Goal) board[6]).addToGoal(amount);
			} else if (start > 6 && start < 13 && currentPlayer == player2) {
				amount = board[start].removePieces();
				amount += board[start - 7].removePieces();
				peicesWon += amount;
				((Goal) board[13]).addToGoal(amount);
			}
		}
		// if ended by a goal returns true;
		if (start == 6) {
			if (currentPlayer == player1) {
				return true;
			}
		}
		if (start == 13) {
			if (currentPlayer == player2) {
				return true;
			}
		}

		return false;
	}

	// add to pieces and make it return a boolean

	public void checkForMoves() {
		boolean found = false;
		int amount = 0;
		for (int i = 0; i < 6; i++) {
			if (board[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			for (int i = 7; i < 13; i++) {
				amount += board[i].removePieces();
			}
			((Goal) board[13]).addToGoal(amount);
			return;
		}

		found = true;
		amount = 0;
		for (int i = 7; i < 13; i++) {
			if (board[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			for (int i = 7; i < 13; i++) {
				amount += board[i].removePieces();
			}
			((Goal) board[6]).addToGoal(amount);
			return;
		}
	}

	// Rule - for the other version

	// If you run into your own store, deposit one piece in it.
	// If you run into your opponent's store, skip it.
	// If the last piece you drop is in your own store, you get a free turn.If
	// the last piece you drop is in an empty hole on your side, you capture
	// that piece and any pieces in the hole directly opposite.
	// Always place all captured pieces in your store.
	// The game ends when all six spaces on one side of the Mancala board are
	// empty.
	// The player who still has pieces on his side of the board when the game
	// ends captures all of those pieces.

	// ****will need to check every cell after every turn...

}