package mancala;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Cup extends JComponent {

	private static final long serialVersionUID = 1L;
	protected Image piece;
	protected ArrayList<Image> marbles;
	protected int count;
	// protected int originalCount;
	protected Random random;
	protected int x;
	protected int y;

	public Cup(int x, int y) {
		setPreferredSize(new Dimension(110, 250));
		marbles = new ArrayList<Image>();
		count = 4;
		random = new Random();
		randomMarbles();
		piece = marbles.get(random.nextInt(4));
		this.x = x;
		this.y = y;
	}

	private void randomMarbles() {

		marbles.add(new ImageIcon(getClass().getResource("/BlueMarble.png"))
				.getImage());
		marbles.add(new ImageIcon(getClass().getResource("/YellowMarble.png"))
				.getImage());
		marbles.add(new ImageIcon(getClass().getResource("/PinkMarble.png"))
				.getImage());
		marbles.add(new ImageIcon(getClass().getResource("/GreenMarble.png"))
				.getImage());
	}

	public void setCount(int count) {
		this.count = count;
		repaint();
	}

	public void emptyCup() {
		count = 4;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		int piecesDrawn = 0, piecesPerRow = 4, location = 0, rows = 1;
		for (int i = 0; i < count; i++) {
			if (piecesDrawn % piecesPerRow == 0) {
				rows++;
				location = 0;
			}
			g.drawImage(piece = marbles.get(random.nextInt(4)), rows * 10,
					location * 15, this);
			piecesDrawn++;
			location++;

		}

	}

	public void addPiece() {
		count++;
	}

	public int getCount() {
		return count;
	}

	public int removePieces() {
		int temp = count;
		count = 0;
		return temp;
	}

	public void reset() {
		count = 4;
	}

	public int getComponentX() {
		return this.x;
	}

	public int getComponentY() {
		return this.y;
	}
}