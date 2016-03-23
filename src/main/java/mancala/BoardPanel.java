package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel cupPanel1, cupPanel2, cupsPanel, goalPanel1, goalPanel2;
	private JComponent[] cupComponents;
	private Board board;

	public BoardPanel(JComponent[] cupComponents2) {
		this.cupComponents = cupComponents2;
		this.setBorder(new LineBorder(Color.black, 8, true));
		this.setLayout(new BorderLayout());
		createComponents();
		formatComponetents();
		addComponets();


		for (int i = 0; i < 6; i++) {
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

		cupComponents[6] = new GoalComponent();
		cupComponents[6].setToolTipText(Integer.toString(board.getContent(6)));
		cupComponents[13] = new GoalComponent();
		cupComponents[13]
				.setToolTipText(Integer.toString(board.getContent(13)));

		goalPanel1.add(cupComponents[6], new GridBagConstraints());
		goalPanel2.add(cupComponents[13], new GridBagConstraints());
	}

	private void createComponents() {
		cupsPanel = new JPanel();
		cupPanel1 = new JPanel(new FlowLayout());
		cupPanel2 = new JPanel(new FlowLayout());
		goalPanel1 = new JPanel(new GridBagLayout());
		goalPanel2 = new JPanel(new GridBagLayout());
		board = new Board();

	}

	private void formatComponetents() {
		cupsPanel.setLayout(new BoxLayout(cupsPanel, BoxLayout.Y_AXIS));
		cupsPanel.setBackground(new Color(139, 69, 19));
		cupPanel1.setBackground(new Color(139, 69, 19));
		cupPanel2.setBackground(new Color(139, 69, 19));
		goalPanel1.setBackground(new Color(139, 69, 19));
		goalPanel2.setBackground(new Color(139, 69, 19));
	}

	private void addComponets() {
		cupsPanel.add(cupPanel2);
		cupsPanel.add(cupPanel1);
		add(cupsPanel, BorderLayout.CENTER);
		add(goalPanel1, BorderLayout.EAST);
		add(goalPanel2, BorderLayout.WEST);
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
