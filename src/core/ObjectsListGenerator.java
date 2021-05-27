package core;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class generates ArrayLists of in-game objects using the parameters specified game-parameters.
 * @author Dillon Pike, Daniel Pallesen
 * @version 24 April 2021
 */
public class ObjectsListGenerator {

	/** 
	 * Returns an ArrayList of in-game items.
	 * @return ArrrayList of Item objects
	 */
	public static ArrayList<Item> generateItem() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Scanner itemData = null;
		
		try {
			itemData = new Scanner(new File("game-parameters/items.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Items data file not found!");
			e.printStackTrace();
		}
		
		itemData.useDelimiter(";");
		
		// skip first line in file
		itemData.nextLine();
		
		// generate object for each line and add to ArrayList
		while(itemData.hasNext()) {
			String name = itemData.next().strip();
			String desc = itemData.next();
			int size = itemData.nextInt();
			int value = itemData.nextInt();
			Item newItem = new Item(name, desc, size, value, false);
			itemList.add(newItem);
		}	
		return itemList;
	}
	
	/** 
	 * Returns an ArrayList of in-game weapons.
	 * @return ArrrayList of Item objects
	 */
	public static ArrayList<Item> generateWeapon() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Scanner itemData = null;
		
		try {
			itemData = new Scanner(new File("game-parameters/weapons.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Weapons data file not found!");
			e.printStackTrace();
		}
		
		itemData.useDelimiter(";");
		
		// skip first line in file
		itemData.nextLine();
		
		// generate object for each line and add to ArrayList
		while(itemData.hasNext()) {
			String name = itemData.next().strip();
			String desc = itemData.next();
			int size = itemData.nextInt();
			int value = itemData.nextInt();
			int shots = itemData.nextInt();
			int damage = itemData.nextInt();
			Item newItem = new Weapon(name, desc, size, value, shots, damage);
			itemList.add(newItem);
		}
		return itemList;
	}
	
	/** 
	 * Returns an ArrayList of in-game ships.
	 * @return ArrrayList of Ship objects
	 */
	public static ArrayList<Ship> generateShip() {
		ArrayList<Ship> shipList = new ArrayList<Ship>();
		Scanner shipData = null;
		
		try {
			shipData = new Scanner(new File("game-parameters/ships.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Ships data file not found!");
			e.printStackTrace();
		}
		
		shipData.useDelimiter(";");
		
		// skip first line in file
		shipData.nextLine();
		
		// generate object for each line and add to ArrayList
		while(shipData.hasNext()) {
			String name = shipData.next().strip();
			int crew = shipData.nextInt();
			int space = shipData.nextInt();
			int health = shipData.nextInt();
			int speed = shipData.nextInt();
			int endurance = shipData.nextInt();
			Ship newShip = new Ship(name,crew,space,health,speed,endurance);
			shipList.add(newShip);
		}
		return shipList;
	}
	
	/** 
	 * Returns an ArrayList of in-game islands.
	 * @return ArrrayList of Island objects
	 */
	public static ArrayList<Island> generateIsland() {
		ArrayList<Island> islandList = new ArrayList<Island>();
		Scanner islandData = null;
		
		try {
			islandData = new Scanner(new File("game-parameters/islands.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Islands data file not found!");
			e.printStackTrace();
		}
		
		islandData.useDelimiter(";");
		
		// skip first line in file
		islandData.nextLine();
		
		// generate object for each line and add to ArrayList
		while(islandData.hasNext()) {
			String name = islandData.next().strip();			
			Island newIsland = new Island(name);
			
			islandList.add(newIsland);
			
		}
		return islandList;
	}
	
	/** 
	 * Returns an ArrayList of in-game routes.
	 * @param islands ArrayList of islands in the game
	 */
	public static void generateRoute(ArrayList<Island> islands) {
		ArrayList<Route> routeList = new ArrayList<Route>();
		Scanner routeData = null;
		
		try {
			routeData = new Scanner(new File("game-parameters/routes.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Islands data file not found!");
			e.printStackTrace();
		}
		
		routeData.useDelimiter(";");
		
		// skip first line in file
		routeData.nextLine();
		
		// generate object for each line and add to ArrayList
		while(routeData.hasNext()) {
			String islandName1 = routeData.next().strip();	
			String islandName2 = routeData.next();
			int days = routeData.nextInt();
			int pirateDanger = routeData.nextInt();
			int weatherDanger = routeData.nextInt();
			int sailorsOdds = routeData.nextInt();
			Island island1 = null;
			Island island2 = null;
			for (Island island : islands ) {
				String islandName = island.getName();
				if (islandName1.equals(islandName)) {
					island1 = island;
				}
				if (islandName2.equals(islandName)) {
					island2 = island;
				}
			}
			if (island1 == null || island2 == null) {
				throw new java.lang.Error("Not all islands found when generating route list");
			}
			
			Route newRoute = new Route(island1,island2,days,pirateDanger,weatherDanger,sailorsOdds);
			island1.addRoute(newRoute);
			island2.addRoute(newRoute);
			routeList.add(newRoute);
		}	
	}
}
