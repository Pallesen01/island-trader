package core;
import java.util.ArrayList;

/**
 * This class models a ship.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Ship {
	
	private final int MIN_HEALTH = 0;
	private final double REPAIR_MODIFIER = 0.5;
	
	private String name;
	private int crew;
	private int space;
	private int health;
	private int maxHealth;
	private int speed;
	private int endurance;
	private ArrayList<Item> cargo = new ArrayList<Item>();
	private ArrayList<Item> weapons = new ArrayList<Item>();
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
		this.addCargo(ObjectsListGenerator.generateWeapon().get(0)); // Add basic cannon to ship
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
	 * Returns the ship's maximum health.
	 * @return ship's maxhealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Returns the cost to repair the ship.
	 * @return ship's repair cost
	 */
	public int getRepairCost() {
		return (int) ((maxHealth - health) * REPAIR_MODIFIER);
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
	 * Returns an ArrayList of the ship's weapons.
	 * @return ArrayList of ship's weapons
	 */
	public ArrayList<Item> getWeapons() {
		return weapons;
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
			if (item.isWeapon()) {
				weapons.add(item);
			}
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
		for (Item cargoItem : cargo) {
			if (cargoItem.getName().equals(item.getName())) {
				cargo.remove(cargoItem);
				if (cargoItem.isWeapon()){
					weapons.remove(cargoItem);
				}
				space += item.getSize();
				removed = true;
				break;
			}
		}
		return removed;
	}
	
	/**
	 * Empties player's cargo
	 */
	public void emptyCargo() {
		cargo =  new ArrayList<Item>();
	}
	
	public void printUpgrades() {
		// TODO implement
	}
}
