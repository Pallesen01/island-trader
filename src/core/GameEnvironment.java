package core;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the man logic of the game and runs it.
 * @author Dillon Pike, Daniel Pallesen
 * @version 6 May 2021
 */
public class GameEnvironment {	
	
	/**
	 *  Handles logic for selecting and traveling to a new island.
	 * @param island
	 * @param ship
	 * @param input
	 * @return island traveled to
	 */
	private static Island travel(Island island, Ship ship, Scanner input) {
		System.out.println("Reachable Islands:");
		ArrayList<Route> routes = island.getRoutes(); 
		for (int i = 0; i <routes.size(); i++) {
			for (Island island2: routes.get(i).getIslands()) {
				if (island2 != island) {
					System.out.println((i+1) + " - " + island2.getName());
				}
			}
			System.out.println("\tDays: " + routes.get(i).getDays(ship)); //int days, int pirateDanger, int weatherDanger, int sailorsOdds
			System.out.println("\tChance of encountering pirates: " + routes.get(i).getPirateDanger() + '%');
			System.out.println("\tChance of encountering dangerous weather: " + routes.get(i).getWeatherDanger() + '%');
			System.out.println("\tChance of encountering lost sailors: " + routes.get(i).getSailorsOdds() + "%\n");
		}
		System.out.print("Choose route to take or enter '-1' to cancel: ");
		int routeChosen = input.nextInt() - 1;
		if (routeChosen < 0) {
			return island;
		}
		ship.travelForDays(routes.get(routeChosen).getDays(ship));
		Island[] routeIslands = routes.get(routeChosen).getIslands();
		if (routeIslands[0] != island) {
			return routeIslands[0];
		}
		else {
			return routeIslands[1];
		}
		
	}
	
	/**
	 * Handles game logic for buying from a store
	 * @param island
	 * @param ship
	 * @param input
	 */
	private static void buyFromStore(Island island, Ship ship, Scanner input) {		
		System.out.println("Current gold: " + ship.getGold());
		System.out.println("Items avaliable for purchase:");
		ArrayList<Item> items = island.getStore().getBuys(); 
		for (int i = 0; i < items.size(); i++) {
			System.out.println("\t"+(i+1)+" - "+items.get(i).getName()+", "+items.get(i).getSize()+"kg, "+items.get(i).getPrice()+" gold"); //name;description;size;value;
			}
		System.out.print("Choose item to buy or enter '-1' to cancel: ");
		int itemChosen = input.nextInt() - 1;
		if (itemChosen > 0) {
			if (!ship.buyCargo(items.get(itemChosen))) {
				System.out.println("Failed to buy item - insufficient gold or cargo space");
			}
			
		}
		
	}

	
	public static void main(String[] args) {
		final int STARTING_GOLD = 250;
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\s*\n\\s*");
		ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
		ArrayList<Item> items = ObjectsListGenerator.generateItem();
		ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
		ArrayList<Island> islands = ObjectsListGenerator.generateIsland();
		ObjectsListGenerator.generateRoute(islands);
		Island currentIsland = islands.get(0);
		Ship playerShip;
		boolean gameRunning = true;
		
		
		System.out.println("Playable ships:");
		for (int i = 0; i < ships.size(); i++) {
			System.out.println((i+1)+" - "+ships.get(i).getName()+':');
			System.out.println("\tCrew Members: "+ships.get(i).getCrew());
			System.out.println("\tCargo Space: "+ships.get(i).getSpace());
			System.out.println("\tHealth: "+ships.get(i).getHealth());
			System.out.println("\tSpeed: "+ships.get(i).getSpeed());
			System.out.println("\tEndurance: "+ships.get(i).getEndurance()+'\n');
		}
		
		System.out.print("Choose a ship to captain: ");
		int selectedShip = input.nextInt() - 1;
		playerShip = ships.get(selectedShip);
		System.out.println(playerShip.getName() + " Selected");
		System.out.print("Enter a name for your ship: ");
		String shipName = input.next();
		playerShip.setTitle(shipName);
		System.out.print("Ship named: " + playerShip.getTitle() + "\n");
		System.out.print("Set number of days for game to last (reccomended > 50): ");
		playerShip.setDays(input.nextInt());
		playerShip.setGold(STARTING_GOLD);
		
		
		while (gameRunning && playerShip.getDays() > 0) {
			
			System.out.println(playerShip.getDays()+" Days Remaining");
			
			System.out.println("Avaliable Actions:");
			System.out.println("1 - Travel");
			System.out.println("2 - Buy from store");
			System.out.println("3 - Sell to store");
			System.out.println("4 - Check cargo");
			System.out.print("Select Action to Perform: ");
			
	        int selection = input.nextInt();
	        switch (selection) {
	            case 1:  currentIsland = travel(currentIsland, playerShip , input);;
	                     break;
	            case 2:  buyFromStore(currentIsland, playerShip, input);
	                     break;
	            case 3:  buyFromStore(currentIsland, playerShip, input);//sellToStore();
	                     break;
	            case 4: buyFromStore(currentIsland, playerShip, input);//checkCargo();
	                     break;
					
	        }
		}
	}
}
