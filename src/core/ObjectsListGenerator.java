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
	public static void main(String[] args) {
		/*File testFile = new File("");
	    String currentPath = testFile.getAbsolutePath();
	    System.out.println("current path is: " + currentPath);*/
		// Run generator
		System.out.println(ItemsListGenerator.generateItem().size());
		Store newStore = new Store();
		System.out.println(newStore.getBuys());
		for (Item item : newStore.getBuys()) {
			System.out.println(item.getName() + " " + item.getPrice());
	}
		System.out.println("NEXT STORE");
		newStore.generateBuys();
		System.out.println(newStore.getBuys());
		for (Item item : newStore.getBuys()) {
			System.out.println(item.getName() + " " + item.getPrice());
	}
	}

}
