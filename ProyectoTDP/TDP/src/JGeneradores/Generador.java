package JGeneradores;
import Tanques.*;

import java.util.List;
import java.util.Random;

import Juego.Celda;
import Juego.Logica;
import Juego.Mapa;
import Juego.Tematica;
import Poderes.*;

public abstract class Generador{
	protected Logica logica;
	protected Celda proximaCeldaSpawneo;
	protected InteligenciaEnemigo inteligencia; //Es para evitar tener 4 objetos distintos
	
	protected Generador(Logica logica){
		this.logica=logica;
		proximaCeldaSpawneo=logica.getMapa().getCelda(0, logica.getMapa().cantidadColumnas()-1);
		inteligencia= new InteligenciaEnemigo(logica);
	}
	
	protected Celda getProximaCelda(){
		Mapa mapa = logica.getMapa(); 
		if(proximaCeldaSpawneo.getColumna()==0){
			return mapa.getCelda(0, mapa.cantidadColumnas()/3);
		}
		else{
			if(proximaCeldaSpawneo.getColumna()==mapa.cantidadColumnas()/3){
				return mapa.getCelda(0,mapa.cantidadColumnas()/3 * 2);
			}
			else{
				if(proximaCeldaSpawneo.getColumna()==mapa.cantidadColumnas()/3*2){
					return mapa.getCelda(0, mapa.cantidadColumnas()-1);
				}
				else {
					return mapa.getCelda(0,0);
				}
			}
		}
	}
	
	public void generarEnemigo(){
		List<Enemigo> enemigos = logica.getListaEnemigos();
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
		proximaCeldaSpawneo = getProximaCelda(); 
	}
	
	public abstract boolean perteneceRapido(int n);
	
	public abstract boolean perteneceDePoder(int n);
	
	public abstract boolean perteneceBlindado(int n);

	public PowerUp generarPowerUp(){
		Mapa mapa = logica.getMapa(); 
				
		int fila, columna,power;
		PowerUp nuevo=null;
		
		Random rnd = new Random();
		fila = rnd.nextInt(mapa.cantidadFilas());
		columna= rnd.nextInt(mapa.cantidadColumnas());
		power=rnd.nextInt(9);
		Celda celda= mapa.getCelda(fila, columna);
		
		
		//Si habia un power up lo sobreescribe. 
		if(celda.getPower()!=null){
			celda.getPower().ponerImagenVacia(); 
			celda.getPower().setCelda(null); 
		}
		
//		switch(power){
//			case 0: {
//				nuevo= new Casco(celda);
//				break;
//			}
//			case 1: {
//				nuevo= new Granada(celda, logica.getListaEnemigos());
//				break;
//			}
//			case 2: {
//				nuevo= new TimerPower(celda, logica.getControladorEnemigos()); 
//				break;
//			}
//			case 3: {
//				nuevo= new Pala(celda, logica);
//				break;
//			}
//			case 4: {
//				nuevo= new TanquePower(celda); 
//				break;
//			}
//			case 5: {
//				if(Tematica.getTematica()!="Coraje")
//					nuevo= new TematicaCoraje(celda,logica);
//				break;
//			}	
//			case 6: {
//				if(Tematica.getTematica()!="EdEdd&Eddy")
//					nuevo= new TematicaEEnE(celda,logica);
//				break;
//			}
//			case 7: {
//				if(Tematica.getTematica()!="Dexter")
//					nuevo= new TematicaDexter(celda,logica);
//				break;
//			}
//			case 8: {
//				nuevo= new Estrella(celda); 
//				break;
//			}
//		}
//		
//		if(nuevo==null){
//			nuevo= new Estrella(celda);
//		}
		
		// nuevo = new Granada(celda,enemigos);
		// nuevo = new Casco(celda);
		// nuevo = new TimerPower(celda, logica.getControladorEnemigos()); 
		// nuevo = new Pala(celda, logica); 
		// nuevo = new TanquePower(celda, logica); 
		// nuevo = new Estrella(celda); 
		 //nuevo = new TematicaCoraje(celda,logica); 
		
		celda.setPower(nuevo);
		return nuevo;
	}
	
	public abstract Generador getSiguienteGenerador();

}
