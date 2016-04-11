
package mancala;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.inject.Inject;

public class NameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@Inject
	public NameFrame(InsertNamesPanel playersPanel, JLabel backgroundLabel) {
		setTitle("Players");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(false);

		StartScreenPanel panel = null;
		try {
			panel = new StartScreenPanel(getClass().getResource("/MancalaStartScreen.png").openStream());
		} catch (IOException e) {
		}

		// backgroundLabel = new JLabel(new
		// ImageIcon(getClass().getResource("/MancalaStartScreen.png")));
		// backgroundLabel.setLayout(new BorderLayout());
		
		add(panel, BorderLayout.CENTER);
		add(playersPanel, BorderLayout.PAGE_END);
	}
}