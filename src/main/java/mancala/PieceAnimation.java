package mancala;

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
			
			// if marbles reach next cup to the left(assume each cup is 100 away) 
			if (yAxis == yAxisTemp-100){
				yAxisTemp -=100;
				pieceAmount--;
				cupNumber--;
			}//if marbles reach next cup to the right (assume each cup is 100 away)
			else if (yAxis == yAxisTemp+100){
				yAxisTemp+=100;
				pieceAmount--;
				cupNumber--;
			}
				
			// if this is the last marble stop timer
			if (pieceAmount == 0) {
				timer.stop();
				g.clearRect(0, 0, getWidth(), getHeight());
			}
			
			//if its not the last marble need to move marble(s) 
			//if in cup 2-6 or 9-13 just move left/right
			if (cupNumber > 1 && cupNumber < 7){
				yAxis--;
			}
			else if (cupNumber > 8 && cupNumber < 14){
				yAxis++;
			}
			//if in goal1 move upper left
			else if (cupNumber == 7){
				yAxis--;
				xAxis--;
			}
			//if in goal2 move lower right
			else if (cupNumber == 14){
				yAxis++;
				xAxis++;
			}
			//if in cup one move lower left
			else if (cupNumber == 1){
				yAxis--;
				xAxis++;
			}
			//if in cup eight move upper right
			else if (cupNumber == 8){
				yAxis++;
				xAxis--;
			}
		}
		
		

	}
}
