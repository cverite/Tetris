package src;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
  * Classe de type thread permettant de lire le son tetris.wav en meme temps que le jeu se déroule
  */
class MonThread extends Thread {
    
    /* variable permettant de jouer le son */
    private JouerSon tetrisSon;
       
    /* chemin d'accès relatif du son */
    private String cheminAccesCourrant ="./src/son/";
    
   /**
    * Constructeur 
    */
    MonThread() {
           tetrisSon =new JouerSon(cheminAccesCourrant+"tetris.wav");
    }

   /**
    * méthode qui lance le thread
    */
    public void run() {

       while(true){
            InputStream stream = new ByteArrayInputStream(tetrisSon.getSamples());
            tetrisSon.play(stream); 
        }
    }
}