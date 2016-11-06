package Poderes;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class Estrella extends PowerUp{
	protected Logica logica; 
	
	//Constructor
	
	public Estrella(Celda celda, Logica logica){
		super(celda); 
		this.logica = logica; 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Estrella.gif"));
		cambiarImagenActual(0);
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		logica.aumentarNivelJugador(); 
	}
	
}
