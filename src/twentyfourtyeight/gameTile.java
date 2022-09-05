package twentyfourtyeight;
import javax.swing.JLabel;

import framework.*;


public class gameTile implements Tile{

	private int tileValue;
	private JLabel tileImage;

	public gameTile(JLabel tileImage, int tileValue) {
		this.tileImage = tileImage;
		this.tileValue = tileValue;
	}
	
	@Override
	public int getValue() {
		return tileValue;
	}

	@Override
	public JLabel getLabel() {
		return tileImage;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean isMovable() {
		return false;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}

}
