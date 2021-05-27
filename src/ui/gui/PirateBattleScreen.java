package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.ObjectsListGenerator;
import core.Route;
import core.Ship;
import ui.GameUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;

public class PirateBattleScreen extends Screen {
	private JFrame frame;
	private GameEnvironment game;
	private Ship pirateShip;
	private String battleText;
	private JTextPane battleTextPane;
	private Route route;
	private int initialHealth;
	private GUI ui;
	private JLabel lblsShipHealth;
	private JLabel lblPirateShipHealth;
	
	/**
	 * Create the application
	 * @param game, instance of game environment
	 */
	public PirateBattleScreen(GUI ui, GameEnvironment game, Route route) {
		super(game);
		frame = new JFrame();
		this.game = game;
		this.initialHealth = game.getShip().getHealth();
		this.route = route;
		this.ui = ui;		
		this.pirateShip = game.generatePirateShip();
		this.battleText = "";
		initialiseFrame();
		configureFrame();
	}
	
	private void progressGame() {
		battleText += game.playerTurn(pirateShip);
		if (pirateShip.getHealth() <= 0) {
			ui.pirateResolutionScreen(route, (initialHealth - game.getShip().getHealth()));
		}
		battleText += game.pirateTurn(pirateShip);
		if (game.getShip().getHealth() <= 0) {
			ui.pirateResolutionScreen(route, (initialHealth - game.getShip().getHealth()));
		}
		battleTextPane.setText(battleText);
		lblsShipHealth.setText(game.getName() + "'s Ship Health: " + game.getShip().getHealth() + "/" + game.getShip().getMaxHealth());
		lblPirateShipHealth.setText("Pirate Ship Health: " + pirateShip.getHealth() + "/" + pirateShip.getMaxHealth());
	}

	@Override
	JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 520, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Pirate Encounter");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblsShipHealth = new JLabel(game.getName() + "'s Ship Health: " + game.getShip().getHealth() + "/" + game.getShip().getMaxHealth());
		
		lblPirateShipHealth = new JLabel("Pirate Ship Health: " + pirateShip.getHealth() + "/" + pirateShip.getMaxHealth());
		lblPirateShipHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnFire = new JButton("Fire!");
		btnFire.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnFire.setFocusable(false);
		btnFire.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblsShipHealth, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(lblPirateShipHealth, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnFire, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblsShipHealth)
						.addComponent(lblPirateShipHealth))
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnFire, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		btnFire.addActionListener(e -> progressGame());
		
		battleTextPane = new JTextPane();
		scrollPane.setViewportView(battleTextPane);
		battleTextPane.setBackground(UIManager.getColor("Button.background"));
		battleTextPane.setText(battleText);
		battleTextPane.setEditable(false);
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
