package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Island;
import core.Route;
import core.Ship;
import ui.GameUI;
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
	private GameUI ui;
	private JFrame frame;
	private GameEnvironment game;
	private Island island;
	private JTable tableRoutes;
	/**
	 * Create the application.
	 */
	protected DisplayIslandInfoScreen(GameEnvironment game, GameUI ui, Island island) {
		super(game);
		frame = new JFrame();		
		this.game = game;
		this.ui = ui;
		this.island = island;
		initialiseFrame();
		configureFrame();	
		
	}

	@Override
	JFrame getFrame() {
		return frame;
	}
	
	private void initialiseFrame() {
		frame.setBounds(100, 100, 620, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Island Info - " + island.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> ui.islandInfo());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton btnMenu = new JButton("Menu");
		backBtn.addActionListener(e -> ui.menu());
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMenu.setFocusable(false);
		btnMenu.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane tableScrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Routes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
					.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tableScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(16)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		ArrayList<Route> routes = island.getRoutes();
		String[] columns = {"Island","Days","Pirate Danger","Weather Danger", "Lost Sailors Chance"};
		Object routesInfo[][] = new Object[routes.size()][];
		int i = 0;
		String islandName;
		for (Route route : routes) {
			if (route.getIslands()[0].getName() == island.getName()) {
				islandName = route.getIslands()[1].getName();
			}
			else {
				islandName = route.getIslands()[0].getName();
			}
			Object routeInfo[] = {islandName,route.getDays(game.getShip().getSpeed()),route.getPirateDanger(),route.getWeatherDanger(),route.getSailorsOdds()};
			routesInfo[i] = routeInfo;
			i++;
		}
		tableRoutes = new JTable(routesInfo, columns);
		tableRoutes.setEnabled(false);
		tableRoutes.setRowSelectionAllowed(false);
		tableScrollPane.setViewportView(tableRoutes);
		frame.getContentPane().setLayout(groupLayout);
	}
}
