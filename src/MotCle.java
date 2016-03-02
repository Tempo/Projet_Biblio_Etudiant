import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;


public class MotCle extends Index implements Serializable{
    private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private String _motCle;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public MotCle(String motCle)
    {
        super();
        this.setMotCle(motCle);

    }                

// -----------------------------------------------
        // Getters
// -----------------------------------------------

    public String getMotCle(){
        return this._motCle;
    }

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setMotCle(String motCle){
        this._motCle = motCle;
    }
                
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public void afficherMotCle()
    {
            System.out.print(this.getMotCle());                      
    }
                                                                                           
}
