package core;

/**
 * This class models a game-like item with a name, description, and size.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Item {
	
	String name;
	String desc;
	int size;
	
	/**
	 * Creates an item with the given parameter values.
	 * @param name item's name
	 * @param desc item's description
	 * @param size item's size
	 */
	public Item(String name, String desc, int size) {
		this.name = name;
		this.desc = desc;
		this.size = size;
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
}
