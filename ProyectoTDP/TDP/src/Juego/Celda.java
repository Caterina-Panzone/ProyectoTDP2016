package Juego;

import Tanques.*;
import Obstaculos.*;
import Poderes.*; 

public class Celda {
	//Atributos 
	
	protected int fila;
	protected int columna; 
	protected Obstaculo obstaculo;
	protected Disparo bala; 
	protected PowerUp powerUp; 
	protected Tanque tanque; 
	
	//Constructor
	
	public Celda (int fila, int columna){
		this.fila = fila;
		this.columna = columna;
	}
	
	//Comandos
	
	public void setTanque(Tanque tanque){
		this.tanque=tanque;
	}
	
	public void setBala(Disparo disparo){
		bala = disparo;
	}
	
	public void setObstaculo(Obstaculo obstaculo){
		this.obstaculo = obstaculo; 
	}
	
	public void setPower(PowerUp power){
		powerUp = power; 
	}
	
	//Consultas
	
	public int getColumna(){
		return columna;
	}
	
	public int getFila(){
		return fila; 
	}
	
	public boolean permitidoAvanzarTanque(){
		return (((obstaculo==null) || obstaculo.atraviesanTanques()) && tanque==null);
	}
	
	public Obstaculo getObstaculo(){
		return obstaculo;
	}
	
	public Tanque getTanque(){
		return tanque;
	}
	
	public Disparo getBala(){
		return bala;
	}
	
	public PowerUp getPower(){
		return powerUp; 
	}
}