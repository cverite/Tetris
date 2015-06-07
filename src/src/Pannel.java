package src;
import javax.swing.JPanel;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;


/**
 * Cette classe permet l'affichage du Jeu 
 * @author Aurélie OTTAVI et Céline VERITE
 */

public class Pannel extends JPanel {
	
	/**
	 * Ecran que l'ont afcihe dans le pannel
	 */
	Ecran ecranJeu;
	
	/**
	 * Label dans lequel on affiche le score
	 */
	JLabel label;
	
	/**
	 * permet de retenir si le jeu est en pause et d'afficher une image si c'est le cas
	 */
	boolean pause;
	
	/**
	 * permet de retenir si le jeu est perdu et d'afficher une image si c'est le cas
	 */
	boolean perdu;
	
	/**
	 * permet de retenir si le jeu est gagn� et d'afficher une image si c'est le cas
	 */
    boolean gagne;
    
    /**
	 * permet de savoir si la pi�ce que l'on bouge est une pi�ce terroriste
	 */
	int terroriste=0;
	
	/**
	* taille d'un �l�ment (pour l'affichage)
	*/
	int size=22;
	
	/**
	 * Couleur de la pi�ce corriace
	 */
	Color couleurcoriace=new Color(51,51,51);
	
	/**
	 * permet de savoir s'il faut envoyer la mise en pause de la partie à l'autre adversaire
	 */
	boolean envoip;
	
	/**
	* fenetre dans laquelle le pannel doit s'ins�rer
	*/
	Fenetre f;
	

	/**
	 * Constructeur de la classe
	 * @param ecranJeu ecran que l'on affiche dans le pannel
	 * @param f fenetre dans laquelle le pannel doit s'insérer
	 */
	Pannel(Ecran ecranJeu,Fenetre f){
		super();
		this.f=f;
		envoip=false;
		this.ecranJeu=ecranJeu;
		ecranJeu.pannel=this;
		label= new JLabel("Score : 0");
		this.add(label);
		setLayout(null);
		label.setBounds(size*(ecranJeu.LARGEUR+2),size*(7),5*size,size);
		setBounds(10,10,500,500);	
		
		//initialisation des variables
		pause=false;
		perdu=false;
        gagne=false;
	}
        
	/**
	 * cette fonction permet de charger l'image dont le chemin d'acces est pass� en parametre
	 * @param nombre chemin d'acces de l'image
	 * @return l'image chargée
	 */
	public BufferedImage loadImage(String nombre) {
		 URL url=null; 
		 try { 
			 url = getClass().getClassLoader().getResource(nombre); 
			 return ImageIO.read(url);  
			} 
		 catch (Exception e) { 
				 System.out.println("L'image ne peut être chargée " + nombre +" de "+url); 
				 System.out.println("L'erreur est : "+e.getClass().getName()+" "+e.getMessage()); 
				 System.exit(0); 
				 return null;  
			 }  
		 }

