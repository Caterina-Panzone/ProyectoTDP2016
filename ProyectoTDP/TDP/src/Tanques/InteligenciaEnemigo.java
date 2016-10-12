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
	
	public void moverse(Enemigo enemigo){
		int i = enemigo.getFila(); 
		int j = enemigo.getColumna(); 
		char direccion=enemigo.getDireccion();

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
				case 'I':
					{
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i, j-1));
					enemigo.cambiarImagenActual(3);
				    break; 
				    }
				case 'D': 
					{
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i, j+1));
					enemigo.cambiarImagenActual(2);
					break; 
				    }
				case 'B': 
					{
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i+1, j));
					enemigo.cambiarImagenActual(1);
					break; 
					}
				case 'A': 
					{
					mapa.concretarMovimientoTanque(mapa.getCelda(i, j), mapa.getCelda(i-1, j));
					enemigo.cambiarImagenActual(0);
					break; 
					}
				}
		}
	}
	
	/*
	 * METODOS PRIVADOS
	 */
	
	private char transformarDireccion(int n){
		char retorno='A';
		
		switch (n){
		case 1: {
			retorno = 'B'; 
			break;
			}
		case 2: {
			retorno = 'D'; 
			break;
			}
		case 3: {
			retorno = 'I'; 
			break;
			}
		}
		
		return retorno; 
	}
	
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
	
	private boolean puedoMover(char direccion, int i, int j){
		return ( (direccion=='I' && puedoMoverIzquierda(i,j)) ||
			     (direccion=='D' && puedoMoverDerecha(i,j)    ||
			     (direccion=='B' && puedoMoverAbajo(i,j))     ||
			     (direccion=='A' && puedoMoverArriba(i,j))    ) );
	}
	
	private void nuevaDireccion(Enemigo enemigo){
		int n; char direccion; 
		Random rnd = new Random();
		do{
			n = rnd.nextInt(4);
			enemigo.cambiarImagenActual(n);
			direccion = transformarDireccion(n); 
		}while(direccion==enemigo.getDireccion()); 
		
		enemigo.setDireccion(direccion); 
	}
}