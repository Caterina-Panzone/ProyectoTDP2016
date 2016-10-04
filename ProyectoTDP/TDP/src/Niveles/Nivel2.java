package Niveles;

public class Nivel2 extends Nivel{
	
	//Constructor 
	
	public Nivel2(){
		velocidadMovimiento = 3; 
		velocidadDisparo = 2;
		resistencia = 1;
		disparosSimultaneos = 1; 
	}
	
	//Consultas
		
	public Nivel siguienteNivel(){
		return new Nivel3();
	}
}