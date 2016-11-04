package Tanques;

import Juego.*;

import java.util.List;
import Poderes.PowerUp;

public abstract class Enemigo extends Tanque{

	//Atributos
	
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int resistencia;
	protected int puntos;
	protected InteligenciaEnemigo inteligencia; 
	protected List<Enemigo> enemigos; 
	protected Celda nuevaCelda; 
	protected Celda viejaCelda; 
	protected int espera; 
	
	//Constructor
	
	public Enemigo(int vm, int vd, int resis,Celda celda, int puntos, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
		super(celda);
		viejaCelda=celda;
		viejaCelda.bloquear(); 
		nuevaCelda=null; 
		this.enemigos = enemigos; 
		resistencia = resis; 
		velocidadDisparo = vd; 
		velocidadMovimiento = vm; 
		this.puntos= puntos;
		this.inteligencia = inteligencia; 
		espera = 0; 
	}
	
	//Comandos
	
	public void actuar(PowerUp poder){
		
	}
	
	public void moverse(){	
		//Si el tanque dejó de moverse.
		
		if(lock<=0){
			nuevaCelda = inteligencia.moverse(this); 
			lock = tamaño/(velocidadMovimiento*aumento);  	
		}
		
		//Si la direccion que tiene el tanque es válida se mueve. 
		
		if(nuevaCelda != null){
			//Posiciona el tanque en la fila y columna final del movimiento. 
			if(lock==1){
				x = celda.getColumna()*tamaño;
				y = celda.getFila()*tamaño; 
				cambiarImagenActual(direccion);  
				viejaCelda.desbloquear(); 
				viejaCelda = celda;
				nuevaCelda = null; 
			}
			else {
				if(lock == (tamaño/(velocidadMovimiento*aumento))/2){ 
					inteligencia.concretarMovimiento(this, nuevaCelda);
					viejaCelda.bloquear();
				}
				//Genera el movimiento continuo del tanque con posiciones, dependiendo de la velocidad de cada tanque. 
				moverseGraficamente();
			}
			lock--;
		}else{
			//Se utiliza para volver a buscar una direccion en la inteligencia del tanque. 
			nuevaCelda = null; 
			lock=0; 
		}
	}
	
	public boolean recibirGolpe(Jugador tanque){
		golpesRecibidos++; 
		
		if(resistencia<=golpesRecibidos){
			celda.setTanque(null);
			enemigos.remove(this);
			celda.desbloquear(); 
			viejaCelda.desbloquear(); 
			if(nuevaCelda!=null){
				nuevaCelda.desbloquear(); 
				nuevaCelda = null; 
			}
			celda = null; 
			ponerImagenVacia();
		}
		return true; 
	}
	
	public Disparo disparar (Logica logica){
		Disparo disparo = null; 
		if(espera<=0){
			disparo = super.disparar(logica); 
			espera = getEsperaPersonal(); 
		}else{
			espera--; 
		}
		return disparo; 
	}
	
	public boolean recibirGolpe(Enemigo tanque){
		return false; 
	}
	
	public boolean dispareTanque(Tanque tanque){
		return tanque.recibirGolpe(this);
	}
	
	//Consultas
	
	public abstract int getEsperaPersonal();
	
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
