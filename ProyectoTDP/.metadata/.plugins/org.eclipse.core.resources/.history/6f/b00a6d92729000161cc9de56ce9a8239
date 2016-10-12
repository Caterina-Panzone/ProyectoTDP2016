package Juego;

import java.util.List;

import Tanques.*;

public class ControladorDisparos extends Thread{
	List<Disparo> disparos;
	private volatile boolean ejecutar; 
	
	public ControladorDisparos(List<Disparo> disparos){
		this.disparos = disparos; 
	}
	
	public void terminate(){
		ejecutar = false; 
	}
	
	public void run() {
		ejecutar = true; 
		while(ejecutar){
			try {
				for(int i=0; i<disparos.size(); i++){
					//disparos.get(i).avanzar); 
				}
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
