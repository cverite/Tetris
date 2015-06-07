package src;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.WindowConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.Image; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Cette classe permet la création d'une fenetre à laquelle on associe les 
 * pannels et les jeux des 2 joueurs. Cette classe permet aussi la gestion des 
 * événements qui permette de controler les jeux.
 * @author Aur�lie OTTAVI et C�line VERITE
 */
public class Fenetre extends JFrame implements ActionListener,KeyListener,ItemListener{
	
        /**
	 * Port sur lequel ont se connect pour le réseau
	 */
        int port=44444;
        
        /**
	 * Thread permettant de jouer la music d tetris
	 */
	MonThread son;
	

        /**
	 * Ecran du joueur 1
	 */
	Ecran ecranJeu;
        
       /**
	 * Ecran du joueur 2
	 */
	Ecran ecranJeu2;
        
       /**
	 * Pannel du joueur 1
	 */
	Pannel pannel;
        
       /**
	 * Pannel du joueur 2
	 */
	Pannel pannel2;
        
        /**
	 * Menu pour quitter 
	 */
	JMenuItem quitter;
        
        /**
	 * Menu pour enlever / remettre le son 
	 */
	JCheckBox sonEnable;
        
        /**
	 * Menu pour lancer un nouveau jeu 1 joueur 
	 */
	JMenuItem nouveau;
        
       /**
	 * Menu pour lancer un nouveau jeu 2 joueurs 
	 */
	JMenuItem nouveau2j;
        
       /**
	 * Menu pour lancer un nouveau jeu 2 joueurs en réseau
	 */
        JMenuItem nouveauReseau;
        
        /**
	 * Fenetre pour indiquer au serveur qu'il doit attendre la connexion d'un client
	 */
        JFrame win;
        
       /**
	 * Variable permettant de savoir si un nouveau jeu à été cré
	 */
	boolean nouveauJeu ;
        
        /**
	 * Variable permettant de savoir si le jeu crée est en réseau
	 */
	boolean jeuReseau ;
       /**
	 * Variable permettant de sauvegarder si le joueur desir jouer avec ou sans son
	 */
	boolean notSon;
        
       /**
	 * Jeu 1 joueur / ou jeu 2 joueurs en reseau
	 */
	Jeu jeu;
        
       /**
	 * Jeu 2 joueur
	 */
	Jeu2j jeu2j;
        
       /**
	 * Variable permettant de savoir si un joueur a préssé la touche pause 
         * de manière à savoir si l'on doit transmettre la pause par le réseau
	 */
        boolean PisPressed=false;
        
        /**
	 * Variable permettant de savoir quand l'on doit transmettre le tableau 
         * des pièces spéciales qui ont été choisie par le serveur
         */
        boolean envoiChoixForme=false;

        
       /**
	 * Constructeur de la classe
	 * @param title chaine de caractère correspondant au titre de la fenetre
	 */
	public Fenetre(String title){
            
                /* Creation d'une JFrame aux dimension 1050*500*/
		super(title);
		this.setPreferredSize(new Dimension(1050,500));
                
                /* On associe un KeyListener*/
                this.addKeyListener(this);
                
                /* Pour que le programme quitte si l'on ferme la fenetre */
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();


	/**********************************************************************
			Creation du menu  
	***********************************************************************/
	
		JMenuBar menu = new JMenuBar();
                
		JMenu menu1 = new JMenu("Fichier");
		nouveau = new JMenuItem("Nouveau Jeu");       
		quitter = new JMenuItem("Quitter");
		nouveau2j= new JMenuItem("Nouveau Jeu 2 joueurs");
                nouveauReseau= new JMenuItem("Nouveau Jeu Réseau");
		sonEnable= new JCheckBox("Enlever Son");
		sonEnable.setSelected(false);
		
		quitter.addActionListener(this);
		sonEnable.addItemListener(this);
		nouveau.addActionListener(this);
		nouveau2j.addActionListener(this);
                nouveauReseau.addActionListener(this);
                
		menu1.add(nouveau);
		menu1.add(nouveau2j);
                menu1.add(nouveauReseau);
		menu1.add(sonEnable);
		menu1.add(quitter);            
		menu.add(menu1);
		
		this.setJMenuBar(menu);
		
		
	/************************************************************************
			Pour changer l'icone de la fenetre
	***********************************************************************/		
		Image icone = Toolkit.getDefaultToolkit().getImage("src/icone1.png");
		this.setIconImage(icone);
                               
		this.setVisible(true);
	}

        
        /**
          * Listener de la checkbox permettant de chosir si l'on veut du son ou pas 
          */
	public void itemStateChanged(ItemEvent e) {
	    
	    Object source = e.getItemSelectable();

	    if (source == sonEnable) {
	    	if(son!=null)son.stop();
			notSon=true;

	    } 

	    if (e.getStateChange() == ItemEvent.DESELECTED){
	    	son=new MonThread();
  			son.start();	
  			notSon=false;
	    }

	}

