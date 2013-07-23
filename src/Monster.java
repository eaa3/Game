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
		
		
		
		System.out.println("Doing bfs from " + p0);
		int tempI, tempJ;
		Point temp;
		//Enquanto nossa lista de nos para visitar nao estah vazia, visitamos o pessoal
		while( !queue.isEmpty() )
		{
		
			currI = queue.head.a;
			currJ = queue.head.b;
			map.visited[currI][currJ] = true; //Visitamos o primeiro da fila			
			temp = new Point(currI,currJ);
			queue.removeFront(); //O removemos da fila
			
			
			//Visitamos todos os seus adjacentes
			for(int i = 0; i < 8; i++)
			{
				tempI = currI + possibleMovementsI[i];
				tempJ = currJ + possibleMovementsJ[i];
				
				//Adjacentes sao tiles que estao dentro do mapa, nao foram visitados e que sao transitaveis, iguais a 0 (Zero == Grama)
				if( map.isInside(tempI, tempJ) && !map.visited[tempI][tempJ] && map.getTileId(tempI, tempJ) == 0 )
				{
					
					//Ao adicionar um tile na lista de nos que serao visitados no futuro, podemos dizer tambem a partir de qual tile nos o descobrimos (seu pai)
					fathers[tempI][tempJ] = temp;
					queue.addBack(tempI,tempJ);		
				}
												
			}
		}
		
		
		//Aqui nos vamos apenas pegar os tiles que formam um caminho do target ateh o no de partida (p0)
		System.out.println("Gathering visited tiles");
		if( map.visited[targetI][targetJ]  )
		{
			temp = target;
			do
			{
				//Essas continhas sao pra converter para um ponto na tela (e nao no mapa)
				path.addFront(temp.y*map.tileW, temp.x*map.tileH);
				
			
				temp = fathers[temp.x][temp.y];				
				
				
			}while( !fathers[temp.x][temp.y].equals(p0) ); //fazemos isso enquanto nao voltamos pro ponto inicial de onde saimos
		}
		
		return path;				
	}
}
