package ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import core.GameEnvironment;
import core.Ship;
import ui.GameUI;

/**
 * This class creates the starting screen for the game.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public class StartScreen extends Screen {
	
	/**
	 * Frame that holds all GUI elements
	 */
    private JFrame frame;
    
    /**
     * Field for the player to enter their name
     */
	private JTextField nameField;
	
	/**
	 * Slider for the player to choose the number of days 
	 */
	private JSlider daysSlider;
	
	/**
	 * Table of available ships
	 */
	private JTable shipTable;
	
	/**
	 * Displays message if the player's name doesn't meet requirements
	 */
	private JLabel nameErrorLbl;
	
	/**
	 * Displays message if player hasn't selected a ship
	 */
	private JLabel shipErrorLbl;
	
	/**
	 * Stores the game instance then creates and sets up the frame.
	 */
	protected StartScreen(GameEnvironment game) {
		super(game);
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}
	
	/**
	 * If the user's input is valid, the GUI passes the input to the game.
	 */
	private void finish() {
		if (checkInput()) {
			String name = nameField.getText();
			int days = daysSlider.getValue();
			Ship ship = getGame().getShips().get(shipTable.getSelectedRow());
			getGame().finishSetup(name, days, ship);
		}
	}
	
	/** 
	 * Checks that the user has entered a valid name and selected a ship and displays error messages if not.
	 * @return true if user has input a valid name and selected a ship, otherwise false
	 */
	private boolean checkInput() {
		boolean valid = true;
		if (nameField.getText().matches(GameUI.NAME_REGEX)) {
			nameErrorLbl.setText(null);
		} else {
			nameErrorLbl.setText(GameUI.NAME_ERROR);
			valid = false;
		} if (shipTable.getSelectedRowCount() == 1) {
			shipErrorLbl.setText(null);
		} else {
			shipErrorLbl.setText("You must select a ship.");
			valid = false;
		}
		return valid;
	}
	
    /**
     * Returns the screen's frame.
     * @return screen's frame
     */
	@Override
	JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Returns 2D array of ships where each row has the ship's name, crew, space, max health, speed, and endurance.
	 * @param ships an ArrayList of ships
	 * @return 2D array of ship info
	 */
	private Object[][] makeShipArray(ArrayList<Ship> ships) {
		Object shipArray[][] = new Object[ships.size()][];
		int i = 0;
		for (Ship ship : ships) {
			Object shipRow[] = {ship.getName(), ship.getCrew(), ship.getMaxSpace(), ship.getMaxHealth(), ship.getSpeed(), ship.getEndurance()};
			shipArray[i] = shipRow;
			i++;
		}
		return shipArray;
	}

	/**
	 * Initialise the contents of the frame.
	 */
	void initialiseFrame() {
		frame.setBounds(100, 100, 620, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel welcomeLabel = new JLabel("Welcome to Island Trader!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel nameLabel = new JLabel("What's your trader name?");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel daysLbl = new JLabel("How many days you would like to play for?");
		
		daysSlider = new JSlider();
		daysSlider.setMinorTickSpacing(1);
		daysSlider.setMajorTickSpacing(5);
		daysSlider.setSnapToTicks(true);
		daysSlider.setPaintLabels(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setValue(35);
		daysSlider.setMaximum(50);
		daysSlider.setMinimum(20);
		
		JLabel shipLbl = new JLabel("What ship would you like to captain?");
		
		JScrollPane tableScrollPane = new JScrollPane();
		
		shipTable = new JTable();
		shipTable.setShowGrid(false);
		tableScrollPane.setViewportView(shipTable);
		shipTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		String[] columnText = {"Name", "Crew Members", "Cargo Space", "Health", "Speed", "Endurance"};
		Object[][] shipArray = makeShipArray(getGame().getShips());
		shipTable.setModel(new DefaultTableModel(shipArray, columnText) {
			private static final long serialVersionUID = 5594750025154585643L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(e -> finish());
		
		nameErrorLbl = new JLabel("");
		nameErrorLbl.setForeground(Color.RED);
		
		shipErrorLbl = new JLabel("");
		shipErrorLbl.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(daysLbl)
												.addGap(18)
												.addComponent(daysSlider, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(nameLabel)
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(nameErrorLbl)
													.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
										.addGap(47))
									.addComponent(confirmBtn)
									.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(shipLbl)
									.addGap(18)
									.addComponent(shipErrorLbl)
									.addPreferredGap(ComponentPlacement.RELATED, 271, GroupLayout.PREFERRED_SIZE)))
							.addGap(21))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(welcomeLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameErrorLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(daysLbl))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(daysSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(shipLbl)
						.addComponent(shipErrorLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(confirmBtn)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