         /**
          * Listener du menu
          */
	public void actionPerformed(ActionEvent e) {
            
	/********************************************************************
		Pour quitter quand on appuie sur le bouton Quitter 
	********************************************************************/
		
		if(e.getActionCommand().equals("Quitter")){ 
		System.exit(0);}
       
       /********************************************************************
		Pour créer un nouveau jeu réseau
	********************************************************************/
                if(e.getActionCommand().equals("Nouveau Jeu Réseau")){
                    jeuStop();
                    ImageIcon icon = new ImageIcon("src/icone1.png");
                    Object[] possibilities = {"Client", "Serveur"};
                    String s1 = (String)JOptionPane.showInputDialog(this,"Voulez vous etre le serveur ou le client ?","Serveur ou Client ?", JOptionPane.PLAIN_MESSAGE,icon,possibilities,"Serveur");
                    
                    if(s1!=null){
                        if (s1 .equals("Serveur"))  {
                            System.out.println(s1);
                            Serveur S=new Serveur(this);
                            S.start();
                            jeuReseau=true;
                        }
                        if (s1 .equals("Client"))  {
                            System.out.println(s1);
                            String s2 = (String)JOptionPane.showInputDialog(this,"Veuillez entrer l'adresse ip du serveur","Adresse ip",JOptionPane.PLAIN_MESSAGE,icon,null,"");
                             if(s2!=null){
                                Client C=new Client(this,s2);
                                C.start();
                                jeuReseau=true;
                            }
                        } 
                    }
                } 
	
        /********************************************************************
		Pour créer un nouveau jeu 1 joueur
	********************************************************************/       
		if(e.getActionCommand().equals("Nouveau Jeu")){

                    jeuStop();    
                    new ChoixForme(1,this);
			
		}
       /********************************************************************
		Pour créer un nouveau jeu 2 joueurs
	********************************************************************/

		if(e.getActionCommand().equals("Nouveau Jeu 2 joueurs")){
                    jeuStop();   
                    new ChoixForme(2,this);
		}
	}
        
        
        /**
          * Listener des touches du clavier pour gerer le jeu
          */ 
         public void keyPressed(KeyEvent e) 
                 
