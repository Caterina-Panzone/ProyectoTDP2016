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
		generarEnemigos();
	}
	
	//Comandos 
	
	public void aņadirDisparo(Disparo disparo){
		disparos.add(disparo);
		gui.add(disparo.getImagenActual());
	}
	
	private void generarNuevoMapa(){
//		mapa = new Mapa(this.getClass().getResource("/Archivos/nivel1.txt").getPath(),gui);
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
	
	public void generarEnemigos(){
		InteligenciaEnemigo inteligencia = new InteligenciaEnemigo(mapa);
		Celda celda;
		Enemigo enemigo;  
		
		celda = mapa.getCelda(0, mapa.cantidadColumnas()-1);
		enemigo = new Basico(celda, inteligencia,enemigos); 
		celda.setTanque(enemigo);
		enemigos.add(enemigo);
		enemigo.setDireccion(1);
		gui.add(enemigo.getImagenActual()); 	
		
		celda = mapa.getCelda(0, 0);
		enemigo = new DePoder(celda, inteligencia,enemigos); 
		celda.setTanque(enemigo);
		enemigos.add(enemigo);
		enemigo.setDireccion(1);
		gui.add(enemigo.getImagenActual()); 
		
		celda = mapa.getCelda(0, mapa.cantidadColumnas()/2 -1);
		enemigo = new Blindado(celda, inteligencia,enemigos); 
		celda.setTanque(enemigo);
		enemigos.add(enemigo);
		enemigo.setDireccion(3);
		gui.add(enemigo.getImagenActual()); 
		
		celda = mapa.getCelda(0, mapa.cantidadColumnas()/2);
		enemigo = new Rapido(celda, inteligencia,enemigos); 
		celda.setTanque(enemigo);
		enemigos.add(enemigo);
		enemigo.setDireccion(2);
		gui.add(enemigo.getImagenActual()); 
		
		controladorEnemigos.start(); 
		controladorDisparos.start();
	}
	
	//ELIMINAR DESPUES DE GENERAR ENEMIGOS. 
	public void generarEnemigo(){
		
		if(enemigos.size()>4){
			eliminarEnemigo();
		}
		else{
			Celda celda= mapa.getCelda(0, mapa.cantidadColumnas()-1);
			InteligenciaEnemigo inteligencia = new InteligenciaEnemigo(mapa);
			if(celda.getTanque()==null){
				Enemigo enemigo= new Basico(celda, inteligencia, enemigos);
				celda.setTanque(enemigo);
				enemigos.add(enemigo);
				gui.add(enemigo.getImagenActual());
			}
		}
	}
	
	public void eliminarEnemigo(){
		Enemigo eliminado=enemigos.get(0);
		int fila= eliminado.getFila();
		int columna= eliminado.getColumna();
		
		jugador.aumentarPuntos(eliminado.getPuntos());
		
		mapa.getCelda(fila, columna).setTanque(null);
		eliminado.setCelda(null);
		enemigos.remove(eliminado);
		gui.remove(eliminado.getImagenActual());
	}
	
	public void finalizarJuego(){
		//HACER
		System.out.println("Finalizo Juego");
		
		controladorEnemigos.terminate(); 
		controladorDisparos.terminate(); 
		
		gui.setInstrucciones(); 
	}
	
	public void jugadorDispara(){
		Disparo disparo = jugador.disparar(mapa);
		if (disparo!= null)
			aņadirDisparo(disparo); 
	}
	
	public void respawnearJugador(){
		Celda celda = mapa.getCelda(mapa.cantidadFilas()-1, mapa.cantidadColumnas()/2-2);
		celda.setTanque(jugador);
		jugador.setCelda(celda);
		jugador.cambiarImagenActual(0);
		gui.add(jugador.getImagenActual()); 
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