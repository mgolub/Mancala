package mancala;

//logic of a computer mancala game 
//preferred

public class Board {

	private Cup[] board;
	private int player1;
	private int player2;
	private int currentPlayer;
	private int start;
	private int piecesWon;// by both combined
	private final int GOAL1 = 6;
	private final int GOAL2 = 13;

	public Board() {
		board = new Cup[14];
		player1 = 1;
		player2 = 2;
		currentPlayer = player1;
		piecesWon = 0;

		for (int i = 0; i < board.length; i++) {
			if (i == GOAL1 || i == GOAL2) {
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
		piecesWon = 0;
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
		if (piecesWon == 48) {
			return true;
		} else {
			return false;
		}
	}

	public int calculateWinner() {
		if (board[GOAL1].getCount() > board[GOAL2].getCount()) {
			return 1;
		} else if (board[GOAL1].getCount() < board[GOAL2].getCount()) {
			return 2;
		}
		return 0;// no winner
	}

	public int getContent(int i) {
		return board[i].getCount();
	}

	public boolean distribute(int startPosit) {
		start = startPosit;
		int amount = board[start].removePieces();

		for (int i = 0, position = start + 1; i < amount; i++, position++) {
			if (position != GOAL1 && position != GOAL2) {
				board[position].addPiece();
			} else {
				if (currentPlayersGoal(position)) {
					piecesWon++;
					board[position].addPiece();
				} else {
					i--;
				}

			}

			start = position;
			if (position == board.length-1) {
				position = -1;// 0 after increment
			}
		} // pieces done being distributed
		return checkTurn();
	}

	private boolean currentPlayersGoal(int position) {
		return (position == GOAL1 && currentPlayer == player1)
				|| (position == GOAL2 && currentPlayer == player2);
	}

	// checks to see if landed in a goal our landed in an empty cup
	private boolean checkTurn() {
		int amount;
		if (board[start].getCount() == 1) {
			if (start > -1 && start < 6 && currentPlayer == player1) {
				amount = board[start].removePieces();
				amount = amount + board[Math.abs(start - 12)].removePieces();
				System.out.println("amount is " + amount);
				piecesWon = piecesWon + amount;
				((Goal) board[6]).addToGoal(amount);
			} else if (start > 6 && start < 13 && currentPlayer == player2) {
				amount = board[start].removePieces();
				amount = amount + board[12 - start].removePieces();
				System.out.println("amount is " + amount);
				piecesWon = piecesWon + amount;
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

	// add to piecesWon and make it return the player pieces added to
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
			piecesWon += amount;
			return 2;
		} // currentPlayer is player2
		found = false;
		amount = 0;
		for (int i = 7; i < 13; i++) {
			if (board[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			for (int i = 0; i < 6; i++) {
				amount += board[i].removePieces();
			}
			((Goal) board[6]).addToGoal(amount);
			piecesWon += amount;
			return 1;
		}
		return 0;
	}
}
