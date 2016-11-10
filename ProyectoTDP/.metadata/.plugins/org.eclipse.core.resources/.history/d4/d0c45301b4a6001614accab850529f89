package Tanques;

import java.util.Random;

import Juego.*;

public class InteligenciaEnemigo {
	//Atributos
	protected Mapa mapa; 
	protected Logica logica;
	
	//Constructor
	public InteligenciaEnemigo(Logica logica){
		this.logica= logica;
		mapa = logica.getMapa(); 
	}
	
	//Comandos
	
	public Celda moverse(Enemigo enemigo){
		
		int i = enemigo.getFila(); 
		int j = enemigo.getColumna(); 
		int direccion=enemigo.getDireccion();
		Celda proximaCelda=null; 

		if(!puedoMover(direccion,i,j)){ 
			boolean cumple=false; int corte=0;
			while(!cumple && corte!=2){
				nuevaDireccion(enemigo);
				direccion=enemigo.getDireccion();
				cumple = puedoMover(direccion,i,j); 
				corte++; 
			}
		}
		else{
			switch(enemigo.getDireccion()){
				case 3:{
					proximaCelda = mapa.getCelda(i, j-1);
					proximaCelda.bloquear();
				    break; 
				    }
				case 2: {
					proximaCelda = mapa.getCelda(i, j+1);
					proximaCelda.bloquear();
					break; 
				    }
				case 1: {
					proximaCelda = mapa.getCelda(i+1, j);
					proximaCelda.bloquear();
					break; 
					}
				case 0: {
					proximaCelda = mapa.getCelda(i-1, j);
					proximaCelda.bloquear();
					break; 
					}
				}
		}
		return proximaCelda;
	}
	
	public void concretarMovimiento(Enemigo enemigo, Celda proximaCelda){
		int i = enemigo.getFila(); 
		int j = enemigo.getColumna(); 

		mapa.concretarMovimientoTanque(enemigo, mapa.getCelda(i, j), proximaCelda);
	}	
	
	/*
	 * METODOS PRIVADOS
	 */
	
	private boolean puedoMoverIzquierda(int i, int j){
		return ((j-1>=0) && (mapa.getCelda(i, j-1).permitidoAvanzarTanque()));
	}
	
	private boolean puedoMoverDerecha(int i, int j){
		return ((j+1<mapa.cantidadColumnas()) && (mapa.getCelda(i, j+1).permitidoAvanzarTanque()));
	}
	
	private boolean puedoMoverAbajo(int i, int j){
		return ((i+1<mapa.cantidadFilas()) && (mapa.getCelda(i+1, j).permitidoAvanzarTanque()));
	}
	
	private boolean puedoMoverArriba(int i, int j){
	    return ((i-1>=0) && (mapa.getCelda(i-1, j).permitidoAvanzarTanque()));
	}
	
	private boolean puedoMover(int direccion, int i, int j){
		return ( (direccion==3 && puedoMoverIzquierda(i,j)) ||
			     (direccion==2 && puedoMoverDerecha(i,j)    ||
			     (direccion==1 && puedoMoverAbajo(i,j))     ||
			     (direccion==0 && puedoMoverArriba(i,j))    ) );
	}
	
	private void nuevaDireccion(Enemigo enemigo){
		int n; int direccion; 
		Random rnd = new Random();
		do{
			n = rnd.nextInt(4);
			enemigo.cambiarImagenActual(n);
			direccion = n;
		}while(direccion==enemigo.getDireccion()); 
		
		enemigo.setDireccion(direccion); 
	}
	
	public void aumentarDestruidosJugador(int puntos){
		logica.aumentarDestruidosJugador(puntos);
	}
}