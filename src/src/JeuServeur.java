package src;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe qui permet de gérer les connexion réseau d'un point de vu Serveur.
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class JeuServeur extends Thread {

	/**
	 * Socket d'écoute qui permet de savoir si le client s'est connecté.
	 */
	private ServerSocket socketEcoute;
	
	/**
	 * Chaine de caractere à envoyer au serveur
	 */
	private String requete;
	
	/**
	 * fenetre dans laquelle on crée la classe
	 */
	private Fenetre f;

	
	/**
	 * Constructeur de la classe
	 * @param f fenetre dans laquelle on crée la classe
	 */
	public JeuServeur(Fenetre f){
        this.f=f;
		requete=null;

		try{		
				new ChoixForme(3,f);
			socketEcoute=new ServerSocket(f.port);
		}
		catch (IOException e){
			
			System.err.println("Erreur réseau "+e);
			System.exit(1);
		}
		this.start();
	}
	
	/**
	 * Lance le serveur et attend que le client se connecte.
	 * Puis cr�e une connexion avec le client.
	 */
	public void run(){
		try{
			
			while(true){ Socket socketClient=socketEcoute.accept();
			f.win.dispose();
			new Connexion(socketClient,this);
			}
		}
		catch(IOException e){
			System.err.println("Erreur réseau "+e);
			System.exit(1);
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
	 * @param str  la chaine � mettre dans la variable requete
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
