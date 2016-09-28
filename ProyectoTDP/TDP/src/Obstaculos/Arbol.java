package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;

public class Arbol extends Obstaculo{
	
	//Constructor
	
	public Arbol(Celda celda){
		super(true, true, celda); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/B.png"));
		cambiarImagenActual(0); 
	}
	
	//Comandos
	
	public boolean recibirGolpe(){
		return false; 
	}

}