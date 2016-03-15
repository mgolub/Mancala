package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI2 extends JFrame {

	private JPanel options, game, stats;
	private JButton newGame;
	private JLabel player1, player2, stats1, stats2, currentTurn;
	private String player1Name, player2Name;
	private int currentPlayer;
	private int wins1, wins2;
	private JPanel cupsPanel, cupPanel1, cupPanel2, goalPanel1, goalPanel2;
	private JLabel[] cups;
	// private JLabel goal1, goal2;
	private Board board;

	public GUI2(String name1, String name2) {
		setTitle("Mancala");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		currentPlayer = 1;
		options = new JPanel();
		newGame = new JButton("New Game");
		board = new Board(name1, name2);

		game = new JPanel(new BorderLayout());
		cupsPanel = new JPanel();
		cupPanel1 = new JPanel();
		cupPanel2 = new JPanel();
		goalPanel1 = new JPanel();
		goalPanel2 = new JPanel();
		stats = new JPanel(new FlowLayout());
		player1 = new JLabel("Player1: " + name1);
		player2 = new JLabel("Player2: " + name2);
		stats1 = new JLabel("Player1 Wins: " + wins1);
		stats2 = new JLabel("Player2 Wins: " + wins2);
		currentTurn = new JLabel("Current Player: " + player1Name);

		cups = new JLabel[14];
		// goal1 = new JLabel();
		// goal2 = new JLabel();
		add();
		format();
		resetNumbers();
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
			cups[i].setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 165));
			cups[i].setVerticalAlignment(JLabel.CENTER);

			if (i != 6 || i != 13) {
				cups[i].putClientProperty("index", i);
				cups[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						JLabel label = (JLabel) event.getSource();
						int index = (Integer) label.getClientProperty("index");
						turn(index);
						System.out.println("Selected " + index);
					}
				});
			}

		}
	}

	public void add() {
		add(options, BorderLayout.NORTH);
		options.add(newGame);

		add(game, BorderLayout.CENTER);
		game.add(cupsPanel, BorderLayout.CENTER);
		game.add(goalPanel1, BorderLayout.EAST);
		game.add(goalPanel2, BorderLayout.WEST);
		cupsPanel.add(cupPanel2);
		cupsPanel.add(cupPanel1);
		for (int i = 0; i < 6; i++) {
			cups[i] = new JLabel();
			cupPanel1.add(cups[i]);
		}
		for (int i = 12; i > 6; i--) {
			cups[i] = new JLabel();
			cupPanel2.add(cups[i]);
			cups[i].setEnabled(false);
		}
		cups[6]= new JLabel();
		cups[13] = new JLabel();
		goalPanel1.add(Box.createRigidArea(new Dimension(1, 185)));
		goalPanel1.add(cups[6]);
		goalPanel2.add(Box.createRigidArea(new Dimension(1, 185)));
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
		board.distribute(index);
		resetNumbers();
		if (board.checkGame()) {
			int winner = board.calculateWinner();

			// display dialog box
			System.out.println(currentPlayer + "won");

			board.resetBoard();
			return;
		}
		currentPlayer = board.switchPlayer();
		disableLabels();

	}

	private void disableLabels() {
		for (int i = 0; i < 13; i++) {
			if (cups[i].isEnabled()) {
				cups[i].setEnabled(false);
			} else {
				cups[i].setEnabled(true);

			}
		}

	}

	public static void main(String[] args) {
		new GUI2("gfgf", "gtdftr").setVisible(true);
	}

	public void resetNumbers() {
		for (int i = 0; i < 14; i++) {
			cups[i].setText(board.getContent(i) + "");
		}
	}

}
