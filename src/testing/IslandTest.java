package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.Island;
import core.Route;


class IslandTest {
	
	/**
	 * First island's name.
	 */
	private static final String testIslandName1 = "Test Island 1";
	
	/**
	 * Second island's name.
	 */
	private static final String testIslandName2 = "Test Island 2";
	
	/**
	 * First island used in testing.
	 */
	private static Island testIsland1;
	
	/**
	 * Second island used in testing.
	 */
	private static Island testIsland2;
	
	/**
	 * Route used in testing.
	 */
	private static Route testRoute;
	
	@BeforeAll
	static void initialiseIsland() {
		testIsland1 = new Island(testIslandName1);
		testIsland2 = new Island(testIslandName2);
		testRoute = new Route(testIsland1, testIsland2, 5, 5, 5, 5);
	}
	
	@Test
	void islandNameTest() {
		// Checks that the island's names were set correctly
		assertEquals(testIslandName1, testIsland1.getName());
		assertEquals(testIslandName2, testIsland2.getName());
	}
	
	@Test
	void addRouteTest() {
		// Checks that the islands start with no routes
		assertEquals(0, testIsland1.getRoutes().size());
		assertEquals(0, testIsland2.getRoutes().size());
		
		// Adds test route to first island and checks it was added
		testIsland1.addRoute(testRoute);
		assertEquals(testRoute, testIsland1.getRoutes().get(0));
		
		// Checks only one route was added
		assertEquals(1, testIsland1.getRoutes().size());
	}
}
