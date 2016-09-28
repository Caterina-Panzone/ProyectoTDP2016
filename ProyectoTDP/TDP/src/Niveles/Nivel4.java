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
	
	public int getVelocidadMovimiento(){
		return velocidadMovimiento;
	}
	
	public int getVelocidadDisparo(){
		return velocidadDisparo;
	}
	
	public int getResistencia(){
		return resistencia; 
	}
	
	public int getDisparosSimultaneos(){
		return disparosSimultaneos; 
	}
	
	public Nivel siguienteNivel(){
		return this;
	}
}