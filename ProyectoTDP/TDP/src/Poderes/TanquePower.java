package Poderes;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class TanquePower extends PowerUp{
	//Constructor
	
	public TanquePower(Celda celda){
		super(celda); 
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Vida.gif"));
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		tanque.aumentarVida();  
	}
	
}