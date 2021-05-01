package core;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

/**
 * This class models a store.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Store {
	
	final private double BUY_MODIFIER = 0.75;
	final private double VARIATION_MODIFIER = 0.4;
	
	private Random randomGenerator = new Random();
	private HashMap<Item, Integer> buys = new HashMap<Item, Integer>();
	private HashMap<Item, Integer> sells = new HashMap<Item, Integer>();
	
	/**
	 * Constructs hash maps of item and price pairs for each item the store buys and sells.
	 */
	public Store() {
		this.generateBuys();
		this.generateSells();
		/*for (Item item : buys) {
			int price = randomizePrice(item);
			this.buys.put(item, price);
		}
		for (Item item : sells) {
			int price = randomizePrice(item);
			this.sells.put(item, price);
		}*/
	}
	
	/**
	 * Generates random price for an item within a range
	 * @param item
	 * @return Returns price for item
	 */
	private int randomizePrice(Item item) {
		int basePrice = item.getBasePrice();
		double minPrice = (basePrice + basePrice * VARIATION_MODIFIER) * BUY_MODIFIER;
		double maxPrice = (basePrice - basePrice * VARIATION_MODIFIER) * BUY_MODIFIER;
		int price = (int) ((Math.random() * (maxPrice - minPrice)) + minPrice);
		return price;
	}
	
	/**
	 * Returns hash map of item and price pairs for each item the store buys.
	 * @return 
	 */
	public HashMap<Item, Integer> getBuys() {
		return buys;
	}
	/**
	 * Returns hash map of item and price pairs for each item the store sells.
	 * @return 
	 */
	public HashMap<Item, Integer> getSells() {
		return sells;
	}
	
	/**
	 * Generates a random list of items bought by the store
	 */
	public void generateBuys() {
		HashMap<Item, Integer> newBuys = new HashMap<Item, Integer>();
		ArrayList<Item> allItems = ItemsListGenerator.generateItem();
		for (int i=0;i<10; i++) {
			Item newItem = allItems.get(randomGenerator.nextInt(allItems.size()));
			int price = randomizePrice(newItem);
			newBuys.put(newItem, price);
		}
		buys = newBuys;
		
	}
	/**
	 * Generates a random list of items sold by the store
	 */
	public void generateSells() {
		HashMap<Item, Integer> newSells = new HashMap<Item, Integer>();
		ArrayList<Item> allItems = ItemsListGenerator.generateItem();
		for (int i=0;i<10; i++) {
			Item newItem = allItems.get(randomGenerator.nextInt(allItems.size()));
			int price = randomizePrice(newItem);
			newSells.put(newItem, price);
		}
		sells = newSells;
		
	}
}
