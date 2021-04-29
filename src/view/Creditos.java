package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import model.Game;

public class Creditos extends Tela {


	
	public Creditos(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	
	public void render(Graphics g){
		g.drawImage(fundo, 0, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font("gabriola",Font.BOLD,33));
		g.drawString("Cr�ditos: ", (Game.WIDTH * Game.SCALE)/2 - 63, (Game.HEIGHT * Game.SCALE)/2 -208);
		g.setFont(new Font("gabriola",Font.BOLD,26));
		g.drawString(">> Universidade Federal Rural de Pernambuco", (Game.WIDTH * Game.SCALE)/2 - 320, (Game.HEIGHT * Game.SCALE)/2 -164);
		g.drawString(">> Aluno: Anderson Ricardo Henrique Pinto", (Game.WIDTH * Game.SCALE)/2 - 320, (Game.HEIGHT * Game.SCALE)/2 -142);
		g.drawString(">> Disciplina: Modelagem e Programa��o Orientada a Objetos", (Game.WIDTH * Game.SCALE)/2 - 320, (Game.HEIGHT * Game.SCALE)/2 -120);
		g.drawString(">> Professor: Rico D'Emery", (Game.WIDTH * Game.SCALE)/2 - 320, (Game.HEIGHT * Game.SCALE)/2 -98);
		g.drawString(">> Jogo: Formas Geom�tricas", (Game.WIDTH * Game.SCALE)/2 - 320, (Game.HEIGHT * Game.SCALE)/2 -76);
		
	}
}
