package main;

import core.GameEnvironment;
import ui.GameUI;
import ui.TextUI;
import ui.gui.GUI;

public class Main {
	
	private static final boolean TEXT_MODE = false;
	private static final int STARTING_ISLAND = 0;
	private static final int STARTING_GOLD = 250;
	
	public static void main(String[] args) {
		if (TEXT_MODE) {
			GameUI ui = new TextUI();
			GameEnvironment game = new GameEnvironment(ui, STARTING_ISLAND, STARTING_GOLD);
		} else {
			GameUI ui = new GUI();
			GameEnvironment game = new GameEnvironment(ui, STARTING_ISLAND, STARTING_GOLD);
		}
	}
}
