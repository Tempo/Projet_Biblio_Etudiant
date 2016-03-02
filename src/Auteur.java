import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


import java.io.Serializable;


public class Auteur extends Index implements Serializable{
    	private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private String _nomAuteur;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Auteur(String nomAuteur)
    {
        super();  
        this.setNomAuteur(nomAuteur);

    }                

       
// -----------------------------------------------
        // Getters
// -----------------------------------------------

    public String getNomAuteur(){
        return this._nomAuteur;
    }

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setNomAuteur(String nom){
        this._nomAuteur = nom;
    }

// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public void afficherAuteur()
    {
        System.out.print(this.getNomAuteur());                      
    }
}
