package core;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class models a store.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Store {
	
	final private double BUY_MODIFIER = 0.75;
	final private double VARIATION_MODIFIER = 0.4;
	
	private HashMap<Item, Integer> buys = new HashMap<Item, Integer>();
	private HashMap<Item, Integer> sells = new HashMap<Item, Integer>();
	
	/**
	 * Constructs hash maps of item and price pairs for each item the store buys and sells.
	 * @param buys items the store buys
	 * @param sells items the store sells
	 */
	public Store(ArrayList<Item> buys, ArrayList<Item> sells) {
		for (Item item : buys) {
			int basePrice = item.getBasePrice();
			double minPrice = (basePrice + basePrice * VARIATION_MODIFIER) * BUY_MODIFIER;
			double maxPrice = (basePrice - basePrice * VARIATION_MODIFIER) * BUY_MODIFIER;
			int price = (int) ((Math.random() * (maxPrice - minPrice)) + minPrice);
			this.buys.put(item, price);
		}
		for (Item item : sells) {
			int basePrice = item.getBasePrice();
			double minPrice = basePrice + basePrice * VARIATION_MODIFIER;
			double maxPrice = basePrice - basePrice * VARIATION_MODIFIER;
			int price = (int) ((Math.random() * (maxPrice - minPrice)) + minPrice);
			this.sells.put(item, price);
		}
	}
	
	/**
	 * Returns hash map of item and price pairs for each item the store buys.
	 * @return 
	 */
	public HashMap<Item, Integer> getBuys() {
		return buys;
	}
}
