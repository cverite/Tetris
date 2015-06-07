package src;
import java.util.Random;

/**
 * Classe representant la forme Zgauche
 * @author Aurélie OTTAVI et Céline VERITE
 */
class Ldroite extends Forme {

    /**
     * Constructeur d'une forme dont on initialise les éléments de manière à 
     * avoir un Zgauche dont la position de départ est choisie aléatoirement
     * @param ecran auquel la formme sera associée 
     */  	
	Ldroite(Ecran e){
		/*On crée une forme*/
		super();
		
		/* On attribu un nombre d'élément specifique au type de la pièce*/
		super.setNbrElement(4);
		Random random= new Random();
		int rand=random.nextInt(4);
		
		/* On genere la piece en choisisant aléatoirement parmis l'une 
        des positions qu'elle aura au depart dans l'écran */
		if(rand==0){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2+1,3,vert));
			setElementAt(1,new Element(e.LARGEUR/2+1,2,vert));
			setElementAt(2,new Element(e.LARGEUR/2+1,1,vert));
			setElementAt(3,new Element(e.LARGEUR/2,3,vert));
		}
		
		if(rand==1){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2,1,vert));
			setElementAt(1,new Element(e.LARGEUR/2+1,1,vert));
			setElementAt(2,new Element(e.LARGEUR/2,2,vert));
			setElementAt(3,new Element(e.LARGEUR/2,3,vert));
		}
		
		if(rand==2){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2+1,2,vert));
			setElementAt(1,new Element(e.LARGEUR/2+1,3,vert));
			setElementAt(2,new Element(e.LARGEUR/2,2,vert));
			setElementAt(3,new Element(e.LARGEUR/2-1,2,vert));
		}
		
		if(rand==3){
			super.setElement(new Element[super.getNbrElement()]);
			setElementAt(0,new Element(e.LARGEUR/2-1,3,vert));
			setElementAt(1,new Element(e.LARGEUR/2,3,vert));
			setElementAt(2,new Element(e.LARGEUR/2+1,3,vert));
			setElementAt(3,new Element(e.LARGEUR/2-1,2,vert));
		}
	}
}