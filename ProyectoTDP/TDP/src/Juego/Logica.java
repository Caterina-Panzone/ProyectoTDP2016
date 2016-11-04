package Juego;

import Tanques.*;

import java.awt.event.KeyEvent;

import java.util.LinkedList;
import java.util.List;

public class Logica {

	//Atributos 

	protected GUI gui;
	protected List<Enemigo> enemigos;
	protected List<Disparo> disparos;
	protected Jugador jugador;
	protected int nivelMapa;
	protected Mapa mapa;
	protected ControladorEnemigos controladorEnemigos; 
	protected ControladorDisparos controladorDisparos; 
	protected Generador generador;

	//Constructor

	public Logica(GUI gui){
		this.gui=gui; 
		nivelMapa=1; 
		generarNuevoMapa(); 

		Celda celda = mapa.getCelda(mapa.cantidadFilas()-1, mapa.cantidadColumnas()/2-2);
		jugador = new Jugador(celda, this);
		celda.setTanque(jugador);
		gui.add(jugador.getImagenActual()); 
		
		enemigos= new LinkedList<Enemigo>();
		controladorEnemigos = new ControladorEnemigos(this);
		disparos = new LinkedList<Disparo>(); 
		controladorDisparos = new ControladorDisparos(this); 
		generador= new GeneradorNivel1(this);
		generador.start();
		
		controladorEnemigos.start();
		controladorDisparos.start();
	}

	//Comandos 

	public void añadirDisparo(Disparo disparo){
		disparos.add(disparo);
		gui.add(disparo.getImagenActual());
	}

	private void generarNuevoMapa(){
		//mapa = new Mapa(this.getClass().getResource("/Archivos/nivel1.txt").getPath(),gui,this);
		mapa = new Mapa("nivel"+nivelMapa+".txt",gui,this); 		
	}


	public void cambiarNivelJugador(){
		jugador.aumentarNivel(); 

		// Muestra por pantalla los atributos de Jugador modificados. 

		System.out.println("---------------------------------");
		System.out.println("Nuevo nivel: ");
		System.out.println(); 
		System.out.println("Resistencia: "+jugador.getResistencia()); 
		System.out.println("Velocidad Movimiento: "+jugador.getVelocidadMovimiento()); 
		System.out.println("Velocidad Disparo: "+jugador.getVelocidadDisparo()); 
		System.out.println("Disparos Simultaneos: "+jugador.cantMaximaDisparos()); 
	}

	public void moverJugador(int dir){
		int i = jugador.getFila(), j = jugador.getColumna();

		switch (dir){
		case KeyEvent.VK_UP : //Arriba
			if ((i-1>=0 && mapa.getCelda(i-1,j).permitidoAvanzarTanque())){
				mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i-1,j)); 
			}
			jugador.setDireccion(0);
			jugador.cambiarImagenActual(0);
			break;
		case KeyEvent.VK_DOWN : //Abajo
			if ((i+1<mapa.cantidadFilas()) && mapa.getCelda(i+1,j).permitidoAvanzarTanque()){
				mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i+1,j)); 
			}
			jugador.setDireccion(1);
			jugador.cambiarImagenActual(1);
			break;
		case KeyEvent.VK_LEFT: //Izquierda
			if ((j-1>=0) && mapa.getCelda(i,j-1).permitidoAvanzarTanque()){
				mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i,j-1)); 
			}
			jugador.setDireccion(3);
			jugador.cambiarImagenActual(3);
			break;
		case KeyEvent.VK_RIGHT : //Derecha
			if ((j+1<mapa.cantidadColumnas()) && mapa.getCelda(i,j+1).permitidoAvanzarTanque()){
				mapa.concretarMovimientoTanque(mapa.getCelda(i,j),mapa.getCelda(i,j+1)); 
			}
			jugador.setDireccion(2);
			jugador.cambiarImagenActual(2);
			break;
		}
	}
	
	public void finalizarJuego(){
		//HACER
		System.out.println("Finalizo Juego");

		controladorEnemigos.terminate(); 
		controladorDisparos.terminate(); 

		gui.setInstrucciones(); 
	}

	public void jugadorDispara(){
		Disparo disparo = jugador.disparar(this);
		if (disparo!= null)
			añadirDisparo(disparo); 
	}
	
	private void generarPowerUp(){
		gui.add(generador.generarPowerUp().getImagenActual());
	}
	
	public void aumentarDestruidosJugador(){
		jugador.aumentarEnemigosDestruidos();
		if(jugador.getEnemigosDestruidos()%4==0){
			generarPowerUp();
		}
	}

	public void respawnearJugador(){

		//VER QUE PASA SI EN LA CELDA DONDE RESPAWNEO HAY UN ENEMIGO


		Celda celda = mapa.getCelda(mapa.cantidadFilas()-1, mapa.cantidadColumnas()/2-2);
		celda.setTanque(jugador);
		jugador.setCelda(celda);
		jugador.cambiarImagenActual(0);
		gui.add(jugador.getImagenActual()); 
	}

	public void añadirEnemigoEnGui(Enemigo enemigo){
		gui.add(enemigo.getImagenActual());
		if(jugador.getEnemigosDestruidos()>=16){
			generador.terminate();
		}
	}
	
	//Consultas

	public List<Disparo> getListaDisparos(){
		return disparos;
	}

	public List<Enemigo> getListaEnemigos(){
		return enemigos;
	}

	public Mapa getMapa(){
		return mapa; 
	}
}