package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Island;
import core.Ship;
import ui.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class IslandInfoScreen extends Screen {
	private GameUI ui;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public IslandInfoScreen(GameEnvironment game, GameUI ui) {
		super(game);
		frame = new JFrame();
		
		JLabel lblNewLabel = new JLabel("Island Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Which Island would you like to view?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> ui.menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JComboBox comboBoxIslands = new JComboBox();
		ArrayList<Island> islands = getGame().getIslands();
		String[] islandNames = new String[islands.size()];
		int i = 0;
		for (Island island : islands) {
			if (island.getName() == game.getIsland().getName()) {
				islandNames[i] = island.getName() + " (Current Island)";
			}
			else {
				islandNames[i] = island.getName();
			}			
			i++;
		}
		comboBoxIslands.setModel(new DefaultComboBoxModel(islandNames));
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfirm.setFocusable(false);
		btnConfirm.setBackground(Color.LIGHT_GRAY);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
					.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(147, Short.MAX_VALUE)
					.addComponent(comboBoxIslands, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGap(141))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(31)
					.addComponent(comboBoxIslands, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
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
	private void initialiseFrame() {
		frame.setBounds(100, 100, 530, 295);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
