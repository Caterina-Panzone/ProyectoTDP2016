package Poderes;

import java.util.LinkedList;
import java.util.List;

import Juego.Celda;
import Juego.Logica;
import Juego.Mapa;
import Obstaculos.Obstaculo;
import TDisparo.Disparo;
import Tanques.*; 

public abstract class CambioTematica extends PowerUp{
	protected Logica logica; 
	
	public CambioTematica(Celda celda, Logica logica){
		super(celda); 
		this.logica = logica; 
	}
	
	public void actuar(Jugador tanque) {
		super.actuar(tanque);
		
		tanque.setearImagenes(); 
		tanque.cambiarImagenActual(tanque.getDireccion());
		
		List<Enemigo> enemigos = logica.getListaEnemigos(); 
		Enemigo enemigo; 
		for(int i=0;i<enemigos.size(); i++){
			enemigo = enemigos.get(i); 
			enemigo.setearImagenes(); 
			enemigo.cambiarImagenActual(enemigo.getDireccion()); 
		}
		
		List<Disparo> disparos = logica.getListaDisparos(); 
		Disparo disparo;  
		for(int i=0;i<disparos.size(); i++){
			disparo = disparos.get(i); 
			disparo.setearImagenes(); 
			disparo.cambiarImagenActual(disparo.getDireccion()); 
		}
		
		logica.cambiarFondoGUI();
		
		List<PowerUp> poderes = new LinkedList<PowerUp>(); 
		Mapa mapa = logica.getMapa(); 
		Celda celdaActual; 
		Obstaculo obst; 
		PowerUp poder; 
		
		for(int f=0;f<mapa.cantidadFilas(); f++){
			for(int c=0; c<mapa.cantidadColumnas(); c++){
				celdaActual = mapa.getCelda(f, c);
				obst = celdaActual.getObstaculo(); 
				if(obst!=null){
					obst.setearImagenes(); 
					obst.cambiarImagenActual(obst.indiceActual());
				}
				poder = celdaActual.getPower(); 
				if(poder!=null){
					poderes.add(poder); 
				}
			}
		}
		
		for(int i=0;i<poderes.size(); i++){
			//Si el jugador no agarro el poder antes. 
			poder = poderes.get(i); 
			if(poder!=null){
				poder.setearImagenes(); 
				poder.cambiarImagenActual(0); 
			}
		}
	}
}
