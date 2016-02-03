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
		 * Le dictionnaire de lecteur permet à la bibliotheque de 
		 * garantir l'unicité de ces derniers, et facilitent les recherches et créations.
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
		 * La méthode nouveauLecteur permet de créer un lecteur en demandant la saisie de son numéro
		 * nom, prénom, date de naissance, adresse et numéro de téléphone.
		 * L'age doit être compris entre 3 et 110 ans
		 * Le lecteur est identifié par son numéro, si celui ci existe déjà dans le dictionnaire
		 * de bibliothèque, un message d'erreur est affiché.
		 * Une fois le nouveau lecteur créé, il est ajouté au dictionnaire de lecteur
		 * afin de garantir la cohérence des données.
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
			EntreesSorties.afficherMessage("Le nouveau lecteur a été créé avec succés!");
                        EntreesSorties.afficherMessage("Son nom de lecteur est le numéro " + numLecteur);
			lierLecteur(numLecteur, L);
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur existe déjà.");
		}
		
	}
	
        /*
        * La methode supprimerLecteur permet de supprimer un lecteur de la base de donnees de la bibliothèque
        */
        
        public void supprimerLecteur(){

                Integer numLecteur = EntreesSorties.lireEntier("Entrez le numéro du lecteur à supprimer:");
		Lecteur l = unLecteur(numLecteur);
		
		if (l != null)
                {
                    if(l.etatSansEmprunt())
                     {                      
			this.delierLecteur(numLecteur);
                        System.out.println("Le lecteur a été supprimé avec succès !");
                     }
                    else
                    {
			EntreesSorties.afficherMessage("ERREUR : ce lecteur a des emprunts en cours et ne peut pas être supprimé...");                        
                    }
                }    
		else 
                {
			EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur n'existe pas.");
		}    
        }
                	
	/*
	 * La méthode consulterLecteur permet d'afficher l'ensemble des informations relatives à
	 * un lecteur, par la saisie de son identifiant (numéro de lecteur).
	 * Si le numéro de lecteur n'est pas dans la base de données de bibliotheque un message d'erreur est
	 * renvoyé a l'utilisateur.
	 */
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
        
        /*
        * La methode listerLecteur permet d'afficher la liste des numéros de lecteurs enregistrés dans
        * la base de données de la bibliothèque.
        */
        public void listerLecteurs()
        {
            System.out.println("Liste des numéros de lecteurs enregistrés : ");
            for (int num : _dicoLecteur.keySet())
            {
                System.out.println(num);
            }
        }
               
        /*
        * La méthode nouvelOuvrage permet de créer un nouvel ouvrage en demandant la saisie de
        * son numero ISBN, du titre de l'ouvrage, du nom de l'auteur, du nom de l'éditeur, du publicCible,
        * et de la date de parution.
        * L'ouvrage est identifié dans la bibliothèque par son numéro ISBN. Si celui-ci existe déjà dans le
        * dictionnaire de la bibliothèque, un message d'erreur est affiché.
        * Une fois le nouvel ouvrage créé, il est inséré dans le dictionnaire d'ouvrages
        * pour garantir la cohérence des données.
        */
	
	public void nouvelOuvrage()
	{
                boolean ok = false;
                String ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'ouvrage : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O == null) 
		{
			String titreOuvrage = EntreesSorties.lireChaine("Entrez le titre de l'ouvrage : ");
                        HashSet<String> nomsAuteurs = new HashSet<String>();
                        String nomAuteur;
                        do{
                             nomAuteur = EntreesSorties.lireChaine ("Entrez un nom d'auteur ou tapez fin pour arrêter la saisie : ");
                             if (!(nomAuteur.equals ("fin")))
                             nomsAuteurs.add(nomAuteur);
                        }while(!(nomAuteur.equals("fin")));                                    
                        
                        String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur : ");
                        do{
                            try{
                                    String sPublicCible = EntreesSorties.lireChaine("Public Cible (enfant/ado/adulte)? : ");                                    
                                    PublicCible publicCible = PublicCible.valueOf(sPublicCible);
                                    ok = true;
                                    GregorianCalendar dateParution;
                                    dateParution = EntreesSorties.lireDate("Entrez la date de parution : ");
                                    EntreesSorties.afficherMessage("Fin de saisie");
			
                                    O = new Ouvrage(ISBN, titreOuvrage, nomsAuteurs, nomEditeur, publicCible, dateParution);
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

        /*
        * La méthode supprimerOuvrage permet de supprimer un ouvrage de la base de données de la bibliothèque
        */
        
        public void supprimerOuvrage(){

                String ISBN = EntreesSorties.lireChaine("Entrez le numéro ISBN de l'ouvrage à supprimer : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O != null) 
		{   
                    if(O.etatSansExemplaire())
                    {                       
			this.delierOuvrage(ISBN);
                        System.out.println("L'ouvrage a été supprimé avec succès !");
                    }
                    else{
                        EntreesSorties.afficherMessage("ERREUR : pour supprimer l'ouvrage, supprimer au préalable ses exemplaires");
                    }
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numéro d'ouvrage n'existe pas.");
		}
            
        }
                        
	/*
	 * La méthode consulterOuvrage permet d'afficher l'ensemble des informations relatives à
	 * un ouvrage, par la saisie de son ISBN.
	 * Si le numéro ISBN n'est pas dans la base de données de bibliothèque un message d'erreur est
	 * renvoyé a l'utilisateur.
	 */        
	public void consulterOuvrage()
	{
                String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN : ");
		
		Ouvrage O = unOuvrage(ISBN);
		
		if (O!=null){
			O.afficherOuvrage();
		}
		else {
			EntreesSorties.afficherMessage("ERREUR : aucun ouvrage n'est associe à ce numéro ISBN.");
		}
	}

        /*
        * La méthode listerOuvrages permet d'afficher la liste des numéros ISBN de tous les ouvrages
        * qui sont dans la base de données de la bibliothèque.
        */
        
        public void listerOuvrages(){
            System.out.println("Liste des numéros ISBN des ouvrages enregistrés : ");
            for (String ISBN : _dicoOuvrage.keySet()){
                System.out.println(ISBN);
            }               
        }
        
        /*
        * La méthode nouvelOuvrage permet de créer un nouvel ouvrage en demandant la saisie de
        * son numero ISBN, du titre de l'ouvrage, du nom de l'auteur, du nom de l'éditeur, du publicCible,
        * et de la date de parution.
        * L'ouvrage est identifié dans la bibliothèque par son numéro ISBN. Si celui-ci existe déjà dans le
        * dictionnaire de la bibliothèque, un message d'erreur est affiché.
        * Une fois le nouvel ouvrage créé, il est inséré dans le dictionnaire d'ouvrages
        * pour garantir la cohérence des données.
        */
	
	public void nouvelExemplaire()
	{
		
                String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN de l'exemplaire : ");
		Ouvrage o = unOuvrage(ISBN);
		String nouvelExemplaire = "oui";
                GregorianCalendar dateReception = null;
                
		if (o != null) 
		{
                 while(nouvelExemplaire.equals("oui")) {
                        dateReception = EntreesSorties.lireDate("Entrez la date de reception de l'exemplaire : ");
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
                                System.out.println("ERREUR : la disponibilite saisie est invalide...");                                
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
		else {
			EntreesSorties.afficherMessage("ERREUR : ce numéro ISBN n'existe pas...");
		}
		
	}
  
        /* 
        * La méthode supprimerExemplaire permet de supprimer un exemplaire par son ISBN
        */
        
        public void supprimerExemplaire(){

                String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN de l'exemplaire à supprimer : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O != null) 
                    O.supprimerExemplaire();
		else 
                    EntreesSorties.afficherMessage("ERREUR : ce numero ISBN n'est pas référencé...");
        }
                
	/*
	 * La méthode consulterExemplairesOuvrage permet d'afficher l'ensemble des informations relatives aux
	 * exemplaires d'un ouvrage, par la saisie de leur ISBN.
	 * Si le numéro ISBN n'est pas dans la base de données de bibliotheque un message d'erreur est
	 * renvoyé a l'utilisateur.
	 */        

        public void consulterExemplairesOuvrage()
	{
                String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O!=null){
			O.afficherTitreNbEx();
                        O.afficherExemplaires();
                }
		else {
			EntreesSorties.afficherMessage("ERREUR : aucun ouvrage n'est associe a ce numero ISBN.");
		}
	}
        
        /*
        * La méthode emprunterExemplaire permet au lecteur saisi d'emprunter l'exemplaire selectionne
        */
	
	public void emprunterExemplaire()
	{
		
            Integer numLect = EntreesSorties.lireEntier("Entrez le numero du lecteur concerné: ");
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
                        ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN de l'exemplaire: ");                        
                    
                        Ouvrage o = this.unOuvrage(ISBN);
                        if(o != null)
                        {
                            numExemplaire = EntreesSorties.lireEntier("Entrez le numero de l'exemplaire: ");
                            Exemplaire ex = this.unExemplaire(ISBN, numExemplaire);
                            
                            if(ex != null)
                            {
                                if (ex.etatNonEmprunte())
                                {
                                    if (ex.empruntable())
                                    {
                                        if(this.publicOk(o,l))
                                        {    
                                            dateEmprunt = EntreesSorties.lireDate("Entrez la date d'emprunt de l'exemplaire: ");                                        
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
                                                EntreesSorties.afficherMessage("ERREUR : le public cible de l'ouvrage est incompatible avec l'âge du lecteur!");                                            
                                        }
                                    }
                                    else
                                    {
                                        EntreesSorties.afficherMessage("ERREUR : cet exemplaire est seulement consultable sur place...");                                    
                                    }
                                }
                                else
                                {
                                    EntreesSorties.afficherMessage("ERREUR : l'exemplaire est deja emprunte...");                                    
                                }
                            }
                            else
                            {
                                EntreesSorties.afficherMessage("Ces donnees ne correspondent a aucun exemplaire...");                                
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
                    EntreesSorties.afficherMessage("ERREUR : ce lecteur a deja emprunte plus de 5 exemplaires...");                    
                }
            }
            else 
            {
                EntreesSorties.afficherMessage("ERREUR : ce numero de lecteur n'existe pas...");
            }
        }
        
        /*
        * La méthode rendreExemplaire permet au lecteur saisi de rendre un exemplaire emprunté
        */        
         public void rendreExemplaire(){
            String ISBN = EntreesSorties.lireChaine("Entrez le numero ISBN de l'ouvrage emprunté: ");                        
            Integer numExemplaire = EntreesSorties.lireEntier("Entrez le numero de l'exemplaire emprunté: ");

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


         /*
         * La methode consulterEmpruntsLecteur permet d'afficher les emprunt d'un lecteur donné
         */
         
        public void consulterEmpruntsLecteur()
	{
            Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");
            Lecteur l = unLecteur(numLecteur);		
		if (l!=null)
                {
			l.afficherNomPrenom();
                        EntreesSorties.afficherMessage("");                        
                        l.afficherEmprunts();
		}
		else 
                {
			EntreesSorties.afficherMessage("ERREUR : aucun lecteur n'est associe a ce numero.");
		}
	}
        
        /*
        * La methode relancer lecteur permet d'afficher les informations concernant les emprunts
        * en retard au sein de la bibliothèque
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
                EntreesSorties.afficherMessage("Il n'y a aucun retard à la bibliothèque!");                
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
	 * La méthode unLecteur permet de rechercher dans la base de données de la bibliotheque un objet 
	 * lecteur identifié par son numéro, et de renvoyer l'objet (ou la donnée null s'il n'a pas été trouvé).
	 */
	private Lecteur unLecteur(Integer numLecteur)
	{
		return _dicoLecteur.get(numLecteur);
	}
	
        /*
        * La méthode unOuvrage permet de rechercher dans la base de données de la bibliothèque un objet
        * ouvrage identifié par son numéro ISBN, et de renvoyer l'objet (ou la valeur null s'il n'a pas été trouvé).
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
            return O.unExemplaire(numExemplaire);
        }
        
        /*
        * La methode unEmprunt permet de rechercher l'existence d'un emprunt pour un lecteur donne
        */
        
        private Emprunt unEmprunt(String ISBN, Integer numExemplaire){
            Exemplaire e;
            e=this.unExemplaire(ISBN, numExemplaire);
            return e.getEmprunt();
        }
        
        
	/*
	 * La méthode lierLecteur permet d'ajouter un lecteur a la base de données de bibliotheque.
	 */
	private void lierLecteur(Integer numLecteur, Lecteur L)
	{
		_dicoLecteur.put(numLecteur, L);
	}
        
        /*
        * La méthode delierLecteur permet de supprimer un lecteur de la base de données de la bibliothèque.
        */
        
        private void delierLecteur(Integer numLecteur){
                _dicoLecteur.remove(numLecteur);
        }
                
        /*
        * La méthode lierOuvrage permet d'ajouter un ouvrage à la base de données de bibliothèque.
        */
	private void lierOuvrage(String ISBN, Ouvrage O){
                _dicoOuvrage.put(ISBN, O);
        }

        /*
        * La méthode delierOuvrage permet de supprimer un lecteur de la base de données de la bibliothèque.
        */
        
        private void delierOuvrage(String ISBN){
                _dicoOuvrage.remove(ISBN);
        }
        
      	/*
	 * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans le but de les parcourir
	 * pour eventuellement les relancer.
	 */
	private Iterator<Lecteur> lesLecteurs() {
		return _dicoLecteur.values().iterator();
	}
        
        /*
        * La méthode nouveauNumLect permet d'avoir d'incrémenter le numéro de lecteur et d'obtenir le numéro suivant
        * non utilisé.
        */
        private void nouveauNumLect(){
            Integer _NumTempLect;
            _NumTempLect = this.getDerNumLect();
            _NumTempLect = _NumTempLect + 1;
            this.setDerNumLect(_NumTempLect);
        }
        
        /*
        * La méthode publicOk permet de connaître la compatibilité d'un lecteur avec un ouvrage donné
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
