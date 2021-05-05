package core;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEnvironment {	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Ship> ships = ObjectsListGenerator.generateShip();
		ArrayList<Item> items = ObjectsListGenerator.generateItem();
		ArrayList<Item> weapons = ObjectsListGenerator.generateWeapon();
		ArrayList<Island> islands = ObjectsListGenerator.generateIsland();
		ObjectsListGenerator.generateRoute(islands);
		Ship playerShip;
		
		System.out.println("Playable ships:");
		for (int i = 0; i < ships.size(); i++) {
			System.out.println(i+" - "+ships.get(i).getName()+':');
			System.out.println("\tCrew Members: "+ships.get(i).getCrew());
			System.out.println("\tCargo Space: "+ships.get(i).getSpace());
			System.out.println("\tHealth: "+ships.get(i).getHealth());
			System.out.println("\tSpeed: "+ships.get(i).getSpeed());
			System.out.println("\tEndurance: "+ships.get(i).getEndurance()+'\n');
		}
		
		System.out.print("Choose a ship to captain: ");
		int selectedShip = input.nextInt();
		playerShip = ships.get(selectedShip);
		System.out.print(playerShip.getName() + " Selected");
	
		
	}
	

}
