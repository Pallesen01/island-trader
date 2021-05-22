package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import core.GameEnvironment;
import core.Island;
import core.Item;
import core.ObjectsListGenerator;
import core.Route;
import core.Ship;
import core.Weapon;

public class TextUI implements GameUI{
	
	private final Scanner input;
	private GameEnvironment game;
	private Random randomGenerator = new Random();
	
	enum StoreOption {
		LEAVE("Leave"),
		GOODS("View goods"),
		BUY("Buy"),
		SELL("Sell");
		
		public final String label;

        StoreOption(String label) {
            this.label = label;
        }
	}
	
	public TextUI() {
		this.input = new Scanner(System.in);
		input.useDelimiter("\\s*\n\\s*");
	}

	@Override
	public void start(GameEnvironment game) {
		this.game = game;
		String name = getName();
		int days = getValidInt(20, 50, DAYS_PROMPT, DAYS_ERROR);
		Ship ship = getShip();
		game.finishSetup(name, days, ship);
	}

	@Override
	public void menu() {
		final MenuOption options[] = MenuOption.values();
		
		while (game.getDays() > 0) {
			printGameState();
			printMenuOptions();
			int choice = getValidInt(1, options.length, "\nSelect Action to Perform:", INT_ERROR);
			switch (options[choice-1]) {
	            case SHIP_INFO: shipInfo();
	                     		break;
	            case GOODS:  	goods();
	                     		break;
	            case ISL_INFO:  islandInfo();
	                     		break;
	            case STORE:  	store();
	                     		break;
	            case REPAIR:    repair();
	            				break;
	            case TRAVEL:	travel();
	            				break;
			}
		}

	}

	@Override
	public void shipInfo() {
		System.out.println(game.getShip());
		// TODO add extra info
	}

	@Override
	public void goods() {
		// TODO implement sold goods
		System.out.println("Current goods:");
		int i = 0;
		for (Item item : game.getShip().getCargo()) {
			if (item.isWeapon()) {
				System.out.println("\t" + (i+1) + " - " + item.getName() + ", " + item.getSize() + "kg, " + item.getBasePrice() + " gold - \""+item.getDesc()+"\"");
			}
			else {
				System.out.println("\t" + (i+1) + " - " + item.getName() + ", " + item.getSize() + "kg, " + item.getBasePrice() + " gold");
			
			}
			i++;
		}

	}

	@Override
	public void islandInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void store() {
		final StoreOption options[] = StoreOption.values();
		
		while (true) {
			printStoreOptions();
			String prompt = "\nSelect Action to Perform:";
			int choice = getValidInt(1, MenuOption.values().length, prompt, INT_ERROR);
			switch (options[choice-1]) {
				case LEAVE: return;
				case GOODS: goods();
							break;
				case BUY:  	buy();
				     		break;
				case SELL:  sell();
				     		break;
			}
		}
		
	}
	
	@Override
	public void repair() {
		Ship ship = game.getShip();
		if (ship.getHealth() == ship.getMaxHealth()) {
			System.out.println(REPAIR_MAX);
		} else {
			System.out.println("Cost: " + ship.getRepairCost() + "\nWould you like to repair your ship?");
			String options[] = {"Yes", "No"};
			for (int i = 0; i < options.length; i++) {
				System.out.println((i+1) + " - " + options[i]);
			}
			String prompt = "\nSelect Action to Perform:";
			int choice = getValidInt(1, options.length, prompt, INT_ERROR);
			if (choice == 1) {
				if (game.repairShip()) {
					System.out.println(REPAIR_SUCCESS);
				} else {
					System.out.println(REPAIR_FAIL);
				}
			}
		}
	}

	@Override
	public void travel() {
		if (game.getShip().getHealth() != game.getShip().getMaxHealth()) {
			System.out.println("You cannot sail before repairing your ship.");
		} else {
			System.out.println("Reachable Islands:");
			printIslands();
			ArrayList<Route> routes = game.getIsland().getRoutes();
			String prompt = "Choose route to take or enter '0' to cancel: ";
			int choice = getValidInt(0, routes.size(), prompt, INT_ERROR);
			if (choice != 0) {
				Route route = routes.get(choice-1); 
				if (game.canTravelRoute(route)) {
					game.travelRoute(route);
				} else {
					System.out.println("Cannot travel along this route - insufficient gold to pay wages.");
				}
			}
		}
	}
	
	
	private String getName() {
		while (true) {
			System.out.println(NAME_PROMPT);
			String name = input.nextLine();
			if (name.matches(NAME_REGEX)) {
				return name;
			}
			System.out.println(NAME_ERROR);
		}
	}
	
