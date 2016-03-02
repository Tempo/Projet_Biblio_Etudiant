import java.io.Serializable;
import java.util.GregorianCalendar;

// Classe de gestion d'exemplaire

public class Exemplaire implements Serializable 
{
	
private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private Integer _numExemplaire;
    private GregorianCalendar _dateReception;
    private Disponibilite _dispo;
    private Ouvrage _ouvrage;
    private Emprunt _emprunt;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Exemplaire(Integer numExemplaire, GregorianCalendar dateReception, Disponibilite dispo, Ouvrage ouvrage)
    {
            this.setNumExemplaire(numExemplaire);
            this.setDateReception(dateReception);
            this.setDisponibilite(dispo);
            this.lierOuvrage(ouvrage);
            this.setEmprunt(null);
    }
		

//----------------------------------------------
        // Getters
//----------------------------------------------

    public Emprunt getEmprunt(){
            return _emprunt;
    }
    public GregorianCalendar getDateReception(){
            return _dateReception;
    }                

    private Integer getNumExemplaire(){
            return _numExemplaire;
    }

    private Disponibilite getDisponibilite(){
            return _dispo;
    }

    private Ouvrage getOuvrage(){
            return _ouvrage;
    }

    // -----------------------------------------------
            //Setters
    // -----------------------------------------------

    private void setNumExemplaire(Integer numExemplaire){
            this._numExemplaire = numExemplaire;
    }

    private void setDateReception(GregorianCalendar dateReception){
            this._dateReception = dateReception;
    }

    private void setDisponibilite(Disponibilite dispo){
            this._dispo = dispo;
    }

    private void setOuvrage(Ouvrage ouvrage){
        this._ouvrage = ouvrage;
    }

    private void setEmprunt(Emprunt emprunt){
        this._emprunt = emprunt;
    }    
    
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public void afficherExemplaire()                 
    {
        Emprunt e;
        System.out.println("Numéro d'exemplaire: " + this.getNumExemplaire());
        System.out.println("Date de réception : " + EntreesSorties.ecrireDate(this.getDateReception()));
        System.out.println("Public cible : " + this.getOuvrage().getPublicCible());                        
        System.out.println("Cet exemplaire est " + this.getDisponibilite());
        if (this.empruntable())
        {
            if (this.etatNonEmprunte())
            {
                System.out.println("Exemplaire actuellement non emprunté");
            }
            else
            {
                e = this.getEmprunt();
                System.out.println("Exemplaire actuellement emprunté");
                e.afficherLecteurEmprunt();
                e.afficherDatesEmprunt();
            }
        }
        EntreesSorties.afficherMessage("");
    }

    public void afficherEmpruntExemplaire()
    {                        
        this.getOuvrage().afficherISBNTitre();
        this.afficherNumExemplaire();
    }


    public void afficherNumExemplaire()
    {
        System.out.println("Numéro d'exemplaire: " + this.getNumExemplaire());                    
    }


    public boolean etatNonEmprunte()
    {
        return this.getEmprunt()==null;
    }

    public boolean empruntable()
    {
        return this.getDisponibilite().equals(Disponibilite.empruntable);
    }


    public void ajouterEmprunt(Emprunt emprunt){
        this.lierEmprunt(emprunt);
    }


    public void rendreEmprunt(){
        this.delierEmprunt();
    }                
              
    private void lierOuvrage(Ouvrage ouvrage){
        this.setOuvrage(ouvrage);
    }

    private void lierEmprunt(Emprunt emprunt){
        this.setEmprunt(emprunt);
    }

    private void delierEmprunt(){
        this.setEmprunt(null);
    }
}
		
		
