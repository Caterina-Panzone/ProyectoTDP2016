package Niveles;

public class Nivel1 extends Nivel{
	
	//Constructor 
	
	public Nivel1(){
		velocidadMovimiento = 2; 
		velocidadDisparo = 2;
		resistencia = 1;
		disparosSimultaneos = 1; 
	}
	
	//Consultas
		
	public Nivel siguienteNivel(){
		return new Nivel2();
	}
}
