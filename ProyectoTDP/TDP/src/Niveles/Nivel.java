package Niveles;

public abstract class Nivel {
	
	//Atributos 
	
	protected int disparosSimultaneos;
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int resistencia;
	
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
	
	public abstract Nivel siguienteNivel(); 
}
