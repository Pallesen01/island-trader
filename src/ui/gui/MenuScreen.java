package ui.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;

import core.GameEnvironment;
import javax.swing.JProgressBar;

public class MenuScreen extends Screen{
	
	private JFrame frame;

	/**
	 * Create the application.
	 */
	protected MenuScreen(GameEnvironment game) {	
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
	 * Initialize the contents of the frame.
	 */
	void initialiseFrame() {
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel daysLbl = new JLabel(getGame().getDays() + " Days Remaining");
		daysLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel goldLbl = new JLabel("Gold: " + getGame().getGold());
		goldLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton shipInfoBtn = new JButton("View Ship Info");
		shipInfoBtn.addActionListener(e -> getGame().getUI().shipInfo());
		shipInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		shipInfoBtn.setFocusable(false);
		shipInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton visitStoreBtn = new JButton("Visit Store");
		visitStoreBtn.addActionListener(e -> getGame().getUI().store());
		visitStoreBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		visitStoreBtn.setFocusable(false);
		visitStoreBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton repairShipBtn = new JButton("Repair Ship");
		repairShipBtn.addActionListener(e -> getGame().getUI().repair());
		repairShipBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		repairShipBtn.setFocusable(false);
		repairShipBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewGoodsbtn = new JButton("View Goods");
		viewGoodsbtn.addActionListener(e -> getGame().getUI().goods());
		viewGoodsbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewGoodsbtn.setFocusable(false);
		viewGoodsbtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewIslandInfoBtn = new JButton("View Island Info");
		viewIslandInfoBtn.addActionListener(e -> getGame().getUI().islandInfo());
		viewIslandInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewIslandInfoBtn.setFocusable(false);
		viewIslandInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton travelBtn = new JButton("Travel");
		travelBtn.addActionListener(e -> getGame().getUI().travel());
		travelBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		travelBtn.setFocusable(false);
		travelBtn.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Idk maybe put an image here");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(10, 11));
		progressBar.setMaximumSize(new Dimension(10, 11));
		progressBar.setMaximum(getGame().getStartDays());
		progressBar.setValue(getGame().getDays());
		
		JLabel islandLbl = new JLabel("Current Island: " + getGame().getIsland().getName());
		islandLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(daysLbl)
							.addGap(18)
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
							.addGap(14))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(islandLbl)
							.addContainerGap(471, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(goldLbl)
							.addContainerGap(621, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(shipInfoBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
								.addComponent(visitStoreBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(repairShipBtn, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(viewGoodsbtn, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(travelBtn, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(viewIslandInfoBtn, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
							.addGap(14))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(daysLbl)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(islandLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(goldLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(viewIslandInfoBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(viewGoodsbtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(shipInfoBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(repairShipBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(visitStoreBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(travelBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
