package mancala;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TempMainForAnimation {

	private static Cup[] cupComponents;
	public static void main(String[] args) {
		
		cupComponents = new Cup[14];
		cupComponents[0] = new Cup(100,200);
		cupComponents[1] = new Cup(100,300);
		cupComponents[2] = new Cup(100,400);
		cupComponents[3] = new Cup(100,500);
		cupComponents[4] = new Cup(100,600);
		cupComponents[5] = new Cup(100,700);
		cupComponents[6] = new Goal(300,800);
		cupComponents[7] = new Cup(500,700);
		cupComponents[8] = new Cup(500,600);
		cupComponents[9] = new Cup(500,500);
		cupComponents[10] = new Cup(500,400);
		cupComponents[11] = new Cup(500,300);
		cupComponents[12] = new Cup(500,200);
		cupComponents[13] = new Goal(300,100);

	

		
		
		JComponent animation = new PieceAnimation(cupComponents,12, 4);
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(animation);
		app.setSize(1000, 1000);
		app.setVisible(true);

	}

}
