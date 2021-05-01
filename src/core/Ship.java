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
	private ArrayList<Item> cargo = new ArrayList<Item>();
	
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
	 * Removes an item from the ship's cargo if the ship's cargo contains the item.
	 * @param item item to be removed
	 * @return true if successful, otherwise false
	 */
	public boolean removeCargo(Item item) {
		boolean removed = false;
		if (cargo.contains(item)) {
			cargo.remove(item);
			space += item.getSize();
			removed = true;
		}
		return removed;
	}
}
