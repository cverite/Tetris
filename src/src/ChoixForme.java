package src;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * Cette classe permet la sélection des piéces spéciales à utiliser pour la partie que l'on vient de lancer
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class ChoixForme extends JFrame implements ActionListener,WindowListener,MouseListener {
	private static final long serialVersionUID = 1L;
	
        /**
         * nb de joueur(1 pour un joueur ,2 pour 2joueurs, 3 pour 2 joueurs en réseau)
         */
        private int nb;

        /**
         * Fenetre de l'application
         */
        private Fenetre f;
        
        //on a une checkbox par piéce
        private JCheckBox lievre = new JCheckBox("Le Lièvre", true);
        private JCheckBox voisinKiller = new JCheckBox("La Voisin killer", true);
        private JCheckBox mamanCoriace = new JCheckBox("La Maman Coriace", true);
        private JCheckBox mamanSuperCassePied = new JCheckBox("La Maman Super Casse-pied", true);
        private JCheckBox tortue = new JCheckBox("La Tortue", true);
        private JCheckBox terroriste = new JCheckBox("La Terroriste", true);
        private JCheckBox echangiste = new JCheckBox("L'�changiste", true);
        private JCheckBox pickpocket = new JCheckBox("La Pickpocket",true);
        private JCheckBox mamanO = new JCheckBox("La maman 'O'", true);
        private JCheckBox ligneKiller = new JCheckBox("La Ligne Killer", true);
        private JCheckBox cassePied = new JCheckBox("La Casse-pied", true);

   /**
    * Constructeur
    * @param nbJoueur nb de joueur(1 pour un joueur ,2 pour 2joueurs, 3 pour 2 joueurs en réseau)
    * @param f Fenetre de l'aplication
    */
	public ChoixForme(int nbJoueur,Fenetre f){
		super();
                this.f=f;
                if(nbJoueur==1)
                this.setLocation(400,250);
                else this.setLocation(300,250);
		nb = nbJoueur;
		this.setResizable(false);
		this.setLayout(null);
		this.setIconImage(createImageIcon("images/icone1.png").getImage());
		 ((JComponent) this.getContentPane()).setBorder(BorderFactory.createTitledBorder(
          "Choix des pi�ces sp�ciales"));
		// this.setIconImage(image);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		/**************************************************************************************
			Si l'on joue à un joueur
		 **************************************************************************************/
		
		if(nbJoueur == 1){
			this.setSize(200, 300);                          

                        
			tortue.setBorderPainted(false);
			
			Insets insets = this.getInsets();
			Dimension size = tortue.getPreferredSize();
			tortue.setBounds(60 + insets.left, 25 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = voisinKiller.getPreferredSize();
			voisinKiller.setBounds(60 + insets.left,75 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = ligneKiller.getPreferredSize();
			ligneKiller.setBounds(60 + insets.left, 125 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = cassePied.getPreferredSize();
			cassePied.setBounds(60 + insets.left, 175 + insets.top,
			             size.width, size.height);
			
			
			
			this.add(tortue);
			this.add(voisinKiller);
			this.add(ligneKiller);
			this.add(cassePied);
		
		JPanel buttons = new JPanel();
		JButton ok = new JButton("OK",createImageIcon("images/petit_icone1.png"));
		ok.setActionCommand("ok");
		ok.addActionListener(this);
		
		buttons.setSize(new Dimension(200,90));
		buttons.setLayout(null);
		
		insets = buttons.getInsets();
		size = ok.getPreferredSize();
		ok.setBounds((buttons.getWidth()/2-45) + insets.left, (buttons.getHeight()/2-38) + insets.top,
		             size.width, size.height);
		buttons.setBorder(BorderFactory.createRaisedBevelBorder());
		
		buttons.add(ok);
		
		
		insets = this.getInsets();
		size = buttons.getPreferredSize();
		buttons.setBounds(0 + insets.left, 215 + insets.top,
		             size.width, size.height);
		
		this.add(buttons);
	
		/**************************************************************************************
		Si l'on joue à 	deux joueurs (en réseau ou non) 
		**************************************************************************************/
			
		}else if(nbJoueur == 2 || nbJoueur == 3){
			
			this.setSize(470, 405);
			
                        
			cassePied.setToolTipText("Cette brique s�autod�truit au bout d�un certain temps, laissant un trou dans la grille de jeu. Attention cette brique n�a pas besoin d��tre d�truite pour agir.");		
			lievre.setToolTipText("Cette brique permet d�acc�l�rer la vitesse de descente des pi�ces de l�adversaire.");
			voisinKiller.setToolTipText("Cette brique permet de casser les briques adjacentes.");
			mamanCoriace.setToolTipText("Cette brique permet d�envoyer � l�adversaire une brique qui a besoin d��tre d�truite deux fois pour dispara�tre.");
			mamanSuperCassePied.setToolTipText("Cette brique permet d'ajouter une ligne composée de trou au bas de l'écran de l'adverssaire.");
			tortue.setToolTipText("Cette brique permet de ralentir la vitesse de descente des pi�ces.");
			echangiste.setToolTipText("Cette brique permet d��changer les jeux des joueurs.");
			pickpocket.setToolTipText("Cette brique permet de voler la prochaine pi�ce de l�adversaire.");
			mamanO.setToolTipText("Cette brique permet d�envoyer une brique O � l�adversaire.");
			ligneKiller.setToolTipText("Cette brique permet de supprimer la ligne la plus en dessous m�me si elle n�est pas compl�te.");
			if(nbJoueur == 2) terroriste.setToolTipText("Cette brique permet de prendre le contr�le du jeu de l�adversaire, le temps de poser une pi�ce. Pendant ce temps le jeu du � lanceur � est en pause.");
                        
                        lievre.addMouseListener(this);
			voisinKiller.addMouseListener(this);
			mamanCoriace.addMouseListener(this);
			mamanSuperCassePied.addMouseListener(this);
			tortue.addMouseListener(this);
			echangiste.addMouseListener(this);
			pickpocket.addMouseListener(this);
			mamanO.addMouseListener(this);
                        ligneKiller.addMouseListener(this);
			cassePied.addMouseListener(this);
                        if(nbJoueur == 2)  terroriste.addMouseListener(this);
			
			
			Insets insets = this.getInsets();
			Dimension size = lievre.getPreferredSize();
			lievre.setBounds(60 + insets.left, 25 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = voisinKiller.getPreferredSize();
			voisinKiller.setBounds(60 + insets.left,75 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = mamanCoriace.getPreferredSize();
			mamanCoriace.setBounds(60 + insets.left, 125 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = mamanSuperCassePied.getPreferredSize();
			mamanSuperCassePied.setBounds(60 + insets.left, 175 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = tortue.getPreferredSize();
			tortue.setBounds(60 + insets.left, 225 + insets.top,
			             size.width, size.height);
	
			
			insets = this.getInsets();
			size = echangiste.getPreferredSize();
			echangiste.setBounds(330 + insets.left, 25 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = pickpocket.getPreferredSize();
			pickpocket.setBounds(330 + insets.left, 75 + insets.top,
			             size.width, size.height);
			
			insets = this.getInsets();
			size = mamanO.getPreferredSize();
			mamanO.setBounds(330 + insets.left, 125 + insets.top,
			             size.width, size.height);
			
                        insets = this.getInsets();
			size = ligneKiller.getPreferredSize();
			ligneKiller.setBounds(330 + insets.left, 175 + insets.top,
			             size.width, size.height);
			
                         insets = this.getInsets();
			size = cassePied.getPreferredSize();
			cassePied.setBounds(330 + insets.left, 225 + insets.top,
			size.width, size.height);
                        
                        if(nbJoueur == 2){		
                            insets = this.getInsets();
                            size = terroriste.getPreferredSize();
                            terroriste.setBounds(60 + insets.left,275 + insets.top,
                                         size.width, size.height);
                        }
                        
			this.add(lievre);
			this.add(voisinKiller);
			this.add(mamanCoriace);
			this.add(mamanSuperCassePied);
			this.add(tortue);
			this.add(echangiste);
			this.add(pickpocket);
			this.add(mamanO);
                        this.add(ligneKiller);
			this.add(cassePied);
                        if(nbJoueur == 2)this.add(terroriste);
                        
			JPanel buttons = new JPanel();
			JButton ok = new JButton("OK",createImageIcon("images/petit_icone1.png"));
			ok.setActionCommand("ok");
			ok.addActionListener(this);
			
			buttons.setSize(new Dimension(470,90));
			buttons.setLayout(null);
			
			insets = buttons.getInsets();
			size = ok.getPreferredSize();
			ok.setBounds((buttons.getWidth()/2-45) + insets.left, (buttons.getHeight()/2-38) + insets.top,
			             size.width, size.height);
			buttons.setBorder(BorderFactory.createRaisedBevelBorder());
			
			buttons.add(ok);
			
			
			insets = this.getInsets();
			size = buttons.getPreferredSize();
			buttons.setBounds(0 + insets.left, 315 + insets.top,
			             size.width, size.height);
			
			this.add(buttons);
			
			
		}
		
		this.setVisible(true);
	}

	
	/** 
	 * Fonction qui permet de gérer les divers évènement qui peuvent survenir sur la fenetre
	 */
	public void actionPerformed(ActionEvent e) {
		
	if(e.getActionCommand() == "ok"){
          
          if(nb==1){ 
          f.initJeu1j();
          if(tortue.isSelected())f.jeu.pieces_speciales.addElement(Color.GREEN);
          if(ligneKiller.isSelected())f.jeu.pieces_speciales.addElement(Color.BLUE);
          if(voisinKiller.isSelected())f.jeu.pieces_speciales.addElement(Color.ORANGE);
          if(cassePied.isSelected())f.jeu.pieces_speciales.addElement(Color.LIGHT_GRAY);
          
          }
          if(nb==2 ){ 
          f.initJeu2j();
          if(tortue.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.GREEN);
              f.jeu2j.t2.pieces_speciales.addElement(Color.GREEN);
          }
          if(ligneKiller.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.BLUE);
              f.jeu2j.t2.pieces_speciales.addElement(Color.BLUE);
          }
          if(voisinKiller.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.ORANGE);
              f.jeu2j.t2.pieces_speciales.addElement(Color.ORANGE);
          }
          if(lievre.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.YELLOW);
              f.jeu2j.t2.pieces_speciales.addElement(Color.YELLOW);
          }
          if(echangiste.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.DARK_GRAY);
              f.jeu2j.t2.pieces_speciales.addElement(Color.DARK_GRAY);
          }
          if(mamanCoriace.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.CYAN);
              f.jeu2j.t2.pieces_speciales.addElement(Color.CYAN);
          }
          if(terroriste.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.PINK);
              f.jeu2j.t2.pieces_speciales.addElement(Color.PINK);
          }
          if(mamanO.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.WHITE);
              f.jeu2j.t2.pieces_speciales.addElement(Color.WHITE);
          }
          if(pickpocket.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.GRAY);
              f.jeu2j.t2.pieces_speciales.addElement(Color.GRAY);
          }
          if(mamanSuperCassePied.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.MAGENTA);
              f.jeu2j.t2.pieces_speciales.addElement(Color.MAGENTA);
          }
          if(cassePied.isSelected()){
              f.jeu2j.t1.pieces_speciales.addElement(Color.LIGHT_GRAY);
              f.jeu2j.t2.pieces_speciales.addElement(Color.LIGHT_GRAY);
          }
          }
          if(nb==3){
              f.initJeu2jreseau(1);
              if(tortue.isSelected())f.jeu.pieces_speciales.addElement(Color.GREEN);
              if(ligneKiller.isSelected())f.jeu.pieces_speciales.addElement(Color.BLUE);
              if(voisinKiller.isSelected())f.jeu.pieces_speciales.addElement(Color.ORANGE);
              if(cassePied.isSelected())f.jeu.pieces_speciales.addElement(Color.LIGHT_GRAY);
              if(lievre.isSelected())f.jeu.pieces_speciales.addElement(Color.YELLOW);
              if(echangiste.isSelected())f.jeu.pieces_speciales.addElement(Color.DARK_GRAY);
              if(mamanCoriace.isSelected())f.jeu.pieces_speciales.addElement(Color.CYAN);          
              if(mamanO.isSelected())f.jeu.pieces_speciales.addElement(Color.WHITE);
              if(pickpocket.isSelected())f.jeu.pieces_speciales.addElement(Color.GRAY);
              if(mamanSuperCassePied.isSelected())f.jeu.pieces_speciales.addElement(Color.MAGENTA);
              f.win=new JFrame() ;
              f.win.setSize(150,50);
              JLabel label= new JLabel();
              label.setText("En attente du Client...");
              f.win.getContentPane().add(label,BorderLayout.CENTER);
              f.win.setVisible(true);
              f.win.setLocation(380, 300);
          }

          this.setVisible(false);
	}
}

	/**
	 * Permet de creer une icone à partir d'une image
	 * @param path chemin d'accès à l'image
	 * @return une icone
	 */
	protected static ImageIcon createImageIcon(String path) {
            java.net.URL imgURL = ChoixForme.class.getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        }
	
	/**
	 * Permet de charger l'image dont le chemin d'accès est passé en paramettre
	 * @param nombre le chiffre de l'image
	 * @return l'image 
	 */
	public BufferedImage loadImage(String nombre) {

        URL url=null;
        try {
          url = getClass().getClassLoader().getResource(nombre);
          return ImageIO.read(url);
        } catch (Exception e) {
          System.out.println("Impossible de charger l'image " + nombre +" de "+url);
          System.exit(0);
          return null;
        }
      }
		       
		       

	 /**
	 * Fonction qui permet l'affichage de la fenetre
	 */
	public void paint(Graphics g) {
		  super.paint(g);
		  
		  /**************************************************************************************
			Si l'on joue à un joueur
		**************************************************************************************/
		  
		  if(nb == 1){
		  	   BufferedImage tortueImage = loadImage("images/tortue.jpg");
		  	   BufferedImage voisinKillerImage = loadImage("images/voisin.jpg");
		  	   BufferedImage ligneKillerImage = loadImage("images/ligne.jpg");
		  	   BufferedImage cassePiedImage = loadImage("images/casse.jpg");
		
		  	   g.drawImage(tortueImage,25,52, null);
		  	   g.drawImage(voisinKillerImage,25,102, null);
		  	   g.drawImage(ligneKillerImage,25,152, null);
		  	   g.drawImage(cassePiedImage,25,202, null);
		
		/**************************************************************************************
				Si l'on joue à 	deux joueurs (en réseau ou pas) 
		 **************************************************************************************/ 	   
		 
		  }else if(nb == 2 || nb == 3){
			   BufferedImage lievreImage = loadImage("images/lievre.jpg");
		  	   BufferedImage voisinImage = loadImage("images/voisin.jpg");
		  	   BufferedImage mamanCoriaceImage = loadImage("images/mum_coriace.jpg");                     
		  	   BufferedImage echangisteImage = loadImage("images/echange.jpg");
                           BufferedImage mamanOImage = loadImage("images/0.jpg");
		  	   BufferedImage mamanSuperCassePiedImage = loadImage("images/mum_supercasse.jpg");
		  	   BufferedImage tortueImage = loadImage("images/tortue.jpg");
		  	   BufferedImage pickpocketImage = loadImage("images/mum_pick.jpg");
		  	   BufferedImage ligneKillerImage = loadImage("images/ligne.jpg");
                           BufferedImage cassePiedImage = loadImage("images/casse.jpg");
                           BufferedImage terroristeImage = loadImage("images/terro.jpg");
		
		  	   g.drawImage(lievreImage,25,52, null);
		  	   g.drawImage(voisinImage,25,102, null);
		  	   g.drawImage(mamanCoriaceImage,25,152, null);
		  	   g.drawImage(mamanSuperCassePiedImage,25,202, null);
		  	   g.drawImage(tortueImage,25,252, null);
		  	   g.drawImage(echangisteImage,295,52, null);
		  	   g.drawImage(pickpocketImage,295,102, null);
		  	   g.drawImage(mamanOImage,295,152, null);
                           g.drawImage(ligneKillerImage,295,202, null);
                           g.drawImage(cassePiedImage,295,252, null);
                           if(nb == 2) g.drawImage(terroristeImage,25,302, null);

		  }

     }

	
	public void windowActivated(WindowEvent arg0) {
		this.repaint();
		
	}

	
	public void windowClosed(WindowEvent arg0) {
		this.repaint();
		
	}

	
	public void windowClosing(WindowEvent arg0) {
		this.repaint();
		
	}

	
	public void windowDeactivated(WindowEvent arg0) {
		this.repaint();
		
	}

	
	public void windowDeiconified(WindowEvent arg0) {
		this.repaint();
		
	}

	
	public void windowIconified(WindowEvent arg0) {
		this.repaint();
		
	}

	
	public void windowOpened(WindowEvent arg0) {
		this.repaint();
		
	}


	public void mouseClicked(MouseEvent arg0) {
		this.repaint();
	}


	public void mouseEntered(MouseEvent arg0) {
	}


	public void mouseExited(MouseEvent arg0) {
		this.repaint();
	}


	public void mousePressed(MouseEvent arg0) {
		this.repaint();
	}


	public void mouseReleased(MouseEvent arg0) {
		this.repaint();
	}

}