package core;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEnvironment {	
	
	private static Island travel(Island island, Ship ship,Scanner input) {
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
			System.out.println("\tChance of encountering lost sailors: " + routes.get(i).getSailorsOdds() + '%');
		}
		System.out.print("Choose route to take or enter '-1' to cancel: ");
		int routeChosen = input.nextInt() - 1;
		if (routeChosen < 0) {
			return island;
		}
		Island[] routeIslands = routes.get(routeChosen).getIslands();
		if (routeIslands[0] != island) {
			return routeIslands[0];
		}
		else {
			return routeIslands[1];
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\s*\n\\s*");
		ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
		ArrayList<Item> items = ObjectsListGenerator.generateItem();
		ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
		ArrayList<Island> islands = ObjectsListGenerator.generateIsland();
		ObjectsListGenerator.generateRoute(islands);
		Island currentIsland = islands.get(0);
		int gold = 100;
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
		System.out.print("Ship named: " + playerShip.getTitle()+"\n\n");
		
		
		while (gameRunning) {
			currentIsland = travel(currentIsland, playerShip ,input);
			
		}
		
	
		
	}
	

}
