import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


// Classe de gestion de la Bibliotheque

public class Bibliotheque implements Serializable 
{
	
	private static final long serialVersionUID = 262L;

	// -----------------------------------------------
		//Attributs
	// -----------------------------------------------
	
		private HashMap<Integer, Lecteur> _dicoLecteur;
                private HashMap<String, Ouvrage> _dicoOuvrage;
                private Integer _derNumLect;
		
		/*
		 * Le dictionnaire de lecteur permet Ã  la bibliotheque de 
		 * garantir l'unicitÃ© de ces derniers, et facilitent les recherches et crÃ©ations.
		 */
	
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
	
		public Bibliotheque() {
			this.setLecteurs(new HashMap<Integer, Lecteur>());
                        this.setOuvrages(new HashMap<String, Ouvrage>());
                        this.setDerNumLect(0);
		}
	
// -----------------------------------------------
	// Public
// -----------------------------------------------	
		
		// -----------------------------------------------
			// Methodes
		// -----------------------------------------------
	
		/*
		 * La mÃ©thode nouveauLecteur permet de crÃ©er un lecteur en demandant la saisie de son numÃ©ro
		 * nom, prÃ©nom, date de naissance, adresse et numÃ©ro de tÃ©lÃ©phone.
		 * L'age doit Ãªtre compris entre 3 et 110 ans
		 * Le lecteur est identifiÃ© par son numÃ©ro, si celui ci existe dÃ©jÃ  dans le dictionnaire
		 * de bibliothÃ¨que, un message d'erreur est affichÃ©.
		 * Une fois le nouveau lecteur crÃ©Ã©, il est ajoutÃ© au dictionnaire de lecteur
		 * afin de garantir la cohÃ©rence des donnÃ©es.
		 */
	public void nouveauLecteur()
	{
                this.nouveauNumLect();
                Integer numLecteur = this.getDerNumLect();
//                Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero de lecteur :");
		
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
			EntreesSorties.afficherMessage("Le nouveau lecteur a Ã©tÃ© crÃ©Ã© avec succÃ©s!");
                        EntreesSorties.afficherMessage("Le numÃ©ro de ce lecteur est le " + numLecteur);
			lierLecteur(numLecteur, L);
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur existe dÃ©jÃ .");
		}
		
	}
	
        /*
        * La methode supprimerLecteur permet de supprimer un lecteur de la base de donnees de la bibliothÃ¨que
        */
        
        public void supprimerLecteur(){

                Integer numLecteur = EntreesSorties.lireEntier("Entrez le numÃ©ro du lecteur Ã  supprimer:");
		Lecteur l = unLecteur(numLecteur);
		
		if (l != null)
                {
                    if(l.etatSansEmprunt())
                     {                      
			this.delierLecteur(numLecteur);
                        System.out.println("Le lecteur a Ã©tÃ© supprimÃ© avec succÃ¨s !");
                     }
                    else
                    {
			EntreesSorties.afficherMessage("ERREUR : ce lecteur a des emprunts en cours et ne peut pas Ãªtre supprimÃ©...");                        
                    }
                }    
		else 
                {
			EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur n'existe pas.");
		}    
        }
                	
	/*
	 * La mÃ©thode consulterLecteur permet d'afficher l'ensemble des informations relatives Ã 
	 * un lecteur, par la saisie de son identifiant (numÃ©ro de lecteur).
	 * Si le numÃ©ro de lecteur n'est pas dans la base de donnÃ©es de bibliotheque un message d'erreur est
	 * renvoyÃ© a l'utilisateur.
	 */
	public void consulterLecteur()
	{
                Integer numLecteur = EntreesSorties.lireEntier("Entrez le numÃ©ro du lecteur : ");
		
		Lecteur L = unLecteur(numLecteur);
		
		if (L!=null){
			L.afficherLecteur();
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : aucun lecteur n'est associÃ© Ã  ce numÃ©ro.");
		}
	}
        
        /*
        * La methode listerLecteur permet d'afficher la liste des numÃ©ros de lecteurs enregistrÃ©s dans
        * la base de donnÃ©es de la bibliothÃ¨que.
        */
        public void listerLecteurs()
        {
            System.out.println("Liste des numÃ©ros de lecteurs enregistrÃ©s : ");
            for (int num : _dicoLecteur.keySet())
            {
                System.out.println(num);
            }
        }
               
        /*
        * La mÃ©thode nouvelOuvrage permet de crÃ©er un nouvel ouvrage en demandant la saisie de
        * son numero ISBN, du titre de l'ouvrage, du nom de l'auteur, du nom de l'Ã©diteur, du publicCible,
        * et de la date de parution.
        * L'ouvrage est identifiÃ© dans la bibliothÃ¨que par son numÃ©ro ISBN. Si celui-ci existe dÃ©jÃ  dans le
        * dictionnaire de la bibliothÃ¨que, un message d'erreur est affichÃ©.
        * Une fois le nouvel ouvrage crÃ©Ã©, il est insÃ©rÃ© dans le dictionnaire d'ouvrages
        * pour garantir la cohÃ©rence des donnÃ©es.
        */
	
	public void nouvelOuvrage()
	{
                boolean ok = false;
                String ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN de l'ouvrage : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O == null) 
		{
			String titreOuvrage = EntreesSorties.lireChaine("Entrez le titre de l'ouvrage : ");
                        HashSet<String> nomsAuteurs = new HashSet<String>();
                        String nomAuteur;
                        do{
                             nomAuteur = EntreesSorties.lireChaine ("Entrez un nom d'auteur ou tapez fin pour arrÃªter la saisie : ");
                             if (!(nomAuteur.equals ("fin")))
                             nomsAuteurs.add(nomAuteur);
                        }while(!(nomAuteur.equals("fin")));                                    
                        
                        String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'Ã©diteur : ");
                        do{
                            try{
                                    String sPublicCible = EntreesSorties.lireChaine("Public Cible (enfant/ado/adulte)? : ");                                    
                                    PublicCible publicCible = PublicCible.valueOf(sPublicCible);
                                    ok = true;
                                    GregorianCalendar dateParution;
                                    dateParution = EntreesSorties.lireDate("Entrez la date de parution : ");
                                    EntreesSorties.afficherMessage("Fin de saisie");
			
                                    O = new Ouvrage(ISBN, titreOuvrage, nomsAuteurs, nomEditeur, publicCible, dateParution);
                                    EntreesSorties.afficherMessage("Le nouvel ouvrage a Ã©tÃ© crÃ©Ã© avec succÃ©s!");                                    
                                }catch (Exception e){
                                ok = false;
                                System.out.println("ERREUR : le public cible saisi est invalide...");
                                }
                        }while(!ok);			
                        
                        lierOuvrage(ISBN, O);                   
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numÃ©ro ISBN existe dÃ©jÃ  pour un ouvrage.");
		}
		
	}

        /*
        * La mÃ©thode supprimerOuvrage permet de supprimer un ouvrage de la base de donnÃ©es de la bibliothÃ¨que
        */
        
        public void supprimerOuvrage(){

                String ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN de l'ouvrage Ã  supprimer : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O != null) 
		{   
                    if(O.etatSansExemplaire())
                    {                       
			this.delierOuvrage(ISBN);
                        System.out.println("L'ouvrage a Ã©tÃ© supprimÃ© avec succÃ¨s !");
                    }
                    else{
                        EntreesSorties.afficherMessage("ERREUR : pour supprimer l'ouvrage, supprimer au prÃ©alable ses exemplaires");
                    }
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numÃ©ro d'ouvrage n'existe pas.");
		}
            
        }
                        
	/*
	 * La mÃ©thode consulterOuvrage permet d'afficher l'ensemble des informations relatives Ã 
	 * un ouvrage, par la saisie de son ISBN.
	 * Si le numÃ©ro ISBN n'est pas dans la base de donnÃ©es de bibliothÃ¨que un message d'erreur est
	 * renvoyÃ© a l'utilisateur.
	 */        
	public void consulterOuvrage()
	{
                String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN : ");
		
		Ouvrage O = unOuvrage(ISBN);
		
		if (O!=null){
			O.afficherOuvrage();
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : aucun ouvrage n'est associÃ© Ã  ce numÃ©ro ISBN.");
		}
	}

        /*
        * La mÃ©thode listerOuvrages permet d'afficher la liste des numÃ©ros ISBN de tous les ouvrages
        * qui sont dans la base de donnÃ©es de la bibliothÃ¨que.
        */
        
        public void listerOuvrages(){
            System.out.println("Liste des numÃ©ros ISBN des ouvrages enregistrÃ©s : ");
            for (String ISBN : _dicoOuvrage.keySet()){
                System.out.println(ISBN);
            }               
        }
        
        /*
        * La mÃ©thode nouvelOuvrage permet de crÃ©er un nouvel ouvrage en demandant la saisie de
        * son numero ISBN, du titre de l'ouvrage, du nom de l'auteur, du nom de l'Ã©diteur, du publicCible,
        * et de la date de parution.
        * L'ouvrage est identifiÃ© dans la bibliothÃ¨que par son numÃ©ro ISBN. Si celui-ci existe dÃ©jÃ  dans le
        * dictionnaire de la bibliothÃ¨que, un message d'erreur est affichÃ©.
        * Une fois le nouvel ouvrage crÃ©Ã©, il est insÃ©rÃ© dans le dictionnaire d'ouvrages
        * pour garantir la cohÃ©rence des donnÃ©es.
        */
	
	public void nouvelExemplaire()
	{
		
                String ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN de l'exemplaire : ");
		Ouvrage o = unOuvrage(ISBN);
		String nouvelExemplaire = "oui";
                GregorianCalendar dateReception = null;
                
		if (o != null) 
		{
                 while(nouvelExemplaire.equals("oui")) {
                        dateReception = EntreesSorties.lireDate("Entrez la date de rÃ©ception de l'exemplaire : ");
                        if (!dateReception.before(o.getDateParution()))
                        {
                            String sDispo = EntreesSorties.lireChaine("DisponibilitÃ© de l'exemplaire (empruntable/consultable)?");                                                             
                            if((sDispo.equals("consultable")) || (sDispo.equals("empruntable")))
                            {
                                Disponibilite dispo = Disponibilite.valueOf(sDispo);                                    
                                o.nouvelExemplaire(dateReception, dispo);                            
                            }
                            else
                            {
                                System.out.println("ERREUR : la disponibilitÃ© saisie est invalide...");                                
                            }
                        }
                        else
                        {
                            System.out.println("ERREUR : la date de rÃ©ception de l'exemplaire doit Ãªtre postÃ©rieure Ã  la date de parution de l'ouvrage"  + " (" + EntreesSorties.ecrireDate(o.getDateParution()) + ")");                            
                        }                              
                    nouvelExemplaire = EntreesSorties.lireChaine("Voulez-vous rentrer un autre exemplaire (oui/non)?");
                    
                    while (!nouvelExemplaire.equals("oui") && !nouvelExemplaire.equals("non")){
                    nouvelExemplaire = EntreesSorties.lireChaine("Voulez-vous rentrer un autre exemplaire (oui/non)?");                        
                    }  
                 }                 
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numÃ©ro ISBN n'existe pas...");
		}
		
	}
  
        /* 
        * La mÃ©thode supprimerExemplaire permet de supprimer un exemplaire par son ISBN
        */
        
        public void supprimerExemplaire(){

                String ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN de l'exemplaire Ã  supprimer : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O != null) 
                    O.supprimerExemplaire();
		else 
                    EntreesSorties.afficherMessage("ERREUR : ce numero ISBN n'est pas rÃ©fÃ©rencÃ©...");
        }
                
	/*
	 * La mÃ©thode consulterExemplairesOuvrage permet d'afficher l'ensemble des informations relatives aux
	 * exemplaires d'un ouvrage, par la saisie de leur ISBN.
	 * Si le numÃ©ro ISBN n'est pas dans la base de donnÃ©es de bibliothÃ¨que un message d'erreur est
	 * renvoyÃ© a l'utilisateur.
	 */        

        public void consulterExemplairesOuvrage()
	{
                String ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O!=null){
			O.afficherTitreNbEx();
                        O.afficherExemplaires();
                }
		else {
			EntreesSorties.afficherMessage("ERREUR : aucun ouvrage n'est associÃ© Ã  ce numÃ©ro ISBN.");
		}
	}
        
        /*
        * La mÃ©thode emprunterExemplaire permet au lecteur saisi d'emprunter l'exemplaire sÃ©lectionnÃ©.
        */
	
	public void emprunterExemplaire()
	{
		
            Integer numLect = EntreesSorties.lireEntier("Entrez le numÃ©ro du lecteur concernÃ© : ");
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
                        ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN de l'exemplaire: ");                        
                    
                        Ouvrage o = this.unOuvrage(ISBN);
                        if(o != null)                            
                        {
                            if(this.publicOk(o,l))                            
                            {
                                numExemplaire = EntreesSorties.lireEntier("Entrez le numÃ©ro de l'exemplaire: ");
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
                                                EntreesSorties.afficherMessage("L'exemplaire a Ã©tÃ© empruntÃ© avec succÃ¨s!");
                                            }
                                            else
                                            {
                                                EntreesSorties.afficherMessage("ERREUR : La date d'emprunt doit Ãªtre aprÃ¨s la date de rÃ©ception de l'exemplaire " + "(" + EntreesSorties.ecrireDate(dateReception) + ")");
                                            }                                        
                                        }
                                        else
                                        {
                                            EntreesSorties.afficherMessage("ERREUR : cet exemplaire est seulement consultable sur place...");                                    
                                        }
                                    }
                                    else
                                    {
                                        EntreesSorties.afficherMessage("ERREUR : l'exemplaire est deja empruntÃ©...");                                    
                                    }
                                }
                                else
                                {
                                    EntreesSorties.afficherMessage("Ces donnÃ©es ne correspondent Ã  aucun exemplaire...");                                
                                }
                            }    
                            else
                            {
                                EntreesSorties.afficherMessage("ERREUR : le public cible de l'ouvrage est incompatible avec l'Ã¢ge du lecteur!");                                            
                            }
                        }
                        else
                        {
                            EntreesSorties.afficherMessage("ERREUR : cet ISBN ne correspond Ã  aucun ouvrage...");                                                                
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
                    EntreesSorties.afficherMessage("ERREUR : ce lecteur a dÃ©jÃ  empruntÃ© plus de 5 exemplaires...");                    
                }
            }
            else 
            {
                EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur n'existe pas...");
            }
        }
        
        /*
        * La mÃ©thode rendreExemplaire permet au lecteur saisi de rendre un exemplaire empruntÃ©
        */        
         public void rendreExemplaire(){
            String ISBN = EntreesSorties.lireChaine("Entrez le numÃ©ro ISBN de l'ouvrage empruntÃ© : ");                        
            Integer numExemplaire = EntreesSorties.lireEntier("Entrez le numÃ©ro de l'exemplaire empruntÃ© : ");

            Emprunt emp = this.unEmprunt(ISBN, numExemplaire);
            
            if (emp != null)
            {
                emp.rendreEmprunt();
                EntreesSorties.afficherMessage("L'emprunt a bien Ã©tÃ© rendu.");                
            }
            else
            {
                EntreesSorties.afficherMessage("ERREUR : cette rÃ©fÃ©rence ne correspond pas Ã  un emprunt...");
            }
            
         }


         /*
         * La mÃ©thode consulterEmpruntsLecteur permet d'afficher les emprunts d'un lecteur donnÃ©
         */
         
        public void consulterEmpruntsLecteur()
	{
            Integer numLecteur = EntreesSorties.lireEntier("Entrez le numÃ©ro du lecteur : ");
            Lecteur l = unLecteur(numLecteur);		
		if (l!=null)
                {
			l.afficherNomPrenom();
                        EntreesSorties.afficherMessage("");                        
                        l.afficherEmprunts();
		}
		else 
                {
			EntreesSorties.afficherMessage("ERREUR : aucun lecteur n'est associÃ© Ã  ce numÃ©ro.");
		}
	}
        
        /*
        * La mÃ©thode relancerlecteur permet d'afficher les informations concernant les emprunts
        * en retard au sein de la bibliothÃ¨que
        */
        
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
                EntreesSorties.afficherMessage("Il n'y a aucun retard Ã  la bibliothÃ¨que!");                
            }
        }
        
        
