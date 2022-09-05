package framework;

import javax.swing.JLabel;

public interface Tile {
	/**
	 * 
	 * @return the integer value of the tile
	 */
	public int getValue();
	/**
	 * 
	 * @return the label of the tile
	 */
	public JLabel getLabel();
	
	/**
	 * 
	 * @return true if the tile is solid
	 */
	public boolean isSolid();
	
	/**
	 *  
	 * @return true if tile is movable
	 */
	public boolean isMovable();
	
	/**
	 * 
	 * @return true if tile is a player tile
	 */
	public boolean isPlayer();
}
