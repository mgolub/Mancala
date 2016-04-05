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

		for (int j = 0; j < cups.size(); j++) {
			cupAmounts.put(cups.get(j), cups.get(j).getCount());

			if (cups.get(j).getCount() == j) {
				return j;
			}
		}

		for (int i = 0; i < cups.size(); i++) {
			if (cups.get(i).getCount() > i) {
				counts.add(cups.get(i).getCount());
			}
		}
		System.out.println(counts + "b4");
		Collections.sort(counts);
		System.out.println(counts + "after");
		return (int) cups.indexOf(counts.get(0));

	}
}