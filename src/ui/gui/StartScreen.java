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
 * 
 * @author Dillon Pike, Daniel Pallesen
 * @version 18 May 2021
 */
public class StartScreen extends Screen {
	
	private JTextField nameField;
	private JLabel daysLabel;
	private JSlider daysSlider;
	private JLabel shipLabel;
	private JTable shipTable;
	private JLabel nameErrorLabel;
	private JLabel shipErrorLabel;
	
	/**
	 */
	protected StartScreen(GameEnvironment game) {
		super("Island Trader", game);
	}
	
	/**
	 * 
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
			nameErrorLabel.setText(null);
		} else {
			nameErrorLabel.setText(GameUI.NAME_ERROR);
			valid = false;
		} if (shipTable.getSelectedRowCount() == 1) {
			shipErrorLabel.setText(null);
		} else {
			shipErrorLabel.setText("You must select a ship.");
			valid = false;
		}
		return valid;
		
	}

	/**
	 * Initialise the contents of the frame.
	 */
	void initialise(JFrame frame) {
		frame.setBounds(100, 100, 620, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel welcomeLabel = new JLabel("Welcome to Island Trader!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel nameLabel = new JLabel("What's your trader name?");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		daysLabel = new JLabel("How many days you would like to play for?");
		
		daysSlider = new JSlider();
		daysSlider.setMinorTickSpacing(1);
		daysSlider.setMajorTickSpacing(5);
		daysSlider.setSnapToTicks(true);
		daysSlider.setPaintLabels(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setValue(35);
		daysSlider.setMaximum(50);
		daysSlider.setMinimum(20);
		
		shipLabel = new JLabel("What ship would you like to captain?");
		
		JScrollPane tableScrollPane = new JScrollPane();
		
		shipTable = new JTable();
		tableScrollPane.setViewportView(shipTable);
		shipTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ArrayList<Ship> ships = getGame().getShips();
		Object shipsInfo[][] = new Object[ships.size()][];
		int i = 0;
		for (Ship ship : ships) {
			Object shipInfo[] = {ship.getName(), ship.getCrew(), ship.getSpace(), ship.getHealth(), ship.getSpeed(), ship.getEndurance()};
			shipsInfo[i] = shipInfo;
			i++;
		}
		
		shipTable.setModel(new DefaultTableModel(shipsInfo, new String[] {"Name", "Crew Members", "Cargo Space", "Health", "Speed", "Endurance"}) {
			private static final long serialVersionUID = 5594750025154585643L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(e -> finish());
		
		nameErrorLabel = new JLabel("");
		nameErrorLabel.setForeground(Color.RED);
		
		shipErrorLabel = new JLabel("");
		shipErrorLabel.setForeground(Color.RED);
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
												.addComponent(daysLabel)
												.addGap(18)
												.addComponent(daysSlider, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(nameLabel)
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(nameErrorLabel)
													.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
										.addGap(47))
									.addComponent(confirmBtn)
									.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(shipLabel)
									.addGap(18)
									.addComponent(shipErrorLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 271, GroupLayout.PREFERRED_SIZE)))
							.addGap(21))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(welcomeLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameErrorLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(daysLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(daysSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(shipLabel)
						.addComponent(shipErrorLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(confirmBtn)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