    {                 
            
	switch (e.getKeyCode()) {
            
            /*Si le joueur appui sur la fleche gauche on gere le deplacement gauche du joueur 1*/
        	case KeyEvent.VK_LEFT  : 
                    
            	if ( pannel.terroriste==0){ 
                    if(pannel2!=null){
                        if(pannel2.terroriste==0 ){
                                if(!pannel.pause && !pannel.perdu)
                                ecranJeu.currentForme.deplacerGauche(ecranJeu);
                                repaint();
                        }
                        else {
                                if(!pannel2.pause && !pannel2.perdu)
                                ecranJeu2.currentForme.deplacerGauche(ecranJeu2);
                                repaint();
                        }
                    }
                    else {
                        if(!pannel.pause && !pannel.perdu)
                            ecranJeu.currentForme.deplacerGauche(ecranJeu);
                            repaint();
                    }
                }
                
        	break;
                
                
                /*Si le joueur appui sur la fleche droite on gere le deplacement droit du joueur 1*/
        	case KeyEvent.VK_RIGHT :
                 
                if (pannel.terroriste==0){ 
            	
                    if(pannel2!=null){
                            if(pannel2.terroriste==0){
                                    if(!pannel.pause && !pannel.perdu)
                                        ecranJeu.currentForme.deplacerDroite(ecranJeu);
                                    repaint();
                            }
                            else {
                                    if(!pannel2.pause && !pannel2.perdu)
                                    ecranJeu2.currentForme.deplacerDroite(ecranJeu2);
                                    repaint();
                           }			
                    }
                    else{
                        if(!pannel.pause && !pannel.perdu)
                            ecranJeu.currentForme.deplacerDroite(ecranJeu);
                        repaint();
                    }
                }
                
        	break;
                
                /*Si le joueur appui sur la fleche du haut on gere la rotation de la piece du joueur 1*/
        	case KeyEvent.VK_UP    : 
                 
                if (pannel.terroriste==0){ 
                	
                    if(pannel2!=null){
                        if(pannel2.terroriste==0){
                		if(!pannel.pause && !pannel.perdu)
                                    ecranJeu.currentForme.tourner(ecranJeu);
                		repaint();
                	}
                	else{
                		if(!pannel2.pause && !pannel2.perdu)
                                    ecranJeu2.currentForme.tourner(ecranJeu2);
	            		repaint();
                	}
                }
                else{
                    if(!pannel.pause && !pannel.perdu)
                	ecranJeu.currentForme.tourner(ecranJeu);
                    repaint();
                }
             }
               
             break;
             
             /*Si le joueur appui sur la fleche du bas on gere le deplacement vers le bas du joueur 1*/
        	case KeyEvent.VK_DOWN  : 
                    
                if (pannel.terroriste==0){ 
            	
                    if(pannel2!=null){
                            if(pannel2.terroriste==0){
                                    if(!pannel.pause && !pannel.perdu)
                                    ecranJeu.currentForme.descendre(ecranJeu);
                                    repaint();
                            }
                            else{
                                    if(!pannel2.pause && !pannel2.perdu)
                                    ecranJeu2.currentForme.descendre(ecranJeu2);
                                    repaint();
                            }
                    }    
                    else{
                        if(!pannel.pause && !pannel.perdu)
                            ecranJeu.currentForme.descendre(ecranJeu);
                        repaint();
                    }
                }
               
		break;
                
                /*Si le joueur appui sur la touche P on gere la pause chez les 2 joueurs */
        	case KeyEvent.VK_P : 
        		
        		pannel.envoip=true;
                   
                    if(pannel2!=null ){
                        if(pannel2.terroriste==0 && pannel.terroriste==0){
                            if(!pannel.pause && !pannel.perdu)  {
                                    pannel.pause=true;
                                    pannel2.pause=true;
                                    repaint();
                                    if(son!=null)
                                    son.suspend() ;
                            }
                            else {
                                    pannel.pause=false;
                                    if(son!=null)
                                    son.resume() ;
                                    pannel2.pause=false;
                            }
                            
                        }
                    }
                    else {
                         if(!pannel.pause && !pannel.perdu)  {
                                    pannel.pause=true;
                                    repaint();
                                    if(son!=null)
                                    son.suspend() ;
                            }
                            else {
                                    pannel.pause=false;
                                    if(son!=null)
                                    son.resume() ;
                            }
                    }
                
              
 		break;

                
                /*Si le joueur appui sur la touche Q on gere le deplacement 
                 gauche du joueur 2 (mode deux joueurs sur un meme ordinateur 
                 uniquement) */
		case KeyEvent.VK_Q  : 
                
                if(pannel2!=null && !jeuReseau){
                    if(!pannel.pause && !pannel.perdu && pannel2.terroriste==0){
                
                	if(pannel.terroriste==0)
                            ecranJeu2.currentForme.deplacerGauche(ecranJeu2);
                	else 
                            ecranJeu.currentForme.deplacerGauche(ecranJeu);
                            repaint();
                    }
                }
                
        	break;
                
                
                /*Si le joueur appui sur la touche D on gere le deplacement droit 
                 du joueur 2 (mode deux joueurs sur un meme ordinateur uniquement) */
        	
        	case KeyEvent.VK_D :
                    
                if(pannel2!=null && !jeuReseau){
                    if(!pannel.pause && !pannel.perdu && pannel2.terroriste==0){
                
                	if(pannel.terroriste==0)
                            ecranJeu2.currentForme.deplacerDroite(ecranJeu2);
                	else 
                            ecranJeu.currentForme.deplacerDroite(ecranJeu);
                            repaint();
                    }
                }
                
        	break;                 
                
                /*Si le joueur appui sur la touche Z on gere lla rotation de la 
                 piece du joueur 2 (mode deux joueurs sur un meme ordinateur uniquement) */
        	
        	case KeyEvent.VK_Z    : 
                    
                    if(pannel2!=null && !jeuReseau){
                    if(!pannel.pause && !pannel.perdu && pannel2.terroriste==0){
                
                	if(pannel.terroriste==0)
                		ecranJeu2.currentForme.tourner(ecranJeu2);
                	else ecranJeu.currentForme.tourner(ecranJeu);
									 repaint();
                    }
                }
                
		break;
       
                /*Si le joueur appui sur la touche S on gere le deplacement vers 
                 le bas du joueur 2 (mode deux joueurs sur un meme ordinateur uniquement) */
        	
        	case KeyEvent.VK_S    : 
                 
                if(pannel2!=null && !jeuReseau){
                if(!pannel.pause && !pannel.perdu && pannel2.terroriste==0){
            
            	if(pannel.terroriste==0)
            		ecranJeu2.currentForme.descendre(ecranJeu2);
            	else ecranJeu.currentForme.descendre(ecranJeu);
								 repaint();
                }
            }

	break;
	    }

        /* On donne l'ordre d'envoyer l'écran de l'adverssaire par le réseau */
      pannel.ecranJeu.envoi=true;
      
	}
    
