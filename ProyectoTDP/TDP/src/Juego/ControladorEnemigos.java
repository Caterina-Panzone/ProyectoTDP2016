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
		Disparo disparo;
		while(ejecutar){
			try {
				for(int i=0; i<enemigos.size(); i++){
					disparo=enemigos.get(i).disparar();
					enemigos.get(i).moverse(); 
					if(disparo!=null){
						logica.aņadirDisparo(disparo);
					}
				}
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
