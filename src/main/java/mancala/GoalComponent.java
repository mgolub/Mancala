package mancala;

import java.awt.Dimension;
import java.awt.Graphics;

public class GoalComponent extends CupComponent {

	private static final long serialVersionUID = 1L;

	public GoalComponent() {
		super();
		originalCount = count = 0;
		setPreferredSize(new Dimension(120, 455));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.fillOval(0, 0, 115, 215);

		for (int i = 0, j = -25; i < count; i++, j++) {
			if (i < 25) {
				g.drawImage(piece = marbles.get(random.nextInt(4)), 10, i * 15, this);
			} else {
				g.drawImage(piece = marbles.get(random.nextInt(4)), 40, j * 15, this);

			}
		}

		// repaint();
	}
}
