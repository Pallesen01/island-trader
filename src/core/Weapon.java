package core;
/**
 * Extends the Item class for in-game weapons, adding shots and damage attributes.
 * @author Dillon Pike, Daniel Pallesen
 * @version 23 April 2021
 */
public class Weapon extends Item {
	
	/**
	 * Number of shots the weapon can fire at once
	 */
	private int shots;
	
	/**
	 * Amount of damage one shot of the weapon does
	 */
	private int damage;

	/**
	 * Creates a weapon with the given parameters values.
	 * @param name weapon's name
	 * @param desc weapon's description
	 * @param size weapon's size
	 * @param basePrice weapon's base price
	 * @param shots number of shots the weapon can fire
	 * @param damage amount of damage one shot does
	 */
	public Weapon(String name, String desc, int size, int basePrice, int shots, int damage) {
		super(name, desc, size, basePrice, true);
		this.shots = shots;
		this.damage = damage;
	}
	
	/** 
	 * Returns the number of shots the weapon can fire at once.
	 * @return number of shots fired by the weapon
	 */
	public int shots() {
		return shots;
	}
	
	/** 
	 * Returns the weapons' damage per shot.
	 * @return weapon's damage per shot
	 */
	public int damage() {
		return damage;
	}

}
