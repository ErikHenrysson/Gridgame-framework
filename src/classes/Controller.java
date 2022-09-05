		package classes;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import framework.*;

public class Controller implements ControllerStrategy{

	
	Gridgame game;
	String [] moves;
	
	/**
	 * Creates the controller for the game
	 * @param game an instance of the game
	 */
	public Controller(Gridgame game) {
		this.game = game;
	}
	
	/**
	 * Creates the controller for the game
	 * @param game an instance of the game
	 * @param moves predefined moves to be executed at a later point
	 */
	public Controller(Gridgame game, String[] moves) {
		this.game = game;
		this.moves = moves;
	}
	

	public void excecute() throws InterruptedException {
		for(String s : moves) {
			TimeUnit.MILLISECONDS.sleep(150);
			switch(s) {
			case "up": move("up");
			break;
			case "down": move("down");
			break;
			case "left": move("left");
			break;
			case "right": move("right");
			break;
			}
		}
	}
	
	public void inputStream(String [] inputs) {
		moves = inputs;
	}
	
	@Override
	public void move(String move) {
		switch(move) {
		case "up": game.moveUp();
		break;
		case "down": game.moveDown();
		break;
		case "left": game.moveLeft();
		break;
		case "right": game.moveRight();
		break;
		case "save": game.save();
		break;
		case "load": game.load();
		break;
		case "restart": game.restart();
		break;
		}
		
	}
}
