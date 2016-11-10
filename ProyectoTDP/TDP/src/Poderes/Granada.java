package Poderes;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class Granada extends PowerUp{
	
	protected List<Enemigo> enemigos;
	
	//Constructor
	
	public Granada(Celda celda, List<Enemigo> enemigos){
		super(celda); 
		this.enemigos= enemigos;
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Granada.gif"));
	}
	
	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		int i = 0; 
		while(!enemigos.isEmpty() && i<4){
			enemigos.get(0).morirme();
			i++; 
		}
	}
}
