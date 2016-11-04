package Juego;
import Tanques.*;

import java.util.List;
import java.util.Random;

import Poderes.*;

public abstract class Generador extends Thread {

	private volatile boolean ejecutar; 
	protected Logica logica;
	protected Mapa mapa;
	protected List<Enemigo> enemigos;
	protected Celda proximaCeldaSpawneo;
	
	protected Generador(Logica logica){
		this.logica=logica;
		mapa=logica.getMapa();
		enemigos=logica.getListaEnemigos();
		proximaCeldaSpawneo=mapa.getCelda(0, mapa.cantidadColumnas()-1);
	}
	
	private Celda getProximaCelda(){
		if(proximaCeldaSpawneo.getColumna()==0){
			return mapa.getCelda(0, mapa.cantidadColumnas()/2);
		}
		else{
			if(proximaCeldaSpawneo.getColumna()==mapa.cantidadColumnas()/2){
				return mapa.getCelda(0,mapa.cantidadColumnas()-1);
			}
			else{
				return mapa.getCelda(0,0);
			}
		}
	}
	
	public void terminate(){
		ejecutar = false; 
	}
	public void run() {
		ejecutar = true; 
		while(ejecutar){
			try {
				if(enemigos.size()<4){
					generarEnemigo();
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void generarEnemigo() {
		Enemigo nuevo;
		int n;
		Random rnd = new Random();
		n = rnd.nextInt(20);
		
		while(!proximaCeldaSpawneo.permitidoAvanzarTanque()){
			proximaCeldaSpawneo=getProximaCelda();
		}
		
		if(perteneceRapido(n)){
			nuevo= new Rapido(proximaCeldaSpawneo, new InteligenciaEnemigo(mapa), enemigos);
		}
		else{
			if(perteneceDePoder(n)){
				nuevo= new DePoder(proximaCeldaSpawneo, new InteligenciaEnemigo(mapa), enemigos);
			}
			else{
				if(perteneceBlindado(n)){
					nuevo= new Blindado(proximaCeldaSpawneo, new InteligenciaEnemigo(mapa), enemigos);
				}
				else{ //pertenece a basico
					nuevo= new Basico(proximaCeldaSpawneo, new InteligenciaEnemigo(mapa), enemigos);
				}
			}
		}
		proximaCeldaSpawneo.setTanque(nuevo);
		enemigos.add(nuevo);
		logica.a�adirEnemigoEnGui(nuevo);
		
//		celda = mapa.getCelda(0, 0);
//		enemigo = new DePoder(celda, inteligencia,enemigos); 
//		celda.setTanque(enemigo);
//		enemigos.add(enemigo);
//		enemigo.setDireccion(1);
//		gui.add(enemigo.getImagenActual()); 
//
		
		
	}
	
	public abstract boolean perteneceRapido(int n);
	
	public abstract boolean perteneceDePoder(int n);
	
	public abstract boolean perteneceBlindado(int n);

	public abstract PowerUp generarPowerUp();

}