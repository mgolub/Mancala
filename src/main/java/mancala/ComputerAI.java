package mancala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO Fix Logic
public class ComputerAI {

	List<Cup> cups;

	public ComputerAI(List<Cup> cups) {
		this.cups = cups;
	}

	public int calculateBestMove() {
		List<Integer> counts = new ArrayList<Integer>();

		for (int j = 0; j < cups.size(); j++) {
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
		return (int) counts.get(0);

	}
}