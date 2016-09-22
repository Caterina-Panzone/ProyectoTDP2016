package Tanques;

import Juego.*;
import Poderes.PowerUp;

public class Enemigo extends Tanque{

	//Atributos
	
	protected int puntos;
	
	//Constructor
	
	public Enemigo(int vm, int vd, int resis, char direc, Celda celda, int puntos){
		super(vm, vd, resis, direc, celda);
		this.puntos= puntos;
	}
	
	//Comandos
	
	public boolean actuar(PowerUp poder){
		return false;
	}
	
	//Consultas
	
	public int getPuntos(){
		return puntos;
	}
	
}
