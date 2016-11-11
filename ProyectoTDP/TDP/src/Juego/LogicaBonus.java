package Juego;

public class LogicaBonus extends Logica{
	protected int puntos; 
	
	public LogicaBonus(GUI gui, int puntos) {
		super(gui); 
		nivelMapa = 0;
		this.puntos = puntos;  
		gui.setPuntaje(puntos);
		
		//El jugador comienza con nivel 4 
		for(int i=0; i<4; i++){
			jugador.aumentarNivel(); 
		}
		
		for(int i=0; i<3; i++){
			jugador.disminuirVida(); 
		}
	}
	
	// Comandos
	
	protected void elegirTematica(){
		Tematica.setTematica("Bonus");
	}
	
	protected void generarNuevoMapa() {
		mapa = new Mapa(this.getClass().getResource("/Archivos/Bonus.txt").getPath(),this);
	}
	
	public void aumentarDestruidosJugador(int puntos) {
		jugador.aumentarEnemigosDestruidos(puntos);
		gui.setPuntaje(jugador.getPuntos()+puntos);
		generador.generarEnemigo();
	}
	
	//Nivel Bonus. No se resetea el mapa. 
	public void resetearMapa(){}
}
