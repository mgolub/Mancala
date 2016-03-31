package mancala;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class PieceAnimation extends JComponent {

	private static final long serialVersionUID = 1L;
	int cupNumber;
	int pieceAmount;
	int xAxis;
	Timer timer;

	public PieceAnimation(int cupNumber, int pieceAmount) {

		this.cupNumber = cupNumber + 1;// assume cupNumber starts at 0
		this.pieceAmount = pieceAmount;

		if (this.cupNumber > 0 && this.cupNumber < 6) {
			xAxis = cupNumber * 100;
		} else if (this.cupNumber > 6 && this.cupNumber < 13) {
			xAxis = (cupNumber * 100);
		}
		repaint();
		ActionListener animater = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}

		};
		timer = new Timer(50, animater);
		timer.start();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < pieceAmount; i++) {
			Image piece = new ImageIcon(getClass().getResource("/BlueMarble.png")).getImage();
			if (cupNumber > 0 && cupNumber < 6) {// top row
				g.drawImage(piece, xAxis, (i * 15) + 100, this);
			} 
			else {//bottom row
				g.drawImage(piece, xAxis, (i * 15) + 400, this);
			}

			// if marbles reach next cup
			if (xAxis == (cupNumber - 1) * 100) {
				pieceAmount--;
				cupNumber--;
				// if this is the last marble stop timer
				if (pieceAmount == 0) {
					timer.stop();
					g.clearRect(0, 0, getWidth(), getHeight());
				}
			}
			xAxis -= 2;

		}

	}

}
