package mancala;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class PlayMancala {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new MancalaModule());
		GameGui frame = injector.getInstance(GameGui.class);
		frame.setVisible(true);

	}
}
