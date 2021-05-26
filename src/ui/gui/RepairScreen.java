package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Ship;
import ui.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

/**
 * Shows the players gold, ship's health, and ship's repair cost and allows the player to repair their ship.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class RepairScreen extends Screen {

	/**
	 * Frame that holds all GUI elements.
	 */
	private JFrame frame;
	
	/**
	 * Label that displays the player's current gold.
	 */
	private JLabel goldLbl;
	
	/**
	 * Label that displays the ship's health.
	 */
	private JLabel healthLbl;
	
	/**
	 * Label that displays the cost to repair the player's ship.
	 */
	private JLabel costLbl;
	
	/**
	 * Label that displays the result of clicking the repair button.
	 */
	private JLabel resultLbl;

	/**
	 * Stores the game instance then creates and sets up the frame.
	 * @param game game instance
	 */
	public RepairScreen(GameEnvironment game) {
		super(game);
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}
	
	/**
	 * Repairs the ship if its below its maximum health and the player has enough gold.
	 */
	private void repair() {
		Ship ship = getGame().getShip();
		if (ship.getHealth() == ship.getMaxHealth()) {
			// Don't repair ship if it's at max health
			resultLbl.setText(GameUI.REPAIR_MAX);
		} else if (getGame().repairShip()) {
			// Update labels if repair is successful
			goldLbl.setText("Gold: " + getGame().getGold());
			costLbl.setText("Repair Cost: " + getGame().getShip().getRepairCost());
			healthLbl.setText("Ship Health: " + getGame().getShip().getHealth());
			resultLbl.setText(GameUI.REPAIR_SUCCESS);
		} else {
			resultLbl.setText(GameUI.REPAIR_FAIL);
		}
	}
	
	@Override
	JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> getGame().getUI().menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JLabel titleLbl = new JLabel("Repair " + getGame().getShip().getName());
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		costLbl = new JLabel("Repair Cost: " + getGame().getShip().getRepairCost());
		costLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnRepair = new JButton("Repair");
		btnRepair.addActionListener(e -> repair());
		btnRepair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRepair.setFocusable(false);
		btnRepair.setBackground(Color.LIGHT_GRAY);
		
		resultLbl = new JLabel("");
		resultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		healthLbl = new JLabel("Ship Health: " + getGame().getShip().getHealth());
		healthLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		goldLbl = new JLabel("Gold: " + getGame().getGold());
		goldLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleLbl, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
							.addComponent(btnRepair, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addComponent(resultLbl, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
						.addComponent(goldLbl)
						.addComponent(healthLbl)
						.addComponent(costLbl))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(goldLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(costLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(healthLbl)
					.addGap(33)
					.addComponent(resultLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRepair, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
