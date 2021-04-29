package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Boss extends Entity {
	
	private double speed = 0.5;
	private int frames = 0,max_frames = 20,index = 0,max_index = 1;
	private BufferedImage[] sprites;
	private int maskx = 2, masky = 1,maskw = 16, maskh = 26;
	public double life  = 100, maxLife = 100;

	public Boss(int x, int y, int widht, int height, BufferedImage sprite) {
		super(x, y, widht, height, sprite);
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(16, 64, 26, 32);
		sprites[1] = Game.spritesheet.getSprite(42, 64, 26, 32);
	}

	public void tick() {
		depth = 0;
		if(isColiddingWithPlayer() == false) {
			if((int)x < Game.player.getX() && Wold.isFree((int)(x+speed) +10, this.getY())
					&& !(isColidding((int)(x+speed), this.getY()))) {
				x+=speed;
			}else if((int)x > Game.player.getX() && Wold.isFree((int)(x-speed), this.getY())
					&& !(isColidding((int)(x-speed), this.getY()))) {
				x-=speed;
			}else if((int)y < Game.player.getY() && Wold.isFree(this.getX() , (int)(y+speed)+16)
					&& !(isColidding(this.getX(), (int)(y+speed)))) {
				y+=speed;
			}else if((int)y > Game.player.getY() && Wold.isFree(this.getX(), (int)(y-speed))
					&& !isColidding(this.getX(), (int)(y-speed))) {
				y-=speed;
			}
		}else {
			//Atacando o player
			if (Game.random.nextInt(100)<10) {
				Som.dano.play();
				Game.player.life-= 25;
				if (Game.player.life <= 1) {
					Game.player.vidas -= 1;
					Game.player.life =100;
				}
				Game.player.isDamaged = true;
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
			destroirBoss(); 
			return;
		}

	}
	
	public void destroirBoss() {
		Game.entities.remove(this);
	}
	
	public void colisaoFormaGeo() {
		for(int i = 0; i < Game.tiroFGs.size();i++) {
			Entity e = Game.tiroFGs.get(i);
			if(e instanceof TiroFG) {
				if (Entity.isColidding(this, e)) {
					if (TiroFG.tipoBala == 1 ||TiroFG.tipoBala == 2 ||TiroFG.tipoBala == 3 ||
							TiroFG.tipoBala == 4 ||TiroFG.tipoBala == 5)  {
						life-=5;
					}else {
						life--;
					}
					
					Game.tiroFGs.remove(i);
					if (life <= 0) {
						Game.tiroFGs.removeAll(Game.tiroFGs);
					}
					
					return;

				}
			}
		}

	}
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() +maskx +2, this.getY()+masky +2,maskw,maskh); 
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);


		return enemyCurrent.intersects(player);
	}

	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext +maskx, ynext+masky,maskw,maskh); 
		for(int i = 0; i < Game.bossA.size();i++) {
			Boss e = Game.bossA.get(i);
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
		g.setColor(Color.red);
		
		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y -4, 26,3 );
		g.setColor(Color.GREEN);
		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y -4, (int)((Game.boss.life/Game.boss.maxLife)*26),3 );
	
		g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		/*g.setColor(Color.blue);
		g.fillRect( this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
	*/}
}
