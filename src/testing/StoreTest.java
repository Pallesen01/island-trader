package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.Item;
import core.Store;

class StoreTest {

	/**
	 * Generates a new store until it sells and buys a common item, and checks
	 * that the item is sold for a higher or equal price than the store sells it for.
	 * Does this until 5 items have been compared.
	 */
	@Test
	void priceTest() {
		int counter = 0;
		while (counter < 5) {
			Store testStore = new Store();
			ArrayList<Item> buys = testStore.getBuys();
			ArrayList<Item> sells = testStore.getSells();
			
			for (Item buyItem : buys) {
				for (Item sellItem : sells) {
					if (buyItem.getName().equals(sellItem.getName())) {
						assertEquals(true, buyItem.getPrice() >= sellItem.getPrice());
						counter++;
					}
				}
			}
		}
	}
}