	/**
	 * Gets a valid integer from the user within a specific range.
	 * @param lowerBound
	 * @param upperBound
	 * @param prompt
	 * @param errorMsg
	 * @return
	 */
	private int getValidInt(int lowerBound, int upperBound, String prompt, String errorMsg) {
		while (true) {
			System.out.println(prompt);
			try {
				int choice = input.nextInt();
				if (lowerBound <= choice && choice <= upperBound ) {
					return choice;
				}
			} catch (java.util.InputMismatchException e) {
				input.nextLine();
			}
			System.out.println(errorMsg);
		}
	}
	
	private Ship getShip() {
		System.out.println(SHIP_HEADER);
		ArrayList<Ship> ships = game.getShips();
		int i = 0;
		for (Ship ship : ships) {
			System.out.println((i+1) + " - " + ship + "\n");
			i++;
		}
		
		int choice = getValidInt(1, ships.size(), SHIP_PROMPT, INT_ERROR);
		return ships.get(choice-1);
	}
	
	private void printGameState() {
		System.out.println("\n" + game.getDays()+" Days Remaining");
		System.out.println("Current Island: " + game.getIsland().getName());
	}
	
	private void printMenuOptions() {
		System.out.println("\nAvaliable Actions:");
		int i = 0;
		for (MenuOption option : MenuOption.values()) {
			System.out.println((i+1) + " - " + option.label);
			i++;
		}
	}
	
	private void printStoreOptions() {
		System.out.println("\nAvaliable Actions:");
		int i = 0;
		for (StoreOption option : StoreOption.values()) {
			System.out.println((i+1) + " - " + option.label);
			i++;
		}
	}
	
	private void buy() {
		while (true) {
			System.out.println("\nCurrent gold: " + game.getGold());
			ArrayList<Item> items = game.getStore().getBuys();
			System.out.println("\nItems avaliable for purchase:");
			printItems(items);
			String prompt = "\nChoose item to buy or enter '0' to go back: ";
			int choice = getValidInt(0, items.size(), prompt, INT_ERROR);
			if (choice == 0) {
				break;
			} else {
				if (game.buyItem(items.get(choice-1))) {
					System.out.println("Item bought.");
				} else {
					System.out.println("Failed to buy item - insufficient gold or cargo space.");
				}
			}
		}
	}
	
	private void sell() {
		while (true) {
			System.out.println("\nCurrent gold: " + game.getGold());
			ArrayList<Item> items = game.getStore().getSells();
			System.out.println("\nItems avaliable to sell:");
			printItems(items);
			String prompt = "\nChoose item to sell or enter '0' to go back: ";
			int choice = getValidInt(0, items.size(), prompt, INT_ERROR);
			if (choice == 0) {
				break;
			} else {
				if (game.sellItem(items.get(choice-1))) {
					System.out.println("Item sold.\n");
				} else {
					System.out.println("Failed to sell item - not found in cargo.\n");
				}
			}
		}
		
	}
	
	private void printItems(ArrayList<Item> items) {
		int i = 0;
		for (Item item : items) {
			if (item.isWeapon()) {
				System.out.println("\t" + (i+1) + " - " + item.getName() + ", " + item.getSize() + "kg, " + item.getPrice() + " gold - \""+item.getDesc()+"\"");
			}
			else {
				System.out.println("\t" + (i+1) + " - " + item.getName() + ", " + item.getSize() + "kg, " + item.getPrice() + " gold");
			}
			i++;
		}
	}
	
	private void printIslands() {
		Island island = game.getIsland();
		ArrayList<Route> routes = island.getRoutes();
		Ship ship = game.getShip();
		int i = 0;
		for (Route route : routes) {
			System.out.print((i+1) + " - ");
			System.out.println(route.getInfo(island, ship.getSpeed()) + "\n");
			i++;
		}
	}

