package src;
import java.util.Random;

/**
 * Classe representant la forme carré
 * @author Aurélie OTTAVI et Céline VERITE
 */

public class Barre extends Forme {
    /**
     * Constructeur d'une forme dont on initialise les éléments de manière à 
     * avoir une barre dont la position de départ est choisie aléatoirement
     * @param ecran auquel la forme sera associée 
     */ 
	Barre(Ecran e){
		/*On crée une forme*/
		super();        
		
		/* On attribue un nombre d'élément specifique au type de la pièce*/
		super.setNbrElement(4);
		super.setElement(new Element[super.getNbrElement()]);
		Random random= new Random();
		/* On genere la piece */
			if(random.nextBoolean()){
				setElementAt(0,new Element(e.LARGEUR/2,3,vertbleu));
				setElementAt(1,new Element(e.LARGEUR/2+1,3,vertbleu));
				setElementAt(2,new Element(e.LARGEUR/2-1,3,vertbleu));
				setElementAt(3,new Element(e.LARGEUR/2-2,3,vertbleu));
			}
			else{
				setElementAt(0,new Element(e.LARGEUR/2+1,1,vertbleu));
				setElementAt(1,new Element(e.LARGEUR/2+1,0,vertbleu));
				setElementAt(2,new Element(e.LARGEUR/2+1,2,vertbleu));
				setElementAt(3,new Element(e.LARGEUR/2+1,3,vertbleu));
			}
	}
}