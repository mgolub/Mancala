package mancala;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class PieceAnimation extends JComponent{
	
	
	private static final long serialVersionUID = 1L;
	int cupNumber;
	int pieceAmount;
	int xAxis;
	Timer timer;
	public PieceAnimation(int cupNumber, int pieceAmount){
		
		this.cupNumber = cupNumber;
		this.pieceAmount = pieceAmount;
		xAxis = cupNumber * 100;
		repaint();
		ActionListener animater = new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}
			
		};
		timer = new Timer(50, animater);
		timer.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		 
		for (int i = 0; i < pieceAmount; i++){
			Image piece = new ImageIcon(getClass().getResource("/BlueMarble.png")).getImage();
			g.drawImage(piece, xAxis, (i* 15)+100, this);
			
			//if marbles reach next cup
			if (xAxis == (cupNumber-1)*100){
				//if this is the last marble stop timer
				if (pieceAmount == 0){
				timer.stop();
				}
				else{
					pieceAmount--;
					cupNumber--;
				}
			}
			xAxis-=2;
			
		}
		
	}

}
