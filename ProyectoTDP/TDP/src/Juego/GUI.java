package Juego;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.util.List;
import Obstaculos.*;

public class GUI extends JFrame{
	//Atributos 
	
	private static final long serialVersionUID = 1L;
	protected JPanelFondo contentPane;
	protected Timer timerFinJuego; 
	protected JLabel vida,nivel,puntaje, enemigos;
	protected JLabel muertos[];
	protected JPanel contentPuntaje; 
	protected JPanel contentJuego; 
	protected JPanel contentPower; 
	protected JPanel menu; 
	protected Logica logica;
	protected boolean tecladoHabilitado; 
	
	protected boolean puedoDisparar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		addKeyListener(new OyenteTeclado(this));   
		setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
		habilitarTeclado();  	
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1216, 706);
		puedoDisparar=true;
		contentPane = new JPanelFondo();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TimerClass tc = new TimerClass(); 
		timerFinJuego = new Timer(2500,tc); 
		
		crearMenu(); 
	}
	
	protected void crearMenu(){
		menu = new JPanel(); 
		menu.setBounds(0, 0, 1216, 706);
		menu.setOpaque(false);
		menu.setLayout(null); 
		
		JPanel BotonesJuego = new JPanel(); 
		BotonesJuego.setBounds(0, 450, 1216, 100);
		BotonesJuego.setOpaque(false); 
		BotonesJuego.setLayout(new GridLayout(0,3));
		
		OyenteJugar oyJug = new OyenteJugar(); 
		JButton jugar = new JButton("Jugar");
		jugar.addMouseListener(oyJug); 
		caracterizarBoton(jugar);
		
		OyenteControles oyControles = new OyenteControles(); 
		JButton controles = new JButton("Controles");
		controles.addMouseListener(oyControles); 
		caracterizarBoton(controles);
		
		OyenteSalir oySalir = new OyenteSalir(this); 
		JButton salir = new JButton("Salir");
		salir.addMouseListener(oySalir); 
		caracterizarBoton(salir);
		
		BotonesJuego.add(jugar); 
		BotonesJuego.add(controles); 
		BotonesJuego.add(salir); 
		
		menu.add(BotonesJuego); 
		
		Image inicio = new ImageIcon(getClass().getResource("/Imagenes/Inicio.png")).getImage();
		contentPane.setImage(inicio);
		contentPane.add(menu); 
		
		this.repaint(); 
	}
	
	private void caracterizarBoton(JButton boton){
		boton.setOpaque(true);
		boton.setHorizontalAlignment(JLabel.CENTER);
		boton.setVerticalTextPosition(JLabel.CENTER);
		boton.setFont(new Font("Arial",1,26));
		boton.setForeground(Color.WHITE);
		hacerBotonMonotono(boton); 
	}
	
	private void hacerBotonMonotono(JButton boton) {
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
	}
	
	protected void inicializarPanelesJuego(){	
		habilitarTeclado();  	
		inicializarPaneles();	
		logica = new Logica(this);
		cambiarFondo();
		this.repaint(); 
	}
	
	protected void inicializarPaneles(){
		inicializarPanelesAux(); 
		crearPanelPuntajeJuego(); 
	}
	
	protected void inicializarPanelesBonus(int puntos){
		habilitarTeclado();  	
		inicializarPanelesAux();	
		crearPanelPuntajeGeneral(); 
		contentPane.add(contentPuntaje); 
			
		logica = new LogicaBonus(this,puntos);
		cambiarFondo();
		this.repaint();
	}
	
	protected void inicializarPanelesAux(){
		contentJuego = new JPanel(); 
		contentJuego.setLayout(null);
		contentJuego.setBounds(100, 0, 1116, 706);
		contentJuego.setOpaque(false);
		
		contentPower = new JPanel();
		contentPower.setLayout(null);
		contentPower.setBounds(100, 0, 1116, 706);
		contentPower.setOpaque(false);
		
		contentPane.add(contentPower);
		contentPane.add(contentJuego);
	}
	
	protected void crearPanelPuntajeJuego(){
		crearPanelPuntajeGeneral(); 
				
		enemigos = new JLabel(); 
		enemigos.setBounds(0,300,100,350);
		enemigos.setLayout(new GridLayout(8,2));
		muertos = new JLabel[16]; 
		
		for(int i=0; i<16; i++){
			muertos[i] = new JLabel();
			muertos[i].setHorizontalAlignment(JLabel.CENTER);
			muertos[i].setVerticalTextPosition(JLabel.CENTER);
			muertos[i].setIcon(new ImageIcon(getClass().getResource("/Imagenes/calabera.png")));
			enemigos.add(muertos[i]);
		}
			
		contentPuntaje.add(enemigos);
		contentPane.add(contentPuntaje);
	}
	
	protected void crearPanelPuntajeGeneral(){
		contentPuntaje = new JPanel(); 
		contentPuntaje.setLayout(null); 
		contentPuntaje.setOpaque(false);
		contentPuntaje.setBounds(0,0,100,675);
		contentPuntaje.setBorder(BorderFactory.createLineBorder(Color.black,3));
		
		nivel = new JLabel();
		nivel.setBounds(0,0,100,100);
		nivel.setHorizontalAlignment(JLabel.CENTER);	
		
		puntaje = new JLabel(); 
		puntaje.setBounds(0,100,100,80);
		puntaje.setHorizontalAlignment(JLabel.CENTER);
		puntaje.setVerticalTextPosition(JLabel.CENTER);
		puntaje.setFont(new Font("Arial",1,15));
		puntaje.setOpaque(true);
		puntaje.setForeground(Color.WHITE);
		puntaje.setBackground(Color.BLACK);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/Vida.png"));
		vida = new JLabel(img);
		vida.setBounds(0,200,100,80);
		vida.setHorizontalAlignment(JLabel.CENTER);
		vida.setVerticalTextPosition(JLabel.CENTER);
		vida.setFont(new Font("Arial",1,22));
		
		contentPuntaje.add(nivel);
		contentPuntaje.add(puntaje);
		contentPuntaje.add(vida); 
	}
	
	public void gameOver(){
		String puntos = puntaje.getText(); 
				
		JLabel score = new JLabel();
		score.setBounds(0,550,1216, 40);
		score.setForeground(Color.WHITE);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setFont(new Font("Arial",1,22));
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/"+Tematica.getTematica()+"/Jugador1Derecha.gif"));
		score.setIcon(imagen);
		score.setText(puntos);
		
		JPanel GameOver = new JPanel();
		GameOver.setOpaque(false);
		GameOver.setBounds(0, 0, 1216, 706);
		GameOver.add(score);
		
		contentPuntaje = null; 
		contentJuego = null; 
		contentPower = null; 
		
		contentPane.removeAll();
		
		Image img = new ImageIcon(getClass().getResource("/Imagenes/GameOver.png")).getImage(); 		
		contentPane.setImage(img);
		
		contentPane.add(GameOver); 	
		
		repaint(); 
		timerFinJuego.start(); 
	}
	
	public void reiniciarPanelesJuego(){
		contentPuntaje.removeAll();
		contentJuego.removeAll(); 
		contentPower.removeAll(); 
		contentPane.removeAll(); 
		
		contentPuntaje = null; 
		contentJuego = null; 
		contentPower = null; 
		
		cambiarFondo();	
		this.repaint(); 
	}
	
	protected void jugadorDispara(){
		logica.jugadorDispara();
	}
	
