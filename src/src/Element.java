package src;
import java.awt.Color; 

/**
 * Classe representant les elément qui compose une forme
 * @author Aurélie OTTAVI et Céline VERITE
 */

public class Element{
	
     /**
      * coordonnée x correspondant à l'endroit ou l'element se situe dans l'ecran
      */
	private int x;
     /**
      * coordonnée y correspondant à l'endroit ou l'element se situe dans l'ecran
      */
	private int y;
     /**
      * couleur associé à l'element
      */
	private Color couleur;
        

     /**
      * Constructeur d'un élément avec initialisation à des valeurs par défault
      */
	Element(){
		x=0; y=0; 
		this.couleur=Color.BLACK;
	}
      
      /**
       * Constructeur d'un élément avec initialisation à des valeurs passées en paramètre
       * @param abcisse de l'ecran ou devra se situer l'élément.
       * @param ordonnée de l'ecran ou devra se situer l'élément.
       * @param couleur de l'élément.
       */  
	Element(int xo, int yo ,Color couleur){
		x=xo; y=yo; 
		this.couleur=couleur;
	}

	/**
         * Methode d'accès en lecture à l'abcisse de l'élément.         
         * @return entier correspondant à l'ordonnée de l'élément
         */
	int getX () {
		return x;
		}
        
	/**
         * Methode d'accès en lecture à l'ordonnée de l'élément.         
         * @return entier correspondant à l'abcisse de l'élément
         */
	int getY () {
		return y;
		}
		
        /**
         * Methode d'accès en lecture à la couleur de l'élément.         
         * @return entier correspondant à la couleur de l'élément
         */
        Color getCouleur(){
            return couleur;
        }
	
        /**
         * Methode d'accès en ecriture à la couleur de l'élément.         
         * @param entier correspondant à la couleur de l'élément
         */
        void setCouleur( Color couleur){
            this.couleur=couleur;
        }
        
        /**
         * Methode d'accès en écriture à l'abcisse de l'élément.         
         * @param entier correspondant à l'abcisse de l'élément
         */
        void setX (int x) {
		this.x=x;
	}
    
        /**
         * Methode d'accès en écriture à l'ordonnée de l'élément.         
         * @param entier correspondant à l'ordonnée de l'élément
         */
        void setY(int y) {
		this.y=y;
	}
}