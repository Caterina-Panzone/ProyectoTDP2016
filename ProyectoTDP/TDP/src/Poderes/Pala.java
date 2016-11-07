package Poderes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Juego.*;
import Tanques.*; 

public class Pala extends PowerUp{
	protected Logica logica; 
	protected Timer tiempo; 
	
	//Constructor
	
	public Pala(Celda celda, Logica logica){
		super(celda); 
		this.logica = logica; 
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"Pala.gif"));
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		logica.getMapa().protegerAguila(logica); 
		TimerClass timerC = new TimerClass();
        tiempo = new Timer(10000,timerC);
		tiempo.start(); 
	}
	
	private class TimerClass implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			tiempo.stop();
			logica.getMapa().desprotegerAguila(logica); 
			tiempo = null; 
		}
	}
	
}
