package Juego;

import Tanques.*;
import java.awt.event.KeyEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Obstaculos.Obstaculo;

public class Logica {
	
	//Atributos 
	
	protected GUI gui;
	protected List<Enemigo> enemigos;
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
		enemigos= new LinkedList<Enemigo>();
//		generarEnemigos();
	}
	
	//Comandos 
	
	private void generarNuevoMapa(){
		mapa = new Mapa("nivel"+nivelMapa+".txt",gui); 		
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
	
	public void generarEnemigo(){
		
		if(enemigos.size()==1){
			eliminarEnemigo();
		}
		else{
			Celda celda= mapa.getCelda(0, mapa.cantidadColumnas()-1);
			if(celda.getTanque()==null){
				Enemigo enemigo= new Basico(celda);
				celda.setTanque(enemigo);
				enemigos.add(enemigo);
				gui.add(enemigo.getImagenActual());
			}
		}
	}
	
	private void eliminarEnemigo(){
		Enemigo eliminado=enemigos.get(0);
		int fila= eliminado.getFila();
		int columna= eliminado.getColumna();
		
		jugador.aumentarPuntos(eliminado.getPuntos());
		System.out.println(jugador.getPuntos());
		
		mapa.getCelda(fila, columna).setTanque(null);
		eliminado.setCelda(null);
		enemigos.remove(eliminado);
		gui.remove(eliminado.getImagenActual());
	}
	
	public void romperPared(){
		
		Random random= new Random();
		boolean rompi=false;
		do{
			Celda celda= mapa.getCelda(random.nextInt(mapa.cantidadFilas()),random.nextInt(mapa.cantidadColumnas()));
			Obstaculo pared= celda.getObstaculo();
			if(pared!=null){
	           for(int i=0; i<4; i++){
	        	  pared.recibirGolpe();
	           }
	           rompi=true;
			}
		}while(!rompi);
	}
}