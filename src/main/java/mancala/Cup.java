package mancala;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Cup extends JComponent {

	private static final long serialVersionUID = 1L;
	protected Image piece;
	//create class called marbleBag
	protected ArrayList<Image> possibleMarbles;
	protected ArrayList<Image> cupsMarbles;
	protected int count;
	protected Random random;
	protected int x;
	protected int y;

	public Cup(int x, int y) {
		setPreferredSize(new Dimension(110, 250));
		possibleMarbles = new ArrayList<Image>();
		cupsMarbles = new ArrayList<Image>();
		count = 4;
		random = new Random();
		randomMarbles();
		cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
		cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
		cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
		cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));

		this.x = x;
		this.y = y;
	}

	private void randomMarbles() {

		possibleMarbles.add(new ImageIcon(getClass().getResource("/BlueMarble.png"))
				.getImage());
		possibleMarbles.add(new ImageIcon(getClass().getResource("/YellowMarble.png"))
				.getImage());
		possibleMarbles.add(new ImageIcon(getClass().getResource("/PinkMarble.png"))
				.getImage());
		possibleMarbles.add(new ImageIcon(getClass().getResource("/GreenMarble.png"))
				.getImage());
	}

	public void setCount(int count) {
		this.count = count;
		repaint();
	}

	public void emptyCup() {
		count = 4;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		int piecesDrawn = 0, piecesPerRow = 4, location = 0, rows = 1;
		for (int i = 0; i < count; i++) {
			if (piecesDrawn % piecesPerRow == 0) {
				rows++;
				location = 0;
			}
			g.drawImage(piece = this.cupsMarbles.get(piecesDrawn), rows * 10,
					location * 15, this);
			piecesDrawn++;
			location++;

		}

	}

	public void addPiece(Image piece) {
		count++;
		//cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
		cupsMarbles.add(piece);

	}

	public int getCount() {
		return count;
	}

	public Image[] removePieces() {
		Image[] pieces = new Image[count];
		int upTo =0;
		for(Image marble: cupsMarbles){
			pieces[upTo++] = marble;
		}
		count = 0;
		cupsMarbles.clear();
		return pieces;
	}

	public void reset() {
		count = 4;
		if(cupsMarbles.size() != 4){
			cupsMarbles.clear();
			cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
			cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
			cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
			cupsMarbles.add(possibleMarbles.get(random.nextInt(4)));
		}
		
		
		

	}
}