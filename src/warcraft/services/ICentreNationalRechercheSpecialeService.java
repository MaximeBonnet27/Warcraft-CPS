/**
 * 
 */
package warcraft.services;

/**
 * @author 3100381
 *
 */
public interface ICentreNationalRechercheSpecialeService {

	// Observators
	
	public int prixConstruction();
	public int prixRecherche();
	public int tempsDeConstruction();
	public int tempsDeRecherche();
	public int tempsCourant();
	public boolean enConstruction();
	public boolean constructionFinie();
	public int rechercheCourante();
	public boolean enRecherche();
	public boolean rechercheFinie();
	
	// Invariants
	
	/**
	 * \inv : 0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()
	 * \inv : enConstruction() == (tempsCourant() > 0)
	 * \inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())
	 * \inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()
	 * \inv : enRecherche() == (rechercheCourante() > 0)
	 * \inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())
	 */
	
	// Constructors
	/**
	 * \pre : tempsDeConstruction > 0
	 * \pre : tempsDeRecherche > 0
	 * \pre : prixConstruction > 0
	 * \pre : prixRecherche > 0
	 * 
	 * \post : tempsDeConstruction() == tempsDeConstruction
	 * \post : tempsDeRecherche() == tempsDeRecherche
	 * \post : prixConstruction() == prixConstruction
	 * \post : prixRecherche() == prixRecherche
	 * \post : tempsCourant() == 0
	 * \post : rechercheCourante() == 0
	 */
	public ICentreNationalRechercheSpecialeService init(int tempsDeConstruction, int tempsDeRecherche, int prixConstruction, int prixRecherche);
	
	// Operators
	
	/**
	 * \pre : !enConstruction()
	 * \pre : prix == prixConstruction()
	 * 
	 * \post : tempsCourant() == 1
	 */
	public ICentreNationalRechercheSpecialeService commencerConstruction(int prix);
	
	/**
	 * \pre : !constructionFinie()
	 * 
	 * \post : tempsCourant() == tempsCourant()@pre + 1
	 */
	public ICentreNationalRechercheSpecialeService construire();
	
	/**
	 * \pre : constructionFinie()
	 * \pre : prix == prixRecherche()
	 * 
	 * \post : rechercheCourante() == 1
	 */
	public ICentreNationalRechercheSpecialeService commencerRecherche(int prix);
	
	/**
	 * \pre : !rechercheFinie()
	 * 
	 * \post : rechercheCourante() == rechercheCourante()@pre + 1
	 */
	public ICentreNationalRechercheSpecialeService recherche();
	
}
