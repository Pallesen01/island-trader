package core;
/**
 * This class extends the Item class for in-game weapons. This adds a shots and damage attribute.
 * @author Dillon Pike, Daniel Pallesen
 * @version 23 April 2021
 */
public class Weapon extends Item {
	private int shots;
	private int damage;

	public Weapon(String name, String desc, int size, int basePrice, int shots, int damage) {
		super(name, desc, size, basePrice);
		this.shots = shots;
		this.damage = damage;
	}
	
	/** 
	 * Returns the weapon's shots.
	 * @return number of shots fired by the weapon
	 */
	public int shots() {
		return shots;
	}
	
	/** 
	 * Returns the weapons' damage. 
	 * @return weapon's damage
	 */
	public int damage() {
		return damage;
	}

}
