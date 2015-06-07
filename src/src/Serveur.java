package src;

/**
 * Thread qui permet de créer et lancer un serveur
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Serveur extends Thread {
	/**
	 * fenetre dans laquelle on crée la classe
	 */
	private Fenetre F;
	
	/**
	 * Constructeur de la classe
	 * @param f fenetre dans laquelle on crée la classe
	 */
	public Serveur(Fenetre f){
		F=f;
	}
	
	/**
	 * permet de creer un JeuServeur donc de lancer l'écoute et l'envoi de données 
	 */
	public void run(){
		new JeuServeur(F);
	}	
}
