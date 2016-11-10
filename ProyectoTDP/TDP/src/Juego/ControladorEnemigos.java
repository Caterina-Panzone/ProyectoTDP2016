package Juego;

import Tanques.*; 
import java.util.List;

import TDisparo.Disparo;


public class ControladorEnemigos extends Thread{
	protected Logica logica;
	protected List<Enemigo> enemigos; 
	private volatile boolean ejecutar; 
	private volatile boolean dormir; 
	
	public ControladorEnemigos(Logica logica){
		this.logica=logica;
		enemigos=logica.getListaEnemigos();
	}
	
	public void terminate(){
		ejecutar = false; 
	}
	
	public void dormir (boolean dormir){
		this.dormir= dormir; 
	}
	
	public void run() {
		ejecutar = true; 
		while(ejecutar){
			if(!dormir){
				try {
					Disparo disparo; 
					for(int i=0; i<enemigos.size(); i++){
						disparo = enemigos.get(i).disparar(logica); 
						if(disparo!=null){
							logica.aņadirDisparo(disparo);
						}
						enemigos.get(i).moverse();
					}
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
