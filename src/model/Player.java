package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.lang.model.element.QualifiedNameable;

import view.Selecao;

public class Player extends Entity{

	public boolean right, up, left, down;
	public boolean FGPrecionada;
	public boolean FGSolta;
	public double speed = 0.7;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public int dir2 = right_dir;
	public int right_dir2 = 0, left_dir2 = 1;
	private int frames = 0, max_frames = 5,index = 0, max_index = 3;
	private boolean moved = false;
	public static double life  = 100, maxLife = 100;
	public boolean isDamaged = false;
	private int damageFrames = 0;
	public static int vidas = 5;
	private double tempoVida = 0.0;
	public static int municaoCirculo=0;
	public static int municaoTriangulo=0;
	public static int municaoLosango=0;
	public static int municaoRetangulo =0;
	public static int municaoQuadrado = 0;
	public static int municaoTrapezio = 150;

	public static double tempoComFormaGeo = 100, maxTempo = 100;

	int pos1 = 1;
	int pos2 = 2;
	int pos3 = 3;
	int pos4 = 4;
	int pos5 = 5;

	private boolean formaCirculo = false;
	private boolean formaRetangulo = false;
	private boolean formaQuadrado = false;
	private boolean formaLosango = false;
	private boolean formaTriangulo = false;
	private boolean formaLosangoGrande = false;



	private Circulo circulo;
	private Triangulo triangulo;
	private Losango losango;
	private Quadrado quadrado;
	private Retangulo retangulo;



	public static boolean acertouBranco;
	public static boolean acertouAzul;
	public static boolean acertouLaranja;
	public static boolean acertouAmarelo;
	public static boolean acertouVermelho;

	private int tickPassos;
	private boolean playPassos= false;
	public boolean tiro;
	public int qtdFormasGeo = 3000;

	private BufferedImage[] rightPlayer1;
	private BufferedImage[] leftPlayer1;
	private BufferedImage[] upPlayer1;
	private BufferedImage[] downPlayer1;
	private BufferedImage[] rightPlayer2;
	private BufferedImage[] leftPlayer2;
	private BufferedImage[] upPlayer2;
	private BufferedImage[] downPlayer2;
	private BufferedImage playerDamage;

	public Player(int x, int y, int widht, int height, BufferedImage sprite) {
		super(x, y, widht, height, sprite);
		rightPlayer1 = new BufferedImage[4];
		leftPlayer1 = new BufferedImage[4];
		upPlayer1 = new BufferedImage[4];
		downPlayer1 = new BufferedImage[4];
		rightPlayer2 = new BufferedImage[4];
		leftPlayer2 = new BufferedImage[4];
		upPlayer2 = new BufferedImage[4];
		downPlayer2 = new BufferedImage[4];
		playerDamage = Game.spritesheet.getSprite(0, 64, 16, 16);

		//Carregando as sprites do jogador 2
		for (int i = 0; i < 4; i++) {//Pegando a animação para a direita
			rightPlayer1[i] = Game.spritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		}
		for (int i = 0; i < 4; i++) {//Pegando a animação para a esquerda
			leftPlayer1[i] = Game.spritesheet.getSprite(0 + (i * 16), 16, 16, 16);
		}
		for (int i = 0; i < 4; i++) {//Pegando a animação para a esquerda
			upPlayer1[i] = Game.spritesheet.getSprite(0 + (i * 16), 32, 16, 16);
		}
		for (int i = 0; i < 4; i++) {//Pegando a animação para a esquerda
			downPlayer1[i] = Game.spritesheet.getSprite(0 + (i * 16), 48, 16, 16);
		}
		//Carregando as sprites do jogador 2
		for (int i = 0; i < 4; i++) {//Pegando a animação para a direita
			rightPlayer2[i] = Game.spritesheet.getSprite(0 + (i * 16), 96, 16, 16);
		}
		for (int i = 0; i < 4; i++) {//Pegando a animação para a esquerda
			leftPlayer2[i] = Game.spritesheet.getSprite(0 + (i * 16), 112, 16, 16);
		}
		for (int i = 0; i < 4; i++) {//Pegando a animação para a esquerda
			upPlayer2[i] = Game.spritesheet.getSprite(0 + (i * 16), 128, 16, 16);
		}
		for (int i = 0; i < 4; i++) {//Pegando a animação para a esquerda
			downPlayer2[i] = Game.spritesheet.getSprite(0 + (i * 16), 144, 16, 16);
		}
	}


