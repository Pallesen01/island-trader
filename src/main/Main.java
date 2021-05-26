package main;

import core.GameEnvironment;
import ui.GameUI;
import ui.TextUI;
import ui.gui.GUI;

/**
 * This class sets some starting parameters and starts the game.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class Main {
	
	/**
	 * Whether or not the game will use the command line UI.
	 */
	private static final boolean TEXT_MODE = false;
	
	/**
	 * ArrayList index of the starting island.
	 */
	private static final int STARTING_ISLAND_INDEX = 0;
	
	/**
	 * Amount of gold the player starts with.
	 */
	private static final int STARTING_GOLD = 250;
	
	/**
	 * Creates an instance of the game with a command line UI or GUI.
	 * @param args arguments passed in when running main in terminal
	 */
	public static void main(String[] args) {
		if (TEXT_MODE) {
			GameUI ui = new TextUI();
			GameEnvironment game = new GameEnvironment(ui, STARTING_ISLAND_INDEX, STARTING_GOLD);
		} else {
			GameUI ui = new GUI();
			GameEnvironment game = new GameEnvironment(ui, STARTING_ISLAND_INDEX, STARTING_GOLD);
		}
	}
}
