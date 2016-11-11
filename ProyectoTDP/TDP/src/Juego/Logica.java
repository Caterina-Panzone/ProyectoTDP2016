package Juego;

import Tanques.*;
import Obstaculos.*;
import TDisparo.Disparo;

import java.awt.event.KeyEvent;

import java.util.LinkedList;
import java.util.List;

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

	// Constructor

	public Logica(GUI gui) {
		this.gui = gui;
		nivelMapa = 1;
		elegirTematica();
		cantidadBosques=0;
		generarNuevoMapa();

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

		generador = new GeneradorNivel1(this);
		for (int i = 0; i < 4; i++) {
			generador.generarEnemigo();
		}

		controladorEnemigos.start();
		controladorDisparos.start();
	}

	// Comandos

	private void elegirTematica() {
		Tematica.setTematica("EdEdd&Eddy");
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

	private void generarNuevoMapa() {
		mapa = new Mapa(this.getClass().getResource("/Archivos/nivel"+nivelMapa+".txt").getPath(),this);
	}
	
	//ELIMINAR
	public void aumentarNivelJugador() {
		jugador.aumentarNivel();
	}

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
		System.out.println("Finalizo Juego");
		gui.deshabilitarTeclado(); 
		controladorEnemigos.terminate();
		controladorDisparos.terminate();
		controladorEnemigos = null; 
		controladorDisparos = null; 
		gui.gameOver();
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
				// HAY QUE CAMBIAR EL MAPA
				nivelMapa++;
				if(nivelMapa<4){
					resetearMapa();
				} else {
					//AVISAR A LA GUI QUE GANO.
				}
			}
		}
		if (jugador.getEnemigosDestruidos() % 4 == 0) {
			generarPowerUp();
		}
	}
	//private
	public void resetearMapa(){
		gui.deshabilitarTeclado();
	
		controladorEnemigos.terminate(); 
		controladorDisparos.terminate(); 
		
		while(!disparos.isEmpty()){
			disparos.get(0).destruirse(); 
			System.out.println("Disparo destruido.");
		}
		while(!enemigos.isEmpty()){
			enemigos.get(0).destroy(); 
			System.out.println("Enemigo destruido.");
		}
		
		enemigos = new LinkedList<Enemigo>();
		controladorEnemigos = new ControladorEnemigos(this);
		disparos = new LinkedList<Disparo>();
		controladorDisparos = new ControladorDisparos(this);
		
		cantidadBosques=0; 
		nivelMapa++; 
		gui.reiniciarPanelesJuego();
		generarNuevoMapa();
		
		jugador.reiniciarDestruidos();
		jugador.volverPosicionInicial(); 
		
		generador = generador.getSiguienteGenerador();
		for (int i = 0; i < 4; i++) {
			generador.generarEnemigo();
		}
		
		//controladorEnemigos.start(); 
		//controladorDisparos.start(); 
		
		gui.add(jugador.getImagenActual());
		gui.setVida(jugador.getVidas());
		gui.setPuntaje(jugador.getPuntos());
		gui.habilitarTeclado();
		gui.setNivel(nivelMapa);
		gui.repaint(); 
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
}