import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;

public class Titre extends Index implements Serializable{
    private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private String _titre;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Titre(String titre)
    {     
          super();
          this.setTitre(titre);
    }                

                
// -----------------------------------------------
        // Getters
// -----------------------------------------------

    public String getTitre()
    {
        return this._titre;
    }

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setTitre(String motCle)
    {
        this._titre = motCle;
    }
                
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

        
    public void afficherTitre()
    {
        System.out.println(this.getTitre());                      
    }
                                      
}
