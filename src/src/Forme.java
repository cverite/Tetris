package src;
import java.awt.Color; 

/**
 * Classe abstraite permettant de regrouper les différents type de forme
 * @author Aurélie OTTAVI et Céline VERITE
 */
public  abstract class Forme{
	
        /**
          * tableau d'élément permettant de representer une forme
          */
	private Element element[];
        
        /**
          * nombre d'éléments composant la forme
          */
	private int nbrElement;
        
        /**
          * boolean qui permet de savoir si la piece est posée ou si au contraire elle peut encore descendre
          */
        private boolean peutDescendre;
        
        /**
         * Declaration de variables du type couleur qui permettent de donner une couleur à une forme
         */
	public Color vert= new Color (1,255,1);
	public Color bleu= new Color (1,1,255);
	public Color rouge= new Color (255,1,1);
	public Color bleurouge= new Color (128,1,128);
	public Color vertrouge= new Color (128,128,1);
	public Color vertbleu= new Color (1,128,128);
	public Color bleurougevert= new Color (128,128,128);
        public Color gris42= new Color (42,42,42);    
	public Color black=Color.BLACK;        
  
        /**
          * Constructeur d'une forme qui sera appelé par les sous classes 
          */
	Forme () {

          /* Lors de la création d'une forme, on initialise la variable peut 
           descendre à true, cette variable sera ensuite recalculée à chaque 
           fois que la forme descendra dans l'ecran */
            this.peutDescendre=true;
        }
	
        /**
          * Methode permettant de convertir une forme en chaine de caractères 
          * de manière à pouvoir la transmettre par le réseau. 
          * On transmet ainsi les coordonnées et les couleurs de chaque élément
          * composant la forme. 
          * @return chaine de caractère associèe à la forme
          */
	public String toString(){
            
		String str=new String();
		int i;
                
                /*pour chaque élément de la forme*/
		for(i=0;i<this.nbrElement;i++){
                    
                    /* On convertit l'abcisse en un chiffre composé de deux 
                     caractères car les coordonnées peuvent être superieurs 
                     à 10 (selon la taille de l'écran)*/
                    if(this.element[i].getX()< 10)
                        str+="0"+this.element[i].getX();
                    else str+=this.element[i].getX();
                    
                    /* On convertit l'ordonnée en un chiffre composé de deux 
                     caractères car les coordonnées peuvent être superieurs 
                     à 10 (selon la taille de l'écran)*/
                    if(this.element[i].getY()< 10)
                        str+="0"+this.element[i].getY();
                    else str+=this.element[i].getY();
                    
                    /* On convertit la couleur de l'élément en un chiffre*/
                    if(this.element[i].getCouleur().equals(vert))str+='0';
                    else if(this.element[i].getCouleur().equals(vertbleu))str+='1';
                    else if(this.element[i].getCouleur().equals(vertrouge))str+='2';
                    else if(this.element[i].getCouleur().equals(bleurougevert))str+='3';
                    else if(this.element[i].getCouleur().equals(rouge))str+='4';
                    else if(this.element[i].getCouleur().equals(bleu))str+='5';
                    else if(this.element[i].getCouleur().equals(bleurouge))str+='6';
                    else if(this.element[i].getCouleur().equals(gris42))str+='7';
		}
		return str;
	}

         /**
          * Methode permettant de convertir une chaine de caractère
          * crée à partir de la methode toString de la classe Forme en une forme
          * lors de sa reception par le réseau. 
          * On decode ainsi les coordonnées et les couleurs de chaque élément
          * composant la forme. 
          * @param str chaine de caractère associèe à la forme
          */
	public void decode(String str){
            
		int nbr=str.length()/5;
		Element elementDecode[] =new Element[nbr];
		Color couleur=black;
                
                /*pour chaque élément de la forme*/
		for (int i=0; i<nbr ; i++){ 
                    
                    /* On recupère la couleur*/
                    int coul=Integer.parseInt(str.substring((5*i)+4,(5*i)+5));
                    
                    switch (coul){
                        case 0: couleur=vert;break;
                        case 1: couleur=vertbleu;break;
                        case 2: couleur=vertrouge;break;
                        case 3: couleur=bleurougevert;break;
                        case 4: couleur=rouge;break;
                        case 5: couleur=bleu;break;
                        case 6: couleur=bleurouge;break;
                        case 7: couleur=gris42;break;

                    }
                    
                    /* On recupère les coordonnées de l'élément et on stocke les données récupérée dans un element*/
                    elementDecode[i]=new Element( Integer.parseInt(str.substring((5*i),(5*i)+2)),Integer.parseInt(str.substring((5*i)+2,(5*i)+4)),couleur); 
		
                }
                
                /*On modifie la forme avec les données récupérée*/
                this.nbrElement=nbr;
                this.element=elementDecode;
	
	}
	
	
/***********************************************************************
	Pour realiser un deplacement, il faut verifier que la piece n'est pas encore "posée",
	c'est a dire qu'elle peut encore descendre.	
***********************************************************************/
	
	
	
