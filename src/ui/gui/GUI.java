package ui.gui;

import java.util.ArrayList;

import core.GameEnvironment;
import core.Island;
import core.Item;
import core.Route;
import core.Ship;
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

	/**
	 *  Opens the menu screen if it is possible for the player to travel, otherwise end game.
	 */
	@Override
	public void menu() {
		if (!game.isGameOver()) {
			screen.quit();		
			if (checkCanTravel()) {
				screen = new MenuScreen(game);
				screen.show();
			}
			else {
				endGame("No More Routes Can Be Travelled");
			}
		}
	}
	
	/**
	 * Opens the ship info screen.
	 */
	@Override
	public void shipInfo() {
		screen.quit();
		screen = new ShipInfoScreen(game);
		screen.show();
	}

	/**
	 * Opens the ship goods screen.
	 */
	@Override
	public void goods() {
		screen.quit();
		screen = new GoodsScreen(game);
		screen.show();
	}

	/**
	 * Opens the selection window for island info.
	 */
	@Override
	public void islandInfo() {
		screen.quit();
		screen = new IslandInfoScreen(game);
		screen.show();

	}
	
	/**
	 * Displays island info screen for given island.
	 */
	@Override
	public void displayIslandInfo(Island island) {
		screen.quit();
		screen = new DisplayIslandInfoScreen(game, island);
		screen.show();

	}

	/**
	 * Displays store screen for current island.
	 */
	@Override
	public void store() {
		screen.quit();
		screen = new StoreScreen(game);
		screen.show();
	}

	/**
	 * Displays ship repair screen.
	 */
	@Override
	public void repair() {
		screen.quit();
		screen = new RepairScreen(game);
		screen.show();
	}

	/**
	 * Displays travel screen.
	 */
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
	
	/**
	 * Checks if it is possible for the ship to travel along any routes.
	 * @return returns true if it is possible to travel, else false.
	 */
	private boolean checkCanTravel() {
		
		ArrayList<Route> routes = game.getIsland().getRoutes();
		
		// Calculate gold that can be made by selling cargo
		ArrayList<Item> storeSell = game.getIsland().getStore().getSells();
		int storeGold = 0;
		for (Item item1: storeSell) {
			for (Item item2: game.getShip().getCargo()) {
				if (item1.getName().equals(item2.getName())){
					storeGold += item1.getPrice();
				}
			}
		}
		// Check that it is possible to travel at least one route with max gold after selling items and repairing ship
		int goldAfterAction = game.getGold() - game.getShip().getRepairCost() + storeGold;
		boolean canTravel = false;
		for (Route route: routes) {
			int days = route.getDays(game.getShip().getSpeed());
			if (days <= game.getDaysLeft() && game.getTravelCost(route) <= goldAfterAction ) {
				canTravel = true;
				break;	
			}
		}
		return canTravel;
	}

}