	public void tick() {
		depth = 2;
		moved = false;
		if(right && Wold.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir = right_dir;
			dir2 = right_dir2;
			x +=speed;
		}else if(left && Wold.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			dir2 = left_dir2;
			x -=speed;
		}
		if(up && Wold.isFree(this.getX(),(int)(y-speed))) {
			moved = true;
			dir = up_dir;
			y -=speed;
		}else if(down && Wold.isFree(this.getX(),(int)(y+speed))) {
			moved = true;
			dir = down_dir;
			y +=speed;
		}

		if(moved) {
			tempoComFormaGeo+=0.1;
			if (tempoComFormaGeo >= maxTempo) {
				tempoComFormaGeo = 100;
			}
			frames ++;
			if(frames == max_frames) {
				frames = 0;
				index++;
				if(index > max_index) {
					index = 0;
				}
			}
		}

		if(formaCirculo) {
			tempoComFormaGeo-=0.2;
			if(tempoComFormaGeo <=0) {
				formaCirculo = false;
				soltarformaCirculo();

			}
		}
		if(formaTriangulo) {
			tempoComFormaGeo-=0.2;
			if(tempoComFormaGeo <=0) {
				formaTriangulo = false;
				soltarFormaTriangulo();

			}
		}
		if(formaRetangulo) {
			tempoComFormaGeo-=0.2;
			if(tempoComFormaGeo <=0) {
				formaRetangulo = false;
				soltarFormaRetangulo();

			}
		}
		if(formaQuadrado) {
			tempoComFormaGeo-=0.2;
			if(tempoComFormaGeo <=0) {
				formaQuadrado = false;
				soltarFormaQuadrado();

			}
		}
		if(formaLosango) {
			tempoComFormaGeo-=0.2;
			if(tempoComFormaGeo <=0) {
				formaLosango = false;
				soltarFormaLosango();

			}
		}


		this.checkCollisionLifePack();
		this.checkCollisionCirculo();
		this.checkCollisionRetangulo();
		this.checkCollisionQuadrado();
		this.checkCollisionLosango();
		this.checkCollisionTriangulo();
		this.moverVida();



		if(isDamaged) {
			this.damageFrames++;
			if(this.damageFrames ==8) {
				this.damageFrames = 0;
				isDamaged = false;
			}
		}

		if (tiro) {
			tiro = false;
			if(!formaCirculo && !formaRetangulo && !formaTriangulo && !formaLosango && !formaQuadrado) {


				int dx = 0;
				int dy = 0;
				int px = 0;
				int py = 0;
				if(dir == right_dir) {
					dx = 1;
					px = -2;
					py = 3;
				}else if(dir == left_dir){
					dx = -1;
					px = -8;
					py = 3;
				}else if(dir == up_dir) {
					dy = -1;
					px = -5;
					py = -6;
				}else if(dir == down_dir) {
					dy = 1;
					px = -5;
					py = +10;
				}
				if(Game.CUR_LEVEL ==4) {
					if (municaoCirculo > 0/* ||  || 
							||  || */) {					
						if (Game.ui.options[Game.ui.currentOption] == "tecla1") {
							municaoCirculo --;
							TiroFG tiroFG = new TiroFG(this.getX() + px ,this.getY()+ py, 6, 6, null,dx,dy,1);
							Game.tiroFGs.add(tiroFG);
						}
					}

					if(municaoTriangulo>0) {
						if(Game.ui.options[Game.ui.currentOption] == "tecla2") {
							municaoTriangulo --;
							TiroFG tiroFG = new TiroFG(this.getX() + px ,this.getY()+ py, 6, 6, null,dx,dy,2);
							Game.tiroFGs.add(tiroFG);
						}
					}
					if(municaoLosango>0) {
						if(Game.ui.options[Game.ui.currentOption] == "tecla3") {
							municaoLosango --;
							TiroFG tiroFG = new TiroFG(this.getX() + px ,this.getY()+ py, 6, 6, null,dx,dy,3);
							Game.tiroFGs.add(tiroFG);
						}
					}
					if(municaoQuadrado >0 ) {
						if(Game.ui.options[Game.ui.currentOption] == "tecla4") {
							municaoQuadrado --;
							TiroFG tiroFG = new TiroFG(this.getX() + px ,this.getY()+ py, 6, 6, null,dx,dy,4);
							Game.tiroFGs.add(tiroFG);
						}
					}
					if(municaoRetangulo >0) {
						if(Game.ui.options[Game.ui.currentOption] == "tecla5") {
							municaoRetangulo --;
							TiroFG tiroFG = new TiroFG(this.getX() + px ,this.getY()+ py, 6, 6, null,dx,dy,5);
							Game.tiroFGs.add(tiroFG);
						}
					}

				}
				if (municaoTrapezio > 0) {

					if(Game.ui.options[Game.ui.currentOption] == "tecla6") {
						municaoTrapezio--;
						TiroFG tiroFG = new TiroFG(this.getX() + px ,this.getY()+ py, 6, 6, null,dx,dy,6);
						Game.tiroFGs.add(tiroFG);
					}
				}

			}
		}


		if (vidas == 0) {

			//Gameover
			life = 0;
			Game.gameState = "GAME_OVER"; 

		}

		updateCamera();

	}

