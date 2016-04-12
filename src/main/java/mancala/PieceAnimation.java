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
	private Cup[] cupComponents;

	public PieceAnimation() {

	}

	public void animate(Cup[] cups, int cupNumberIndex) {
		piece = new ImageIcon(getClass().getResource("/BlueMarble.png")).getImage();
		this.cupComponents = cups;
		// cupComponents[cupNumber].cupsMarbles();
		this.pieceAmount = cupComponents[cupNumberIndex].getCount();
		this.cupNumber = cupNumberIndex;
		switch (this.cupNumber) {
		case 0:
			yAxis = 216;
			xAxis = 460;
			break;
		case 1:
			yAxis = 328;
			xAxis = 460;
			break;
		case 2:
			yAxis = 442;
			xAxis = 460;
			break;
		case 3:
			yAxis = 554;
			xAxis = 460;
			break;
		case 4:
			yAxis = 668;
			xAxis = 460;
			break;
		case 5:
			yAxis = 780;
			xAxis = 460;
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
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < pieceAmount; i++) {
			g.drawImage(piece, yAxis, (i * 15) + xAxis, this);
			setOpaque(false);

			// if marbles reach next cup to the left(assume each cup is 100
			// away)
			if (yAxis == yAxisTemp - 112) {
				yAxisTemp -= 112;
				pieceAmount--;
				cupNumber++;
				if (cupNumber == 14){
					cupNumber = 0;
				}
				dropPieceSound();
				if (cupNumber != 6 && cupNumber != 13) {
					(this.cupComponents[cupNumber]).addPiece(piece);
				} else {
					((Goal) this.cupComponents[cupNumber]).addPiece(piece);
				}
				

			} // if marbles reach next cup to the right (assume each cup is 100
				// away)
			else if (yAxis == yAxisTemp + 112) {
				yAxisTemp += 112;
				pieceAmount--;
				cupNumber++;
				if (cupNumber == 14){
					cupNumber = 0;
				}
				dropPieceSound();
				if (cupNumber != 6 && cupNumber != 13) {
					(this.cupComponents[cupNumber]).addPiece(piece);
				} else {
					((Goal) this.cupComponents[cupNumber]).addPiece(piece);
				}
			}

			// if this is the last marble stop timer
			if (pieceAmount == 0) {
				timer.stop();
			}
		}
		// if its not the last marble need to move marble(s)

		// if in goal1 move upper left
		if (cupNumber == 6) {
			yAxis -= 2;
			xAxis --;//-= 2;
		}
		// if in goal2 move lower right
		else if (cupNumber == 13) {
			yAxis += 2;
			xAxis ++;//+= 2;
		}
		// if in cup one move lower left
		else if (cupNumber == 12) {
			yAxis -= 2;
			xAxis ++;//+= 2;
		}
		// if in cup eight move upper right
		else if (cupNumber == 5) {
			yAxis += 2;
			xAxis --;//-= 2;
		} else if (cupNumber < 12 && cupNumber > 6) {
			yAxis -= 2;
		} else if (cupNumber >= 0 && cupNumber > 5) {
			yAxis += 2;
		}

	}

	private void dropPieceSound() {

		new Thread(new Runnable() {

			public void run() {
				Applet.newAudioClip(getClass().getResource("/marbleDrop.wav")).play();
			}
		}).start();
	}

	/*public boolean distibute(int start, Board board) {

		Image[] pieces = this.cupComponents[start].removePieces();

		for (int i = 0, position = start + 1; i < pieces.length; i++, position++) {
			if (position != Board.GOAL1 && position != Board.GOAL2) {
				this.cupComponents[position].addPiece(pieces[i]);
			} else {
				if (board.currentPlayersGoal(position)) {
					board.setPiecesWon(board.getPiecesWon() + 1);
					((Goal) this.cupComponents[position]).addPiece(pieces[i]);
				} else {
					i--;
				}
				// board[position].repaint();
			}

			start = position;
			if (position == this.cupComponents.length - 1) {
				position = -1;// 0 after increment
			}
		} // pieces done being distributed
		return board.checkTurn();
	}*/
}
