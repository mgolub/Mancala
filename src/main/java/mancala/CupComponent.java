package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CupComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Image piece;
	protected ArrayList<Image> marbles;
	protected int count;
	protected int originalCount;
	protected Random random;
	protected int x;
	protected int y;

	public CupComponent(int x, int y) {
		// setLayout(new GridLayout(4, 4));
		setPreferredSize(new Dimension(110, 250));
		marbles = new ArrayList<Image>();
		originalCount = count = 4;

		random = new Random();
		randomMarbles();
		piece = marbles.get(random.nextInt(4));
		this.x = x;
		this.y = y;
	}

	private void randomMarbles() {

		marbles.add(new ImageIcon(getClass().getResource("/BlueMarble.png")).getImage());
		marbles.add(new ImageIcon(getClass().getResource("/YellowMarble.png")).getImage());
		marbles.add(new ImageIcon(getClass().getResource("/PinkMarble.png")).getImage());
		marbles.add(new ImageIcon(getClass().getResource("/GreenMarble.png")).getImage());
	}

	public void setCount(int count) {
		this.count = count;
		repaint();
	}

	public void emptyCup() {
		count = originalCount;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color color = new Color(255, 255, 255, 50);
		g.setColor(color);
		g.fillOval(0, 0, 100, 120);

		for (int i = 0, j = -14; i < count; i++, j++) {

			if (i < 14) {
				g.drawImage(piece = marbles.get(random.nextInt(4)), 10, i * 15, this);

			} else {
				g.drawImage(piece = marbles.get(random.nextInt(4)), 40, j * 15, this);

			}
		}

		// super.repaint();

	}

}
