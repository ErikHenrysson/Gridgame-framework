package sokoban;


import javax.swing.JLabel;

import framework.Tile;

public class SokobanTile implements Tile{
	
	//integer value of the tile for representation in command window
	private int tileValue;
	private JLabel tileImage;
	private boolean solid = false;
	private boolean movable = false;
	private boolean isPlayer = false;
	
	
	public SokobanTile(JLabel tileImage, int tileValue) {
		this.tileImage = tileImage;
		this.tileValue = tileValue;
	}
	
	public SokobanTile(JLabel tileImage, int tileValue, boolean isPlayer) {
		this.tileImage = tileImage;
		this.tileValue = tileValue;
		this.isPlayer = isPlayer;
	}
	public SokobanTile(JLabel tileImage, int tileValue, boolean solid, boolean movable) {
		this.tileImage = tileImage;
		this.tileValue = tileValue;
		this.solid = solid;
		this.movable = movable;
	}
	
	
	
	@Override
	public JLabel getLabel() {
		return tileImage;
	}
	@Override
	public int getValue() {
		return tileValue;
	}

	@Override
	public boolean isSolid() {
		return solid;
	}
	@Override
	public boolean isMovable() {
		return movable;
	}
	@Override
	public boolean isPlayer() {
		return isPlayer;
	}
	
}