	public void soltarformaCirculo() {
		if(dir2 == right_dir2) {
			circulo = new Circulo(Game.player.getX()+ 11, Game.player.getY() + 2, 16, 16, Entity.CIRCULO_EN);
			if(this.colideEncaixeCirculo(circulo)) {
				acertouAzul = true;
			}
			else {
				Game.entities.add(circulo);
				acertouAzul = false;
			}									
		}else if(dir2 == left_dir2) {
			circulo = new Circulo(Game.player.getX()- 11, Game.player.getY() - 2, 16, 16, Entity.CIRCULO_EN);
			if(this.colideEncaixeCirculo(circulo)) {
				acertouAzul = true;

			}
			else {
				Game.entities.add(circulo);
				acertouAzul = false;
			}
		}
	}
	public void soltarFormaTriangulo() {
		if(dir2 == right_dir2) {
			triangulo = new Triangulo(Game.player.getX()+ 11, Game.player.getY() + 2, 16, 16, Entity.TRIANGULO_EN);
			if(this.colideEncaixeTriangulo(triangulo)) {
				acertouBranco = true;
				System.out.println(municaoTriangulo);
			}
			else {
				Game.entities.add(triangulo);
				acertouBranco = false;
			}									
		}else if(dir2 == left_dir2) {
			triangulo = new Triangulo(Game.player.getX()- 11, Game.player.getY() - 2, 16, 16, Entity.TRIANGULO_EN);
			if(this.colideEncaixeTriangulo(triangulo)) {
				acertouBranco = true;
			}
			else {
				Game.entities.add(triangulo);
				acertouBranco = false;
			}
		}
	}
	public void soltarFormaLosango() {
		if(dir2 == right_dir2) {
			losango = new Losango(Game.player.getX()+ 11, Game.player.getY() + 2, 16, 16, Entity.LOSANGO_EN);
			if(this.colideEncaixeLosango(losango)) {
				acertouVermelho = true;
			}
			else {
				Game.entities.add(losango);
				acertouVermelho = false;
			}									
		}else if(dir2 == left_dir2) {
			losango = new Losango(Game.player.getX()- 11, Game.player.getY() - 2, 16, 16, Entity.LOSANGO_EN);
			if(this.colideEncaixeLosango(losango)) {
				acertouVermelho = true;
			}
			else {
				Game.entities.add(losango);
				acertouVermelho = false;
			}
		}
	}




	public void soltarFormaRetangulo() {
		if(dir2 == right_dir2) {
			retangulo = new Retangulo(Game.player.getX()+ 11, Game.player.getY() + 2, 16, 16, Entity.RETANGULO_EN);
			if(this.colideEncaixeRetangulo(retangulo)) {
				acertouAmarelo = true;
			}
			else {
				Game.entities.add(retangulo);
				acertouAmarelo = false;
			}									
		}else if(dir2 == left_dir2) {
			retangulo = new Retangulo(Game.player.getX()- 11, Game.player.getY() - 2, 16, 16, Entity.RETANGULO_EN);
			if(this.colideEncaixeRetangulo(retangulo)) {
				acertouAmarelo = true;
			}
			else {
				Game.entities.add(retangulo);
				acertouAmarelo = false;
			}
		}
	}
	public void soltarFormaQuadrado() {
		if(dir2 == right_dir2) {
			quadrado = new Quadrado(Game.player.getX()+ 11, Game.player.getY() + 2, 16, 16, Entity.QUADRADO_EN);
			if(this.colideEncaixeQuadrado(quadrado)) {
				acertouLaranja = true;
			}
			else {
				Game.entities.add(quadrado);
				acertouLaranja = false;
			}									
		}else if(dir2 == left_dir2) {
			quadrado = new Quadrado(Game.player.getX()- 11, Game.player.getY() - 2, 16, 16, Entity.QUADRADO_EN);
			if(this.colideEncaixeQuadrado(quadrado)) {
				acertouLaranja = true;
			}
			else {
				Game.entities.add(quadrado);
				acertouLaranja = false;
			}
		}
	}

