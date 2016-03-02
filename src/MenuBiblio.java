
public class MenuBiblio {
	private Bibliotheque _bibliotheque;
	
	public MenuBiblio (Bibliotheque bibliotheque) {
	_bibliotheque = bibliotheque;
	}
	
	/*
	 * menuPrincipal permet � l'utilisateur de selectionner un type de sous menu (Lecteur, Ouvrage ou Exemplaire) 
	 * o� il effectuera par la suite l'action d�sir�e. Si l'utilisateur a fini d'utiliuser le programme, il choisit l'option Quitter.
	*/
public void menuPrincipal() {
	Integer menu;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("|                   Menu Principal                       |");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Menu Lecteur : 1                                       |");
		EntreesSorties.afficherMessage("| Menu Ouvrage : 2                                       |");
                EntreesSorties.afficherMessage("| Menu Exemplaire: 3                                     |");
                EntreesSorties.afficherMessage("| Menu Périodique: 4                                     |");                
                EntreesSorties.afficherMessage("| Menu Article: 5                                        |");
                EntreesSorties.afficherMessage("| Menu Emprunt: 6                                        |");
                EntreesSorties.afficherMessage("| Menu Recherche: 7                                      |");                
                EntreesSorties.afficherMessage("| Quitter : 0                                            |");
		EntreesSorties.afficherMessage(" ========================================================");
		menu = EntreesSorties.lireEntier();
			
			switch (menu){
				case 1 : {
					this.menuLecteur();
					break;
				}
                                
                                case 2 : {
                                        this.menuOuvrage();
                                        break;
                                }
                                
                                case 3 : {
                                        this.menuExemplaire();
                                        break;
                                }

                                case 4 : {
                                        this.menuPeriodique();
                                        break;
                                }
                                case 5 : {
                                        this.menuArticle();
                                        break;
                                }                                
                                case 6 : {
                                        this.menuEmprunt();
                                        break;
                                }
                                case 7 : {
                                        this.menuRecherche();
                                        break;
                                }				
				default : {
					break;
				}
			}
	} while (menu != 0);	
}

	/* menuLecteur permet d'effectuer une série d'action concernant les utilisateur (lecteurs) de la biblioth�que.
	 * Une fois une action effectuée, l'utilisateur sera redirigé vers ce méme menu afin de pouvoir selectionner
	 * une nouvelle fois une action concernant les lecteurs.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
	*/
public void menuLecteur() {
	Integer menuLect;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouveau Lecteur : 1                                    |");
		EntreesSorties.afficherMessage("| Consulter Lecteur : 2                                  |");
                EntreesSorties.afficherMessage("| Liste numeros de lecteurs : 3                          |");
		EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuLect = EntreesSorties.lireEntier();
			
			switch (menuLect){
				case 1 : {
					_bibliotheque.nouveauLecteur();
					break;
				}
				case 2 : {
					_bibliotheque.consulterLecteur();
					break;
				}
                                case 3 : {
                                        _bibliotheque.listerLecteurs();
                                        break;
                                }
				default : {
					break;
				}
			}
	} while (menuLect != 0);	
}

	/* menuOuvrage permet d'effectuer une série d'action concernant les ouvrages de la biblioth�que.
	 * Une fois une action effectuée, l'utilisateur sera redirigé vers ce méme menu afin de pouvoir selectionner
	 * une nouvelle fois une action concernant les ouvrages.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
	*/

public void menuOuvrage() {
	Integer menuOuvrage;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvel ouvrage : 1                                     |");
                EntreesSorties.afficherMessage("| Liste des numéros ISBN : 2                             |");
		EntreesSorties.afficherMessage("| Consulter ouvrage : 3                                  |");
                EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuOuvrage = EntreesSorties.lireEntier();
			
			switch (menuOuvrage){
				case 1 : {
					_bibliotheque.nouvelOuvrage();
					break;
				}
                                case 3 : {
                                        _bibliotheque.listerOuvrages();
                                        break;
                                }
				case 4 : {
					_bibliotheque.consulterOuvrage();
					break;
				}
				default : {
					break;
				}
			}
	} while (menuOuvrage != 0);	
}

	/* menuExemplaire permet d'effectuer une série d'action concernant les exemplaires de la bibliothèque.
	 * Une fois une action effectuée, l'utilisateur sera redirigé vers ce méme menu afin de pouvoir selectionner
	 * une nouvelle fois une action concernant les exemplaires.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
	*/


