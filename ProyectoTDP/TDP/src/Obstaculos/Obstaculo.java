package Obstaculos;

import Juego.*;
import Tanques.Tanque;

public abstract class Obstaculo extends ObjetoConImagen {
	//Atributos
	
	protected boolean atraviesanDisparos;
	protected boolean atraviesanTanques;
	
	//Constructor
	
	public Obstaculo(boolean ad, boolean at,Celda celda){
		super(celda); 
		atraviesanDisparos = ad;
		atraviesanTanques = at; 
		cambiarImagenActual(0); 
	}
	
	//Comandos
	
	public abstract void recibirGolpe(Tanque tanque); 
	
	//Consultas
	
	public int indiceActual(){
		return 0; 
	}
	
	public boolean atraviesanDisparos(){
		return atraviesanDisparos;
	}
	
	public boolean atraviesanTanques(){
		return atraviesanTanques;
	}
}