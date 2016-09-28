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
	
	public Mapa(String archivo, GUI gui){
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
							obst = new Aguila(celda);
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
		boolean agarre;
		
		Tanque tanque = vieja.getTanque();
		nueva.setTanque(tanque);
		vieja.setTanque(null);
		tanque.setCelda(nueva);
		
		agarre= tanque.actuar(nueva.getPower());
		if (agarre)
			nueva.setPower(null);
	
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