package src;
import java.util.Random;

/**
 * Classe representant la forme T
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class T extends Forme {
	
	
     /**
       * Constructeur d'une forme dont on initialise les éléments de manière à 
       * avoir un T dont la position de départ est choisie aléatoirement
       * @param ecran auquel la forme sera associée 
       */ 
	T(Ecran e){
            
                /*On crée une forme*/
                super();   
            
                /* On attribue un nombre d'élément specifique au type de la pièce*/
		super.setNbrElement(4);
                
                /* On genere la piece en choisisant aléatoirement parmis l'une 
                   des positions qu'elle aura au depart dans l'écran */
                super.setElement(new Element[super.getNbrElement()]);
		Random random= new Random();
		int rand=random.nextInt(4);
		
		if(rand==0){
			super.setElementAt(0,new Element(e.LARGEUR/2,2,super.rouge));
			super.setElementAt(1,new Element(e.LARGEUR/2-1,2,super.rouge));
			super.setElementAt(2,new Element(e.LARGEUR/2+1,2,super.rouge));
			super.setElementAt(3,new Element(e.LARGEUR/2,3,super.rouge));
		}
		
		if(rand==1){
			super.setElementAt(0,new Element(e.LARGEUR/2,3,super.rouge));
			super.setElementAt(1,new Element(e.LARGEUR/2-1,3,super.rouge));
			super.setElementAt(2,new Element(e.LARGEUR/2+1,3,super.rouge));
			super.setElementAt(3,new Element(e.LARGEUR/2,2,super.rouge));
		}
		
		if(rand==2){
			super.setElementAt(0,new Element(e.LARGEUR/2,2,super.rouge));
			super.setElementAt(1,new Element(e.LARGEUR/2,1,super.rouge));
			super.setElementAt(2,new Element(e.LARGEUR/2,3,super.rouge));
			super.setElementAt(3,new Element(e.LARGEUR/2+1,2,super.rouge));
		}
		
		if(rand==3){
			super.setElementAt(0,new Element(e.LARGEUR/2+1,2,super.rouge));
			super.setElementAt(1,new Element(e.LARGEUR/2+1,1,super.rouge));
			super.setElementAt(2,new Element(e.LARGEUR/2+1,3,super.rouge));
			super.setElementAt(3,new Element(e.LARGEUR/2,2,super.rouge));
		}
	}
}

 