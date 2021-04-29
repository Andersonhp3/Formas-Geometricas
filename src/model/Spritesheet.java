package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private BufferedImage spritesheet;
	
	public Spritesheet(String path) {
		
		try {
			//Vai carregar a imagem a partir do endereço
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	public BufferedImage getSprite(int x, int y, int width,int height) {
		//Vai retornar a sprite recortada
		return spritesheet.getSubimage(x, y, width, height);
	}

}