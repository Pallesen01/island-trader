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
	private GameUI ui;

	/**
	 * Create the application.
	 */
	protected MenuScreen(GameEnvironment game, GameUI ui) {	
		super("Island Trader", game);	
		this.ui = ui;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialise(JFrame frame) {
		frame.setBounds(100, 100, 620, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		splitPane.setFocusable(false);
		splitPane.setFocusTraversalKeysEnabled(false);
		splitPane.setOpaque(false);
		splitPane.setEnabled(false);
		splitPane.setForeground(Color.WHITE);
		splitPane.setResizeWeight(0.7);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		splitPane.setLeftComponent(panel_1);
		
		JLabel days_label = new JLabel(getGame().getDays() + " Days Remaining");
		
		JLabel gold_label = new JLabel(getGame().getGold() + " Gold");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(gold_label, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(days_label, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(559, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(days_label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(gold_label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(267, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		splitPane.setRightComponent(panel);
		
		JButton shipInfoBtn = new JButton("View Ship Information");
		shipInfoBtn.addActionListener(e -> ui.shipInfo());
		shipInfoBtn.setFocusable(false);
		shipInfoBtn.setMinimumSize(new Dimension(80, 20));
		shipInfoBtn.setMaximumSize(new Dimension(80, 20));
		shipInfoBtn.setFont(new Font("Arial", Font.BOLD, 16));
		shipInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton visitStoreBtn = new JButton("Visit Store");
		visitStoreBtn.addActionListener(e -> ui.store());
		visitStoreBtn.setFocusable(false);
		visitStoreBtn.setMinimumSize(new Dimension(80, 20));
		visitStoreBtn.setMaximumSize(new Dimension(80, 20));
		visitStoreBtn.setFont(new Font("Arial", Font.BOLD, 16));
		visitStoreBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewGoodsbtn = new JButton("View Goods");
		viewGoodsbtn.addActionListener(e -> ui.goods());
		viewGoodsbtn.setFocusable(false);
		viewGoodsbtn.setMinimumSize(new Dimension(80, 20));
		viewGoodsbtn.setMaximumSize(new Dimension(80, 20));
		viewGoodsbtn.setFont(new Font("Arial", Font.BOLD, 16));
		viewGoodsbtn.setBackground(Color.LIGHT_GRAY);
		
		JButton repairShipBtn = new JButton("Repair Ship");
		repairShipBtn.addActionListener(e -> ui.repair());
		repairShipBtn.setFocusable(false);
		repairShipBtn.setMinimumSize(new Dimension(80, 20));
		repairShipBtn.setMaximumSize(new Dimension(80, 20));
		repairShipBtn.setFont(new Font("Arial", Font.BOLD, 16));
		repairShipBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewIslandInfoBtn = new JButton("View Island Information");
		viewIslandInfoBtn.addActionListener(e -> ui.islandInfo());
		viewIslandInfoBtn.setFocusable(false);
		viewIslandInfoBtn.setMinimumSize(new Dimension(80, 20));
		viewIslandInfoBtn.setMaximumSize(new Dimension(80, 20));
		viewIslandInfoBtn.setFont(new Font("Arial", Font.BOLD, 16));
		viewIslandInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton travelBtn = new JButton("Travel");
		travelBtn.addActionListener(e -> ui.travel());
		travelBtn.setFocusable(false);
		travelBtn.setMinimumSize(new Dimension(80, 20));
		travelBtn.setMaximumSize(new Dimension(80, 20));
		travelBtn.setFont(new Font("Arial", Font.BOLD, 16));
		travelBtn.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(shipInfoBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(viewGoodsbtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(viewIslandInfoBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(visitStoreBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(repairShipBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(travelBtn, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
					.addGap(14))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(shipInfoBtn, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(viewGoodsbtn, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(viewIslandInfoBtn, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(travelBtn, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(repairShipBtn, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(visitStoreBtn, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addGap(38))
		);
		panel.setLayout(gl_panel);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
