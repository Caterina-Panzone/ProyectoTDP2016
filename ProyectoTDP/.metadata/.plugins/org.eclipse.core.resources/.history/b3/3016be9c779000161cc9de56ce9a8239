package Juego;

import Tanques.*; 
import java.util.List;


public class ControladorEnemigos extends Thread{
	protected List<Enemigo> enemigos; 
	private volatile boolean ejecutar; 
	
	public ControladorEnemigos(List<Enemigo> enemigos){
		this.enemigos = enemigos; 
	}
	
	public void terminate(){
		ejecutar = false; 
	}
	
	public void run() {
		ejecutar = true; 
		while(ejecutar){
			try {
				for(int i=0; i<enemigos.size(); i++){
					enemigos.get(i).moverse(); 
					enemigos.get(i).disparar(); 
				}
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
