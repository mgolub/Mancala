package mancala;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TempMainForAnimation {

	private static CupComponent[] cupComponents;
	public static void main(String[] args) {
		
		cupComponents = new CupComponent[13];
			for (int i = 0; i < Board.GOAL1; i++) {
				cupComponents[i] = new CupComponent(647, 1010);// For testing the
																// x,y
			//	cupPanel1.add(cupComponents[i]);
			//	cupComponents[i].setToolTipText(Integer.toString(board.getContent(i)));
			}
			for (int i = 12; i >= 7; i--) {
				cupComponents[i] = new CupComponent(639, 1560); // For testing the
																// x,y
				//cupPanel2.add(cupComponents[i]);
			//	cupComponents[i].setToolTipText(Integer.toString(board.getContent(i)));
			}

			//cupComponents[Board.GOAL1] = new GoalComponent(0, 0);
			//cupComponents[Board.GOAL1].setToolTipText(Integer.toString(board.getContent(Board.GOAL1)));
			//cupComponents[Board.GOAL2] = new GoalComponent(0, 0);
			//cupComponents[Board.GOAL2].setToolTipText(Integer.toString(board.getContent(Board.GOAL2)));

	

		
		
		JComponent animation = new PieceAnimation(cupComponents,5, 34);
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(animation);
		app.setSize(1000, 1000);
		app.setVisible(true);

	}

}
