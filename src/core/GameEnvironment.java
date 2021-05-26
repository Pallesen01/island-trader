package core;
import java.util.ArrayList;
import java.util.Random;

import ui.GameUI;
import ui.TextUI;

/**
 * This class handles the main game logic and keeps track of the state.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class GameEnvironment {
	
	/**
	 * Multiples the wage value by the modifier 
	 */
	private final double WAGE_MODIFIER = 0.5;
	
	/**
	 * Minimum reward given by lost sailors.
	 */
	private final int MIN_SAILOR_REWARD = 20;
	
	/**
	 * Maximum reward given by lost sailors.
	 */
	private final int MAX_SAILOR_REWARD = 60;
	
	/**
	 * Minimum damage inflicted by weather.
	 */
	private final int MIN_WEATHER_DAMAGE = 10;
	
	/**
	 * Maximum damage inflicted by weather.
	 */
	private final int MAX_WEATHER_DAMAGE = 40;
	
	/**
	 * Minimum value of cargo needed for pirates to be satisfied.
	 */
	private final int PIRATE_CARGO_THRESHOLD = 100;
	
	/**
	 * Multiplies the value of the player's cargo at the end of the game
	 * with this modifier to get a new value.
	 */
	private final double CARGO_VALUE_MODIFIER = 0.7;

	// Ships available in the game
	private ArrayList<Ship> ships;
	
	// Islands in the game
	private ArrayList<Island> islands;
	
	// All goods purchased by the player during the game
	private ArrayList<Item> goods;
	
	// TODO Unused so perhaps delete?
	private ArrayList<Item> items;
	private ArrayList<Item> weapons;
	private ArrayList<Route> routes;
	
	// User interface used by the game
	private GameUI ui;
	
	// Current island the player is on
	private Island island;
	
	// Amount of gold the player currently has
	private int gold;
	
	// Player's name
	private String name;
	
	// Days left in the game
	private int daysLeft;
	
	// Total number of days the game will take place over
	private int totalDays;
	
	// Player's ship
	private Ship ship;
	
	// Boolean to check if game is over
	private boolean gameOver = false;
	
	/**
	 * Instantiates the game with the given user interface, starting island, and starting gold amount.
	 * @param ui user interface the game will use
	 * @param islandIndex ArrayList index of the starting island
	 * @param gold starting gold amount
	 */
	public GameEnvironment(GameUI ui, int islandIndex, int gold) {
		initArrayLists();
		this.ui = ui;
		this.island = islands.get(islandIndex);
		this.gold = gold;
		this.ship = ships.get(0);
		ui.start(this);
	}
	
	/**
	 * Initialises all the ArrayLists needed for the game.
	 */
	private void initArrayLists() {
		ships = ObjectsListGenerator.generateShip();
		items = ObjectsListGenerator.generateItem();
		weapons = ObjectsListGenerator.generateWeapon();
		islands = ObjectsListGenerator.generateIsland();
		routes = ObjectsListGenerator.generateRoute(islands);
		goods = new ArrayList<Item>();
	}
	
	/**
	 * Finished the setup by assigning the player's name, ship, and the number of days the game will last.
	 * Afterwards, the game menu is launched.
	 * @param name player's name
	 * @param days number of days the game will last
	 * @param ship player's ship
	 */
	public void finishSetup(String name, int days, Ship ship) {
		this.name = name;
		this.totalDays = days;
		this.daysLeft = days;
		this.ship = ship;
		ui.menu();
	}
	
	/**
	 * Return the game's user interface.
	 * @return game's user interface
	 */
	public GameUI getUI() {
		return ui;
	}
	
	/**
	 * Returns all the ships available in the game.
	 * @return available ships
	 */
	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	/**
	 * Returns an Array list of in game islands
	 * @return islands
	 */
	public ArrayList<Island> getIslands() {
		return islands;
	}
	
	/**
	 * Returns the player's chosen ship.
	 * @return player's ship
	 */
	public Ship getShip() {
		return ship;
	}
	
	/**
	 * Returns the player's name.
	 * @return player's name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Returns the number of days left.
	 * @return number of days left
	 */
	public int getDaysLeft() {
		return daysLeft;
	}
	
	/** 
	 * Returns the total number days for the whole game.
	 * @return total number of days
	 */
	public int getTotalDays() {
		return totalDays;
	}
	
	/** 
	 * Ends the game
	 */
	public void endGame(String reason) {
		ui.endGame(reason);
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
	
	/**
	 * Sets the player's gold to 0.
	 */
	public void loseGold() {
		gold = 0;
	}
	
	/**
	 * Sets the players gold to the given value.
	 * @param new gold value
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	/**
	 * Returns all the goods the player has bought during the game.
	 * @return goods the player has bought
	 */
	public ArrayList<Item> getGoods() {
		return goods;
	}
	
	/**
	 * If the player has enough gold and space for the item, the player is charged and it's added to the ship's cargo.
	 * A copy is also added to the goods ArrayList.
	 * If successfully bought, true is returned, otherwise false.
	 * @param item item to buy
	 * @return true if successful, otherwise false
	 */
	public boolean buyItem(Item item) {
		boolean bought = false;
		if (gold >= item.getPrice() && ship.addCargo(item)) {
			bought = true;
			gold -= item.getPrice();
			
			// Adds a copy of item to goods with the necessary constructor variables
			// Note: price is passed in as basePrice since basePrice not needed
			goods.add(new Item(item.getName(), item.getDesc(), item.getSize(), item.getPrice(), item.isWeapon()));
		}
		return bought;
	}
	
	/**
	 * If the player's ship's cargo contains the item, the player is paid and the item is removed from their ship's cargo.
	 * The first copy found in the goods ArrayList is also updated with its sell price and location.
	 * If successfully sold, true is returned, otherwise false.
	 * @param item item to sell
	 * @return true if successful, otherwise false
	 */
	public boolean sellItem(Item item) {
		boolean sold = false;
		if (ship.removeCargo(item)) {
			sold = true;
			gold += item.getPrice();
			for (Item goodsItem : goods) {
				
				// Sets the first unsold occurrence of this item in goods to sold and stores the
				// sell price and location
				if (goodsItem.getName().equals(item.getName()) && goodsItem.getSoldAt() == null) {
					goodsItem.setSoldAt(island.getName());
					goodsItem.setSoldFor(item.getPrice());
					break;
				}
			}
		}
		return sold;
	}
	
	/** 
	 * Attempts to repair ship, but fails if the player doesn't have enough gold.
	 * @return true if successful, otherwise false
	 */
	public boolean repairShip() {
		boolean repaired = false;
		int cost = ship.getRepairCost();
		if (gold > cost) {
			gold -= cost;
			ship.setHealth(ship.getMaxHealth());
			repaired = true;
		}
		return repaired;
	}
	
	/**
	 * Returns true if the player can afford to travel along route, otherwise false.
	 * @param route the route to travel on
	 * @return true if possible, otherwise false
	 */
	public boolean canAffordRoute(Route route) {
		return gold >= getTravelCost(route);
	}
	
	/**
	 * Returns the cost it will take to travel along the given route.
	 * @param route route to get the cost of
	 * @return cost of traveling along route
	 */
	public double getTravelCost(Route route) {
		int daysTaken = route.getDays(ship.getSpeed());
		return daysTaken * ship.getCrew() * WAGE_MODIFIER;
		
	}
	
	/**
	 * Returns true if there are enough days left for the player to travel along route, otherwise false.
	 * @param route the route to travel on
	 * @return true if possible, otherwise false
	 */
	public boolean isTimeForRoute(Route route) {
		boolean isTime = false;
		int daysTaken = route.getDays(ship.getSpeed());
		if (daysLeft >= daysTaken) {
			isTime = true;
		}
		return isTime;
	}

	/** 
	 * Decreases the number of days by the days of the route, decreases gold by the crew's wages,
	 * possibly encounters random events if the user interface is text based, 
	 * and changes island to the destination of the route.
	 * @param route the route to travel on
	 */
	public void travelRoute(Route route) {
		if (ui instanceof TextUI) {
			if (route.encounterPirates()) {
				ui.pirateEncounter(route);
			}
			if (route.encounterWeatherEvent()) {
				ui.weatherEncounter(route);
			}
			if (route.encounterLostSailors()) {
				ui.sailorEncounter(route);
			}
		}
		int daysTaken = route.getDays(ship.getSpeed());
		this.daysLeft -= daysTaken;
		this.gold -= getTravelCost(route); // Charges crew wages
		
		Island[] islands = route.getIslands();	
		// Sets the game's current island to the other island in the route
		if (islands[0] != island) {
			island = islands[0];
		} else {
			island = islands[1];
		}
	}
	
	/**
	 * Empties the player's cargo and returns true if they are satisfied with the loot, otherwise false.
	 * @return true if pirate's are satisfied, otherwise false.
	 */
	public boolean pirateLossOutcome() {
		int totalValue = 0;
		for (Item item : ship.getCargo()) {
			totalValue += item.getBasePrice();
		}
		ship.emptyCargo(); // Pirates steal all goods
		return totalValue > PIRATE_CARGO_THRESHOLD;
	}
	
	/**
	 * Simulates player turn in a battle.
	 * @param pirateShip
	 * @return
	 */
	public String playerTurn(Ship pirateShip) {
		Ship playerShip = this.getShip();
		String battleText = "";
		Random randomGenerator = new Random();
		// Player Turn
		battleText += "Your Turn:\n";
					
		for (Item item : playerShip.getWeapons()) {
			Weapon weapon = (Weapon) item; 
			battleText += "\tFiring " + weapon.getName()+"!\n";
			for (int i = 0; i < weapon.shots(); i++) {
			int damage = randomGenerator.nextInt(weapon.damage());
			if (damage > 0) {
				int resisted = randomGenerator.nextInt(pirateShip.getEndurance());
				resisted = Math.min(resisted, damage);
				battleText += "\t"+resisted+" damage resisted by pirate ship\n";
				battleText += "\t"+(damage-resisted)+" damage dealt to pirate ship\n";
				pirateShip.setHealth(pirateShip.getHealth() - damage + resisted);
			}
			else {
				battleText += "Missed pirate ship\n";
			}
			}	
		
		}
		return battleText;
	}
	
	/**
	 * Simulates pirate turn in a battle.
	 * @param pirateShip
	 * @return
	 */
	public String pirateTurn(Ship pirateShip) {
		Ship playerShip = this.getShip();
		String battleText = "";
		Random randomGenerator = new Random();
		// Pirate Turn
		battleText += "Pirate Turn:\n";
		for (Item item : pirateShip.getWeapons()) {
			Weapon weapon = (Weapon) item; 
			battleText += "\tFiring " + weapon.getName()+"!\n";
			for (int i = 0; i < weapon.shots(); i++) {
				int damage = randomGenerator.nextInt(weapon.damage());
				if (damage > 0) {
					int resisted = randomGenerator.nextInt(playerShip.getEndurance());
					resisted = Math.min(resisted, damage);
					battleText += "\t"+resisted+" damage resisted by your ship\n";
					battleText += "\t"+(damage-resisted)+" damage dealt to your ship\n";
					playerShip.setHealth(playerShip.getHealth() - damage + resisted);
				}
				else {
					battleText += "Missed your ship\n";
				}
			}			
		}
		return battleText;
	}
	
	/**
	 * Simulates a battle between the player and the pirate's ship.
	 * @return damage done to the player's ship
	 */
	public int pirateEvent() {
		Random randomGenerator = new Random();
		// Generate enemy ship
		ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
		int shipInt = randomGenerator.nextInt(ships.size());
		Ship pirateShip = ships.get(shipInt);
		Ship playerShip = ship;
		int initialHealth = playerShip.getHealth();
		
		// Player and pirate take turns rolling dice
		while (playerShip.getHealth() > 0 && pirateShip.getHealth() > 0) {
			// Player Turn
			for (Item item : playerShip.getWeapons()) {
				Weapon weapon = (Weapon) item; 
				for (int i = 0; i < weapon.shots(); i++) {
					int damage = randomGenerator.nextInt(weapon.damage());
					if (damage > 0) {
						int resisted = randomGenerator.nextInt(pirateShip.getEndurance());
						resisted = Math.min(resisted, damage);
						pirateShip.setHealth(pirateShip.getHealth() - damage + resisted);
					}
				}				
			}
			if (pirateShip.getHealth() <= 0){
				break;
			}
			// Pirate Turn
			for (Item item : pirateShip.getWeapons()) {
				Weapon weapon = (Weapon) item; 
				for (int i = 0; i < weapon.shots(); i++) {
					int damage = randomGenerator.nextInt(weapon.damage());
					if (damage > 0) {
						int resisted = randomGenerator.nextInt(playerShip.getEndurance());
						resisted = Math.min(resisted, damage);
						playerShip.setHealth(playerShip.getHealth() - damage + resisted);
					}
				}				
			}
		}
		return initialHealth - playerShip.getHealth();
	}

	
	/**
	 * Generates a random amount of damage between a constant maximum and minimum, then subtracts it from the ship's health.
	 * @return amount of damage
	 */
	public int weatherEvent() {
		Random randomGenerator = new Random();
		// Deal random damage between a range
		int damage = randomGenerator.nextInt(MAX_WEATHER_DAMAGE - MIN_WEATHER_DAMAGE) + MIN_WEATHER_DAMAGE;
		ship.setHealth(ship.getHealth() - damage);
		return damage;
	}
	
	/**
	 * Generates a random reward amount between a constant maximum and minimum, then adds it to the player's gold.
	 * @return reward amount
	 */
	public int sailorEvent() {
		Random randomGenerator = new Random();
		// Give monetary reward between a range
		int reward = randomGenerator.nextInt(MAX_SAILOR_REWARD - MIN_SAILOR_REWARD) + MIN_SAILOR_REWARD;
		setGold(gold + reward);
		return reward;
	}
	/**
	 * Calculates the players score based on final gold, current goods and ship health.
	 * @return score
	 */
	public int calcScore() {
		return (int) (this.gold + Math.round(ship.getCargoValue()*CARGO_VALUE_MODIFIER) - ship.getRepairCost());
	}
	
	/**
	 * Returns true is the game is over
	 * @return gameOver
	 */
	public boolean isGameOver() {
		return this.gameOver;
	}
	
	/**
	 * Sets the game over variable to true
	 */
	public void setGameOver() {
		this.gameOver = true;
	}
}
