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
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Granada.gif"));
		cambiarImagenActual(0);
	}
	
	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		while(!enemigos.isEmpty()){
			enemigos.get(0).morirme();
		}
	}
}