	public void soltarFormaLosangoGrande() {
		if(dir2 == right_dir2) {
			losango = new Losango(Game.player.getX()+ 11, Game.player.getY() + 2, 16, 16, Entity.LOSANGO_EN);
			if(this.colideEncaixeLosango(losango)) {
				acertouVermelho = true;
			}
			else {
				Game.entities.add(losango);
				acertouVermelho = false;
			}									
		}else if(dir2 == left_dir2) {
			losango = new Losango(Game.player.getX()- 11, Game.player.getY() - 2, 16, 16, Entity.LOSANGO_EN);
			if(this.colideEncaixeLosango(losango)) {
				acertouVermelho = true;
			}
			else {
				Game.entities.add(losango);
				acertouVermelho = false;
			}
		}
	}

	public void updateCamera() {
		//Pega a posição do personagem - 
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,Wold.WIDTH*16 - Game.WIDTH); 
		Camera.y =  Camera.clamp(this.getY() - (Game.HEIGHT/2),0, Wold.HEIGHT*16 - Game.HEIGHT);
	}

	public void checkCollisionLifePack() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Vida) {
				if(Entity.isColidding(this, atual)) {
					Som.vida.play();
					if(life == 100) {
						vidas += 1;
						if (vidas >5) {
							vidas = 5;
						}
					}else {
						life=100;									
					}
					Game.entities.remove(atual);
				}
			}
		}
	}

	public void checkCollisionCirculo() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Circulo) {
				if(Entity.isColidding(this, atual) && FGPrecionada  && !formaRetangulo && !formaTriangulo && !formaLosango && !formaQuadrado) {
					formaCirculo = true;
					Game.entities.remove(atual);
				}
			}
		}
		if(FGSolta && formaCirculo) {
			formaCirculo = false;
			soltarformaCirculo();
		}

	}

	public boolean colideEncaixeCirculo(Circulo circulo) {
		Game.circulos.add(circulo);
		for(int i = 0; i < Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof EncaixeCirculo) {
				if (Entity.isColidding(circulo, e)) {
					Game.entities.add(new Circulo(Wold.posAzulX, Wold.posAzulY, 16, 16, Entity.CIRCULO_EN));
					Som.acerto.play();
					if(Game.getCUR_LEVEL() ==1) {
						municaoCirculo +=1;
						if(municaoCirculo >1) {
							municaoCirculo = 1;
						}
					}
					else if(Game.getCUR_LEVEL() == 2) {
						municaoCirculo =2;
						if(municaoCirculo >2) {
							municaoCirculo = 2;
						}
					}else if(Game.getCUR_LEVEL() == 3) {
						municaoCirculo =3;
						if(municaoCirculo >3) {
							municaoCirculo = 3;
						}
					}
					return true;

				}
			}
		}
		return false;
	}

	public boolean colideEncaixeTriangulo(Triangulo triangulo) {
		Game.triangulos.add(triangulo);
		for(int i = 0; i < Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof EncaixeTriangulo) {
				if (Entity.isColidding(triangulo, e)) {
					Game.entities.add(new Triangulo(Wold.posBraX, Wold.posBraY, 16, 16, Entity.TRIANGULO_EN));
					Som.acerto.play();
					if(Game.getCUR_LEVEL() ==1) {
						municaoTriangulo =1;
						if(municaoTriangulo >1) {
							municaoTriangulo = 1;
						}
					}
					else if(Game.getCUR_LEVEL() == 2) {
						municaoTriangulo =2;
						if(municaoTriangulo >2) {
							municaoTriangulo= 2;
						}
					}else if(Game.getCUR_LEVEL() == 3) {
						municaoTriangulo =3;
						if(municaoTriangulo >3) {
							municaoTriangulo = 3;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	public boolean colideEncaixeTriangulo(TriangPequeno triangPequeno) {
		Game.triangPequenos.add(triangPequeno);
		for(int i = 0; i < Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof EncaixeTriangulo) {
				if (Entity.isColidding(triangPequeno, e)) {
					Game.entities.add(new Triangulo(Wold.posBraX, Wold.posBraY, 8, 7, Entity.TRIANGULO_PEQUENO));
					//Errado - salvar no xml
					return true;
				}
			}
		}
		return false;
	}

	public boolean colideEncaixeLosango(Losango losango) {
		Game.losangos.add(losango);
		for(int i = 0; i < Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof EncaixeLosango) {
				if (Entity.isColidding(losango, e)) {
					Game.entities.add(new Losango(Wold.posVermX, Wold.posVermY, 16, 16, Entity.LOSANGO_EN));
					Som.acerto.play();
					if(Game.getCUR_LEVEL() ==1) {
						municaoLosango =1;
						if(municaoLosango >1) {
							municaoLosango = 1;
						}
					}
					else if(Game.getCUR_LEVEL() == 2) {
						municaoLosango =2;
						if(municaoLosango >2) {
							municaoLosango = 2;
						}
					}else if(Game.getCUR_LEVEL() == 3) {
						municaoLosango =3;
						if(municaoLosango >3) {
							municaoLosango = 3;
						}
					}
					return true;
				}
			}
		}
		return false;
	}



	public boolean colideEncaixeQuadrado(Quadrado quadrado) {

		Game.quadrados.add(quadrado);
		for(int i = 0; i < Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof EncaixeQuadrado) {
				if (Entity.isColidding(quadrado, e)) {
					Game.entities.add(new Quadrado(Wold.posLaraX, Wold.posLaraY, 16, 16, Entity.QUADRADO_EN));
					Som.acerto.play();
					if(Game.getCUR_LEVEL() ==1) {
						municaoQuadrado =1;
						if(municaoQuadrado >1) {
							municaoQuadrado = 1;
						}
					}
					else if(Game.getCUR_LEVEL() == 2) {
						municaoQuadrado =2;
						if(municaoQuadrado >2) {
							municaoQuadrado= 2;
						}
					}else if(Game.getCUR_LEVEL() == 3) {
						municaoQuadrado =3;
						if(municaoQuadrado>3) {
							municaoQuadrado = 3;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public void moverVida() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Vida) {
				tempoVida+=0.1;

				if (tempoVida >= 100) {
					tempoVida = 0;
					Game.entities.remove(atual);

					int random = Game.random.nextInt(4) +1;
					if(random == pos1 ) {
						Game.entities.add(new Vida(140, 280,  16, 16, Entity.VIDA_EN));
					}else if(random == pos2){
						Game.entities.add(new Vida(270, 200,  16, 16, Entity.VIDA_EN));
					}else if(random == pos3){
						Game.entities.add(new Vida(220, 60,  16, 16, Entity.VIDA_EN));
					}else if(random == pos4){
						Game.entities.add(new Vida(100, 14,  16, 16, Entity.VIDA_EN));
					}else if(random == pos5){
						Game.entities.add(new Vida(280, 280,  16, 16, Entity.VIDA_EN));
					}



				}
			}

		}
	}


	public boolean colideEncaixeRetangulo(Retangulo retangulo) {
		Game.retangulos.add(retangulo);
		for(int i = 0; i < Game.entities.size();i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof EncaixeRetangulo) {
				if (Entity.isColidding(retangulo, e)) {
					Game.entities.add(new Retangulo(Wold.posAmareX, Wold.posAmareY, 16, 16, Entity.RETANGULO_EN));
					Som.acerto.play();
					if(Game.getCUR_LEVEL() ==1) {
						municaoRetangulo =1;
						if(municaoRetangulo>1) {
							municaoRetangulo = 1;
						}
					}
					else if(Game.getCUR_LEVEL() == 2) {
						municaoRetangulo =2;
						if(municaoRetangulo >2) {
							municaoRetangulo = 2;
						}
					}else if(Game.getCUR_LEVEL() == 3) {
						municaoRetangulo =3;
						if(municaoRetangulo >3) {
							municaoRetangulo = 3;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public void checkCollisionTriangulo() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Triangulo) {
				if(Entity.isColidding(this, atual) && FGPrecionada && !formaCirculo && !formaRetangulo  && !formaLosango && !formaQuadrado) {
					formaTriangulo = true;
					Game.entities.remove(atual);


				}
			}
		}
		if(FGSolta && formaTriangulo) {
			formaTriangulo = false;
			soltarFormaTriangulo();

		}
	}


	

	public void checkCollisionLosango() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Losango) {
				if(Entity.isColidding(this, atual) && FGPrecionada && !formaCirculo && !formaRetangulo && !formaTriangulo &&  !formaQuadrado ) {
					formaLosango = true;
					Game.entities.remove(atual);
				}
			}
		}
		if(FGSolta && formaLosango) {
			formaLosango = false;
			soltarFormaLosango();

		}

	}

	public void checkCollisionRetangulo() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Retangulo) {
				if(Entity.isColidding(this, atual) && FGPrecionada && !formaCirculo  && !formaTriangulo && !formaLosango && !formaQuadrado) {
					formaRetangulo = true;
					Game.entities.remove(atual);

				}
			}
		}
		if(FGSolta && formaRetangulo) {
			formaRetangulo = false;
			soltarFormaRetangulo();

		}
	}

	public void checkCollisionQuadrado() {
		for(int i = 0; i < Game.entities.size();i++ ) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Quadrado) {
				if(Entity.isColidding(this, atual) && FGPrecionada && !formaCirculo && !formaRetangulo && !formaTriangulo && !formaLosango) {
					formaQuadrado = true;
					Game.entities.remove(atual);
				}
			}
		}
		if(FGSolta && formaQuadrado) {
			formaQuadrado = false;
			soltarFormaQuadrado();
		}
	}


	public void desenharFormaFrente(Graphics g,BufferedImage imagem) {
		if(Selecao.jogador == 1) {
			if(dir2 == right_dir2) {
				g.drawImage(rightPlayer1[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				g.drawImage(imagem, this.getX() + 11 - Camera.x, this.getY()+2 - Camera.y, null);				
			}else if(dir2 == left_dir2) {
				g.drawImage(leftPlayer1[index], this.getX() - Camera.x, this.getY()- Camera.y, null);
				g.drawImage(imagem, this.getX() -10 - Camera.x, this.getY() +2 - Camera.y, null);

			}
		}else if(Selecao.jogador == 2) {
			if(dir2 == right_dir2) {
				g.drawImage(rightPlayer2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				g.drawImage(imagem, this.getX() + 11 - Camera.x, this.getY()+2 - Camera.y, null);				
			}else if(dir2 == left_dir2) {
				g.drawImage(leftPlayer2[index], this.getX() - Camera.x, this.getY()- Camera.y, null);
				g.drawImage(imagem, this.getX() -10 - Camera.x, this.getY() +2 - Camera.y, null);

			}
		}
	}



	public void render(Graphics g) {

		if(!isDamaged && !formaCirculo && !formaRetangulo && !formaTriangulo && !formaLosango && !formaQuadrado) {
			if(Selecao.jogador == 1) {
				if(dir == right_dir) {
					g.drawImage(rightPlayer1[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

				}else if(dir == left_dir) {
					g.drawImage(leftPlayer1[index], this.getX() - Camera.x, this.getY()- Camera.y, null);

				}else if(dir == up_dir) {
					g.drawImage(upPlayer1[index], this.getX()- Camera.x, this.getY()- Camera.y, null);

				}else if(dir == down_dir) {
					g.drawImage(downPlayer1[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
				}
			}else if(Selecao.jogador == 2) {
				if(!isDamaged && !formaCirculo && !formaRetangulo && !formaTriangulo && !formaLosango && !formaQuadrado) {
					if(dir == right_dir) {
						g.drawImage(rightPlayer2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

					}else if(dir == left_dir) {
						g.drawImage(leftPlayer2[index], this.getX() - Camera.x, this.getY()- Camera.y, null);

					}else if(dir == up_dir) {
						g.drawImage(upPlayer2[index], this.getX()- Camera.x, this.getY()- Camera.y, null);

					}else if(dir == down_dir) {
						g.drawImage(downPlayer2[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
					}
				}
			}
		}else if(isDamaged) {
			g.drawImage(playerDamage,this.getX() - Camera.x, this.getY()- Camera.y,  null);
		}else if(formaCirculo) {
			desenharFormaFrente(g,Entity.CIRCULO_EN);
		}else if(formaLosango) {
			desenharFormaFrente(g, Entity.LOSANGO_EN);
		}else if(formaRetangulo) {
			desenharFormaFrente(g, Entity.RETANGULO_EN);
		}else if(formaQuadrado) {
			desenharFormaFrente(g, Entity.QUADRADO_EN);
		}else if(formaTriangulo) {
			desenharFormaFrente(g, Entity.TRIANGULO_EN);
		}else if(formaLosangoGrande) {
			desenharFormaFrente(g, Entity.LOSANGO_GRANDE);
		}
	}	
}
