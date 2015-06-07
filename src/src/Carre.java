package src;
/**
 * Classe representant la forme carré
 * @author Aurélie OTTAVI et Céline VERITE
 */

public  class Carre extends Forme {
	   
         /**
           * Constructeur d'une forme dont on initialise les éléments de manière à 
           * avoir un carre dont la position de départ est choisie aléatoirement
           * @param ecran auquel la forme sera associée 
           */ 
	Carre(Ecran e){
		
            /*On crée une forme*/
	    super();     

            /* On attribue un nombre d'élément specifique au type de la pièce*/
            super.setNbrElement(4);
            
             /* On genere la piece */
            super.setElement(new Element[super.getNbrElement()]);
            super.setElementAt(0,new Element(e.LARGEUR/2,2,bleurougevert));
            super.setElementAt(1,new Element(e.LARGEUR/2,3,bleurougevert));
            super.setElementAt(2,new Element(e.LARGEUR/2+1,2,bleurougevert));
            super.setElementAt(3,new Element(e.LARGEUR/2+1,3,bleurougevert));
	}
}
