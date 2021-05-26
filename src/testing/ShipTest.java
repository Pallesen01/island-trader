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
	
	// Random number generator
	private static Random randomGenerator;
	
	// Ship used in testing
	private static Ship testShip;
	
	// All ships in the game
	private static ArrayList<Ship> ships;
	
	// All items in the game
	private static ArrayList<Item> items;
	
	@BeforeAll
	static void initialiseShip() {
		randomGenerator = new Random();
		ships = ObjectsListGenerator.generateShip();
		testShip = ships.get(randomGenerator.nextInt(ships.size()));
		items = ObjectsListGenerator.generateItem();
	}

	@Test
	void cargoAddRemoveTest() {
		// Get two random items
		Item randomItem1 = items.get(randomGenerator.nextInt(items.size()));
		Item randomItem2 = items.get(randomGenerator.nextInt(items.size()));
		
		// Ensure the random items are different
		while (randomItem1 == randomItem2) {
			randomItem2 = items.get(randomGenerator.nextInt(items.size()));
		}
		
		// Add an item twice and check they were added
		testShip.addCargo(randomItem1);
		testShip.addCargo(randomItem1);
		assertEquals(randomItem1, testShip.getCargo().get(1));
		assertEquals(randomItem1, testShip.getCargo().get(2));
		
		// Size starts at 1 because each ship comes with a cannon
		assertEquals(3, testShip.getCargo().size());
		
		// Add another item and check it was added
		testShip.addCargo(randomItem2);
		assertEquals(randomItem2, testShip.getCargo().get(3));
		assertEquals(4, testShip.getCargo().size());
		
		// Remove the first item and check it was removed
		testShip.removeCargo(randomItem1);
		assertEquals(randomItem1, testShip.getCargo().get(1));
		assertEquals(randomItem2, testShip.getCargo().get(2));
		assertEquals(3, testShip.getCargo().size());
	}
	
	@Test
	void () {
		
	}

}
