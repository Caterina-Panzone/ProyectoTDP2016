package Poderes;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class Estrella extends PowerUp{
	//Constructor
	
	public Estrella(Celda celda){
		super(celda); 
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Estrella.gif"));
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		tanque.aumentarNivel();
	}
	
}
