package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.Item;
import core.Store;

class StoreTest {
	
	/**
	 * Store used in testing.
	 */
	private static Store testStore;

	@Test
	void priceTest() {
		testStore = new Store();
		ArrayList<Item> buys = testStore.getBuys();
		ArrayList<Item> sells = testStore.getSells();
		for (Item buyItem : buys) {
			for (Item sellItem : sells) {
				if (buyItem.getName().equals(sellItem.getName())) {
					assertEquals(true, buyItem.getPrice() > sellItem.getPrice());
				}
			}
		}
	}
}
