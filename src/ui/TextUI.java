package ui;

import java.util.ArrayList;
import java.util.Scanner;

import core.GameEnvironment;
import core.Island;
import core.Item;
import core.Route;
import core.Ship;

public class TextUI implements GameUI {
	
	private final Scanner input;
	private GameEnvironment game;
	
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
		
		while (game.getDaysLeft() > 0) {
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
			System.out.println(TRAVEL_SHIP_ERROR);
		} else {
			System.out.println("Reachable Islands:");
			printIslands();
			ArrayList<Route> routes = game.getIsland().getRoutes();
			String prompt = "Choose route to take or enter '0' to cancel: ";
			int choice = getValidInt(0, routes.size(), prompt, INT_ERROR);
			if (choice != 0) {
				Route route = routes.get(choice-1); 
				if (game.canAffordRoute(route)) {
					game.travelRoute(route);
				} else {
					System.out.println(TRAVEL_GOLD_ERROR);
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
		System.out.println("\n" + game.getDaysLeft()+" Days Remaining");
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
					System.out.println(BOUGHT);
				} else {
					System.out.println(BUY_FAIL);
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
					System.out.println(SOLD);
				} else {
					System.out.println(SELL_FAIL);
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
	public void pirateEncounter(Route route) {
		System.out.println(PIRATE_ENCOUNTER);
		int damage = game.pirateEvent();
		if (game.getShip().getHealth() > 0) {
			System.out.println("But you defeated them!\nYour ship has taken " + damage + " damage.");
		} else {
			// Lose the game if the goods value is below the threshold
			if (!game.pirateLossOutcome()) {
				game.loseGold();
				System.out.println(PIRATE_UNSATISFIED);
				game.endGame("Killed by Pirates");
			}
			else {
				System.out.println(PIRATE_SATISFIED);
			}
		}
	}

	@Override
	public void weatherEncounter(Route route) {
		Ship playerShip = game.getShip();
		int damage = game.weatherEvent();
		System.out.println(WEATHER_ENCOUNTER);
		System.out.println("Your ship has taken " + damage + " damage.");
		// end game if ship is destroyed
		if (playerShip.getHealth() <= 0) {
			System.out.println("Your ship has been destroyed in the storm.");
			playerShip.emptyCargo();
			game.loseGold();
			game.endGame("Killed in Storm");
		}
		
	}

	@Override
	public void sailorEncounter(Route route) {
		int reward = game.sailorEvent();
		System.out.println(SAILOR_ENCOUNTER);		
		System.out.println("The sailors give you " + reward + " gold as a reward for rescuing them.");
	}

	@Override
	public void displayIslandInfo(Island island) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame(String reason) {
		// TODO Auto-generated method stub
		
	}
}
