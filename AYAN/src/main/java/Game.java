import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener {
	public boolean isRunning = false;

	public static final int WIDTH = 1020, HEIGHT = 580;
	public static final String TITLE = "ayan-man";
	private Thread thread; //SUBPROCESS to do multiple things
	public static Ayanman player;

	public Game() {
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		addKeyListener(this);
		player =new Ayanman(Game.WIDTH/2,Game.HEIGHT/2);
	}

	public synchronized void start() { //synchronized sert a ne pas perdre le thread en executant
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		player.update();
	}

	private void render() {
		BufferStrategy bs= getBufferStrategy(); //temporary place of storage "next step"
		if(bs==null) {
			createBufferStrategy(4);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		player.render(g);
		g.dispose();
		bs.show();
	}

	public void run() {
		requestFocus();
		int fps=0;
		double timer=System.currentTimeMillis();
		long lastTime=System.nanoTime();
		double targetTick=60.0;
		double delta=0;
		double ns=1000000000/targetTick;
		while (isRunning) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			
			while(delta>=1) {
				update();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis()-timer>=1000) {
				System.out.println(fps);
				fps=0;
				timer+=1000;
				
			}
		}
		stop();
	}

	
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle(Game.TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		game.start();
	}

	
	
	
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right=true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left=true;
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right=false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left=false;
		
	}
}
