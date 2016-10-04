package Tanques;

import Juego.*;
import Poderes.PowerUp;

public class Enemigo extends Tanque{

	//Atributos
	
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int resistencia;
	protected int puntos;
	
	//Constructor
	
	public Enemigo(int vm, int vd, int resis, char direc, Celda celda, int puntos){
		super(direc, celda);
		resistencia = resis; 
		velocidadDisparo = vd; 
		velocidadMovimiento = vm; 
		this.puntos= puntos;
	}
	
	//Comandos
	
	public void actuar(PowerUp poder){
		
	}
	
	//Consultas
	
	public int getPuntos(){
		return puntos;
	}
	
	public int getVelocidadDisparo(){
		return velocidadDisparo; 
	}
	
	public int getVelocidadMovimiento(){
		return velocidadMovimiento; 
	}
	
	public int getResistencia(){
		return resistencia; 
	}
}
