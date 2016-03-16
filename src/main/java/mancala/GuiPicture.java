package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiPicture extends JFrame {

	private JPanel options, game, stats;
	private JButton newGame;
	private CupComponent[] cups;
	private GoalComponent goal1, goal2;
	private JLabel player1, player2, stats1, stats2, currentTurn;
	private String player1Name, player2Name;
	private int wins1, wins2;
	private JPanel cupPanel, goalPanel1, goalPanel2;

	public GuiPicture() {
		setTitle("Mancala");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		options = new JPanel();
		newGame = new JButton("New Game");

		game = new JPanel(new BorderLayout());
		goal1 = new GoalComponent();
		goal2 = new GoalComponent();
		cupPanel = new JPanel();
		goalPanel1 = new JPanel();
		goalPanel2 = new JPanel();
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
		cupPanel.setLayout(new FlowLayout());
		cupPanel.setOpaque(false);
		goalPanel1.setBackground(Color.green);
		goalPanel2.setBackground(Color.green);}

	public void add() {
		add(options, BorderLayout.NORTH);
		options.add(newGame);

		add(game, BorderLayout.CENTER);
		game.add(goalPanel1, BorderLayout.EAST);
		game.add(goalPanel2, BorderLayout.WEST);
		game.add(cupPanel, BorderLayout.CENTER);
		
		CupComponent  component;
		for (int i = 0; i < 12; i++) {
			 component = new CupComponent(i, i % 6);
			component.setBackground(Color.orange);
			cupPanel.add(component);
			//cupPanel.add(new CupComponent(i, i % 6));
			
		}
		
		goalPanel1.add(goal1);
		goalPanel2.add(goal2);

		add(stats, BorderLayout.SOUTH);
		stats.add(player1);
		stats.add(player2);
		stats.add(stats1);
		stats.add(stats2);
		stats.add(currentTurn);
	}

	public static void main(String[] args) {
		new GuiPicture().setVisible(true);
	}

}
