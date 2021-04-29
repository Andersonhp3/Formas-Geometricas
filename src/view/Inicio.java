package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Game;

public class Inicio {


	BufferedImage telaInicio = null;
	private boolean showMessageGameOver = false;
	private int framesGameOver = 0;
	public boolean enterInicio;



	public Inicio() {
		try {
			telaInicio = ImageIO.read(getClass().getResource("/telaInicio.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick() {
		this.framesGameOver++;
		if (this.framesGameOver == 30) {
			this.framesGameOver = 0;
			if (this.showMessageGameOver) {
				this.showMessageGameOver = false;
			}else {
				this.showMessageGameOver = true;  
			}
		}
		if(enterInicio) {
			Game.gameState = "NORMAL";
		}
	}

	public void render(Graphics g) {
		g.drawImage(telaInicio, 0, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,38));
		g.drawString("Objetivo:", (Game.WIDTH * Game.SCALE)/2 - 52, (Game.HEIGHT * Game.SCALE)/2 -204);
		g.setFont(new Font("gabriola",Font.BOLD,25));
		g.drawString("Você deve encaixar as formas geométricas nos lugares corretos", (Game.WIDTH * Game.SCALE)/2 - 262, (Game.HEIGHT * Game.SCALE)/2 -174);
		g.drawString("para passar de level e derrotar o boss na fase final.", (Game.WIDTH * Game.SCALE)/2 - 212, (Game.HEIGHT * Game.SCALE)/2 -154);
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,38));
		g.drawString("Dicas: ", (Game.WIDTH * Game.SCALE)/2 - 38, (Game.HEIGHT * Game.SCALE)/2 -115);
		g.setFont(new Font("gabriola",Font.BOLD,25));
		g.drawString("O jogador  deve  andar  pelo mapa para encher o tempo de carregar as formas", (Game.WIDTH * Game.SCALE)/2 - 322, (Game.HEIGHT * Game.SCALE)/2 -85);
		g.drawString("geométricas. Economize munição para o boss. ",(Game.WIDTH * Game.SCALE)/2 - 200, (Game.HEIGHT * Game.SCALE)/2 -63);
		g.setColor(Color.red);
		if(showMessageGameOver){
			g.drawString(">Pressione enter para iniciar<", (Game.WIDTH * Game.SCALE)/2 - 125, (Game.HEIGHT * Game.SCALE)/2 +20) ;
		}
	}
}
