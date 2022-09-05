package framework;

public interface ControllerStrategy {
	/**
	 * Executes the move
	 * @param move String representation of the move you want to execute
	 */
	public void move(String move);
	
	/**
	 * 
	 * executes all the moves from the input stream
	 *
	 * @throws InterruptedException to be able to use timer between moves
	 */
	public void excecute()throws InterruptedException;

	/**
	 * should be executable 
	 * @param inputs string representation of moves in order
	 */
	public void inputStream(String [] inputs);
	
}
