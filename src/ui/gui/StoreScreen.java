package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Item;
import ui.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Displays the player's gold and a list of items the player can choose to buy and sell.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class StoreScreen extends Screen {
	
	/**
	 * First part of message to the user when they haven't selected
	 * an item to buy/sell.
	 */
	private final String NOT_SELECTED = "You must select an item";
	
	/**
	 * Frame that holds all GUI elements.
	 */
	private JFrame frame;
	
	/**
	 * Table that displays all the items the player can buy.
	 */
	private JTable buyTable;
	
	/**
	 * Table that displays all the items the player can sell
	 */
	private JTable sellTable;
	
	/**
	 * Label displaying the player's current gold.
	 */
	private JLabel goldLbl;
	
	/**
	 * Label displaying the result of the player attempting to buy or sell an item.
	 */
	private JLabel resultLbl;

	/**
	 * Stores the game instance then creates and sets up the frame.
	 * @param game game instance
	 */
	public StoreScreen(GameEnvironment game) {
		super(game);
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}
	
	@Override
	JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Gets the item selected in the buy table and buys it if the player has enough gold.
	 * Sets the result label's text depending on the outcome.
	 */
	private void buy() {
		if (buyTable.getSelectedRowCount() == 0) {
			resultLbl.setText(NOT_SELECTED + " to buy.");
		} else {
			Item item = getGame().getStore().getBuys().get(buyTable.getSelectedRow());
			if (getGame().buyItem(item)) {
				resultLbl.setText(GameUI.BOUGHT);
				goldLbl.setText("Gold: " + getGame().getGold());
			} else {
				resultLbl.setText(GameUI.BUY_FAIL);
			}
		}
	}
	
	/**
	 * Gets the item selected in the buy table and sells it if its in the ship's cargo.
	 * Sets the result label's text depending on the outcome.
	 */
	private void sell() {
		if (sellTable.getSelectedRowCount() == 0) {
			resultLbl.setText(NOT_SELECTED + " to sell.");
		} else {
			Item item = getGame().getStore().getSells().get(sellTable.getSelectedRow());
			if (getGame().sellItem(item)) {
				resultLbl.setText(GameUI.SOLD);
				goldLbl.setText("Gold: " + getGame().getGold());
			} else {
				resultLbl.setText(GameUI.SELL_FAIL);
			}
		}
	}
	
	/**
	 * Returns 2D array from items where each row has a name, price, size, and description.
	 * @param items an ArrayList of items
	 * @return 2D array of item info
	 */
	private Object[][] makeItemArray(ArrayList<Item> items) {
		Object itemArray[][] = new Object[items.size()][];
		int i = 0;
		for (Item item : items) {
			Object itemRow[] = {item.getName(), item.getPrice(), item.getSize(), item.getDesc()};
			itemArray[i] = itemRow;
			i++;
		}
		return itemArray;
	}
	
	/**
	 * Initialise the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 760, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel storeLbl = new JLabel(getGame().getIsland().getName() + " Store");
		storeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		storeLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JScrollPane buyScrollPane = new JScrollPane();
		
		JLabel buyLbl = new JLabel("Items available for purchase:");
		buyLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel sellLbl = new JLabel("Items available to sell:");
		sellLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JScrollPane sellScrollPane = new JScrollPane();
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> getGame().getUI().menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton buyBtn = new JButton("Buy");
		buyBtn.addActionListener(e -> buy());
		buyBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buyBtn.setFocusable(false);
		buyBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton sellBtn = new JButton("Sell");
		sellBtn.addActionListener(e -> sell());
		sellBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sellBtn.setFocusable(false);
		sellBtn.setBackground(Color.LIGHT_GRAY);
		
		resultLbl = new JLabel("");
		resultLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		goldLbl = new JLabel("Gold: " + getGame().getGold());
		goldLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(storeLbl, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
						.addComponent(goldLbl)
						.addComponent(buyLbl)
						.addComponent(buyScrollPane, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
						.addComponent(sellLbl)
						.addComponent(sellScrollPane, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(resultLbl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sellBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(buyBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(storeLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(goldLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buyLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buyScrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(sellLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sellScrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(sellBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(buyBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(resultLbl))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
			
		sellTable = new JTable();
		sellTable.setShowGrid(false);
		sellTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		String[] columnText = {"Name", "Price", "Size", "Description"};
		Object[][] sellArray = makeItemArray(getGame().getStore().getSells());
		sellTable.setModel(new DefaultTableModel(sellArray, columnText) {
			private static final long serialVersionUID = -2398083386409854464L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		sellTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		sellScrollPane.setViewportView(sellTable);
		
		buyTable = new JTable();
		buyTable.setShowGrid(false);
		buyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		Object[][] buyArray = makeItemArray(getGame().getStore().getBuys());
		buyTable.setModel(new DefaultTableModel(buyArray, columnText) {
			private static final long serialVersionUID = -2398083386409854464L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		buyTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		buyScrollPane.setViewportView(buyTable);
		frame.getContentPane().setLayout(groupLayout);
	}
}
