package ui.gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.GameEnvironment;
import core.Ship;
// import seng201.rocketmanager.ui.gui.MainScreen;
import ui.GameUI;

public class GUI implements GameUI {

	private GameEnvironment game;
    private Screen screen;
	
	@Override
	public void start(GameEnvironment game) {
		this.game = game;
        screen = new StartScreen(game);
        screen.show();
	}

	@Override
	public void menu() {
		screen.quit();
		screen = new MenuScreen(game, this);
		screen.show();		
	}

	@Override
	public void shipInfo() {
		// TODO Auto-generated method stub
		System.out.println("Ship Info");

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
	public void store() {
		// TODO Auto-generated method stub

	}

	@Override
	public void repair() {
		

	}

	@Override
	public void travel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pirateEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void weatherEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sailorsEncounter() {
		// TODO Auto-generated method stub

	}

}
