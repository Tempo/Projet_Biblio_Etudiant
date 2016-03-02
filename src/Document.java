import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Document implements Serializable{

private static final long serialVersionUID = 422L;
	
// -----------------------------------------------
    //Attributs
// -----------------------------------------------

    protected Titre _titre;
    protected GregorianCalendar _dateParution;
    protected HashSet<Auteur> _auteurs;
    protected HashSet<MotCle> _motsCles;                    

// -----------------------------------------------
    //Constructeur
// -----------------------------------------------

    public Document(Titre titre, GregorianCalendar dateParution, HashSet<Auteur> auteurs, HashSet<MotCle> motsCles)
    {
            this.setTitre(titre);
            this.setDateParution(dateParution);
            this.setAuteurs(auteurs);
            this.setMotsCles(motsCles);
            
            for (Auteur auteur : auteurs)
            {
                auteur.lierDocument(this);
            }

            for (MotCle motCle : motsCles)
            {
                motCle.lierDocument(this);
            }
            
            titre.lierDocument(this);
            
    }


// -----------------------------------------------
        //Setters
// -----------------------------------------------

    protected void setTitre(Titre titre){
        this._titre = titre;
    }

    protected void setDateParution(GregorianCalendar dateParution){
        this._dateParution = dateParution;
    }

    protected void setAuteurs(HashSet<Auteur> auteurs){
        this._auteurs = auteurs;
    }

    protected void setMotsCles(HashSet<MotCle> motsCles){
        this._motsCles = motsCles;
    }

//------------------------------------------------
        //Getters
//------------------------------------------------


    protected HashSet<Auteur> getAuteurs(){
        return this._auteurs;
    }

    protected HashSet<MotCle> getMotsCles(){
        return this._motsCles;
    }

    protected Titre getTitre(){
        return this._titre;
    }

    public GregorianCalendar getDateParution(){
        return this._dateParution;
    }                

                
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public void afficherDocument()
    {
        System.out.println("Titre : " + this.getTitre().getTitre());
        System.out.println("Date de parution : " + EntreesSorties.ecrireDate(this.getDateParution()));

        System.out.print("Nom de(s) auteur(s): ");
        String chaineAuteurs = "";
        for (Auteur auteur : this.getAuteurs()){
              chaineAuteurs = chaineAuteurs + auteur.getNomAuteur() + ", ";        
        }
        System.out.println(chaineAuteurs.substring(0, chaineAuteurs.length()-2));                        
    }                
}
