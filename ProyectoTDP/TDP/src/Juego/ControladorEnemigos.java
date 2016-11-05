package Juego;

import Tanques.*; 
import java.util.List;


public class ControladorEnemigos extends Thread{
	protected Logica logica;
	protected List<Enemigo> enemigos; 
	private volatile boolean ejecutar; 
	
	public ControladorEnemigos(Logica logica){
		this.logica=logica;
		enemigos=logica.getListaEnemigos();
	}
	
	public void terminate(){
		ejecutar = false; 
	}
	
	public void run() {
		ejecutar = true; 
		while(ejecutar){
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
