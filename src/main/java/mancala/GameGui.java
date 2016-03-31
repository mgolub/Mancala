package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel optionsPanel, statsPanel;
	private JButton newGameButton, rulesButton;
	private JComponent[] cupComponents;
	private JLabel statsLabel1, statsLabel2, descriptionLabel;

	private Players players;
	private BoardPanel board;
	private int winner;

	public GameGui(String name1, String name2) {
		setTitle("Mancala");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		optionsPanel = new JPanel();
		cupComponents = new JComponent[14];
		players = new Players(name1, name2);

		board = new BoardPanel(this.cupComponents, this.players);
		statsPanel = new JPanel(new BorderLayout());

		setPanels();

		newGameButton = new JButton("New Game");
		rulesButton = new JButton("Rules");
		statsLabel1 = new JLabel(players.playersName(1) + " Wins: " + players.gamesWon(1));
		statsLabel2 = new JLabel(players.playersName(2) + " Wins: " + players.gamesWon(2));
		descriptionLabel = new JLabel();

		this.setIconImage(new ImageIcon(getClass().getResource("/MancalaBoard.png")).getImage());

		add();
		format();
		addActionListeners();
		resetBoard();
	}

	private void setPanels() {
		optionsPanel = new JPanel();
		statsPanel = new JPanel(new BorderLayout());
	}

	public void format() {
		Font font1 = new Font("Rockwell Extra Bold", Font.PLAIN, 28);
		Font font2 = new Font("Calibri", Font.PLAIN, 38);
		optionsPanel.setBackground(Color.BLACK);
		optionsPanel.setPreferredSize(new Dimension(1000, 40));
		newGameButton.setFont(font1);
		newGameButton.setBackground(Color.black);
		newGameButton.setForeground(Color.red);
		rulesButton.setFont(font1);
		rulesButton.setBackground(Color.black);
		rulesButton.setForeground(Color.red);

		// statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		statsPanel.setBackground(Color.BLACK);
		statsPanel.setPreferredSize(new Dimension(1000, 40));

		statsLabel1.setFont(font1);
		statsLabel1.setForeground(Color.MAGENTA);
		statsLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		statsLabel2.setFont(font1);
		statsLabel2.setForeground(Color.MAGENTA);
		statsLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(font2);
		descriptionLabel.setForeground(Color.BLUE);
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < cupComponents.length; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {
				cupComponents[i].putClientProperty("index", i);
				cupComponents[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						CupComponent cup = (CupComponent) event.getSource();
						int index = (Integer) cup.getClientProperty("index");
						if (!cup.isEnabled() || (board.getQtyMarbles(index) == 0)) {
							return;
						}
						turn(index);
					}
				});
			}
		}

	}

	public void add() {
		optionsPanel.add(newGameButton);
		optionsPanel.add(rulesButton);
		add(optionsPanel, BorderLayout.NORTH);

		add(board, BorderLayout.CENTER);

		statsPanel.add(statsLabel1, BorderLayout.EAST);
		statsPanel.add(statsLabel2, BorderLayout.WEST);
		statsPanel.add(descriptionLabel, BorderLayout.NORTH);
		add(statsPanel, BorderLayout.SOUTH);
	}

	// called by action listener
	public void turn(int index) {
		boolean goalTurn = board.distributePieces(index);
		// returns true if landed in a goal
		resetCups();
		repaint();

		int piecesAdded = board.checkForMoves();
		if (piecesAdded != 0) {
			JOptionPane.showMessageDialog(null, "Left over peices added to " + players.playersName(piecesAdded)
					+ "'s goal!!");
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
		if (!goalTurn) {
			players.switchPlayers();
			changeDescription(1);
			disableCups();
			repaint();
			return;
		}
		changeDescription(3);
	}

	private void disableCups() {
		for (int i = 0; i < 13; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {
				if (cupComponents[i].isEnabled()) {
					cupComponents[i].setEnabled(false);
				} else {
					cupComponents[i].setEnabled(true);
				}
			}
		}

	}

	public void displayWinner() {
		changeDescription(2);
		JOptionPane.showMessageDialog(null, players.playersName(1) + ": " + board.getQtyMarbles(6) + " points\n"
				+ players.playersName(2) + ": " + board.getQtyMarbles(13) + " points\n\n" + players.playersName(winner)
				+ " won!!");
		statsLabel1.setText(players.playersName(1) + " Wins: " + players.gamesWon(1));
		statsLabel2.setText(players.playersName(2) + " Wins: " + players.gamesWon(2));
	}

	public void changeDescription(int code) {
		switch (code) {
		case 1:
			descriptionLabel.setText(players.currentPlayersName() + "'s Turn...");
			break;
		case 2:
			descriptionLabel.setText("**** GREAT JOB " + players.playersName(winner) + "!!! ****");
			break;
		case 3:
			descriptionLabel.setText(players.currentPlayersName() + " landed in the goal - player goes again");
			break;
		case 4:
			descriptionLabel.setText("Tie Game - no one wins!");
		}
		resetCups();
	}

	public void resetCups() {
		for (int i = 0; i < 14; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {
				((CupComponent) cupComponents[i]).setCount(board.getQtyMarbles(i));
			} else {
				((GoalComponent) cupComponents[i]).setCount(board.getQtyMarbles(i));
			}
		}
		for (int i = 0; i < 14; i++) {
			cupComponents[i].setToolTipText(Integer.toString(board.getQtyMarbles(i)));
		}
		repaint();
	}

	public void addActionListeners() {
		newGameButton.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
				resetCups();
			}
		});
		rulesButton.addActionListener(new ActionListener() {
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
		changeDescription(1);
		for (int i = 0; i < 6; i++) {
			cupComponents[i].setEnabled(true);
		}
		for (int i = 7; i < 13; i++) {
			cupComponents[i].setEnabled(false);
		}
	}

	public static void main(String[] args) {
		GameGui test = new GameGui("one", "two");
		test.setVisible(true);

	}

}
