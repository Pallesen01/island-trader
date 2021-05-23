package ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class DillonsMainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DillonsMainScreen window = new DillonsMainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DillonsMainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel daysLbl = new JLabel("35 Days Remaining");
		daysLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel goldLbl = new JLabel("Gold: 250");
		goldLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton shipInfoBtn = new JButton("View Ship Info");
		shipInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		shipInfoBtn.setFocusable(false);
		shipInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton visitStoreBtn = new JButton("Visit Store");
		visitStoreBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		visitStoreBtn.setFocusable(false);
		visitStoreBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton repairShipBtn = new JButton("Repair Ship");
		repairShipBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		repairShipBtn.setFocusable(false);
		repairShipBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewGoodsbtn = new JButton("View Goods");
		viewGoodsbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewGoodsbtn.setFocusable(false);
		viewGoodsbtn.setBackground(Color.LIGHT_GRAY);
		
		JButton viewIslandInfoBtn = new JButton("View Island Info");
		viewIslandInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		viewIslandInfoBtn.setFocusable(false);
		viewIslandInfoBtn.setBackground(Color.LIGHT_GRAY);
		
		JButton travelBtn = new JButton("Travel");
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(daysLbl, Alignment.LEADING)
						.addComponent(goldLbl, Alignment.LEADING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(shipInfoBtn, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(visitStoreBtn, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(viewGoodsbtn, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(viewIslandInfoBtn, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(repairShipBtn, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(travelBtn, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(14, Short.MAX_VALUE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(repairShipBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
							.addComponent(visitStoreBtn, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addComponent(travelBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
