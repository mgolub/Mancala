package mancala;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RulesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JButton button;

	public RulesFrame() {
		setTitle("Rules");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);

		button = new JButton("Got It!");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		textArea = new JTextArea();
		setText();
		add(textArea, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);

	}

	private void setText() {
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);

		StringBuilder builder = new StringBuilder();
		builder.append("Here's How To Play:");
		builder.append(
				"\n\n1.  The Mancala 'board' is made up of two rows of six holes, or pits, each. If you don't have a Mancala board handy, an empty egg carton can work.");
		builder.append(
				"\n\n2.  Four pieces -- marbles or stones -- are placed in each of the 12 holes. The color of the pieces is irrelevant.");
		builder.append(
				"\n\n3.  Each player has a 'store' to the right side of the Mancala board. (Cereal bowls work well for this purpose if you're using an egg carton.");
		builder.append(
				"\n\n4.  The game begins with one player picking up all of the pieces in any one of the holes on his side.");
		builder.append(
				"\n\n5.  Moving counter-clockwise, the player deposits one of the stones in each hole until the stones run out.");
		builder.append(
				"\n\n6.  If you run into your own store, deposit one piece in it. If you run into your opponent's store, skip it.");
		builder.append("\n\n7.  If the last piece you drop is in your own store, you get a free turn.");
		builder.append(
				"\n\n8.If the last piece you drop is in an empty hole on your side, you capture that piece and any pieces in the hole directly opposite.");
		builder.append("\n\n9.  Always place all captured pieces in your store.");
		builder.append("\n\n10.  The game ends when all six spaces on one side of the Mancala board are empty.");
		builder.append(
				"\n\n11.  The player who still has pieces on his side of the board when the game ends captures all of those pieces.");
		builder.append("\n\n12.  Count all the pieces in each store. The winner is the player with the most pieces.");
		builder.append("\n\n\n\nTips:");
		builder.append(
				"\n\nPlanning ahead is essential to victory in board games like Mancala. Try to plan two or three moves into the future.");
		textArea.setText(builder.toString());

	}

}
