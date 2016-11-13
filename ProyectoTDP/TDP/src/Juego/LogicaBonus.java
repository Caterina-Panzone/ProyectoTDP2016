package Juego;

import JGeneradores.GeneradorBonus;

public class LogicaBonus extends Logica{
	protected int puntos; 
	
	public LogicaBonus(GUI gui, int puntos) {
		super(gui); 
		this.puntos = puntos;  
		gui.setPuntaje(puntos);
		gui.setNivel(nivelMapa);
		
		//El jugador comienza con nivel 4 
		for(int i=0; i<4; i++){
			jugador.aumentarNivel(); 
		}
		
		for(int i=0; i<3; i++){
			jugador.disminuirVida(); 
		}
	}
	
	// Comandos
	
	protected void crearGenerador(){
		generador = new GeneradorBonus(this);
		for (int i = 0; i < 4; i++) {
			generador.generarEnemigo();
		}
	}
	
	protected void elegirTematica(){
		nivelMapa = 0;
		Tematica.setTematica("Bonus");
	}
	
	public void aumentarDestruidosJugador(int puntos) {
		jugador.aumentarEnemigosDestruidos(puntos);
		gui.setPuntaje(jugador.getPuntos()+puntos);
		generador.generarEnemigo();
	}
	
	//Nivel Bonus. No se resetea el mapa. 
	protected void resetearMapa(){}
}
