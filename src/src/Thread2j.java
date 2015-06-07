package src;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;

/**
 * Cette classe permet de créé un jeu un joueur ou un jeu deux joueur en réseau
 * @author Aurélie OTTAVI et Céline VERITE
 */
public class Thread2j extends Thread{

        /**
         * Fenetre dans laquelle le jeu s'exécute
         */
	Fenetre f;
        
         /**
         * Ecran du jeu dans lequel est stocké la matrice du plateau
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
	 * vitesse du jeu (correspond en fait à un temp d'attente de la thread)
	 */
	int speed;

        /**
	 * nombre de pièce que le joueur a eu
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
         * permet de savoir si l'autre joueur a envoyé une pièce corriace
         */
        boolean corriace;
        
        /**
         * permet d'avoir accès au jeu de l'autre lors de l'envoi (ou de l'action) de pièces spéciales)
         */
        Thread2j autreJeu;
        
        /**
         * tableau des pi�ces sp�ciales avec lesquelles on joue
         */
        Vector<Color> pieces_speciales;    
        
	
 
	/**
	 * Constructeur de la classe
	 * @param f Fenetre dans laquelle le jeu s'exécute
	 */
	public Thread2j(Pannel pannel,Fenetre f){
		this.pannel=pannel;
		ecranJeu=pannel.ecranJeu;
		this.f=f;
                this.pieces_speciales=new Vector<Color>();
                
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
                speed=1500;
                nbpiece=0;
                this.corriace=false;
                f.nouveauJeu=false;
                ligneDetruite=false;
                supprLigneSpecial=false;
                pannel.pause=false;
                pannel.perdu=false;
                pannel.gagne=false;
                boolean tropDeSpecial;
                boolean inverseJeu;
                this.ecranJeu.casse=-1;
                
                //initialisation des cases de l'ecran à noir
               for(int i=0;i<ecranJeu.LARGEUR;i++)
                    for(int j=0; j<ecranJeu.HAUTEUR ; j++)
                        ecranJeu.ecran [i][j]=Color.BLACK;

               f.repaint();


                while(!(pannel.perdu ||pannel.gagne) ){//tant que l'on a pas perdu ...

                    if(pannel.terroriste==1){pannel.terroriste+=1;autreJeu.pannel.pause=false;}
                    if(pannel.terroriste==2)pannel.terroriste=0;

                    if(ecranJeu.formeSuivante instanceof O )
                {
                    //on sauvegarde la formesuivante si une pièce O a été envoyée par l'autre joueur
                    ecranJeu.currentForme=ecranJeu.formeSuivante;
                    ecranJeu.formeSuivante=ecranJeu.formeSiO;
                    
                }

                else {  /*la forme suivante devient forme courante et la forme 
                        suivante est remplacée par une nouvelle forme*/
                        ecranJeu.currentForme=ecranJeu.formeSuivante;
                        ecranJeu.newForme();
                        

                }

                //Faire un random pour ajouter pieces speciales

                if(corriace){
                    //si on recoit une corriace, on la rajoute dans la forme 
                    Random random2= new Random();
                    int rand2=random2.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                    ecranJeu.currentForme.getElementAt(rand2).setCouleur(this.pannel.couleurcoriace);
                    corriace=false;

                    //Faire un random pour ajouter pieces speciales
                    if (ligneDetruite){                        
                        Random random= new Random();
                        /*si on a detruit une ou plusieurs lignes, on a droit a une piéce spéciale
                        mais il ne faut pas que celle si remplace la corriace*/
                        int rand=random.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                        while(rand==rand2)
                        rand=random.nextInt(this.ecranJeu.formeSuivante.getNbrElement());
                        int coul;
                        if(pieces_speciales.size()!=0){
                           /* si il y a deja une pièce casse pied sur le plateau 
                            on s'arrange pour qu'on ne puisse pas en avoir une autre */
                            if(this.ecranJeu.casse>0){
                                
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

                    } //on remet la variable a false pour la prochaine boucle
                        ligneDetruite=false;
                    }

                }



                else{

                if (ligneDetruite){
                    //si on a detruit une ou plusieurs lignes, on genere une pièce spéciale
                    Random random= new Random();          
                    int rand=random.nextInt(this.ecranJeu.currentForme.getNbrElement());
                    while(ecranJeu.currentForme instanceof O && rand==0) 
                    rand=random.nextInt(this.ecranJeu.currentForme.getNbrElement());

                    int coul;
                    if(pieces_speciales.size()!=0){
                        if(this.ecranJeu.casse>0){
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

                    ligneDetruite=false;
                }}
               while(ecranJeu.currentForme.getPeutDescendre()){

               /* si on clique sur menu nouveau jeu,il faut sortir de la boucle*/
               if(f.nouveauJeu)break;
               
               /* si le compteur de la brique casse pied a atteint 0, il faut la faire disparaitr*/
               if(this.ecranJeu.casse==0){
                       for(int b=4; b<ecranJeu.HAUTEUR ; b++){
                                for(int a=0;a<ecranJeu.LARGEUR;a++){
                                    if(this.ecranJeu.ecran[a][b]==Color.LIGHT_GRAY)
                                            this.ecranJeu.ecran[a][b]=Color.BLACK;       				
                                }
                        }      
               }

               /* si la partie n'est pas en pause on fait descendre la forme et on envoi l'écran a l'autre joueur*/
                if(!(pannel.pause)) {
                ecranJeu.currentForme.descendre(ecranJeu);
                                f.repaint();
               /* On effectue le temps d'attente correspondant à la vitesse du jeu*/
               try {
                   this.ecranJeu.casse-=1;// on diminue le compteur de la piece casse pied
                  Thread.sleep(speed);
                } catch (InterruptedException ignore) {

               }
                }

             }
               /* si on clique sur menu nouveau jeu,il faut sortir de la boucle*/
               if(f.nouveauJeu)break;
               nbligne=0;
               tropDeSpecial=false;
               inverseJeu=false;

               /* On regarde si une ligne est pleine */
               for(int i=4;i<ecranJeu.HAUTEUR;i++)
                    if(ecranJeu.lignePleine(i)){                       
                            for (int j=0;j<ecranJeu.LARGEUR;j++){
                                 /*si la ligne pleine comporte une piece speciale tortue on diminue la vitesse du jeu */
                                if (ecranJeu.ecran[j][i]==Color.GREEN)
                                    speed+=100;
                                 /*si la ligne pleine comporte une piece speciale lievre on augmente la vitesse du jeu */
                                if (ecranJeu.ecran[j][i]==Color.YELLOW)
                                    autreJeu.speed-=100;
                                 /*si la ligne pleine comporte une piece speciale corriace on met la varriable corriace à true */            
                                if (ecranJeu.ecran[j][i]==Color.CYAN)
                                        autreJeu.corriace=true;  
                                 /*si la ligne pleine comporte une piece speciale ligneKiller on met la variable ligneSpeciale à true */
                                if (ecranJeu.ecran[j][i]==Color.BLUE)
                                    supprLigneSpecial=true;
                                 /*si la ligne pleine comporte une piece speciale O on crée une forme O et on la stocke dans une variable */
                                 if (ecranJeu.ecran[j][i]==Color.WHITE && !tropDeSpecial){
                                        tropDeSpecial=true;
                                        autreJeu.ecranJeu.formeSiO=autreJeu.ecranJeu.formeSuivante;
                                        autreJeu.ecranJeu.formeSuivante=new O(autreJeu.ecranJeu);
                                    }
                                 /*si la ligne pleine comporte une piece speciale super casse pied on ajoute une ligne à trou à l'écran de l'adverssaire */
                                if (ecranJeu.ecran[j][i]==Color.MAGENTA){
                                        autreJeu.ecranJeu.addLigneATrou();
                                   }
                                 /*si la ligne pleine comporte une piece speciale pickpocket 
                                  on met la forme suivante de l'adverssaire dans la forme suivate 
                                  du joueur et on crée une nouvelle forme suivante pour l'adverssaire */
                                if (ecranJeu.ecran[j][i]==Color.GRAY && !tropDeSpecial){
                                        tropDeSpecial=true;
                                        this.ecranJeu.formeSuivante=autreJeu.ecranJeu.formeSuivante;
                                        autreJeu.ecranJeu.newForme();
                                    }
                                 /*si la ligne pleine comporte une piece speciale echangiste on echange les ecrans des deux joueurs */
                                if (ecranJeu.ecran[j][i]==Color.DARK_GRAY && !tropDeSpecial){
                                        tropDeSpecial=true;
                                        inverseJeu=true;
                                        for (int k=0; k<autreJeu.ecranJeu.currentForme.getNbrElement(); k++)
                                            autreJeu.ecranJeu.ecran[autreJeu.ecranJeu.currentForme.getElementAt(k).getX()][autreJeu.ecranJeu.currentForme.getElementAt(k).getY()]=Color.BLACK;
                                            Color [][] tmp=autreJeu.ecranJeu.ecran;
                                            autreJeu.ecranJeu.ecran=ecranJeu.ecran;
                                            ecranJeu.ecran=tmp;
                                            autreJeu.pannel.repaint();
                                            pannel.repaint();
                                        }
                                 /*si la ligne pleine comporte une piece speciale terroriste on met 
                                  le jeu du joueur en pause et on change les controleur de manière 
                                  à ce ke le joueur controle la piece courrnate de l'autre joueur */
                                if (ecranJeu.ecran[j][i]==Color.PINK){
                                        pannel.pause=true;
                                        autreJeu.pannel.terroriste=1;
                                        pannel.repaint();

                                }
                            }
                            if(inverseJeu)autreJeu.ecranJeu.supprLigne(i);
                            else ecranJeu.supprLigne(i);

                            nbligne++;
                            ligneDetruite=true;
                      }
                      /* Si une pièce ligne killer à étée detruite, on supprime une ligne supplementaire */  
                      if(supprLigneSpecial){
                            ecranJeu.supprLigne(ecranJeu.HAUTEUR-1);
                            supprLigneSpecial=false;
                      }

                      /* On augmente le score si une ligne à étée detruite et on le reaffiche*/
                      if(nbligne==1)score+=100;
                      if(nbligne==2)score+=300;
                      if(nbligne==3)score+=800;
                      if(nbligne==4)score+=1500;
                      if(nbligne>=5)score+=2000;   
                      pannel.label.setText("Score : "+score);
                      pannel.repaint();

                      /* On augmente la vitesse toute les dix pièces possées*/
                      if(nbpiece%10==0)speed-=5;


                      for(int i=0;i<ecranJeu.currentForme.getNbrElement();i++){

                          /* On test si l'un des joueurs à perdu 
                           (si la pièce est dans la partie non visible de l'écran)*/
                          if(ecranJeu.currentForme.getElementAt(i).getY()<4){
                                pannel.perdu=true; 
                                autreJeu.pannel.gagne=true;
                           }

                     /* On effectue l'action de la pièce voisinKiller*/
                          if (ecranJeu.currentForme.getElementAt(i).getCouleur()==Color.ORANGE){

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

               /* Lorsque le joueur à perdu, on joue le son de game over si le joueur 
                  n'a pas séléctioné l'option qui permet d'enlever le son */
                if(pannel.perdu && f.notSon==false){ 
                        MortSon sonmort=new MortSon();
                        sonmort.start();
                    }        	
           }
       }
    }
}

