package src;


/**
 * Cette classe permet de créer un jeu 2 joueurs
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Jeu2j{

	
	/**
	 * pannel pour le jeu du joueur 1
	 */
	Pannel pannel;
	
	/**
	 * pannel pour le jeu du joueur 2
	 */
	Pannel pannel2;
		
	/**
	 * Thead pour le joueur 1
	 */
	Thread2j t1;
	
	/**
	 * Thead pour le joueur 2
	 */
	Thread2j t2;

        
	/**
	 * Construteur de la classe
	 * @param f fenetre dans laquelle le jeu deux joueurs doit se lancer
	 */
	public Jeu2j(Fenetre f){
		Ecran ecranJeu=new Ecran(16,20);
		Ecran ecranJeu2=new Ecran(16,20);
		pannel= new Pannel(ecranJeu,f);
		pannel2= new Pannel(ecranJeu2,f);
		t1=new Thread2j(pannel,f);
		t2=new Thread2j(pannel2,f);
		t1.autreJeu=t2;
		t2.autreJeu=t1;
		f.pannel=pannel;
		f.ecranJeu=ecranJeu;
		f.pannel2=pannel2;
		f.ecranJeu2=ecranJeu2;
		f.setLayout(null);
		pannel.setBounds(pannel.size*(pannel2.ecranJeu.LARGEUR+8),10,500,500);	
		f.getContentPane().add(pannel);
		f.getContentPane().add(pannel2);
		f.pack();
	}

	/**
	 * Lancement des threads thread2j
	 */
	public void run(){

		t1.start();
		t2.start();
	}	
}


