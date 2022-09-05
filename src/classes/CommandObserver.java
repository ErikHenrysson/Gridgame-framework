package classes;
import framework.*;
public class CommandObserver implements Observer{

	@Override
	public void update(Gridgame game) {
		printGame(game);
	}

	/**
	 * Prints the current state of the game in the terminal
	 * @param game
	 */
	public void printGame(Gridgame game) {
		for(int row = 0; row < game.getHeight(); row++) {
			for(int col = 0; col < game.getWidth(); col++) {
			System.out.print(game.getTile(row, col).getValue()+ "  ");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
}
