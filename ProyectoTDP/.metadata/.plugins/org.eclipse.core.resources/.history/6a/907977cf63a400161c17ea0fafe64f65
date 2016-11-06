package Juego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import Obstaculos.*;
import Tanques.*; 

public class Mapa {
	//Atributos 
	
	protected Celda[][] matriz;
	
	//Constructor
	
	public Mapa(String archivo, Logica logica){
		BufferedReader br = null; 
		try{
			br = new BufferedReader(new FileReader(archivo));
			int fila = Integer.parseInt(br.readLine()); 
			int columna = Integer.parseInt(br.readLine());
			
			matriz = new Celda[fila][columna];
			String [] arr = new String[fila];
			String actual,caracter;
			Celda celda; 
			Obstaculo obst = null; 
			
			for(int i=0; i<fila; i++){
				actual = br.readLine(); 
				arr = actual.split(" "); 
				for(int j=0; j<columna; j++){
					caracter = arr[j];
					celda = new Celda(i,j); 
					switch (caracter){
						case "L":{
							obst = new Ladrillo(celda); 
							break; 
						}
						case "A":{
							obst = new Acero(celda); 
							break; 
						}
						case "B":{
							obst = new Arbol(celda); 
							break; 
						}
						case "H":{
							obst = new Agua(celda); 
							break; 
						}
						case "F":{
							obst = new Aguila(celda,logica);
							break; 
						}
						case "P":{
							obst = null;
							break; 
						}
					}
					celda.setObstaculo(obst); 
					matriz[i][j]=celda; 
					if (obst!=null){
						logica.añadirObstaculo(obst);
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(br!=null)
					br.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	//Comandos 
	
	public void concretarMovimientoTanque(Celda vieja, Celda nueva){
		Tanque tanque = vieja.getTanque();
		nueva.setTanque(tanque);
		vieja.setTanque(null);
		tanque.setCelda(nueva);
		
		vieja.desbloquear(); 
		nueva.bloquear(); 
		
		tanque.actuar(nueva.getPower());
	}
	
	//Consultas
	
	public Celda getCelda(int fila, int columna){
		return matriz[fila][columna]; 
	}
	
	/*
	 * Retorna la celda contigua dependiendo de la direcion. 
	 * Si está en los límites del mapa retorna nulo. 
	 */
	
	public Celda getCeldaSiguiente(Celda celda,int direccion){
		int fila = celda.getFila(); 
		int columna = celda.getColumna(); 
	
		Celda nueva = null; 
		
		switch(direccion){
			case 3:{//Izquierda
				if(columna-1>=0)
					nueva = getCelda(fila, columna-1); 
			    break; 
		    }
		case 2:{//Derecha
				if(columna+1<cantidadColumnas())
					nueva = getCelda(fila, columna+1); 
				break; 
		    }
		case 1: {//Abajo
				if(fila+1<cantidadFilas())
					nueva = getCelda(fila+1, columna); 
				break; 
			}
		case 0: {//Arriba
				if(fila-1>=0)
					nueva = getCelda(fila-1, columna); 
				break; 
			}
		}
		
		return nueva; 
	}
	
	public void protegerAguila(Logica logica){
		int columna = (cantidadColumnas()-1)/2-1; 
		int fila = cantidadFilas()-1; 
		
		setearCeldaAcero(matriz[fila][columna],logica);
		setearCeldaAcero(matriz[fila][columna+2],logica);
		
		fila--; 
		for(int i=0; i<3; i++){
			setearCeldaAcero(matriz[fila][columna+i],logica);
		}
	}
	
	public void desprotegerAguila(Logica logica){
		int columna = (cantidadColumnas()-1)/2-1; 
		int fila = cantidadFilas()-1; 
		
		setearCeldaLadrillo(matriz[fila][columna],logica);
		setearCeldaLadrillo(matriz[fila][columna+2],logica);
		
		fila--; 
		for(int i=0; i<3; i++){
			setearCeldaLadrillo(matriz[fila][columna+i],logica);
		}
	}
	
	private void setearCeldaAcero(Celda celda, Logica logica){
		if(celda.getObstaculo()!=null){
			celda.getObstaculo().setCelda(null);
			celda.getObstaculo().ponerImagenVacia(); 
			celda.setObstaculo(null); 
		}
		Obstaculo acero = new Acero(celda);
		logica.añadirObstaculo(acero); 
		celda.setObstaculo(acero);
	}
	
	private void setearCeldaLadrillo(Celda celda, Logica logica){
		if(celda.getObstaculo()!=null){
			celda.getObstaculo().setCelda(null);
			celda.getObstaculo().ponerImagenVacia(); 
			celda.setObstaculo(null); 
			Obstaculo acero = new Ladrillo(celda);
			logica.añadirObstaculo(acero); 
			celda.setObstaculo(acero);
		}
	}
	
	public int cantidadFilas(){
		return matriz.length; 
	}
	
	public int cantidadColumnas(){
		return matriz[0].length; 
	}
}