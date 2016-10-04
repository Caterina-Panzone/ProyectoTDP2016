package Niveles;

public class Nivel4 extends Nivel{
	
	//Constructor 
	
	public Nivel4(){
		velocidadMovimiento = 2; 
		velocidadDisparo = 3;
		resistencia = 4;
		disparosSimultaneos = 3; 
	}
	
	//Consultas
	
	public Nivel siguienteNivel(){
		return this;
	}
}
