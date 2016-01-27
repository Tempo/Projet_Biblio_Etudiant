import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

// Classe de gestion de Lecteur

public class Ouvrage implements Serializable 
{
	
	private static final long serialVersionUID = 422L;
	
	// -----------------------------------------------
		//Attributs
	// -----------------------------------------------
	
                private Integer _ISBN;
                private String _titreOuvrage;
                private HashSet<String> _nomsAuteurs;
                private String _nomEditeur;
                private PublicCible _publicCible;
                private GregorianCalendar _dateParution;
                private Integer _nbExemplaires;
                private HashMap<Integer, Exemplaire> _dicoExemplaire;
                private Integer _derNumEx;

        
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
		
		public Ouvrage(Integer ISBN, String titreOuvrage, HashSet<String> nomsAuteurs, String nomEditeur, PublicCible publicCible, GregorianCalendar dateParution)
		{
			this.setISBN(ISBN);
			this.setTitreOuvrage(titreOuvrage);
                        this.setNomAuteur(nomsAuteurs);
                        this.setNomEditeur(nomEditeur);
                        this.setPublicCible(publicCible);
                        this.setDateParution(dateParution);
                        this.setNbExemplaires(0);
			this.setExemplaires(new HashMap<Integer, Exemplaire>());                        
                        this.setDerNumEx(0);
		}
		
// -----------------------------------------------
	// Public
// -----------------------------------------------
                
                //------------------------------------------------
                        //Getters
                //------------------------------------------------
                
		public GregorianCalendar getDateParution() {
			return _dateParution;
		}

                public PublicCible getPublicCible() {
			return _publicCible;
		}
                
                // -----------------------------------------------
			// Methodes
		// -----------------------------------------------

                /*
                * La méthode unExemplaire permet de rechercher dans la base de données de l'ouvrage un objet
                * exemplaire identifié par son numExemplaire, et de renvoyer l'objet (ou la valeur null s'il n'a pas été trouvé).
                */
                public Exemplaire unExemplaire(Integer numExemplaire){
                    return _dicoExemplaire.get(numExemplaire);
                }
                                
		/*
		 * La methode afficherOuvrage affiche l'ensemble des informations relatives a un lecteur.
		 */
		public void afficherOuvrage()
		{
			System.out.println("ISBN : " + this.getISBN());
			System.out.println("Titre de l'ouvrage: " + this.getTitreOuvrage());
                        
                        System.out.print("Nom de(s) auteur(s): ");
                        String chaineAuteurs = "";
                        for (String auteur : this.getNomsAuteurs()){
                              chaineAuteurs = chaineAuteurs + auteur + ", ";        
                        }
                        System.out.println(chaineAuteurs.substring(0, chaineAuteurs.length()-2));
                        
                        System.out.println("Nom de l'éditeur: " + this.getNomEditeur());
                        System.out.println("Public Cible: " + this.getPublicCible());
                        System.out.println("Date de parution: " + EntreesSorties.ecrireDate(this.getDateParution()));
                        System.out.println("Nombre d'exemplaires: " + this.getNbExemplaires());
			EntreesSorties.afficherMessage("");
		}

		/*
		 * La methode afficherISBNTitreNbEx affiche l'ISBN le titre et le nombre d'exemplaires
                 * de l'ouvrage considéré.
		 */
		public void afficherTitreNbEx()
		{
			System.out.println("Titre de l'ouvrage: " + this.getTitreOuvrage());
                        System.out.println("Nombre d'exemplaires: " + this.getNbExemplaires());
			EntreesSorties.afficherMessage("");
		}

		/*
		 * La methode afficherTitreISBN affiche l'ISBN le titre et le nombre d'exemplaires
                 * de l'ouvrage considéré.
		 */
		public void afficherISBNTitre()
		{
			System.out.println("ISBN : " + this.getISBN());
			System.out.println("Titre de l'ouvrage: " + this.getTitreOuvrage());
		}
                
                /*
                * La methode afficheExemplaires affiche les informations relatives à tous les exemplaires de l'ouvrage
                */
                public void afficherExemplaires(){                    
                    for (Iterator<Exemplaire> iter = this.lesExemplaires(); iter.hasNext();){
                        iter.next().afficherExemplaire();
                    }                    
                }                    
                                                
                /*
                * La méthode nouvelExemplaire permet la création d'un nouvel exemplaire rattaché à un ouvrage donné
                * Les exemplaires seront rajoutés au _dicoExemplaire de l'ouvrage en question
                */
                
