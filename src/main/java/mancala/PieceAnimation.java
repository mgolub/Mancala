package mancala;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
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
	private final int GOAL1 = 7;
	private final int GOAL2 = 14;
	private Image piece;

	public PieceAnimation(CupComponent[] cupComponents,int cupNumber, int pieceAmount) {

		piece = new ImageIcon(getClass().getResource("/BlueMarble.png")).getImage();
		this.cupNumber = cupNumber + 1;// assume cupNumber starts at 0
		this.pieceAmount = pieceAmount;
		
		xAxis = cupComponents[cupNumber].getX();
		yAxis = cupComponents[cupNumber].getY();
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
			
			g.drawImage(piece, yAxis, (i * 15)+ xAxis, this);
			
			// if marbles reach next cup
			
			//if in cup 1 to go to GOAL2
			if (yAxis == 100){
				pieceAmount--;
				cupNumber = 14;
			}
		
			//if in any cup on top row go left
			else if (yAxis == yAxisTemp - 100) {
				yAxisTemp -= 100;
				pieceAmount--;
				cupNumber--;
			
			}
			//if in any cup on bottom row go right
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
		
		//if in cup 1 go lower left to GOAL2
		if (cupNumber == 1){
			yAxis--;
			xAxis++;
		}
		//if in GOAL2 go lower right to cup 13
		else if (cupNumber == GOAL2){
			yAxis++;
			xAxis++;
		}
		//if in top row go left
		else if (cupNumber > 0 && cupNumber < GOAL1) {
			yAxis--;
		} 
		//if in bottom row go right
		else if (cupNumber > GOAL1 && cupNumber < GOAL2){
			yAxis++;
		}
		//if in GOAL1 up upper left
		else if (cupNumber == GOAL1){
			yAxis--;
			xAxis--;
		}

	}
}
