package sokoban;

import java.util.LinkedList;
import classes.*;
public class Run {
	public static void main(String[] cmdln) {
		int[][] easymap = new int[][] {
			{6,6,6,6,6,6,6},
			{6,1,1,2,1,1,6},
			{6,1,1,3,1,1,6},
			{6,2,3,5,3,2,6},
			{6,1,1,3,1,1,6},
			{6,1,1,2,1,1,6},
			{6,6,6,6,6,6,6},};
		
		int[][] map = new int[][]{
			{1,1,6,6,6,6,6,1},
			{6,6,6,1,1,1,6,1},
			{6,2,5,3,1,1,6,1},
			{6,6,6,1,3,2,6,1},
			{6,2,6,6,3,1,6,1},
			{6,1,6,1,2,1,6,6},
			{6,3,1,4,3,3,2,6},
			{6,1,1,1,2,1,1,6},
			{6,6,6,6,6,6,6,6}};
				
		int [][] snorsvarbana = new int[][] {
			{1,1,1,1,1,1,6,6,6,6,6,6,6,6,6,6,6,6,1,1},
			{1,1,1,1,1,6,6,2,2,1,1,1,1,6,1,1,1,6,1,1},
			{1,1,1,1,6,6,2,2,4,1,3,1,1,1,1,3,1,6,1,1},
			{1,1,1,6,6,2,2,4,2,6,1,6,1,6,3,1,6,6,1,1},
			{1,1,1,6,2,2,4,2,6,1,6,1,6,1,3,1,1,6,1,1},
			{6,6,6,6,2,2,2,6,1,1,6,1,1,1,1,6,1,6,1,1},
			{6,1,1,6,6,1,6,1,1,1,1,1,1,1,1,1,1,6,1,1},
			{6,1,5,3,1,3,1,6,6,6,1,1,6,1,6,1,6,6,1,1},
			{6,1,3,1,1,1,3,1,1,1,6,1,6,1,1,1,6,1,1,1},
			{6,6,6,3,3,1,1,1,6,1,6,1,6,1,6,1,6,1,1,1},
			{1,1,6,1,1,1,3,1,1,1,6,1,6,1,6,6,6,6,6,1},
			{1,1,6,1,3,6,1,6,6,6,6,6,1,1,1,1,1,1,6,1},
			{1,1,6,3,1,1,1,6,1,1,1,6,1,1,1,6,1,1,6,1},
			{1,1,6,1,1,6,6,6,1,1,1,6,6,1,1,1,1,1,6,1},
			{1,1,6,1,1,6,1,1,1,1,1,1,6,1,1,1,1,6,6,1},
			{1,1,6,6,6,6,1,1,1,1,1,1,6,6,6,6,6,6,1,1}};
			
		int[][] debugMap = new int[][] {
			{6,6,6,6,6,6,6},
			{1,1,1,2,1,1,6},
			{1,1,1,3,1,1,6},
			{1,1,1,3,1,1,6},
			{1,1,1,5,3,1,2},
			{1,1,1,4,1,1,6},
			{1,1,1,1,1,1,6},
			{6,6,6,6,6,6,6}
		};
		
		LinkedList<int[][]> mapList = new LinkedList<int[][]>();
		//mapList.add(debugMap);
		mapList.add(easymap);
		mapList.add(map);
		mapList.add(snorsvarbana);
		SokobanGame game = new SokobanGame(mapList);
		game.playBackGroundMusic("bgsong.wav");
		
		// Uncomment to see winning strategy for first and second map
		
		String[] solmap1 = {"up", "down","down","up","left","right","right"};
		//Controller controller = new Controller(game, solmap1);
		game.newContoller(new Controller(game, solmap1));
		try {
			game.executeinputs();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		String[] solmap2 = {"right","up","right","right","down","left","left","left","right","right","right","down","down","down"
				,"left","down","right","left","left","down","left","left","up","up","down","down","right","right","up","right"
				,"right","up","up","up","up","left","left","down","right","down","right","down","down","left","left","down","left"
				,"left","up","right"};
		
		controller.inputStream(solmap2);
		try {
			controller.excecute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
}
