package Tanques;

import Juego.*;

import java.util.List;
import Poderes.PowerUp;

public class Enemigo extends Tanque{

	//Atributos
	
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int resistencia;
	protected int puntos;
	protected InteligenciaEnemigo inteligencia; 
	protected List<Enemigo> enemigos; 
	
	//Constructor
	
	public Enemigo(int vm, int vd, int resis, char direc, Celda celda, int puntos, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
		super(direc, celda);
		this.enemigos = enemigos; 
		resistencia = resis; 
		velocidadDisparo = vd; 
		velocidadMovimiento = vm; 
		this.puntos= puntos;
		this.inteligencia = inteligencia; 
		cantMaxima=1;
	}
	
	//Comandos
	
	public void actuar(PowerUp poder){
		
	}
	
	public void moverse(){
		inteligencia.moverse(this); 
	}
	
	public void recibirGolpe(Jugador tanque){
		golpesRecibidos++;
		if(resistencia<=golpesRecibidos){
			enemigos.remove(this);
			celda.setTanque(null);
			celda = null; 
			ponerImagenVacia();
		}
	}
	
	public void recibirGolpe(Enemigo tanque){
		
	}
	
	public void dispareTanque(Tanque tanque){
		tanque.recibirGolpe(this);
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
