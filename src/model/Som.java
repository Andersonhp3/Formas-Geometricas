package model;

import java.applet.Applet;
import java.applet.AudioClip;

public class Som {

	private AudioClip clip;
	
	public static final Som musicafundo = new Som("/Babylon2.wav");
	public static final Som dano = new Som("/dano.wav");
	public static final Som acerto = new Som("/acerto.wav");
	public static final Som vida = new Som("/vida.wav");
	
	private Som(String name) {
		try {
			clip = Applet.newAudioClip(Som.class.getResource(name));
		}catch (Throwable e) {
			// TODO: handle exception
		}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
				clip.play();	
				}
			}.start();
		} catch (Throwable e) {
			// TODO: handle exception
		}
	}
	
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
				clip.loop();	
				}
			}.start();
		} catch (Throwable e) {
			// TODO: handle exception
		}
	}
}