//  ELIMINAR
	
//	protected void cambiarNivelJugador(){
//		logica.aumentarNivelJugador();
//	}
	
	protected void mover(KeyEvent key){	
		logica.moverJugador(key.getKeyCode());
		this.repaint();
	}
	
	public void deshabilitarTeclado(){
		tecladoHabilitado = false; 
	}
	
	public void habilitarTeclado(){
		tecladoHabilitado = true; 
	}
	
	public void habilitarDisparo(){
		puedoDisparar=true;
	}
	
	public Component add (Component comp){
		return contentJuego.add(comp); 
	}
	
	public void cambiarFondo(){
		Image img = new ImageIcon(getClass().getResource("/Imagenes/"+Tematica.getTematica()+"/Fondo.png")).getImage(); 		
		contentPane.setImage(img);
	}
	
	public void ubicarBosque(List<Obstaculo> bosque){
		for(int i=0; i<bosque.size(); i++){
			contentJuego.add(bosque.get(i).getImagenActual(),0);		
		}
	}
	
	public void ubicarPower(JLabel imagen){
		contentPower.add(imagen); 
		contentPower.setComponentZOrder(imagen, 0);
	}
	
	public void traerFrenteDisparo(JLabel imagen){
		contentJuego.setComponentZOrder(imagen,logica.getcantidadBosques());
	}
	
	public void setNivel(int nivel){
		ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/Nivel"+nivel+".png"));
		this.nivel.setIcon(img); 
	}
	
	public void setVida(int vida){
		this.vida.setText(" "+vida); 
	}
	
	public void setPuntaje(int puntaje){
		this.puntaje.setText(" "+puntaje+" ");
	}
	
	public void aumentarEnemigosDestruidos(int i){
		if(i<=16){
			muertos[16-i].setIcon(null); 
		}
	}
	
	public void resetearMapa(){
		logica.resetearMapaELIMINAR(); 
	}

	private class JPanelFondo extends JPanel{ 
		private static final long serialVersionUID = 1L;
		private Image image = null; 
	
		protected void paintComponent(Graphics g) { 
			Graphics2D g2 =(Graphics2D) g; 
			if(getImage()!=null){ 
				g2.drawImage(getImage(), 0, 0, getWidth(), getHeight(), null); 
			} 
		} 
	
		public Image getImage() { 
			return image; 
		} 
	
		public void setImage(Image image) { 
			this.image = image; 
		} 
	}
	
	private class TimerClass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			timerFinJuego.stop();
			contentPane.removeAll(); 
			contentPane.setLayout(null);
			//crearMenu(); 
			inicializarPanelesJuego(); 
		}
	}
	
	private class OyenteJugar extends MouseAdapter{
		public void mouseClicked(MouseEvent arg0){
			contentPane.removeAll(); 
			inicializarPanelesJuego(); 
		}
	}
	
	private class OyenteControles extends MouseAdapter{
		public void mouseClicked(MouseEvent arg0){
			JFrame controles = new JFrame("Controles");
			controles.setSize(700, 400);
			controles.setLayout(null);
			controles.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			controles.setVisible(true);
			controles.setResizable(false);
			
			JPanelFondo contentPane = new JPanelFondo();
			contentPane.setImage(new ImageIcon(this.getClass().getResource("/Imagenes/Instrucciones.png")).getImage());
			contentPane.setLayout(null);
			contentPane.setSize(700, 400);
			controles.setContentPane(contentPane);
			
			OyenteSalir oySalir = new OyenteSalir(controles); 
			JButton botonAtras = new JButton("Atr�s");
			botonAtras.addMouseListener(oySalir); 
			botonAtras.setBounds(50,300,150,30); 
			caracterizarBoton(botonAtras);

			contentPane.add(botonAtras);
		}
	}
	
	private class OyenteSalir extends MouseAdapter{
		protected JFrame gui; 
		
		public OyenteSalir(JFrame gui){
			this.gui = gui; 
		}
		
		public void mouseClicked(MouseEvent arg0){
			gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	private class OyenteTeclado extends KeyAdapter{
		protected GUI  gui;
		
		public OyenteTeclado(GUI gui){
			this.gui = gui; 
		}
		
		private boolean esFlecha(KeyEvent e){
			return (e.getKeyCode()==KeyEvent.VK_UP || 
					e.getKeyCode()==KeyEvent.VK_DOWN ||
					e.getKeyCode()==KeyEvent.VK_LEFT || 
					e.getKeyCode()==KeyEvent.VK_RIGHT);
		}
		
		public void keyPressed(KeyEvent arg0) {
			if(logica!=null && tecladoHabilitado){
				if(arg0.getKeyCode()==KeyEvent.VK_SPACE && puedoDisparar){
					puedoDisparar=false;
					ContadorTiempoDisparo ctd=new ContadorTiempoDisparo(gui);
					ctd.start();
					jugadorDispara();	
				} else {
					if (arg0.getKeyCode()==KeyEvent.VK_N){
						resetearMapa(); 
						//cambiarNivelJugador(); 
					}else if(esFlecha(arg0)){
							mover(arg0);
						}
				}
			}
		}
	}
}