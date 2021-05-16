package main;

import core.GameEnvironment;
import ui.TextUI;

public class Main {
	private static final boolean TEXT_MODE = true;
	private static final int STARTING_ISLAND = 0;
	private static final int STARTING_GOLD = 250;
	
	public static void main(String[] args) {
		
		if (TEXT_MODE) {
			TextUI ui = new TextUI();
			GameEnvironment game = new GameEnvironment(ui, STARTING_ISLAND, STARTING_GOLD);
		}
	}
}
