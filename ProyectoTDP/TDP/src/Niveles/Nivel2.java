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
		return new Nivel3();
	}
}