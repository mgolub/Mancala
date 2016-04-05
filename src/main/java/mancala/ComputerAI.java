package mancala;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;

//TODO Fix Logic
public class ComputerAI {

	List<Cup> cups;
	Map<Cup, Integer> cupAmounts;

	public ComputerAI(List<Cup> cups) {
		this.cups = cups;
		Collections.reverse(cups);
		cupAmounts = new HashMap<Cup, Integer>();
	}

	public int calculateBestMove() {
		List<Integer> counts = new ArrayList<Integer>();
		int move = 0;

		for (int i = 0; i < cups.size(); i++) {
			cupAmounts.put(cups.get(i), cups.get(i).getCount());
			if (cups.get(i).getCount() == i) {
				return i;
			} else if (cups.get(i).getCount() > i) {
				counts.add(cups.get(i).getCount());

				for (Map.Entry entry : cupAmounts.entrySet()) {
					if (counts.get(0).equals(entry.getValue())) {
						move = cups.indexOf((Cup) entry.getKey());
					}
				}
			}
		}

		System.out.println(counts + "b4");
		Collections.sort(counts);
		System.out.println(counts + "after");
		return move;

	}
}