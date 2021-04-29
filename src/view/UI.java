package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Entity;
import model.Game;

public class UI {



	public String[] options = {"tecla1","tecla2","tecla3","tecla4", "tecla5","tecla6"};
	public boolean selecionaFC, selecionaFT,selecionaFR,selecionaFQ,selecionaFL,selecionaFTrapezio;



	public int currentOption = 0;
	public int maxOption = options.length - 1;



	public void tick() {
		if(selecionaFC) {
			selecionaFC =false;
			currentOption = 0;
		}
		else if(selecionaFT) {
			selecionaFT =false;
			currentOption = 1;
		}else if(selecionaFL) {
			selecionaFL =false;
			currentOption = 2;
		}else if(selecionaFQ) {
			selecionaFQ =false;
			currentOption = 3;
		}else if(selecionaFR) {
			selecionaFR =false;
			currentOption = 4;
		}else if(selecionaFTrapezio) {
			selecionaFTrapezio =false;
			currentOption = 5;
		}



	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(733, 15, 150,20 );
		g.setColor(Color.GREEN);
		g.fillRect(733, 15, (int)((Game.player.life/Game.player.maxLife)*150),20 );
		g.setColor(Color.white);
		g.setFont(new Font("aria",Font.BOLD,16));
		g.drawString((int)Game.player.life + "/" + (int)Game.player.maxLife,780, 30 );
		for (int i = 0; i <Game.player.vidas ; i++) {
			g.drawImage(Entity.VIDA_EN, 728 + (i * 30), 30,40, 40, null);
		}
		g.setColor(Color.white);
		g.setFont(new Font("aria",Font.BOLD,16));
		g.drawString("Level: " + Game.CUR_LEVEL,737, 90 );
		g.drawImage(Entity.CIRCULO_EN, 728, 100,35, 35, null);
		g.drawString(": " + Game.player.municaoCirculo,765, 122);
		g.drawImage(Entity.TRIANGULO_EN, 728, 130,35, 35, null);
		g.drawString(": " + Game.player.municaoTriangulo,765, 157);
		g.drawImage(Entity.LOSANGO_EN, 728, 165,35, 35, null);
		g.drawString(": " + Game.player.municaoLosango,765, 189);
		g.drawImage(Entity.QUADRADO_EN, 726, 197,35, 35, null);
		g.drawString(": " + Game.player.municaoQuadrado,765, 222);
		g.drawImage(Entity.RETANGULO_EN, 728, 229,35, 35, null);
		g.drawString(": "+ Game.player.municaoRetangulo,765, 252);
		g.drawImage(Entity.TRAPEZIO_EN, 730, 262,35, 35, null);
		g.drawString(": "+ Game.player.municaoTrapezio,765, 282);


		g.setColor(Color.RED);
		g.setFont(new Font("aria",Font.BOLD,18));
		if(options[currentOption] == "tecla1") {
			g.drawString("<<", 790,122);
		}else  if(options[currentOption] == "tecla2") {
			g.drawString("<<", 790,157);
		}else  if(options[currentOption] == "tecla3") {
			g.drawString("<<", 790,189);
		}else  if(options[currentOption] == "tecla4") {
			g.drawString("<<", 790,222);
		}else  if(options[currentOption] == "tecla5") {
			g.drawString("<<", 790,252);
		}else  if(options[currentOption] == "tecla6") {
			g.drawString("<<", 805,282);
		}


		g.setColor(Color.white);
		g.setFont(new Font("aria",Font.BOLD,16));
		g.drawString("Tempo: ",730, 320 );
		g.setColor(Color.gray);
		g.fillRect(733, 330, 150,10 );
		g.setColor(Color.WHITE);
		g.fillRect(733, 330, (int)((Game.player.tempoComFormaGeo/Game.player.maxTempo)*150),10 );


	}

}
