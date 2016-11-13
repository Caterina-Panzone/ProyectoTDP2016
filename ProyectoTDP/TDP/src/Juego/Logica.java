package Juego;

import Tanques.*;
import Obstaculos.*;
import TDisparo.Disparo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JLabel;

import JGeneradores.Generador;
import JGeneradores.GeneradorNivel1;

public class Logica {
	// Atributos

	protected GUI gui;
	protected List<Enemigo> enemigos;
	protected List<Disparo> disparos;
	protected Jugador jugador;
	protected int nivelMapa;
	protected Mapa mapa;
	protected ControladorEnemigos controladorEnemigos;
	protected ControladorDisparos controladorDisparos;
	protected Generador generador;
	protected int cantidadBosques;
	protected Timer tiempo; 

	// Constructor

	public Logica(GUI gui) {
		this.gui = gui;
		nivelMapa = 1;
		cantidadBosques=0;
		generarNuevoMapa();
		
		TimerGameOver tgo = new TimerGameOver(); 
		tiempo = new Timer(500,tgo); 

		Celda celda = mapa.getCelda(mapa.cantidadFilas() - 1, mapa.cantidadColumnas() / 2 - 2);
		jugador = new Jugador(celda, this);
		gui.add(jugador.getImagenActual());
		gui.setVida(jugador.getVidas());
		gui.setPuntaje(jugador.getPuntos());
		gui.setNivel(nivelMapa);

		enemigos = new LinkedList<Enemigo>();
		controladorEnemigos = new ControladorEnemigos(this);
		disparos = new LinkedList<Disparo>();
		controladorDisparos = new ControladorDisparos(this);

		crearGenerador(); 

		controladorEnemigos.start();
		controladorDisparos.start();
	}

	// Comandos
	
	protected void crearGenerador(){
		generador = new GeneradorNivel1(this);
		for (int i = 0; i < 4; i++) {
			generador.generarEnemigo();
		}
	}

	protected void elegirTematica() {
		Random rnd = new Random();
		int tematica = rnd.nextInt(3);
		
//		Tematica.setTematica("Dexter");
		
		switch(tematica){
			case 0: {
				Tematica.setTematica("Dexter");
				break;
			}
			case 1: {
				Tematica.setTematica("Coraje");
				break;
			}
			case 2: {
				Tematica.setTematica("EdEdd&Eddy");
				break;
			}
		}
	}
	
	public void cambiarFondoGUI(){
		gui.cambiarFondo(); 
	}
	
	public void setImagenJugador(){
		jugador.setearImagenes(); 
	}

	public void añadirDisparo(Disparo disparo) {
		disparos.add(disparo);
		gui.add(disparo.getImagenActual());
		gui.traerFrenteDisparo(disparo.getImagenActual());
	}
	
	public void añadirBosque(List<Obstaculo> bosque){
		gui.ubicarBosque(bosque);
	}

	public void añadirObstaculo(Obstaculo obstaculo) {
		gui.add(obstaculo.getImagenActual());
	}

	protected void generarNuevoMapa() {
		elegirTematica();
		mapa = new Mapa(this.getClass().getResource("/Archivos/nivel"+nivelMapa+".txt").getPath(),this);
	}
	
	//ELIMINAR
//	public void aumentarNivelJugador() {
//		jugador.aumentarNivel();
//	}

	public void moverJugador(int dir) {
		if(!jugador.bloqueado()){
			int i = jugador.getFila(), j = jugador.getColumna();

			switch (dir) {
			case KeyEvent.VK_UP: // Arriba
				jugador.setDireccion(0);
				if ((i - 1 >= 0 && mapa.getCelda(i - 1, j).permitidoAvanzarTanque())) {
					jugador.moverse(mapa.getCelda(i - 1, j)); 
				}else {
					jugador.cambiarImagenActual(0);
				}
				break;
			case KeyEvent.VK_DOWN: // Abajo
				jugador.setDireccion(1);
				if ((i + 1 < mapa.cantidadFilas()) && mapa.getCelda(i + 1, j).permitidoAvanzarTanque()) {
					jugador.moverse(mapa.getCelda(i + 1, j));
				} else {
					jugador.cambiarImagenActual(1);
				}
				break;
			case KeyEvent.VK_LEFT: // Izquierda
				jugador.setDireccion(3);
				if ((j - 1 >= 0) && mapa.getCelda(i, j - 1).permitidoAvanzarTanque()) {
					jugador.moverse(mapa.getCelda(i, j-1));
				} else {
					jugador.cambiarImagenActual(3);
				}
				break;
			case KeyEvent.VK_RIGHT: // Derecha
				jugador.setDireccion(2);
				if ((j + 1 < mapa.cantidadColumnas()) && mapa.getCelda(i, j + 1).permitidoAvanzarTanque()) {
					jugador.moverse(mapa.getCelda(i, j+1));
				} else{
					jugador.cambiarImagenActual(2);
				}
				break;
			}
		}
	}

