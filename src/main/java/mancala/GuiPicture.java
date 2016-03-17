package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GuiPicture extends JFrame {

	private JPanel options, game, stats;
	private JButton newGame, rules;
	private JComponent[] cups;
	private JLabel stats1, stats2, description;
	private String player1Name, player2Name;
	private int currentPlayer;
	private int wins1, wins2, winner;
	private JPanel cupsPanel, cupPanel1, cupPanel2, goalPanel1, goalPanel2;
	private Board2 board;
	private LineBorder border;
	private Font font1, font2;

	public GuiPicture(String name1, String name2) {
		setTitle("Mancala");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		/*
		 * setContentPane(new JLabel(new ImageIcon(getClass().getResource(
		 * "/wood.jpg"))));
		 */
		this.setIconImage(new ImageIcon(getClass().getResource("/icon.jpg")).getImage());

		player1Name = name1;
		player2Name = name2;

		currentPlayer = 1;
		options = new JPanel();
		newGame = new JButton("New Game");
		rules = new JButton("Rules");
		board = new Board2(name1, name2);

		game = new JPanel(new BorderLayout());
		cupPanel1 = new JPanel();
		cupPanel2 = new JPanel();
		cupsPanel = new JPanel();
		goalPanel1 = new JPanel();
		goalPanel2 = new JPanel();
		stats = new JPanel(new FlowLayout());
		stats1 = new JLabel(getPlayerName(1) + " Wins: " + wins1);
		stats2 = new JLabel(getPlayerName(2) + " Wins: " + wins2);
		description = new JLabel();
		border = new LineBorder(Color.black, 8, true);
		font1 = new Font("Rockwell Extra Bold", Font.PLAIN, 25);
		font2 = new Font("Calibri", Font.BOLD, 22);

		wins1 = 0;
		wins2 = 0;

		cups = new JComponent[14];

		add();
		format();
		repaint();
		addActionListeners();
		changeDescription(1);

	}

	public void format() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		cupPanel2.setLayout(new FlowLayout());
		cupPanel2.setBackground(new Color(139, 69, 19));
		cupPanel1.setLayout(new FlowLayout());
		cupPanel1.setBackground(new Color(139, 69, 19));
		goalPanel1.setBackground(new Color(139, 69, 19));
		goalPanel1.setLayout(new GridBagLayout());
		goalPanel2.setBackground(new Color(139, 69, 19));
		goalPanel2.setLayout(new GridBagLayout());
		game.setBorder(border);
		stats.setBackground(Color.gray);
		options.setBackground(Color.gray);
		stats1.setFont(font1);
		stats1.setForeground(Color.red);
		stats2.setForeground(Color.red);
		stats2.setFont(font1);
		description.setFont(font2);
		description.setForeground(Color.red);
		newGame.setBackground(Color.black);
		newGame.setForeground(Color.red);
		newGame.setFont(font1);
		rules.setBackground(Color.black);
		rules.setForeground(Color.red);
		rules.setFont(font1);

		for (int i = 0; i < cups.length; i++) {
			if (i != 6 && i != 13) {
				cups[i].putClientProperty("index", i);
				cups[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						CupComponent cup = (CupComponent) event.getSource();
						int index = (Integer) cup.getClientProperty("index");
						if (!cup.isEnabled() || board.getContent(index) == 0) {
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
		game.add(cupsPanel, BorderLayout.CENTER);

		for (int i = 0; i < 14; i++) {
			if (i != 6 && i != 13) {
				cups[i] = new CupComponent(i, 0);

			} else {
				cups[i] = new GoalComponent();
			}
		}

		for (int i = 0; i < 6; i++) {
			cupPanel1.add(cups[i]);
		}
		for (int i = 13; i >= 7; i--) {
			cupPanel2.add(cups[i]);
		}

		goalPanel1.add(cups[6], new GridBagConstraints());
		goalPanel2.add(cups[13], new GridBagConstraints());

		add(stats, BorderLayout.SOUTH);
		stats.add(stats1);
		stats.add(Box.createRigidArea(new Dimension(40, 0)));
		stats.add(stats2);
		stats.add(Box.createRigidArea(new Dimension(70, 0)));
		stats.add(description);
	}

	// called by action listener
	public void turn(int index) {
		boolean goalTurn = board.distribute(index);// returns if landed in a
													// goal
		resetCups();
		repaint();
		int piecesAdded = board.checkForMoves();
		if (piecesAdded != 0) {
			JOptionPane.showMessageDialog(null,
					"Left over peices added to " + getPlayerName(piecesAdded) + "'s goal!!");
			repaint();
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
			repaint();
			return;
		}
		if (!goalTurn) {
			currentPlayer = board.switchPlayer();
			changeDescription(1);
			disableCups();
			repaint();
			return;
		}
		changeDescription(3);
	}

	private void disableCups() {
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

	public void displayWinner() {
		changeDescription(2);
		// display dialog box
		JOptionPane.showMessageDialog(null, getPlayerName(1) + ": " + board.getGoal1() + " points\n" + getPlayerName(2)
				+ ": " + board.getGoal2() + " points\n\n" + getPlayerName(winner) + " won!!");
		stats1.setText(getPlayerName(1) + " wins: " + wins1);
		stats2.setText(getPlayerName(2) + " wins: " + wins2);
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
			description.setText(getPlayerName(currentPlayer) + " landed in the goal - player goes again");
			break;
		case 4:
			description.setText("Tie Game - no one wins!");
		}
		resetCups();
	}

	public void resetCups() {
		for (int i = 0; i < 14; i++) {
			if (i != 6 && i != 13) {
				((CupComponent) cups[i]).setCount(board.getContent(i));
				System.out.println(i + " has " + board.getContent(i));
			} else {
				((GoalComponent) cups[i]).setCount(board.getContent(i));
				System.out.println(i + " has " + board.getContent(i));
			}
		}
		repaint();
	}

	public void addActionListeners() {
		newGame.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
				resetCups();
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
		repaint();
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
		new GuiPicture("player1", "player2").setVisible(true);
	}

}
