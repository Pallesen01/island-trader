package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class endGameScreen extends Screen {
	private JFrame frame;
	private String reason;
	private GameEnvironment game;
	
	/**
	 * Create the application.
	 */
	public endGameScreen(GameEnvironment game, String reason) {
		super(game);
		frame = new JFrame();
		this.game = game;
		this.reason = reason;
		initialiseFrame();
		configureFrame();
		
		
	}

	@Override
	JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	void initialiseFrame() {
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Game Over");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblNewLabel_1 = new JLabel(reason);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Trader name: " + game.getName());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2_1 = new JLabel("Selected game duration: "+ game.getTotalDays());
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Actual game duration: " + (game.getTotalDays() - game.getDaysLeft()));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Final gold: " + game.getGold());
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Final cargo value: " + game.getShip().getCargoValue());
		lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Final score: " + game.calcScore());
		lblNewLabel_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> this.quit());
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setFocusable(false);
		btnExit.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1_1_1, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1_1_1_1, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
						.addComponent(btnExit, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addGap(79)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
