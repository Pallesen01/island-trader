package core;
import java.util.ArrayList;
import java.util.Scanner;

import ui.GameUI;

/**
 * This class handles the man logic of the game and runs it.
 * @author Dillon Pike, Daniel Pallesen
 * @version 6 May 2021
 */
public class GameEnvironment {
	
	final ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
	final ArrayList<Item> items = ObjectsListGenerator.generateItem();
	final ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
	final ArrayList<Island> islands = ObjectsListGenerator.generateIsland();
	final ArrayList<Route> routes = ObjectsListGenerator.generateRoute(islands);
	
	private GameUI ui;
	private Island island;
	private int gold;
	private String name;
	private int days;
	private Ship ship;
	
	public GameEnvironment(GameUI ui, int islandIndex, int gold) {
		this.ui = ui;
		this.island = islands.get(islandIndex);
		this.gold = gold;
		ui.start(this);
	}
	
	public void finishSetup(String name, int days, Ship ship) {
		this.name = name;
		this.days = days;
		this.ship = ship;
		ui.menu();
	}
	
	
	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	/** 
	 * Returns the number of days left.
	 * @return number of days left
	 */
	public int getDays() {
		return days;
	}
	
	/**
	 * Returns the current island.
	 * @return current island
	 */
	public Island getIsland() {
		return island;
	}
	
	/**
	 * Returns the store on the current island.
	 * @return current store
	 */
	public Store getStore() {
		return island.getStore();
	}
	
	/**
	 * Returns the player's gold.
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}
	
	public boolean buyItem(Item item) {
		boolean bought = false;
		if (gold >= item.getPrice() && ship.addCargo(item)) {
			gold -= item.getPrice();
			bought = true;
		}
		return bought;
	}
	
	public boolean sellItem(Item item) {
		boolean sold = false;
		if (ship.removeCargo(item)) {
			gold += item.getPrice();
			sold = true;
		}
		return sold;
	}

	/** 
	 * Decreases the number of days by the days of the route, decreases gold by the crew's wages, 
	 * and changes island to the destination of the route.
	 * @param route the route to travel on
	 */
	public void travelRoute(Route route) {
		this.days -= route.getDays(ship.getSpeed());
		this.gold -= days * ship.getCrew();
		Island[] islands = route.getIslands();
		if (islands[0] != island) {
			island = islands[0];
		} else {
			island = islands[1];
		}
	}
}
