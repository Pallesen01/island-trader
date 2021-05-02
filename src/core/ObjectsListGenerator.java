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
	 * Returns an ArrayList of in-game items 
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
			String name = itemData.next();
			//System.out.println(name);
			String desc = itemData.next();
			//System.out.println(desc);
			int size = itemData.nextInt();
			//System.out.println(size);
			int value = itemData.nextInt();
			//System.out.println(value);
			Item newItem = new Item(name, desc, size, value);
			//System.out.println(newItem);
			itemList.add(newItem);
		}
		
		
		return itemList;
		
	}
	
	/** 
	 * Returns an ArrayList of in-game weapons 
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
			String name = itemData.next();
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
	 * Returns an ArrayList of in-game ships 
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
			String name = shipData.next();
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
	 * Returns an ArrayList of in-game ships 
	 * @return ArrrayList of Ship objects
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
			String name = islandData.next();			
			Island newIsland = new Island(name);
			islandList.add(newIsland);
		}
		
		
		return islandList;
		
	}

}
