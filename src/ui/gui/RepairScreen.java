package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import ui.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class RepairScreen extends Screen {

	private GameUI ui;
	private JFrame frame;
	private int gold;
	private int repairCost;

	/**
	 * Create the application.
	 */
	public RepairScreen(GameEnvironment game, GameUI ui) {
		super(game);
		frame = new JFrame();
		gold = game.getGold();
		repairCost = game.getShip().getRepairCost();
		this.ui = ui;
		initialiseFrame();
		configureFrame();
	}
	
	private void repair() {
		if (gold < repairCost) {
			
		}
	}
	
	@Override
	JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> ui.menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JLabel titleLbl = new JLabel("Repair " + getGame().getShip().getName());
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel costLbl = new JLabel("Repair Cost: " + repairCost);
		costLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnRepair = new JButton("Repair");
		btnRepair.addActionListener(e -> repair());
		btnRepair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRepair.setFocusable(false);
		btnRepair.setBackground(Color.LIGHT_GRAY);
		
		JLabel resultLbl = new JLabel("");
		resultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel healthLabel = new JLabel("Ship Health: " + getGame().getShip().getHealth());
		healthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel goldLbl = new JLabel("Gold: " + gold);
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
						.addComponent(healthLabel)
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
					.addComponent(healthLabel)
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
