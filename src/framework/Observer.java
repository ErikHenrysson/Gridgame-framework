package framework;

public interface Observer {
	/**
	 * Updates the current state of the observer
	 * @param game An instance of the game
	 */
	public void update(Gridgame game);
}
