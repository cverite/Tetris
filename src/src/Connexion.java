package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Cette classe permet de gérer la connexion entre le serveur et le client.
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Connexion extends Thread{
	/**
	 * Flux que l'on reçoit du Client
	 */
	private BufferedReader d;
	
	/**
	 * Flux dans lequel on envoi vers le Client
	 */
	private PrintStream out;
	
	/**
	 * jeuServeur 
	 */
	private JeuServeur jeuServeur;
	
	/**
	 * Constructeur de la classe
	 * @param client le socket avec lequel on communique avec le client
	 * @param jeuServeur classe ou est cr��e cette classe 
	 */
	public Connexion(Socket client, JeuServeur jeuServeur){
		try{  
			this.jeuServeur=jeuServeur;       
	          d = new BufferedReader(new InputStreamReader(client.getInputStream()));
	          out=new PrintStream(client.getOutputStream());
		}
		
		catch(IOException e){
			System.err.println("Erreur "+e);
			System.exit(1);
		}
		this.start();
	}
	            
	/**
	 * Cette m�thode permet l'envoi des données au client
	 */
	public void run(){
		for(;;){
		
			//On cr�e une classe pour envoyer les données
			EnvoiServeur e=new EnvoiServeur(out,jeuServeur);
			e.start();
			
			//On cr�e une classe pour recevoir les donn�es
			Ecoute ec=new Ecoute(true,d,jeuServeur.getFenetre());
			ec.start();
                        
			//On lance la thread de jeu
			jeuServeur.getFenetre().jeu.start();

				while(true){
					if(jeuServeur.getRequete()==null){
					//si la requete n'est pas nulle c'est que l'on a pas encore envoy� son contenu donc on ne le remplace pas
                               
                        	if(jeuServeur.getFenetre().envoiChoixForme==true){         // envoi du tableau de pieces spéciales avec lesquelles on joue                     
                        		jeuServeur.setRequete(jeuServeur.getFenetre().jeu.SpetoString());
                        		jeuServeur.getFenetre().envoiChoixForme=false; 
                                  }
                            else if(jeuServeur.getFenetre().jeu.envoicorriace==true){//envoi d'une piece corriace a l'adversaire
                            	jeuServeur.setRequete("c");
                            	jeuServeur.getFenetre().jeu.envoicorriace=false; }
                            
                            else if(jeuServeur.getFenetre().jeu.lievre==true){//envoi la destruction d'une piece lievre à l'adversaire
                            	jeuServeur.setRequete("L");
                            	jeuServeur.getFenetre().jeu.lievre=false;
                                }
                        	
                            else if(jeuServeur.getFenetre().jeu.superCassePied==true){//envoi la destruction d'une piece super casse-pied à l'adversaire
                            	jeuServeur.setRequete("S");
                            	jeuServeur.getFenetre().jeu.superCassePied=false;
                                }
                                     
                            else if(jeuServeur.getFenetre().jeu.O==true){//envoi d'une piece 'O' à l'adversaire
                            	jeuServeur.setRequete("O");
                            	jeuServeur.getFenetre().jeu.O=false;
                                }
                            
                            else if(jeuServeur.getFenetre().jeu.pickpocket==true){//envoi un demande à l'adversaire pour qu'il tranqmette sa prochaine piece 
                            	jeuServeur.setRequete("V");
                            	jeuServeur.getFenetre().jeu.pickpocket=false;
                                }

                            else if (jeuServeur.getFenetre().jeu.envoiForme==true){// envoi de sa prochaine piece a l'adversaire
                            	jeuServeur.setRequete("V"+jeuServeur.getFenetre().ecranJeu.formeSuivante.toString());    
                            	jeuServeur.getFenetre().ecranJeu.newForme();
                            	jeuServeur.getFenetre().jeu.envoiForme=false;
                                }
                                  
                            else if(jeuServeur.getFenetre().jeu.echangiste1==true){// envoi de l'écran et de la forme courrante en cas de piece �changiste detruite et demande d'nvoi du jeu de l'autre
                                  jeuServeur.setRequete("E"+jeuServeur.getFenetre().ecranJeu.currentForme.getNbrElement()+jeuServeur.getFenetre().ecranJeu.currentForme.toString()+jeuServeur.getFenetre().ecranJeu.toString());
                                  jeuServeur.getFenetre().jeu.echangiste1=false;
                                  }

                            else if(jeuServeur.getFenetre().jeu.echangiste2==true){//envoi de l'écran et de la forme courrante en cas de piece échangiste detruite
                                  jeuServeur.setRequete("e"+jeuServeur.getFenetre().jeu.ecranJeuTemp.toString());  
                                  jeuServeur.getFenetre().jeu.echangiste2=false;
                                  }
                            else if(jeuServeur.getFenetre().pannel.perdu==true)jeuServeur.setRequete("P");//envoi de la perte de la partie
                           
                            else if(jeuServeur.getFenetre().pannel.gagne==true)jeuServeur.setRequete("G");//envoi é l'adversaire qu'il a gagn�
                            
                            else if(jeuServeur.getFenetre().pannel.envoip==true) {jeuServeur.setRequete("p");}//envoi de la mise en pause de la partie
                            
                            else if(jeuServeur.getFenetre().pannel.ecranJeu.envoi==true ){// envoi de l'écran à l'adversaire
                                   jeuServeur.setRequete(jeuServeur.getFenetre().pannel.ecranJeu.formeSuivante.getNbrElement()+jeuServeur.getFenetre().pannel.ecranJeu.formeSuivante.toString()+ jeuServeur.getFenetre().pannel.ecranJeu.toString());    
                                   jeuServeur.getFenetre().pannel.ecranJeu.envoi=false; 
                                   }

					}

				}
	
		}
				
	}
	
}
