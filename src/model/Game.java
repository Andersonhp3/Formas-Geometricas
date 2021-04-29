package model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.text.Position.Bias;

import view.Ajuda;
import view.Creditos;
import view.Final;
import view.Inicio;
import view.Menu;
import view.Selecao;
import view.UI;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;

	private Thread thread;
	public static JFrame frame;

	private BufferedImage image;
	public static Spritesheet spritesheet;

	private boolean isRunning = true;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;
	public static boolean level4;
	public static boolean level1;
	private BufferedImage imagem;
	
	public static UI ui;


	public static String gameState = "MENU";
	private boolean showMessageGameOver = false;
	private int framesGameOver = 0;

	public static Player player;
	public static Wold wold;
	private boolean restartGame = false; 
	public static Random random;
	public static int CUR_LEVEL = 1;

	public int MAX_LEVEL = 4;



	public Menu menu;
	public Inicio inicio;
	public Final final1;


	public Selecao selecao;
	public Ajuda ajuda;
	public Creditos creditos;
	public static Boss boss;
	public static String newWold;
	
	public static Enemy enemy;

	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<Boss> bossA;
	public static List<Circulo> circulos;
	public static List<Triangulo> triangulos;
	public static List<Losango> losangos;
	public static List<Quadrado> quadrados;
	public static List<Retangulo> retangulos;
	public static List<TiroFG> tiroFGs;
	public static List<LosangoGrande> losangoGrandes;
	public static List<TriangPequeno> triangPequenos;
	public static List<RetanguloPequeno> retanguloPequenos;
	
	



	public Game() {
		Som.musicafundo.loop();
		random = new Random();
		addKeyListener(this);;
		setPreferredSize(new Dimension(WIDTH* SCALE, HEIGHT * SCALE));
		initFrame();
		//Inicializando objetos
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		bossA = new ArrayList<Boss>();
		circulos = new ArrayList<Circulo>();
		triangulos = new ArrayList<Triangulo>();
		losangos = new ArrayList<Losango>();
		quadrados = new ArrayList<Quadrado>();
		retangulos = new ArrayList<Retangulo>();
		tiroFGs = new ArrayList<TiroFG>();
		retanguloPequenos = new ArrayList<RetanguloPequeno>();
		triangPequenos = new ArrayList<TriangPequeno>();
		losangoGrandes = new ArrayList<LosangoGrande>();

		spritesheet = new Spritesheet("/Spritesheet.png");		
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(0, 0, 16, 16));
		entities.add(player);
		wold = new Wold("/level1.png");	

		ui = new UI();
		menu = new Menu();
		inicio = new Inicio();
		final1 = new Final();
		selecao = new Selecao();
		ajuda = new Ajuda("/fundoAjuda.png");
		creditos = new Creditos("/fundoAjuda.png");
		instanciarInimigo();

		

	}


	public static void instanciarInimigo() {
		if(level4) {
			boss = new Boss(200, 100, 26, 32, Entity.BOSS_EN);
			Game.entities.add(boss);
			Game.bossA.add(boss);
			Enemy inimigoAzul2 = new Enemy(100, 260, 16, 16, Entity.INIMIGO_Azul_EN,3);
			Enemy inimigoAzul = new Enemy(200, 120, 16, 16, Entity.INIMIGO_Azul_EN,3);
			Enemy inimigoBranco = new Enemy(40, 240, 16, 16, Entity.INIMIGO_BRANCO_EN, 4);
			Enemy inimigoBranco2 = new Enemy(230, 270, 16, 16, Entity.INIMIGO_BRANCO_EN, 4);
			adicionarInimigo(inimigoBranco);
			adicionarInimigo(inimigoAzul2);
			adicionarInimigo(inimigoBranco2);
			adicionarInimigo(inimigoAzul);
		}else {
			Enemy inimigoAzul = new Enemy(200, 120, 16, 16, Entity.INIMIGO_Azul_EN,3);
			Enemy inimigoAzul2 = new Enemy(100, 280, 16, 16, Entity.INIMIGO_Azul_EN,3);
			Enemy inimigoBranco = new Enemy(40, 280, 16, 16, Entity.INIMIGO_BRANCO_EN, 4);
			Enemy inimigoBranco2 = new Enemy(230, 270, 16, 16, Entity.INIMIGO_BRANCO_EN, 4);
			Enemy inimigoVerde = new Enemy(270, 45, 16, 16, Entity.INIMIGO_VERDE_EN,2);
			Enemy inimigoVerde2 = new Enemy(100, 170, 16, 16, Entity.INIMIGO_VERDE_EN,2);
			boss = new Boss(200, 100, 26, 32, Entity.BOSS_EN);
			adicionarInimigo(inimigoVerde);
			adicionarInimigo(inimigoVerde2);
			adicionarInimigo(inimigoAzul);
			adicionarInimigo(inimigoBranco);
			adicionarInimigo(inimigoBranco2);
			adicionarInimigo(inimigoAzul2);
		}



	}

	public static void adicionarInimigo(Enemy inimigo) {
		Game.entities.add(inimigo);
		Game.enemies.add(inimigo);
	}


	
	private void initFrame() {
		frame = new JFrame("Formas Geométricas");
		frame.add(this);
		frame.setUndecorated(true);
		frame.setResizable(false);//Não redimensionar a tela	
		frame.pack();
		frame.setLocationRelativeTo(null);//Tela no centro
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);//Deixar a tela visivel

	}

	public synchronized void start() {
		thread = new Thread(this);
		this.isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		//Atualizar o jogo
		if(gameState == "NORMAL") {
			ui.tick();
			frame.setSize(300 *SCALE,HEIGHT * SCALE);
			frame.setLocationRelativeTo(null);
			this.restartGame = false;
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			for (int i = 0; i < tiroFGs.size(); i++) {
				tiroFGs.get(i).tick();
			}
			if(Game.boss.life <= 0) {
				gameState = "FINAL";
				CUR_LEVEL = 0;
				Game.boss.life = 150;
				CUR_LEVEL++;
				level4 = false;
				if(CUR_LEVEL > MAX_LEVEL) {
					CUR_LEVEL = 1;
					
					

				}
				newWold = "level"+CUR_LEVEL+".png";
				Wold.restartGame(newWold);

			}

			
		

			

			
			if (player.acertouAzul && player.acertouBranco && player.acertouAmarelo && player.acertouLaranja && player.acertouVermelho) {
				CUR_LEVEL++;
				player.acertouAzul =false;
				player.acertouBranco = false;
				player.acertouAmarelo = false;
				player.acertouLaranja = false;
				player.acertouVermelho = false;
				if(CUR_LEVEL > MAX_LEVEL) {
					CUR_LEVEL = 1;
					

				}
				if (CUR_LEVEL == 4) {
					level4 = true;
				}


				newWold = "level"+CUR_LEVEL+".png";
				Wold.restartGame(newWold);
			}			
		}else if(gameState == "GAME_OVER") {
			this.framesGameOver++;
			if (this.framesGameOver == 30) {
				this.framesGameOver = 0;
				if (this.showMessageGameOver) {
					this.showMessageGameOver = false;
				}else {
					this.showMessageGameOver = true;  
				}				
			}if(restartGame) {
				this.restartGame = false;
				this.gameState = "NORMAL";
				player.vidas = 5;
				player.life = 100;
				CUR_LEVEL = 1;
				String newWold = "level"+CUR_LEVEL+".png";
				Wold.restartGame(newWold);
			}
		}else if(gameState == "MENU") {
			//
			menu.tick();
			frame.setSize(WIDTH * SCALE,HEIGHT * SCALE);
			frame.setLocationRelativeTo(null);
		}else if(gameState == "TELA_INICIO") {
			inicio.tick();
		}else if(gameState == "FINAL") {
			final1.tick();
			frame.setSize(WIDTH * SCALE,HEIGHT * SCALE);
			frame.setLocationRelativeTo(null);
		}else if(gameState == "SELECAO"){
			selecao.tick();
		}else if(gameState == "AJUDA") {
			ajuda.tick();
		}else if(gameState == "CREDITOS") {
			creditos.tick();
		}

	}

	public void render() {//Desenhar
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		/*Renderizando o jogo*/
		wold.render(g);
		Collections.sort(entities,Entity.nodeSorter);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);;
		}
		for (int i = 0; i < tiroFGs.size(); i++) {
			tiroFGs.get(i).render(g); ;
		}
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH * SCALE,HEIGHT * SCALE,null);

		g.setColor(new Color(0,0,0,100));
		g.fillRect((WIDTH * SCALE) - 80, (HEIGHT * SCALE)/2 -237, 75, 37);
		g.setFont(new Font("arial",Font.BOLD,15));
		g.setColor(Color.WHITE);
		g.drawString("E - Pegar", (WIDTH * SCALE)/2 +285 , (HEIGHT * SCALE)/2 -220);
		g.drawString("R - Soltar", (WIDTH * SCALE)/2 +285 , (HEIGHT * SCALE)/2 -205);
		g.setColor(Color.black);
		g.fillRect(720, 0, 200, 480);
		ui.render(g);


		if(gameState == "GAME_OVER") {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0,0,0,100));
			g2.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
			g.setFont(new Font("arial",Font.BOLD,36));
			g.setColor(Color.WHITE);
			g.drawString("Game Over", (WIDTH * SCALE)/2 - 92, (HEIGHT * SCALE)/2 +0) ;
			g.setFont(new Font("arial",Font.BOLD,23));
			if(showMessageGameOver)
				g.drawString(">Pressione Enter para reiniciar<", (WIDTH * SCALE)/2 - 180, (HEIGHT * SCALE)/2 +40) ;
		}else if(gameState == "MENU") {
			menu.render(g);
		}else if(gameState == "TELA_INICIO") {
			inicio.render(g);
		}else if(gameState == "FINAL") {
			final1.render(g);
		}else if(gameState == "SELECAO") {
			selecao.render(g);
		}else if(gameState == "AJUDA") {
			ajuda.render(g);
		}else if(gameState == "CREDITO") {
			creditos.render(g);
		}
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime(); //Ultimo momento em nanotime
		double amoutOfTicks = 60.0;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		int frames = 0;
		double timer  = System.currentTimeMillis();
		requestFocus();//Deixar a tela em foco quando abrir
		while(isRunning) { //Enquanto isRunning for verdadeira irá rodar o tick(Logica do jogo) e o render(Parte grafica)
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1 ) {
				tick();//Atualiza
				render();//renderizar
				frames++;
				delta--;
			}

			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}

		}
		stop();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
			if(gameState == "SELECAO") {
				selecao.right = true;
			}

		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
			if(gameState == "SELECAO") {
				selecao.left = true;
			}

		}
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
			if(gameState == "MENU") {
				menu.up = true;
			}


		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			if(gameState == "MENU") {
				menu.down = true;
			}

		}
		if(e.getKeyCode() == KeyEvent.VK_1) {
			if(gameState == "NORMAL") {
				ui.selecionaFC= true;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_2) {
			if(gameState == "NORMAL") {
				ui.selecionaFT= true;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_3) {
			if(gameState == "NORMAL") {
				ui.selecionaFL = true;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_4) {
			if(gameState == "NORMAL") {
				ui.selecionaFQ = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_5) {
			if(gameState == "NORMAL") {
				ui.selecionaFR = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_6) {
			if(gameState == "NORMAL") {
				ui.selecionaFTrapezio = true;
			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_E) {
			player.FGPrecionada = true;
		}else if(e.getKeyCode() == KeyEvent.VK_R) {
			player.FGSolta = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.restartGame = true;
			if (gameState == "MENU") {
				menu.enter = true;
			}
			if (gameState == "TELA_INICIO") {
				inicio.enterInicio = true;
			}if (gameState == "FINAL") {
				final1.enterFinal = true;
			}if (gameState == "SELECAO") {
				selecao.enter = true;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "NORMAL") {
				gameState = "MENU";
				menu.pause = true;
			}
			if (gameState == "SELECAO" || gameState == "AJUDA" || gameState == "CREDITO") {
				gameState = "MENU";
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(gameState == "NORMAL") {
				player.tiro = true;
			}


		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;

		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;

		}
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;


		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}		else if(e.getKeyCode() == KeyEvent.VK_E) {
			player.FGPrecionada = false;
		}else if(e.getKeyCode() == KeyEvent.VK_R) {
			player.FGSolta = false;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static int getCUR_LEVEL() {
		return CUR_LEVEL;
	}

}
