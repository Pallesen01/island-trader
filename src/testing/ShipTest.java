package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.ObjectsListGenerator;
import core.Ship;

class ShipTest {
	
	private Ship testShip;
	private ArrayList<Ship> ships;
	
	@BeforeAll
	void initialiseShips() {
		ships = ObjectsListGenerator.generateShip();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
