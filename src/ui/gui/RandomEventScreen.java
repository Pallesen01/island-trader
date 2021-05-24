package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Route;
import ui.gui.GUI.RandomEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RandomEventScreen extends Screen {
	
	private JFrame frame;
	private Route route;
	private RandomEvent event;

	/**
	 * Create the application.
	 */
	public RandomEventScreen(GameEnvironment game, Route route, RandomEvent event) {
		super(game);
		this.route = route;
		this.event = event;
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}
	
	private void setTitleText(JLabel titleLbl) {
		switch (event) {
			case PIRATES: titleLbl.setText("You've encountered a pirate ship!");
						  break;
			case WEATHER: titleLbl.setText("You've encountered a storm!");
						  break;
			case SAILORS: titleLbl.setText("You've encountered lost sailors!");
						  break;
		}
	}
	
	private void setMessageText(JLabel messageLbl) {
		switch (event) {
			case PIRATES: messageLbl.setText("You've encountered a pirate ship!");
						  break;
			case WEATHER: messageLbl.setText("You've encountered a storm!");
						  break;
			case SAILORS: messageLbl.setText("You've encountered lost sailors!");
						  break;
		}
	}
	
	private void continueGame() {
		switch (event) {
			case PIRATES: 
				if (route.encounterWeatherEvent()) {
					getGame().getUI().weatherEncounter(route);
					break;
				}
			case WEATHER: 
				if (route.encounterLostSailors()) {
					getGame().getUI().sailorsEncounter(route);
					break;
				}
			case SAILORS: 
				getGame().getUI().menu();
				break;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titleLbl = new JLabel();
		setTitleText(titleLbl);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel messageLbl = new JLabel();
		setMessageText(messageLbl);
		messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton continueBtn = new JButton("Continue");
		continueBtn.addActionListener(e -> continueGame());
		continueBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		continueBtn.setFocusable(false);
		continueBtn.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(messageLbl, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(titleLbl, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(continueBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addGap(18)
					.addComponent(messageLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
					.addComponent(continueBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
