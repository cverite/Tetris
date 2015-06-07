package src;
import java.util.Random;

/**
 * Classe representant la forme Lgauche
 * @author Aurélie OTTAVI et Céline VERITE
 */
class Lgauche extends Forme {
	
     /**
       * Constructeur d'une forme dont on initialise les éléments de manière à 
       * avoir un Lgauche dont la position de départ est choisie aléatoirement
       * @param ecran auquel la forme sera associée 
       */ 
	Lgauche(Ecran e){
            
                /*On crée une forme*/
		super();    
                
                /* On attribue un nombre d'élément specifique au type de la pièce*/
		super.setNbrElement(4);
		
                /* On genere la piece en choisisant aléatoirement parmis l'une 
                   des positions qu'elle aura au depart dans l'écran */
		Random random= new Random();
		int rand=random.nextInt(4);
		
		if(rand==0){
                    super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2,1,super.bleu));
			setElementAt(1,new Element(e.LARGEUR/2,2,super.bleu));
			setElementAt(2,new Element(e.LARGEUR/2,3,super.bleu));
			setElementAt(3,new Element(e.LARGEUR/2+1,3,super.bleu));
		}
		
		if(rand==1){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2+1,1,bleu));
			setElementAt(1,new Element(e.LARGEUR/2,1,bleu));
			setElementAt(2,new Element(e.LARGEUR/2+1,2,bleu));
			setElementAt(3,new Element(e.LARGEUR/2+1,3,bleu));
		}
		
		if(rand==2){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2+1,3,bleu));
			setElementAt(1,new Element(e.LARGEUR/2+1,2,bleu));
			setElementAt(2,new Element(e.LARGEUR/2,3,bleu));
			setElementAt(3,new Element(e.LARGEUR/2-1,3,bleu));
		}
		
		if(rand==3){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2-1,2,bleu));
			setElementAt(1,new Element(e.LARGEUR/2,2,bleu));
			setElementAt(2,new Element(e.LARGEUR/2+1,2,bleu));
			setElementAt(3,new Element(e.LARGEUR/2-1,3,bleu));
		}
	}
}