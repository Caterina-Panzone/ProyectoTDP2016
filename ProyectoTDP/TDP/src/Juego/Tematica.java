package Juego;

public final class Tematica{
	protected static String miTematica = null;
	protected static Sonido sonido;
	
	public static void setTematica(String tematica){
		if(sonido!=null){
			sonido.apagar();
		}
		miTematica = tematica;
		sonido=new Sonido();
	}
	
	public static String getTematica(){
		return miTematica; 
	}
}
