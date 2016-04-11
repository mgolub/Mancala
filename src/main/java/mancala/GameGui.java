package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel optionsPanel, statsPanel;
	private JButton newGameButton, rulesButton;
	private JLabel statsLabel1, statsLabel2, descriptionLabel;
	private Players players;
	private BoardPanel board;

	public GameGui(String name1, String name2) {

		setTitle("Mancala");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		optionsPanel = new JPanel();
		players = new Players(name1, name2);
		board = new BoardPanel(this.players);
		statsPanel = new JPanel(new BorderLayout());
		newGameButton = new JButton("New Game");
		rulesButton = new JButton("Rules");
		descriptionLabel = new JLabel(board.description());
		
		setPanels();
		add();
		format();
		addActionListeners();
		board.resetBoard();

		for (int i = 0; i < 14; i++) {
			if ((i != Board.GOAL1) && (i != Board.GOAL2)) {

				board.getCup(i).putClientProperty("index", i);
				board.getCup(i).addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						Cup cup = (Cup) event.getSource();
						int index = (Integer) cup.getClientProperty("index");
						if (!cup.isEnabled()
								|| (board.getQtyMarbles(index) == 0)) {
							return;
						}
						board.disableAllCups();
						board.turn(index);
						descriptionLabel.setText(board.description());

					}
				});
			}
		}
	}

	private void setPanels() {
		optionsPanel = new JPanel();
		statsPanel = new JPanel(new BorderLayout());
	}

	public void format() {
		Font font1 = new Font("Rockwell Extra Bold", Font.PLAIN, 28);
		Font font2 = new Font("Calibri", Font.PLAIN, 38);
		optionsPanel.setBackground(Color.BLACK);
		optionsPanel.setPreferredSize(new Dimension(1000, 50));
		newGameButton.setFont(font1);
		newGameButton.setBackground(Color.black);
		newGameButton.setForeground(Color.GREEN);
		rulesButton.setFont(font1);
		rulesButton.setBackground(Color.black);
		rulesButton.setForeground(Color.GREEN);
		statsPanel.setBackground(Color.BLACK);
		statsPanel.setPreferredSize(new Dimension(1000, 45));
		descriptionLabel.setFont(font2);
		descriptionLabel.setForeground(Color.GREEN);
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public void add() {
		optionsPanel.add(newGameButton);
		optionsPanel.add(rulesButton);
		add(optionsPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		statsPanel.add(descriptionLabel, BorderLayout.NORTH);
		add(statsPanel, BorderLayout.SOUTH);
	}

	public void addActionListeners() {
		newGameButton.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				board.resetBoard();
				board.resetCups();
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

	public static void main(String[] args) {
		GameGui test = new GameGui("one", "two");
		test.setVisible(true);
	}
}
