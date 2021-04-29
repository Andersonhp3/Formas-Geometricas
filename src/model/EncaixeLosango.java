package model;

import java.awt.image.BufferedImage;

public class EncaixeLosango extends Entity {

	public int depth;
	public EncaixeLosango(int x, int y, int widht, int height, BufferedImage sprite) {
		super(x, y, widht, height, sprite);
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		depth =0;
	}

}
