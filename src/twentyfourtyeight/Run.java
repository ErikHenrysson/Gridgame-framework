package twentyfourtyeight;

import java.util.LinkedList;

public class Run {
	public static void main(String[] args) {
		int[][] win = {
				{10,9,8,7},
				{3,4,5,6},
				{2,1,1,0},
				{0,0,0,0}
		};
		
		int [][] riktigBana = {
				{0,1,0,1},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
			LinkedList<int[][]> mapList = new LinkedList<int[][]>();
			mapList.add(riktigBana);
			Game game = new Game(mapList);	
			game.playBackGroundMusic("bgsong.wav");
	}
}
