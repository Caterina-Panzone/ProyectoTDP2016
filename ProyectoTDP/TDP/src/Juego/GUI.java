package Juego;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image; 
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 756);
		contentPane = new JPanelFondo();
		Image img = new ImageIcon(getClass().getResource("/Imagenes/Fondo.png")).getImage(); 	
		contentPane.setImage(img); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentJuego = new JPanel(); 
		contentJuego.setLayout(null);
		contentJuego.setBounds(0, 0, 1213, 756);
		contentJuego.setOpaque(false);
	
		contentPane.add(contentJuego); 
		
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
	
	public void traerFrenteDisparo(JLabel imagen){
		contentJuego.setComponentZOrder(imagen,3);
	}
	
	public void traerFrentePower(JLabel imagen){
		contentJuego.setComponentZOrder(imagen,1); 
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