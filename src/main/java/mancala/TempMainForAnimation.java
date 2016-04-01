package mancala;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TempMainForAnimation {

	private static CupComponent[] cupComponents;
	public static void main(String[] args) {
		
		cupComponents = new CupComponent[14];
		cupComponents[0] = new CupComponent(100,200);
		cupComponents[1] = new CupComponent(100,300);
		cupComponents[2] = new CupComponent(100,400);
		cupComponents[3] = new CupComponent(100,500);
		cupComponents[4] = new CupComponent(100,600);
		cupComponents[5] = new CupComponent(100,700);
		cupComponents[6] = new CupComponent(300,800);
		cupComponents[7] = new CupComponent(500,700);
		cupComponents[8] = new CupComponent(500,600);
		cupComponents[9] = new CupComponent(500,500);
		cupComponents[10] = new CupComponent(500,400);
		cupComponents[11] = new CupComponent(500,300);
		cupComponents[12] = new CupComponent(500,200);
		cupComponents[13] = new CupComponent(300,100);

	

		
		
		JComponent animation = new PieceAnimation(cupComponents,12, 14);
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(animation);
		app.setSize(1000, 1000);
		app.setVisible(true);

	}

}