                public void nouvelExemplaire(GregorianCalendar dateReception, Disponibilite dispo){
                    
                    this.nouveauNumEx();
                    Integer numExemplaire = this.getDerNumEx();
                    
                    Exemplaire E = unExemplaire(numExemplaire);
		
                    if (E == null) 
                    {
			E = new Exemplaire(numExemplaire, dateReception, dispo, this);
                        
                        this.ajouterUnNbExemplaire();
			this.lierExemplaire(numExemplaire, E);
		}
		else {
			EntreesSorties.afficherMessage("Ce numero d'exemplaire existe deja.");
		}
                }
                
                /*
                * La méthode supprimerExemplaire permet la suppression d'un exemplaire de la base de donnée
                */
                
        public void supprimerExemplaire(){

                Integer numExemplaire = EntreesSorties.lireEntier("Entrez le numero de l'exemplaire à supprimer :");
		Exemplaire E = unExemplaire(numExemplaire);
		
		if (E != null)
                {
                    if(E.etatNonEmprunte())                    
                    {                      
			this.delierExemplaire(numExemplaire);
                        this.supprimerUnNbExemplaire();
                        System.out.println("L'exemplaire a été supprimé avec succès!");
                    }
                    else
                    {
			EntreesSorties.afficherMessage("ERREUR: cet exemplaire est actuellement emprunte...");                        
                    }
                }
		else
                {
			EntreesSorties.afficherMessage("ERREUR: Ce numero d'exemplaire n'est pas référencé...");
		}
            
        }
        
        
        
                public boolean etatSansExemplaire(){
                    return this.getNbExemplaires()==0;
                }
                
	
// -----------------------------------------------
	// Private
// -----------------------------------------------
                
                //------------------------------------------------
                        //Getters
                //------------------------------------------------
                private Integer getDerNumEx(){
                    return _derNumEx;
                }
	
		private Integer getISBN() {
			return _ISBN;
		}

		private String getTitreOuvrage() {
			return _titreOuvrage;
		}

		private String getNomEditeur() {
			return _nomEditeur;
		}

		private HashSet<String> getNomsAuteurs() {
			return _nomsAuteurs;
		}
                
                private Integer getNbExemplaires(){
                    return _nbExemplaires;
                }
                               
		// -----------------------------------------------
			//Setters
		// -----------------------------------------------

		private void setISBN(Integer ISBN){
                    this._ISBN = ISBN;
                }
                
                private void setTitreOuvrage(String titreOuvrage){
                    this._titreOuvrage = titreOuvrage;
                }
                
                private void setNomEditeur(String nomEditeur){
                    this._nomEditeur = nomEditeur;
                }
                
                private void setDateParution(GregorianCalendar dateParution){
                    this._dateParution = dateParution;
                }
                
                private void setNomAuteur(HashSet<String> nomsAuteurs){
                    this._nomsAuteurs = nomsAuteurs;
                }
                
                private void setPublicCible(PublicCible publicCible){
                    this._publicCible = publicCible;
                }
                
                private void setNbExemplaires(Integer nbExemplaires){
                    this._nbExemplaires = nbExemplaires;
                }

                private void setDerNumEx(Integer derNumEx){
                    this._derNumEx = derNumEx;
                }

                private void setExemplaires(HashMap<Integer, Exemplaire> dicoExemplaire) {
                    this._dicoExemplaire = dicoExemplaire;
                }                
                
                //-------------------------------------------
                        // Methodes
                //-------------------------------------------                                                                              
                
                /*
                * La méthode lierExemplaire permet d'ajouter un exemplaire a la base de données de ouvrage.
                */
                private void lierExemplaire(Integer numExemplaire, Exemplaire E)
                {
                    _dicoExemplaire.put(numExemplaire, E);
                }
                
                /*
                * La méthode delierExemplaire permet de supprimer un exemplaire de la base de données de l'ouvrage.        
                */
                 private void delierExemplaire(Integer numExemplaire){
                _dicoExemplaire.remove(numExemplaire);
                }
         
                /*
                * La méthode nouveauNumLect permet d'avoir d'incrémenter le numéro de lecteur et d'obtenir le numéro suivant
                * non utilisé.
                */
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
                
                private void supprimerUnNbExemplaire() {
                    Integer nbExTemp;
                    nbExTemp = this.getNbExemplaires();
                    nbExTemp = nbExTemp- 1;
                    this.setNbExemplaires(nbExTemp);
                }
                
        	/*
                 * La méthode lesExemplaires permet de créer un iterator sur les exemplaires, dans le but de les parcourir
                 * pour pouvoir afficher leurs informations.
                 */
                private Iterator<Exemplaire> lesExemplaires() {
		return _dicoExemplaire.values().iterator();
                }
                
}