	@Override
	public void pirateEncounter() {
		final String VICTORY_MESSAGE = "Pirates defeated";
		final int CARGO_THRESHOLD = 100;
		System.out.println("Pirates Encountered!\n");
		
		// Generate enemy ship
		ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
		int shipInt = randomGenerator.nextInt(ships.size());
		Ship pirateShip = ships.get(shipInt);
		Ship playerShip = game.getShip();
		
		// Player and pirate take turns rolling dice
		while (playerShip.getHealth() > 0 && pirateShip.getHealth() > 0) {
			// Print Health
			System.out.println("Your Health - " + playerShip.getHealth());
			System.out.println("Pirate Health - " + pirateShip.getHealth());
			// Player Turn
			System.out.println("Your Turn:");
			for (Item item : playerShip.getWeapons()) {
				Weapon weapon = (Weapon) item; 
				System.out.println("\tFiring " + weapon.getName()+'!');
				for (int i = 0; i < weapon.shots(); i++) {
					int damage = randomGenerator.nextInt(weapon.damage());
					if (damage == 0) {
						System.out.println("Missed pirate ship");
					}
					else {
						System.out.println("\t"+damage+" damage dealt to pirate ship");
						int resisted = randomGenerator.nextInt(pirateShip.getEndurance());
						resisted = Math.min(resisted, damage);
						System.out.println("\t"+resisted+" damage resisted by pirate ship");
						pirateShip.setHealth(pirateShip.getHealth() - damage + resisted);
					}
					
					
				}				
			}
			if (pirateShip.getHealth() <= 0){
				System.out.println(VICTORY_MESSAGE);
				return;
			}
			// Pirate Turn
			System.out.println("Pirate's Turn:");
			for (Item item : pirateShip.getWeapons()) {
				Weapon weapon = (Weapon) item; 
				System.out.println("\tFiring " + weapon.getName()+'!');
				for (int i = 0; i < weapon.shots(); i++) {
					int damage = randomGenerator.nextInt(weapon.damage());
					if (damage == 0) {
						System.out.println("Missed your ship");
					}
					else {
						System.out.println("\t"+damage+" damage dealt to your ship");
						int resisted = randomGenerator.nextInt(playerShip.getEndurance());
						resisted = Math.min(resisted, damage);
						System.out.println("\t"+resisted+" damage resisted by your ship");
						playerShip.setHealth(playerShip.getHealth() - damage + resisted);
					}
				}				
			}
		}
		
		// If loss pirates steal all goods
		int totalValue = 0;
		for (Item item : playerShip.getCargo()) {
			totalValue += item.getBasePrice();
		}
		playerShip.emptyCargo();
		// If good value below threshold lose game
		if (totalValue < CARGO_THRESHOLD) {
			game.loseGold();
			System.out.println("The pirates are NOT satisfied with the value of your cargo\nYou are forced to walk the plank");
			game.endGame();
		}
		else {
			System.out.println("The pirates are satisfied with the value of your cargo");
		}
		
		
	}

	@Override
	public void weatherEncounter() {
		final int MIN_DAMAGE = 10;
		final int MAX_DAMAGE = 40;
		Ship playerShip = game.getShip();
		System.out.println("Your ship has been struck by unfortunate weather.");
		
		// Deal random damage between a range
		int damage = randomGenerator.nextInt(MAX_DAMAGE - MIN_DAMAGE) + MIN_DAMAGE;
		System.out.println("Your ship has taken "+damage+" damage");
		playerShip.setHealth(playerShip.getHealth() - damage);
		// end game if ship is destroyed
		if (playerShip.getHealth() <= 0) {
			System.out.println("Your ship has been destroyed in the storm");
			playerShip.emptyCargo();
			game.loseGold();
			game.endGame();
		}
		
	}

	@Override
	public void sailorsEncounter() {
		final int MIN_REWARD = 20;
		final int MAX_REWARD = 60;
		System.out.println("Your ship has come across lost sailors");		
		// Give monetary reward between a range
		int reward = randomGenerator.nextInt(MAX_REWARD - MIN_REWARD) + MIN_REWARD;
		System.out.println("The sailors give you "+reward+" gold as a reward for rescuing them");
		game.setGold(game.getGold() + reward);
	}
}
