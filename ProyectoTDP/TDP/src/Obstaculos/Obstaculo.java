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
		celda.setObstaculo(this);
		atraviesanDisparos = ad;
		atraviesanTanques = at; 
		cambiarImagenActual(0); 
	}
	
	//Comandos
	
	public abstract void recibirGolpe(Tanque tanque); 
	
	public void setCelda(Celda nueva){
		if(celda!=null){
			celda.setObstaculo(null);
		}
		celda = nueva; 
		if (celda!=null){
			celda.setObstaculo(this);  
		}
	}
	
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