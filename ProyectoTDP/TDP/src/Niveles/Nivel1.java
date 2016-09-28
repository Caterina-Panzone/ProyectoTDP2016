package Niveles;

public class Nivel1 extends Nivel{
	
	//Constructor 
	
	public Nivel1(){
		velocidadMovimiento = 2; 
		velocidadDisparo = 1;
		resistencia = 1;
		disparosSimultaneos = 1; 
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
		return new Nivel2();
	}
}