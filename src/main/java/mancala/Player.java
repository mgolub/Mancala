package mancala;

public class Player {

	private String name;
	private int wins;
	
	public Player(String name){
		this.name = name;
		this.wins = 0;
	}

	public int getWins() {
		return wins;
	}

	public void addWin() {
		this.wins++;
	}

	public String getName() {
		return name;
	}

}
