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
	protected InteligenciaEnemigo inteligencia; //Es para evitar tener 4 objetos distintos
	
	protected Generador(Logica logica){
		this.logica=logica;
		mapa=logica.getMapa();
		enemigos=logica.getListaEnemigos();
		proximaCeldaSpawneo=mapa.getCelda(0, mapa.cantidadColumnas()-1);
		inteligencia= new InteligenciaEnemigo(logica);
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
			nuevo= new Rapido(proximaCeldaSpawneo, inteligencia, enemigos);
		}
		else{
			if(perteneceDePoder(n)){
				nuevo= new DePoder(proximaCeldaSpawneo, inteligencia, enemigos);
			}
			else{
				if(perteneceBlindado(n)){
					nuevo= new Blindado(proximaCeldaSpawneo, inteligencia, enemigos);
				}
				else{ //pertenece a basico
					nuevo= new Basico(proximaCeldaSpawneo, inteligencia, enemigos);
				}
			}
		}
		proximaCeldaSpawneo.setTanque(nuevo);
		enemigos.add(nuevo);
		logica.añadirEnemigoEnGui(nuevo);
			
	}
	
	public abstract boolean perteneceRapido(int n);
	
	public abstract boolean perteneceDePoder(int n);
	
	public abstract boolean perteneceBlindado(int n);

	public PowerUp generarPowerUp(){
		
		//ver que pasa si ya habia un powerup en la celda
		
		int fila, columna;
		Random rnd = new Random();
		fila = rnd.nextInt(mapa.cantidadFilas());
		columna= rnd.nextInt(mapa.cantidadColumnas());
		//Celda celda = mapa.getCelda(11,0); 
		Celda celda= mapa.getCelda(fila, columna);
		
		//PowerUp nuevo= new Granada(celda,enemigos);
		//PowerUp nuevo= new Casco(celda);
		PowerUp nuevo = new Pala(celda, mapa); 
		celda.setPower(nuevo);
		return nuevo;
		
	}

}
