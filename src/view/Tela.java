package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Tela {

	BufferedImage fundo = null;
	public boolean voltar = false;
	
	public Tela(String path) {
		try {
			fundo = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public void tick() {
		if(voltar) {
			
		}
	}
	
	
	public void render(Graphics g) {
		g.drawImage(fundo, 0, 0, null);
	}
	
}
