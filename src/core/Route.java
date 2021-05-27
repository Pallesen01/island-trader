package core;
import java.util.Random;

/**
 * This class models a route between islands.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class Route {
	
	/**
	 * Modifier that multiplies the number of days needed before dividing by ship speed.
	 */
	private final int DAYS_MODIFIER = 20;
	
	/**
	 * Random number generator.
	 */
	private Random randomGenerator;
	
	/**
	 * One island in the route.
	 */
	private Island island1;
	
	/**
	 * Other island in the route.
	 */
	private Island island2;
	
	/**
	 * Base number of days it takes to travel along the route.
	 */
	private int days;
	
	/**
	 * Probability (out of 100) of encountering pirates while traveling along the route.
	 */
	private int pirateDanger;
	
	/**
	 * Probability (out of 100) of bad weather while traveling along the route.
	 */
	private int weatherDanger;
	
	/**
	 * Probability (out of 100) of finding lost sailors while traveling along the route.
	 */
	private int sailorsOdds;
	
	/**
	 * Creates a route with the given parameter values.
	 * @param island1 one island in the route
	 * @param island2 other island in the route
	 * @param days base number of days it takes to travel this route
	 * @param pirateDanger chance of encountering pirates (out of 100)
	 * @param weatherDanger chance of bad weather (out of 100)
	 * @param sailorsOdds change of finding lost sailors (out of 100)
	 */
	public Route(Island island1, Island island2, int days, int pirateDanger, int weatherDanger, int sailorsOdds) {
		randomGenerator = new Random();
		this.island1 = island1;
		this.island2 = island2;
		this.days = days;
		this.pirateDanger = pirateDanger;
		this.weatherDanger = weatherDanger;
		this.sailorsOdds = sailorsOdds;
	}
	
	/**
	 * Returns a list with the islands the route goes between
	 * @return island list
	 */
	public Island[] getIslands() {
		Island[] islands = {island1, island2};
		return islands;
	}
	
	/**
	 * Returns string of what island the route will go to,
	 * how many days it will take,
	 * and the chances of encountering random events.
	 * @param island island the player is currently on
	 * @param speed speed of the player's ship
	 * @return string of information
	 */
	public String getInfo(Island island, int speed) {
		String string = "";
		if (island != island1) {
			string += island1.getName() + "\n";
		} else {
			string += island2.getName() + "\n";
		}
		string += "\tDays: " + this.getDays(speed) + "\n"; // int days, int pirateDanger, int weatherDanger, int sailorsOdds
		string += "\tChance of encountering pirates: " + pirateDanger + "%\n";
		string += "\tChance of encountering dangerous weather: " + weatherDanger + "%\n";
		string += "\tChance of encountering lost sailors: " + sailorsOdds + "%";
		return string;
	}
	
	/**
	 * Returns number of days route takes
	 * @param speed ship's speed
	 * @return days
	 */
	public int getDays(int speed) {
		return days * DAYS_MODIFIER / speed;
	}
	
	/**
	 * Returns chance of encountering pirates
	 * @return pirateDanger
	 */
	public int getPirateDanger() {
		return pirateDanger;
	}
	
	/**
	 * Returns chance of encountering dangerous weather
	 * @return weatherDanger
	 */
	public int getWeatherDanger() {
		return weatherDanger;
	}
	
	/**
	 * Returns chance of encountering lost sailors
	 * @return sailorOdds
	 */
	public int getSailorsOdds() {
		return sailorsOdds;
	}
	
	/**
	 * Returns true if the player encounters pirates
	 * @return if pirates encountered
	 */
	public boolean encounterPirates() {
		return (randomGenerator.nextInt(100) < pirateDanger);
	}
	
	/**
	 * Returns true if the player encounters dangerous weather
	 * @return if dangerous weather encountered
	 */
	public boolean encounterWeatherEvent() {
		return (randomGenerator.nextInt(100) < weatherDanger);
	}
	
	/**
	 * Returns true if the player encounters lost sailors
	 * @return if lost sailors encountered
	 */
	public boolean encounterLostSailors() {
		return (randomGenerator.nextInt(100) < sailorsOdds);
	}
}


