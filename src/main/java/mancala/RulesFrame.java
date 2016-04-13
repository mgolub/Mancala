package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class RulesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
	private JButton button;

	public RulesFrame() {
		setTitle("HOW TO PLAY!");
		setSize(1100, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);

		textArea = new JTextArea(
				"*  Four pieces are placed in each of the 12 holes.\n*  Each player has a goal to the right side of the Mancala board."
						+ "\n*  The game begins with one player selecting a hole on his side to pick up all of the pieces in that hole. \n*  Moving counter-clockwise, the computer deposits one of the stones in each hole until the stones run out.\n*  If you run into your own goal, the computer deposits one piece in it. \n*  If you run into your opponent's goal, the computer skips it.\n*  If the last piece you drop is in your own goal, you get a free turn.\n*  If the last piece you drop is in an empty hole on your side, you capture that piece and any pieces in the hole directly opposite.\n*  All captured pieces are placed in your goal.\n*  The game ends when all six spaces on one side of the Mancala board are empty.\n*  The player who still has pieces on his side of the board when the game ends captures all of those pieces.\n*  All the pieces in each goal is counted. The winner is the player with the most pieces.	\n\n*** Try to plan two or three moves into the future.");
		button = new JButton("Got It?");

		// setText();
		addListener();
		addFormat();
	}

	private void addFormat() {

		Font font1 = new Font("Calibri", Font.BOLD, 20);
		Font font2 = new Font("Rockwell Extra Bold", Font.PLAIN, 30);

		textArea.setFont(font1);
		textArea.setForeground(Color.GREEN);
		textArea.setSize(getWidth(), getHeight());

		button.setFont(font2);
		button.setForeground(Color.GREEN);
		button.setBackground(Color.black);
		button.setBorder(new LineBorder(Color.orange, 3, true));

		add(textArea, BorderLayout.NORTH);
		add(button, BorderLayout.LINE_START);
	}

	private void addListener() {
		button.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void setText() {
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);

	}

	/*
	 * public static void main(String[] args) { RulesFrame frame = new
	 * RulesFrame(); frame.setVisible(true); }
	 */
}
