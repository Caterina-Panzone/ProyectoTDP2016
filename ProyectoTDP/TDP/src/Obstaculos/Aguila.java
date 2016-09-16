package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;

public class Aguila extends Obstaculo{
	//Constructor
	
	public Aguila(Celda celda){
		super(false, false, celda); 
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/F.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/AguilaMuerta.png"));
		cambiarImagenActual(0); 
	}
	
	//Comando
	
	public boolean recibirGolpe(){
		cambiarImagenActual(1);
		return true; 
	}

}
