package core;
import java.util.Random;

/**
 * This class models a route between islands.
 * @author Dillon Pike, Daniel Pallesen
 * @version 1 May 2021
 */
public class Route {

	private Random randomGenerator = new Random();
	private Island island1;
	private Island island2;
	private int days;
	private int pirateDanger;
	private int weatherDanger;
	private int sailorsOdds;
	
	/**
	 * Creates a route with the given parameter values.
	 * @param island1
	 * @param island2
	 * @param days
	 * @param pirateDanger
	 * @param weatherDanger
	 * @param sailorsOdds
	 */
	public Route(Island island1, Island island2, int days, int pirateDanger, int weatherDanger, int sailorsOdds) {
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
	public Island[] getIslands(){
		Island[] islands= {island1,island2};
		return islands;
	}
	
	/**
	 * Returns number of days route takes
	 * @return days
	 */
	public int getDays(Ship ship) {
		return (days * (ship.getSpeed()/20));
	}
	
	/**
	 * Returns true if the player encounters pirates
	 * @return if pirates encountered
	 */
	public boolean encouterPirates() {
		return (randomGenerator.nextInt(100) <= pirateDanger);
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
	 * Returns true if the player encounters dangerous weather
	 * @return if dangerous weather encountered
	 */
	public boolean encouterWeatherEvent() {
		return (randomGenerator.nextInt(100) <= weatherDanger);
	}
	
	/**
	 * Returns true if the player encounters lost sailors
	 * @return if lost sailors encountered
	 */
	public boolean encouterLostSailors() {
		return (randomGenerator.nextInt(100) <= sailorsOdds);
	}
}


