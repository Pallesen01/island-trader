package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.Item;
import core.ObjectsListGenerator;
import core.Ship;

class ShipTest {
	
	/**
	 * Random number generator.
	 */
	private static Random randomGenerator;
	
	/**
	 * Ship used in testing.
	 */
	private static Ship testShip;
	
	/**
	 * All ships in the game.
	 */
	private static ArrayList<Ship> ships;
	
	/**
	 * All items in the game.
	 */
	private static ArrayList<Item> items;
	
	/**
	 * Initialises the ships and item arrays, as well as the test ship.
	 */
	@BeforeAll
	static void initialiseTests() {
		randomGenerator = new Random();
		items = ObjectsListGenerator.generateItem();
		ships = ObjectsListGenerator.generateShip();
		
		// Picks a random ship for testing
		testShip = ships.get(randomGenerator.nextInt(ships.size()));
	}

	/**
	 * Tests that adding and removing cargo works as expected.
	 */
	@Test
	void cargoAddRemoveTest() {
		// Get three random items
		Item item1 = items.get(0);
		Item item2 = items.get(1);
		Item item3 = items.get(2);

		// Add an item twice and check they were added
		testShip.addCargo(item1);
		testShip.addCargo(item1);
		assertEquals(item1, testShip.getCargo().get(1));
		assertEquals(item1, testShip.getCargo().get(2));
		
		// Size starts at 1 because each ship comes with a cannon
		assertEquals(3, testShip.getCargo().size());
		
		// Add another item and check it was added
		testShip.addCargo(item2);
		assertEquals(item2, testShip.getCargo().get(3));
		assertEquals(4, testShip.getCargo().size());
		
		// Remove the first item and check it was removed
		testShip.removeCargo(item1);
		assertEquals(item1, testShip.getCargo().get(1));
		assertEquals(item2, testShip.getCargo().get(2));
		assertEquals(3, testShip.getCargo().size());
		
		// Try to remove item not in cargo
		assertEquals(false, testShip.removeCargo(item3));
	}
	
	/**
	 * Checks that the ship's health is kept between 0 and its maximum health
	 * when setting its health.
	 */
	@Test
	void healthConstrainedTest() {
		// Checks that ship's health is kept at 0 or above
		testShip.setHealth(-1);
		assertEquals(0, testShip.getHealth());
		
		// Checks that ship's health is kept at its max health or below
		testShip.setHealth(testShip.getMaxHealth() + 1);
		assertEquals(testShip.getMaxHealth(), testShip.getHealth());
	}
	
	/**
	 * Checks that the ship's cargo and weapons are empty after running the emptyCargo method.
	 */
	@Test
	void emptyCargoTest() {
		// Empties cargo and checks that the ship's cargo and weapons are empty
		testShip.emptyCargo();
		assertEquals(0, testShip.getCargo().size());
		assertEquals(0, testShip.getWeapons().size());
	}

}
