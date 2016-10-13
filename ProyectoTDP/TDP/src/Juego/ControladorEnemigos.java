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
					enemigos.get(i).moverse(); 
					disparo=enemigos.get(i).disparar();
					if(disparo!=null){
						logica.a�adirDisparo(disparo);
					}
				}
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}