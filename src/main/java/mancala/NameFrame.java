package mancala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.google.inject.Inject;

public class NameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public NameFrame(InsertNamesPanel playersPanel, JLabel backgroundLabel) {
		setTitle("Players");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(false);

		backgroundLabel = new JLabel(new ImageIcon(getClass()
				.getResource("/startBackground.png")));

		backgroundLabel.setLayout(new BorderLayout());

		backgroundLabel.add(playersPanel, BorderLayout.PAGE_END);
		add(backgroundLabel);

	}

	
}
