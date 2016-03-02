import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Article extends Document implements Serializable{
     	private static final long serialVersionUID = 422L;
	
// -----------------------------------------------
	//Attributs
// -----------------------------------------------
        private Integer _numPage;
        private Parution _parution;
             
// -----------------------------------------------
	//Constructeur
// -----------------------------------------------
		
        public Article(Titre titre, GregorianCalendar dateEcriture, HashSet<Auteur> auteurs, Integer numPage, Parution parution, HashSet<MotCle> motsCles)
        {
                super(titre, dateEcriture, auteurs, motsCles);
                this.setNumPage(numPage);
                this.setParution(parution);
        }
		

// -----------------------------------------------
	// Getters
// -----------------------------------------------
                
    private Integer getNumPage(){
        return this._numPage;
    }
    
    private Parution getParution(){
        return this._parution;
    }
                  
// -----------------------------------------------
	//Setters
// -----------------------------------------------
                                                                                                                           
    private void setNumPage(Integer numPage){
        this._numPage = numPage;
    }

    private void setParution(Parution parution){
        this._parution = parution;
    }

    
    
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    public void afficherArticle()
    {
            super.afficherDocument();                        
            System.out.println("Numéro de la page où trouver l'article : " + this.getNumPage());
    }
    
    public void afficherISSNTitreDateArticle()
    {
            this.getParution().afficherISSNTitreDate();
            super.afficherDocument();                        
            System.out.println("Numéro de la page où trouver l'article : " + this.getNumPage());
    }    

    public void afficherArticleMotsClés()
    {                          
            this.afficherArticle();
            System.out.print("mot(s) clé(s): ");
            String chaineMotsCles = "";
            for (MotCle motCle : this.getMotsCles()){
                  chaineMotsCles = chaineMotsCles + motCle.getMotCle() + ", ";        
            }
            if(chaineMotsCles.length()>2)
                System.out.println(chaineMotsCles.substring(0, chaineMotsCles.length()-2));                        
    }                
                                                                          
    private void lierParution(Parution parution)
    {
        this.setParution(parution);
    }                
}

