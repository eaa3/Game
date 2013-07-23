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
		
		//Convertendo (x,y) para posicao no mapa (i,j)
		p0 = map.getMapPos(x, y);
		int currI = p0.x, currJ = p0.y;
		
		//Convertendo a posicao (x,y) do target para posicao (i_target,j_target) no mapa
		target = map.getMapPos(target.x, target.y);
		int targetI = target.x, targetJ = target.y;
		
		//Primeiro no a ser visitado na bfs (ponto de inicio)
		queue.addBack(currI,currJ);
		
		
		return path;				
	}
}
