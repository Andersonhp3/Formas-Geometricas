package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Game;
import model.Player;

public class Selecao {

	BufferedImage fundo = null;
	BufferedImage moldura = null;
	public String[] options = {"primeiro", "segundo"};
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	private boolean showMessageGameOver = false;
	private int framesGameOver = 0;
	public boolean left,right, enter;
	public static int jogador = 1;
	

	public Selecao() {
		try {
			fundo = ImageIO.read(getClass().getResource("/selecao.png"));
			moldura = ImageIO.read(getClass().getResource("/moldura.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick() {
		this.framesGameOver++;
		if (this.framesGameOver == 34) {
			this.framesGameOver = 0;
			if (this.showMessageGameOver) {
				this.showMessageGameOver = false;
			}else {
				this.showMessageGameOver = true;  
			}
		}
		if(right) {
			//Se apertar para cima o marcador vai subir as opções
			right = false;
			//Depois coloca o up em false para não subir infinitamente
			currentOption--;
			if (currentOption < 0) {
				currentOption = maxOption;
			}
		}
		if (left) {
			//Se apertar para baixo o marcador vai descer as opções
			left = false;
			//Depois coloca o do down em false para não descer infinitamente
			currentOption ++;
			if (currentOption > maxOption) {
				currentOption = 0;

			}
		}if (enter) {
			enter = false;
			
			if (options[currentOption] == "primeiro") {
				jogador = 2;
				Game.gameState = "MENU";
			}else if(options[currentOption] == "segundo") {
				jogador = 1;
				Game.gameState = "MENU";
				
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(fundo, 0, 0, null);
		if(options[currentOption] == "primeiro") {
			if(showMessageGameOver) {
				g.drawImage(moldura, 287, 90, null);
			}
		}else if(options[currentOption] == "segundo") {
			if(showMessageGameOver) {
				g.drawImage(moldura, 398, 90, null);
			}
		}
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,25));
		g.drawString("Pressione enter para selecionar", 230, 230);

	}
}
