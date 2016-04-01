package mancala;

import java.awt.Dimension;
import java.awt.Graphics;

public class GoalComponent extends CupComponent {

	private static final long serialVersionUID = 1L;

	public GoalComponent(int x, int y) {
		super(x, y);
		originalCount = count = 0;
		setPreferredSize(new Dimension(120, 455));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		/*
		 * for (int i = 0, j = -15; i < count; i++, j++) { if (i < 15) {
		 * g.drawImage(piece = marbles.get(random.nextInt(4)), 10, i * 15,
		 * this); } else { g.drawImage(piece = marbles.get(random.nextInt(4)),
		 * 40, j * 15, this);
		 * 
		 * } }
		 */
		// repaint();
	}
}
