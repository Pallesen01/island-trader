package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class generates and ArrayList of in-game weapons using the parameters specified in weapons.txt.
 * @author Dillon Pike, Daniel Pallesen
 * @version 23 April 2021
 */
public class WeaponsListGenerator extends ItemsListGenerator {
	
	/** 
	 * Returns an ArrayList of in-game weapons 
	 * @return ArrrayList of Item objects
	 */
	public static ArrayList<Item> generate() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Scanner itemData = null;
		
		try {
			itemData = new Scanner(new File("game-parameters\\weapons.txt"));
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
		// Run generator
		System.out.println(WeaponsListGenerator.generate());
	}

}
