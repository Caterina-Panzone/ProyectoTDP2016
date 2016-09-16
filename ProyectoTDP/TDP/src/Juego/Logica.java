package Juego;

import Tanques.*;
import java.awt.event.KeyEvent;


public class Logica {
	//Atributos 
	protected GUI gui;
//	protected Enemigo[] enemigos;
	protected Jugador jugador;
	protected int nivelMapa;
	protected Mapa mapa;
	
	//Constructor
	
	public Logica(GUI gui){
		this.gui=gui; 
		nivelMapa=1; 
		generarNuevoMapa(); 
		Celda celda = mapa.getCelda(mapa.cantidadFilas()-1, mapa.cantidadColumnas()/2-2);
		jugador = new Jugador(celda);
		celda.setTanque(jugador);
		gui.add(jugador.getImagenActual()); 
//		generarEnemigos();
	}
	
	//Comandos 
	
	private void generarNuevoMapa(){
		mapa = new Mapa(this.getClass().getResource("/Archivos/nivel"+nivelMapa+".txt").getPath(),gui); 		
	}
	
	public void moverJugador(int dir){
		int i = jugador.getFila(), j = jugador.getColumna();
		
		switch (dir){
			case KeyEvent.VK_UP : //Arriba
				if ((i-1>=0 && mapa.getCelda(i-1,j).permitidoAvanzarTanque())){
					mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i-1,j)); 
					jugador.cambiarImagenActual(0);
					jugador.setDireccion('A');
				}
				break;
			case KeyEvent.VK_DOWN : //Abajo
				if ((i+1<mapa.cantidadFilas()) && mapa.getCelda(i+1,j).permitidoAvanzarTanque()){
					mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i+1,j)); 
					jugador.cambiarImagenActual(1);
					jugador.setDireccion('B');
				}
				break;
			case KeyEvent.VK_LEFT: //Izquierda
				if ((j-1>=0) && mapa.getCelda(i,j-1).permitidoAvanzarTanque()){
					mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i,j-1)); 
					jugador.cambiarImagenActual(3);
					jugador.setDireccion('I');
				}
				break;
			case KeyEvent.VK_RIGHT : //Derecha
				if ((j+1<mapa.cantidadColumnas()) && mapa.getCelda(i,j+1).permitidoAvanzarTanque()){
					mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i,j+1)); 
					jugador.cambiarImagenActual(2);
					jugador.setDireccion('D');
				}
				break;
		}
	}

}