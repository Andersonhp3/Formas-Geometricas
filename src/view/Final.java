package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Game;

public class Final {
	
	BufferedImage telaFinal = null;
	private boolean showMessageGameOver = false;
	private int framesGameOver = 0;
	public boolean enterFinal;
	
	public Final() {
		try {
			telaFinal = ImageIO.read(getClass().getResource("/telaInicio.png"));
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
		if(enterFinal) {
			Game.gameState = "MENU";
		}
	}
	public void render(Graphics g) {
		g.drawImage(telaFinal, 0, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,38));
		g.drawString("Parabéns!!", (Game.WIDTH * Game.SCALE)/2 - 62, (Game.HEIGHT * Game.SCALE)/2 -194);
		g.setFont(new Font("gabriola",Font.BOLD,28));
		g.drawString("Você conseguiu passar de todas as fases.", (Game.WIDTH * Game.SCALE)/2 - 212, (Game.HEIGHT * Game.SCALE)/2 -154);
		g.setColor(Color.red);
		if(showMessageGameOver){
			g.drawString(">Pressione enter para voltar ao menu<", (Game.WIDTH * Game.SCALE)/2 - 205, (Game.HEIGHT * Game.SCALE)/2 -90) ;
		}
	}

}
