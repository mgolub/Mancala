package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGUI extends JFrame {

	private JPanel options, game, stats;
	private JButton newGame;
	private CupComponent[] cups;
	private GoalComponent goal1, goal2;
	private JLabel player1, player2, stats1, stats2, currentTurn;
	private String player1Name, player2Name;
	private int wins1, wins2;

	public BoardGUI() {
		setTitle("Mancala");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		options = new JPanel();
		newGame = new JButton("New Game");

		game = new JPanel(new BorderLayout());
		goal1 = new GoalComponent();
		goal2 = new GoalComponent();

		stats = new JPanel(new FlowLayout());
		player1 = new JLabel("Player1: " + player1Name);
		player2 = new JLabel("Player2: " + player2Name);
		stats1 = new JLabel("Player1 Wins: " + wins1);
		stats2 = new JLabel("Player2 Wins: " + wins2);
		currentTurn = new JLabel("Current Player: " + player1Name);

		format();
		add();

	}

	public void format() {
		game.setBackground(new Color(156, 93, 82));
	}

	public void add() {
		add(options, BorderLayout.NORTH);
		options.add(newGame);

		add(game, BorderLayout.CENTER);
		
		
		game.add(goal1, BorderLayout.EAST);
		game.add(goal2, BorderLayout.WEST);
		
		for (int i = 0; i < 12; i++) {
			game.add(new CupComponent(i, i % 6), BorderLayout.CENTER);
		}
		

		add(stats, BorderLayout.SOUTH);
		stats.add(player1);
		stats.add(player2);
		stats.add(stats1);
		stats.add(stats2);
		stats.add(currentTurn);
	}

	public static void main(String[] args) {
		new BoardGUI().setVisible(true);
	}

}
