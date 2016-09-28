package Niveles;

public abstract class Nivel {
	
	//Atributos 
	
	protected int disparosSimultaneos;
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int resistencia;
	
	//Consultas
	
	public abstract int getDisparosSimultaneos();
	
	public abstract int getVelocidadMovimiento();
	
	public abstract int getVelocidadDisparo(); 
	
	public abstract int getResistencia(); 
	
	public abstract Nivel siguienteNivel(); 
}