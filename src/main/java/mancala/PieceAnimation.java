package mancala;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.inject.Singleton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.google.inject.Inject;

@Singleton
public class PieceAnimation extends JPanel {

	private static final long serialVersionUID = 1L;
	private int cupNumber;
	private int pieceAmount;
	private int yAxis;
	private int xAxis;
	private int yAxisTemp;
	private Timer timer;
	private Cup[] cupComponents;
	private Image[] pieces;

	@Inject
	public PieceAnimation() {

	}

	public boolean animate(Cup[] cups, int cupNumberIndex, Board board) {
		this.cupComponents = cups;
		this.pieceAmount = cups[cupNumberIndex].getCount();
		this.cupNumber = cupNumberIndex;
		pieces = cupComponents[cupNumber].removePieces();

		switch (this.cupNumber) {
		case 0:
			yAxis = 216;
			xAxis = 450;
			break;
		case 1:
			yAxis = 328;
			xAxis = 450;
			break;
		case 2:
			yAxis = 442;
			xAxis = 450;
			break;
		case 3:
			yAxis = 554;
			xAxis = 450;
			break;
		case 4:
			yAxis = 668;
			xAxis = 450;
			break;
		case 5:
			yAxis = 780;
			xAxis = 450;
			break;
		case 6: // goal
			xAxis = 880;
		case 7:
			yAxis = 780;
			xAxis = 330;
			break;
		case 8:
			yAxis = 668;
			xAxis = 330;
			break;
		case 9:
			yAxis = 554;
			xAxis = 330;
			break;
		case 10:
			yAxis = 442;
			xAxis = 330;
			break;
		case 11:
			yAxis = 328;
			xAxis = 330;
			break;
		case 12:
			yAxis = 216;
			xAxis = 330;
			break;
		case 13:// goal 2
			xAxis = 116;
		}

		yAxisTemp = yAxis;
		repaint();

		ActionListener animater = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		};
		timer = new Timer(2, animater);
		timer.start();
		int landGoal = cupNumber + pieceAmount;
		while (landGoal > 13) {
			landGoal -= 13;
		}
		if (landGoal == Board.GOAL1 || landGoal == Board.GOAL2) {
			return true;
		}
		board.turnFinished();
		return board.checkEmptyCup(landGoal);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < pieceAmount; i++) {
			// if(pieces.length ==0){
			// return;
			// }
			// keep this line you need it for animation i commented it out for
			// testing
			g.drawImage(pieces[i], yAxis, (i * 15) + xAxis, this);
			setOpaque(false);

			// if marbles reach next cup to the left(assume each cup is 100
			// away)
			if (yAxis == yAxisTemp - 112) {
				yAxisTemp -= 112;
				pieceAmount--;
				cupNumber++;
				if (cupNumber == 14) {
					cupNumber = 0;
				}
				dropPieceSound();
				if (cupNumber != 6 && cupNumber != 13) {
					(this.cupComponents[cupNumber])
							.addPiece(pieces[pieceAmount]);
				} else {
					((Goal) this.cupComponents[cupNumber])
							.addPiece(pieces[pieceAmount]);
				}

			} // if marbles reach next cup to the right (assume each cup is 100
				// away)
			else if (yAxis == yAxisTemp + 112) {
				yAxisTemp += 112;
				pieceAmount--;
				cupNumber++;
				if (cupNumber == 14) {
					cupNumber = 0;
				}
				dropPieceSound();
				if (cupNumber != 6 && cupNumber != 13) {
					(this.cupComponents[cupNumber])
							.addPiece(pieces[pieceAmount]);
				} else {
					((Goal) this.cupComponents[cupNumber])
							.addPiece(pieces[pieceAmount]);
				}
			}

			// if this is the last marble stop timer
			if (pieceAmount == 0) {
				timer.stop();

			}
		}
		// if its not the last marble need to move marble(s)
		// if in cup 2-6 or 9-13 just move left/right

		// if in goal1 move upper left
		if (cupNumber == 6) {
			yAxis -= 2;
		}
		// if in goal2 move lower right
		else if (cupNumber == 13) {
			yAxis += 2;
			xAxis++;// += 2;
		}
		// if in cup one move lower left
		else if (cupNumber == 12) {
			yAxis -= 2;
			xAxis++;// += 2;
		}
		// if in cup eight move upper right
		else if (cupNumber == 5) {
			yAxis += 2;
			xAxis--;// -= 2;
		} else if (cupNumber < 12 && cupNumber > 6) {
			yAxis -= 2;
		} else if (cupNumber >= 0 && cupNumber < 5) {
			yAxis += 2;
		}

	}

	private void dropPieceSound() {

		new Thread(new Runnable() {

			public void run() {
				Applet.newAudioClip(getClass().getResource("/marbleDrop.wav"))
						.play();
			}
		}).start();
	}

}
