package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GoalComponent extends JComponent {

	private int count;
	private Image piece;

	public GoalComponent() {
		count = 0;
		setPreferredSize(new Dimension(120, 455));
		piece = new ImageIcon(getClass().getResource("/red.png")).getImage();
	}

	public void emptyGoal() {
		count = 0;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.black);
		g.fillOval(0, 0, 115, 450);

		for (int i = 0, j = -13; i < count; i++, j++) {
			if (i < 13) {
				g.drawImage(piece, 10, i * 30, this);
			} else {
				g.drawImage(piece, 40, j * 30, this);

			}
		}

		repaint();
	}

	public void setCount(int count) {
		this.count = count;
		repaint();
	}
}
