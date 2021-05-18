package ui.gui;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import core.GameEnvironment;

public abstract class Screen {
	
    private JFrame frame;
    private final GameEnvironment game;

    
    protected Screen(String title, GameEnvironment game) {
        this.game = game;
        initialise(title);
    }
    
    private void initialise(final String title) {
        frame = new JFrame();
        frame.setTitle(title);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	System.out.println("Hi Daniel");
                game.endGame();
            }
        });

        initialise(frame);

        frame.pack();

        // Put frame in centre of screen
        frame.setLocationRelativeTo(null);
    }
    
    abstract void initialise(JFrame Frame);
    
    protected void show() {
        frame.setVisible(true);
    }
    
    protected GameEnvironment getGame() {
    	return game;
    }
    
    /**
     * Quits this screen. This should dispose of the screen as necessary.
     */
    void quit() {
        frame.dispose();
    }
}
