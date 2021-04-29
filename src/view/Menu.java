package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Game;
import model.Spritesheet;
import model.Wold;

public class Menu {




	public String[] options = {"novo jogo","selecionar","ajuda","creditos", "sair"};
	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up,down, enter;


	BufferedImage logo = null;
	BufferedImage fundo = null;
	BufferedImage jogador1 = null;
	BufferedImage jogador2 = null;
	public static boolean pause = false;



	public Menu() {
		try {
			logo = ImageIO.read(getClass().getResource("/logoJogo.png"));
			fundo = ImageIO.read(getClass().getResource("/fundo2.png"));
			jogador1 = ImageIO.read(getClass().getResource("/jogador1.png"));
			jogador2 = ImageIO.read(getClass().getResource("/jogador2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void tick() {
		if(up) {
			//Se apertar para cima o marcador vai subir as opções
			up = false;
			//Depois coloca o up em false para não subir infinitamente
			currentOption--;
			if (currentOption < 0) {
				currentOption = maxOption;
			}
		}
		if (down) {
			//Se apertar para baixo o marcador vai descer as opções
			down = false;
			//Depois coloca o do down em false para não descer infinitamente
			currentOption ++;
			if (currentOption > maxOption) {
				currentOption = 0;

			}
		}
		if (enter) {
			enter = false;
			if (options[currentOption] == "novo jogo"  || options[currentOption] == "Continuar") {
				Game.gameState = "TELA_INICIO";
				pause = false;
			}else if(options[currentOption] == "selecionar") {
				Game.gameState = "SELECAO";
			}else if(options[currentOption] == "ajuda") {
				Game.gameState = "AJUDA";
			}else if(options[currentOption] == "creditos") {
				Game.gameState = "CREDITO";
			}else if(options[currentOption] == "sair") {
				System.out.println();
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.drawImage(fundo, 0, 0, null);
		g.drawImage(logo, (Game.WIDTH * Game.SCALE)/2 - 200, (Game.HEIGHT * Game.SCALE)/2 -230, null);
		if(Selecao.jogador == 1) {
			g.drawImage(jogador1, 60, 316, null);
		}else if(Selecao.jogador == 2) {
			g.drawImage(jogador2, 60, 316, null);
		}
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,38));
		if(pause == false) {
			g.drawString("Novo jogo", (Game.WIDTH * Game.SCALE)/2 - 77, (Game.HEIGHT * Game.SCALE)/2 -44);
		}else {
			g.drawString("voltar ao jogo", (Game.WIDTH * Game.SCALE)/2 - 77, (Game.HEIGHT * Game.SCALE)/2 -44);
		}
		g.drawString("Selecionar personagem", (Game.WIDTH * Game.SCALE)/2 - 77, (Game.HEIGHT * Game.SCALE)/2 -9);
		g.drawString("Ajuda", (Game.WIDTH * Game.SCALE)/2 - 77, (Game.HEIGHT * Game.SCALE)/2 +26);
		g.drawString("Crédito", (Game.WIDTH * Game.SCALE)/2 - 77, (Game.HEIGHT * Game.SCALE)/2 +61);
		g.drawString("Sair", (Game.WIDTH * Game.SCALE)/2 - 77, (Game.HEIGHT * Game.SCALE)/2 +93);

		if(options[currentOption] == "novo jogo") {
			g.drawString(">", (Game.WIDTH * Game.SCALE)/2 - 100, (Game.HEIGHT * Game.SCALE)/2 -46);
		}else  if(options[currentOption] == "selecionar") {
			g.drawString(">", (Game.WIDTH * Game.SCALE)/2 - 100, (Game.HEIGHT * Game.SCALE)/2 - 11);
		}else  if(options[currentOption] == "ajuda") {
			g.drawString(">", (Game.WIDTH * Game.SCALE)/2 - 100, (Game.HEIGHT * Game.SCALE)/2 + 24);
		}else  if(options[currentOption] == "creditos") {
			g.drawString(">", (Game.WIDTH * Game.SCALE)/2 - 100, (Game.HEIGHT * Game.SCALE)/2 + 59);
		}else  if(options[currentOption] == "sair") {
			g.drawString(">", (Game.WIDTH * Game.SCALE)/2 - 100, (Game.HEIGHT * Game.SCALE)/2 + 91);
		}

	}


}