    /**
     * cette méthode permet de redimensionner une image
     * @param img l'image a redimensionner
     * @param newW la nouvelle largeur
     * @param newH la nouvelle hauteur
     * @return l'image redimensionnée
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {   
        int w = img.getWidth();   
        int h = img.getHeight();   
        BufferedImage dimg  = new BufferedImage(newW, newH, img.getType());    
        Graphics2D g = dimg.createGraphics();   
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);   
        g.dispose();   

        return dimg;   
    }  
    
	/** 
	 * Permet d'afficher le jeu a l'écran
	 */
	public void paint(Graphics g){


		super.paint(g);

		
	/**********************************************************************
			Affichage de l'ecran
	***********************************************************************/

        Color current;
        int currentX;
        int currentY;
        Element e = new Element();
        int i;


		for(int b=4; b<ecranJeu.HAUTEUR ; b++){
			for(int a=0;a<ecranJeu.LARGEUR;a++){
				e.setCouleur(ecranJeu.ecran[a][b]);
				e.setX(a);
				e.setY(b);
				current =e.getCouleur();
				currentX=e.getX()*size;
				currentY=(e.getY()-4)*size;
			        
                                if (current==Color.GREEN){
                                    BufferedImage img=loadImage("images/tortue.jpg");
                                    /*BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/tortue.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX,currentY, this);
                                }
                                
                                else if (current==Color.PINK){
                                    BufferedImage img=loadImage("images/terro.jpg");  
                                  /*  BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/terro.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }
                                
                                else if (current==Color.YELLOW){
                                  /*    BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/0.jpg");*/
                                    BufferedImage img=loadImage("images/lievre.jpg");                                    
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }
                                
                                 else if (current==Color.ORANGE){
                                    BufferedImage img=loadImage("images/voisin.jpg"); 
                                   /* BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/voisin.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }
                                
                                else if (current==Color.WHITE){
                                    BufferedImage img=loadImage("images/O.jpg");     
                                   /* BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/lievre.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }
                                
                                else if (current==Color.GRAY){
                                    BufferedImage img=loadImage("images/mum_pick.jpg");     
                                   /* BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/mum_pick.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }
                              
                                else if (current==Color.CYAN){
                                    BufferedImage img=loadImage("images/mum_coriace.jpg");     
                                  /*  BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/mum_supercasse.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }
                                
                              else if (current==Color.LIGHT_GRAY){
                                    BufferedImage img=loadImage("images/casse.jpg");                                       
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }

                                else if (current==Color.MAGENTA){
                                    BufferedImage img=loadImage("images/mum_supercasse.jpg");     
                                   /* BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/mum_casse.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }

                                else if (current==Color.DARK_GRAY){
                                    BufferedImage img=loadImage("images/echange.jpg");     
                                   /* BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/echange.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX,currentY, this);
                                }   
                                
                                else if (current==Color.BLUE){
                                    BufferedImage img=loadImage("images/ligne.jpg");     
                                  /*  BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/ligne.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }   
                                
                                else if (current.equals(couleurcoriace)){
                                    BufferedImage img=loadImage("images/coriace.jpg");     
                                  /*  BufferedImage img=loadImage("C:/Documents and Settings/HP_Administrateur/Mes documents/java/tetris 28mars/src/ligne.jpg");*/
                                    g.drawImage(resize(img,size,size), currentX, currentY, this);
                                }   
                                
                                else  if  (current==Color.BLACK){
					g.setColor(current);		
					g.fillRect(currentX,currentY,size,size);
                                }

                                else{	
					g.setColor(current);
					g.fillRect(currentX,currentY,size-1,size-1);
					current=current.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter();
					g.setColor(current);
					
					for (i = 0; i < 2; i++) {
						g.drawLine(currentX+ i, currentY + i, currentX+size-2 - i, currentY + i);
						g.drawLine(currentX+ i, currentY+ i,currentX+ i, currentY+size-2- i);
					}

					current=current.darker().darker().darker().darker();
					g.setColor(current);
					
					for (i = 0; i < 2; i++) {
						g.drawLine(currentX +size-1- i, currentY + i, currentX +size-1 - i, currentY+size-1 - i);
						g.drawLine(currentX  + i, currentY+size-1 - i, currentX +size-1 - i,currentY+size-1 - i);
					}
				}	
			}
		}
	/**********************************************************************
			Affichage du carre de prévisualisation de la piece suivante
	***********************************************************************/
		current=Color.BLACK;
		for(int a=0;a<5;a++)
			for(int b=1;b<6;b++)
				{
				g.setColor(current);		
				g.fillRect((1+a+ecranJeu.LARGEUR)*size,b*size,size,size);	
				}
		
		/*******************************************
			Pour centrer la pièce
		/*******************************************/
		int x,y;
		int lmin,lmax,hmin,hmax;
		lmin=ecranJeu.formeSuivante.getElementAt(0).getX();
		lmax=ecranJeu.formeSuivante.getElementAt(0).getX();
		hmin=ecranJeu.formeSuivante.getElementAt(0).getY();
		hmax=ecranJeu.formeSuivante.getElementAt(0).getY();
		
		for(int j=0;j<ecranJeu.formeSuivante.getNbrElement();j++){
			if(ecranJeu.formeSuivante.getElementAt(j).getX()>lmax) lmax=ecranJeu.formeSuivante.getElementAt(j).getX();
			if(ecranJeu.formeSuivante.getElementAt(j).getX()<lmin) lmin=ecranJeu.formeSuivante.getElementAt(j).getX();
			if(ecranJeu.formeSuivante.getElementAt(j).getY()>hmax) hmax=ecranJeu.formeSuivante.getElementAt(j).getY();
			if(ecranJeu.formeSuivante.getElementAt(j).getY()<hmin) hmin=ecranJeu.formeSuivante.getElementAt(j).getY();
		}
		
		if(ecranJeu.LARGEUR%2==1){		
		x=(5*size-(lmax-lmin+1)*size)/2-size; // coordonnees pour rectifier la position de la pièce
		y=(5*size-(hmax-hmin+1)*size)/2;
		
		}
		
		else {
		x=(5*size-(lmax-lmin+1)*size)/2; // coordonnees pour rectifier la position de la pièce
		y=(5*size-(hmax-hmin+1)*size)/2;
		}
		/*******************************************
			Affichage de la piece suivante 
		/*******************************************/		
		
		for(int j=0;j<ecranJeu.formeSuivante.getNbrElement();j++){
		
			g.setColor(ecranJeu.formeSuivante.getElementAt(j).getCouleur());
			currentX=size*(ecranJeu.formeSuivante.getElementAt(j).getX()+ecranJeu.LARGEUR/2+3)-x+size;
			currentY=size*(ecranJeu.formeSuivante.getElementAt(j).getY()+1)-y+size;
			current=ecranJeu.formeSuivante.getElementAt(j).getCouleur();
			g.fillRect(currentX,currentY,size-1,size-1);
			current=current.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter();
			g.setColor(current);
				
			for (i = 0; i < 2; i++) {
				g.drawLine(currentX+ i, currentY + i, currentX+size-2 - i, currentY + i);
				g.drawLine(currentX+ i, currentY+ i,currentX+ i, currentY+size-2- i);
			}

			current=current.darker().darker().darker().darker();
			g.setColor(current);
			
			for (i = 0; i < 2; i++) {
				g.drawLine(currentX +size-1- i, currentY + i, currentX +size-1 - i, currentY+size-1 - i);
				g.drawLine(currentX  + i, currentY+size-1 - i, currentX +size-1 - i,currentY+size-1 - i);
			}
		}

		if (pause==true ){
            		BufferedImage img=loadImage("images/pause.jpg");
                                    g.drawImage(resize(img,ecranJeu.LARGEUR*size,ecranJeu.LARGEUR*size*121/441),0,130, this);
                 
        	}
                
                if (perdu==true){
         	for(i=0;i<ecranJeu.HAUTEUR;i++){
         		for(int j=0;j<ecranJeu.LARGEUR;j++){
         				g.setColor(Color.BLACK);
					g.fillRect(0,0,size*ecranJeu.LARGEUR,size*(ecranJeu.HAUTEUR-4));
         		}
         	
         	}
                
         	
         
         BufferedImage img=loadImage("images/game over.jpg");
	//		BufferedImage img=loadImage("./game over.jpg");
                                    g.drawImage(resize(img,ecranJeu.LARGEUR*size-20,ecranJeu.LARGEUR*size*242/441),10,((ecranJeu.HAUTEUR-4)*size-(ecranJeu.LARGEUR*size*242/441))/2, this);
               
        	}
                
                else if (gagne==true){
         	for(i=0;i<ecranJeu.HAUTEUR;i++){
         		for(int j=0;j<ecranJeu.LARGEUR;j++){
         				g.setColor(Color.BLACK);
					g.fillRect(0,0,size*ecranJeu.LARGEUR,size*(ecranJeu.HAUTEUR-4));
         		}
         	
         	}
                
         	
         
         BufferedImage img=loadImage("images/gagne.jpg");
	//		BufferedImage img=loadImage("./game over.jpg");
                                    g.drawImage(resize(img,ecranJeu.LARGEUR*size-20,ecranJeu.LARGEUR*size*242/441),10,((ecranJeu.HAUTEUR-4)*size-(ecranJeu.LARGEUR*size*242/441))/2, this);
               
        	}
                
		/***********************************************************************/
	}
	
}   

