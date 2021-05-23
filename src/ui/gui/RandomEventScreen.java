package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;

public class RandomEventScreen extends Screen {
	
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public RandomEventScreen(GameEnvironment game) {
		super(game);
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	JFrame getFrame() {
		return frame;
	}

}
