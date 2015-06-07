package src;

/**
 * Thread qui permet de créer et lancer un client 
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Client extends Thread{
	/**
	 * fenetre dans laquelle on cr�e la classe
	 */
	private Fenetre F;
	
	/**
	 * chaine de caractere contenant l'adresse ip du serveur
	 */
	private String s;
	
	/**
	 * Constructeur de la classe
	 * @param f fenetre dans laquelle on crée la classe
	 * @param s2 chaine de caractere contenant l'adresse ip du serveur
	 */
	public Client(Fenetre f, String s2){
		F=f;
		s=s2;
	}
	
	/**
	 * permet de creer un JeuClient donc de lancer l'écoute et l'envoi de données 
	 */
	public void run(){
        new JeuClient(s,F); 
	}
}