import java.io.Serializable;
import java.util.GregorianCalendar;

// Classe de gestion de Lecteur

public class Exemplaire implements Serializable 
{
	
	private static final long serialVersionUID = 422L;
	
	// -----------------------------------------------
		//Attributs
	// -----------------------------------------------
	
                private Integer _numExemplaire;
		private GregorianCalendar _dateReception;
		private Disponibilite _dispo;
                private Ouvrage _ouvrage;
                private Emprunt _emprunt;
                
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
		
		public Exemplaire(Integer numExemplaire, GregorianCalendar dateReception, Disponibilite dispo, Ouvrage ouvrage)
		{
			this.setNumExemplaire(numExemplaire);
			this.setDateReception(dateReception);
			this.setDisponibilite(dispo);
                        this.lierOuvrage(ouvrage);
                        this.setEmprunt(null);
		}
		
// -----------------------------------------------
	// Public
// -----------------------------------------------

                //----------------------------------------------
                        // Getters
                //----------------------------------------------

                public Emprunt getEmprunt(){
                        return _emprunt;
                }
		public GregorianCalendar getDateReception(){
			return _dateReception;
		}                

		// -----------------------------------------------
			// Methodes
		// -----------------------------------------------
		
		/*
		 * La méthode afficherExemplaire affiche l'ensemble des informations relatives à un exemplaire.
		 */
		public void afficherExemplaire()                 
		{
                        Emprunt e;
                        System.out.println("Numéro d'exemplaire: " + this.getNumExemplaire());
			System.out.println("Date de réception : " + EntreesSorties.ecrireDate(this.getDateReception()));
			System.out.println("Public cible : " + this.getOuvrage().getPublicCible());                        
                        System.out.println("Cet exemplaire est " + this.getDisponibilite());
                        if (this.etatNonEmprunte())
                        {
                            System.out.println("Exemplaire actuellement non emprunté");
                        }
                        else
                        {
                            e = this.getEmprunt();
                            System.out.println("Exemplaire actuellement emprunté");
                            e.afficherLecteurEmprunt();
                            e.afficherDatesEmprunt();
                        }
			EntreesSorties.afficherMessage("");
		}
                
                /*
                * La méthode afficherEmpruntExemplaire permet d'afficher le titre de l'ouvrage
                * de l'exemplaire considéré ainsi que son numéro ISBN
                */
                public void afficherEmpruntExemplaire()
                {                        
                        this.getOuvrage().afficherISBNTitre();
                        this.afficherNumExemplaire();
                }
                
                /*
                * La methode afficherNumExemplaire permet d'afficher le numero de l'exemplaire considéré
                */
                
                public void afficherNumExemplaire()
                {
                        System.out.println("Numéro d'exemplaire: " + this.getNumExemplaire());                    
                }
              
                /*
                * La methode etatNonEmprunte permet de savoir si l'exemplaire est actuellement emprunte ou non
                */
	
                public boolean etatNonEmprunte(){
                        return this.getEmprunt()==null;
                }

                /*
                * La methode empruntable permet de savoir si un exemplaire est empruntable
                */
                public boolean empruntable(){
                        return this.getDisponibilite().equals(Disponibilite.empruntable);
                }
                
                
                
                /*
                * La methode ajouterEmprunt permet d'ajouter un emprunt a l'emplaire courant
                */
                
                public void ajouterEmprunt(Emprunt emprunt){
                        this.lierEmprunt(emprunt);
                }
                
                /*
                * La methode rendreEmprunt permet de supprimer l'emprunt en cours de la bibliotheque             
                */                
                public void rendreEmprunt(){
                        this.delierEmprunt();
                }                
                
// -----------------------------------------------
	// Private
// -----------------------------------------------
		
		// -----------------------------------------------
			//Getters
		// -----------------------------------------------

		private Integer getNumExemplaire(){
			return _numExemplaire;
		}
		
		private Disponibilite getDisponibilite(){
			return _dispo;
		}
                
                private Ouvrage getOuvrage(){
                        return _ouvrage;
                }
                
		// -----------------------------------------------
			//Setters
		// -----------------------------------------------

		private void setNumExemplaire(Integer numExemplaire){
			this._numExemplaire = numExemplaire;
		}

		private void setDateReception(GregorianCalendar dateReception){
			this._dateReception = dateReception;
		}

		private void setDisponibilite(Disponibilite dispo){
			this._dispo = dispo;
		}
                
                private void setOuvrage(Ouvrage ouvrage){
                    this._ouvrage = ouvrage;
                }
                
                private void setEmprunt(Emprunt emprunt){
                    this._emprunt = emprunt;
                }

                //--------------------------------------------------
                        // Methodes
                //--------------------------------------------------
                
                private void lierOuvrage(Ouvrage ouvrage){
                    this.setOuvrage(ouvrage);
                }
                
                private void lierEmprunt(Emprunt emprunt){
                    this.setEmprunt(emprunt);
                }
                
                private void delierEmprunt(){
                    this.setEmprunt(null);
                }
}
		
		
