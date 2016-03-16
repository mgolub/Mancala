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

public class GuiNewLogic extends JFrame {

	private JPanel options, game, stats;
	private JButton newGame, rules;
	private JLabel player1, player2, stats1, stats2, currentTurn;
	private String player1Name, player2Name;
	private int currentPlayer;
	private int wins1, wins2;
	private JPanel cupsPanel, cupPanel1, cupPanel2, goalPanel1, goalPanel2;
	private JLabel[] cups;
	// private JLabel goal1, goal2;
	private Board2 board;

	public GuiNewLogic(String name1, String name2) {
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
		stats1 = new JLabel("Player1 Wins: " + wins1);
		stats2 = new JLabel("Player2 Wins: " + wins2);
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
		stats.add(player1);
		stats.add(player2);
		stats.add(stats1);
		stats.add(stats2);
		stats.add(currentTurn);
	}

	// called by action listener
	public void turn(int index) {
		boolean goalTurn = board.distribute(index); // returns if landed in a
													// goal
		resetNumbers();
		board.checkForMoves();

		if (board.checkGame()) {
			int winner = board.calculateWinner();
			if (currentPlayer == 1) {
				wins1++;
			} else {
				wins2++;
			}
			// display dialog box
			JOptionPane.showMessageDialog(null, currentPlayer + " won");
			stats1.setText("Player1 Wins: " + wins1);
			stats2.setText("Player2 Wins: " + wins2);
			resetBoard();
			return;
		}
		if (!goalTurn) {
			currentPlayer = board.switchPlayer();
			disableLabels();
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
		for(int i = 0; i < 6; i++){
			cups[i].setEnabled(true);
		}
		for(int i = 7; i < 13; i++){
			cups[i].setEnabled(false);
		}
	}

	public static void main(String[] args) {
		new GuiNewLogic("gfgf", "gtdftr").setVisible(true);
	}

}
