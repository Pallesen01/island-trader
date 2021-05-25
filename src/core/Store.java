package core;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class models a store with a list of items that can be bought from and sold to the store.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class Store {
	/**
	 * Modifier that's applied to the maximum and minimum price of an item by multiplication.
	 */
	private final double PRICE_MODIFIER = 0.75;
	
	/**
	 * Applied to an item's base price to find its maximum and minimum price before modified by PRICE_MODIFIER.
	 * Increases/decreases an item's base price by the percentage of the modifier.
	 */
	private final double VARIATION_MODIFIER = 0.4;
	
	/**
	 * TODO Daniel
	 */
	private final int SELL_DIFFERENCE = 5; 
	
	// Random number generator for picking items and prices
	private Random randomGenerator;
	
	// Items that can be bought at the store
	private ArrayList<Item> buys;
	
	// Items that can be sold to the store
	private ArrayList<Item> sells;
	
	/**
	 * Creates a store with a random number generator and ArrayLists of items that can be bought from and sold to the store,
	 * then populates the lists with items.
	 */
	public Store() {
		randomGenerator = new Random();
		buys = new ArrayList<>();
		sells = new ArrayList<>();
		generateBuys();
		generateSells();
	}
	
	/**
	 * Generates random price for an item within a range
	 * @param item
	 * @return Returns price for item
	 */
	private int randomizePrice(Item item) {
		int basePrice = item.getBasePrice();
		double minPrice = (basePrice + basePrice * VARIATION_MODIFIER) * PRICE_MODIFIER;
		double maxPrice = (basePrice - basePrice * VARIATION_MODIFIER) * PRICE_MODIFIER;
		int price = (int) ((Math.random() * (maxPrice - minPrice)) + minPrice);
		return price;
	}
	
	/**
	 * Returns the items that can be bought from the store.
	 * @return items that can be bought
	 */
	public ArrayList<Item> getBuys() {
		return buys;
	}
	
	/**
	 * Returns the items that can be sold to the store.
	 * @return items that can be sold
	 */
	public ArrayList<Item> getSells() {
		return sells;
	}
	
	/**
	 * Generates a random list of items that can be bought from the store.
	 * TODO give method inside comments
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
	 * Generates a random list of items that can be sold to the store.
	 * TODO give method inside comments
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
		for (Item item1 : newSells) {
			for (Item item2: this.buys) {
				if (item1.getName().equals(item2.getName())){
					if (item2.getPrice() < item1.getPrice()) {
						int newPrice = item2.getPrice()-(1+randomGenerator.nextInt(SELL_DIFFERENCE-1));
						if (newPrice < 1) {
							newPrice = 1;
						}
						item1.setPrice(newPrice);
					}
				}
			}
		}
		sells = newSells;
	}
}
