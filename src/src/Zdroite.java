package src;
import java.util.Random;

/**
 * Classe representant la forme Zdroite
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Zdroite extends Forme {
	
	
     /**
       * Constructeur d'une forme dont on initialise les éléments de manière à 
       * avoir un Zdroite dont la position de départ est choisie aléatoirement
       * @param ecran auquel la forme sera associée 
       */  
	Zdroite(Ecran e){
            
                /*On crée une forme*/
                super();   
            
                /* On attribuun nombre d'élément specifique au type de la pièce*/    
		super.setNbrElement(4);
                
                /* On genere la piece en choisisant aléatoirement parmis l'une 
                   des positions qu'elle aura au depart dans l'écran */
                super.setElement(new Element[super.getNbrElement()]);
		Random random= new Random();
		
		if(random.nextBoolean()){
			setElementAt(0,new Element(e.LARGEUR/2,2,super.bleurouge));
			setElementAt(1,new Element(e.LARGEUR/2,3,super.bleurouge));
			setElementAt(2,new Element(e.LARGEUR/2+1,2,super.bleurouge));
			setElementAt(3,new Element(e.LARGEUR/2-1,3,super.bleurouge));
		}
		
		else{
			setElementAt(0,new Element(e.LARGEUR/2+1,2,super.bleurouge));
			setElementAt(1,new Element(e.LARGEUR/2,1,super.bleurouge));
			setElementAt(2,new Element(e.LARGEUR/2,2,super.bleurouge));
			setElementAt(3,new Element(e.LARGEUR/2+1,3,super.bleurouge));
		}
	}
}