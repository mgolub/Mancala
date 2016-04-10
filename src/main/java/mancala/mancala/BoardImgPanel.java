package mancala;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardImgPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;

	public BoardImgPanel() {
image = new ImageIcon(getClass().getResource("/MancalaBoardFinal.jpg")).getImage();
		//try {
		//	image = ImageIO.read(getClass().getResourceAsStream("/MancalaBoardFinal.jpg"));
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public Image getImage() {
		return this.image;
	}
}