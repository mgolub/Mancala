package mancala;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel cupPanel1, cupPanel2, cupsPanel, goalPanel1, goalPanel2;
	private Board board;
	private Players players;
	private int winner;
	private String turnDescription;
	private boolean mouseEnabled;

	public BoardPanel(Players players) {
		this.players = players;
		this.setLayout(new BorderLayout());
		createComponents();
		formatComponents();
		addComponents();
		this.changeDescription(1);
		this.mouseEnabled = true;

	}

	// called by action listener
	public void turn(int index) {

			boolean goalTurn = board.distribute(index);

			repaint();

			int piecesAdded = board.checkForMoves();
			if (piecesAdded != 0) {
				JOptionPane.showMessageDialog(null,
						"Left over peices added to " + players.playersName(piecesAdded) + "'s goal!!");
				repaint();
			}
			if (board.checkGame()) {
				winner = board.calculateWinner();
				switch (winner) {
				case 0:
					changeDescription(4);
					break;
				case 1:
					players.increaseWins(1);
					displayWinner();
					break;
				case 2:
					players.increaseWins(2);
					displayWinner();
					break;
				}
				repaint();
				return;

			}
			if (goalTurn) {
				changeDescription(3);
			} else if (!goalTurn) {
				
				players.switchPlayers();
		
				if (players.getCurrentPlayer()==1) {
					mouseEnabled = false;
				}
			}
		
		changeDescription(1);
		disableCups();
	
	}

	private void disableCups() {
		for (int i = 0; i < 13; i++) {
			if (!(i == Board.GOAL1) || (i == Board.GOAL2)) {
				if (board.getCup(i).isEnabled()) {
					board.getCup(i).setEnabled(false);
				} else {
					board.getCup(i).setEnabled(true);
				}
			}
		}

	}

	private void createComponents() {
		cupsPanel = new JPanel();
		cupsPanel.setLayout(new BorderLayout());
		cupPanel1 = new JPanel(new FlowLayout());
		cupPanel2 = new JPanel(new FlowLayout());
		goalPanel1 = new JPanel(new GridBagLayout());
		goalPanel2 = new JPanel(new GridBagLayout());
		board = new Board(players);
		addCups();

	}

	private void addCups() {
		for (int i = 0; i < Board.GOAL1; i++) {

			cupPanel1.add(board.getCup(i));

		}
		for (int i = 12; i >= 7; i--) {

			cupPanel2.add(board.getCup(i));
		}

	}

	private void formatComponents() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		Dimension cupPanelDimension = new Dimension(700, 20);
		cupsPanel.setOpaque(false);
		cupPanel1.setOpaque(false);
		cupPanel2.setOpaque(false);
		cupPanel1.setPreferredSize(cupPanelDimension);
		cupPanel2.setPreferredSize(cupPanelDimension);
		goalPanel1.setOpaque(false);
		goalPanel2.setOpaque(false);
		goalPanel2.setPreferredSize(new Dimension(159, 700));
		goalPanel1.setPreferredSize(new Dimension(125, 700));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		BoardImgPanel imgPanel = new BoardImgPanel();
		g.drawImage(imgPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	private void addComponents() {

		addSpaceHolder(cupsPanel, 200, 190);
		cupsPanel.add(cupPanel2, BorderLayout.SOUTH);
		cupsPanel.add(cupPanel1, BorderLayout.CENTER);
		addSpaceHolder(cupsPanel, 200, 10);
		goalPanel1.add(board.getGoal(Board.GOAL1));
		goalPanel2.add(board.getGoal(Board.GOAL2));
		JPanel west = new JPanel();
		west.setLayout(new FlowLayout());
		west.setPreferredSize(new Dimension(159, 50));
		west.setOpaque(false);
		addSpaceHolder(west, 159, 200);
		west.add(goalPanel2);
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(125, 50));

		east.setOpaque(false);
		addSpaceHolder(east, 125, 200);
		east.add(goalPanel1);
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(cupsPanel, BorderLayout.CENTER);
	}

	private void addSpaceHolder(JPanel panel, int width, int height) {
		JPanel spaceHolder = new JPanel();
		spaceHolder.setPreferredSize(new Dimension(width, height));
		spaceHolder.setOpaque(false);
		panel.add(spaceHolder);

	}

	public int getQtyMarbles(int cup) {
		return board.getContent(cup);
	}

	public boolean distributePieces(int startCup) {
		return board.distribute(startCup);
	}

	public int checkForMoves() {
		return board.checkForMoves();
	}

	public boolean checkGame() {
		return board.checkGame();
	}

	public int calculateWinner() {
		return board.calculateWinner();
	}

	public void resetCups() {
		for (int i = 0; i < 14; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {
				board.getCup(i).setCount(board.getContent(i));
			} else {
				board.getGoal(i).setCount(board.getContent(i));
			}
		}
		for (int i = 0; i < 14; i++) {
			board.getCup(i).setToolTipText(Integer.toString(board.getContent(i)));
		}
		repaint();
	}

	public void resetBoard() {
		board.resetBoard();
		changeDescription(1);

		repaint();
		for (int i = 0; i < 6; i++) {
			board.getCup(i).setEnabled(true);
		}
		for (int i = 7; i < 13; i++) {
			board.getCup(i).setEnabled(false);
		}
	}

	public int getWinner() {
		return winner;
	}

	public void changeDescription(int code) {
		switch (code) {
		case 1:
			turnDescription = players.currentPlayersName() + "'s Turn...";
			break;
		case 2:
			turnDescription = "**** GREAT JOB " + players.playersName(winner) + "!!! ****";
			break;
		case 3:
			turnDescription = players.currentPlayersName() + " landed in the goal - player goes again";
			break;
		case 4:
			turnDescription = "Tie Game - no one wins!";
		}
		resetCups();
	}

	public void displayWinner() {
		changeDescription(2);
		JOptionPane.showMessageDialog(null,
				players.playersName(1) + ": " + board.getContent(6) + " points\n" + players.playersName(2) + ": "
						+ board.getContent(13) + " points\n\n" + players.playersName(winner) + " won!!");
		// statsLabel1.setText(players.playersName(1) + " Wins: " +
		// players.gamesWon(1));
		// statsLabel2.setText(players.playersName(2) + " Wins: " +
		// players.gamesWon(2));
	}

	public String description() {
		return this.turnDescription;
	}

	public Cup getCup(int cupNum) {
		return board.getCup(cupNum);
	}

	public boolean isMouseEnabled() {
		return this.mouseEnabled;
	}
	
	public void setMouseEnabled(boolean isEnabled){
		this.mouseEnabled = isEnabled;
	}

	public int computerAI() {

		ArrayList<Cup> cups = new ArrayList();
		for (int i = 0; i < board.getCups().length; i++) {
			cups.add(board.getCups()[i]);
		}
		List<Cup> computerCups = cups.subList(cups.indexOf(cups.get(1)), cups.indexOf(cups.get(7)));
		System.out.println(computerCups);
		System.out.println(computerCups.size());
		ComputerAI ai = new ComputerAI(computerCups);
		int move = ai.calculateBestMove();
		System.out.println(move);
		return move;

	}
}