package ui;

import core.GameEnvironment;
import core.Island;
import core.Route;

/**
 * Interface for any user interface class. Includes methods that need to be implemented, and strings that
 * can be used to keep output consistent over different user interfaces.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public interface GameUI {
	
	/**
	 * Message displayed when player doesn't enter a valid integer.
	 */
	final String INT_ERROR = "Please enter a valid integer.";
	
	/**
	 * Prompt for getting the player's name.
	 */
	final String NAME_PROMPT = "What is your trader name?";
	
	/**
	 * Message displayed when player's name isn't valid.
	 */
	final String NAME_ERROR = "Name must be between 3 and 15 letters.";
	
	/**
	 * Regular expression that defines a valid name.
	 */
	final String NAME_REGEX = "[a-zA-Z]{3,15}";
	
	/**
	 * Prompt for getting the number of days the game will last.
	 */
	final String DAYS_PROMPT = "How many days you would like to play for?";
	
	/**
	 * Message displayed when days chosen isn't valid.
	 */
	final String DAYS_ERROR = "Days must be between 20 and 50.";
	
	/**
	 * Prompt for getting the ship the player would like.
	 */
	final String SHIP_PROMPT = "What ship would you like to captain?";
	
	/**
	 * Header to display above available ships.
	 */
	final String SHIP_HEADER = "Playable ships:";
	
	/**
	 * Message displayed when the player tries to repair a ship at max health.
	 */
	final String REPAIR_MAX = "Ship already at max health.";
	
	/**
	 * Message displayed when the player repairs their ship.
	 */
	final String REPAIR_SUCCESS = "Ship repaired.";
	
	/**
	 * Message displayed when the player tries to repair their ship, but doesn't have enough gold
	 */
	final String REPAIR_FAIL = "Ship not repaired - insufficient gold.";
	
	/**
	 * Message displayed when the player buys an item.
	 */
	final String BOUGHT = "Item bought.";
	
	/**
	 * Message displayed when the player sells an item.
	 */
	final String SOLD = "Item sold.";
	
	/**
	 * Message displayed when the player tries to buy an item, but fails.
	 */
	final String BUY_FAIL = "Failed to buy item - insufficient gold or cargo space.";
	
	/**
	 * Message displayed when the player tries to sell an item, but fails.
	 */
	final String SELL_FAIL = "Failed to sell item - not found in cargo.";
	
	/**
	 * Message displayed when the player tries to travel when their ship isn't at maximum health
	 */
	final String TRAVEL_SHIP_ERROR = "You cannot sail before repairing your ship.";
	
	/**
	 * Message displayed when the player tries to travel along a route they can't afford.
	 */
	final String TRAVEL_GOLD_ERROR = "Cannot travel along this route - insufficient gold to pay wages.";
	
	/**
	 * Message displayed when the player tries to travel along a route that will take longer
	 * than the number of days left in the game.
	 */
	final String TRAVEL_DAYS_ERROR = "There are not enough days left to travel along this route.";
	
	/**
	 * Message displayed when the pirate random event occurs.
	 */
	final String PIRATE_ENCOUNTER = "Uh oh! There's a pirate ship in the distance!";
	
	/**
	 * Message displayed when the weather random event occurs.
	 */
	final String WEATHER_ENCOUNTER = "A storm is brewing!";
	
	/**
	 * Message displayed when the lost sailors random event occurs.
	 */
	final String SAILOR_ENCOUNTER = "What's that? There are some sailors in the water!";
	
	/**
	 * Start of the message that's displayed when the player loses to the pirates.
	 */
	final String PIRATE_LOSS = "Unfortunately, you were utterly defeated and the pirates took all of your cargo.\n";
	
	/**
	 * Message displayed when the player loses to the pirates and the pirates aren't satisfied with their loot.
	 */
	final String PIRATE_UNSATISFIED = PIRATE_LOSS + "They were severely disappointed with the loot.\nYou are forced to walk the plank.";
	
	/**
	 * Message displayed when the player loses to the pirates and the pirates are satisfied with their loot.
	 */
	final String PIRATE_SATISFIED = PIRATE_LOSS + "On the bright side, they were thrilled with the loot and decided to spare your crew's lives.";
	
	/**
	 * Start of the game where the player is asked for their name, 
	 * the number of days they want to play for, and which ship they want.
	 * @param game game instance
	 */
	void start(GameEnvironment game);
	
	/**
	 * Menu screen where the player can choose an option out of all the ones available in the game.
	 */
	void menu();
	
	/**
	 * Displays the properties of the player's ship, what's in the cargo, and which cargo items are weapons.
	 */
	void shipInfo();
	
	/**
	 * Displays all the goods the user has ever bought, how much they were bought and sold for,
	 * and where they were sold.
	 */
	void goods();
	
	/**
	 * Player can pick an island and view what routes are available on that island, as well as the items
	 * they can buy and sell at the island's store.
	 */
	void islandInfo();
	
	/**
	 * Displays the island's available routes and the items the player can buy and sell at its store.
	 * @param island island to display info of
	 */
	void displayIslandInfo(Island island);
	
	/**
	 * Displays the player's gold and a list of items the player can choose to buy and sell.
	 */
	void store();
	
	/**
	 * Shows the players gold, ship's health, and ship's repair cost and allows the player to repair their ship.
	 */
	void repair();
	
	/**
	 * Displays the available routes on the current island and allows the player to choose one to take.
	 */
	void travel();

	/**
	 * 
	 * @param route route currently being traveled along
	 */
	void pirateEncounter(Route route);
	
	/**
	 * 
	 * @param route route currently being traveled along
	 */
	void weatherEncounter(Route route);
	
	/**
	 * 
	 * @param route route currently being traveled along
	 */
	void sailorEncounter(Route route);
	
	/**
	 * Displays a game over screen with the reason for the game ending, player's name, 
	 * expected and actual game duration, final gold, final cargo value, and the player's final score.
	 * @param reason reason for game ending
	 */
	void endGame(String reason);
}
