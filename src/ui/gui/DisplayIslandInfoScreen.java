package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Island;
import core.Item;
import core.Route;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DisplayIslandInfoScreen extends Screen{
	
	private JFrame frame;
	private Island island;
	
	/**
	 * Create the application.
	 */
	protected DisplayIslandInfoScreen(GameEnvironment game, Island island) {
		super(game);
		frame = new JFrame();		
		this.island = island;
		initialiseFrame();
		configureFrame();	
		
	}
	
	/**
	 * Returns 2D array of items where each row has a name, price, size, and description.
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
	 * Returns 2D array of routes where each row has the island it travels to, days the route takes, and random event chance.
	 * @param routes an ArrayList of routes
	 * @return 2D array of route info
	 */
	private Object[][] makeRouteArray(ArrayList<Route> routes) {
		Object routeArray[][] = new Object[routes.size()][];
		int i = 0;
		String islandName;
		for (Route route : routes) {
			if (route.getIslands()[0].getName() == island.getName()) {
				islandName = route.getIslands()[1].getName();
			} else {
				islandName = route.getIslands()[0].getName();
			}
			Object routeInfo[] = {islandName,route.getDays(getGame().getShip().getSpeed()),route.getPirateDanger(),route.getWeatherDanger(),route.getSailorsOdds()};
			routeArray[i] = routeInfo;
			i++;
		}
		return routeArray;
	}

	@Override
	JFrame getFrame() {
		return frame;
	}
	
	private void initialiseFrame() {
		frame.setBounds(100, 100, 620, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titleLbl = new JLabel("Island Info - " + island.getName());
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> getGame().getUI().islandInfo());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton menuBtn = new JButton("Menu");
		menuBtn.addActionListener(e -> getGame().getUI().menu());
		menuBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBtn.setFocusable(false);
		menuBtn.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane routeScrollPane = new JScrollPane();
		
		JLabel routeLbl = new JLabel("Routes");
		routeLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		routeLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel buyLbl = new JLabel("Items available for purchase");
		buyLbl.setHorizontalAlignment(SwingConstants.LEFT);
		buyLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel storeLbl = new JLabel(island.getName() + " Store");
		storeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		storeLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JScrollPane buyScrollPane = new JScrollPane();
		
		JLabel sellLbl = new JLabel("Items available to sell");
		sellLbl.setHorizontalAlignment(SwingConstants.LEFT);
		sellLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane sellScrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(titleLbl, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
					.addComponent(menuBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(storeLbl, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(routeScrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(routeLbl, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(buyLbl, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(sellScrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(sellLbl, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(buyScrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(titleLbl)
					.addGap(16)
					.addComponent(routeLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(routeScrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(storeLbl)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buyLbl, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buyScrollPane, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(sellLbl, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sellScrollPane, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(menuBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		String[] itemColumnText = {"Name", "Price", "Size", "Description"};
		Object[][] sellArray = makeItemArray(island.getStore().getSells());
		JTable sellTable = new JTable(sellArray, itemColumnText);
		sellTable.setFillsViewportHeight(true);
		sellTable.setShowGrid(false);
		sellTable.setEnabled(false);
		sellScrollPane.setViewportView(sellTable);
		
		Object[][] buyArray = makeItemArray(island.getStore().getBuys());
		JTable buyTable = new JTable(buyArray, itemColumnText);
		buyTable.setFillsViewportHeight(true);
		buyTable.setShowGrid(false);
		buyTable.setEnabled(false);
		buyScrollPane.setViewportView(buyTable);

		String[] routeColumnText = {"Island", "Days", "Pirate Danger", "Weather Danger", "Lost Sailors Chance"};
		Object[][] routeArray = makeRouteArray(island.getRoutes());
		JTable routeTable = new JTable(routeArray, routeColumnText);
		routeTable.setFillsViewportHeight(true);
		routeTable.setShowGrid(false);
		routeTable.setEnabled(false);
		routeTable.setRowSelectionAllowed(false);
		routeScrollPane.setViewportView(routeTable);
		frame.getContentPane().setLayout(groupLayout);
	}
}
