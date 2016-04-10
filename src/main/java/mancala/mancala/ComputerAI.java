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

	// private List<Cup> cups;
	// private Map<Cup, Integer> cupAmounts;
	private Cup[] cups;
	// private ComputerMoveListener listener;
	private PieceAnimation animation;
	private Board board;
	private boolean goAgain;

	public ComputerAI(PieceAnimation animator, Board board) {
		cups = board.getCups();
		goAgain = false;
		// ArrayList<Cup> cupsList = new ArrayList<Cup>();
		// for (int i = 0; i < cupsArray.length; i++) {
		// cupsList.add(cupsArray[i]);
		// }
		// cups = cupsList.subList(cupsList.indexOf(cupsList.get(7)),
		// cupsList.indexOf(cupsList.get(13)));
		// Collections.reverse(cups);
		// cupAmounts = new HashMap<Cup, Integer>();
		// this.listener = listener;
		this.animation = animator;
		this.board = board;
	}

	/**
	 * First Choice: Land in goal- If number of marbles in the cup is equal to
	 * the index of the cup +1, then the marbles will land in the goal. Second
	 * Choice: Drop marble in goal, but move the least amount of pieces to the
	 * other side- Move the cup that has the least amount of marbles, but the
	 * quantity still >index of cup. If cup has no marbles in it, keep repeating
	 * choice 2.
	 **/
	/*
	 * @Override public void run() {
	 * 
	 * List<Integer> counts = new ArrayList<Integer>(); int move = 0; boolean
	 * returned = false;
	 * 
	 * for (int i = 0; i < cups.size(); i++) { Cup cupIndx = cups.get(i);
	 * cupAmounts.put(cupIndx, cupIndx.getCount());
	 * 
	 * if (cupIndx.getCount() == cups.indexOf(cupIndx) + 1) {
	 * listener.onMove(switchMove(i)); System.out.println("called onmove");
	 * return; } else if (cupIndx.getCount() > i && cupIndx.getCount() > 0) {
	 * counts.add(cupIndx.getCount());
	 * 
	 * for (Map.Entry entry : cupAmounts.entrySet()) { if
	 * (counts.get(0).equals(entry.getValue())) { move = cups.indexOf((Cup)
	 * entry.getKey()); } } } else if (counts.isEmpty()) { Random rand = new
	 * Random(); do { move = rand.nextInt(6);
	 * System.out.println("called random"); } while (cups.get(move).getCount()
	 * == 0); } } System.out.println(counts); int bestMove = switchMove(move);
	 * System.out.println(bestMove); listener.onMove(bestMove); }
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer bestCup = null;
		Random rand = new Random();
		// find cup that goes into goal
		for (int i = 12; i > 7; i--) {
			if (i + cups[i].getCount() == Board.GOAL2) {
				bestCup = i;
				break;
			}
		} // choose any cup

		while (bestCup == null) {
			bestCup = rand.nextInt(6) + 7;
			if(cups[bestCup].getCount() ==0){
				bestCup = null;
			}
		}
		goAgain = animation.distibute(bestCup, this.board);

	}

	public boolean goAgain() {
		return goAgain;
	}




/*
 * private int switchMove(int move) {
 * 
 * switch (move) { case 0: move = 12; break; case 1: move = 11; break; case 2:
 * move = 10; break; case 3: move = 9; break; case 4: move = 8; break; case 5:
 * move = 7; break; } return move; }
 */
}
