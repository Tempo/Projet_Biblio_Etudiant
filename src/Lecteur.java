import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Lecteur implements Serializable 
{
	
private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private String _nom;
    private String _prenom;
    private Integer _numLecteur;
    private GregorianCalendar _dateNaiss;
    private String _adresse;
    private String _tel;
    private HashSet<Emprunt> _emprunts;                

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Lecteur(String nom, String prenom, Integer numLecteur, GregorianCalendar dateNaiss, String adresse, String tel)
    {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setNumLecteur(numLecteur);
        this.setDateNaiss(dateNaiss);
        this.setAdresse(adresse);
        this.setTel(tel);
        this.setEmprunts(new HashSet<Emprunt>());
    }



//-----------------------------------------------
        //Getters
//-----------------------------------------------

    public HashSet<Emprunt> getEmprunts() {
        return _emprunts;
    }

    private String getNom() {
        return _nom;
    }

    private String getPrenom() {
        return _prenom;
    }

    private Integer getNumLecteur() {
        return _numLecteur;
    }

    private GregorianCalendar getDateNaiss() {
        return _dateNaiss;
    }

    private String getAdresse() {
        return _adresse;
    }

    private String getTel() {
        return _tel;
    }

    // -----------------------------------------------
            //Setters
    // -----------------------------------------------

    private void setNom(String nom) {
        this._nom = nom;
    }

    private void setPrenom(String prenom) {
        this._prenom = prenom;
    }

    private void setNumLecteur(Integer numLecteur) {
        this._numLecteur = numLecteur;
    }

    private void setDateNaiss(GregorianCalendar dateNaiss) {
        this._dateNaiss = dateNaiss;
    }

    private void setAdresse(String adresse) {
        this._adresse = adresse;
    }

    private void setTel(String tel) {
        this._tel = tel;
    }

    private void setEmprunts(HashSet<Emprunt> emprunts){
        this._emprunts = emprunts;
    }


// -----------------------------------------------
        // Methodes
// -----------------------------------------------


    public void afficherLecteur()
    {
        System.out.println("Numero lecteur : " + this.getNumLecteur());
        System.out.println("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());
        System.out.println("Age : " + this.calculAge() + " ans");
        System.out.println("Adresse : " + this.getAdresse());
        System.out.println("Telephone : " + this.getTel());
        EntreesSorties.afficherMessage("");
    }

    public void afficherNumNomPrenom()
    {
        System.out.println("Numero du lecteur: " + this.getNumLecteur());
        System.out.println("Prenom du lecteur: " + this.getPrenom());                    
        System.out.println("Nom du lecteur: " + this.getNom());                    
    }

    public void afficherNomPrenom()
    {
        System.out.println("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());                    
    }

        
    public void afficherEmprunts()
    {
        if (!this.getEmprunts().isEmpty())
        {
            for (Emprunt emprunt : this.getEmprunts())
            {
                emprunt.afficherEmprunt();
            }
        }
        else
        {
            System.out.println("Ce lecteur n'a pas d'emprunt en cours.");
        }
    }
		

    public Integer calculAge() {
        Integer age;
        GregorianCalendar dateNaissComp;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), _dateNaiss.get(GregorianCalendar.MONTH), _dateNaiss.get(GregorianCalendar.DATE));
        if(dateNaissComp.before(dateActuelle)){
                age=dateActuelle.get(GregorianCalendar.YEAR)-_dateNaiss.get(GregorianCalendar.YEAR);
        }
        else{
                age=dateActuelle.get(GregorianCalendar.YEAR)-_dateNaiss.get(GregorianCalendar.YEAR)-1;
        }
        return age;
    }
		                
    public boolean etatNonSature()
    {
        return this.nbEmprunts()<5;
    }

    public boolean etatSansEmprunt()
    {
        return (this.nbEmprunts()==0);
    }

    public void ajouterEmprunt(Emprunt emprunt)
    {
            this.lierEmprunt(emprunt);
    }

    public void supprimerEmprunt(Emprunt emprunt)
    {
            this.delierEmprunt(emprunt);
    }


    private Integer nbEmprunts()
    {
        Integer nb = 0;

        for (Emprunt emprunt : this.getEmprunts()){
            nb = nb + 1;
        }
        return nb;
    }

    private void lierEmprunt(Emprunt emprunt){
        this.getEmprunts().add(emprunt);
    }

    private void delierEmprunt(Emprunt emprunt){
        this.getEmprunts().remove(emprunt);
    }

}
