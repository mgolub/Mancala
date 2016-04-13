package mancala;

import java.util.Properties;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class MancalaModule implements Module {

	private Binder binder;
	

	public void configure(Binder binder) {
		this.binder = binder;
	}

}
