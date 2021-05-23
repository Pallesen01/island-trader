package ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Route;
import ui.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class TravelScreen extends Screen {

	private GameUI ui;
	private JFrame frame;
	private JTable routeTable;
	private JLabel errorLbl;

	/**
	 * Create the application.
	 */
	public TravelScreen(GameEnvironment game, GameUI ui) {
		super(game);
		frame = new JFrame();
		this.ui = ui;
		initialiseFrame();
		configureFrame();
	}

	@Override
	JFrame getFrame() {
		return frame;
	}
	
	private void travel() {
		if (routeTable.getSelectedRowCount() == 0) {
			errorLbl.setText("You must select a route.");
		} else {
			Route route = getGame().getIsland().getRoutes().get(routeTable.getSelectedRow());
			if (getGame().getShip().getHealth() != getGame().getShip().getMaxHealth()) {
				errorLbl.setText(GameUI.TRAVEL_SHIP_ERROR);
			} else if (!getGame().canTravelRoute(route)) {
				errorLbl.setText(GameUI.TRAVEL_GOLD_ERROR);
			} else {
				getGame().travelRoute(route);
				ui.menu();
			}
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 620, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> ui.menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane routeScrollPane = new JScrollPane();
		
		JLabel titleLbl = new JLabel("Travel to Another Island");
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel routeLbl = new JLabel("Routes");
		routeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(e -> travel());
		confirmBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmBtn.setFocusable(false);
		confirmBtn.setBackground(Color.LIGHT_GRAY);
		
		errorLbl = new JLabel("");
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
							.addComponent(confirmBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addComponent(titleLbl, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(routeLbl)
						.addComponent(routeScrollPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(errorLbl))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(routeLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(routeScrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		routeTable = new JTable();
		routeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ArrayList<Route> routes = getGame().getIsland().getRoutes();
		String[] columnNames = {"Island", "Days", "Pirate Danger", "Weather Danger", "Lost Sailors Chance"};
		Object routesTable[][] = new Object[routes.size()][];
		int i = 0;
		String islandName;
		for (Route route : routes) {
			if (route.getIslands()[0].getName() == getGame().getIsland().getName()) {
				islandName = route.getIslands()[1].getName();
			} else {
				islandName = route.getIslands()[0].getName();
			}
			Object routeRow[] = {islandName, route.getDays(getGame().getShip().getSpeed()), route.getPirateDanger(), route.getWeatherDanger(), route.getSailorsOdds()};
			routesTable[i] = routeRow;
			i++;
		}
		routeTable.setModel(new DefaultTableModel(routesTable, columnNames) {
			private static final long serialVersionUID = -156423262431076534L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		routeScrollPane.setViewportView(routeTable);
		frame.getContentPane().setLayout(groupLayout);
	}

}
