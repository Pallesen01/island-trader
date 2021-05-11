package core;

/**
 * This class contains attributes and methods relating to the current game state.
 * @author Dillon Pike, Daniel Pallesen
 * @version 11 May 2021
 */
public class GameState {
	
	private int gold;
	private int days;
	private Island currentIsland;
	
	/**
	 * Initialises a game state with necessary variables.
	 * @param gold starting amount of gold
	 * @param days number of days
	 * @param island starting island
	 */
	public GameState(int gold, int days, Island island) {
		this.gold = gold;
		this.days = days;
		this.currentIsland = island;
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
	 * @param gold new gold for the ship
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	/** 
	 * Changes gold by amount.
	 * @param name new gold for the ship
	 */
	public void changeGold(int amount) {
		gold += amount;
	}
	
	/** 
	 * Returns the number of days left 
	 * @return number of days left
	 */
	public int getDays() {
		return days;
	}
	
	/** 
	 * Sets the ship's days.
	 * @param name new days for the ship
	 */
	public void setDays(int days) {
		this.days = days;
	}
	
	/** 
	 * Decreases the number of days by days and decreases gold by the crew's wages.
	 * @param days number of days to decrease by
	 * @param crew number of crew members
	 */
	public void travelForDays(int days, int crew) {
		this.days -= days;
		gold -= days * crew;
	}
	
	/**
	 * Returns the current island.
	 * @return current island
	 */
	public Island getIsland() {
		return currentIsland;
	}
	
	/**
	 * Sets the current island
	 * @param island island to set the current island to
	 */
	public void setIsland(Island island) {
		currentIsland = island;
	}

}
