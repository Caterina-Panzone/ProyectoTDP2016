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

public class GUI extends JFrame{
	//Atributos 
	
	private static final long serialVersionUID = 1L;
	protected JPanelFondo contentPane;
	protected Timer timerFinJuego; 
	protected JLabel vida,nivel,puntaje, enemigos;
	protected JLabel muertos[];
	protected JPanel contentPuntaje; 
	protected JPanel contentJuego; 
	protected JPanel contentArboles; 
	protected JPanel contentAgua; 
	protected Logica logica;
	
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
		addKeyListener(new OyenteTeclado()); 
		setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
	    
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1216, 706);
		contentPane = new JPanelFondo();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TimerClass tc = new TimerClass(); 
		timerFinJuego = new Timer(5000,tc); 
		
		crearMenu(); 
	}
	
	protected void crearMenu(){
		JPanel menu = new JPanel(); 
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
		boton.setForeground(Color.LIGHT_GRAY);
		hacerBotonMonotono(boton); 
	}
	
	private void hacerBotonMonotono(JButton boton) {
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
	}
	
	protected void inicializarPanelesJuego(){		
		contentJuego = new JPanel(); 
		contentJuego.setLayout(null);
		contentJuego.setBounds(100, 0, 1116, 706);
		contentJuego.setOpaque(false);
		
		contentArboles = new JPanel();
		contentArboles.setLayout(null);
		contentArboles.setBounds(100, 0, 1116, 706);
		contentArboles.setOpaque(false);
		
		contentAgua = new JPanel();
		contentAgua.setLayout(null);
		contentAgua.setBounds(100, 0, 1116, 706);
		contentAgua.setOpaque(false);
		
		crearPanelPuntaje(); 
		
		contentPane.add(contentArboles);
		contentPane.add(contentJuego); 
		contentPane.add(contentAgua);
		contentPane.add(contentPuntaje); 
		
		logica = new Logica(this);
		
		Image img = new ImageIcon(getClass().getResource("/Imagenes/"+Tematica.getTematica()+"/Fondo.png")).getImage(); 	
		contentPane.setImage(img); 
		
		this.repaint(); 
	}
	
	protected void crearPanelPuntaje(){
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
		
		ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/Vida.png"));
		vida = new JLabel(img);
		vida.setBounds(0,200,100,80);
		vida.setHorizontalAlignment(JLabel.CENTER);
		vida.setVerticalTextPosition(JLabel.CENTER);
		vida.setFont(new Font("Arial",1,22));
		
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
		
		contentPuntaje.add(nivel);
		contentPuntaje.add(puntaje);
		contentPuntaje.add(vida); 	
		contentPuntaje.add(enemigos);
	}
	
	public void gameOver(){
		logica = null; 
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
				
		contentPane.removeAll(); 
//		this.remove(contentPane);
		
		contentPuntaje = null; 
		contentJuego = null; 
		contentArboles = null; 
		contentAgua = null; 
		
//		contentPane = new JPanelFondo();
//		setContentPane(contentPane);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Image img = new ImageIcon(getClass().getResource("/Imagenes/GameOver.png")).getImage(); 		
		contentPane.setImage(img);
//		contentPane.setLayout(null);
		
		contentPane.add(GameOver); 
		
		this.repaint(); 
		
		timerFinJuego.start(); 
		
	}
	
	protected void jugadorDispara(){
		logica.jugadorDispara();
	}
	
	protected void cambiarNivelJugador(){
		logica.aumentarNivelJugador();
	}
	
	protected void mover(KeyEvent key){	
		logica.moverJugador(key.getKeyCode());
		this.repaint();
	}
	
	public Component add (Component comp){
//		Component componente = contentJuego.add(comp); 
//		contentJuego.setComponentZOrder(componente,0);
		return contentJuego.add(comp); 
	}
	
	public void ubicarAgua(JLabel imagen){
		contentAgua.add(imagen); 
	}
	
	public void ubicarArbol(JLabel imagen){
		contentArboles.add(imagen);
	}
	
	public void ubicarPower(JLabel imagen){
		contentArboles.add(imagen); 
		contentArboles.setComponentZOrder(imagen, 0);
	}
	
	public void traerFrenteDisparo(JLabel imagen){
		contentJuego.setComponentZOrder(imagen,0);
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
			crearMenu(); 
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
		public void keyPressed(KeyEvent arg0) {
			if(logica!=null){
				if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
					jugadorDispara();	
				} else {
					if (arg0.getKeyCode()==KeyEvent.VK_N){
						cambiarNivelJugador(); 
					}else{
						mover(arg0);
					}
				}
			}
		}
	}
}