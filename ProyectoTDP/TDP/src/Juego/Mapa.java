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
	
	public Mapa(String archivo, GUI gui, Logica logica){
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
						gui.add(obst.getImagenActual());
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
		
		tanque.actuar(nueva.getPower());
	}
	
	public void concretarMovimientoDisparo(Celda vieja){
		Disparo disparo = vieja.getBala();
		int fila = vieja.getFila(); 
		int columna = vieja.getColumna(); 
		Celda nueva = null; 
		char direccion = disparo.getDireccion(); 
		
		switch(direccion){
			case 'I':
			{
				if(columna-1>=0)
					nueva = getCelda(fila, columna-1); 
				else {
					disparo.destruirse(); 
				}
			    break; 
		    }
		case 'D': 
			{
				if(columna+1<cantidadColumnas())
					nueva = getCelda(fila, columna+1); 
				else {
					disparo.destruirse(); 
				}
				break; 
		    }
		case 'B': 
			{
				if(fila+1<cantidadFilas())
					nueva = getCelda(fila+1, columna); 
				else {
					disparo.destruirse();  
				}
				break; 
			}
		case 'A': 
			{
				if(fila-1>=0)
					nueva = getCelda(fila-1, columna); 
				else {
					disparo.destruirse();  
				}
				break; 
			}
		}
		
		vieja.setBala(null);
		
		if(nueva!=null){
			
			if(nueva.getObstaculo()!=null && nueva.getObstaculo().atraviesanDisparos()){
				if(nueva.getBala()==null){
					if(nueva.getTanque()==null){
						nueva.setBala(disparo); 
						disparo.setCelda(nueva);
					}
					else{
						nueva.getTanque().recibirGolpe(); 
					}
				}
				else{
					nueva.getBala().destruirse(); 
					disparo.destruirse(); 
				}
			}
			else{
				//Ver lo de la pared de acero. 
				if(nueva.getObstaculo()!=null){
					nueva.getObstaculo().recibirGolpe();
					disparo.destruirse(); 
				}
			}
		}
	}
	
	
	
	//Consultas
	
	public Celda getCelda(int fila, int columna){
		return matriz[fila][columna]; 
	}
	
	public int cantidadFilas(){
		return matriz.length; 
	}
	
	public int cantidadColumnas(){
		return matriz[0].length; 
	}
}