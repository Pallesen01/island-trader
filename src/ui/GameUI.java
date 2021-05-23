package ui;

import core.GameEnvironment;
import core.Island;

public interface GameUI {
	
	final String INT_ERROR = "Please enter a valid integer.";
	
	final String NAME_PROMPT = "What is your trader name?";
	final String NAME_ERROR = "Name must be between 3 and 15 letters.";
	final String NAME_REGEX = "[a-zA-Z]{3,15}";
	
	final String DAYS_PROMPT = "How many days you would like to play for?";
	final String DAYS_ERROR = "Days must be between 20 and 50.";
	
	final String SHIP_PROMPT = "What ship would you like to captain?";
	final String SHIP_HEADER = "Playable ships:";
	
	final String REPAIR_MAX = "Ship already at max health.";
	final String REPAIR_SUCCESS = "Ship repaired.";
	final String REPAIR_FAIL = "Ship not repaired - insufficient gold.";
	
	final String BOUGHT = "Item bought.";
	final String SOLD = "Item sold.";
	final String BUY_FAIL = "Failed to buy item - insufficient gold or cargo space.";
	final String SELL_FAIL = "Failed to sell item - not found in cargo.";
	
	enum MenuOption {
		SHIP_INFO("View ship information"),
		GOODS("View goods"),
		ISL_INFO("View island information"),
		STORE("Visit the store"),
		REPAIR("Repair your ship"),
		TRAVEL("Travel to another island");
		
		public final String label;

        MenuOption(String label) {
            this.label = label;
        }
	}
	
	
	void start(GameEnvironment game);
	
	
	void menu();
	
	
	void shipInfo();
	
	
	void goods();
	
	
	void islandInfo();
	
	void displayIslandInfo(Island island);	
	
	void store();
	
	
	void repair();
	
	
	void travel();
	
	
	void pirateEncounter();
	
	
	void weatherEncounter();
	
	
	void sailorsEncounter();
}
