package src;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
  * Classe de type thread permettant de lire le son FATALITY2.wav à la fin du jeu
  */
class MortSon extends Thread {
    
    /* variable permettant de jouer le son */
    private JouerSon mortSon;

    /* chemin d'accès relatif du son */
    private String cheminAccesCourrant ="./src/son/";
    
   /**
    * Constructeur 
    */
    	MortSon() {
            mortSon =new JouerSon(cheminAccesCourrant+"FATALITY2.WAV");
        }

   /**
    * méthode qui lance le thread
    */
    public void run() {
        InputStream stream = new ByteArrayInputStream(mortSon.getSamples());
        mortSon.play(stream); 
    }
}