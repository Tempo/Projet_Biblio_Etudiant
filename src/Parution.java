import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Parution implements Serializable{
    private static final long serialVersionUID = 422L;
	
// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private String _ISSN;
    private Integer _numParution;
    private String _titreParution;
    private GregorianCalendar _dateParution;
    private Integer _nbArticles;
    private HashMap<Titre, Article> _dicoArticle;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Parution(String ISSN, Integer numParution, String titreParution, GregorianCalendar dateParution)
    {
        this.setISSN(ISSN);
        this.setNumParution(numParution);
        this.setTitreParution(titreParution);
        this.setDateParution(dateParution);
        this.setNbArticles(0);
        this.setArticles(new HashMap<Titre, Article>());                        
    }

// -----------------------------------------------
        // Getters
// -----------------------------------------------                

    public GregorianCalendar getDateParution()
    {
        return _dateParution;
    }

    private String getISSN()
    {
        return _ISSN;
    }

    private Integer getNumParution()
    {        
        return _numParution;
    }

    private String getTitreParution()
    {
        return _titreParution;
    }

    private Integer getNbArticles()
    {
        return _nbArticles;
    }

    private HashMap<Titre, Article> getArticle()
    {
        return _dicoArticle;
    }                

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setISSN(String ISSN)
    {
        this._ISSN = ISSN;
    }

    private void setNumParution(Integer numParution)
    {
        this._numParution = numParution;
    }

    private void setTitreParution(String titreParution)
    {
        this._titreParution = titreParution;
    }

    private void setDateParution(GregorianCalendar dateParution)
    {
        this._dateParution = dateParution;
    }

    private void setNbArticles(Integer nbArticles)
    {
        this._nbArticles = nbArticles;
    }

    private void setArticles(HashMap<Titre, Article> dicoArticle)
    {
        this._dicoArticle = dicoArticle;
    }                

// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public Article unArticle(Titre titre)
    {
        return _dicoArticle.get(titre);
    }

    public void afficherParution()
    {
        System.out.println("Titre de la parution: " + this.getTitreParution());

        System.out.println("Date de parution: " + EntreesSorties.ecrireDate(this.getDateParution()));
        System.out.println("Nombre d'articles: " + this.getNbArticles());
        EntreesSorties.afficherMessage("");
    }

    public void afficherTitreNbArt()
    {
        System.out.println("Titre de la parution: " + this.getTitreParution());
        System.out.println("Nombre d'articles: " + this.getNbArticles());
        EntreesSorties.afficherMessage("");
    }


    public void afficherISSNTitreDate()
    {
        System.out.println("ISSN : " + this.getISSN());
        System.out.println("Numéro de parution : " + this.getNumParution());        
        System.out.println("Titre du périodique: " + this.getTitreParution());
    } 

    public void afficherArticles()
    {                    
        for (Iterator<Article> iter = this.lesArticles(); iter.hasNext();)
        {
            iter.next().afficherArticleMotsClés();
            System.out.println("");
            System.out.print("");            
        }                    
    }                    


    public void nouvelArticle(Titre titre, GregorianCalendar dateEcriture, HashSet<Auteur> auteurs, Integer numPage, Parution parution, HashSet<MotCle> motsCles)
    {
        Article a = unArticle(titre);

        if (a == null) 
        {
            a = new Article(titre, dateEcriture, auteurs, numPage, this, motsCles);

            this.ajouterUnNbArticle();
            this.lierArticle(titre, a);
            EntreesSorties.afficherMessage("Le nouvel article a été créé avec succés!");                                                   
        }
        else 
        {
            EntreesSorties.afficherMessage("ERREUR : Cet article est déjà enregistré...");
        }
    }
                      
    public boolean etatSansArticle()
    {
        return this.getNbArticles()==0;
    }

    private void lierArticle(Titre titre, Article a)
    {
        _dicoArticle.put(titre, a);
    }

    private void ajouterUnNbArticle(){
        Integer nbArtTemp;
        nbArtTemp = this.getNbArticles();
        nbArtTemp = nbArtTemp + 1;
        this.setNbArticles(nbArtTemp);
    }

    private void supprimerUnNbArticle() {
        Integer nbArtTemp;
        nbArtTemp = this.getNbArticles();
        nbArtTemp = nbArtTemp- 1;
        this.setNbArticles(nbArtTemp);
    }

    private Iterator<Article> lesArticles() {
    return _dicoArticle.values().iterator();
    }

}

