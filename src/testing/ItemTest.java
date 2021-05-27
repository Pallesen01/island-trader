package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.Item;

class ItemTest {
	
	/**
	 * Name for the test item.
	 */
	private final static String TEST_ITEM_NAME = "Test Item";
	
	/**
	 * Description for the test item.
	 */
	private final static String TEST_ITEM_DESC = "This is a test";
	
	/**
	 * Size of the test item.
	 */
	private final static int TEST_ITEM_SIZE = 15;
	
	/**
	 * Base price of the test item.
	 */
	private final static int TEST_ITEM_BASE_PRICE = 50;
	
	/**
	 * Item used for testing.
	 */
	private static Item testItem;
	
	/**
	 * Initialises the test item.
	 */
	@BeforeAll
	static void initialiseItem() {
		testItem = new Item(TEST_ITEM_NAME, TEST_ITEM_DESC, TEST_ITEM_SIZE, TEST_ITEM_BASE_PRICE, false);
	}
	
	/**
	 * Checks that the constructor set the item's attributes correctly.
	 */
	@Test
	void AttributeTest() {
		assertEquals(TEST_ITEM_NAME, testItem.getName());
		assertEquals(TEST_ITEM_DESC, testItem.getDesc());
		assertEquals(TEST_ITEM_SIZE, testItem.getSize());
		assertEquals(TEST_ITEM_BASE_PRICE, testItem.getBasePrice());
		assertEquals(false, testItem.isWeapon());
		assertEquals(null, testItem.getSoldAt());
		assertEquals(-1, testItem.getSoldFor());
	}
}
