import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Emprunt implements Serializable 
{
	
private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private GregorianCalendar _dateEmprunt;
    private GregorianCalendar _dateRetour;
    private Exemplaire _exemplaire;
    private Lecteur _lecteur;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Emprunt(Exemplaire exemplaire, Lecteur lecteur, GregorianCalendar dateEmprunt)
    {
        this.lierExemplaire(exemplaire);
        this.lierLecteur(lecteur);
        lecteur.ajouterEmprunt(this);
        exemplaire.ajouterEmprunt(this);                        
        this.setDateEmprunt(dateEmprunt);

    try 
    {
        GregorianCalendar dateRetour = new GregorianCalendar();
        dateRetour.set(dateEmprunt.get(Calendar.YEAR), dateEmprunt.get(Calendar.MONTH), dateEmprunt.get(Calendar.DAY_OF_MONTH));
        dateRetour.add(Calendar.DAY_OF_MONTH,8);               
        this.setDateRetour(dateRetour);

    } catch (Exception e) 
        {
             System.out.println("La date de retour n'a pas initialisée correctement");
        }                                                
    }
		
// -----------------------------------------------
        //Getters
// -----------------------------------------------

    private Lecteur getLecteur(){
        return this._lecteur;
    }

    private Exemplaire getExemplaire(){
        return this._exemplaire;
    }

    private GregorianCalendar getDateEmprunt() {
            return this._dateEmprunt;
    }

    private GregorianCalendar getDateRetour() {
            return this._dateRetour;
    }

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setExemplaire(Exemplaire exemplaire){
        this._exemplaire = exemplaire;
    }

    private void setLecteur(Lecteur lecteur){
        this._lecteur = lecteur;
    }

    private void setDateEmprunt(GregorianCalendar dateEmprunt){
        this._dateEmprunt = dateEmprunt;
    }

    private void setDateRetour(GregorianCalendar dateRetour){
        this._dateRetour = dateRetour;
    }
                            
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public void afficherEmprunt()
    {
        this.getExemplaire().afficherEmpruntExemplaire();
        System.out.println("Date d'emprunt: " + EntreesSorties.ecrireDate(this.getDateEmprunt()));
        System.out.println("Date de retour: " + EntreesSorties.ecrireDate(this.getDateRetour()));
        EntreesSorties.afficherMessage("");                    
    }

    public void afficherLecteurEmprunt()
    {
        Lecteur l;
        l = this.getLecteur();
        l.afficherNumNomPrenom();
    }

    public void afficherDatesEmprunt()
    {
        System.out.println("Date d'emprunt: " + EntreesSorties.ecrireDate(this.getDateEmprunt()));
        System.out.println("Date de retour: " + EntreesSorties.ecrireDate(this.getDateRetour()));                    
    }        

         
    public void rendreEmprunt()
    {
        this.getExemplaire().rendreEmprunt();
        this.getLecteur().supprimerEmprunt(this);
    }

     
    public boolean retard()
    {
        GregorianCalendar dateRetard = new GregorianCalendar();
        GregorianCalendar aujourdhui = new GregorianCalendar();
        dateRetard.set(this.getDateEmprunt().get(Calendar.YEAR), this.getDateEmprunt().get(Calendar.MONTH), this.getDateEmprunt().get(Calendar.DAY_OF_MONTH));
        dateRetard.add(Calendar.DAY_OF_MONTH,15);
        return aujourdhui.after(dateRetard);
    }

    private void lierExemplaire(Exemplaire E)
    {
        this.setExemplaire(E);
    }


    private void lierLecteur(Lecteur L)
    {
        this.setLecteur(L);
    }           
}
                
