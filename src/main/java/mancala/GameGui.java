package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.inject.Inject;

public class GameGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel optionsPanel, statsPanel;
	private JButton newGameButton, rulesButton;
	private BoardPanel board;

	@Inject
	public GameGui(Players players, PieceAnimation animation, final BoardPanel board) {

		setTitle("Mancala");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		optionsPanel = new JPanel();
		this.board = board;

		newGameButton = new JButton("New Game");
		rulesButton = new JButton("Rules");
		this.setIconImage(new ImageIcon(getClass().getResource(
				"/MancalaBoard.png")).getImage());

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
						int humanIndex = (Integer) cup
								.getClientProperty("index");
						if ((!cup.isEnabled() || board
								.getQtyMarbles(humanIndex) == 0)) {
							return;
						}
						board.disableAllCups();
						board.turn(humanIndex);
						if (!board.goAgain()) {
							new Timer().schedule(new DelayTask(), 3000);
						}
					}
				});
			}
		}

		setGlassPane(animation);
		getGlassPane().setVisible(true);
		animation.setOpaque(false);
		repaint();
		pack();
	}

	private void setPanels() {
		optionsPanel = new JPanel();
		statsPanel = new JPanel(new BorderLayout());
	}

	public void format() {
		Font font1 = new Font("Rockwell Extra Bold", Font.PLAIN, 28);
		optionsPanel.setBackground(Color.BLACK);
		optionsPanel.setPreferredSize(new Dimension(1000, 50));
		newGameButton.setFont(font1);
		newGameButton.setBackground(Color.black);
		newGameButton.setForeground(Color.GREEN);
		rulesButton.setFont(font1);
		rulesButton.setBackground(Color.black);
		rulesButton.setForeground(Color.GREEN);
		statsPanel.setBackground(Color.BLACK);
		statsPanel.setPreferredSize(new Dimension(1000, 30));
	}

	public void add() {
		//optionsPanel.add(newGameButton);
		optionsPanel.add(rulesButton);
		add(optionsPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(board, BorderLayout.CENTER);
		add(statsPanel, BorderLayout.SOUTH);
	}

	public void addActionListeners() {
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board = new BoardPanel(new Players("your", "computer"),
						new PieceAnimation());
				repaint();
			}
		});
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RulesFrame rulesFrame = new RulesFrame();
				rulesFrame.setVisible(true);
			}
		});
	}

	class DelayTask extends TimerTask {
		@Override
		public void run() {
			board.turn(1);
			System.out.println("called timer task");
		}
	}

}
