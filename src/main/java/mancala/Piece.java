package mancala;

public class Piece {

	private String imageSource;

	public Piece(String color) {
		this.imageSource = color + ".jpg";
	}

	public String getImage() {
		return imageSource;
	}
}
