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
                private HashMap<Integer, Ouvrage> _dicoOuvrage;
                private Integer _derNumLect;
		
		/*
		 * Le dictionnaire de lecteur permet à bibliotheque de 
		 * garantir l'unicité de ces derniers, et facilitent les recherches et créations.
		 */
	
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
	

		public Bibliotheque() {
			this.setLecteurs(new HashMap<Integer, Lecteur>());
                        this.setOuvrages(new HashMap<Integer, Ouvrage>());
                        this.setDerNumLect(0);
		
		}
	
// -----------------------------------------------
	// Public
// -----------------------------------------------	
		
		// -----------------------------------------------
			// Methodes
		// -----------------------------------------------
	
		/*
		 * La méthode nouveauLecteur permet de créé un lecteur en demandant la saisie de son numéro
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
			String nom = EntreesSorties.lireChaine("Entrez le nom :");
			String prenom = EntreesSorties.lireChaine("Entrez le prenom :");
			Integer age;
			GregorianCalendar dateNaiss, dateNaissComp;
			GregorianCalendar dateActuelle = new GregorianCalendar();
			do {
				dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur :");
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
			String adresse = EntreesSorties.lireChaine("Entrez l'adresse :");
			String tel = EntreesSorties.lireChaine("Entrez le numero de telephone :");
			EntreesSorties.afficherMessage("Fin de saisie");
			
			L = new Lecteur(nom, prenom, numLecteur, dateNaiss, adresse, tel);
			EntreesSorties.afficherMessage("Le nouveau lecteur a été créé avec succés!");                        
                        
			lierLecteur(numLecteur, L);
		}
		else {
			EntreesSorties.afficherMessage("Ce numero de lecteur existe deja.");
		}
		
	}
	
        /*
        * La methode supprimer lecteur permet de supprimer un lecteur de la base de donnees de la bibliothèque
        */
        
        public void supprimerLecteur(){

                Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur à supprimer:");
		Lecteur L = unLecteur(numLecteur);
		
		if (L != null) 
		{                      
			this.delierLecteur(numLecteur);
                        System.out.println("Le lecteur a été supprimé avec succès!");
		}
		else {
			EntreesSorties.afficherMessage("Ce numero de lecteur n'existe pas.");
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
                Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");
		
		Lecteur L = unLecteur(numLecteur);
		
		if (L!=null){
			L.afficherLecteur();
		}
		else {
			EntreesSorties.afficherMessage("Aucun lecteur n'est associe a ce numero.");
		}
	}
        
        /*
        * La methode listerLecteur permet d'afficher la liste des numeros de lecteurs enregistres dans
        * la base de donnees de la bibliotheque.
        */
        
        public void listerLecteurs(){
            System.out.println("Liste des numéros de lecteurs enregistrés: ");
            for (int num : _dicoLecteur.keySet()){
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
                Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN de l'ouvrage: ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O == null) 
		{
			String titreOuvrage = EntreesSorties.lireChaine("Entrez le titre de l'ouvrage: ");
			
                        
                        HashSet<String> nomsAuteurs = new HashSet<String>();
                        String nomAuteur;
                        do{
                             nomAuteur = EntreesSorties.lireChaine ("entrer un nom d'auteur ou taper fin pour arrêter la saisie : ");
                             if (!(nomAuteur.equals ("fin")))
                             nomsAuteurs.add(nomAuteur);
                        }while(!(nomAuteur.equals("fin")));                                    
                        
                        String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur: ");
                        do{
                            try{
                                    String sPublicCible = EntreesSorties.lireChaine("Public Cible (enfant/ado/adulte)?: ");                                    
                                    PublicCible publicCible = PublicCible.valueOf(sPublicCible);
                                    ok = true;
                                    GregorianCalendar dateParution;
                                    dateParution = EntreesSorties.lireDate("Entrez la date de parution: ");
                                    EntreesSorties.afficherMessage("Fin de saisie");
			
                                    O = new Ouvrage(ISBN, titreOuvrage, nomsAuteurs, nomEditeur, publicCible, dateParution);
                                    EntreesSorties.afficherMessage("Le nouvel ouvrage a été créé avec succés!");                                    
                                }catch (Exception e){
                                ok = false;
                                System.out.println("La public cible saisi est invalide...");
                            }
                        }while(!ok);			
                        
                        lierOuvrage(ISBN, O);                      
		}
		else {
			EntreesSorties.afficherMessage("Ce numéro ISBN existe deja pour un ouvrage.");
		}
		
	}

        /*
        * La méthode supprimerOuvrage permet de supprimer un ouvrage de la base de données de la bibliothèque
        */
        
        public void supprimerOuvrage(){

                Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN de l'ouvrage à supprimer:");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O != null) 
		{                      
			this.delierOuvrage(ISBN);
                        System.out.println("L'ouvrage a été supprimé avec succès!");
		}
		else {
			EntreesSorties.afficherMessage("Ce numero d'ouvrage n'existe pas.");
		}
            
        }
                        
	/*
	 * La méthode consulterOuvrage permet d'afficher l'ensemble des informations relatives à
	 * un ouvrage, par la saisie de son ISBN.
	 * Si le numéro ISBN n'est pas dans la base de données de bibliotheque un message d'erreur est
	 * renvoyé a l'utilisateur.
	 */        
	public void consulterOuvrage()
	{
                Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN : ");
		
		Ouvrage O = unOuvrage(ISBN);
		
		if (O!=null){
			O.afficherOuvrage();
		}
		else {
			EntreesSorties.afficherMessage("Aucun ouvrage n'est associe a ce numero ISBN.");
		}
	}

        /*
        * La methode listerOuvrages permet d'afficher la liste des numéros ISBN de tous les ouvrages
        * qui sont dans la base de données de la bibliothèque.
        */
        
        public void listerOuvrages(){
            System.out.println("Liste des numéros ISBN des ouvrages enregistrés: ");
            for (int ISBN : _dicoOuvrage.keySet()){
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
		
                Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN de l'exemplaire: ");
		Ouvrage O = unOuvrage(ISBN);
		String nouvelExemplaire = "oui";
                GregorianCalendar dateReception = null;
                Boolean ok = false;
                
		if (O != null) 
		{
                 while(nouvelExemplaire.equals("oui")) {
                    
                    do{
                        dateReception = EntreesSorties.lireDate("Entrez la date de Reception de l'exemplaire: ");
                        if (dateReception.before(O.getDateParution()))
                            System.out.println("La date de réception de l'exemplaire ne peut pas être antérieure à la date de parution de l'ouvrage...");
                    }while(dateReception.before(O.getDateParution()));
                        
                    do{
                            try{
                                    String sDispo = EntreesSorties.lireChaine("Disponibilité de l'exemplaire (empruntable/consultable)?");                                    
                                    Disponibilite dispo = Disponibilite.valueOf(sDispo);
                                    ok = true;
                                    
                                    O.nouvelExemplaire(dateReception, dispo);
		
                                    EntreesSorties.afficherMessage("Le nouvel exemplaire a été créé avec succés!");                                    
                                }catch (Exception e){
                                ok = false;
                                System.out.println("La disponibilite saisie est invalide...");
                            }
                        }while(!ok);			
                   
                    nouvelExemplaire = EntreesSorties.lireChaine("Voulez-vous rentrer un autre exemplaire (oui/non)?");
                    
                    while (!nouvelExemplaire.equals("oui") && !nouvelExemplaire.equals("non")){
                    nouvelExemplaire = EntreesSorties.lireChaine("Voulez-vous rentrer un autre exemplaire (oui/non)?");                        
                    }  
                 }                 
		}
		else {
			EntreesSorties.afficherMessage("Ce numéro ISBN n'existe pas...");
		}
		
	}
  
        public void supprimerExemplaire(){

                Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN de l'exemplaire à supprimer :");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O != null) 
                    O.supprimerExemplaire();
		else 
                    EntreesSorties.afficherMessage("Ce numero ISBN n'est pas référencé...");
        }
                
	/*
	 * La méthode consulterExemplairesOuvrage permet d'afficher l'ensemble des informations relatives aux
	 * exemplaires d'un ouvrage, par la saisie de leur ISBN.
	 * Si le numéro ISBN n'est pas dans la base de données de bibliotheque un message d'erreur est
	 * renvoyé a l'utilisateur.
	 */        

        public void consulterExemplairesOuvrage()
	{
                Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN : ");
		Ouvrage O = unOuvrage(ISBN);
		
		if (O!=null){
			O.afficherTitreISBN();
                        O.afficherExemplaires();
                }
		else {
			EntreesSorties.afficherMessage("Aucun ouvrage n'est associe a ce numero ISBN.");
		}
	}
        
        
        
// -----------------------------------------------
	// Private
// -----------------------------------------------
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
        
        private void setOuvrages(HashMap<Integer, Ouvrage> dicoOuvrage){
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
        private Ouvrage unOuvrage(Integer ISBN){
            return _dicoOuvrage.get(ISBN);
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
	private void lierOuvrage(Integer ISBN, Ouvrage O){
                _dicoOuvrage.put(ISBN, O);
        }

        /*
        * La méthode delierOuvrage permet de supprimer un lecteur de la base de données de la bibliothèque.
        */
        
        private void delierOuvrage(Integer ISBN){
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
}