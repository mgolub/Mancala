package mancala;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TempMainForAnimation {

	private static Cup[] cupComponents;
	public static void main(String[] args) {
		
		cupComponents = new Cup[14];
		cupComponents[0] = new Cup(215,290);
		cupComponents[1] = new Cup(328,290);
		cupComponents[2] = new Cup(441,290);
		cupComponents[3] = new Cup(554,290);
		cupComponents[4] = new Cup(667,290);
		cupComponents[5] = new Cup(780,290);
		cupComponents[6] = new Goal(300,800);
		cupComponents[7] = new Cup(215,423);
		cupComponents[8] = new Cup(328,423);
		cupComponents[9] = new Cup(441,423);
		cupComponents[10] = new Cup(554,423);
		cupComponents[11] = new Cup(667,423);
		cupComponents[12] = new Cup(780,423);
		cupComponents[13] = new Goal(300,423);

	

		
		
		JComponent animation = new PieceAnimation(cupComponents,12, 4);
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(animation);
		app.setSize(1000, 1000);
		app.setVisible(true);

	}

}
