package Juego;

import Tanques.*; 
import java.util.List;

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
					Enemigo enemy; 
					for(int i=0; i<enemigos.size(); i++){
						enemy = enemigos.get(i); 
						enemy.disparar(logica); 
						enemy.moverse();
					}
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
