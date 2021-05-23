package ui.gui;

import core.GameEnvironment;
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
		screen.quit();
		screen = new ShipInfoScreen(game, this);
		screen.show();
	}

	@Override
	public void goods() {
		screen.quit();
		screen = new GoodsScreen(game, this);
		screen.show();
	}

	@Override
	public void islandInfo() {
		screen.quit();
		screen = new IslandInfoScreen(game, this);
		screen.show();

	}

	@Override
	public void store() {
		// TODO Auto-generated method stub

	}

	@Override
	public void repair() {
		screen.quit();
		screen = new RepairScreen(game, this);
		screen.show();
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
