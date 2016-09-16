package Obstaculos;

import Juego.*;

public abstract class Obstaculo extends ObjetoConImagen {
	//Atributos
	
	protected boolean atraviesanDisparos;
	protected boolean atraviesanTanques;
	
	//Constructor
	
	public Obstaculo(boolean ad, boolean at,Celda celda){
		super(32,celda); 
		atraviesanDisparos = ad;
		atraviesanTanques = at;
	}
	
	//Comandos
	/**
	 * 
	 * @return Si le pego al aguila retorna verdadero, sino retorna falso. 
	 */
	
	public abstract boolean recibirGolpe(); 
	
	//Consultas
	
	public boolean atraviesanDisparos(){
		return atraviesanDisparos;
	}
	
	public boolean atraviesanTanques(){
		return atraviesanTanques;
	}
}