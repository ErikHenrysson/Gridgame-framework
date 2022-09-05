package classes;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.Gridgame;
import framework.Observer;



public class WindowObserver<T> extends JFrame implements Observer, KeyListener{
	private JFrame frame;
	private Gridgame<T> game;
	private JPanel panel;
	
	/**
	 * Initiates the GUI frame and adds a KeyListener to it.
	 * Creates the first representation
	 * @param game An instance of the grid game
	 */
	public WindowObserver(Gridgame<T> game) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		this.game = game;
		
		panel = new JPanel(new GridLayout(game.getHeight(), game.getWidth(), 0,0));
		for(int i = 0; i < game.getHeight(); i++)
			for(int j = 0; j<game.getWidth(); j++)
				panel.add(game.getTile(i, j).getLabel());
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);	
	}
	
	
	@Override
	public void update(Gridgame game) {
		frame.remove(panel);
		
		panel = new JPanel(new GridLayout(game.getHeight(), game.getWidth(), 0,0));
		for(int i = 0; i < game.getHeight(); i++)
			for(int j = 0; j<game.getWidth(); j++)
				panel.add(game.getTile(i, j).getLabel());
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			game.move("down");
		} else if (e.getKeyCode()==KeyEvent.VK_UP) {
			game.move("up");
		} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			game.move("right");
		} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			game.move("left");
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			game.move("restart");
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			game.move("save");
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			game.move("load");
		}
		
	}
	
	/* Not used */
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
