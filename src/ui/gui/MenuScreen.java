package ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.border.LineBorder;

import core.GameEnvironment;
import ui.GameUI;

public class MenuScreen extends Screen{
	
	private final String title = "Island Trader";
	
	private GameUI ui;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	protected MenuScreen(GameEnvironment game, GameUI ui) {	
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

	/**
	 * Initialize the contents of the frame.
	 */
	void initialiseFrame() {
		frame.setTitle(title);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel daysLbl = new JLabel(getGame().getDays() + " Days Remaining");
		daysLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel goldLbl = new JLabel("Gold: " + getGame().getGold());
		goldLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton shipInfoBtn = new JButton("View Ship Info");
		shipInfoBtn.addActionListener(e -> ui.shipInfo());
		shipInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		shipInfoBtn.setFocusable(false);
		shipInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton visitStoreBtn = new JButton("Visit Store");
		visitStoreBtn.addActionListener(e -> ui.store());
		visitStoreBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		visitStoreBtn.setFocusable(false);
		visitStoreBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton repairShipBtn = new JButton("Repair Ship");
		repairShipBtn.addActionListener(e -> ui.repair());
		repairShipBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		repairShipBtn.setFocusable(false);
		repairShipBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewGoodsbtn = new JButton("View Goods");
		viewGoodsbtn.addActionListener(e -> ui.goods());
		viewGoodsbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewGoodsbtn.setFocusable(false);
		viewGoodsbtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewIslandInfoBtn = new JButton("View Island Info");
		viewIslandInfoBtn.addActionListener(e -> ui.islandInfo());
		viewIslandInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewIslandInfoBtn.setFocusable(false);
		viewIslandInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton travelBtn = new JButton("Travel");
		travelBtn.addActionListener(e -> ui.travel());
		travelBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		travelBtn.setFocusable(false);
		travelBtn.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Idk maybe put an image here");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(daysLbl)
						.addComponent(goldLbl)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(shipInfoBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
								.addComponent(visitStoreBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(viewGoodsbtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(viewIslandInfoBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(repairShipBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(travelBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))))
					.addGap(14))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(daysLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(goldLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(viewIslandInfoBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(viewGoodsbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(shipInfoBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(repairShipBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(visitStoreBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(travelBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
