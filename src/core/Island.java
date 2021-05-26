package core;
import java.util.ArrayList;

/**
 * This class models an island that has a store and a list of routes to other islands.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class Island {
	
	/**
	 * Name of the island.
	 */
	private String name;
	
	/**
	 * Island's store.
	 */
	private Store store;
	
	/**
	 * Routes from the island to others.
	 */
	private ArrayList<Route> routes;
	
	/**
	 * Creates an island with the given name, gives it a store, 
	 * and gives it an empty ArrayList of routes from the island.
	 * @param name island's name
	 */
	public Island(String name) {
		this.name = name;
		this.store = new Store();
		this.routes = new ArrayList<Route>();
	}
	
	/**
	 * Returns the island's name.
	 * @return island's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the island's store.
	 * @return island's store
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * Returns the routes to and from the island.
	 * @return routes to and from the island.
	 */
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	/**
	 * Adds a route from the island to another one.
	 * @param route to be added.
	 */
	public void addRoute(Route route) {
		routes.add(route);
	}
}