public void menuExemplaire() {
	Integer menuLect;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvel exemplaire : 1                                  |");
		EntreesSorties.afficherMessage("| Consulter exemplaires ouvrage : 2                      |");
		EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuLect = EntreesSorties.lireEntier();
			
			switch (menuLect){
				case 1 : {
					_bibliotheque.nouvelExemplaire();
					break;
				}
				case 2 : {
					_bibliotheque.consulterExemplairesOuvrage();
					break;
				}
				default : {
					break;
				}
			}
	} while (menuLect != 0);	
}

public void menuEmprunt() {
	Integer menuEmprunt;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvel emprunt : 1                                     |");
                EntreesSorties.afficherMessage("| Rendre un emprunt : 2                                  |");
		EntreesSorties.afficherMessage("| Consulter les emprunts d'un lecteur : 3                |");
                EntreesSorties.afficherMessage("| Consulter les retards : 4                              |");
                EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuEmprunt = EntreesSorties.lireEntier();
			
			switch (menuEmprunt){
				case 1 : {
					_bibliotheque.emprunterExemplaire();
					break;
				}
                                case 2 : {
                                        _bibliotheque.rendreExemplaire();
                                        break;
                                }
				case 3 : {
					_bibliotheque.consulterEmpruntsLecteur();
					break;
				}
                                case 4 : {
                                        _bibliotheque.relancerLecteur();
                                }
				default : {
					break;
				}                               
			}
	} while (menuEmprunt != 0);	
}

public void menuPeriodique() {
	Integer menuPeriodique;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvelle Parution : 1                                  |");
		EntreesSorties.afficherMessage("| Liste des codes de parution : 2                        |");
		EntreesSorties.afficherMessage("| Consulter parution : 3                                 |");
                EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuPeriodique = EntreesSorties.lireEntier();
			
			switch (menuPeriodique){
				case 1 : {
					_bibliotheque.nouvelleParution();
					break;
				}
				case 2 : {
					_bibliotheque.listerParutions();
					break;
				}                                
				case 3 : {
					_bibliotheque.consulterParution();
					break;
				}                                
				default : {
					break;
				}                               
			}
	} while (menuPeriodique != 0);	
}
public void menuArticle() {
	Integer menuArt;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Nouvel article : 1                                     |");
		EntreesSorties.afficherMessage("| Consulter articles parution : 2                        |");
                EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
		EntreesSorties.afficherMessage(" ========================================================");
		menuArt = EntreesSorties.lireEntier();
			
			switch (menuArt){
				case 1 : {
					_bibliotheque.nouvelArticle();
					break;
				}
				case 2 : {
					_bibliotheque.consulterArticlesParution();
					break;
				}                              
				default : {
					break;
				}
			}
	} while (menuArt != 0);	
}

public void menuRecherche() {
	Integer menuRech;
	do {
		EntreesSorties.afficherMessage(" ========================================================");
		EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
		EntreesSorties.afficherMessage("| Lister les titres : 1                                  |");
                EntreesSorties.afficherMessage("| Lister les auteurs : 2                                 |");
		EntreesSorties.afficherMessage("| Lister les mots-clés : 3                               |");
                EntreesSorties.afficherMessage("| Recherche par titre : 4                                |");
                EntreesSorties.afficherMessage("| Recherche par auteur : 5                               |");
                EntreesSorties.afficherMessage("| Recherche par mot-clé : 6                              |");              
                EntreesSorties.afficherMessage("| Retour au menu principal : 0                           |");
                EntreesSorties.afficherMessage(" ========================================================");
		menuRech = EntreesSorties.lireEntier();
			
			switch (menuRech){
				case 1 : {
					_bibliotheque.listerTitres();
					break;
				}
                                case 2 : {
                                        _bibliotheque.listerAuteurs();
                                        break;
                                }
				case 3 : {
					_bibliotheque.listerMotsCles();
					break;
				}
				case 4 : {
					_bibliotheque.rechercheTitre();
					break;
				}
				case 5 : {
					_bibliotheque.rechercheAuteur();
					break;
				}                                
				case 6 : {
					_bibliotheque.rechercheMotCle();
					break;
				}
                                default : {
					break;
				}
			}
	} while (menuRech != 0);	
}

}

