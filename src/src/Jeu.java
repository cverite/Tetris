package src;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;


/**
 * Cette classe permet de créer un jeu un joueur ou un jeu deux joueur en réseau
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Jeu extends Thread{
	
    /**
     * Fenetre dans laquelle le jeu s'exécute
     */
    Fenetre f;
	
    /**
     * Ecran du jeu dans lequel est stockée la matrice du plateau
     */
    Ecran ecranJeu;
	
	/**
	 * pannel dans lequel le jeu s'affiche
	 */
	Pannel pannel;
    
	/**
	 * score du joueur
	 */
	int score;
	
	/**
	 * nb de ligne que le joueur a detruites
	 */
	int nbligne;
	
	/**
	 * vitesse du jeu
	 */
	int speed;
	
	/**
	 * nombre de pi�ce que le joueur a eu
	 */
	int nbpiece;	
        
        /**
         * permet de savoir si le joeur a detruit une ligne pendant ce tour
         */
        boolean ligneDetruite;
        
        /**
         * permet de savoir si le joueur a detruit la piece spécial ligne killer
         */
        boolean supprLigneSpecial;
        
        /**
         * permet de savoir si l'autre joueur a detruit une brique lievre
         */
        boolean lievre=false;
        
        /**
         * permet de savoir si l'autre joueur a detruit une brique super casse pied
         */
        boolean superCassePied=false;
        
        /**
         * permet de savoir si un joueur a detruit un brique pick pocket
         */
        boolean pickpocket=false;
        
        /**
         * permet de savoir si l'autre joueur a detruit une brique maman o
         */
        boolean O=false;
        
        /**
         * permet de savoir si il faut envoyer la forme courrante a l'autre joueur
         */
        boolean envoiForme=false;
        
        /**
         * variable permettant de traiter la piece specilae echangiste en mode reseau
         */
        boolean echangiste1=false;
        
        /**
         * variable permettant de traiter la piece specilae echangiste en mode reseau
         */
        boolean echangiste2=false;
        
        /**
         * permet de savoir si il faut envoyer une piece corriace a l'autre joueur
         */
        boolean envoicorriace;
        
        /**
         * permet de savoir si l'autre joueur a envoyé une pièce corriace
         */
        boolean corriace;
        
        /**
         * permet de sauvegarder temporrairement l'écran de jeu
         */
        String ecranJeuTemp;
        
        /**
         * tableau des pièces spéciales avec lesquelles on joue
         */
        Vector<Color> pieces_speciales;
        
	/**
	 * Constructeur de la classe
	 * @param f Fenetre dans laquelle le jeu s'ex�cute
	 */
	public Jeu(Fenetre f){
		envoicorriace=false;
		corriace=false;
		this.pieces_speciales=new Vector<Color>();
		ecranJeu=new Ecran(16,20);
		pannel= new Pannel(ecranJeu,f);
		this.f=f;
		f.pannel=pannel;
		f.ecranJeu=ecranJeu;
		f.setLayout(null);
		f.getContentPane().add(pannel);
		f.pack();
	}
	
	/**
	 * cette fonction permet de créer une chaine de caractere codant le tableau de piéces spéciales
	 * @return chaine de caractere permettant de coder le tableau de piéce spéciale
	 */
	public String SpetoString(){
		String str=new String();
                Color couleurcoriace=new Color(51,51,51);
                str+="C";
		for (int i=0;i<pieces_speciales.size();i++){
                
                if(pieces_speciales.elementAt(i).equals(Color.PINK))str+="00";    
                if(pieces_speciales.elementAt(i).equals(Color.GREEN))str+="01";
                if(pieces_speciales.elementAt(i).equals(Color.BLUE))str+="02";
                if(pieces_speciales.elementAt(i).equals(Color.CYAN))str+="03";
                if(pieces_speciales.elementAt(i).equals(Color.GRAY))str+="04";
                if(pieces_speciales.elementAt(i).equals(Color.MAGENTA))str+="05";
                if(pieces_speciales.elementAt(i).equals(Color.ORANGE))str+="06";
                if(pieces_speciales.elementAt(i).equals(Color.YELLOW))str+="07";
                if(pieces_speciales.elementAt(i).equals(Color.WHITE))str+="08";
                if(pieces_speciales.elementAt(i).equals(Color.DARK_GRAY))str+="09";
                if(pieces_speciales.elementAt(i).equals(Color.LIGHT_GRAY))str+="10";
                if(pieces_speciales.elementAt(i).equals(couleurcoriace))str+="11";
                }
		return str;
	}
        
     /** 
     * Lancement du jeu 
     */
    public void run(){
                f.nouveauJeu=true;
            while(true){
        	
            if (f.nouveauJeu==true){
            //initialisation des variables
            score=0;
            nbligne=0;
            speed=2000;
            nbpiece=0;
            f.nouveauJeu=false;
            ligneDetruite=false;
            supprLigneSpecial=false;
            pannel.pause=false;
            pannel.perdu=false;
            this.ecranJeu.casse=-1;
      
            //initialisation des cases de l'ecran � noir
           for(int i=0;i<ecranJeu.LARGEUR;i++)
                for(int j=0; j<ecranJeu.HAUTEUR ; j++)
                    ecranJeu.ecran [i][j]=Color.BLACK;
                
           f.repaint();
           ecranJeu.envoi=true;


            while(!pannel.perdu){//tant que l'on a pas perdu ...
            
             if(ecranJeu.formeSuivante instanceof O )
            {
            	 //on sauvegarde la formesuivante si une pi�ce o a �t� envoy�e par l'autre joueur
            	ecranJeu.currentForme=ecranJeu.formeSuivante;
                ecranJeu.formeSuivante=ecranJeu.formeSiO;
            	
            }
            
            else {
            		//la forme suivante devient forme courante
                    ecranJeu.currentForme=ecranJeu.formeSuivante;
                    //on cr�e une nouvelle forme
                    ecranJeu.newForme();

            }
                        
             
             if(corriace){
            	 
            	//si on recoit une corriace, on la rajoute dans la forme 
              	Random random2= new Random();
                  int rand2=random2.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                  ecranJeu.currentForme.getElementAt(rand2).setCouleur(this.pannel.couleurcoriace);
                  corriace=false;
             
            //Faire un random pour ajouter pieces speciales
            if (ligneDetruite){
            	//si on a detruit une ou plusieurs lignes, on a droit a une pi�ce so�ciale
            	//mais il ne faut pas que celle si remplace la corriace
                Random random= new Random();
                int rand=random.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                while(rand==rand2)
                rand=random.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                while(ecranJeu.currentForme instanceof O && rand==0) 
                rand=random.nextInt(this.ecranJeu.currentForme.getNbrElement());
                int coul;
                if(pieces_speciales.size()!=0){
                if(this.ecranJeu.casse>0){// si il y a deja une pi�ce casse pied sur le plateau on s'arrange pour qu'on ne puisse pas en avoir une autre
                if(pieces_speciales.size()!=1){    
                coul=random.nextInt(pieces_speciales.size()-1);
                Color special=Color.BLACK;
                special=pieces_speciales.elementAt(coul);
                if(special==Color.LIGHT_GRAY)this.ecranJeu.casse=50;
                ecranJeu.currentForme.getElementAt(rand).setCouleur(special);
                }
                }
                else{
                coul=random.nextInt(pieces_speciales.size());
                Color special=Color.BLACK;
                special=pieces_speciales.elementAt(coul);
                if(special==Color.LIGHT_GRAY)this.ecranJeu.casse=50;
                ecranJeu.currentForme.getElementAt(rand).setCouleur(special);
                }
                
                
                
                }
                ligneDetruite=false;//on remet la variable a false pour la prochaine boucle
            }}
            
            else {
            	 if (ligneDetruite){
                     //si on a detruit une ou plusieurs lignes, on a droit a une pi�ce so�ciale
                     Random random= new Random();
                     int rand=random.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                     while(ecranJeu.currentForme instanceof O && rand==0) 
                     rand=random.nextInt(this.ecranJeu.currentForme.getNbrElement());
                     int coul;
                     if(pieces_speciales.size()!=0){
                     if(this.ecranJeu.casse>0){// si il y a deja une pi�ce casse pied sur le plateau on s'arrange pour qu'on ne puisse pas en avoir une autre
                     if(pieces_speciales.size()!=1){    
                     coul=random.nextInt(pieces_speciales.size()-1);
                     Color special=Color.BLACK;
                     special=pieces_speciales.elementAt(coul);
                     if(special==Color.LIGHT_GRAY)this.ecranJeu.casse=50;
                     ecranJeu.currentForme.getElementAt(rand).setCouleur(special);
                     }
                     }
                     else{
                     coul=random.nextInt(pieces_speciales.size());
                     Color special=Color.BLACK;
                     special=pieces_speciales.elementAt(coul);
                     if(special==Color.LIGHT_GRAY)this.ecranJeu.casse=50;
                     ecranJeu.currentForme.getElementAt(rand).setCouleur(special);
                     }
                     
                     
                     
                     }
                     ligneDetruite=false;//on remet la variable a false pour la prochaine boucle
                 }
            }
            
            
           while(ecranJeu.currentForme.getPeutDescendre()){
           if(f.nouveauJeu)break;// si on clique sur menu nouveau jeu,il faut sortir de la boucle
           
           
           if(this.ecranJeu.casse==0){// si le compteur de la brique casse pied a atteint 0, il faut la faire disparaitre
        	   for(int b=4; b<ecranJeu.HAUTEUR ; b++){
       			for(int a=0;a<ecranJeu.LARGEUR;a++){
       			if(this.ecranJeu.ecran[a][b]==Color.LIGHT_GRAY)
       				this.ecranJeu.ecran[a][b]=Color.BLACK;       				
       			}
       			}
        	   
        	   
           }
           
           
            if(!(pannel.pause)) {// si la partie n'est pas en pause on fait descendre la forme et on envoi l'�cran a l'autre joueur
            	            	
                            ecranJeu.currentForme.descendre(ecranJeu);
                            f.repaint();
                            ecranJeu.envoi=true;
           try {
              Thread.sleep(speed);// On fait une p�riode d'attente qui depend de la vitesse du jeu
              this.ecranJeu.casse-=1;// on diminue le compteur de la piece casse pied
            } catch (InterruptedException ignore) {

           }
            }

         }
           if(f.nouveauJeu)break;
           nbligne=0;
           for(int i=4;i<ecranJeu.HAUTEUR;i++)
                if(ecranJeu.lignePleine(i)){
                    for (int j=0;j<ecranJeu.LARGEUR;j++){//si une ligne est pleine on v�rifie si on d�truit une brique bonus
                        if (ecranJeu.ecran[j][i]==Color.GREEN)
                            speed+=100;
                        else if (ecranJeu.ecran[j][i]==Color.CYAN)
	                        envoicorriace=true;     
                        else if (ecranJeu.ecran[j][i]==Color.BLUE){
                            supprLigneSpecial=true;
                            }
                        else if (ecranJeu.ecran[j][i]==Color.YELLOW){
                            lievre=true;
                            }
                        else if (ecranJeu.ecran[j][i]==Color.MAGENTA){
                                superCassePied=true;
                             }
                        else if (ecranJeu.ecran[j][i]==Color.WHITE ){// rajouter la variable tropdespeciales (cf jeu 2j)
                                O=true;
                              }
                        else if (ecranJeu.ecran[j][i]==Color.GRAY ){// rajouter la variable tropdespeciales (cf jeu 2j)
                            pickpocket=true;
                                                                    }
                             else if (ecranJeu.ecran[j][i]==Color.DARK_GRAY ){ // rajouter la variable tropdespeciales (cf jeu 2j)
                             echangiste1=true;
                                            }
                        }


                ecranJeu.supprLigne(i);// on d�truit la ligne pleine

                nbligne++;
                ligneDetruite=true;
            }
                              if(supprLigneSpecial){
                    ecranJeu.supprLigne(ecranJeu.HAUTEUR-1);
                    supprLigneSpecial=false;
                }			//on met a jour le score (plus on fait de ligne en m�me temps, plus le score est �l�v�)
                            if(nbligne==1)score+=100;
                            if(nbligne==2)score+=300;
                            if(nbligne==3)score+=800;
                            if(nbligne==4)score+=1500;
                            if(nbligne>=5)score+=2000;
                            f.pannel.label.setText("Score : "+score);
                            f.pannel.repaint();
							if (f.pannel2!=null)
                            f.pannel2.repaint();
                            if(nbpiece%10==0)speed-=5;


                  for(int i=0;i<ecranJeu.currentForme.getNbrElement();i++)
                {if(ecranJeu.currentForme.getElementAt(i).getY()<4)//si la forme courante est pos� et est en dehor de l'�cran on a perdu
                    f.pannel.perdu=true;                 
                if (ecranJeu.currentForme.getElementAt(i).getCouleur()==Color.ORANGE){
                	//si la fomr s'est pos�e et contenait une pi�ce voisin killer, alor on supprimes les pi�ce qui entouraient celle-ci
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()][ecranJeu.currentForme.getElementAt(i).getY()]=Color.BLACK;

                    if(ecranJeu.currentForme.getElementAt(i).getX()< ecranJeu.LARGEUR-1)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()+1][ecranJeu.currentForme.getElementAt(i).getY()]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getX()>0 )
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()-1][ecranJeu.currentForme.getElementAt(i).getY()]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getX()< ecranJeu.LARGEUR-1 && ecranJeu.currentForme.getElementAt(i).getY()< ecranJeu.HAUTEUR-1)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()+1][ecranJeu.currentForme.getElementAt(i).getY()+1]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getX()< ecranJeu.LARGEUR-1 && ecranJeu.currentForme.getElementAt(i).getY()>0)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()+1][ecranJeu.currentForme.getElementAt(i).getY()-1]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getY()< ecranJeu.HAUTEUR-1)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()][ecranJeu.currentForme.getElementAt(i).getY()+1]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getY()>0)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()][ecranJeu.currentForme.getElementAt(i).getY()-1]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getX()>0 && ecranJeu.currentForme.getElementAt(i).getY()>0)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()-1][ecranJeu.currentForme.getElementAt(i).getY()-1]=Color.BLACK;
                    if(ecranJeu.currentForme.getElementAt(i).getX()>0 && ecranJeu.currentForme.getElementAt(i).getY()< ecranJeu.HAUTEUR-1)
                    ecranJeu.ecran[ecranJeu.currentForme.getElementAt(i).getX()-1][ecranJeu.currentForme.getElementAt(i).getY()+1]=Color.BLACK;


                }	
            }
            }
            //On joue un son pour la fin de la partie
           if(pannel.perdu && f.notSon==false){

            MortSon sonmort=new MortSon();
            sonmort.start();
            
            }     	

            }}
    }}
