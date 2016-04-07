package mancala;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PieceAnimation extends JPanel {

	private static final long serialVersionUID = 1L;
	private int cupNumber;
	private int pieceAmount;
	private int yAxis;
	private int xAxis;
	private int yAxisTemp;
	private Timer timer;
	private Image piece;

	public PieceAnimation() {
	
	}

	public void animate(int cupNumberIndex, int pieceAmount){	
		piece = new ImageIcon(getClass().getResource("/BlueMarble.png"))
				.getImage();
		//cupComponents[cupNumber].cupsMarbles();
		
		this.cupNumber = cupNumberIndex + 1;// assume cupNumber starts at 0
		this.pieceAmount = pieceAmount;

		switch(this.cupNumber){
		case 1:
			cupNumber= 13;
			xAxis = 215;
			yAxis = 423;
			break;
		case 2:
			cupNumber = 12;
			xAxis = 328;
			yAxis = 423;
			break;
		case 3:
			cupNumber = 11;
			xAxis = 441;
			yAxis = 423;
			break;
		case 4:
			cupNumber = 10;
			xAxis = 554;
			yAxis = 423;
			break;
		case 5:
			cupNumber = 9;
			xAxis = 667;
			yAxis = 423;
			break;
		case 6:
			cupNumber = 8;
			xAxis = 780;
			yAxis = 423;
			break;
		case 7: //goal
			cupNumber = 7;
			xAxis=880;
		case 8:
			cupNumber = 6;
			xAxis = 780;
			yAxis = 290;
			break;
		case 9:
			cupNumber = 5;
			xAxis = 667;
			yAxis = 290;
			break;
		case 10:
			cupNumber = 4;
			xAxis = 554;
			yAxis = 290;
			break;
		case 11:
			cupNumber = 3;
			xAxis = 441;
			yAxis = 290;
			break;
		case 12:
			cupNumber = 2;
			xAxis = 328;
			yAxis = 290;
			break;
		case 13:
			cupNumber = 1;
			xAxis = 215;
			yAxis = 290;
			break;
		case 14://goal 2
			cupNumber = 14;
			xAxis = 115;
		}
		
		yAxisTemp = yAxis;
		repaint();

		ActionListener animater = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}

		};
		timer = new Timer(2, animater);
		timer.start();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < pieceAmount; i++) {

			g.drawImage(piece, yAxis, (i * 15) + xAxis, this);
			setOpaque(false);

			if (cupNumber == 1 && yAxis == yAxisTemp + 100){
				pieceAmount--;
				yAxisTemp -=100;
				dropPieceSound();
			}
			// if marbles reach next cup to the left(assume each cup is 100
			// away)
			if (yAxis == yAxisTemp - 113) {
				yAxisTemp -= 113;
				pieceAmount--;
				cupNumber--;
				dropPieceSound();

			} // if marbles reach next cup to the right (assume each cup is 100
				// away)
			else if (yAxis == yAxisTemp + 113) {
				yAxisTemp += 113;
				pieceAmount--;
				cupNumber--;
				dropPieceSound();

			}

			// if this is the last marble stop timer
			if (pieceAmount == 0) {
				timer.stop();
				g.clearRect(0, 0, getWidth(), getHeight());
			}
		}

		// if its not the last marble need to move marble(s)
		// if in cup 2-6 or 9-13 just move left/right
		if (cupNumber > 1 && cupNumber < 7) {
			yAxis--;
		} else if (cupNumber > 8 && cupNumber < 14) {
			yAxis++;
		}
		// if in goal1 move upper left
		else if (cupNumber == 7) {
			yAxis--;
			xAxis--;
		}
		// if in goal2 move lower right
		else if (cupNumber == 14) {
			yAxis++;
			xAxis++;
			
		}
		// if in cup one move lower left
		else if (cupNumber == 1) {
			yAxis--;
			xAxis++;
			cupNumber = 14;
		}
		// if in cup eight move upper right
		else if (cupNumber == 8) {
			yAxis++;
			xAxis--;
		}
	}

	private void dropPieceSound() {

		new Thread(new Runnable() {

			public void run() {
				Applet.newAudioClip(getClass().getResource("/marbleDrop.wav")).play();
			}
		}).start();
	}

}
