package ui.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import core.GameEnvironment;

public abstract class Screen {
	
    private final GameEnvironment game;

    
    protected Screen(GameEnvironment game) {
        this.game = game;
    }
    
    protected void configureFrame() {
        JFrame frame = getFrame();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	System.out.println("Hi DANIEL");
                game.endGame();
            }
        });

        frame.pack();

        // Put frame in centre of screen
        frame.setLocationRelativeTo(null);
    }
    
    
    protected void show() {
    	JFrame frame = getFrame();
        frame.setVisible(true);
    }
    
    protected GameEnvironment getGame() {
    	return game;
    }
    
    /**
     * Quits this screen. This should dispose of the screen as necessary.
     */
    void quit() {
    	JFrame frame = getFrame();
        frame.dispose();
    }
    
    abstract JFrame getFrame();
}
