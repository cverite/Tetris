package src;

import java.awt.Color;
import java.util.Random;

/**
 * Classe qui permet de gérer le tableau des pièces à  afficher
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Ecran  {
	
	//definition des couleurs des pièces
	Color vert= new Color (1,255,1);
	Color bleu= new Color (1,1,255);
	Color rouge= new Color (255,1,1);
	Color bleurouge= new Color (128,1,128);
	Color vertrouge= new Color (128,128,1);
	Color vertbleu= new Color (1,128,128);
	Color bleurougevert= new Color (128,128,128);
    Color couleurcoriace=new Color(51,51,51);
	Color couleuranccoriace=new Color(240,0,0);
    Color gris42= new Color (42,42,42);
	
	/**
	 * 
	 */
	//boolean downReleased;
	
	/**
	 * Matrice de couleur qui représente le plateau de jeu 
	 */
	Color [][] ecran;
	
	/**
	 * forme avec laquelle l'utilisateur est en train de jouer 
	 */
	Forme currentForme;
	
	/**
	 * la prochain forme que l'utilisateur va avoir.
	 */
	Forme formeSuivante;
	
	/**
	 * variable qui permet de sauvegarder la forme suivante si on recoit un O
	 */
	Forme formeSiO;
	   
    /**
     * booléen qui permet de savoir s'il est nécessaire d'envoyer l'écran à  l'autre joueur
     */
    boolean envoi;
	
    /**
     * Largeur de l'écran
     */
    int LARGEUR;
    /**
     * Hauteur de l'écran
     */
    int HAUTEUR;
    
    /**
     * Pannel dans lequel doit s'afficher l'écran.
     */
    Pannel pannel;
    
    
    /**
     * compteur pour la dispartition de la piece casse-pied 
     */
    int casse;

	/**
	 * Constructeur de la classe
	 * @param largeur la largeur de l'écran que l'on veut créer
	 * @param hauteur la hauteur de l'écran que l'on veut créer
	 */
	Ecran(int largeur,int hauteur){
                LARGEUR=largeur;// 9
                HAUTEUR=hauteur;// 14+3
		ecran = new Color [LARGEUR][HAUTEUR];
    		for(int i=0;i<LARGEUR;i++)
				for(int j=0; j<HAUTEUR ; j++)
					ecran [i][j]=Color.BLACK;
					
		Random random= new Random();	
		int rand=random.nextInt(7);
		if(rand==0){formeSuivante=new Carre(this);}
        if(rand==1){formeSuivante=new T(this);}
		if(rand==2){formeSuivante=new Zgauche(this);}
		if(rand==3){formeSuivante=new Zdroite(this);}
		if(rand==4){formeSuivante=new Lgauche(this);}
		if(rand==5){formeSuivante=new Ldroite(this);}
		if(rand==6){formeSuivante=new Barre(this);}
	}


	/**
	 * Méthode qui permet de supprimer une ligne de l'écran.
	 * @param numLigne le numéro de la ligne a supprimer
	 */
	void  supprLigne(int numLigne){
		for(int i=0;i<LARGEUR;i++){
			if(ecran[i][numLigne]!=this.pannel.couleurcoriace)
			ecran[i][numLigne]=Color.BLACK;
			else{
				ecran[i][numLigne]=couleuranccoriace;
			}
			}
		for(int j=numLigne;j>1;j--)
			for(int i=0;i<LARGEUR;i++)
			{if(ecran[i][numLigne]!=couleuranccoriace)
				ecran[i][j]=ecran[i][j-1];
			}
		for(int i=0;i<LARGEUR;i++){
			if(ecran[i][numLigne]!=couleuranccoriace)
		ecran[i][0]=Color.BLACK;}
	}
        
        /**
         * Méthode qui permet de créer une ligne a trou en bas de l'écran (pièce maman super casse pied))
         */
        void addLigneATrou(){
            for (int k=0; k<currentForme.getNbrElement(); k++)
			ecran[currentForme.getElementAt(k).getX()][currentForme.getElementAt(k).getY()]=Color.BLACK;
                        
            for(int j=5;j<HAUTEUR-1;j++)
                for(int i=0;i<LARGEUR;i++)
                    ecran[i][j]=ecran[i][j+1];

            for(int i=0;i<LARGEUR;i++){
                if(i%2==0)ecran[i][HAUTEUR-1].equals(currentForme.gris42);
                else ecran[i][HAUTEUR-1]=Color.BLACK;
            }
            for (int k=0; k<currentForme.getNbrElement(); k++)
            ecran[currentForme.getElementAt(k).getX()][currentForme.getElementAt(k).getY()]=currentForme.getElementAt(k).getCouleur();
        }
        
	/**
	 * Méthode qui permet de tester si une ligne est pleine
	 * @param numLigne le numéro de la ligne à  tester (sa coordonnée y)
	 * @return un booléen qui indique si la ligne est pleine ou non
	 */
	boolean lignePleine (int numLigne){
		for(int i=0;i<LARGEUR;i++)if(ecran[i][numLigne]==Color.BLACK)return false;
		return true;
	}
	
	/**
	 * Cette méthode permet de créer une nouvelle forme de façon aléatoire et de la stocker dans formesuivante
	 */
	void newForme(){
		
		Random random= new Random();
		int rand=random.nextInt(7);
		if(rand==0){formeSuivante=new Carre(this);}
        if(rand==1){formeSuivante=new T(this);}
		if(rand==2){formeSuivante=new Zgauche(this);}
		if(rand==3){formeSuivante=new Zdroite(this);}
		if(rand==4){formeSuivante=new Lgauche(this);}
		if(rand==5){formeSuivante=new Ldroite(this);}
		if(rand==6){formeSuivante=new Barre(this);}
	
	}
	
	/**
	 * @return une chaine de caractère représentant l'écran
	 */
	public String toString(){
		String str="";
			
		
        for(int j=4;j<HAUTEUR;j++)
            for(int i=0;i<LARGEUR;i++)		
            	if(ecran[i][j].equals(Color.BLACK))
            		str+="08";
            	else if(ecran[i][j].equals(currentForme.vert))str+="00";
                else if(ecran[i][j].equals(currentForme.vertbleu))str+="01";
                else if(ecran[i][j].equals(currentForme.vertrouge))str+="02";
                else if(ecran[i][j].equals(currentForme.bleurougevert))str+="03";
                else if(ecran[i][j].equals(currentForme.rouge))str+="04";
                else if(ecran[i][j].equals(currentForme.bleu))str+="05";
                else if(ecran[i][j].equals(currentForme.bleurouge))str+="06";
                else if(ecran[i][j].equals(currentForme.gris42))str+="07";
            	else if(ecran[i][j].equals(Color.GREEN))str+="09";
                else if(ecran[i][j].equals(Color.ORANGE))str+="10";
                else if(ecran[i][j].equals(Color.WHITE))str+="11";
                else if(ecran[i][j].equals(Color.PINK))str+="12";
                else if(ecran[i][j].equals(Color.CYAN))str+="13";
                else if(ecran[i][j].equals(Color.DARK_GRAY))str+="14";
                else if(ecran[i][j].equals(Color.YELLOW))str+="15";
                else if(ecran[i][j].equals(Color.LIGHT_GRAY))str+="16";
                else if(ecran[i][j].equals(Color.BLUE))str+="17";
                else if(ecran[i][j].equals(Color.GRAY))str+="18";
                else if(ecran[i][j].equals(Color.MAGENTA))str+="19";
                else if(ecran[i][j].equals(couleurcoriace))str+="20";
                else if(ecran[i][j].equals(couleuranccoriace))str+="21";

        	str+=pannel.f.jeu.score;
            	return str;
	}
	
	
	/**
	 * Cette méthode permet de mettre à  jour l'écran avec les données contenues dans la chaine de caractére passée en paramètre.
	 * @param str la cahine de caractere à  décoder
	 */
	public void decode(String str){

		int k=0;
		for(int j=4;j<HAUTEUR;j++)
		for (int i=0; i<LARGEUR; i++){ 
                    int coul=Integer.parseInt(str.substring(k,k+2));
              
                    switch (coul){
                    case 0: ecran[i][j]=vert;break;
                    case 1: ecran[i][j]=vertbleu;break;
                    case 2: ecran[i][j]=vertrouge;break;
                    case 3: ecran[i][j]=bleurougevert;break;
                    case 4: ecran[i][j]=rouge;break;
                    case 5: ecran[i][j]=bleu;break;
                    case 6: ecran[i][j]=bleurouge;break;
                    case 7: ecran[i][j]=gris42;break;   
                    case 8:ecran[i][j]=Color.BLACK;break;
                    case 9: ecran[i][j]=Color.GREEN;break;
                    case 10: ecran[i][j]=Color.ORANGE;break;
                    case 11: ecran[i][j]=Color.WHITE;break;
                    case 12: ecran[i][j]=Color.PINK;break;
                    case 13: ecran[i][j]=Color.CYAN;break;
                    case 14: ecran[i][j]=Color.DARK_GRAY;break; 
                    case 15: ecran[i][j]=Color.YELLOW;break;
                    case 16: ecran[i][j]=Color.LIGHT_GRAY;break;   
                    case 17: ecran[i][j]=Color.BLUE;break;
                    case 18: ecran[i][j]=Color.GRAY;break; 
                    case 19: ecran[i][j]=Color.MAGENTA;break;
                    case 20: ecran[i][j]=couleurcoriace;break; 
                    case 21: ecran[i][j]=couleuranccoriace;break; 
                    }
                    k+=2;
	}
		int score=Integer.parseInt(str.substring(k, str.length()));
		pannel.f.pannel2.label.setText(("Score : "+score));

	}
}


     

	