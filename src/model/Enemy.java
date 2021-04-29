package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends Entity{

	private double speed = 0.4;
	private int maskx = 7, masky = 5,maskw = 4, maskh = 12;
	private int frames = 0,max_frames = 20,index = 0,max_index = 1;
	private BufferedImage[] sprites;
	int corInimigo;
	private BufferedImage[] spritesVerde;
	private BufferedImage[] spritesAzul;
	private BufferedImage[] spritesBranco;
	private int life = 5,maxLife=5;


	boolean parado =false;



	public Enemy(int x, int y, int widht, int height, BufferedImage sprite,int corInimigo) {
		super(x, y, widht, height, null);
		this.corInimigo = corInimigo;
		sprites = new BufferedImage[2];
		spritesVerde = new BufferedImage[2];
		spritesAzul = new BufferedImage[2];
		spritesBranco = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(64, 32, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(64 + 16, 32, 16, 16);
		spritesVerde[0] = Game.spritesheet.getSprite(112, 48, 16, 16);
		spritesVerde[1] = Game.spritesheet.getSprite(112 + 16, 48, 16, 16);
		spritesAzul[0] = Game.spritesheet.getSprite(112, 64, 16, 16);
		spritesAzul[1] = Game.spritesheet.getSprite(112 + 16, 64, 16, 16);
		spritesBranco[0] = Game.spritesheet.getSprite(112, 80, 16, 16);
		spritesBranco[1] = Game.spritesheet.getSprite(112 + 16, 80, 16, 16);

	}

	public void tick() {
		depth = 1;
		if(isColiddingWithPlayer() == false) {
			if(corInimigo == 3 || corInimigo ==4) {
				y+=speed;
				if (

						!(Wold.isFree((int)(x+speed), this.getY()))
						|| !(Wold.isFree((int)(x-speed), this.getY())) 
						|| !(Wold.isFree(this.getX(), (int)(y+speed)))
						|| !(Wold.isFree(this.getX(), (int)(y-speed)))) {
					speed = speed * -1;
				}
			}else {
				x+=speed;
				if (
						!(Wold.isFree((int)(x+speed), this.getY()))
						|| !(Wold.isFree((int)(x-speed), this.getY())) 
						|| !(Wold.isFree(this.getX(), (int)(y+speed)))
						|| !(Wold.isFree(this.getX(), (int)(y-speed)))) {
					speed = speed * -1;
				}
			}

		}else {
			//Atacando o player
			if(corInimigo == 1) {
				danoInimigo(0.05);
			}if (corInimigo ==2) {
				danoInimigo(0.20);
			}if (corInimigo ==3) {
				danoInimigo(0.25);
			}if (corInimigo ==4) {
				danoInimigo(0.50);
			}

		}


		frames++;
		if(frames == max_frames) {
			frames = 0;
			index++;
			if(index > max_index) {
				index = 0;
			}
		}

		colisaoFormaGeo();

		if (life <=0) {
			destroirInimigo(); 
			return;
		}


	}

	public void destroirInimigo() {
		Game.entities.remove(this);
	}

	public void  danoInimigo(double dano) {
		if (Game.random.nextInt(100)<10) {
			Som.dano.play();
			Game.player.life-= Game.player.life * dano;
			if (Game.player.life <= 2) {
				Game.player.vidas -= 1;
				Game.player.life =100;
			}
			Game.player.isDamaged = true;
		}
	}

	public void colisaoFormaGeo() {
		for(int i = 0; i < Game.tiroFGs.size();i++) {
			Entity e = Game.tiroFGs.get(i);
			if(e instanceof TiroFG) {
				if (Entity.isColidding(this, e)) {
					life--;
					Game.tiroFGs.remove(i);
					return;

				}
			}
		}

	}





	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() +maskx, this.getY()+masky,maskw,maskh); 
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);


		return enemyCurrent.intersects(player);
	}

	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext +maskx, ynext+masky,maskw,maskh); 
		for(int i = 0; i < Game.enemies.size();i++) {
			Enemy e = Game.enemies.get(i);
			if(e==this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky,maskw,maskh);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}		
		}	
		return false;	
	}



	public void render(Graphics g) {



		if(corInimigo == 1) {
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}if(corInimigo == 2) {
			g.drawImage(spritesVerde[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}if(corInimigo == 3) {
			g.drawImage(spritesAzul[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}if(corInimigo == 4) {
			g.drawImage(spritesBranco[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}

		/*g.setColor(Color.blue);
		g.fillRect( this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
		 */}

}
