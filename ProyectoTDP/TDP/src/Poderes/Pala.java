package Poderes;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class Pala extends PowerUp{
	protected Mapa mapa; 
	
	//Constructor
	
	public Pala(Celda celda, Mapa mapa){
		super(celda); 
		this.mapa = mapa; 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Pala.gif"));
		cambiarImagenActual(0);
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		mapa.protegerAguila(); 
		//Tiene que tener un contador
	}
	
}
