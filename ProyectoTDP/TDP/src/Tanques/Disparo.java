package Tanques;

import java.util.List;

import javax.swing.ImageIcon;
import Juego.*;

public class Disparo extends ObjetoConImagen{

	//Atributos
	
	protected Tanque emisor;
	protected int direccion;
	
	//Constructor
	
	public Disparo(Tanque emisor, int dir, Celda celda){
		super(32,celda);
		
		celda.setBala(this); 
		this.emisor=emisor;
		direccion=dir;
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaA.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaB.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaD.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaI.png"));
		
		cambiarImagenActual(direccion);
	}
	
	//Comandos 
	
	public void destruirse(List<Disparo> disparos){
		disparos.remove(this);
		celda.setBala(null);
		cambiarImagenActual(direccion);
		ponerImagenVacia();
		emisor.eliminarDisparo(); 
		//System.out.println("DESTRUCCION BALA! fila "+celda.getFila()+" columna "+celda.getColumna());
		celda=null;		
		emisor=null;
		System.out.println("Tama�o disparos:: "+disparos.size());
	}
	
	public void avanzar(Mapa mapa, List<Disparo> disparos){

		Celda nueva = mapa.getCeldaSiguiente(celda, direccion); 
				
		if(nueva!=null){
			System.out.println("AVANCE DISPARO! fila "+nueva.getFila()+" columna "+nueva.getColumna());
			if(nueva.getObstaculo()!=null && nueva.getObstaculo().atraviesanDisparos()){
				System.out.println("objeto");
				auxiliar(nueva,disparos); 
			}
			else{
				//Ver lo de la pared de acero.
				if(nueva.getObstaculo()!=null){ 
					System.out.println("pared de acero");
					nueva.getObstaculo().recibirGolpe();
					destruirse(disparos);
				}
				else{
					System.out.println("obstaculo null");
					auxiliar(nueva,disparos); 
//					if(nueva.getTanque()!= null && getEmisor().dispareTanque(nueva.getTanque())){ 
//						destruirse(disparos);
//					}
//					else {
//						concretarMovimientoDisparo(nueva); 
//					}
				}
			}
		}
		else{
			System.out.println("outbound DISPARO! fila "+celda.getFila()+" columna "+celda.getColumna());
			destruirse(disparos); 
		}
		System.out.println("AVANZAR SIZE:: "+disparos.size());	
	}
	
	/*
	 * Setea el disparo en la nueva celda. 
	 */
	
	private void concretarMovimientoDisparo(Celda nueva){
		celda=nueva;
		celda.setBala(this);
		cambiarImagenActual(direccion);
	}
	
	/*
	 * aux
	 */
	private void auxiliar(Celda nueva, List<Disparo> disparos){
		if(nueva.getBala()==null){
			if(nueva.getTanque()!= null && getEmisor().dispareTanque(nueva.getTanque())){ 
				System.out.println("DESTRUIR DISPARO UNICO:: "+this.getColumna()+" "+this.getFila()); 
				destruirse(disparos);
			}
			else {
				concretarMovimientoDisparo(nueva); 
			}
		}
		else{ //Colisi�n de dos disparos. 
			
			System.out.println("----------"+this.getColumna()+" "+this.getFila()); 
			System.out.println("COLISION DISPARO! fila "+nueva.getFila()+" columna "+nueva.getColumna());
			System.out.println("----------"+nueva.getBala().getColumna()+" "+nueva.getBala().getFila());
			System.out.println("Colision entre disparos");
			nueva.getBala().destruirse(disparos); 
			destruirse(disparos);
			System.out.println("----------"); 
		}
	}
	
	//Consultas
	
	public Tanque getEmisor(){
		return emisor; 
	}
	
	public int getDireccion(){
		return direccion; 
	}
}
