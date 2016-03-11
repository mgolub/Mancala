package mancala;

public class Board {

	private Cup[] board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private int peicesWon;// by both combined

	public Board(String name1, String name2) {
		board = new Cup[14];
		player1 = new Player(name1);
		player2 = new Player(name2);
		peicesWon = 0;
		for (int i = 0; i < board.length; i++) {
			board[i] = new Cup();
		}
	}

	public void resetBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i].reset();
		}
	}

	public void distribute(int start, int player) {
		int amount = board[start].getCount();

		for (int i = 0, position = start + 1; i < amount; i++, position++) {
			Piece piece = board[start].removePiece();
			if (position == 6) {
				if (currentPlayer == player1) {
					peicesWon++;
					board[position].addPiece(piece);
				}
			} else if (position == 13) {
				if (currentPlayer == player2) {
					peicesWon++;
					board[position].addPiece(piece);
				}
			} else {
				board[position].addPiece(piece);
			}
			if (position == 15) {
				position = -1;// 0 after increment
			}
		}

	}

	public void switchPlayer(int player) {
		if (player == 0) {
			currentPlayer = player1;
		} else {
			currentPlayer = player2;
		}
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
			player1.addWin();
			return 0;
		} else {
			player2.addWin();
			return 1;
		}
	}
}
