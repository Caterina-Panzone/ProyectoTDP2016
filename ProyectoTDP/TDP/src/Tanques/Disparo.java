package Tanques;

import java.util.List;

import javax.swing.ImageIcon;
import Juego.*;

public class Disparo extends ObjetoConImagen{

	//Atributos
	
	protected Tanque emisor;
	protected char direccion;
	
	//Constructor
	
	public Disparo(Tanque emisor, char dir, Celda celda){
		super(32,celda);
		this.emisor=emisor;
		direccion=dir;
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaA.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaB.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaD.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaI.png"));
		
		switch (direccion){
			case 'A': {
				cambiarImagenActual(0);
				break;
			}
			case 'B': {
				cambiarImagenActual(1);
				break;
			}
			case 'D': {
				cambiarImagenActual(2);
				break;
			}
			case 'I': {
				cambiarImagenActual(3);
				break;
			}
		}
	}
	
	//Comandos 
	
	public void destruirse(List<Disparo> disparos){
		cambiarImagenActual(transformarDireccion());
		emisor.eliminarDisparo();
		emisor=null;
		celda.setBala(null);
		celda=null;
		disparos.remove(this);
		ponerImagenVacia();
	}
	
	public void setCelda(Celda celda){
		this.celda = celda;  
	}
	
	public void avanzar(Mapa mapa, List<Disparo> disparos){
		
		int fila = celda.getFila(); 
		int columna = celda.getColumna(); 
		Celda nueva = null; 
		
		switch(direccion){
			case 'I':
			{
				if(columna-1>=0)
					nueva = mapa.getCelda(fila, columna-1); 
				else {
					destruirse(disparos); 
				}
			    break; 
		    }
		case 'D': 
			{
				if(columna+1<mapa.cantidadColumnas())
					nueva = mapa.getCelda(fila, columna+1); 
				else {
					destruirse(disparos); 
				}
				break; 
		    }
		case 'B': 
			{
				if(fila+1<mapa.cantidadFilas())
					nueva = mapa.getCelda(fila+1, columna); 
				else {
					destruirse(disparos);  
				}
				break; 
			}
		case 'A': 
			{
				if(fila-1>=0)
					nueva = mapa.getCelda(fila-1, columna); 
				else {
					destruirse(disparos); 
				}
				break; 
			}
		}
		
		if(nueva!=null){
			
			if(nueva.getObstaculo()!=null && nueva.getObstaculo().atraviesanDisparos()){
				if(nueva.getBala()==null){
					if(nueva.getTanque()==null){
						nueva.setBala(this); 
						setCelda(nueva);
						cambiarImagenActual(transformarDireccion());
					}
					else{
						//No avanza a la otra celda
						if(getEmisor().dispareTanque(nueva.getTanque()))
							destruirse(disparos);
						else {
							nueva.setBala(this);
							celda=nueva;
							cambiarImagenActual(transformarDireccion());
						}
							
					}
				}
				else{
					nueva.getBala().destruirse(disparos); 
					destruirse(disparos);
				}
			}
			else{
				//Ver lo de la pared de acero. 
				if(nueva.getObstaculo()!=null){
					nueva.getObstaculo().recibirGolpe();
					destruirse(disparos);
				}
				else{
					nueva.setBala(this);
					celda=nueva;
					cambiarImagenActual(transformarDireccion());
				}
			}
		}
	}
	
	private int transformarDireccion(){
		int retorno=3;
		
		switch (direccion){
			case 'A': {
				retorno = 0; 
				break;
				}
			case 'B':{ 
				retorno = 1; 
				break;
				}
			case 'D': {
				retorno = 2; 
				break;
				}
		}
		
		return retorno; 
	}
	
	//Consultas
	
	public Tanque getEmisor(){
		return emisor; 
	}
	
	public char getDireccion(){
		return direccion; 
	}
}
