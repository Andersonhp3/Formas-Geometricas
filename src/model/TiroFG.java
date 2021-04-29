package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class TiroFG extends Entity {

	private double velocidade = 3.1;
	private int life = 40,curlife = 0;


	private int direcaoX;
	private int direcaoY;
	public int depth;
	public static BufferedImage BALA_CIRCULO_EN = Game.spritesheet.getSprite(144, 48, 6, 6);
	public static BufferedImage BALA_LOSANGO_EN = Game.spritesheet.getSprite(150, 48, 6, 6);
	public static BufferedImage BALA_QUADRADO_EN = Game.spritesheet.getSprite(144, 53, 6, 6);
	public static BufferedImage BALA_TRIANGULO_EN = Game.spritesheet.getSprite(150, 54, 6, 6);
	public static BufferedImage BALA_RETANGULO_EN = Game.spritesheet.getSprite(144, 60, 6, 4);
	public static BufferedImage BALA_TRAPEZIO_EN = Game.spritesheet.getSprite(150, 60, 6, 4);

	public static int tipoBala;

	public TiroFG(int x, int y, int widht, int height, BufferedImage sprite, int direcaoX,int direcaoY,int tipoBala) {
		super(x, y, widht, height, sprite);
		this.tipoBala = tipoBala;
		this.direcaoX = direcaoX;
		this.direcaoY = direcaoY;

	}

	public void tick() {
		depth =2;
		x += direcaoX * velocidade;
		y += direcaoY * velocidade;
		curlife++;
		if (curlife == life) {
			Game.tiroFGs.remove(this);
			return;
		}
	}

	public void render(Graphics g) {
		if(tipoBala == 1) {
			g.drawImage(BALA_CIRCULO_EN, this.getX() - Camera.x +10, this.getY() - Camera.y +4,width , height, null);
		}else if(tipoBala ==2 ) {	
			g.drawImage(BALA_TRIANGULO_EN, this.getX() - Camera.x +10, this.getY() - Camera.y +4,width , height, null);
		}else if(tipoBala ==3 ) {	
			g.drawImage(BALA_LOSANGO_EN, this.getX() - Camera.x +10, this.getY() - Camera.y +4,width , height, null);
		}else if(tipoBala ==4 ) {	
			g.drawImage(BALA_QUADRADO_EN, this.getX() - Camera.x +10, this.getY() - Camera.y +4,width , height, null);
		}else if(tipoBala ==5 ) {	
			g.drawImage(BALA_RETANGULO_EN, this.getX() - Camera.x +10, this.getY() - Camera.y +4,width , height, null);
		}else if(tipoBala ==6 ) {	
			g.drawImage(BALA_TRAPEZIO_EN, this.getX() - Camera.x +10, this.getY() - Camera.y +4,width , height, null);
		}

	}

	public int getXTiro() {
		return (int) this.x;
	}






}

