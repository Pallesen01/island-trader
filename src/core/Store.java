package core;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class models a store.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Store {
	
	final private double BUY_MODIFIER = 0.75;
	final private double VARIATION_MODIFIER = 0.4;
	
	private Random randomGenerator = new Random();
	private ArrayList<Item> buys = new ArrayList<>();
	private ArrayList<Item> sells = new ArrayList<>();
	
	/**
	 * Constructs hash maps of item and price pairs for each item the store buys and sells.
	 */
	public Store() {
		this.generateBuys();
		this.generateSells();
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
	public ArrayList<Item> getBuys() {
		return buys;
	}
	/**
	 * Returns hash map of item and price pairs for each item the store sells.
	 * @return 
	 */
	public ArrayList<Item> getSells() {
		return sells;
	}
	
	/**
	 * Generates a random list of items bought by the store
	 */
	public void generateBuys() {
		ArrayList<Item> newBuys = new ArrayList<>();
		ArrayList<Item> allItems = ObjectsListGenerator.generateItem();
		ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
		allItems.addAll(weapons);
		ArrayList<Integer> prevInts = new ArrayList<>();
		for (int i=0;i<10; i++) {
			int nextInt = randomGenerator.nextInt(allItems.size());
			while (prevInts.contains(nextInt)) {
				nextInt = randomGenerator.nextInt(allItems.size());
			}
			prevInts.add(nextInt);
			Item newItem = allItems.get(nextInt);
			int price = randomizePrice(newItem);
			newItem.setPrice(price);
			newBuys.add(newItem);
		}
		buys = newBuys;
		
	}
	/**
	 * Generates a random list of items sold by the store
	 */
	public void generateSells() {
		ArrayList<Item> newSells = new ArrayList<>();
		ArrayList<Item> allItems = ObjectsListGenerator.generateItem();
		ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
		allItems.addAll(weapons);
		ArrayList<Integer> prevInts = new ArrayList<>();
		for (int i=0;i<10; i++) {
			int nextInt = randomGenerator.nextInt(allItems.size());
			while (prevInts.contains(nextInt)) {
				nextInt = randomGenerator.nextInt(allItems.size());
			}
			prevInts.add(nextInt);
			Item newItem = allItems.get(nextInt);
			int price = randomizePrice(newItem);
			newItem.setPrice(price);
			newSells.add(newItem);
		}
		sells = newSells;
		
	}
}