	 /**
          * Methode permettant de de déplacer une forme d'un cran à droite dans l'écran
          * @param Ecran auquel la forme est associée
          */
        
	void deplacerDroite (Ecran e) {

             boolean peutDroite=true;

             /* On teste si on ne sort pas de l'écran */
             for (int i=0; i<nbrElement ; i++){ 
                if(element[i].getX()>e.LARGEUR-2){
                        peutDroite=false; 
                        return;
                    }
             }
             /* On teste si il n'y a pas d'autre pièce à l'endroit a déplacer*/
             for (int i=0; i<nbrElement ; i++){
                    if(e.ecran[element[i].getX()+1][element[i].getY()]!=Color.BLACK) {
                            peutDroite=false;  
                            for (int j=0;j<nbrElement;j++){
                                        if((element[i].getX()+1==element[j].getX()) && (element[i].getY()==element[j].getY()))
                                            peutDroite=true;
                                }
                                if(!peutDroite) return;
                            }
                    }
   
             /* Le déplacement est possible donc on le réalise */
            for (int i=0; i<nbrElement; i++){
                    e.ecran[element[i].getX()][element[i].getY()]=Color.BLACK;   
                    element[i].setX(element[i].getX()+1);
            }
            for (int i=0; i<nbrElement; i++){
                    e.ecran[element[i].getX()][element[i].getY()]=element[i].getCouleur();
            }
            
            /* Teste si on peut encore descendre la piece */
            this.peutDescendre(e);
        }
        
        
      /**
        * Methode permettant de de déplacer une forme d'un cran à gauche dans l'écran
        * @param Ecran auquel la forme est associée
        */
	void deplacerGauche (Ecran e) {
            
		boolean peutGauche=true;
                
                /* On teste si on ne sort pas de l'écran */
		for (int i=0; i<nbrElement ; i++){ 
			if(element[i].getX()<1){
				peutGauche=false; 
				return;
			}
		}

                /* On teste si il n'y a pas d'autre pièce à l'endroit a déplacer*/
		
		for (int i=0; i<nbrElement ; i++){
			if(e.ecran[element[i].getX()-1][element[i].getY()]!=Color.BLACK) {
				peutGauche=false;  
				for (int j=0;j<nbrElement;j++){
					if((element[i].getX()-1==element[j].getX()) && (element[i].getY()==element[j].getY()))
                                            peutGauche=true;
				}
				if(!peutGauche) return;
			}
		}

                 /* Le déplacement est possible donc on le réalise */
		for (int i=0; i<nbrElement; i++){
			e.ecran[element[i].getX()][element[i].getY()]=Color.BLACK;
			element[i].setX(element[i].getX()-1);     
		}
		for (int i=0; i<nbrElement; i++){
			e.ecran[element[i].getX()][element[i].getY()]=element[i].getCouleur();
		}        
	
	/* Teste si on peut encore descendre la piece */
		this.peutDescendre(e);
	
        }
	
       /**
        * Methode permettant de déplacer une forme d'un cran vers le bas dans l'écran
        * @param Ecran auquel la forme est associée
        */
	void descendre (Ecran e) {
            
            /* On teste si on peut effectuer le deplacement */
		this.peutDescendre(e);
	            
            /* Si le déplacement est possible, on le réalise */
                if (peutDescendre){

			for (int i=0; i<nbrElement; i++){
                            e.ecran[element[i].getX()][element[i].getY()]=Color.BLACK;
                            element[i].setY(element[i].getY()+1);    
			}
			for (int i=0; i<nbrElement; i++){
                            e.ecran[element[i].getX()][element[i].getY()]=element[i].getCouleur();
			}
		}      
	
                /* Teste si on peut encore descendre la piece */
		this.peutDescendre(e);
        }
	
