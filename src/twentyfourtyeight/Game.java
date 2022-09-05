package twentyfourtyeight;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import classes.CommandObserver;
import classes.WindowObserver;
import framework.*;

public class Game extends Gridgame{

	public Game(LinkedList mapList) {
		super(mapList);
		CommandObserver terminalObserver = new CommandObserver();
		WindowObserver<Game> windowObserver = new WindowObserver<Game>(this);
		this.addObserver(terminalObserver);
		this.addObserver(windowObserver);
	}
	
	public Game(LinkedList<int[][]> mapList, ControllerStrategy controller) {
		super(mapList,controller);
		CommandObserver terminalObserver = new CommandObserver();
		WindowObserver<Game> windowObserver = new WindowObserver<Game>(this);
		this.addObserver(terminalObserver);
		this.addObserver(windowObserver);
	}

	@Override
	public Tile createTile(int tileValue) {
			if(tileValue == 0) {
			JLabel label = new JLabel();
			return (new gameTile(label, 0));
			} else if(tileValue == 1) {
				java.net.URL url = getClass().getResource("two.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 1));
			} else if(tileValue == 2) {
				java.net.URL url = getClass().getResource("four.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 2));
			} else if(tileValue == 3) {
				java.net.URL url = getClass().getResource("eight.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 3));
			} else if(tileValue == 4) {
				java.net.URL url = getClass().getResource("sixteen.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 4));
			} else if(tileValue == 5) {
				java.net.URL url = getClass().getResource("thirtytwo.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 5));
			} else if(tileValue == 6) {
				java.net.URL url = getClass().getResource("sixtyfour.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 6));
			}else if(tileValue== 7) {
				java.net.URL url = getClass().getResource("hundredtwentyeight.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 7));
			}else if(tileValue == 8) {
				java.net.URL url = getClass().getResource("twohundredfiftysix.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 8));
			} else if(tileValue == 9) {
				java.net.URL url = getClass().getResource("fivehundredtwelve.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 9));
			}
			else if(tileValue== 10) {
				java.net.URL url = getClass().getResource("onethousandtwentyfour.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 10));
			}
			else if(tileValue == 11) {
				java.net.URL url = getClass().getResource("twothousandfourtyeight.png");
				ImageIcon icon = new ImageIcon(url);
				JLabel label = new JLabel(icon);
				return (new gameTile(label, 11));
			}
			return null;
	}

	@Override
	public void moveUp() {
		boolean moveMade = false;
		//check if box to left is empty, if it is, check next (while loop)
				for(int i = 0; i < getHeight(); i++) {
					for(int j = 0; j < getWidth(); j++) {
						
						int row = i;
						int col = j;
						boolean keepGoing = true;
						while(keepGoing) {
							//if its going to  be out of bounds
							if(checkWall(row-1, col) || getTile(row, col).getValue() == 0) {
								keepGoing = false;
							} else {
								//System.out.println("row i : " + i+ " col j: " + j +"\n" + "row row: " + row + " col col: " +col );
								//if it is an empty tile
								if(getTile(row-1,col).getValue() == 0 ) {
									//move it one step left 
									setTile(row-1, col, getTile(row, col));
									//make empty space where you were
									setTile(row,col, createTile(0));
									row--;
									moveMade = true;
								} else if(getTile(row-1, col).getValue() == getTile(row,col).getValue()) {
									//found a tile with the same value
									setTile(row-1, col, createTile(getTile(row, col).getValue()+1));
									setTile(row,col, createTile(0));
									moveMade = true;
									keepGoing = false;
								}else {
									keepGoing = false;
								}
							}	
						}	
					}
				}
		if(moveMade) {
			insertAtRandom();
		}
	}

	@Override
	public void moveDown() {
		boolean moveMade = false;
		//check if box to left is empty, if it is, check next (while loop)
				for(int i = 0; i < getHeight(); i++) {
					for(int j = 0; j < getWidth(); j++) {
						int row = i;
						int col = j;
						boolean keepGoing = true;
						while(keepGoing) {
							//if its going to  be out of bounds
							if(checkWall(row+1, col) || getTile(row, col).getValue() == 0) {
								keepGoing = false;
							} else {
								//System.out.println("row i : " + i+ " col j: " + j +"\n" + "row row: " + row + " col col: " +col );
								//if it is an empty tile
								if(getTile(row+1,col).getValue() == 0 ) {
									//move it one step left 
									setTile(row+1, col, getTile(row, col));
									//make empty space where you were
									setTile(row,col, createTile(0));
									row++;
									moveMade = true;
								} else if(getTile(row+1, col).getValue() == getTile(row,col).getValue()) {
									//found a tile with the same value
									setTile(row+1, col, createTile(getTile(row, col).getValue()+1));
									setTile(row,col, createTile(0));
									moveMade = true;
									keepGoing = false;
								}else {
									keepGoing = false;
								}
							}	
						}	
					}
				}
		if(moveMade) {
			insertAtRandom();
		}
	}

	@Override
	public void moveLeft() {
		boolean moveMade = false;
		//check if box to left is empty, if it is, check next (while loop)
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				int row = i;
				int col = j;
				boolean keepGoing = true;
				while(keepGoing) {
					//if its going to  be out of bounds
					if(checkWall(row, col-1) || getTile(row, col).getValue() == 0) {
						keepGoing = false;
					} else {
						//System.out.println("row i : " + i+ " col j: " + j +"\n" + "row row: " + row + " col col: " +col );
						//if it is an empty tile
						if(getTile(row,col-1).getValue() == 0 ) {
							//move it one step left 
							setTile(row, col-1, getTile(row, col));
							//make empty space where you were
							setTile(row,col, createTile(0));
							col--;
							moveMade = true;
						} else if(getTile(row, col-1).getValue() == getTile(row,col).getValue()) {
							//found a tile with the same value
							setTile(row, col-1, createTile(getTile(row, col).getValue()+1));
							setTile(row,col, createTile(0));
							moveMade = true;
							keepGoing = false;
						} else {
							keepGoing = false;
						}
					}	
				}	
			}
		}
		if(moveMade) {
			insertAtRandom();
		}
	}

	@Override
	public void moveRight() {
		boolean moveMade = false;
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				int row = i;
				int col = j;
				boolean keepGoing = true;
				while(keepGoing) {
					//if its going to  be out of bounds
					if(checkWall(row, col+1) || getTile(row, col).getValue() == 0) {
						keepGoing = false;
					} else {
						//if it is an empty tile
						if(getTile(row,col+1).getValue() == 0 ) {
							//move it one step left 
							setTile(row, col+1, getTile(row, col));
							//make empty space where you were
							setTile(row,col, createTile(0));
							col++;
							moveMade = true;
						} else if(getTile(row, col+1).getValue() == getTile(row,col).getValue()) {
							//found a tile with the same value
							setTile(row, col+1, createTile(getTile(row, col).getValue()+1));
							setTile(row,col, createTile(0));
							moveMade = true;
							keepGoing = false;
						}else {
							keepGoing = false;
						}
					}	
				}	
			}
		}
		if(moveMade) {
			insertAtRandom();
		}
	}

	/**
	 * Creates a tile with a small chance of it being a 4 instead of a two
	 * @return tile
	 */
	public Tile createRandomTile() {
		int rand = (int)(Math.random()*10);
		if(rand < 8) {
			return createTile(1);
		} else
			return createTile(2);
	}
	
	/**
	 * Inserts a tile with random value from 2-4 in a empty place on the grid
	 */
	public void insertAtRandom() {
		int row;
		int col;
		do {
			row = (int)Math.floor((Math.random()*(3-0+1)+0));
			col = (int)Math.floor((Math.random()*(3-0+1)+0));
		} while(getTile(row, col).getValue() != 0);
		
		setTile(row, col, createRandomTile());
	}
	
	@Override
	public boolean checkWall(int row, int col) {
		if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkCrate(int row, int col) {return false;}

	@Override
	public void moveCrate(int row, int col) {}

	@Override
	public boolean checkVictory() {
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				if(getTile(i, j).getValue() == 11) {
					System.out.println("Grattis du har vunnit!");
					return true;
				}
			}
		}
		return false;
	}
}
