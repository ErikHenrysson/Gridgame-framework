package framework;

import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import classes.*;

public abstract class Gridgame<T>{
	
	private Tile[][] grid;
	private LinkedList<int[][]> mapList;
	
	private LinkedList<Observer> observers = new LinkedList<Observer>();
	private ControllerStrategy controller = new Controller(this);
	
	private int[][] saved;
	private int savedPreviousType;
	private int[][] restartMap;
	
	private int previousType;
	private int playerRow;
	private int playerCol;
	
	private AudioPlayer backgroundMusic;
	private AudioPlayer applaus;
	/**
	 * Initiate the game and create the first map
	 * @param mapList
	 */
	public Gridgame(LinkedList<int[][]> mapList) {
		this.mapList = mapList;
		grid = createMap(mapList.getFirst());
		restartMap = getIntMap();
		initApplaus();
	}
	
	/**
	 * Initiate the game and create the first map
	 * set a controller strategy
	 * @param mapList
	 * @param controller
	 */
	public Gridgame(LinkedList<int[][]> mapList, ControllerStrategy controller) {
		this.mapList = mapList;
		this.controller = controller;
		grid = createMap(mapList.getFirst());
		restartMap = getIntMap();
		
		try {
			backgroundMusic = new AudioPlayer("bgsong.wav");
			backgroundMusic.playLoop();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param c controller to replace the default controller
	 */
	public void newContoller(Controller c) {
		controller = c;
	}
	/**
	 * 
	 * @return the current state of the game
	 */
	public Tile [][] getMap(){
		return grid;
	}
	
	/**
	 * 
	 * @return an integer representation of the current map state
	 */
	public int[][] getIntMap(){
		int [][] result = new int[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				result[i][j] = grid[i][j].getValue();
			}
		}
		return result;
	}
	
	public void executeinputs() throws InterruptedException {
		controller.excecute();
		updateObservers();
	}
	
	
	/**
	 * 
	 * @return the amount of horizontal boxes
	 */
	public int getWidth() {
		return grid[0].length;
	}
	
	/**
	 * Set the current grid to the specified grid
	 * @param grid
	 */
	public void setGrid(Tile [][] grid){
		this.grid = grid;
	}
	
	/**
	 * removes the current map from the map pool and starts the next one
	 */
	public void nextMap(){
		if(mapList.isEmpty())
			return;
		saved = null;
		mapList.removeFirst();
		if(mapList.isEmpty()) {
			System.out.println("You have completed all the maps!");
			return;
		}
		setGrid(createMap(mapList.getFirst()));
		restartMap = getIntMap();
		updateObservers();
	}
	
	/**
	 * Swap the current map to the saved one
	 * @param map
	 */
	public void swapMap(int [][] map) {
		if(!mapList.isEmpty())
		mapList.removeFirst();
		
		mapList.addFirst(map);
		setGrid(createMap(map));
		updateObservers();
	}
	
	/**
	 * saves current state of the
	 */
	public void save() {
		savedPreviousType = getPreviousType();
		saved = getIntMap();
	}
	
	/**
	 * Load the saved state of the game
	 */
	public void load() {
		//mapList.addFirst(saved.);
		if (saved == null) {
			System.out.println("There is no save on this level yet");
			return;
		}
		setPreviousType(savedPreviousType);
		swapMap(saved);
	}
	
	/**
	 * Restarts the game to the pre-saved state
	 */
	public void restart() {
		setPreviousType(1);
		swapMap(restartMap);
	}
	
	/**
	 * 
	 * @return the amount of vertical boxes
	 */
	public int getHeight() {
		return grid.length;
		//return grid[0].length;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return tile with position (x,y)
	 */
	public Tile getTile(int row, int col) {
		if(row<0 || row >= getHeight() || col < 0 || col >= getWidth())
		return null;
		Tile result = grid[row][col];
		return result;	
	}
	
	/**
	 * sets the tile with index (x,y) to tile
	 * @param x
	 * @param y
	 * @param tile
	 */
	public void setTile(int row, int col, Tile tile) {
		grid[row][col] = tile;
	}
	
	/**
	 * Plays the applaus
	 * @throws IOException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 */
	public void playApplaus() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		applaus.stop();
		applaus.play();
	}
	/**
	 * creates and plays the specified sound file in a loop
	 * @param backGroundMusic .wav file containing background music
	 */
	public void playBackGroundMusic(String backGroundMusic) {
		try {
			backgroundMusic = new AudioPlayer(backGroundMusic);
			backgroundMusic.playLoop();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initiates the applaus sound which can be played at a specified time in the game
	 */
	public void initApplaus() {
		try {
			applaus = new AudioPlayer("applaus.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * calls the update function for every observer in the observer list
	 */
	public void updateObservers() {
		for (Observer o: observers)
			o.update(this);
	}
	
	/**
	 * 
	 * @param o adds observer for the game 
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	/**
	 * 
	 * @return the players current row
	 */
	public int getPlayerRow() {
		return playerRow;
	}
	
	/**
	 * 
	 * @return the players current column
	 */
	public int getPlayerCol() {
		return playerCol;
	}
	
	/**
	 * increments player row
	 */
	public void incPlayerRow(){
		playerRow++;
	}
	
	/**
	 * increments player column
	 */
	public void incPlayerCol(){
		playerCol++;
	}
	
	/**
	 * decrements player row
	 */
	public void decPlayerRow(){
		playerRow--;
	}
	
	/**
	 * decrements player column
	 */
	public void decPlayerCol(){
		playerCol--;
	}
	
	/**
	 * sets the location for the player
	 * @param row
	 * @param col
	 */
	public void setPlayerPos(int row, int col) {
		playerRow = row;
		playerCol = col;
	}
	
	/**
	 * 
	 * @return the correct integer representation of the tile you you are currently standing on
	 */
	public int getPreviousType() {
		return previousType;
	}
	
	/**
	 * Sets the previous type tile to the specified one
	 * @param newPreviousType 
	 */
	public void setPreviousType(int newPreviousType) {
		previousType = newPreviousType;
	}
	
	/**
	 * 
	 * @param map integer representation of the map you want to create
	 * @return a tile map with both labels and integer representation of the map
	 */
	public Tile[][] createMap(int[][] map){
		Tile[][] tileMap = new Tile[map.length][map[0].length];
		for(int row = 0; row <map.length; row++){
			for(int col = 0; col < map[0].length ;col++) {
				Tile tile = createTile(map[row][col]);
				if(tile.isPlayer())
					setPlayerPos(row,col);
				tileMap[row][col] = tile;
			}	
		}
		updateObservers();
		return tileMap;
	}

	/**
	 * Single move function
	 * @param move string representation of what action to perform
	 */
	public  void move(String move) {
		controller.move(move);
		updateObservers();
		checkVictory();
	}
	
	/**
	 * 
	 * @param tileValue integer representation of the tile you want to create
	 * @return a tile component with a label and integer value
	 */
	public abstract Tile createTile(int tileValue);	
	
	/**
	 * controllers  for the game, specific for each game
	 */
	public abstract void moveUp();
	public abstract void moveDown();
	public abstract void moveLeft();
	public abstract void moveRight();
	
	/**
	 * Check if there is a wall or out of bounds on the specified row and column
	 * @param row
	 * @param col
	 * @return true if there is a wall, false if there is not
	 */
	public abstract boolean checkWall(int row, int col);
	
	/**
	 * Check if there is a crate on the specified row and column
	 * @param row
	 * @param col
	 * @return true if there is a crate, false if there is not
	 */
	public abstract boolean checkCrate(int row, int col);
	
	/**
	 * creates the correct type of crate at the specified location when trying to move a crate
	 * @param row
	 * @param col
	 */
	public abstract void moveCrate(int row, int col);
	
	/**
	 * Checks for winning state of the board
	 * Different for each game since the victory is achieved differently
	 */
	public abstract boolean checkVictory();
}
