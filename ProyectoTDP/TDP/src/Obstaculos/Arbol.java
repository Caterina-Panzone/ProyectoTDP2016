package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Tematica;
import Tanques.Tanque;

public class Arbol extends Obstaculo{
	
	//Constructor
	
	public Arbol(Celda celda){
		super(true, true, celda); 
	}
	
	//Comandos
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/B.png"));
	}
	
	public void recibirGolpe(Tanque tanque){	}

}
