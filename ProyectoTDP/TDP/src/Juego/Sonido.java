package Juego;

import java.applet.AudioClip;

public class Sonido {

	protected AudioClip sonido;
	
	public Sonido(){
		sonido= java.applet.Applet.newAudioClip(this.getClass().getResource("/ASonidos/"+Tematica.getTematica()+".wav"));
		sonido.loop();
	}
	
	public void apagar(){
		sonido.stop();
	}
}