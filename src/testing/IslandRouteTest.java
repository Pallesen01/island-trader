package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.Island;
import core.Route;


class IslandRouteTest {
	
	/**
	 * First island's name.
	 */
	private static final String TEST_ISLAND_NAME_1 = "Test Island 1";
	
	/**
	 * Second island's name.
	 */
	private static final String TEST_ISLAND_NAME_2 = "Test Island 2";
	
	/**
	 * Values for the days and random event chances in the route.
	 */
	private static final int ROUTE_VALUE = 5;
	
	/**
	 * Base ship speed for getting the route's number of days.
	 */
	private static final int BASE_SPEED = 20;
	
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
	
	/**
	 * Creates test islands and test route.
	 */
	@BeforeAll
	static void initialiseIsland() {
		testIsland1 = new Island(TEST_ISLAND_NAME_1);
		testIsland2 = new Island(TEST_ISLAND_NAME_2);
		testRoute = new Route(testIsland1, testIsland2, ROUTE_VALUE, ROUTE_VALUE, ROUTE_VALUE, ROUTE_VALUE);
	}
	
	/**
	 * Checks that the island's names were set correctly
	 */
	@Test
	void islandNameTest() {
		assertEquals(TEST_ISLAND_NAME_1, testIsland1.getName());
		assertEquals(TEST_ISLAND_NAME_2, testIsland2.getName());
	}
	
	/**
	 * Checks that the islands start with no routes and routes are added correctly.
	 */
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
	
	/**
	 * Check that the route attributes were set correctly in the constructor.
	 */
	@Test
	void attributeTest() {
		Island[] islands = testRoute.getIslands();
		if (islands[0].getName().equals(TEST_ISLAND_NAME_1)) {
			assertEquals(TEST_ISLAND_NAME_2, islands[1].getName());
		} else if (islands[1].getName().equals(TEST_ISLAND_NAME_1)) {
			assertEquals(TEST_ISLAND_NAME_2, islands[0].getName());
		} else {
			fail("Islands in route aren't set correctly in constructor.");
		}
		
		assertEquals(ROUTE_VALUE, testRoute.getDays(BASE_SPEED));
		assertEquals(ROUTE_VALUE, testRoute.getPirateDanger());
		assertEquals(ROUTE_VALUE, testRoute.getWeatherDanger());
		assertEquals(ROUTE_VALUE, testRoute.getSailorsOdds());
	}
}
