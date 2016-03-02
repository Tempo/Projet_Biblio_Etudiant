import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;

public class Index implements Serializable{
    private static final long serialVersionUID = 422L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private HashSet<Document> _documents;

// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Index()
    {
          this.setDocument(new HashSet<Document>());

    }                
                
// -----------------------------------------------
        // Getters
// -----------------------------------------------

    public HashSet<Document> getDocument()
    {
        return this._documents;
    }                

// -----------------------------------------------
        //Setters
// -----------------------------------------------

    private void setDocument(HashSet<Document> documents)
    {
        this._documents = documents;
    }                

                
// -----------------------------------------------
        // Methodes
// -----------------------------------------------

    
    protected void afficherDocumentsTitre()
    {
        for (Document document : this.getDocument()){
              document.afficherDocument();
        }
    }    
                          
    protected void lierDocument(Document document)
    {
        this._documents.add(document);
    }
            
}