// -----------------------------------------------
	// Private
// -----------------------------------------------
	//-----------------------------------------------
                //Gettersparameter
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
        
        
        private void setDerNumLect(Integer derNumLect){
                this._derNumLect = derNumLect;
        }
	
	
	// -----------------------------------------------
		// Methodes
	// -----------------------------------------------
	
	/*
	 * La mÃ©thode unLecteur permet de rechercher dans la base de donnÃ©es de la bibliotheque un objet 
	 * lecteur identifiÃ© par son numÃ©ro, et de renvoyer l'objet (ou la donnÃ©e null s'il n'a pas Ã©tÃ© trouvÃ©).
	 */
	private Lecteur unLecteur(Integer numLecteur){
		return _dicoLecteur.get(numLecteur);
	}
	
        /*
        * La mÃ©thode unOuvrage permet de rechercher dans la base de donnÃ©es de la bibliothÃ¨que un objet
        * ouvrage identifiÃ© par son numÃ©ro ISBN, et de renvoyer l'objet (ou la valeur null s'il n'a pas Ã©tÃ© trouvÃ©).
        */
        private Ouvrage unOuvrage(String ISBN){
            return _dicoOuvrage.get(ISBN);
        }
        
        /*
        * La methode unExemplaire permet de recherche l'existence de l'exemplaire dont les attributs sont rentres
        * en parametres
        */
        
        private Exemplaire unExemplaire(String ISBN, Integer numExemplaire){
            Ouvrage O;
            O = this.unOuvrage(ISBN);
            if (O!=null)
                return O.unExemplaire(numExemplaire);
            else
                return null;   
        }
        
        /*
        * La mÃ©thode unEmprunt permet de rechercher l'existence d'un emprunt pour un lecteur donnÃ©
        */
        private Emprunt unEmprunt(String ISBN, Integer numExemplaire){
            Exemplaire e;
            e = this.unExemplaire(ISBN, numExemplaire);
            if (e!=null)
                return e.getEmprunt();
            else
                return null;
        }
        
        
	/*
	 * La mÃ©thode lierLecteur permet d'ajouter un lecteur Ã  la base de donnÃ©es de bibliothÃ¨que.
	*/
	private void lierLecteur(Integer numLecteur, Lecteur L){
		_dicoLecteur.put(numLecteur, L);
	}
        
        /*
        * La mÃ©thode delierLecteur permet de supprimer un lecteur de la base de donnÃ©es de la bibliothÃ¨que.
        */
        private void delierLecteur(Integer numLecteur){
                _dicoLecteur.remove(numLecteur);
        }
                
        /*
        * La mÃ©thode lierOuvrage permet d'ajouter un ouvrage Ã  la base de donnÃ©es de bibliothÃ¨que.
        */
	private void lierOuvrage(String ISBN, Ouvrage O){
                _dicoOuvrage.put(ISBN, O);
        }

        /*
        * La mÃ©thode delierOuvrage permet de supprimer un lecteur de la base de donnÃ©es de la bibliothÃ¨que.
        */
        
        private void delierOuvrage(String ISBN){
                _dicoOuvrage.remove(ISBN);
        }
        
      	/*
	 * La mÃ©thode lesLecteurs permet de crÃ©er un iterator sur les lecteurs, dans le but de les parcourir
	 * pour eventuellement les relancer.
	 */
	private Iterator<Lecteur> lesLecteurs()
        {
		return _dicoLecteur.values().iterator();
	}
        
        /*
        * La mÃ©thode nouveauNumLect permet d'avoir d'incrÃ©menter le numÃ©ro de lecteur et d'obtenir le numÃ©ro suivant
        * non utilisÃ©.
        */
        private void nouveauNumLect(){
            Integer _NumTempLect;
            _NumTempLect = this.getDerNumLect();
            _NumTempLect = _NumTempLect + 1;
            this.setDerNumLect(_NumTempLect);
        }
        
        /*
        * La mÃ©thode publicOk permet de connaÃ®tre la compatibilitÃ© d'un lecteur avec un ouvrage donnÃ©
        * Renvoie vrai pour le lecteur adulte quelque soit le pubilc cible de l'ouvrage
        * Renvoie vrai pour le lecteur ado et pour un public cible de l'ouvrage enfant ou ado
        * Renvoie vrai pour le lecteur enfant lorsque le public cible de l'ouvrage est enfant
        */
        
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
