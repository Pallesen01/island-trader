package core;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class generates and ArrayList of in-game items using the parameters specified in items.txt.
 * @author Dillon Pike, Daniel Pallesen
 * @version 24 April 2021
 */
public class ItemsListGenerator {

	/** 
	 * Returns an ArrayList of in-game items 
	 * @return ArrrayList of Item objects
	 */
	public static ArrayList<Item> generate() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Scanner itemData = null;
		
		try {
			itemData = new Scanner(new File("game-parameters\\items.txt"));
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
			String desc = itemData.next();
			int size = itemData.nextInt();
			int value = itemData.nextInt();
			//Item newItem = new Item(name, desc, size, value);
			Item newItem = new Item(name, desc, size);
			itemList.add(newItem);
		}
		
		
		return itemList;
		
	}
	public static void main(String[] args) {
		// Run generator
		System.out.println(ItemsListGenerator.generate());
	}

}
