package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import javax.xml.soap.Node;

public class Entity {
	
	public static BufferedImage VIDA_EN = Game.spritesheet.getSprite(80, 48, 16, 16);
	public static BufferedImage BOSS_EN = Game.spritesheet.getSprite(16, 64, 26, 32);	
	public static BufferedImage QUADRADO_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
	public static BufferedImage CIRCULO_EN = Game.spritesheet.getSprite(96, 16, 16, 16);
	public static BufferedImage TRIANGULO_EN = Game.spritesheet.getSprite(144, 0, 16, 16);
	public static BufferedImage RETANGULO_EN = Game.spritesheet.getSprite(128, 0, 16, 16);
	public static BufferedImage LOSANGO_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage TRAPEZIO_EN = Game.spritesheet.getSprite(144, 64, 16, 16);
	
	public static BufferedImage LOSANGO_PEQUENO = Game.spritesheet.getSprite(65, 113, 8, 9);
	public static BufferedImage CIRCULO_PEQUENO= Game.spritesheet.getSprite(76, 113, 9, 9);
	public static BufferedImage QUADRADO_PEQUENO = Game.spritesheet.getSprite(88, 114, 7, 7);
	public static BufferedImage RETANGULO_PEQUENO = Game.spritesheet.getSprite(99, 115, 8, 6);
	public static BufferedImage TRIANGULO_PEQUENO = Game.spritesheet.getSprite(111, 114, 8, 7);
	
	
	public static BufferedImage LOSANGO_GRANDE = Game.spritesheet.getSprite(64, 124, 19,19);
	public static BufferedImage CIRCULO_GRANDE = Game.spritesheet.getSprite(123, 125, 17, 17);
	public static BufferedImage QUADRADO_GRANDE = Game.spritesheet.getSprite(85, 123, 17, 17);
	public static BufferedImage RETANGULO_GRANDE = Game.spritesheet.getSprite(103, 127, 16, 13);
	public static BufferedImage TRIANGULO_GRANDE = Game.spritesheet.getSprite(142, 126, 17, 17);
	
	
	
	public static BufferedImage INIMIGO_EN = Game.spritesheet.getSprite(64, 32, 16, 16);
	public static BufferedImage INIMIGO_Azul_EN = Game.spritesheet.getSprite(112, 64, 16, 16);
	public static BufferedImage INIMIGO_VERDE_EN = Game.spritesheet.getSprite(112, 48, 16, 16);
	public static BufferedImage INIMIGO_BRANCO_EN = Game.spritesheet.getSprite(112, 80, 16, 16);
	
	
	public static BufferedImage ENCAIXE_CIRCULO_EN = Game.spritesheet.getSprite(128, 16, 16, 16);
	public static BufferedImage ENCAIXE_QUADRADO_EN = Game.spritesheet.getSprite(144, 16, 16, 16);
	public static BufferedImage ENCAIXE_RETANGULO_EN = Game.spritesheet.getSprite(128, 32, 16, 16);
	public static BufferedImage ENCAIXE_LOSANGO_EN = Game.spritesheet.getSprite(112, 16, 16, 16);
	public static BufferedImage ENCAIXE_TRIANGULO_EN = Game.spritesheet.getSprite(144, 32, 16, 16);
	public int depth;

	protected double x,y;
	protected int width;
	protected int height;
	private BufferedImage sprite;
	
	private int maskx,masky,mwidth,mheight;
	
	
	public Entity(int x, int y, int widht, int height,BufferedImage sprite) {
		super();
		this.x = x;
		this.y = y;
		this.width = widht;
		this.height = height;
		this.sprite = sprite;
		
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	
	public void setMask(int maskx,int masky, int mwidth,int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth) {
				return +1;
			}
			if(n1.depth > n0.depth) {
				return -1;
			}
			
			return 0;
		}
	};
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		
		
		//g.setColor(Color.red);
		//g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, mheight);
	}
	
	public void tick() {
		
	}
	
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1mask = new Rectangle(e1.getX()+ e1.maskx,e1.getY() + e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2mask = new Rectangle(e2.getX()+ e2.maskx,e2.getY() + e2.masky,e2.mwidth,e2.mheight);
		return e1mask.intersects(e2mask);
	}
	
	
	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public void setX(int x) {
		this.x = x;
	}



	public void setY(int y) {
		this.y = y;
	}

	public int getWidht() {
		return width;
	}

	public void setWidht(int widht) {
		this.width = widht;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
