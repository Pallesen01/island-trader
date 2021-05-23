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
	private JTable tableRoutes;
	private JTable tableSold;
	private JTable tableBought;
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
	 * Returns 2D array from items where each row has a name, price, size, and description.
	 * @param items an ArrayList of items
	 * @return 2D array of item info
	 */
	private Object[][] makeItemTable(ArrayList<Item> items) {
		Object itemTable[][] = new Object[items.size()][];
		int i = 0;
		for (Item item : items) {
			Object itemRow[] = {item.getName(), item.getPrice(), item.getSize(), item.getDesc()};
			itemTable[i] = itemRow;
			i++;
		}
		return itemTable;
	}

	@Override
	JFrame getFrame() {
		return frame;
	}
	
	private void initialiseFrame() {
		frame.setBounds(100, 100, 620, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Island Info - " + island.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> getGame().getUI().islandInfo());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(e -> getGame().getUI().menu());
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMenu.setFocusable(false);
		btnMenu.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane tableScrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Routes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_1_1 = new JLabel("Items Sold");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel(island.getName() + " Store");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Items Bought");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
					.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(16)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(34)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		String[] columns = {"Name", "Price", "Size", "Description"};
		Object[][] data = makeItemTable(island.getStore().getBuys());
		tableBought = new JTable(data, columns);
		tableBought.setEnabled(false);
		scrollPane_1.setViewportView(tableBought);
		
		data = makeItemTable(island.getStore().getSells());
		tableSold = new JTable(data, columns);
		tableSold.setEnabled(false);
		scrollPane.setViewportView(tableSold);
		
		ArrayList<Route> routes = island.getRoutes();
		String[] columnNames = {"Island", "Days", "Pirate Danger", "Weather Danger", "Lost Sailors Chance"};
		Object routesInfo[][] = new Object[routes.size()][];
		int i = 0;
		String islandName;
		for (Route route : routes) {
			if (route.getIslands()[0].getName() == island.getName()) {
				islandName = route.getIslands()[1].getName();
			} else {
				islandName = route.getIslands()[0].getName();
			}
			Object routeInfo[] = {islandName,route.getDays(getGame().getShip().getSpeed()),route.getPirateDanger(),route.getWeatherDanger(),route.getSailorsOdds()};
			routesInfo[i] = routeInfo;
			i++;
		}
		tableRoutes = new JTable(routesInfo, columnNames);
		tableRoutes.setEnabled(false);
		tableRoutes.setRowSelectionAllowed(false);
		tableScrollPane.setViewportView(tableRoutes);
		frame.getContentPane().setLayout(groupLayout);
	}
}
