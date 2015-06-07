package src;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Cette classe permet la reception des données sur le réseau
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Ecoute extends Thread{

	/**
	 * flux ou l'on va lire les données
	 */
	private BufferedReader in;
	/**
	 * Fenetre de l'application
	 */
	private Fenetre f;

    
	/**
	 * Constructeur de la classe
	 * @param serveur booléen qui indique si cette classe est celle du serveur ou non 
	 * @param in flux où l'on va lire les données
	 * @param f Fenetre de l'application
	 */
	public Ecoute(boolean serveur,BufferedReader in, Fenetre f){
		this.in=in;
                this.f=f; 
	}
	
	/**
	 * Permet d'écouter en boucle les informations reçues sur le réseau et de les traiter.
	 */
	public void run(){
		try{
		while (true){
			String reponse=in.readLine();
			if(reponse==null)
				{System.out.println("Connexion fermée par le serveur");
				break;}
			else {         
                            if(reponse.equals("Spe")){// si on reçoit spe, il faut envoyer le tableau pieces spéciales avec lesquelles on joue      
                                 f.envoiChoixForme=true;
                            }
                            
                            else if(reponse.equals("L")){ // si on recoit "L", c'est que l'autre joueur a détruit une pièce li�vre il faut donc acc�l�r� le jeu
                                 f.jeu.speed-=100;
                            }
                            else if(reponse.equals("S")){//si l'on recoit "S",c'est que l'autre joueur a détruit une pièce super casse-pied, il faut donc ajouter une ligne � trou en bas de l'�cran
                                f.ecranJeu.addLigneATrou();
                            }
                            else if(reponse.equals("O")){ //Si l'on reçoit "O" ,c'est que l'autre joueur a envoyé une pièce O                
                                f.ecranJeu.formeSiO=f.ecranJeu.formeSuivante;
                                f.ecranJeu.formeSuivante=new O(f.ecranJeu);
                            } 
                            else if(reponse.equals("V")){//Si l'on reéoit "V" ,c'est que l'autre joueur a besoin qu'on lui envoit la forme courrante
                               f.jeu.envoiForme=true; 
                            }
                             else if(reponse.equals("P")){//Si l'on reéoit "P" ,c'est que l'autre joueur a perdu
                                 f.jeu.stop();
                                f.pannel2.perdu=true;
                                f.pannel.gagne=true;                  
                                f.repaint();
                             }
                            
                             else if(reponse.equals("p")){//Si l'on reçoit "p" ,c'est que l'autre joueur a appuyé sur pause
                                f.pannel2.pause=!f.pannel2.pause;
                                f.pannel.pause=!f.pannel.pause; 
                                f.repaint();
                            }
                            
                             else if(reponse.equals("c")){//Si l'on reçoit "c" ,c'est que l'autre joueur a envoyé une pi�ce corriace
                                 f.jeu.corriace=true;
                                 f.repaint();
                             }
                            
                             else if(reponse.equals("G")){//Si l'on reçoit "G" ,c'est que l'autre joueur a gagné
                                f.jeu.stop();
                                f.pannel2.gagne=true;                               
                                f.repaint();
                                //fermer connexion reseau
                            }
			else if(reponse.substring(0,1).equals("E")){ //Si l'on reçoit "E" ,c'est que l'autre joueur a détruit une pi�ce echangiste il faut donc envoyer son ecran et sa forme courrante et changer son ecran en celui de l'autre    
				for(int k=0;k<f.ecranJeu.currentForme.getNbrElement();k++)
                            	{
                            		f.ecranJeu.ecran[f.ecranJeu.currentForme.getElementAt(k).getY()][f.ecranJeu.currentForme.getElementAt(k).getX()]=Color.black;
                            	}
                                f.jeu.ecranJeuTemp=f.ecranJeu.currentForme.getNbrElement()+f.ecranJeu.currentForme.toString()+f.ecranJeu.toString();
                                int i=Integer.parseInt(reponse.substring(1,2));
                                f.ecranJeu.currentForme.decode(reponse.substring(2,i*5+2));
                                f.ecranJeu.decode(reponse.substring(i*5+2,reponse.length()));
                                f.repaint();
                                f.jeu.echangiste2=true;  
                            }
                            
           else if(reponse.substring(0,1).equals("e")){//Si l'on reçoit "e" ,c'est que l'autre joueur envoi son ecran et sa forme courrante et qu'il faut changer son ecran avec lui
                   
                            	
                            	int i=Integer.parseInt(reponse.substring(1,2));
                                f.ecranJeu.currentForme.decode(reponse.substring(2,i*6+2));
                                f.ecranJeu.decode(reponse.substring(i*5+2,reponse.length()));
                                f.repaint();
                                
                            }
                             else if(reponse.substring(0,1).equals("V")){
                           
                                 int nbrElement = Integer.parseInt( reponse.substring(1,2));

                            Element element[] =new Element[nbrElement];
                            int i; 
                            Color couleur=Color.BLACK;
                            for (i=0; i<nbrElement ; i++){ 
                                int coul=Integer.parseInt(reponse.substring((6*i)+7,(6*i)+8));              
                                switch (coul){
                                    case 0: couleur=f.ecranJeu.currentForme.vert;break;
                                    case 1: couleur=f.ecranJeu.currentForme.vertbleu;break;
                                    case 2: couleur=f.ecranJeu.currentForme.vertrouge;break;
                                    case 3: couleur=f.ecranJeu.currentForme.bleurougevert;break;
                                    case 4: couleur=f.ecranJeu.currentForme.rouge;break;
                                    case 5: couleur=f.ecranJeu.currentForme.bleu;break;
                                    case 6: couleur=f.ecranJeu.currentForme.bleurouge;break;
                                    case 7: couleur=f.ecranJeu.currentForme.gris42;break;

                                }
                                
                                    element[i]=new Element( Integer.parseInt(reponse.substring((6*i)+3,(6*i)+4)),Integer.parseInt(reponse.substring((6*i)+5,(6*i)+6)),couleur); 

                            }
                            f.ecranJeu.formeSuivante.setNbrElement(nbrElement);
                            f.ecranJeu.formeSuivante.setElement(element);
                            f.pannel.ecranJeu.envoi=true;
                            
                             } 
                            else if(reponse.substring(0,1).equals("C")){
                                
                Color couleurcoriace=new Color(51,51,51);

                int coul=0;
                if(reponse.length()!=1){
		for (int j=1;j<reponse.length();j++){
                   coul=Integer.parseInt(reponse.substring(j,j+2));
                   switch(coul){
                       case 0:f.jeu.pieces_speciales.addElement(Color.PINK);break;
                       case 1:f.jeu.pieces_speciales.addElement(Color.GREEN);break;
                       case 2:f.jeu.pieces_speciales.addElement(Color.BLUE);break;
                       case 3:f.jeu.pieces_speciales.addElement(Color.CYAN);break;
                       case 4:f.jeu.pieces_speciales.addElement(Color.GRAY);break;
                       case 5:f.jeu.pieces_speciales.addElement(Color.MAGENTA);break;
                       case 6:f.jeu.pieces_speciales.addElement(Color.ORANGE);break;
                       case 7:f.jeu.pieces_speciales.addElement(Color.YELLOW);break;
                       case 8:f.jeu.pieces_speciales.addElement(Color.WHITE);break;
                       case 9:f.jeu.pieces_speciales.addElement(Color.DARK_GRAY);break;
                       case 10:f.jeu.pieces_speciales.addElement(Color.LIGHT_GRAY);break;
                       case 11:f.jeu.pieces_speciales.addElement(couleurcoriace);break;    
                   }
                   j++;
                }
               
                }   
                      
                                 
                             f.envoiChoixForme=false;
                            }
                            else if(!reponse.equals("P") && !reponse.equals("") && reponse !=null && !reponse.equals("0")){
				while(f.ecranJeu2==null);// si ecranJeu2 n'est pas encore cr�� on attend qu'il le soit.
                int nbrElement=Integer.parseInt(reponse.substring(0,1));
                f.ecranJeu2.formeSuivante.decode(reponse.substring(1,5*nbrElement+1));
				f.ecranJeu2.decode(reponse.substring(5*nbrElement+1, reponse.length()));
                               
				f.repaint();
                            }
                           
                            
                        }
                        
                        }}
		catch(IOException e){
		System.out.println(e);	
		}
		
		
	}
}
