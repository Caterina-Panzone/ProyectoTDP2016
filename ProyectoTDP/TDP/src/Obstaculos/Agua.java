package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;

public class Agua extends Obstaculo{
	
	//Constructor
	
	public Agua(Celda celda){
		super(true, false, celda); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/H.png"));
		cambiarImagenActual(0); 
	}
	
	//Comandos
	
	public boolean recibirGolpe(){
		return false; 
	}

}
