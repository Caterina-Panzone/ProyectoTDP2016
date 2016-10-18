package Tanques;

import java.util.Random;

import Juego.*;

public class InteligenciaEnemigo {
	//Atributos
	protected Mapa mapa; 
	
	//Constructor
	public InteligenciaEnemigo(Mapa mapa){
		this.mapa = mapa; 
	}
	
	//Comandos
	
	public void disparar(Enemigo enemigo){
		
	}
	
	public boolean moverse(Enemigo enemigo){
		int i = enemigo.getFila(); 
		int j = enemigo.getColumna(); 
		int direccion=enemigo.getDireccion();
		boolean seMovio=true; 

		if(!puedoMover(direccion,i,j)){ 
			boolean cumple=false; int corte=0;
			while(!cumple && corte!=2){
				nuevaDireccion(enemigo);
				direccion=enemigo.getDireccion();
				cumple = puedoMover(direccion,i,j); 
				corte++; 
			}
			seMovio=false;
		}
		else{
			switch(enemigo.getDireccion()){
				case 3:{
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i, j-1));
				    break; 
				    }
				case 2: {
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i, j+1));
					break; 
				    }
				case 1: {
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i+1, j));
					break; 
					}
				case 0: {
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i-1, j));
					break; 
					}
				}
		}
		return seMovio;
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
}