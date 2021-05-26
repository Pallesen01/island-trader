package core;

/**
 * This class models a game-like item with a name, description, size, price. It also stores how where it's been sold and for how much.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Item {
	
	/**
	 * Name of the item.
	 */
	private String name;
	
	/**
	 * Item's description.
	 */
	private String desc;
	
	/**
	 * Size of the item.
	 */
	private int size;
	
	/**
	 * Base price of the item (used by to store to determine a price).
	 */
	private int basePrice;
	
	/**
	 * Price the item is being bought or sold for.
	 */
	private int price;
	
	/**
	 * Whether or not the item is a weapon.
	 */
	private boolean isWeapon;
	
	/**
	 * Where the item has been sold.
	 */
	private String soldAt;
	
	/**
	 * How much the item was sold for.
	 */
	private int soldFor;
	
	/**
	 * Creates an item with the given parameter values.
	 * @param name item's name
	 * @param desc item's description
	 * @param size item's size
	 * @param basePrice item's base price
	 * @param isWeapon true if item is weapon, otherwise false
	 */
	public Item(String name, String desc, int size, int basePrice, boolean isWeapon) {
		this.name = name;
		this.desc = desc;
		this.size = size;
		this.basePrice = basePrice;
		this.price = basePrice;
		this.isWeapon = isWeapon;
		
		// Set to null values until they've been sold
		this.soldAt = null;
		this.soldFor = -1;
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
	 * @return item's base price
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
	 * Sets a new price for the item.
	 * @param new price for the item
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Returns true if the item is classified as a weapon, otherwise false.
	 * @return true if weapon, otherwise false 
	 */
	public boolean isWeapon() {
		return isWeapon;
	}
	
	/**
	 * Returns the name of the island where the item was sold at.
	 * @return name of island where item was sold
	 */
	public String getSoldAt() {
		return soldAt;
	}
	
	/**
	 * Sets the name of the island where the item was sold at.
	 * @param islandName name of island where item was sold
	 */
	public void setSoldAt(String islandName) {
		soldAt = islandName;
	}
	
	/**
	 * Returns the price that the item was sold for.
	 * @return price item was sold for
	 */
	public int getSoldFor() {
		return soldFor;
	}
	
	/**
	 * Sets the price that the item was sold for.
	 * @param price price item was sold for
	 */
	public void setSoldFor(int price) {
		soldFor = price;
	}
}
