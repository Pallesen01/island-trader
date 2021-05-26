package ui.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import core.GameEnvironment;

/**
 * Abstract class for GUI screens that contains methods and attributes used across all of them
 * and an abstract class that must be implemented in each one.
 * @author Dillon Pike, Daniel Pallesen
 * @version 25 May 2021
 */
public abstract class Screen {
	
	/**
	 * Title for the screen's title bar
	 */
	private final String title = "Island Trader";
	
	/**
	 * Instance of the game.
	 */
    private final GameEnvironment game;
    
    /**
     * Stores the game instance.
     * @param game game instance
     */
    protected Screen(GameEnvironment game) {
        this.game = game;
    }
    
    /**
     * Configures the frame with attributes common to every screen.
     */
    protected void configureFrame() {
        JFrame frame = getFrame();
        frame.setTitle(title);
        // Centre the frame
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.endGame("Game Closed");
            }
        });
    }
    
    /**
     * Sets the frame to be visible.
     */
    protected void show() {
    	JFrame frame = getFrame();
        frame.setVisible(true);
    }
    
    /**
     * Returns the game instance.
     * @return game instance
     */
    protected GameEnvironment getGame() {
    	return game;
    }
    
    /**
     * Closes the screen.
     */
    void quit() {
    	JFrame frame = getFrame();
        frame.dispose();
    }
    
    /**
     * Returns the screen's frame.
     * @return screen's frame
     */
    abstract JFrame getFrame();
}
