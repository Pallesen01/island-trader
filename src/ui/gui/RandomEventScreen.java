package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Route;
import ui.GameUI;
import ui.gui.GUI.RandomEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class RandomEventScreen extends Screen {
	
	private JFrame frame;
	private Route route;
	private int resultValue;
	private RandomEvent event;

	/**
	 * Create the application.
	 */
	public RandomEventScreen(GameEnvironment game, Route route, int resultValue, RandomEvent event) {
		super(game);
		this.route = route;
		this.resultValue = resultValue;
		this.event = event;
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}
	
	private void setTitleText(JLabel titleLbl) {
		switch (event) {
			case PIRATES: titleLbl.setText(GameUI.PIRATE_ENCOUNTER);
						  break;
			case WEATHER: titleLbl.setText(GameUI.WEATHER_ENCOUNTER);
						  break;
			case SAILORS: titleLbl.setText(GameUI.SAILOR_ENCOUNTER);
						  break;
		}
	}
	
	private void setMessageText(JTextPane messageTextPane) {
		switch (event) {
			case PIRATES:
				if (getGame().getShip().getHealth() > 0) {
					messageTextPane.setText("But you defeated them! Your ship has taken " + resultValue + " damage.");
				} else {
					if (!getGame().pirateLossOutcome()) {
						getGame().loseGold();
						messageTextPane.setText(GameUI.PIRATE_UNSATISFIED);
						getGame().endGame("Killed by Pirates");
					}
					else {
						messageTextPane.setText(GameUI.PIRATE_SATISFIED);
					}
					// getGame().endGame("Killed by Pirates"); Not needed?
				}
				break;
			case WEATHER: 
				if (getGame().getShip().getHealth() > 0) {
					messageTextPane.setText("Your ship has taken " + resultValue + " damage.");
				} else {
					messageTextPane.setText("Your ship has been destroyed in the storm.");
				}
				break;
			case SAILORS: 
				messageTextPane.setText("The sailors give you " + resultValue + " gold as a reward for rescuing them.");
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
					getGame().getUI().sailorEncounter(route);
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
		
		JButton continueBtn = new JButton("Continue");
		continueBtn.addActionListener(e -> continueGame());
		continueBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		continueBtn.setFocusable(false);
		continueBtn.setBackground(Color.LIGHT_GRAY);
		
		JTextPane outcomeTextPane = new JTextPane();
		outcomeTextPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		setMessageText(outcomeTextPane);
		outcomeTextPane.setBackground(UIManager.getColor("menu"));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleLbl, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(continueBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(outcomeTextPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLbl)
					.addGap(18)
					.addComponent(outcomeTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
					.addComponent(continueBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
