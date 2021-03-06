package Tanques;

import Juego.*;

import java.util.List;
import java.util.Random;

import Poderes.PowerUp;
import TDisparo.Disparo;

public abstract class Enemigo extends Tanque{
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
		Random rnd = new Random();
		direccion = rnd.nextInt(4);
		cambiarImagenActual(direccion);
	}
	
	//Comandos
	
	public void actuar(PowerUp poder){}
	
	public void moverse(){	
		//Si el tanque dej� de moverse.
		
		if(lock<=0){
			nuevaCelda = inteligencia.moverse(this); 
			lock = tama�o/(velocidadMovimiento*aumento);  	
		}
		
		//Si la direccion que tiene el tanque es v�lida se mueve. 
		
		if(nuevaCelda != null){
			//Posiciona el tanque en la fila y columna final del movimiento. 
			if(lock==1){
				x = celda.getColumna()*tama�o;
				y = celda.getFila()*tama�o; 
				cambiarImagenActual(direccion);  
				viejaCelda.desbloquear(); 
				viejaCelda = celda;
				nuevaCelda = null; 
			}
			else {
				if(lock == (tama�o/(velocidadMovimiento*aumento))/2){ 
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
			morirme();
		}
		return true; 
	}
	
	public void morirme(){
		destroy(); 
		inteligencia.aumentarDestruidosJugador(puntos);
	}
	
	public void destroy(){
		enemigos.remove(this);
		setCelda(null); 
		viejaCelda.desbloquear(); 
		if(nuevaCelda!=null){
			nuevaCelda.desbloquear(); 
			nuevaCelda = null; 
		}
		ponerImagenVacia();
	}
	
	//Metodo auxiliar.
	
	protected abstract Disparo dispararAux(Logica logica); 
	
	public Disparo disparar (Logica logica){
		Disparo disparo = null; 
		if(espera<=0 && disparosRealizados<cantMaximaDisparos()){
			disparosRealizados++;
			disparo = dispararAux(logica); 
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
