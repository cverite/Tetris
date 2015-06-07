package src;
/**
 * Classe representant la forme O
 * @author Aurélie OTTAVI et Céline VERITE
 */

public  class O extends Forme {
	
     /**
       * Constructeur d'une forme dont on initialise les éléments de manière à 
       * avoir un O dont la position de départ est choisie aléatoirement
       * @param ecran auquel la forme sera associée 
       */ 
	O(Ecran e){
            
                /*On crée une forme*/
		super();      
                
                /* On attribue un nombre d'élément specifique au type de la pièce*/
		super.setNbrElement(9);
                
                /* On genere la piece en choisisant aléatoirement parmis l'une 
                   des positions qu'elle aura au depart dans l'écran */
		super.setElement(new Element[super.getNbrElement()]);
		
				setElementAt(0,new Element(e.LARGEUR/2,2,super.black));
				setElementAt(1 ,new Element(e.LARGEUR/2,1,super.gris42));
				setElementAt(2,new Element(e.LARGEUR/2+1,1,super.gris42));
				setElementAt(3,new Element(e.LARGEUR/2-1,1,super.gris42));
				setElementAt(4,new Element(e.LARGEUR/2-1,2,super.gris42));
				setElementAt(5,new Element(e.LARGEUR/2+1,2,super.gris42));
				setElementAt(6,new Element(e.LARGEUR/2-1,3,super.gris42));
				setElementAt(7,new Element(e.LARGEUR/2,3,super.gris42));
				setElementAt(8,new Element(e.LARGEUR/2+1,3,super.gris42));
	}
}