package core;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the man logic of the game and runs it.
 * @author Dillon Pike, Daniel Pallesen
 * @version 6 May 2021
 */
public class GameEnvironment {
	
	private final static int STARTING_GOLD = 250;
	private final static String VALID_INT_MSG = "Please enter a valid integer.\n";

	/**
	 *  Handles logic for selecting and traveling to a new island.
	 * @param input
	 * @param state
	 * @param ship
	 */
	private static void travel(Scanner input, GameState state, Ship ship) {
		Island currentIsland = state.getIsland();
		System.out.println("Reachable Islands:");
		ArrayList<Route> routes = currentIsland.getRoutes(); 
		for (int i = 0; i < routes.size(); i++) {
			for (Island island : routes.get(i).getIslands()) {
				if (island != currentIsland) {
					System.out.println((i+1) + " - " + island.getName());
				}
			}
			System.out.println("\tDays: " + routes.get(i).getDays(ship.getSpeed())); // int days, int pirateDanger, int weatherDanger, int sailorsOdds
			System.out.println("\tChance of encountering pirates: " + routes.get(i).getPirateDanger() + '%');
			System.out.println("\tChance of encountering dangerous weather: " + routes.get(i).getWeatherDanger() + '%');
			System.out.println("\tChance of encountering lost sailors: " + routes.get(i).getSailorsOdds() + "%\n");
		}
		String prompt = "Choose route to take or enter '0' to cancel: ";
		int routeChosen = getValidInt(input, 0, routes.size(), prompt, VALID_INT_MSG) - 1;
		if (routeChosen != -1) {
			state.travelForDays(routes.get(routeChosen).getDays(ship.getSpeed()), ship.getCrew());
			Island[] islands = routes.get(routeChosen).getIslands();
			if (islands[0] != currentIsland) {
				state.setIsland(islands[0]);
			} else {
				state.setIsland(islands[1]);
			}
		}
	}
	
	/**
	 * Handles game logic for buying from a store
	 * @param input
	 * @param island
	 * @param ship
	 * @param state
	 */
	private static void buyFromStore(Scanner input, Store store, Ship ship, GameState state) {		
		System.out.println("Current gold: " + state.getGold());
		System.out.println("Items avaliable for purchase:");
		ArrayList<Item> items = store.getBuys(); 
		for (int i = 0; i < items.size(); i++) {
			System.out.println("\t"+(i+1)+" - "+items.get(i).getName()+", "+items.get(i).getSize()+"kg, "+items.get(i).getPrice()+" gold"); //name;description;size;value;
			}
		String prompt = "Choose item to buy or enter '0' to cancel: ";
		String errorMessage = VALID_INT_MSG;
		int choice = getValidInt(input, 0, items.size(), prompt, errorMessage) - 1;
		if (choice != -1) {
			Item item = items.get(choice);
			if (state.getGold() >= item.getPrice() && ship.addCargo(item)) {
				System.out.println("Item bought.\n");
				state.changeGold(-item.getPrice());
			} else {
				System.out.println("Failed to buy item - insufficient gold or cargo space.\n");
			}
		}
	}
	
	/**
	 * Handles game logic for selling to a store
	 * @param input
	 * @param island
	 * @param ship
	 * @param state
	 */
	private static void sellToStore(Scanner input, Store store, Ship ship, GameState state) {
		System.out.println("Current gold: " + state.getGold());
		System.out.println("Items able to sell:");
		ArrayList<Item> items = store.getSells();
		for (int i = 0; i < items.size(); i++) {
			System.out.println("\t"+(i+1)+" - "+items.get(i).getName()+", "+items.get(i).getSize()+"kg, "+items.get(i).getPrice()+" gold"); //name;description;size;value;
			}
		String prompt = "Choose item to buy or enter '0' to cancel: ";
		String errorMessage = VALID_INT_MSG;
		int choice = getValidInt(input, 0, items.size(), prompt, errorMessage) - 1;
		if (choice != -1) {
			Item item = items.get(choice);
			if (ship.removeCargo(item)) {
				System.out.println("Item sold.");
				state.changeGold(item.getPrice());
			} else {
				System.out.println("Failed to sell item - not in cargo.");
			}
		}
	}
	
	/** 
	 * Checks if the next input is a valid int and returns it
	 * Otherwise skips it and returns -1
	 * @param input scanner that's scanning the input
	 * @return next valid int in the input, otherwise -1
	 */
	private static int nextValidInt(Scanner input) {
		int next = -1;
		try {
			next = input.nextInt();
		} catch (java.util.InputMismatchException e) {
			input.nextLine();
		}
		return next;
	}
	
	/**
	 * Gets a valid integer from the user within a specific range.
	 * @param input
	 * @param lowerBound
	 * @param upperBound
	 * @param prompt
	 * @param errorMessage
	 * @return
	 */
	private static int getValidInt(Scanner input, int lowerBound, int upperBound, String prompt, String errorMessage) {
		int choice = lowerBound - 1;
		while (choice < lowerBound || choice > upperBound) {
			System.out.print(prompt);
			choice = nextValidInt(input);
			if (choice < lowerBound || choice > upperBound) {
				System.out.println(errorMessage);
			}
		}
		return choice;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\s*\n\\s*");
		
		ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
		ArrayList<Item> items = ObjectsListGenerator.generateItem();
		ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
		ArrayList<Island> islands = ObjectsListGenerator.generateIsland();
		ObjectsListGenerator.generateRoute(islands);
		boolean gameRunning = true;
		
		
		System.out.println("Playable ships:");
		for (int i = 0; i < ships.size(); i++) {
			System.out.println((i+1) + " - " + ships.get(i).toString() + "\n");
		}
		String prompt = "Choose a ship to captain: ";
		int selectedShip = getValidInt(input, 1, ships.size(), prompt, VALID_INT_MSG) - 1;
		Ship playerShip = ships.get(selectedShip);
		System.out.println(playerShip.getName() + " Selected");
		
		System.out.print("Enter a name for your ship: ");
		playerShip.setTitle(input.next());
		System.out.print("Ship named: " + playerShip.getTitle() + "\n");
		
		prompt = "Set number of days for game to last (recommended > 50): ";
		int days = getValidInt(input, 1, (int) Math.pow(2, 31) - 1, prompt, VALID_INT_MSG);

		GameState state = new GameState(STARTING_GOLD, days, islands.get(0));
		
		while (gameRunning && state.getDays() > 0) {
			
			System.out.println(state.getDays()+" Days Remaining");
			System.out.println("Avaliable Actions:");
			System.out.println("1 - Travel");
			System.out.println("2 - Buy from store");
			System.out.println("3 - Sell to store");
			System.out.println("4 - Check cargo");
			System.out.print("Select Action to Perform: ");
			
			int selection = nextValidInt(input);

	        switch (selection) {
	            case 1:  travel(input, state, playerShip);
	                     break;
	            case 2:  buyFromStore(input, state.getIsland().getStore(), playerShip, state);
	                     break;
	            case 3:  sellToStore(input, state.getIsland().getStore(), playerShip, state);
	                     break;
	            case 4:  playerShip.printCargo();
	                     break;	
	            default: System.out.println(VALID_INT_MSG);
	            		 break;
	        }
		}
	}
}
