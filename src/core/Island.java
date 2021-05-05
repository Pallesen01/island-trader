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
	private ArrayList<Route> routes;
	
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
	
	public void addRoute(Route route) {
		routes.add(route);
	}
}