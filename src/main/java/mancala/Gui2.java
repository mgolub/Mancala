package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gui2 extends JFrame {

	private JPanel options, game, stats;
	private JButton newGame, rules;
	private JLabel player1, player2, stats1, stats2, currentTurn, description;
	private String player1Name, player2Name;
	private int currentPlayer;
	private int wins1, wins2, winner;
	private JPanel cupsPanel, cupPanel1, cupPanel2, goalPanel1, goalPanel2;
	private JLabel[] cups;
	// private JLabel goal1, goal2;
	private Board2 board;

	public Gui2(String name1, String name2) {
		setTitle("Mancala");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		player1Name = name1;
		player2Name = name2;

		currentPlayer = 1;
		options = new JPanel();
		newGame = new JButton("New Game");
		rules = new JButton("Rules");
		board = new Board2(name1, name2);

		game = new JPanel(new BorderLayout());
		cupsPanel = new JPanel();
		cupPanel1 = new JPanel();
		cupPanel2 = new JPanel();
		goalPanel1 = new JPanel();
		goalPanel2 = new JPanel();
		stats = new JPanel(new FlowLayout());
		player1 = new JLabel("Player1: " + player1Name);
		player2 = new JLabel("Player2: " + player2Name);
		stats1 = new JLabel(getPlayerName(1) + " Wins: " + wins1);
		stats2 = new JLabel(getPlayerName(2) + " Wins: " + wins2);
		description = new JLabel();
		currentTurn = new JLabel("Current Player: " + player1Name);
		wins1 = 0;
		wins2 = 0;

		cups = new JLabel[14];
		// goal1 = new JLabel();
		// goal2 = new JLabel();
		add();
		format();
		resetNumbers();
		addActionListeners();
		changeDescription(1);
	}

	public void format() {
		game.setBackground(new Color(156, 93, 82));
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		cupPanel2.setLayout(new FlowLayout());
		cupPanel2.setBackground(Color.yellow);
		cupPanel1.setLayout(new FlowLayout());
		cupPanel1.setBackground(Color.magenta);
		goalPanel1.setBackground(Color.green);
		goalPanel2.setBackground(Color.green);
		goalPanel1.setLayout(new BoxLayout(goalPanel1, BoxLayout.Y_AXIS));
		goalPanel2.setLayout(new BoxLayout(goalPanel2, BoxLayout.Y_AXIS));

		for (int i = 0; i < cups.length; i++) {
			cups[i].setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 90));
			cups[i].setVerticalAlignment(JLabel.CENTER);

			if (i != 6 && i != 13) {
				cups[i].putClientProperty("index", i);
				cups[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						JLabel label = (JLabel) event.getSource();
						int index = (Integer) label.getClientProperty("index");
						if (!label.isEnabled() || board.getContent(index) == 0) {
							return;
						}
						turn(index);
					}
				});
			}
		}
	}

	public void add() {
		add(options, BorderLayout.NORTH);
		options.add(newGame);
		options.add(rules);

		add(game, BorderLayout.CENTER);
		game.add(cupsPanel, BorderLayout.CENTER);
		game.add(goalPanel1, BorderLayout.EAST);
		game.add(goalPanel2, BorderLayout.WEST);
		cupsPanel.add(cupPanel2);
		cupsPanel.add(cupPanel1);

		cupPanel1.add(Box.createRigidArea(new Dimension(1, 200)));
		cupPanel2.add(Box.createRigidArea(new Dimension(1, 200)));
		for (int i = 0; i < 6; i++) {
			cups[i] = new JLabel();
			cupPanel1.add(cups[i]);
		}
		for (int i = 12; i > 6; i--) {
			cups[i] = new JLabel();
			cupPanel2.add(cups[i]);
			cups[i].setEnabled(false);
		}
		cups[6] = new JLabel();
		cups[13] = new JLabel();
		goalPanel1.add(Box.createRigidArea(new Dimension(1, 200)));
		goalPanel1.add(cups[6]);
		goalPanel2.add(Box.createRigidArea(new Dimension(1, 200)));
		goalPanel2.add(cups[13]);

		add(stats, BorderLayout.SOUTH);
		// stats.add(player1);
		// stats.add(player2);
		stats.add(stats1);
		stats.add(stats2);
		// stats.add(currentTurn);
		stats.add(Box.createRigidArea(new Dimension(70, 0)));
		stats.add(description);
	}

	// called by action listener
	public void turn(int index) {
		boolean goalTurn = board.distribute(index);
		// returns if landed in a goal
		resetNumbers();
		int piecesAdded = board.checkForMoves();
		if (piecesAdded != 0) {
			JOptionPane.showMessageDialog(null,
					"Left over peices added to " + getPlayerName(piecesAdded) + "'s goal!!");
			resetNumbers();
		}
		if (board.checkGame()) {
			winner = board.calculateWinner();
			switch (winner) {
			case 0:
				changeDescription(4);
				break;
			case 1:
				wins1++;
				displayWinner();
				break;
			case 2:
				wins2++;
				displayWinner();
				break;
			}
			// resetBoard();
			return;
		}
		if (!goalTurn) {
			currentPlayer = board.switchPlayer();
			changeDescription(1);
			disableLabels();
			return;
		}
		changeDescription(3);
	}

	public void displayWinner() {
		changeDescription(2);
		// display dialog box
		JOptionPane.showMessageDialog(null, getPlayerName(winner) + " won!!");
		stats1.setText(getPlayerName(1) + " Wins: " + wins1);
		stats2.setText(getPlayerName(2) + " Wins: " + wins2);
	}

	public void changeDescription(int code) {
		switch (code) {
		case 1:
			description.setText(getPlayerName(currentPlayer) + "'s Turn...");
			break;
		case 2:
			description.setText("GREAT JOB " + getPlayerName(winner) + "!!!");
			break;
		case 3:
			description.setText(getPlayerName(currentPlayer) + " landed in the goal- player goes again");
			break;
		case 4:
			description.setText("Tie Game no one wins!");
		}
	}

	private void disableLabels() {
		for (int i = 0; i < 13; i++) {
			if (i != 6 && i != 13) {
				if (cups[i].isEnabled()) {
					cups[i].setEnabled(false);
				} else {
					cups[i].setEnabled(true);

				}
			}
		}

	}

	public void resetNumbers() {
		for (int i = 0; i < 14; i++) {
			if ((i + 1) % 7 == 0 || i == 5 || i == 7) {
				cups[i].setText(board.getContent(i) + "");
			} else {
				cups[i].setText(board.getContent(i) + "-");
			}
		}
	}

	public void addActionListeners() {
		newGame.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
			}
		});
		rules.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				RulesFrame rulesFrame = new RulesFrame();
				rulesFrame.setVisible(true);
			}
		});
	}

	public void resetBoard() {
		board.resetBoard();
		resetNumbers();
		currentPlayer = 1;
		changeDescription(1);
		for (int i = 0; i < 6; i++) {
			cups[i].setEnabled(true);
		}
		for (int i = 7; i < 13; i++) {
			cups[i].setEnabled(false);
		}
	}

	public String getPlayerName(int player) {
		if (player == 1) {
			return player1Name;
		} else {
			return player2Name;
		}
	}

	public static void main(String[] args) {
		new Gui2("leah", "elise").setVisible(true);
	}

}
