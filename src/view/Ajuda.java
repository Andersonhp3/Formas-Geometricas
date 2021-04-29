package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Game;

public class Ajuda extends Tela {
	


	public Ajuda(String path) {
		super(path);
		
	}
		
	public void render(Graphics g){
		g.drawImage(fundo, 0, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,33));
		g.drawString("Teclas de intera��o:", (Game.WIDTH * Game.SCALE)/2 - 108, (Game.HEIGHT * Game.SCALE)/2 -208);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("> Esc - Voltar / Pausar", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -174);
		g.drawString("> Espa�o - Atirar formas geom�tricas", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -154);
		g.drawString("> 1,2,3,4,5,6 - Escolher formas geom�tricas", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -134);
		g.drawString("> Enter - Selecionar personagem / Iniciar jogo", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -114);
		g.drawString("> E - Pegar forma geom�trica", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -94);
		g.drawString("> R - Soltar forma geom�trica", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -74);
		g.drawString("> W/A/S/D e Setas - Movimenta personagem", (Game.WIDTH * Game.SCALE)/2 - 300, (Game.HEIGHT * Game.SCALE)/2 -54);
	}


	


}