	public void finalizarJuego() {
		gui.deshabilitarTeclado(); 
		controladorEnemigos.terminate();
		controladorDisparos.terminate();
		controladorEnemigos = null; 
		controladorDisparos = null; 
		tiempo.start(); 
	}

	public void jugadorDispara() {
		jugador.disparar(this);
	}

	private void generarPowerUp() {
		JLabel imagenPower = generador.generarPowerUp().getImagenActual();
		gui.add(imagenPower);
		gui.ubicarPower(imagenPower);
	}

	public void aumentarDestruidosJugador(int puntos) {
		jugador.aumentarEnemigosDestruidos(puntos);
		gui.aumentarEnemigosDestruidos(jugador.getEnemigosDestruidos());
		gui.setPuntaje(jugador.getPuntos());

		if (jugador.getEnemigosDestruidos() < 16) {
			generador.generarEnemigo();
		} else {
			if (enemigos.isEmpty()) {
				nivelMapa++;
				if(nivelMapa<4){
					resetearMapa();
				} else {
					gui.reiniciarPanelesJuego();
					gui.inicializarPanelesBonus(jugador.getPuntos()); 
				}
			}
		}
		if (jugador.getEnemigosDestruidos() % 4 == 0) {
			generarPowerUp();
			gui.repaint(); 
		}
	}
	
	protected void resetearMapa(){
		gui.deshabilitarTeclado();
	
		controladorEnemigos.terminate(); 
		controladorDisparos.terminate(); 
		
		while(!disparos.isEmpty()){
			disparos.get(0).eliminarse(); 
		}
		
		enemigos = new LinkedList<Enemigo>();
		controladorEnemigos = new ControladorEnemigos(this);
		disparos = new LinkedList<Disparo>();
		controladorDisparos = new ControladorDisparos(this);
		
		cantidadBosques=0; 
		gui.reiniciarPanelesJuego();
		gui.inicializarPaneles(); 
		
		jugador.reiniciarDestruidos();
		generarNuevoMapa();
		gui.cambiarFondo(); 
			
		jugador.volverPosicionInicial(); 
		jugador.aumentarNivel();
			
		generador = generador.getSiguienteGenerador();
		for (int i = 0; i < 4; i++) {
			generador.generarEnemigo();
		}
			
		controladorEnemigos.start(); 
		controladorDisparos.start(); 
			
		gui.add(jugador.getImagenActual());
		gui.setVida(jugador.getVidas());
		gui.setPuntaje(jugador.getPuntos());
		gui.habilitarTeclado();
		gui.setNivel(nivelMapa);
		gui.repaint(); 
	}
	
	//eliminar
	public void resetearMapaELIMINAR(){
		gui.deshabilitarTeclado();
	
		controladorEnemigos.terminate(); 
		controladorDisparos.terminate(); 
		
		while(!disparos.isEmpty()){
			disparos.get(0).eliminarse(); 
		}
		//Eliminar cuando se saque de la gui. 
		while(!enemigos.isEmpty()){
			enemigos.get(0).destroy(); 
		}
		
		enemigos = new LinkedList<Enemigo>();
		controladorEnemigos = new ControladorEnemigos(this);
		disparos = new LinkedList<Disparo>();
		controladorDisparos = new ControladorDisparos(this);
		
		if(nivelMapa<3){
			cantidadBosques=0; 
			nivelMapa++; 
			gui.reiniciarPanelesJuego();
			gui.inicializarPaneles(); 
			generarNuevoMapa();
			gui.cambiarFondo(); 
			
			jugador.reiniciarDestruidos();
			jugador.volverPosicionInicial(); 
			jugador.aumentarNivel();
			
			generador = generador.getSiguienteGenerador();
			for (int i = 0; i < 4; i++) {
				generador.generarEnemigo();
			}
			
			controladorEnemigos.start(); 
			controladorDisparos.start(); 
			
			gui.add(jugador.getImagenActual());
			gui.setVida(jugador.getVidas());
			gui.setPuntaje(jugador.getPuntos());
			gui.habilitarTeclado();
			gui.setNivel(nivelMapa);
			gui.repaint(); 
		}else {
			gui.reiniciarPanelesJuego();
			gui.inicializarPanelesBonus(jugador.getPuntos()); 
		}
	}

	public void añadirEnemigoEnGui(Enemigo enemigo) {
		gui.add(enemigo.getImagenActual());
	}

	public void resetearVida(int vidas) {
		gui.setVida(vidas);
	}
	
	public void setCantidadBosques(int cant){
		cantidadBosques=cant;
	}

	// Consultas
	
	public ControladorEnemigos getControladorEnemigos() {
		return controladorEnemigos;
	}

	public List<Disparo> getListaDisparos() {
		return disparos;
	}

	public List<Enemigo> getListaEnemigos() {
		return enemigos;
	}

	public Mapa getMapa() {
		return mapa;
	}
	
	public int getcantidadBosques(){
		return cantidadBosques;
	}
	
	public void repaint(){
		gui.repaint();
	}
	
	private class TimerGameOver implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			tiempo.stop(); 
			gui.gameOver();
			tiempo = null; 
		}
	}
}