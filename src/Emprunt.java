import java.io.Serializable;
import java.util.GregorianCalendar;

// Classe de gestion des emprunts

public class Emprunt implements Serializable 
{
	
	private static final long serialVersionUID = 422L;
	
	// -----------------------------------------------
		//Attributs
	// -----------------------------------------------
	
		private GregorianCalendar _dateEmprunt;
                private GregorianCalendar _dateRetour;
                private Exemplaire _exemplaire;
                private Lecteur _lecteur;
                
	// -----------------------------------------------
		//Constructeur
	// -----------------------------------------------
		
		public Emprunt(GregorianCalendar dateEmprunt, GregorianCalendar dateRetour, Exemplaire exemplaire, Lecteur lecteur)
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
		
		// -----------------------------------------------
			//Getters
		// -----------------------------------------------

		public Integer getNumExemplaire(){
			return _numExemplaire;
		}
		
		public GregorianCalendar getDateReception(){
			return _dateReception;
		}

		public Disponibilite getDisponibilite(){
			return _dispo;
		}
                
                public Ouvrage getOuvrage(){
                        return _ouvrage;
                }
                
                public Emprunt getEmprunt(){
                        return _emprunt;
                }
		// -----------------------------------------------
			// Methodes
		// -----------------------------------------------
		
		/*
		 * La méthode afficherExemplaire affiche l'ensemble des informations relatives à un exemplaire.
		 */
		public void afficherExemplaire()
		{
//			System.out.println("Numéro ISBN: " + this.getOuvrage().getISBN());
                        System.out.println("Numéro d'exemplaire: " + this.getNumExemplaire());
			System.out.println("Date de réception : " + EntreesSorties.ecrireDate(this.getDateReception()));
                        System.out.println("Cet exemplaire est " + this.getDisponibilite());
			EntreesSorties.afficherMessage("");
		}
	
// -----------------------------------------------
	// Private
// -----------------------------------------------

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
}
		
		
