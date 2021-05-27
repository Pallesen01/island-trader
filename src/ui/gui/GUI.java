package ui.gui;

import core.GameEnvironment;
import core.Island;
import core.Route;
import ui.GameUI;

/**
 * Implements the game's user interface with graphics.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class GUI implements GameUI {
	
	/**
	 * Instance of the game.
	 */
	private GameEnvironment game;
	
	/**
	 * Current screen of the GUI.
	 */
    private Screen screen;
    
    /**
     * Each type of random event.
     */
	enum RandomEvent {
		PIRATES,
		WEATHER,
		SAILORS;
	}
	
	@Override
	public void start(GameEnvironment game) {
		this.game = game;
        screen = new StartScreen(game);
        screen.show();
	}

	@Override
	public void menu() {
		if (!game.isGameOver()) {
			screen.quit();		
			if (game.checkCanTravel()) {
				screen = new MenuScreen(game);
				screen.show();
			}
			else {
				endGame("No More Routes Can Be Travelled");
			}
		}
	}
	
	@Override
	public void shipInfo() {
		screen.quit();
		screen = new ShipInfoScreen(game);
		screen.show();
	}

	@Override
	public void goods() {
		screen.quit();
		screen = new GoodsScreen(game);
		screen.show();
	}

	@Override
	public void islandInfo() {
		screen.quit();
		screen = new IslandInfoScreen(game);
		screen.show();

	}
	
	@Override
	public void displayIslandInfo(Island island) {
		screen.quit();
		screen = new DisplayIslandInfoScreen(game, island);
		screen.show();

	}

	@Override
	public void store() {
		screen.quit();
		screen = new StoreScreen(game);
		screen.show();
	}

	@Override
	public void repair() {
		screen.quit();
		screen = new RepairScreen(game);
		screen.show();
	}

	@Override
	public void travel() {
		screen.quit();
		screen = new TravelScreen(game);
		screen.show();
	}

	public void pirateEncounter(Route route) {
		screen.quit();
		screen = new PirateBattleScreen(this ,game, route);
		screen.show();
	}
	
	public void pirateResolutionScreen(Route route, int damage) {
		screen.quit();
		screen = new RandomEventScreen(game, route, damage, RandomEvent.PIRATES);
		screen.show();

	}

	@Override
	public void weatherEncounter(Route route) {
		screen.quit();
		int damage = game.weatherEvent();
		screen = new RandomEventScreen(game, route, damage, RandomEvent.WEATHER);
		screen.show();

	}
	
	@Override
	public void sailorEncounter(Route route) {
		screen.quit();
		int reward = game.sailorEvent();
		screen = new RandomEventScreen(game, route, reward, RandomEvent.SAILORS);
		screen.show();
	}

	@Override
	public void endGame(String reason) {
		game.setGameOver();
		screen.quit();
		screen = new EndGameScreen(game, reason);
		screen.show();
		
	}
}
