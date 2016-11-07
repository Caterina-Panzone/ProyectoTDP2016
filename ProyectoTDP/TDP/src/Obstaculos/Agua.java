package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Tematica;
import Tanques.Tanque;

public class Agua extends Obstaculo{
	
	//Constructor
	
	public Agua(Celda celda){
		super(true, false, celda); 
	}
	
	//Comandos
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/H.png"));
	}
	
	public void recibirGolpe(Tanque tanque){	}

}
