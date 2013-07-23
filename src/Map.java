import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Map {

	public int map[][];
	public boolean visited[][];

	public String tileSetFile, mapFile;

	public int tileW, tileH;

	public BufferedImage[] tiles;

	public Map(String tileSetFile, String mapFile) throws IOException
	{
		this.tileSetFile = tileSetFile;
		this.mapFile = mapFile;

		int ntiles = 0;

		Arquivo arq = new Arquivo(tileSetFile, "lol.txt");

		ntiles = arq.readInt();

		tiles = new BufferedImage[ntiles];

		//Reading tiles
		for(int i = 0; i < ntiles; i++)
		{
			tiles[i] = ImageIO.read(new File(arq.readString()));
		}
		arq.close();


		this.reload();


		tileW = tiles[0].getWidth();
		tileH = tiles[0].getHeight();


	}
	
	//Recarrega o mapa do disco
	public void reload()
	{
		Arquivo arq = new Arquivo(mapFile, "lol.txt");

		int ki = 0, kj = 0;

		ki = arq.readInt(); kj = arq.readInt();

		map = new int[ki][kj];
		visited = new boolean[ki][kj];

		for(int i = 0; i < ki; i++)
		{
			for(int j = 0; j < kj; j++ )
			{
				map[i][j] = arq.readInt();
			}
		}		
	}
	
	//Pinta o mapa na tela
	public void paint(Graphics g)
	{


		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++ )
			{
				g.drawImage(tiles[map[i][j]], j*tileW, i*tileH, null);
			}
		}		
	}


/**Os metodos abaixo sao importantes para implementar a bfs **/	

	//Seta todas as posicoes do mapa como nao visitadas (false)
	public void clearVisited()
	{
		for(int i = 0; i < this.visited.length; i++)
		{
			for(int j = 0; j < this.visited[0].length; j++)
			{
				this.visited[i][j] = false;
			}
		}
	}
	
	
	
	//Retorna o id do tile da posicao (i,j) do mapa
	public int getTileId(int i, int j)
	{
		return map[i][j];
	}

	//Conversor: Retorna posicao (i,j) no mapa, dadas as posicoes (x,y) da tela
	public Point getMapPos(int x, int y)
	{
		return new Point(y/tileH, x/tileW);		
	}
	
	//Retorna true se (i,j) estiver dentro do mapa
	public boolean isInside(int i, int j)
	{
		return i >= 0 && i < this.map.length && j >= 0 && j < this.map[0].length;
	}



	//Seta um tile que contem a posicao (x,y) da tela
	public void setTile(int x, int y, int tileId)
	{
		map[y/tileH][x/tileW] = tileId;
	}

}
