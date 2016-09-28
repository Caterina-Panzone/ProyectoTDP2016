package Niveles;

public class Nivel3 extends Nivel{
	
	//Constructor 
	
	public Nivel3(){
		velocidadMovimiento = 2; 
		velocidadDisparo = 2;
		resistencia = 2;
		disparosSimultaneos = 2; 
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
		return new Nivel4();
	}
}