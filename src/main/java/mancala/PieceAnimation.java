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
	private int cupNumber;
	private int pieceAmount;
	private int yAxis;
	private int xAxis;
	private int yAxisTemp;
	private Timer timer;

	public PieceAnimation(int cupNumber, int pieceAmount) {

		this.cupNumber = cupNumber + 1;// assume cupNumber starts at 0
		this.pieceAmount = pieceAmount;

		if (this.cupNumber > 0 && this.cupNumber < 7) {
			yAxis = cupNumber * 100;
			xAxis = 100;
		} else {
			xAxis = 400;
			switch (this.cupNumber) {
			case 13:
				yAxis = 100;
				break;
			case 12:
				yAxis = 200;
				break;
			case 11:
				yAxis = 300;
				break;
			case 10:
				yAxis = 400;
				break;
			case 9:
				yAxis = 500;
				break;
			case 8:
				yAxis = 600;
				break;
			}
		}
		yAxisTemp = yAxis;
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
			if (cupNumber > 0 && cupNumber < 7) {// top row
				g.drawImage(piece, yAxis, (i * 15) + xAxis, this);
				yAxis -= 2;
			} else {// bottom row
				g.drawImage(piece, yAxis, (i * 15) + xAxis, this);
				yAxis += 2;
			}

			// if marbles reach next cup
			if (yAxis == yAxisTemp- 100) {
				yAxisTemp -= 100;
				pieceAmount--;
				cupNumber--;
			
			}
			else if (yAxis == yAxisTemp + 100){
				yAxisTemp += 100;
				pieceAmount--;
				cupNumber--;
				
			}
			// if this is the last marble stop timer
			if (pieceAmount == 0) {
				timer.stop();
				g.clearRect(0, 0, getWidth(), getHeight());
			}
		}

	}
}
