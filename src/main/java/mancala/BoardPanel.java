package mancala;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel cupPanel1, cupPanel2, cupsPanel, goalPanel1, goalPanel2;
	private JComponent[] cupComponents;
	private Board board;

	public BoardPanel(JComponent[] cupComponents2, Players players) {
		this.cupComponents = cupComponents2;
		this.setLayout(new BorderLayout());
		createComponents(players);
		formatComponetents();
		addComponets();

		
		
		


	}

	private void createComponents(Players players) {
		cupsPanel = new JPanel();
		cupPanel1 = new JPanel(new FlowLayout());
		cupPanel2 = new JPanel(new FlowLayout());
		goalPanel1 = new JPanel(new GridBagLayout());
		goalPanel2 = new JPanel(new GridBagLayout());
		board = new Board(players);
		createCupComponents();

	}
	private void createCupComponents(){
		for (int i = 0; i < Board.GOAL1; i++) {
			cupComponents[i] = new CupComponent();
			cupPanel1.add(cupComponents[i]);
			cupComponents[i].setToolTipText(Integer.toString(board
					.getContent(i)));
		}
		for (int i = 12; i >= 7; i--) {
			cupComponents[i] = new CupComponent();
			cupPanel2.add(cupComponents[i]);
			cupComponents[i].setToolTipText(Integer.toString(board
					.getContent(i)));
		}

		cupComponents[Board.GOAL1] = new GoalComponent();
		cupComponents[Board.GOAL1].setToolTipText(Integer.toString(board
				.getContent(Board.GOAL1)));
		cupComponents[Board.GOAL2] = new GoalComponent();
		cupComponents[Board.GOAL2].setToolTipText(Integer.toString(board
				.getContent(Board.GOAL2)));

	}

	private void formatComponetents() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		cupsPanel.setOpaque(false);
		cupPanel1.setOpaque(false);
		cupPanel2.setOpaque(false);
		goalPanel1.setOpaque(false);
		goalPanel2.setOpaque(false);
		Dimension goalSize = new Dimension(120,700);
		goalPanel2.setPreferredSize(goalSize);
		goalPanel1.setPreferredSize(goalSize);

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		ImageIcon icon = new ImageIcon(getClass().getResource("/board.jpg"));
		g.drawImage(icon.getImage(), 0, 0, null);
	}

	private void addComponets() {
		JPanel spaceHolder = new JPanel();
		spaceHolder.setPreferredSize(new Dimension(800, 40));
		spaceHolder.setOpaque(false);
		cupsPanel.add(spaceHolder);
		cupsPanel.add(cupPanel2);
		cupsPanel.add(cupPanel1);
		
		add(cupsPanel, BorderLayout.CENTER);

		goalPanel1.add(cupComponents[Board.GOAL1]);
		goalPanel2.add(cupComponents[Board.GOAL2]);
		JPanel west = new JPanel();
		west.setOpaque(false);
		west.add(goalPanel2);
		JPanel east = new JPanel();
		east.setOpaque(false);
		east.add(goalPanel1);
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(cupsPanel, BorderLayout.CENTER);
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

	public void resetBoard() {
		board.resetBoard();
	}
}
