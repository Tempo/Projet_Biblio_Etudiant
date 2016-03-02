import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class Ouvrage extends Document implements Serializable
{
	
    private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private String _ISBN;
    private String _nomEditeur;
    private PublicCible _publicCible;
    private Integer _nbExemplaires;
    private HashMap<Integer, Exemplaire> _dicoExemplaire;
    private Integer _derNumEx;             


// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Ouvrage(String ISBN, Titre titre, HashSet<Auteur> auteurs, String nomEditeur, PublicCible publicCible, GregorianCalendar dateParution, HashSet<MotCle> motsCles)
    {
        super(titre, dateParution, auteurs, motsCles);
        this.setISBN(ISBN);
        this.setNomEditeur(nomEditeur);
        this.setPublicCible(publicCible);
        this.setNbExemplaires(0);
        this.setExemplaires(new HashMap<Integer, Exemplaire>());                        
        this.setDerNumEx(0);
    }

               
//------------------------------------------------
        //Getters
//------------------------------------------------


    public PublicCible getPublicCible()
    {
        return _publicCible;
    }

    private Integer getDerNumEx()
    {
        return _derNumEx;
    }

    private String getISBN()
    {
        return _ISBN;
    }

    private String getNomEditeur()
    {
        return _nomEditeur;
    }

    private Integer getNbExemplaires()
    {
        return _nbExemplaires;
    }

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setISBN(String ISBN)
    {
        this._ISBN = ISBN;
    }

    private void setNomEditeur(String nomEditeur)
    {
        this._nomEditeur = nomEditeur;
    }

    private void setPublicCible(PublicCible publicCible)
    {
        this._publicCible = publicCible;
    }

    private void setNbExemplaires(Integer nbExemplaires)
    {
        this._nbExemplaires = nbExemplaires;
    }

    private void setDerNumEx(Integer derNumEx)
    {
        this._derNumEx = derNumEx;
    }

    private void setExemplaires(HashMap<Integer, Exemplaire> dicoExemplaire)
    {
        this._dicoExemplaire = dicoExemplaire;
    }                

                          
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public Exemplaire unExemplaire(Integer numExemplaire)
    {
        return _dicoExemplaire.get(numExemplaire);
    }


    public void afficherOuvrage()
    {
            System.out.println("ISBN : " + this.getISBN());
            super.afficherDocument();

            System.out.println("Nom de l'éditeur: " + this.getNomEditeur());
            System.out.println("Public Cible: " + this.getPublicCible());
            System.out.println("Nombre d'exemplaires: " + this.getNbExemplaires());
    }

    public void afficherOuvragesMotsCles()
    {
        this.afficherOuvrage();

        System.out.print("mot(s) clé(s): ");
        String chaineMotsCles = "";
        for (MotCle motCle : this.getMotsCles()){
            chaineMotsCles = chaineMotsCles + motCle.getMotCle() + ", ";        
        }
        System.out.println(chaineMotsCles.substring(0, chaineMotsCles.length()-2));                     
    }


    public void afficherTitreNbEx()
    {
            System.out.println("Titre de l'ouvrage: " + this.getTitre());
            System.out.println("Nombre d'exemplaires: " + this.getNbExemplaires());
            EntreesSorties.afficherMessage("");
    }


    public void afficherISBNTitre()
    {
            System.out.println("ISBN : " + this.getISBN());
            System.out.println("Titre de l'ouvrage: " + this.getTitre());
    }


    public void afficherExemplaires()
    {                    
        for (Iterator<Exemplaire> iter = this.lesExemplaires(); iter.hasNext();){
            iter.next().afficherExemplaire();
        }                    
    }                    


    public void nouvelExemplaire(GregorianCalendar dateReception, Disponibilite dispo){

        this.nouveauNumEx();
        Integer numExemplaire = this.getDerNumEx();

        Exemplaire E = unExemplaire(numExemplaire);

        if (E == null) 
        {
            E = new Exemplaire(numExemplaire, dateReception, dispo, this);

            this.ajouterUnNbExemplaire();
            this.lierExemplaire(numExemplaire, E);
            EntreesSorties.afficherMessage("Le nouvel exemplaire a été créé avec succès.");                        
        }
        else 
        {
            EntreesSorties.afficherMessage("Ce numero d'exemplaire existe deja.");
        }
    }
          
    public boolean etatSansExemplaire()
    {
        return this.getNbExemplaires()==0;
    }
                
    private void lierExemplaire(Integer numExemplaire, Exemplaire E)
    {
        _dicoExemplaire.put(numExemplaire, E);
    }
        
    private void nouveauNumEx(){
        Integer _NumTempEx;
        _NumTempEx = this.getDerNumEx();
        _NumTempEx = _NumTempEx + 1;
        this.setDerNumEx(_NumTempEx);
    }

    private void ajouterUnNbExemplaire(){
        Integer nbExTemp;
        nbExTemp = this.getNbExemplaires();
        nbExTemp = nbExTemp + 1;
        this.setNbExemplaires(nbExTemp);
    }
                
   private Iterator<Exemplaire> lesExemplaires()
   {
   return _dicoExemplaire.values().iterator();
   }
                
}
