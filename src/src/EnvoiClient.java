package src;
import java.io.PrintStream;


/**
 * Classe qui permet d'envoyer des données sur le réseau
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class EnvoiClient extends Thread{
	
	/**
	 * Flux dans lequel on envoi les données
	 */
	private PrintStream out;
	
	/**
	 * classe dans laquelle est créé l'objet et dans laquelle est située la requete à envoyer
	 */
	private JeuClient jeu;
	
	/**
	 * Constructeur de la classe
	 * @param out Flux dans lequel on envoi les donn�es
	 * @param jeu classe dans laquelle est créé l'objet et dans laquelle est située la requete à envoyer
	 */
	public EnvoiClient(PrintStream out,JeuClient jeu){
		this.out=out;
		this.jeu=jeu;
	}
	
	/**
	 * Permet de tester en boucle s'il y a quelquechose à envoyer et,si c'est le cas, de l'envoyer
	 */
	public void run() {
		while (true) {
			if (jeu.getRequete() != null) {
				out.println(jeu.getRequete());
				if (jeu.getRequete().equals("p")) {
					jeu.getFenetre().pannel.envoip = false;
				}
				jeu.setRequete(null);
			}
		}
	}
}
