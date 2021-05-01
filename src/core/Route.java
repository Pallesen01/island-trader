package core;

/**
 * This class models a route between islands.
 * @author Dillon Pike, Daniel Pallesen
 * @version 1 May 2021
 */
public class Route {

	private Island island1;
	private Island island2;
	private int days;
	private int pirateDanger;
	private int weatherDanger;
	
	/**
	 * Creates a route with the given parameter values.
	 * @param island1
	 * @param island2
	 * @param days
	 * @param pirateDanger
	 * @param weatherDanger
	 */
	public Route(Island island1, Island island2, int days, int pirateDanger, int weatherDanger) {
		this.island1 = island1;
		this.island2 = island2;
		this.days = days;
		this.pirateDanger = pirateDanger;
		this.weatherDanger = weatherDanger;
		
	}
	/**
	 * Returns a list with the islands the route goes between
	 * @return island list
	 */
	public Island[] getIslands(){
		Island[] islands= {island1,island2};
		return islands;
	}
}


