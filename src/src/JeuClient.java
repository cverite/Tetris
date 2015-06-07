package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe qui permet de gérer les connexion r�seau d'un point de vu client.
 * @author Aur�lie OTTAVI et Céline VERITE
 */
public class JeuClient {

	
    /**
     * adresse ip du serveur
     */
	private InetAddress ip;
	
	 /**
     * Chaine de caractere à envoyer au serveur
     */
	private String requete;
	
	/**
     * Fenetre dans laquelle est crée la classe
     */
    private Fenetre f;
        

	/**
	 * Constructeur de la classe 
	 * @param adresseIP adresse ip du serveur
	 * @param f Fenetre dans laquelle est crée la classe.
	 */
	public JeuClient(String adresseIP, Fenetre f){
        this.f=f;
		requete=null;
		
		try{
			 ip = InetAddress.getByName(adresseIP);	
			}
		catch(UnknownHostException e){
			System.err.println("Le serveur "+adresseIP+" n'existe pas");
			System.exit(1);
		}
		
		try{
			Socket server=new Socket(ip,f.port);
			System.out.println("Connect� � "+adresseIP+" sur le port "+f.port);
			communique(server);
		}
		catch(IOException e){System.err.println("Erreur r�sau "+e);
		System.exit(1);
			
		}
		
	}
	
	/**
	 * M�thode qui permet l'envoi des données au serveur
	 * @param server le socket du serveur
	 * @throws IOException
	 */
	public void communique(Socket server) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));// flux que l'on recoit
		PrintStream out=new PrintStream(server.getOutputStream());// flux dans lequel on va envoyer

		//On cr�e une classe pour envoyer les donn�es
		EnvoiClient e=new EnvoiClient(out,this);
		e.start();
		
		//On cr�e une classe pour recevoir les donn�es
		Ecoute ec=new Ecoute(false,in,f);
		ec.start();
		
		//on initialise le jeu r�seau et on lance la thread de jeu
        f.initJeu2jreseau(2);
        f.jeu.start();
	
        while(true){

        	if(requete==null){
        		// si la requete n'est pas nulle c'est que l'on a pas encore envoy� son contenu donc on ne le remplace pas
	                
        		if(f.envoiChoixForme==true){    // envoi du tableau de pieces sp�ciales avec lesquelles on joue              
	                  requete="Spe";
	                  f.envoiChoixForme=false;
	                  }
	                
	            else if(f.jeu.envoicorriace==true){//envoi d'une piece corriace a l'adversaire
	                  requete="c";f.jeu.envoicorriace=false; 
	                  }
	            
	            else if(f.jeu.lievre==true){//envoi la destruction d'une piece lievre à l'adversaire
	                  requete="L";
	                  f.jeu.lievre=false;
	                  }
        		
	            else if(f.jeu.superCassePied==true){//envoi la destruction d'une piece super casse-pied à l'adversaire
	                  requete="S";
	                  f.jeu.superCassePied=false;
	                  }
        		
	            else if(f.jeu.O==true){//envoi d'une piece 'O' à l'adversaire
	                  requete="O";
	                  f.jeu.O=false;
	                  }
	            else if(f.jeu.pickpocket==true){//envoi un demande � l'adversaire pour qu'il tranqmette sa prochaine piece 
	                  requete="V";
	                  f.jeu.pickpocket=false;
	                  }
        		
	            else if (f.jeu.envoiForme==true){// envoi de sa prochaine piece a l'adversaire
	                  requete="V"+f.ecranJeu.formeSuivante.toString();    
	                  f.ecranJeu.newForme();
	                  f.jeu.envoiForme=false;
	                  }
        		
        		else if(f.jeu.echangiste1==true){// envoi de l'écran et de la forme courrante en cas de piece échangiste detruite et demande d'nvoi du jeu de l'autre
	                  requete="E"+f.ecranJeu.currentForme.getNbrElement()+f.ecranJeu.currentForme.toString()+f.ecranJeu.toString();
	                  f.jeu.echangiste1=false;
	                  }
	                    
	            else if(f.jeu.echangiste2==true){//envoi de l'écran et de la forme courrante en cas de piece échangiste detruite
	                  requete="e"+f.jeu.ecranJeuTemp.toString();  
	                  f.jeu.echangiste2=false;
	                  }
	                    
	            else if(f.pannel.perdu==true)requete="P";//envoi de la perte de la partie
	                
	            else if(f.pannel.gagne==true)requete="G";//envoi � l'adversaire qu'il a gagn�
	                
	            else if(f.pannel.envoip==true) {requete="p";}//envoi de la mise en pause de la partie
	                    
	            else if(f.pannel.ecranJeu.envoi==true){// envoi de l'écran à l'adversaire
	                  requete=f.pannel.ecranJeu.formeSuivante.getNbrElement()+f.pannel.ecranJeu.formeSuivante.toString()+ f.pannel.ecranJeu.toString();
	                  f.pannel.ecranJeu.envoi=false;
	                  }
        		}
        	}
                                   
	}
	
	/**
	 * Accesseur de la variable requete
	 * @return renvoi la chaine de cractere contenu dans requete
	 */
	public String getRequete(){
		return requete;
	}
	
	/**
	 * Permet de modifier la variable requete
	 * @param str  la chaine à mettre dans la variable requete
	 */
	public void setRequete(String str){
		requete=str;
	}

	/**
	 * Accesseur de la variable f
	 * @return la variable f
	 */
	public Fenetre getFenetre() {
		return f;
	}
	
}
