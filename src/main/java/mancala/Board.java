package mancala;

import java.applet.Applet;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//logic of a computer mancala game 

public class Board {

	private Cup[] cups;
	private int start;
	private int piecesWon;// by both combined
	private Players players;
	protected static final int GOAL1 = 6;
	protected static final int GOAL2 = 13;

	public Board(Players players) {
		this.players = players;
		piecesWon = 0;
		// create cups/goals
		cups = new Cup[14];
		for (int i = 0; i < cups.length; i++) {
			if (i == GOAL1 || i == GOAL2) {
				cups[i] = new Goal(647, 1010);
			} else {
				if (i < GOAL1) {
					cups[i] = new Cup(639, 1560);
				} else {
					cups[i] = new Cup(639, 1560);
				}
			}
			cups[i].setToolTipText(Integer.toString(getContent(i)));

		}
	}

	public void resetBoard() {
		for (int i = 0; i < cups.length; i++) {
			cups[i].reset();
		}
		// currentPlayer = player1;
		piecesWon = 0;
	}

	public boolean checkGame() {
		if (piecesWon == 48) {
			return true;
		} else {
			return false;
		}
	}

	public int calculateWinner() {

		if (getContent(GOAL1) > getContent(GOAL2)) {
			return 1;
		} else if (getContent(GOAL1) < getContent(GOAL2)) {
			return 2;
		}
		return 0;// no winner
	}

	public int getContent(int i) {
		return cups[i].getCount();
	}

	public boolean distribute(int startPosit) {
		start = startPosit;
		int amount = cups[start].removePieces();

		for (int i = 0, position = start + 1; i < amount; i++, position++) {
			if (position != GOAL1 && position != GOAL2) {
				cups[position].addPiece();
				//dropPieceSound();
			} else {
				if (currentPlayersGoal(position)) {
					piecesWon++;
					((Goal) cups[position]).addPiece();

				} else {
					i--;
				}
				// board[position].repaint();
			}

			start = position;
			if (position == cups.length - 1) {
				position = -1;// 0 after increment
			}
		} // pieces done being distributed

		return checkTurn();
	}

	

	private boolean currentPlayersGoal(int position) {
		return (position == GOAL1 && players.currentPlayerNum() == 1)
				|| (position == GOAL2 && players.currentPlayerNum() == 2);
	}

	// checks to see if landed in a goal our landed in an empty cup
	private boolean checkTurn() {
		int amount;
		if (getContent(start) == 1) {
			if (start > -1 && start < 6 && players.currentPlayerNum() == 1) {
				amount = cups[start].removePieces();
				amount = amount + cups[Math.abs(start - 12)].removePieces();
				System.out.println("amount is " + amount);
				piecesWon = piecesWon + amount;
				((Goal) cups[6]).addToGoal(amount);
			} else if (start > 6 && start < 13
					&& players.currentPlayerNum() == 2) {
				amount = cups[start].removePieces();
				amount = amount + cups[12 - start].removePieces();
				System.out.println("amount is " + amount);
				piecesWon = piecesWon + amount;
				((Goal) cups[13]).addToGoal(amount);
			}
		}
		// if ended by a goal returns true;
		if (start == GOAL1) {
			if (players.currentPlayerNum() == 1) {
				return true;
			}
		}
		if (start == GOAL2) {
			if (players.currentPlayerNum() == 2) {
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
			if (cups[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			for (int i = 7; i < 13; i++) {
				amount += cups[i].removePieces();
			}
			((Goal) cups[GOAL2]).addToGoal(amount);
			piecesWon += amount;
			return 2;
		} // currentPlayer is player2
		found = false;
		amount = 0;
		for (int i = 7; i < 13; i++) {
			if (cups[i].getCount() != 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			for (int i = 0; i < 6; i++) {
				amount += cups[i].removePieces();
			}
			((Goal) cups[GOAL1]).addToGoal(amount);
			piecesWon += amount;
			return 1;
		}
		return 0;
	}

	public Cup getCup(int cupNum) {
		return cups[cupNum];
	}

	public Goal getGoal(int cupNum) {
		if (cupNum == GOAL1 || cupNum == GOAL2) {
			return (Goal) cups[cupNum];
		}
		return null;

	}
}
