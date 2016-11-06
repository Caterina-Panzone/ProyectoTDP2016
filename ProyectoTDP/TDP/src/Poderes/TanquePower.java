package Poderes;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class TanquePower extends PowerUp{
	protected Logica logica; 
	
	//Constructor
	
	public TanquePower(Celda celda, Logica logica){
		super(celda); 
		this.logica = logica; 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Vida.gif"));
		cambiarImagenActual(0);
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		logica.aumentarVidaJugador(); 
	}
	
}