     /**
     * Inutilisé
     */
    public void keyReleased(KeyEvent e) {}   

    /**
     * Inutilisé
     */
    public void keyTyped(KeyEvent e) {}

   /**
     * Méthode qui permet d'initialiser un jeu en mode 2 joueurs
     */
    public void initJeu2j(){

        /* On arrete le son si il est lancé*/
        if(notSon!=true){
        son=new MonThread();
        son.start();
        }
        
        /* On supprime les association de pannel*/ 
        if(pannel != null)
        this.remove(pannel);
        if(pannel2 != null)
        this.remove(pannel2);
        
        /*On arrete le jeu en cours*/
        if(jeu !=null)jeu.stop();
        if(jeu2j !=null){
        jeu2j.t1.stop();
        jeu2j.t2.stop();
        }
        
        /* On crée un nouveau jeu et on le lance */
        jeu2j=new Jeu2j(this);
        jeu2j.run();
    }

       /**
        * Méthode qui permet d'initialiser un jeu en mode 2 joueurs en reseau
        */
    public void initJeu2jreseau(int nbr){

         /* On arrete le son si il est lancé*/
       if(notSon!=true){
        son=new MonThread();
        son.start();
        }
        
       /*création du deuxième pannel pour la visualisation du jeu de l'adversaire*/
        ecranJeu2 = new Ecran(16,20);
        pannel2=new Pannel(ecranJeu2,this);
        setLayout(null);
        pannel2.setBounds(pannel2.size*(pannel2.ecranJeu.LARGEUR+8),10,500,500);	
        getContentPane().add(pannel2); 
        this.add(pannel2);

        /* On crée un nouveau jeu (on le lanceras plus tard après que le client soit connécté*/
        jeu=new Jeu(this);
        
        /* Si le joueur est le client , cela veut dire qu'il est connécté on 
         peut alors envoyer le tableau des pièces spéciales que le serveur à séléctioné*/
        if(nbr==2) 
        envoiChoixForme=true;

    }

      /**
        * Méthode qui permet d'initialiser un jeu en mode 1 joueur
        */
        public void initJeu1j(){

       /* On arrete le son si il est lancé*/
        if(notSon!=true){
        son=new MonThread();
        son.start();
        }

         /* On supprime les association de pannel*/ 
        if(pannel != null)
        this.remove(pannel);

        if(pannel2 != null)
        this.remove(pannel2);

        /* On crée un nouveau jeu 1 joueur et on le lance */
        jeu=new Jeu(this);
        nouveauJeu=true;
        jeu.start();
    }


    /**
    * Méthode qui permet d'arreter un jeu qui est en cours
    */
    public void jeuStop(){

        if(son != null) son.stop();

        if(jeu2j !=null){
        jeu2j.t1.stop();
        jeu2j.t2.stop();
        }

        if(jeu!=null) jeu.stop();

    }
    
    /**
    * Méthode main qui permet de lancer l'application
    */
                public static void main(String args[]){

                        new Fenetre("Tetris"); 

        }
}