package mancala;

import java.awt.Dimension;
import java.awt.Graphics;

public class Goal extends Cup {

	private static final long serialVersionUID = 1L;

	public Goal(int x, int y) {
		super(x, y);
		count = 0;
		setPreferredSize(new Dimension(120, 455));
	}

	@Override
	protected void paintComponent(Graphics g) {
		int piecesDrawn = 0, piecesPerRow = 10, location = 0, rows = 1;
		for (int i = 0; i < count; i++) {
			if (piecesDrawn % piecesPerRow == 0) {
				rows++;
				location = 0;
			}
			g.drawImage(piece = marbles.get(random.nextInt(4)), (rows * 5)-10,
					location * 15, this);
			piecesDrawn++;
			location++;

		}

	}

	public void reset() {
		count = 0;
	}

	public void addToGoal(int amount) {
		count = count + amount;
	}
}
