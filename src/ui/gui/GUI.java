package ui.gui;

import java.util.ArrayList;

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
		if (!game.isGameOver()) {
			screen.quit();
			// Check that it is possible to travel at least one route
			ArrayList<Route> routes = game.getIsland().getRoutes();
			int goldAfterRepair = game.getGold() - game.getShip().getRepairCost();
			boolean canTravel = false;
			for (Route route: routes) {
				int days = route.getDays(game.getShip().getSpeed());
				if (days <= game.getDaysLeft() && game.getTravelCost(route) <= goldAfterRepair ) {
					canTravel = true;
					break;
				}
			}
			if (canTravel) {
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
		int damage = game.pirateEvent();
		screen = new RandomEventScreen(game, route, damage, RandomEvent.PIRATES);
		screen.show();
	}

	public void weatherEncounter(Route route) {
		screen.quit();
		int damage = game.weatherEvent();
		screen = new RandomEventScreen(game, route, damage, RandomEvent.WEATHER);
		screen.show();

	}

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
