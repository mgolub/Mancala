package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
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
		cupsPanel.setLayout(new BorderLayout());
		cupPanel1 = new JPanel(new FlowLayout());
		cupPanel2 = new JPanel(new FlowLayout());
		goalPanel1 = new JPanel(new GridBagLayout());
		goalPanel2 = new JPanel(new GridBagLayout());
		board = new Board(players);
		createCupComponents();

	}

	private void createCupComponents() {
		for (int i = 0; i < Board.GOAL1; i++) {
			cupComponents[i] = new CupComponent(647, 1010);// For testing the
															// x,y
			cupPanel1.add(cupComponents[i]);
			cupComponents[i].setToolTipText(Integer.toString(board.getContent(i)));
		}
		for (int i = 12; i >= 7; i--) {
			cupComponents[i] = new CupComponent(639, 1560); // For testing the
															// x,y
			cupPanel2.add(cupComponents[i]);
			cupComponents[i].setToolTipText(Integer.toString(board.getContent(i)));
		}

		cupComponents[Board.GOAL1] = new GoalComponent(0, 0);
		cupComponents[Board.GOAL1].setToolTipText(Integer.toString(board.getContent(Board.GOAL1)));
		cupComponents[Board.GOAL2] = new GoalComponent(0, 0);
		cupComponents[Board.GOAL2].setToolTipText(Integer.toString(board.getContent(Board.GOAL2)));

	}

	private void formatComponetents() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		Dimension cupPanelDimension = new Dimension(700, 20);
		cupsPanel.setOpaque(false);
		cupPanel1.setOpaque(false);
		cupPanel2.setOpaque(false);
		cupPanel1.setPreferredSize(cupPanelDimension);
		cupPanel2.setPreferredSize(cupPanelDimension);
		goalPanel1.setOpaque(false);
		goalPanel2.setOpaque(false);
		goalPanel2.setPreferredSize(new Dimension(159, 700));
		goalPanel1.setPreferredSize(new Dimension(125, 700));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		BoardImgPanel imgPanel = new BoardImgPanel();
		g.drawImage(imgPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	private void addComponets() {

		addSpaceHolder(cupsPanel, 200, 190);
		cupsPanel.add(cupPanel2, BorderLayout.SOUTH);
		cupsPanel.add(cupPanel1, BorderLayout.CENTER);
		addSpaceHolder(cupsPanel, 200, 10);
		goalPanel1.add(cupComponents[Board.GOAL1]);
		goalPanel2.add(cupComponents[Board.GOAL2]);
		JPanel west = new JPanel();
		west.setLayout(new FlowLayout());
		west.setPreferredSize(new Dimension(159, 50));
		west.setOpaque(false);
		addSpaceHolder(west, 159, 200);
		west.add(goalPanel2);
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(125, 50));

		east.setOpaque(false);
		addSpaceHolder(east, 125, 200);
		east.add(goalPanel1);
		add(east, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(cupsPanel, BorderLayout.CENTER);
	}

	private void addSpaceHolder(JPanel panel, int width, int height) {
		JPanel spaceHolder = new JPanel();
		spaceHolder.setPreferredSize(new Dimension(width, height));
		spaceHolder.setOpaque(false);
		panel.add(spaceHolder);

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