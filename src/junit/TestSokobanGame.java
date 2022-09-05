package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import sokoban.*;
import org.junit.jupiter.api.Test;

class TestSokobanGame {

	@Test
	void TestMove() {
		int[][] easymap = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,2,1,1,6},
			{6,1,1,3,1,1,6},
			{6,2,3,5,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
			
		LinkedList<int[][]> maps = new LinkedList<int[][]>();
		maps.add(easymap);
		//Create game with easy map
		SokobanGame game = new SokobanGame(maps);
		//move up four times
		game.move("up");
		game.move("up");
		game.move("up");
		game.move("up");
		//this is what we expect the state of the board to be
		int[][] expected = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,4,1,1,6},
			{6,1,1,5,1,1,6},
			{6,2,3,1,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
		//check if the map matches the expected
		assertArrayEquals(expected, game.getIntMap());
		
		//do this again for right, down and left
		game.move("right");
		game.move("right");
		game.move("right");
		game.move("right");
		game.move("right");
		
		expected = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,4,1,1,6},
			{6,1,1,1,1,5,6},
			{6,2,3,1,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
			
		assertArrayEquals(expected, game.getIntMap());
		
		game.move("down");
		game.move("down");
		game.move("down");
		game.move("down");
		
		expected = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,4,1,1,6},
			{6,1,1,1,1,1,6},
			{6,2,3,1,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,5,6},
			{6,6,6,6,6,6,6},
			};
			
		assertArrayEquals(expected, game.getIntMap());
		
		game.move("left");
		game.move("left");
		game.move("left");
		game.move("left");
		
		expected = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,4,1,1,6},
			{6,1,1,1,1,1,6},
			{6,2,3,1,3,2,6},
			{6,1,1,3,1,1,6},
			{6,5,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
		
			
		assertArrayEquals(expected, game.getIntMap());
		
		//save the board
		game.move("save");
		//reset the board
		game.move("restart");
		//the expected board is a fresh level of the same level
		expected = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,2,1,1,6},
			{6,1,1,3,1,1,6},
			{6,2,3,5,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
		//check if it worked
		assertArrayEquals(expected, game.getIntMap());
			
		//load the previously saved map
		game.move("load");

		expected = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,4,1,1,6},
			{6,1,1,1,1,1,6},
			{6,2,3,1,3,2,6},
			{6,1,1,3,1,1,6},
			{6,5,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
			
		//See if the load worked
		assertArrayEquals(expected, game.getIntMap());
			
	}
	
	@Test
	public void TestGameFunctions() {

		int[][] easymap = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,2,1,1,6},
			{6,1,1,3,1,1,6},
			{6,2,3,5,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,1,6},
			{6,6,6,6,6,6,6},
			};
			
			LinkedList<int[][]> maps = new LinkedList<int[][]>();
			maps.add(easymap);
			SokobanGame game = new SokobanGame(maps);
			
			//check if the player row and column functions works
			assertEquals(3, game.getPlayerRow());
			assertEquals(3, game.getPlayerCol());
			//the game should not be in a winning state
			assertFalse(game.checkVictory());
			
			//check all the walls and make sure they are recognized as such
			assertTrue(game.checkWall(0, 0));
			assertTrue(game.checkWall(0, 1));
			assertTrue(game.checkWall(0, 2));
			assertTrue(game.checkWall(0, 3));
			assertTrue(game.checkWall(0, 4));
			assertTrue(game.checkWall(0, 5));
			assertTrue(game.checkWall(0, 6));
			
			assertTrue(game.checkWall(1, 0));
			assertTrue(game.checkWall(2, 0));
			assertTrue(game.checkWall(3, 0));
			assertTrue(game.checkWall(4, 0));
			assertTrue(game.checkWall(5, 0));
			assertTrue(game.checkWall(6, 0));
			
			assertTrue(game.checkWall(1, 6));
			assertTrue(game.checkWall(2, 6));
			assertTrue(game.checkWall(3, 6));
			assertTrue(game.checkWall(4, 6));
			assertTrue(game.checkWall(5, 6));
			assertTrue(game.checkWall(6, 6));
			assertTrue(game.checkWall(6, 1));
			assertTrue(game.checkWall(6, 2));
			assertTrue(game.checkWall(6, 3));
			assertTrue(game.checkWall(6, 4));
			assertTrue(game.checkWall(6, 5));
			
			//check all the other tiles and make sure it isn't recognized as a wall
			for(int i = 1; i < game.getHeight() -1 ; i++) {
				for (int j  = 1; j <game.getWidth() -1; j++) {
					assertFalse(game.checkWall(i, j));
				}
					
			}
		
			//check the crates and make sure the game recognizes them
			assertTrue(game.checkCrate(2, 3));
			assertTrue(game.checkCrate(3, 2));
			assertTrue(game.checkCrate(3, 4));
			assertTrue(game.checkCrate(4, 3));	
	}
}
