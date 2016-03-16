package mancala;

import javax.print.attribute.standard.RequestingUserName;

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
		System.out.println("switched players");
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

		// System.out.println(board[start].getCount());
		if (board[start].getCount() == 1) {
			if (start > -1 && start < 6 && currentPlayer == player1) {
				amount = board[start].removePieces();
				amount = amount + board[Math.abs(start - 12)].removePieces();
				System.out.println("amount is " + amount);
				peicesWon = peicesWon + amount;
				((Goal) board[6]).addToGoal(amount);
			} else if (start > 6 && start < 13 && currentPlayer == player2) {
				amount = board[start].removePieces();
				amount = amount + board[12 - start].removePieces();
				System.out.println("amount is " + amount);
				peicesWon = peicesWon + amount;
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

	// add to pieces and make it return the player peices added to

	public int checkForMoves() {
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
			peicesWon += amount;
			return 2;
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
			peicesWon += amount;
			return 1;
		}
		return 0;
	}

}
