package mancala;

public class Goal extends Cup {

	public Goal() {
		super();
	}

	@Override
	public void reset() {
		count = 0;
	}

	public void addToGoal(int amount) {
		count = count + amount;
	}

}
