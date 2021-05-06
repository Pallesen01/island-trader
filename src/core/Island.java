package core;
import java.util.ArrayList;

/**
 * This class models an island.
 * @author Dillon Pike, Daniel Pallesen
 * @version 21 April 2021
 */
public class Island {
	
	private String name;
	private Store store;
	private ArrayList<Route> routes = new ArrayList<Route>();
	
	public Island(String name) {
		this.name = name;
		this.store = new Store();
	}
	
	/**
	 * returns the name of the island
	 * @return island name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the store on the island
	 * @return island store
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * returns the routes to and from the island
	 * @return an ArrayList of route objects
	 */
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	/**
	 * Adds a route to or from the island
	 * @param The Route object to be added
	 */
	public void addRoute(Route route) {
		routes.add(route);
	}
}