package src;
import java.awt.Color; 
import java.util.Random;

/**
 * Classe representant la forme Zgauche
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Zgauche extends Forme {
	

     /**
       * Constructeur d'une forme dont on initialise les éléments de manière à 
       * avoir un Zgauche dont la position de départ est choisie aléatoirement
       * @param ecran auquel la formme sera associée 
       */  
	Zgauche(Ecran e){
            
                /*On crée une forme*/
                super();   
            
                /* On attribu un nombre d'élément specifique au type de la pièce*/
		super.setNbrElement(4);
                
                /* On genere la piece en choisisant aléatoirement parmis l'une 
                   des positions qu'elle aura au depart dans l'écran */
                setElement(new Element[super.getNbrElement()]);
		Random random= new Random();
		
		if(random.nextBoolean()){
			setElementAt(0,new Element(e.LARGEUR/2,2,super.vertrouge));
			setElementAt(1,new Element(e.LARGEUR/2,3,super.vertrouge));
			setElementAt(2,new Element(e.LARGEUR/2-1,2,super.vertrouge));
			setElementAt(3,new Element(e.LARGEUR/2+1,3,super.vertrouge));
		}
		
		else{
			setElementAt(0,new Element(e.LARGEUR/2,2,super.vertrouge));
			setElementAt(1,new Element(e.LARGEUR/2+1,2,super.vertrouge));
			setElementAt(2,new Element(e.LARGEUR/2+1,1,super.vertrouge));
			setElementAt(3,new Element(e.LARGEUR/2,3,super.vertrouge));
		}
	}
}