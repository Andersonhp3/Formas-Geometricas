package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class Wold {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	public static int posBraX;
	public static int posBraY;
	public static int posAzulX;
	public static int posAzulY;
	public static int posVermX;
	public static int posVermY;
	public static int posLaraX;
	public static int posLaraY;
	public static int posAmareX;
	public static int posAmareY;
	public static int posParedeX;
	public static int posParedeY;
	
	public Wold(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()]; /*Pega a quantidade de 
			pixels da imagem e guarda array de int*/
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth();xx++) {
				for(int yy = 0; yy  < map.getHeight();yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx +(yy * WIDTH)] = new Chao(Tile.TILE_CHAO , xx * 16, yy * 16);
					if(pixelAtual == 	0xFF000000) {
						//chão
						tiles[xx +(yy * WIDTH)] = new Chao(Tile.TILE_CHAO , xx * 16, yy * 16);
					}else if(pixelAtual == 0xFFFFFFFF) {
						//Parede
						posParedeX = xx * 16;
						posParedeY = yy * 16;
						tiles[xx +(yy * WIDTH)] = new Parede(Tile.TILE_PAREDE , xx * 16, yy * 16);
					}else if(pixelAtual == 0xFF0026FF) {
						//Jogador
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFF4CFF00) {
						//Chão de terra
						tiles[xx +(yy * WIDTH)] = new ChaoTerra(Tile.TILE_CHAO_TERRA , xx * 16, yy * 16);
					}else if(pixelAtual == 0xFF00FFFF) {
						//Agua
						tiles[xx +(yy * WIDTH)] = new Agua(Tile.TILE_AGUA , xx * 16, yy * 16);
					}else if(pixelAtual == 0xFFFF0000) {
						//Inimigo
						Enemy ini = new Enemy(xx*16, yy*16, 16, 16, Entity.INIMIGO_EN,1);
						Game.entities.add(ini);
						Game.enemies.add(ini);
					}else if(pixelAtual == 0xFFFFD800 ) {
						//Vida
						Game.entities.add(new Vida(xx*16, yy*16, 16, 16, Entity.VIDA_EN));
					}
					else if(pixelAtual == 0xFFFF6A00) {
						//Formas - circulo
						Game.entities.add(new Circulo(xx*16, yy*16, 16, 16, Entity.CIRCULO_EN));
					}else if(pixelAtual == 0xFFDAFF7F) {
				 		//Formas - quadrado
						Game.entities.add(new Quadrado(xx*16, yy*16, 16, 16, Entity.QUADRADO_EN));
					}
					else if(pixelAtual == 0xFFFF006E) {
						//Formas - Losango
						Game.entities.add(new Losango(xx*16, yy*16, 16, 16, Entity.LOSANGO_EN));
					}else if(pixelAtual == 0xFF00FF90) {
						//Formas - retangulo
						Game.entities.add(new Retangulo(xx*16, yy*16, 16, 16, Entity.RETANGULO_EN));
					}else if(pixelAtual == 0xFF7F3300) {
						//Formas - triangulo
						Game.entities.add(new Triangulo(xx*16, yy*16, 16, 16, Entity.TRIANGULO_EN));
					}else if(pixelAtual == 0xFFA811FF) {
						//Cores - Vermelha
						posVermX = xx * 16;
						posVermY = yy * 16;
						Game.entities.add(new EncaixeLosango(xx*16, yy*16, 16, 16, Entity.ENCAIXE_LOSANGO_EN));
					}else if(pixelAtual == 0xFF7F0037) {
						//Cores - azul
						posAzulX = xx * 16;
						posAzulY = yy * 16;
						Game.entities.add(new EncaixeCirculo((xx*16), yy*16, 16, 16, Entity.ENCAIXE_CIRCULO_EN));
					}else if(pixelAtual == 0xFF57007F) {
						//Cores - amarelo
						posAmareX = xx * 16;
						posAmareY = yy * 16;
						Game.entities.add(new EncaixeRetangulo((xx*16), yy*16, 16, 16, Entity.ENCAIXE_RETANGULO_EN));
					}else if(pixelAtual == 0xFF007F46) {
						//Cores - EncaixeQuadrado
						posLaraX = xx * 16;
						posLaraY = yy * 16;
						Game.entities.add(new EncaixeQuadrado((xx*16), yy*16, 16, 16, Entity.ENCAIXE_QUADRADO_EN));
					}
					else if(pixelAtual == 0xFFE0FFEA) {
						//Cores - Branco
						posBraX =xx * 16;
						posBraY = yy * 16; 
						Game.entities.add(new EncaixeTriangulo((xx*16), yy*16, 16, 16, Entity.ENCAIXE_TRIANGULO_EN));
					}
					
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void restartGame(String level) {
		Game.entities = new ArrayList<Entity>();
		Game.enemies = new ArrayList<Enemy>();
		Game.circulos = new ArrayList<Circulo>();
		Game.triangulos = new ArrayList<Triangulo>();
		Game.losangos = new ArrayList<Losango>();
		Game.quadrados = new ArrayList<Quadrado>();
		Game.retangulos = new ArrayList<Retangulo>();
		Game.spritesheet = new Spritesheet("/Spritesheet.png");
		Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(0, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.wold = new Wold("/"+level);
		Game.instanciarInimigo();
		return;
	}
	
	
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + TILE_SIZE -1) /TILE_SIZE;
		int y2 = ynext  / TILE_SIZE;
		
		
		int x3 = xnext  /TILE_SIZE;
		int y3 = (ynext + TILE_SIZE -1) / TILE_SIZE;		
		
		int x4 = (xnext + TILE_SIZE -1) /TILE_SIZE;
		int y4 = (ynext + TILE_SIZE -1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*Wold.WIDTH)] instanceof Parede) ||
				(tiles[x2 + (y2*Wold.WIDTH)] instanceof Parede)  ||
				(tiles[x3 + (y3*Wold.WIDTH)] instanceof Parede)  ||
				(tiles[x4 + (y4*Wold.WIDTH)] instanceof Parede)  ||
				(tiles[x1 + (y1*Wold.WIDTH)] instanceof Agua)  ||
				(tiles[x2 + (y2*Wold.WIDTH)] instanceof Agua)  ||
				(tiles[x3 + (y3*Wold.WIDTH)] instanceof Agua)  ||
				(tiles[x4 + (y4*Wold.WIDTH)] instanceof Agua)  );
		
		
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 ||  yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy *WIDTH)];
				tile.render(g);
			}
		}
	}
}

