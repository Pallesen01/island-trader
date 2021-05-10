package core;
import java.util.ArrayList;

/**
 * This class models a ship.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Ship {
	
	final int MIN_HEALTH = 0;
	
	private String name;
	private int crew;
	private int space;
	private int health;
	private int maxHealth;
	private int speed;
	private int endurance;
	private int gold;
	private int days;
	private ArrayList<Item> cargo = new ArrayList<Item>();
	private String title; // name given by player
	
	/**
	 * Creates a ship at full health with the given parameter values.
	 * @param name ship's name
	 * @param crew number of crew members the ship has
	 * @param space amount of space the ship has for cargo
	 * @param health amount of health the ship has
	 * @param speed ship's speed
	 */
	public Ship(String name, int crew, int space, int health, int speed, int endurance) {
		this.name = name;
		this.crew = crew;
		this.space = space;
		this.health = health;
		this.maxHealth = health;
		this.speed = speed;
		this.endurance = endurance;
	}
	
	/**
	 * Returns a string representation of the ship's attributes.
	 * @return string representation of ship
	 */
	public String toString() {
		String string = name + ":";
		string += "\n\tCrew Members: " + crew;
		string += "\n\tCargo Space: " + space;
		string += "\n\tHealth: " + health;
		string += "\n\tSpeed: " + speed;
		string += "\n\tEndurance: " + endurance;
		return string;
	}
	
	/** 
	 * Returns the ship's name. 
	 * @return ship's name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Sets the ship's name.
	 * @param name new name for the ship
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 * Returns the ship's title. 
	 * @return ship's title
	 */
	public String getTitle() {
		return title;
	}
	
	/** 
	 * Sets the ship's title.
	 * @param name new title for the ship
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** 
	 * Returns the ship's gold. 
	 * @return ship's gold
	 */
	public int getGold() {
		return gold;
	}
	
	/** 
	 * Sets the ship's gold
	 * @param name new gold for the ship
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	/** 
	 * Returns the ship's days. 
	 * @return ship's days
	 */
	public int getDays() {
		return days;
	}
	
	/** 
	 * Sets the ship's days
	 * @param name new days for the ship
	 */
	public void setDays(int days) {
		this.days = days;
	}
	
	/** 
	 * Removes from the ship's days
	 * @param amount to be removed
	 */
	public void travelForDays(int days) {
		this.gold -= (days*this.getCrew());
		this.days -= days;
	}
	
	/**
	 * Returns the number of crew members.
	 * @return number of crew members
	 */
	public int getCrew() {
		return crew;
	}
	
	/**
	 * Sets the number of crew members.
	 * @param crew new number of crew members
	 */
	public void setCrew(int crew) {
		this.crew = crew;
	}
	
	/**
	 * Returns the amount of free space on the ship.
	 * @return amount of space
	 */
	public int getSpace() {
		return space;
	}
	
	/**
	 * Returns the ship's health.
	 * @return ship's health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the ship's health value and constrains it between a minimum and maximum value.
	 * @param health new health value for the ship
	 */
	public void setHealth(int health) {
		this.health = Math.max(MIN_HEALTH, Math.min(health, maxHealth));
	}
	
	/**
	 * Returns the ship's speed.
	 * @return ship's speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Sets the ship's speed.
	 * @param speed new ship speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Returns the ship's endurance.
	 * @return ship's endurance
	 */
	public int getEndurance() {
		return endurance;
	}
	
	/**
	 * Sets the ship's endurance.
	 * @param speed new ship endurance
	 */
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	
	/**
	 * Returns an ArrayList of the ship's cargo (the items on the ship).
	 * @return ArrayList of ship's cargo
	 */
	public ArrayList<Item> getCargo() {
		return cargo;
	}
	
	/**
	 * Prints the items in the ship's cargo.
	 */
	public void printCargo() {
		System.out.println(title + "'s cargo:");
		if (cargo.size() == 0) {
			System.out.println("\tEmpty");
		}
		for (int i = 0; i < cargo.size(); i++) {
			System.out.println("\t" + (i+1) + " - " + cargo.get(i).getName() + ", " + cargo.get(i).getSize() + "kg, "+cargo.get(i).getPrice() + " gold");
		}
		System.out.println();
	}
	
	/**
	 * Adds an item to the ship's cargo if there is enough space.
	 * @param item item to be added
	 * @return true if successful, otherwise false
	 */
	public boolean addCargo(Item item) {
		boolean added = false;
		if (space > item.getSize()) {
			cargo.add(item);
			space -= item.getSize();
			added = true;
		}
		return added;
	}
	
	/**
	 * Adds an item to the ship's cargo if there is enough space and the ship has enough gold.
	 * @param item item to be added
	 * @return true if successful, otherwise false
	 */
	public boolean buyCargo(Item item) {
		boolean bought = false;
		if (gold >= item.getPrice() && space > item.getSize()) {
			gold -= item.getPrice();
			bought = addCargo(item);						
		}
		return bought;
	}
	
	/**
	 * Removes an item from the ship's cargo if the ship's cargo contains the item.
	 * @param item item to be removed
	 * @return true if successful, otherwise false
	 */
	public boolean removeCargo(Item item) {
		boolean removed = false;
		for (Item cargoItem : cargo) {
			if (cargoItem.getName().equals(item.getName())) {
				cargo.remove(item);
				space += item.getSize();
				removed = true;
				break;
			}
		}
		return removed;
	}
	
	/**
	 * Removes an item from the ship's cargo if it's there and adds the price to the ship's gold.
	 * @param item item to be removed
	 * @return true if successful, otherwise false
	 */
	public boolean sellCargo(Item item) {
		boolean sold = removeCargo(item);
		if (sold) {
			gold += item.getPrice();
		}
		return sold;
	}
}
