package JGeneradores;

import java.util.List;
import java.util.Random;

import Juego.Logica;
import Tanques.Blindado;
import Tanques.DePoder;
import Tanques.Enemigo;
import Tanques.Rapido;

public class GeneradorBonus extends Generador {
	public GeneradorBonus(Logica logica){
		super(logica);
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
		
		if(perteneceBlindado(n)){
			nuevo= new Blindado(proximaCeldaSpawneo, inteligencia, enemigos);
		}
		else{
			if(perteneceDePoder(n)){
				nuevo= new DePoder(proximaCeldaSpawneo, inteligencia, enemigos);
			}
			else{
				nuevo= new Rapido(proximaCeldaSpawneo, inteligencia, enemigos);
			}
		}
		
		proximaCeldaSpawneo.setTanque(nuevo);
		enemigos.add(nuevo);
		logica.añadirEnemigoEnGui(nuevo);
		proximaCeldaSpawneo = getProximaCelda(); 
	}

	public boolean perteneceDePoder(int n) {
		return (n>=0)&&(n<=6);
	}

	public boolean perteneceBlindado(int n) {
		return (n>=14)&&(n<=19);
	}
	
	public boolean perteneceRapido(int n) {
		return (n>=7)&&(n<=13);
	}
	
	public Generador getSiguienteGenerador(){
		return null; 
	}
}
