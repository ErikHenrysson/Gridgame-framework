package sokoban;

import java.io.IOException;
import java.util.LinkedList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import classes.*;
import framework.*;

public class SokobanGame extends Gridgame{
	
	private boolean godMode = true;
	/**
	 * Create the game and add the terminal and window observer
	 * @param mapList LinkedList containing integer representations of the maps
	 */
	public SokobanGame(LinkedList<int[][]> mapList) {
		super(mapList);
		CommandObserver terminalObserver = new CommandObserver();
		WindowObserver<SokobanGame> windowObserver = new WindowObserver<SokobanGame>(this);
		this.addObserver(terminalObserver);
		this.addObserver(windowObserver);
		setPreviousType(1);
	}
	
	/**
	 * Create the game and add the terminal and window observer
	 * Changes the controller to the specified one
	 * @param mapList LinkedList containing integer representations of the maps
	 * @param controller A controller you want for your game
	 */
	public SokobanGame(LinkedList<int[][]> mapList, ControllerStrategy controller) {
		super(mapList, controller);
		CommandObserver terminalObserver = new CommandObserver();
		WindowObserver<SokobanGame> windowObserver = new WindowObserver<SokobanGame>(this);
		this.addObserver(terminalObserver);
		this.addObserver(windowObserver);
		setPreviousType(1);
	}

	
	
	@Override
	public void moveUp() {
		if(checkWall(getPlayerRow()-1, getPlayerCol())) {
			return;
		}
		if((checkWall(getPlayerRow()-2, getPlayerCol()) || checkCrate(getPlayerRow()-2, getPlayerCol())) 
			&& checkCrate(getPlayerRow()-1, getPlayerCol())){
			return;
		}
		if(checkCrate(getPlayerRow()-1, getPlayerCol())){
			if(getTile(getPlayerRow()-1, getPlayerCol()).getValue() == 3) {
				setTile(getPlayerRow()-1, getPlayerCol(),createTile(1));
			} else if (getTile(getPlayerRow()-1, getPlayerCol()).getValue() == 4) {
				setTile(getPlayerRow()-1, getPlayerCol(),createTile(2));
			}	
			moveCrate(getPlayerRow()-2, getPlayerCol());
		}
		
		setTile(getPlayerRow(), getPlayerCol(), createTile(getPreviousType()));
		decPlayerRow();
		
		setPreviousType (getTile(getPlayerRow(), getPlayerCol()).getValue());
		
		
		setTile(getPlayerRow(), getPlayerCol(), createTile(5));
	}

	@Override
	public void moveDown() {	
		if(checkWall(getPlayerRow()+1, getPlayerCol())) {
			return;
		}
		if((checkWall(getPlayerRow()+2, getPlayerCol()) || checkCrate(getPlayerRow()+2, getPlayerCol())) 
			&& checkCrate(getPlayerRow()+1, getPlayerCol())){
			return;
		}
		
		if(checkCrate(getPlayerRow()+1, getPlayerCol())){
			if(getTile(getPlayerRow()+1, getPlayerCol()).getValue() == 3) {
				setTile(getPlayerRow()+1, getPlayerCol(),createTile(1));
			} else if (getTile(getPlayerRow()+1, getPlayerCol()).getValue() == 4) {
				setTile(getPlayerRow()+1, getPlayerCol(),createTile(2));
			}	
			moveCrate(getPlayerRow()+2, getPlayerCol());
		}

		setTile(getPlayerRow(), getPlayerCol(), createTile(getPreviousType()));
		incPlayerRow();
		
		setPreviousType(getTile(getPlayerRow(), getPlayerCol()).getValue());
		
		setTile(getPlayerRow(), getPlayerCol(), createTile(5));
	}

	@Override
	public void moveLeft() {
		if(checkWall(getPlayerRow(), getPlayerCol()-1)) {
			return;
		}
		if((checkWall(getPlayerRow(), getPlayerCol()-2) || checkCrate(getPlayerRow(), getPlayerCol()-2)) 
			&& checkCrate(getPlayerRow(), getPlayerCol()-1)){
			return;
		}
		
		if(checkCrate(getPlayerRow(), getPlayerCol()-1)){
			if(getTile(getPlayerRow(), getPlayerCol()-1).getValue() == 3) {
				setTile(getPlayerRow(), getPlayerCol()-1,createTile(1));
			} else if (getTile(getPlayerRow(), getPlayerCol()-1).getValue() == 4) {
				setTile(getPlayerRow(), getPlayerCol()-1,createTile(2));
			}
			moveCrate(getPlayerRow(), getPlayerCol()-2);
		}
		
		setTile(getPlayerRow(), getPlayerCol(), createTile(getPreviousType()));
		decPlayerCol();
	
		
		setPreviousType(getTile(getPlayerRow(), getPlayerCol()).getValue());
		
		
		setTile(getPlayerRow(), getPlayerCol(), createTile(5));
	}

