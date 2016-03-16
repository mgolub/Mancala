package mancala;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class NameFrame extends JFrame {

	private String name1, name2;
	private JLabel label1, label2, background;
	private JTextField field1, field2;
	private JButton okay;
	private JPanel panel, player1, player2;

	public NameFrame() {
		setTitle("Add Names");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setBackground(Color.yellow);

		background = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/background4.jpg")).getImage()
				.getScaledInstance(getWidth(), getHeight(), 10)));

		panel = new JPanel();
		player1 = new JPanel();
		player2 = new JPanel();
		label1 = new JLabel("Player 1");
		label2 = new JLabel("Player 2");
		field1 = new JTextField();
		field2 = new JTextField();
		okay = new JButton();

		addListener();
		addComponents();

	}

	private void addComponents() {
		background.setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(40, 100));

		player1.setOpaque(true);
		player1.setBackground(Color.black);
		player2.setOpaque(true);
		player2.setBackground(Color.black);
		player1.setLayout(new FlowLayout());
		player2.setLayout(new FlowLayout());

		Font font1 = new Font("Calibri", Font.BOLD, 40);
		Font font2 = new Font("Calibri", Font.BOLD, 30);
		Font font3 = new Font("Rockwell Extra Bold", Font.PLAIN, 50);

		label1.setForeground(Color.yellow);
		label2.setForeground(Color.yellow);
		label1.setFont(font1);
		label2.setFont(font1);
		label1.setSize(60, 20);
		label2.setSize(60, 20);

		field1.setBackground(Color.darkGray);
		field2.setBackground(Color.darkGray);
		field1.setForeground(Color.orange);
		field2.setForeground(Color.orange);
		field1.setFont(font2);
		field2.setFont(font2);
		field1.setColumns(10);
		field2.setColumns(10);

		okay.setIcon(new ImageIcon(formatIcon(80, 80, getClass().getResource("/yellow.png"))));
		okay.setBackground(Color.black);
		okay.setBorder(new LineBorder(Color.yellow, 1, true));
		
		player1.add(label1);
		player1.add(field1);
		player2.add(label2);
		player2.add(field2);
		panel.add(player1);
		panel.add(player2);
		panel.add(okay);
		background.add(panel, BorderLayout.PAGE_END);
		add(background);
	}

	public Image formatIcon(int width, Integer height, URL image) {
		ImageIcon icon = new ImageIcon(image);
		Image img = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return img;
	}

	private void addListener() {
		okay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				name1 = field1.getText().trim();
				name2 = field2.getText().trim();
				if (name1.length() == 0 || name2.length() == 0) {
					JOptionPane.showMessageDialog(null, "You must enter a name for both players!!", "WARNING!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				dispose();
				GUI2 gui = new GUI2(name1, name2);
				gui.setVisible(true);
			}

		});

	}

	public static void main(String[] args) {
		new NameFrame().setVisible(true);
	}

}
