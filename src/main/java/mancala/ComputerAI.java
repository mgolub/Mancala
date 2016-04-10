package mancala;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class ComputerAI extends Thread {

	public interface ComputerMoveListener {
		public void onMove(int cupNumber);
	}

	List<Cup> cups;
	Map<Cup, Integer> cupAmounts;
	ComputerMoveListener listener;

	public ComputerAI(Cup[] cupsArray, ComputerMoveListener listener) {

		ArrayList<Cup> cupsList = new ArrayList<Cup>();
		for (int i = 0; i < cupsArray.length; i++) {
			cupsList.add(cupsArray[i]);
		}
		cups = cupsList.subList(cupsList.indexOf(cupsList.get(7)), cupsList.indexOf(cupsList.get(13)));
		Collections.reverse(cups);
		cupAmounts = new HashMap<Cup, Integer>();
		this.listener = listener;
	}

	/**
	 * First Choice: Land in goal- If number of marbles in the cup is equal to
	 * the index of the cup +1, then the marbles will land in the goal. Second
	 * Choice: Drop marble in goal, but move the least amount of pieces to the
	 * other side- Move the cup that has the least amount of marbles, but the
	 * quantity still >index of cup. If cup has no marbles in it, keep repeating
	 * choice 2.
	 **/
	@Override
	public void run() {
	
		List<Integer> counts = new ArrayList<Integer>();
		int move = 0;
		boolean returned = false;

		for (int i = 0; i < cups.size(); i++) {
			Cup cupIndx = cups.get(i);
			cupAmounts.put(cupIndx, cupIndx.getCount());

			if (cupIndx.getCount() == cups.indexOf(cupIndx) + 1) {
				listener.onMove(switchMove(i));

				System.out.println("called onmove");
				return;
			} else if (cupIndx.getCount() > i && cupIndx.getCount() > 0) {
				counts.add(cupIndx.getCount());

				for (Map.Entry entry : cupAmounts.entrySet()) {
					if (counts.get(0).equals(entry.getValue())) {
						if (entry.getValue().equals(0)) {
							break;
						}
						move = cups.indexOf((Cup) entry.getKey());
					}
				}
			}
		}
		System.out.println(counts);
		int bestMove = switchMove(move);
		System.out.println(bestMove);

		listener.onMove(bestMove);
	}

	private int switchMove(int move) {

		switch (move) {
		case 0:
			move = 12;
			break;
		case 1:
			move = 11;
			break;
		case 2:
			move = 10;
			break;
		case 3:
			move = 9;
			break;
		case 4:
			move = 8;
			break;
		case 5:
			move = 7;
			break;
		}
		return move;
	}
}