import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Monster {

	
	public BufferedImage img;
	public int x, y;
	
	
	
	public Monster(String spriteSheet) throws IOException
	{
		img = ImageIO.read(new File (spriteSheet));
	}
	
	
	public void paint(Graphics g)
	{
		g.drawImage(img, x, y, null);		
	}
	
	public List computePath(Map map, Point target)
	{
		Point p0;
		List queue = new List();
		List path = new List();
		int possibleMovementsI[] = new int[] {-1, 1, 0, 0, -1, -1, 1, 1 };
		int possibleMovementsJ[] = new int[] {0, 0, -1, 1, -1, 1, -1, 1};
		map.clearVisited();
		Point fathers[][] = new Point[map.map.length][map.map[0].length];
		
		p0 = map.getMapPos(x, y);
		int currI = p0.x, currJ = p0.y;
		target = map.getMapPos(target.x, target.y);
		int targetI = target.x, targetJ = target.y;
		
		queue.addBack(currI,currJ);
		
		
		
		
		System.out.println("Doing bfs from " + p0);
		int tempI, tempJ;
		Point temp;
		//While we didn't reach the target, we search for it!
		while( !queue.isEmpty() )
		{
		
			currI = queue.head.x;
			currJ = queue.head.y;
			map.visited[currI][currJ] = true;
			temp = new Point(currI,currJ);
			queue.removeFront();
			
			
			
			for(int i = 0; i < 8; i++)
			{
				tempI = currI + possibleMovementsI[i];
				tempJ = currJ + possibleMovementsJ[i];
				
				//If we are inside the map and it's not a visited tile and the tile is grass
				if( map.isInside(tempI, tempJ) && !map.visited[tempI][tempJ] && map.getTileId(tempI, tempJ) == 0 )
				{
					
					fathers[tempI][tempJ] = temp;
					queue.addBack(tempI,tempJ);			
				}
												
			}
		}
		
		System.out.println("Gatharing visited tiles");
		if( map.visited[targetI][targetJ]  )
		{
			temp = target;
			do
			{
				path.addFront(temp.y*map.tileW, temp.x*map.tileH);
				
				temp = fathers[temp.x][temp.y];				
				
				
			}while( !fathers[temp.x][temp.y].equals(p0) );
		}
		
		return path;				
	}
}
