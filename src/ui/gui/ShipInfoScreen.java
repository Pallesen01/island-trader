package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import ui.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;

public class ShipInfoScreen extends Screen {

	private GameUI ui;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ShipInfoScreen(GameEnvironment game, GameUI ui) {
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
	private void initialiseFrame() {
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel titleLbl = new JLabel(getGame().getName() + "'s Ship");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel shipLbl = new JLabel(getGame().getShip().toString());
		shipLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> ui.menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleLbl, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(shipLbl, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addGap(18)
					.addComponent(shipLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
