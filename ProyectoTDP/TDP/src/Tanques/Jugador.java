package Tanques;

import Juego.*;
import Niveles.*;
import Obstaculos.Acero;
import Poderes.PowerUp;
import TDisparo.Disparo;
import TDisparo.DisparoJugador;

import javax.swing.ImageIcon;

public class Jugador extends Tanque implements Runnable{
	//Atributos 
	
	protected int puntos;
	protected int vidas;
	protected Nivel nivel;
	protected int enemigosDestruidos;
	protected boolean invulnerabilidad;
	protected Logica logica;
	protected int jugador; 
	protected Thread t; 
	protected Celda nueva; 

	
	//Constructor
	
	public Jugador(Celda celda,Logica logica){
		super(celda);
		jugador = 1; 
		celda.bloquear(); 
		setDireccion(0);
		vidas = 4;
		nivel = new Nivel1();
		puntos = 0;
		enemigosDestruidos = 0;
		invulnerabilidad = false;
		this.logica=logica; 
		cambiarImagenActual(0);
		lock=0;
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica();
		if(tematica == "EdEdd&Eddy"){
			switch(jugador){
				case 0:{
					jugador = 1;
					break; 
				}
				case 1: {
					jugador = 2;
					break;
				}
				case 2: {
					jugador = 3; 
					break;
				}
				case 3: {
					jugador = 1; 
					break;
				}
			}
		}
		else{
			jugador=1;
		}
		setImagenes(); 
	}
	
	private void setImagenes(){
		String tematica = Tematica.getTematica();
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Arriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Abajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Derecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Izquierda.gif"));
	}
	
	//Comando
	
	public void actuar(PowerUp poder){
		if(poder!=null)
			poder.actuar(this);	
	}
	
	public void reiniciarDestruidos(){
		enemigosDestruidos = 0;
	}
	
	public void setInvulnerabilidad(boolean estado){
		String tematica = Tematica.getTematica(); 
		invulnerabilidad = estado;
		if(estado==true){
			imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadArriba.gif"));
			imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadAbajo.gif"));
			imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadDerecha.gif"));
			imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadIzquierda.gif"));
			cambiarImagenActual(direccion);
		}
		else{
			setImagenes();
			cambiarImagenActual(direccion);
		}
	}
	
	protected void aumentarPuntos(int puntos){
		this.puntos += puntos;
	}
	
	public void aumentarNivel(){
		nivel = nivel.siguienteNivel();
	}
	
	public void aumentarVida(){
		vidas++;
		logica.resetearVida(vidas);
	}
	
	public void volverPosicionInicial(){
		Mapa mapa = logica.getMapa(); 
		celda.setTanque(null);
		celda.desbloquear(); 
		celda = null; 
		ponerImagenVacia();
		if(nueva!=null){
			nueva.desbloquear(); 
			nueva = null; 
		}		
		Celda nuevaC = mapa.getCelda(mapa.cantidadFilas() - 1, mapa.cantidadColumnas() / 2 - 2);
		Tanque tanque = nuevaC.getTanque(); 
		if(tanque!=null){
			tanque.destroy(); 
		}
		nuevaC.setTanque(this);
		celda = nuevaC; 
		x = celda.getColumna()*tama�o;
		y = celda.getFila()*tama�o;
		
		setearImagenes(); 
		cambiarImagenActual(0);
		setDireccion(0);
	}
	
	public boolean recibirGolpe(Enemigo tanque){
		if(!invulnerabilidad){
			golpesRecibidos++;
		}
		if(nivel.getResistencia()<=golpesRecibidos){
			volverPosicionInicial(); 
			vidas--;
			logica.resetearVida(vidas);
			if(vidas<=0){
				logica.finalizarJuego();
			}
			else{
				nivel=new Nivel1();
			}
		}
		return true; 
	}
	
	public boolean recibirGolpe(Jugador tanque){
		return false; 
	}
	
	public boolean dispareTanque(Tanque tanque){
		return tanque.recibirGolpe(this);
	}
	
	
	public void romperAcero(Acero acero){
		nivel.romperAcero(acero); 
	}
	
	public void aumentarEnemigosDestruidos(int puntos){
		enemigosDestruidos++;
		aumentarPuntos(puntos); 
	}
	
	public Disparo disparar(Logica logica) {
		Disparo disparo = null; 
		if(disparosRealizados<cantMaximaDisparos()){
			disparosRealizados++;
			disparo = new DisparoJugador(this, direccion, celda, logica);
		}
		return disparo; 
	}
	
	//Consultas
	
	public int getVidas(){
		return vidas;
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public boolean getInvulnerabilidad(){
		return invulnerabilidad;
	}
	
	public int getEnemigosDestruidos(){
		return enemigosDestruidos; 
	}
	
	public int getVelocidadMovimiento(){
		return nivel.getVelocidadMovimiento();
	}
	
	public int getVelocidadDisparo(){
		return nivel.getVelocidadDisparo(); 
	}
	
	public int getResistencia(){
		return nivel.getResistencia();
	}
	
	public int cantMaximaDisparos(){
		return nivel.getDisparosSimultaneos(); 
	}
	
	public boolean bloqueado(){
		return lock!=0; 
	}

	public void moverse(Celda nueva) {
		this.nueva = nueva;
		nueva.bloquear(); 
		lock = tama�o/(getVelocidadMovimiento()*aumento); 
		t = new Thread(this); 
		t.start(); 
	}
	
	public void run() {	
		Celda vieja = celda; 
		
		try {
			for (int i=lock; i>0; i--) {
				if(i==1){
					vieja.desbloquear(); 
					x = celda.getColumna()*tama�o;
					y = celda.getFila()*tama�o; 
					cambiarImagenActual(direccion);  
				} else {
					if(i==(tama�o/(getVelocidadMovimiento()*aumento))/2){ 
						logica.getMapa().concretarMovimientoTanque(this, nueva);
						vieja.bloquear();
						celda.desbloquear(); 
					}
				}
				moverseGraficamente(); 
				Thread.sleep(30); 
			}
			lock=0; 
		} catch (InterruptedException e) {}
	}
}