       /**
        * Methode permettant de faire tourner une forme dans l'écran
        * @param Ecran auquel la forme est associée
        */
	void tourner(Ecran e){ 
		
		boolean peutTourner=true;
		
                /* On teste si on ne sort pas de l'écran */
                for (int i=1; i<nbrElement ; i++){ 
                    if(element[0].getX()+element[i].getY()-element[0].getY()>e.LARGEUR-1 || element[0].getX()+element[i].getY()-element[0].getY()<0 || element[0].getY()-element[i].getX()+element[0].getX()>e.HAUTEUR-1 ||element[0].getY()-element[i].getX()+element[0].getX()<0){
			peutTourner=false; 
			return;
                    }
                }
		
                /* On teste si il n'y a pas d'autre pièce à l'endroit a déplacer */
                for (int i=0; i<nbrElement ; i++){
                    if(e.ecran[element[0].getX()+element[i].getY()-element[0].getY()][element[0].getY()-element[i].getX()+element[0].getX()]!=Color.BLACK) {
			peutTourner=false;  
			for (int j=0;j<nbrElement;j++){
                                if((element[0].getX()+element[i].getY()-element[0].getY()==element[j].getX()) && (element[0].getY()-element[i].getX()+element[0].getX()==element[j].getY()))
                                    peutTourner=true;
			}
			if(!peutTourner) return;
                    }
                }
		
		
		
	         /* Le déplacement est possible donc on le réalise */		
                for(int i=1;i<nbrElement;i++){
                        int temp= element[i].getX();
                        e.ecran[element[i].getX()][element[i].getY()]=Color.BLACK;
                        element[i].setX(element[0].getX()+element[i].getY()-element[0].getY());
                        element[i].setY(element[0].getY()-temp+element[0].getX());
                }
                
                for(int i=1;i<nbrElement;i++){
                        e.ecran[element[i].getX()][element[i].getY()]=element[i].getCouleur();		
                }
                
        	/* Teste si on peut encore descendre la piece */
		this.peutDescendre(e);
	}

      /**
        * Methode permettant de savoir si la forme peut encore descendre ou si elle à été posée 
        * @param Ecran auquel la forme est associée
        */
	void peutDescendre(Ecran e){
            
		peutDescendre=true;
		boolean test;
                
                /* On teste si on ne sort pas de l'ecran */
		for (int i=0; i<nbrElement ; i++){ 
			if(element[i].getY()>e.HAUTEUR-2){
				peutDescendre=false; 
				return;
			}
		}
                
		/* On teste si il n'y a pas d'autre piece a l'endroit a deplacer */
                for (int i=0; i<nbrElement ; i++){
                        if(e.ecran[element[i].getX()][element[i].getY()+1]!=Color.BLACK) {
                                test=false;  
                                for (int j=0;j<nbrElement;j++){
                                        if((element[i].getX()==element[j].getX()) && (element[i].getY()+1==element[j].getY()))
                                                test=true;
                                }
                                if(!test) peutDescendre=false; 
                        }
                }
            }
	
        /**
         * Methode d'accès en lecture à au nombre d'élément de la forme         
         * @return entier correspondant au nombre d'élément de la forme 
         */
        int getNbrElement(){
            return nbrElement;
        }
	
        /**
         * Methode d'accès en lecture à au nombre d'élément de la forme         
         * @return entier correspondant au nombre d'élément de la forme 
         */
        boolean getPeutDescendre(){
            return peutDescendre;
        }
        
        /**
         * Methode d'accès en ecriture à la couleur de l'élément.         
         * @param entier correspondant au nombre d'élément de la forme 
         */
        void setNbrElement( int nbrElement){
            this.nbrElement=nbrElement;
        }
        
                        /**
         * Methode d'accès en lecture à au nombre d'élément de la forme         
         * @return entier correspondant au nombre d'élément de la forme 
         */
        Element getElementAt(int i){
            return element[i];
        }
        
        /**
         * Methode d'accès en ecriture à la couleur de l'élément.         
         * @param entier correspondant au nombre d'élément de la forme 
         */
        void setElement( Element[] element){
            this.element =element;
        }
        
        void setElementAt(int i, Element element){
            this.element[i] =element;
        }
}	
	
	

