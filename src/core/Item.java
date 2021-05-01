package core;

/**
 * This class models a game-like item with a name, description, and size.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Item {
	
	private String name;
	private String desc;
	private int size;
	private int basePrice;
	private int price;
	
	/**
	 * Creates an item with the given parameter values.
	 * @param name item's name
	 * @param desc item's description
	 * @param basePrice item's base price
	 * @param size item's size
	 */
	public Item(String name, String desc, int size, int basePrice) {
		this.name = name;
		this.desc = desc;
		this.size = size;
		this.basePrice = basePrice;
		this.price = basePrice;
	}
	
	/** 
	 * Returns the items's name. 
	 * @return item's name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Returns the item's description.
	 * @return item's name
	 */
	public String getDesc() {
		return desc;
	}
	
	/** 
	 * Returns the item's size. 
	 * @return item's size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns the item's base price.
	 * @return item's basePrice
	 */
	public int getBasePrice() {
		return basePrice;
	}
	
	/**
	 * Returns the item's price.
	 * @return item's price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 *  Sets a new price for the item
	 * @param newPrice for the item
	 */
	public void setPrice(int newPrice) {
		this.price = newPrice;
	}
}
