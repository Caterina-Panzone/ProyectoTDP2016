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
	protected int lock; 
	
	//Constructor
	
	public Enemigo(int vm, int vd, int resis, int direc, Celda celda, int puntos, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
		super(direc, celda);
		this.enemigos = enemigos; 
		resistencia = resis; 
		velocidadDisparo = vd; 
		velocidadMovimiento = vm; 
		this.puntos= puntos;
		this.inteligencia = inteligencia; 
		lock = 0;
	}
	
	//Comandos
	
	public void actuar(PowerUp poder){
		
	}
	
	public void moverse(){
		boolean nuevaDireccion=true; 
		
		//Si el tanque dej� de moverse.
		
		if(lock<=0){
			nuevaDireccion = inteligencia.moverse(this); 
			lock = tama�o/(velocidadMovimiento*2);  	
		}
		
		//Si la direccion que tiene el tanque es v�lida se mueve. 
		
		if(nuevaDireccion){
			//Posiciona el tanque en la fila y columna final del movimiento. 
			if(lock==1){
				x = celda.getColumna()*tama�o;
				y = celda.getFila()*tama�o; 
				cambiarImagenActual(direccion);  
			}
			else {
				//Genera el movimiento continuo del tanque con posiciones, dependiendo de la velocidad de cada tanque. 
				moverseGraficamente();
			}
			lock--;
		}else{
			//Se utiliza para volver a buscar una direccion en la inteligencia del tanque. 
			lock=0; 
		}
	}
	
	public boolean recibirGolpe(Jugador tanque){
		golpesRecibidos++; 
		
		if(resistencia<=golpesRecibidos){
			enemigos.remove(this);
			celda.setTanque(null);
			celda = null; 
			ponerImagenVacia();
		}
		return true; 
	}
	
	public boolean recibirGolpe(Enemigo tanque){
		return false; 
	}
	
	public boolean dispareTanque(Tanque tanque){
		return tanque.recibirGolpe(this);
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
