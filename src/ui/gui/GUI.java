package ui.gui;

import core.GameEnvironment;
import core.Island;
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
	public void displayIslandInfo(Island island) {
		screen.quit();
		screen = new DisplayIslandInfoScreen(game, this, island);
		screen.show();

	}

	@Override
	public void store() {
		screen.quit();
		screen = new StoreScreen(game, this);
		screen.show();
	}

	@Override
	public void repair() {
		screen.quit();
		screen = new RepairScreen(game, this);
		screen.show();
	}

	@Override
	public void travel() {
		screen.quit();
		screen = new TravelScreen(game, this);
		screen.show();
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
