package Juego;

import java.util.List;

import Tanques.*;
import Juego.Mapa;

public class ControladorDisparos extends Thread{

	protected List<Disparo> disparos;
	private volatile boolean ejecutar; 
	protected Mapa mapa;
	protected Logica logica; 
	
	public ControladorDisparos(Logica logica){
		this.logica = logica; 
		disparos = logica.getListaDisparos(); 
		mapa=logica.getMapa();
	}
	
	public void terminate(){
		ejecutar = false; 
	}
	
	public void run() {
		ejecutar = true; 
		while(ejecutar){
			try {
				for(int i=0; i<disparos.size(); i++){
					disparos.get(i).avanzar(disparos);  
				}
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
