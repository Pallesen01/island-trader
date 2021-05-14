package ui;

import java.util.ArrayList;
import java.util.Scanner;

import core.GameEnvironment;
import core.Ship;

public class TextUI implements GameUI{
	
	private final Scanner input;
	private GameEnvironment game;
	
	public TextUI() {
		this.input = new Scanner(System.in);
		input.useDelimiter("\\s*\n\\s*");
	}

	@Override
	public void start(GameEnvironment game) {
		String name = getName();
		int days = getValidInt(20, 50, DAYS_PROMPT, DAYS_ERROR);
		Ship ship = getShip();
	}

	@Override
	public void menu() {
		final MenuOption options[] = MenuOption.values();
		
		while (game.getDays() > 0) {
			printGameState();
			printMenuOptions();
			int choice = getValidInt(1, options.length, "Select Action to Perform:", INT_ERROR);
			MenuOption option = options[choice];
			switch (option) {
	            case SHIP_INFO: shipInfo();
	                     		break;
	            case GOODS:  	goods();
	                     		break;
	            case ISL_INFO:  islandInfo();
	                     		break;
	            case SHOP:  	shop();
	                     		break;
	            case TRAVEL:	travel();
	            				break;
			}
		}

	}

	@Override
	public void shipInfo() {
		System.out.print();
	}

	@Override
	public void goods() {
		// TODO Auto-generated method stub

	}

	@Override
	public void islandInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void travel() {
		// TODO Auto-generated method stub

	}
	
	
	private String getName() {
		while (true) {
			System.out.print(NAME_PROMPT);
			String name = input.nextLine();
			if (name.matches(NAME_REGEX)) {
				return name;
			}
			System.out.println(NAME_ERROR);
		}
	}
	
	/**
	 * Gets a valid integer from the user within a specific range.
	 * @param lowerBound
	 * @param upperBound
	 * @param prompt
	 * @param errorMsg
	 * @return
	 */
	private int getValidInt(int lowerBound, int upperBound, String prompt, String errorMsg) {
		while (true) {
			System.out.print(prompt);
			try {
				int choice = input.nextInt();
				if (lowerBound <= choice && choice <= upperBound ) {
					return choice;
				}
				System.out.println(errorMsg);
			} catch (java.util.InputMismatchException e) {
				input.nextLine();
			}
		}
	}
	
	
	private Ship getShip() {
		System.out.println(SHIP_HEADER);
		ArrayList<Ship> ships = game.getShips();
		int i = 0;
		for (Ship ship : ships) {
			System.out.println((i+1) + " - " + ship + "\n");
			i++;
		}
		
		int choice = getValidInt(1, ships.size(), SHIP_PROMPT, INT_ERROR);
		return ships.get(choice);
	}
	
	private void printGameState() {
		System.out.println(game.getDays()+" Days Remaining");
		System.out.println("Current Island: " + game.getIsland().getName());
	}
	
	private void printMenuOptions() {
		System.out.println("\nAvaliable Actions:");
		int i = 0;
		for (MenuOption option : MenuOption.values()) {
			System.out.println((i+1) + " - " + option.label);
		}
	}
}
