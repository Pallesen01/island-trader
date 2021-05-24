package ui.gui;

import core.GameEnvironment;
import core.Island;
import core.Route;
import ui.GameUI;

public class GUI implements GameUI {

	private GameEnvironment game;
    private Screen screen;
    
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
		screen.quit();
		screen = new MenuScreen(game);
		screen.show();
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
		screen = new RandomEventScreen(game, route, RandomEvent.PIRATES);
		screen.show();
	}

	public void weatherEncounter(Route route) {
		screen.quit();
		screen = new RandomEventScreen(game, route, RandomEvent.WEATHER);
		screen.show();

	}

	public void sailorsEncounter(Route route) {
		screen.quit();
		screen = new RandomEventScreen(game, route, RandomEvent.SAILORS);
		screen.show();
	}

}
