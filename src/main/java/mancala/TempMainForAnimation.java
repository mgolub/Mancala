package mancala;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TempMainForAnimation {

	public static void main(String[] args) {
		JComponent animation = new PieceAnimation(13, 4);
		JFrame app = new JFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(animation);
		app.setSize(1000, 1000);
		app.setVisible(true);

	}

}
