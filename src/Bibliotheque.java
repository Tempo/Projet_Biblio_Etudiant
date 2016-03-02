import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class Bibliotheque implements Serializable 
{
	
private static final long serialVersionUID = 262L;

// -----------------------------------------------
        //Attributs
// -----------------------------------------------

    private HashMap<Integer, Lecteur> _dicoLecteur;
    private HashMap<String, Ouvrage> _dicoOuvrage;
    private HashMap<String, Parution> _dicoParution;
    private HashMap<String, Auteur> _dicoAuteurs;
    private HashMap<String, MotCle> _dicoMotsCle;
    private HashMap<String, Titre> _dicoTitres;
    private Integer _derNumLect;


// -----------------------------------------------
        //Constructeur
// -----------------------------------------------

    public Bibliotheque() 
    {
        this.setLecteurs(new HashMap<Integer, Lecteur>());
        this.setOuvrages(new HashMap<String, Ouvrage>());
        this.setParutions(new HashMap<String, Parution>());
        this.setAuteurs(new HashMap<String, Auteur>());
        this.setMotsCle(new HashMap<String, MotCle>());
        this.setTitres(new HashMap<String, Titre>());
        this.setDerNumLect(0);
    }

    
//-----------------------------------------------
        //Getters
//-----------------------------------------------

    private Integer getDerNumLect(){
        return _derNumLect;
    }

// -----------------------------------------------
        // Setters
// -----------------------------------------------

    private void setLecteurs(HashMap<Integer, Lecteur> dicoLecteur) {
            this._dicoLecteur = dicoLecteur;
    }

    private void setOuvrages(HashMap<String, Ouvrage> dicoOuvrage){
            this._dicoOuvrage = dicoOuvrage;
    }

    private void setParutions(HashMap<String, Parution> dicoParution){
            this._dicoParution = dicoParution;
    }

    private void setAuteurs(HashMap<String, Auteur> dicoAuteurs){
            this._dicoAuteurs = dicoAuteurs;                
    }

    private void setMotsCle(HashMap<String, MotCle> dicoMotsCle){
            this._dicoMotsCle = dicoMotsCle;
    }

    private void setTitres(HashMap<String, Titre> dicoTitres){
            this._dicoTitres = dicoTitres;
    }

    private void setDerNumLect(Integer derNumLect){
            this._derNumLect = derNumLect;
    }


// -----------------------------------------------
        // Methodes de création des objets
// -----------------------------------------------

    public void nouveauLecteur()
    {
        this.nouveauNumLect();
        Integer numLecteur = this.getDerNumLect();

        Lecteur L = unLecteur(numLecteur);

        if (L == null) 
        {
            String nom = EntreesSorties.lireChaine("Entrez le nom : ");
            String prenom = EntreesSorties.lireChaine("Entrez le prenom : ");
            Integer age;
            GregorianCalendar dateNaiss, dateNaissComp;
            GregorianCalendar dateActuelle = new GregorianCalendar();
            do {
                dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur : ");
                dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
                if(dateNaissComp.before(dateActuelle)){
                    age=dateActuelle.get(GregorianCalendar.YEAR)-dateNaiss.get(GregorianCalendar.YEAR);
                }
                else{
                    age=dateActuelle.get(GregorianCalendar.YEAR)-dateNaiss.get(GregorianCalendar.YEAR)-1;
                }
                if ((age<=3) | (age>=110)){
                    EntreesSorties.afficherMessage("Age incorrecte ("+age+"), veuillez recommencer.");
                }
                else {
                    EntreesSorties.afficherMessage("Age du lecteur : " + age + " ans");
                }
            } while ((age<=3) | (age>=110));
            String adresse = EntreesSorties.lireChaine("Entrez l'adresse : ");
            String tel = EntreesSorties.lireChaine("Entrez le numero de telephone : ");
            EntreesSorties.afficherMessage("Fin de saisie");

            L = new Lecteur(nom, prenom, numLecteur, dateNaiss, adresse, tel);
            EntreesSorties.afficherMessage("Le nouveau lecteur a été créé avec succés!");
            EntreesSorties.afficherMessage("Le numéro de ce lecteur est le " + numLecteur);
            lierLecteur(numLecteur, L);
        }
        else {
            EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur existe déjà.");
        }

    }

    public void nouvelOuvrage()
    {
        boolean ok = false;
        String ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'ouvrage : ");
        Ouvrage O = unOuvrage(ISBN);

        if (O == null) 
        {
            String titre = EntreesSorties.lireChaine("Entrez le titre de l'ouvrage : ");
            Titre titre_o = this.unTitre(titre);
            if(titre_o== null){
                titre_o = new Titre(titre);
                this.lierTitre(titre, titre_o);
            }                                 

            HashSet<Auteur> auteurs = new HashSet<Auteur>();
            String nomAuteur;
            do{
                 nomAuteur = EntreesSorties.lireChaine ("Entrez un nom d'auteur ou tapez fin pour arrêter la saisie : ");                             
                 if (!(nomAuteur.equals ("fin"))){
                     Auteur auteur = this.unAuteur(nomAuteur);

                     if(auteur== null){
                         auteur = new Auteur(nomAuteur);
                         this.lierAuteur(nomAuteur, auteur);
                     }

                     auteurs.add(auteur);                                 
                 }

            }while(!(nomAuteur.equals("fin")));                                    

            String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur : ");
            do{
                try{
                        String sPublicCible = EntreesSorties.lireChaine("Public Cible (enfant/ado/adulte)? : ");                                    
                        PublicCible publicCible = PublicCible.valueOf(sPublicCible);
                        ok = true;
                        GregorianCalendar dateParution;
                        dateParution = EntreesSorties.lireDate("Entrez la date de parution : ");

                        HashSet<MotCle> motsCles = new HashSet<MotCle>();
                        String motCle;
                        do{                                    
                            motCle = EntreesSorties.lireChaine ("Entrez un mot clé ou tapez fin pour arrêter la saisie : ");                             
                            if (!(motCle.equals ("fin")))
                            {
                                MotCle motCle_o = this.unMotCle(motCle);

                                if(motCle_o== null)
                                {
                                    motCle_o = new MotCle(motCle);
                                    this.lierMotCle(motCle, motCle_o);
                                }                                 
                                motsCles.add(motCle_o);                                 
                            }                                 
                        }while(!(motCle.equals("fin")));

                    EntreesSorties.afficherMessage("Fin de saisie");

                    O = new Ouvrage(ISBN, titre_o, auteurs, nomEditeur, publicCible, dateParution, motsCles);
                    EntreesSorties.afficherMessage("Le nouvel ouvrage a été créé avec succés!");                                    
                    }catch (Exception e){
                    ok = false;
                    System.out.println("ERREUR : le public cible saisi est invalide...");
                    }
            }while(!ok);			

            lierOuvrage(ISBN, O);                   
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : ce numéro ISBN existe déjà pour un ouvrage.");
        }

    }
    
    public void nouvelExemplaire()
    {

        String ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'exemplaire : ");
        Ouvrage o = unOuvrage(ISBN);
        String nouvelExemplaire = "oui";
        GregorianCalendar dateReception = null;

        if (o != null) 
        {
         while(nouvelExemplaire.equals("oui")) 
         {
            dateReception = EntreesSorties.lireDate("Entrez la date de réception de l'exemplaire : ");
            if (!dateReception.before(o.getDateParution()))
            {
                String sDispo = EntreesSorties.lireChaine("Disponibilité de l'exemplaire (empruntable/consultable)?");                                                             
                if((sDispo.equals("consultable")) || (sDispo.equals("empruntable")))
                {
                    Disponibilite dispo = Disponibilite.valueOf(sDispo);                                    
                    o.nouvelExemplaire(dateReception, dispo);                          
                }
                else
                {
                    System.out.println("ERREUR : la disponibilité saisie est invalide...");                                
                }
            }
            else
            {
                System.out.println("ERREUR : la date de réception de l'exemplaire doit être postérieure à la date de parution de l'ouvrage"  + " (" + EntreesSorties.ecrireDate(o.getDateParution()) + ")");                            
            }                              
            nouvelExemplaire = EntreesSorties.lireChaine("Voulez-vous rentrer un autre exemplaire (oui/non)?");

            while (!nouvelExemplaire.equals("oui") && !nouvelExemplaire.equals("non")){
            nouvelExemplaire = EntreesSorties.lireChaine("Voulez-vous rentrer un autre exemplaire (oui/non)?");                        
            }  
         }                 
        }
        else 
        {
                EntreesSorties.afficherMessage("ERREUR : ce numéro ISBN n'existe pas...");
        }

    }

    public void nouvelleParution()
    {
        String ISSN = EntreesSorties.lireChaine("Entrez le numéro ISSN du périodique : ");
        Integer numParution = EntreesSorties.lireEntier("Entrez le numéro de la parution : ");
        String codeParution = ISSN + "_" + numParution;
        Parution p = uneParution(codeParution);

        if (p == null) 
        {
            String titreParution = EntreesSorties.lireChaine("Entrez le titre du périodique : ");
            GregorianCalendar dateParution;
            dateParution = EntreesSorties.lireDate("Entrez la date de parution du périodique: ");
            EntreesSorties.afficherMessage("Fin de saisie");

            p = new Parution(ISSN, numParution, titreParution, dateParution);
            EntreesSorties.afficherMessage("La nouvelle parution a été créé avec succés!");                                                                                                  
            lierParution(codeParution, p);                   
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : cet ISSN et ce numéro de parution existe déjà.");
        }
    }    
    
    public void nouvelArticle()
    {
        String ISSN = EntreesSorties.lireChaine("Entrez le numéro ISSN du périodique : ");
        Integer numParution = EntreesSorties.lireEntier("Entrez le numéro de la parution : ");
        String codeParution = ISSN + "_" + numParution;
        Parution p = uneParution(codeParution);

        String nouvelArticle = "oui";
        GregorianCalendar dateEcriture = null;

        if (p != null) 
        {
         while(nouvelArticle.equals("oui")) 
         {
            dateEcriture = EntreesSorties.lireDate("Entrez la date d'écriture de l'article : ");
            if (!dateEcriture.before(p.getDateParution()))
            {
                String titre = EntreesSorties.lireChaine("Entrez le titre de l'article : ");
                Titre titre_o = this.unTitre(titre);
                if(titre_o== null){
                    titre_o = new Titre(titre);
                    this.lierTitre(titre, titre_o);
                }

                HashSet<Auteur> auteurs = new HashSet<Auteur>();
                String nomAuteur;
                do{
                     nomAuteur = EntreesSorties.lireChaine ("Entrez un nom d'auteur ou tapez fin pour arrêter la saisie : ");                             
                    if (!(nomAuteur.equals ("fin")))
                    {
                         Auteur auteur = this.unAuteur(nomAuteur);                                 
                    if(auteur== null){
                         auteur = new Auteur(nomAuteur);
                         this.lierAuteur(nomAuteur, auteur);
                     }                                 
                     auteurs.add(auteur);                                 
                    }

                }while(!(nomAuteur.equals("fin")));                                    

                Integer numPage;
                numPage = EntreesSorties.lireEntier("Entrez le numéro de la page où trouver l'article : ");

                HashSet<MotCle> motsCle = new HashSet<MotCle>();
                String motCle;                            
                do{                                    
                    motCle = EntreesSorties.lireChaine ("Entrez un mot clé ou tapez fin pour arrêter la saisie : ");                             
                    if (!(motCle.equals ("fin")))
                    {
                        MotCle motCle_o = this.unMotCle(motCle);

                        if(motCle_o== null)
                        {
                            motCle_o = new MotCle(motCle);
                            this.lierMotCle(motCle, motCle_o);
                        }                                 
                        motsCle.add(motCle_o);                                 
                    }                                 
                }while(!(motCle.equals("fin")));

                p.nouvelArticle(titre_o, dateEcriture, auteurs, numPage,  p, motsCle); 
            }
            else
            {
                System.out.println("ERREUR : la date d'écriture de l'article doit être postérieure à la date de parution du périodique"  + " (" + EntreesSorties.ecrireDate(p.getDateParution()) + ")");                            
            }                              
            nouvelArticle = EntreesSorties.lireChaine("Voulez-vous rentrer un autre article (oui/non)?");

            while (!nouvelArticle.equals("oui") && !nouvelArticle.equals("non")){
            nouvelArticle = EntreesSorties.lireChaine("Voulez-vous rentrer un autre article (oui/non)?");                        
            }  
         }                 
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : ces informations ne correspondent à aucune parution.");
        }	
    }

// -----------------------------------------------
        // Methodes de consultation
// -----------------------------------------------    
    
    public void consulterLecteur()
    {
        Integer numLecteur = EntreesSorties.lireEntier("Entrez le numéro du lecteur : ");

        Lecteur L = unLecteur(numLecteur);

        if (L!=null){
                L.afficherLecteur();
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : aucun lecteur n'est associé à ce numéro.");
        }
    }

    public void consulterOuvrage()
    {
        String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN : ");	

        Ouvrage O = unOuvrage(ISBN);

        if (O!=null){
                O.afficherOuvragesMotsCles();
                EntreesSorties.afficherMessage("");                        
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : aucun ouvrage n'est associé à ce numéro ISBN.");
        }
    }

    public void consulterExemplairesOuvrage()
    {
        String ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN : ");
        Ouvrage O = unOuvrage(ISBN);

        if (O!=null){
                O.afficherTitreNbEx();
                O.afficherExemplaires();
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : aucun ouvrage n'est associé à ce numéro ISBN.");
        }
    }
    
    public void consulterParution()
    {
        String ISSN = EntreesSorties.lireChaine("Entrez le numéro ISSN du périodique : ");
        Integer numParution = EntreesSorties.lireEntier("Entrez le numéro de la parution : ");
        String codeParution = ISSN + "_" + numParution;
        Parution p = uneParution(codeParution);		

        if (p!=null){
                p.afficherParution();
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : ces informations ne correspondent à aucune parution.");
        }
    }

    public void consulterArticlesParution()
    {
        String ISSN = EntreesSorties.lireChaine("Entrez le numéro ISSN du périodique : ");
        Integer numParution = EntreesSorties.lireEntier("Entrez le numéro de la parution : ");
        String codeParution = ISSN + "_" + numParution;
        Parution p = uneParution(codeParution);

        if (p!=null){
                p.afficherTitreNbArt();
                p.afficherArticles();
        }
        else {
                EntreesSorties.afficherMessage("ERREUR : ces informations ne correspondent à aucune parution.");
        }
    }    


// -----------------------------------------------
        // Methodes de listing
// -----------------------------------------------     
    
    public void listerLecteurs()
    {
        System.out.println("Liste des numéros de lecteurs enregistrés : ");
        for (int num : _dicoLecteur.keySet())
        {
            System.out.println(num);
        }
    }
                       
    public void listerOuvrages()
    {
        System.out.println("Liste des numéros ISBN des ouvrages enregistrés : ");
        for (String ISBN : _dicoOuvrage.keySet()){
            System.out.println(ISBN);
        }               
    }

    public void listerParutions(){
         System.out.println("Liste des codes des parutions enregistrées : ");
         for (String codeParution : _dicoParution.keySet()){
             System.out.println(codeParution);
         }               
     }    
        
    public void listerMotsCles()
    {
        System.out.println("Liste des mots-clés enregistrés : ");
        for (String motCle : _dicoMotsCle.keySet())
        {
            System.out.println(motCle);
        }
    }        

    public void listerAuteurs()
    {
        System.out.println("Liste des auteurs enregistrés : ");
        for (String nomAuteur : _dicoAuteurs.keySet())
        {
            System.out.println(nomAuteur);
        }
    }

    public void listerTitres()
    {
        System.out.println("Liste des titres enregistrés : ");                      

        for(Iterator<Titre> iter = this._dicoTitres.values().iterator(); iter.hasNext();)
        {
            Titre t = iter.next();
            t.afficherTitre();
//            t.afficherDocumentsTitre();
        }
    }       

// -----------------------------------------------
        // Methodes de gestion des emprunts
// -----------------------------------------------      
    

    public void emprunterExemplaire()
    {

        Integer numLect = EntreesSorties.lireEntier("Entrez le numéro du lecteur concerné : ");
        Lecteur l = unLecteur(numLect);      
        String nouvelEmprunt = "oui";
        GregorianCalendar dateEmprunt = new GregorianCalendar();
        GregorianCalendar dateReception = new GregorianCalendar();
        String ISBN = "";
        Integer numExemplaire = 0;

        if (l != null) 
        {
            if (l.etatNonSature())
            {
                while(nouvelEmprunt.equals("oui"))
                {
                    ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'exemplaire: ");                        

                    Ouvrage o = this.unOuvrage(ISBN);
                    if(o != null)                            
                    {
                        if(this.publicOk(o,l))                            
                        {
                            numExemplaire = EntreesSorties.lireEntier("Entrez le numéro de l'exemplaire: ");
                            Exemplaire ex = this.unExemplaire(ISBN, numExemplaire);
                            if(ex != null)
                            {
                               if (ex.etatNonEmprunte())
                                {
                                    if (ex.empruntable())
                                    {                                      
                                        dateEmprunt = EntreesSorties.lireDate("Entrez la date d'emprunt de l'exemplaire : ");                                        
                                        dateReception = ex.getDateReception();
                                        if(!dateEmprunt.before(dateReception))
                                        {
                                            Emprunt emprunt=new Emprunt(ex,l,dateEmprunt);                                        
                                            EntreesSorties.afficherMessage("L'exemplaire a été emprunté avec succès!");
                                        }
                                        else
                                        {
                                            EntreesSorties.afficherMessage("ERREUR : La date d'emprunt doit être après la date de réception de l'exemplaire " + "(" + EntreesSorties.ecrireDate(dateReception) + ")");
                                        }                                        
                                    }
                                    else
                                    {
                                        EntreesSorties.afficherMessage("ERREUR : cet exemplaire est seulement consultable sur place...");                                    
                                    }
                                }
                                else
                                {
                                    EntreesSorties.afficherMessage("ERREUR : l'exemplaire est deja emprunté...");                                    
                                }
                            }
                            else
                            {
                                EntreesSorties.afficherMessage("Ces données ne correspondent à aucun exemplaire...");                                
                            }
                        }    
                        else
                        {
                            EntreesSorties.afficherMessage("ERREUR : le public cible de l'ouvrage est incompatible avec l'âge du lecteur!");                                            
                        }
                    }
                    else
                    {
                        EntreesSorties.afficherMessage("ERREUR : cet ISBN ne correspond à aucun ouvrage...");                                                                
                    }                        
                    nouvelEmprunt = EntreesSorties.lireChaine("Voulez-vous enregistrer un nouvel emprunt (oui/non)?");

                    while (!nouvelEmprunt.equals("oui") && !nouvelEmprunt.equals("non"))
                    {
                            nouvelEmprunt = EntreesSorties.lireChaine("Voulez-vous enregistrer un nouvel emprunt (oui/non)?");                        
                    }  
                }        
            }
            else
            {
                EntreesSorties.afficherMessage("ERREUR : ce lecteur a déjà emprunté plus de 5 exemplaires...");                    
            }
        }
        else 
        {
            EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur n'existe pas...");
        }
    }
        
     
    public void rendreExemplaire()
    {
       String ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'ouvrage emprunté : ");                        
       Integer numExemplaire = EntreesSorties.lireEntier("Entrez le numéro de l'exemplaire emprunté : ");

       Emprunt emp = this.unEmprunt(ISBN, numExemplaire);

       if (emp != null)
       {
           emp.rendreEmprunt();
           EntreesSorties.afficherMessage("L'emprunt a bien été rendu.");                
       }
       else
       {
           EntreesSorties.afficherMessage("ERREUR : cette référence ne correspond pas à un emprunt...");
       }

    }


    public void consulterEmpruntsLecteur()
    {
        Integer numLecteur = EntreesSorties.lireEntier("Entrez le numéro du lecteur : ");
        Lecteur l = unLecteur(numLecteur);		
            if (l!=null)
            {
                    l.afficherNomPrenom();
                    EntreesSorties.afficherMessage("");                        
                    l.afficherEmprunts();
            }
            else 
            {
                    EntreesSorties.afficherMessage("ERREUR : aucun lecteur n'est associé à ce numéro.");
            }
    }
        
        
    public void relancerLecteur()
    {
        Integer nbRetard= 0;
        for(Iterator<Lecteur> iter = this.lesLecteurs(); iter.hasNext();)
        {
            for(Emprunt emprunt : iter.next().getEmprunts())
            {
                if(emprunt.retard())
                {
                    emprunt.afficherLecteurEmprunt();
                    emprunt.afficherEmprunt();
                    nbRetard = nbRetard + 1;
                }
            }

        }
        if(nbRetard == 0)
        {
            EntreesSorties.afficherMessage("Il n'y a aucun retard à la bibliothèque!");                
        }
    }
                        
// -----------------------------------------------
        // Methodes de recherche par mots clés
// -----------------------------------------------      
            
    public void rechercheAuteur()
    {
                                  
        Integer choixRech = EntreesSorties.lireEntier("Rechercher un ouvrage (taper 1) ou un article (taper 2) : ");                             
        while(choixRech != 1 && choixRech != 2)
        {
            choixRech = EntreesSorties.lireEntier("Rechercher un ouvrage (taper 1) ou un article (taper 2) : ");                             
        }
        
        String nomAuteur = EntreesSorties.lireChaine("Entrez le nom de l'auteur : ");
        Auteur auteur = this.unAuteur(nomAuteur);
        
        if(auteur!=null)
        {
            HashSet<Document> documents = auteur.getDocument();
            
            for(Document document : documents)
            {
                if(choixRech==1 && document instanceof Ouvrage)
                {
                    System.out.println("");
                    Ouvrage o = (Ouvrage)document;
                    o.afficherOuvrage();
                }
                else if(choixRech==2 && document instanceof Article)
                {
                    System.out.println("");
                    Article a = (Article)document;
                    a.afficherISSNTitreDateArticle();
                }
            }
        }
        else
        {
            System.out.println("Ce nom d'auteur n'est pas répertorié...");
        }        
    }

    public void rechercheMotCle()
    {
                                  
        Integer choixRech = EntreesSorties.lireEntier("Rechercher un ouvrage (taper 1) ou un article (taper 2) : ");                             
        while(choixRech != 1 && choixRech != 2)
        {
            choixRech = EntreesSorties.lireEntier("Rechercher un ouvrage (taper 1) ou un article (taper 2) : ");                             
        }
        
        String motCle = EntreesSorties.lireChaine("Entrez le mot-clé : ");
        MotCle motCle_o = this.unMotCle(motCle);
        
        if(motCle_o!=null)
        {
            HashSet<Document> documents = motCle_o.getDocument();
            
            for(Document document : documents)
            {
                if(choixRech==1 && document instanceof Ouvrage)
                {
                    System.out.println("");
                    Ouvrage o = (Ouvrage)document;
                    o.afficherOuvrage();
                }
                else if(choixRech==2 && document instanceof Article)
                {
                    System.out.println("");
                    Article a = (Article)document;
                    a.afficherISSNTitreDateArticle();
                }
            }
        }
        else
        {
            System.out.println("Ce mot-clé n'est pas répertorié...");
        }        
    }

    public void rechercheTitre()
    {
                                  
        Integer choixRech = EntreesSorties.lireEntier("Rechercher un ouvrage (taper 1) ou un article (taper 2) : ");                             
        while(choixRech != 1 && choixRech != 2)
        {
            choixRech = EntreesSorties.lireEntier("Rechercher un ouvrage (taper 1) ou un article (taper 2) : ");                             
        }
        
        String titre = EntreesSorties.lireChaine("Entrez le titre : ");
        Titre titre_o = this.unTitre(titre);
        
        if(titre_o!=null)
        {
            HashSet<Document> documents = titre_o.getDocument();
            
            for(Document document : documents)
            {
                if(choixRech==1 && document instanceof Ouvrage)
                {
                    System.out.println("");
                    Ouvrage o = (Ouvrage)document;
                    o.afficherOuvrage();
                }
                else if(choixRech==2 && document instanceof Article)
                {
                    System.out.println("");
                    Article a = (Article)document;
                    a.afficherISSNTitreDateArticle();
                }
            }
        }
        else
        {
            System.out.println("Ce titre n'est pas répertorié...");
        }        
    }                     
	
// --------------------------------------------------------------
        // Methodes permettant de récupérer un objet particulier
// --------------------------------------------------------------
	
    private Lecteur unLecteur(Integer numLecteur){
            return _dicoLecteur.get(numLecteur);
    }

    private Ouvrage unOuvrage(String ISBN){
        return _dicoOuvrage.get(ISBN);
    }


    private Parution uneParution(String codeParution){
        return _dicoParution.get(codeParution);
    }

    private Exemplaire unExemplaire(String ISBN, Integer numExemplaire){
        Ouvrage O;
        O = this.unOuvrage(ISBN);
        if (O!=null)
            return O.unExemplaire(numExemplaire);
        else
            return null;   
    }

    private Emprunt unEmprunt(String ISBN, Integer numExemplaire){
        Exemplaire e;
        e = this.unExemplaire(ISBN, numExemplaire);
        if (e!=null)
            return e.getEmprunt();
        else
            return null;
    }

    private Auteur unAuteur(String nomAuteur){
        return this._dicoAuteurs.get(nomAuteur);            
    }

    private MotCle unMotCle(String motCle){
        return this._dicoMotsCle.get(motCle);            
    }

    public Titre unTitre(String titre){
        return this._dicoTitres.get(titre);
    }

// -----------------------------------------------------------------
        // Methodes permettant de lier les objets à la bibliothèque
// -----------------------------------------------------------------
        
    private void lierLecteur(Integer numLecteur, Lecteur L){
            this._dicoLecteur.put(numLecteur, L);
    }

    private void lierOuvrage(String ISBN, Ouvrage O){
            this._dicoOuvrage.put(ISBN, O);
    }

    private void lierParution(String codeParution, Parution p){
            this._dicoParution.put(codeParution, p);
    }

    private void lierAuteur(String nomAuteur, Auteur auteur){
            this._dicoAuteurs.put(nomAuteur, auteur);
    }

    private void lierMotCle(String motCle, MotCle motCle_o){
            this._dicoMotsCle.put(motCle, motCle_o);
    }

    private void lierTitre(String titre, Titre titre_o){
            this._dicoTitres.put(titre, titre_o);
    }
                
// -----------------------------------------------------------------
        // Methodes fournissant les itérateurs
// -----------------------------------------------------------------   

        private Iterator<Lecteur> lesLecteurs()
        {
            return _dicoLecteur.values().iterator();
	}
        
        private Iterator<Parution> lesParutions()
        {
            return _dicoParution.values().iterator();
        }
        
// -----------------------------------------------------------------
        // Methodes permettant la gestion des numéros de lecteurs
// -----------------------------------------------------------------  

        
        private void nouveauNumLect(){
            Integer _NumTempLect;
            _NumTempLect = this.getDerNumLect();
            _NumTempLect = _NumTempLect + 1;
            this.setDerNumLect(_NumTempLect);
        }
        
// ----------------------------------------------------------------------------------
        // Methodes permettant de connaître la compatibilié avec le lecteur concerné
// ----------------------------------------------------------------------------------  
        
        private boolean publicOk(Ouvrage o, Lecteur l)
        {
            Integer ageLect;
            PublicCible publicOuvrage;
            ageLect = l.calculAge();
            publicOuvrage = o.getPublicCible();

            return ((ageLect>=16) || 
            (ageLect<16 && ageLect>=10 && (publicOuvrage.equals(PublicCible.ado) || publicOuvrage.equals(PublicCible.enfant))) ||
            (ageLect<10 && publicOuvrage.equals(PublicCible.enfant)));        
           
        }
        
}