	@Override
	public void moveRight() {
		//if there is a wall in the direction you want to go
		if(checkWall(getPlayerRow(), getPlayerCol()+1)) {
			return;
		}
		//if there is an immovable crate in the direction you want to go
		if((checkWall(getPlayerRow(), getPlayerCol()+2) || checkCrate(getPlayerRow(), getPlayerCol()+2)) 
			&& checkCrate(getPlayerRow(), getPlayerCol()+1)){
			return;
		}
		//if there is a movable create in the direction you want to go
		if(checkCrate(getPlayerRow(), getPlayerCol()+1)){
			//if its a normal crate, create a blank space
			if(getTile(getPlayerRow(), getPlayerCol()+1).getValue() == 3) {
				setTile(getPlayerRow(), getPlayerCol()+1,createTile(1));
				//else if there is a marked crate, create a marked blank
			} else if (getTile(getPlayerRow(), getPlayerCol()+1).getValue() == 4) {
				setTile(getPlayerRow(), getPlayerCol()+1,createTile(2));
			}
			//move the crate one step in the direction youre going
			moveCrate(getPlayerRow(), getPlayerCol()+2);
		}

		setTile(getPlayerRow(), getPlayerCol(), createTile(getPreviousType()));
		incPlayerCol();
		
		setPreviousType(getTile(getPlayerRow(), getPlayerCol()).getValue());
		
		setTile(getPlayerRow(), getPlayerCol(), createTile(5));
	}
	

	@Override
	public boolean checkCrate(int row, int col) {
		//crate found getTile(row, col).getValue() == 3 || getTile(row, col).getValue() == 4
		if(getTile(row, col).isSolid() && getTile(row, col).isMovable()) {
			return true;
		} 
		
		return false;
	}
	
	/**
	 * create a crate of the correct type at the specified location
	 */
	@Override
	public void moveCrate(int row, int col){
		//if the crate will be at a marked spot create a marked crate
		if(getTile(row, col).getValue() == 2) {
			setTile(row, col, createTile(4));
			return;
		}
		//if it was unmarked, create a normal crate.
		setTile(row, col, createTile(3));	
	}
	
	
	
	@Override
	public boolean checkWall(int row, int col) {
		if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
			return true;
		}
		if(getTile(row, col).isSolid() && !getTile(row, col).isMovable() && !godMode) {
			return true;
		} 
		return false;
	}
	
	@Override
	public Tile createTile(int tileValue) {
		if(tileValue == 1) {
			java.net.URL url = getClass().getResource("blank.png");
			ImageIcon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			return new SokobanTile(label, 1);
		} else if(tileValue == 2) {
			java.net.URL url = getClass().getResource("blankmarked.png");
			ImageIcon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			return new SokobanTile(label, 2);
		} else if(tileValue == 3) {
			java.net.URL url = getClass().getResource("crate.png");
			ImageIcon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			return new SokobanTile(label, 3, true, true);
		} else if(tileValue == 4) {
			java.net.URL url = getClass().getResource("cratemarked.png");
			ImageIcon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			return new SokobanTile(label, 4, true, true);
		} else if(tileValue == 5) {
			java.net.URL url = getClass().getResource("player.png");
			ImageIcon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			SokobanTile tile = new SokobanTile(label, 5,true); 
			return tile;
		} else if(tileValue == 6) {
			java.net.URL url = getClass().getResource("wall.png");
			ImageIcon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			return new SokobanTile(label, 6, true, false);
		}
		return null;
	}

	/**
	 * if there are no blankmarked left on the grid, the map is done
	 */
	public boolean checkVictory() {
		if (getPreviousType() == 2)
			return false;
		for (int row = 0; row < getHeight(); row++)
			for(int col = 0; col< getWidth(); col++) {
				if(getTile(row,col).getValue() == 2)
					return false;
			}
		
		try {
			playApplaus();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		nextMap();
		System.out.println("grattis du har vunnit");
		return true;
	}
	
}
