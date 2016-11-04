package Poderes;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class Granada extends PowerUp{
	
	protected List<Enemigo> enemigos;
	protected Generador generador;
	
	//Constructor
	
	public Granada(Celda celda, List<Enemigo> enemigos, Generador generador){
		super(celda); 
		this.enemigos= enemigos;
		this.generador=generador;
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Granada.png"));
		cambiarImagenActual(0);
	}
	
	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		generador.terminate();
		while(!enemigos.isEmpty()){
			enemigos.get(0).morirme();
		}
		generador.run();
	}
}
