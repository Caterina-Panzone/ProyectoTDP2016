package Poderes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import Juego.*;
import Tanques.*; 

public class TimerPower extends PowerUp{
	protected Logica logica; 
	protected ControladorEnemigos controlador; 
	protected Timer tiempo; 
	
	//Constructor
	
	public TimerPower(Celda celda, ControladorEnemigos controlador){
		super(celda); 
		this.controlador = controlador; 
			
		TimerClass timerC = new TimerClass();
		tiempo = new Timer(5000,timerC); 
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Timer.gif"));
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		controlador.dormir(true);
		tiempo.start(); 
		
	}
	
	private class TimerClass implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tiempo.stop();
			controlador.dormir(false);
		}
	}
}