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
import javax.swing.JTextPane;
import javax.swing.UIManager;

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
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> ui.menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		
		JLabel shipLbl_1_1 = new JLabel((String) null);
		shipLbl_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane txtpnAaa = new JTextPane();
		txtpnAaa.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpnAaa.setEditable(false);
		txtpnAaa.setBackground(UIManager.getColor("Button.background"));
		txtpnAaa.setText(getGame().getShip().toString());
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(166)
									.addComponent(txtpnAaa, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
									.addGap(184)
									.addComponent(shipLbl_1_1, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE))))
						.addComponent(titleLbl, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(123)
							.addComponent(shipLbl_1_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(txtpnAaa, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
