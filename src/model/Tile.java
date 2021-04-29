package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Tile {

	public static BufferedImage TILE_CHAO = Game.spritesheet.getSprite(64, 0, 16, 16);
	public static BufferedImage TILE_PAREDE = Game.spritesheet.getSprite(80, 0, 16, 16);
	public static BufferedImage TILE_CHAO_TERRA = Game.spritesheet.getSprite(80, 16, 16, 16);
	public static BufferedImage TILE_AGUA = Game.spritesheet.getSprite(64, 16, 16, 16);
	public static BufferedImage TILE_VERMELHO = Game.spritesheet.getSprite(112, 16, 16, 16);
	public static BufferedImage TILE_AZUL = Game.spritesheet.getSprite(128, 16, 16, 16);
	public static BufferedImage TILE_LARANJA = Game.spritesheet.getSprite(144, 16, 16, 16);
	public static BufferedImage TILE_AMARELO = Game.spritesheet.getSprite(128, 32, 16, 16);
	
	
	private BufferedImage sprite;
	private int x,y;
	
	
	public Tile(BufferedImage sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
}
