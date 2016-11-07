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
import javax.swing.Icon;   

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame{
	//Atributos 
	
	private static final long serialVersionUID = 1L;
	protected JPanelFondo contentPane;
	protected JLabel vida,nivel,puntaje, enemigos;
	protected JLabel muertos[];
	protected JPanel contentPuntaje; 
	protected JPanel contentJuego; 
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
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode()==KeyEvent.VK_SPACE)
					{
					  jugadorDispara();	
					} 
					else {
						if (arg0.getKeyCode()==KeyEvent.VK_N)
						{
						cambiarNivelJugador(); 
						}
						else{
					      mover(arg0);
						}
		}}});
		
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1216, 706);
		contentPane = new JPanelFondo();
		Image img = new ImageIcon(getClass().getResource("/Imagenes/Fondo.png")).getImage(); 	
		contentPane.setImage(img); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentJuego = new JPanel(); 
		contentJuego.setLayout(null);
		contentJuego.setBounds(100, 0, 1116, 706);
		contentJuego.setOpaque(false);
		
//		ImageIcon imgVacia = new ImageIcon(getClass().getResource("/Imagenes/hola.png"));
//		JLabel hola = new JLabel(imgVacia); 
//		JLabel hola2 = new JLabel(imgVacia); 
//		contentJuego.add(hola);
//		contentJuego.add(hola2); 
//		contentJuego.setComponentZOrder(hola,0);
//		contentJuego.setComponentZOrder(hola2,0);
		
		crearPanelPuntaje(); 
	
		contentPane.add(contentJuego); 
		contentPane.add(contentPuntaje); 
		
		logica = new Logica(this);
	}
	
	public Component add (Component comp){
		return contentJuego.add(comp);
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
	
	/*
	 * REINICIA EL JUEGO. 
	 */
	
	public void setInstrucciones(){
		contentPane = new JPanelFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Image img = new ImageIcon(getClass().getResource("/Imagenes/Fondo.png")).getImage(); 	
		contentPane.setImage(img); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logica = new Logica(this);
		
		this.repaint(); 
	}
	
//	public void ubicarObstaculo(JLabel imagen){
//		contentJuego.setComponentZOrder(imagen,1);
//	}
	
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
	
	public void traerFrenteDisparo(JLabel imagen){
		contentJuego.setComponentZOrder(imagen,2);
	}
	
	public void traerFrentePower(JLabel imagen){
		contentJuego.setComponentZOrder(imagen,0); 
	}

	public class JPanelFondo extends JPanel{ 
		private static final long serialVersionUID = 1L;
		private Image image = null; 
		private Icon icon; 
	
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
	
		public Icon getIcon() { 
			return icon; 
		} 
	
		public void setIcon(Icon icon){ 
			this.icon=icon; 
			setImage(((ImageIcon)icon).getImage()); 
		} 
	}
}