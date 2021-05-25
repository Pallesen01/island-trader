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
	private boolean isWeapon;
	private boolean isSold;
	private String soldAt;
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
		this.isSold = false;
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
	 * Sets a new price for the item.
	 * @param newPrice for the item
	 */
	public void setPrice(int newPrice) {
		this.price = newPrice;
	}
	
	/**
	 * Returns true if the item is classified as a weapon, otherwise false.
	 * @return true if weapon, otherwise false 
	 */
	public boolean isWeapon() {
		return isWeapon;
	}
	
	/**
	 * Returns true if the item has been sold, otherwise false.
	 * @return true if sold, otherwise false
	 */
	public boolean getIsSold() {
		return isSold;
	}
	
	/**
	 * Sets the value of isSold to true or false.
	 * @param isSold new value of item's isSold attribute
	 */
	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
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
