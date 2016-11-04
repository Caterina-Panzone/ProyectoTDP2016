package Obstaculos;

import Juego.*;

public abstract class Obstaculo extends ObjetoConImagen {
	//Atributos
	
	protected boolean atraviesanDisparos;
	protected boolean atraviesanTanques;
	
	//Constructor
	
	public Obstaculo(boolean ad, boolean at,Celda celda){
		super(celda); 
		atraviesanDisparos = ad;
		atraviesanTanques = at;
	}
	
	//Comandos
	
	public abstract void recibirGolpe(); 
	
	//Consultas
	
	public boolean atraviesanDisparos(){
		return atraviesanDisparos;
	}
	
	public boolean atraviesanTanques(){
		return atraviesanTanques;
	}
}