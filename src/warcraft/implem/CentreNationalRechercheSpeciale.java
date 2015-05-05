/**
 * 
 */
package warcraft.implem;

import warcraft.services.ICentreNationalRechercheSpecialeService;

/**
 * @author 3100381
 *
 */
public class CentreNationalRechercheSpeciale implements ICentreNationalRechercheSpecialeService{

	private int prixConstruction;
	private int prixRecherche;
	private int tempsDeConstruction;
	private int tempsDeRecherche;
	private int tempsCourant;
	private int rechercheCourante;
	
	@Override
	public int prixConstruction() {
		return prixConstruction;
	}

	@Override
	public int prixRecherche() {
		return prixRecherche;
	}

	@Override
	public int tempsDeConstruction() {
		return tempsDeConstruction;
	}

	@Override
	public int tempsDeRecherche() {
		return tempsDeRecherche;
	}

	@Override
	public int tempsCourant() {
		return tempsCourant;
	}

	@Override
	public boolean enConstruction() {
		return tempsCourant > 0;
	}

	@Override
	public boolean constructionFinie() {
		return tempsCourant == tempsDeConstruction;
	}

	@Override
	public int rechercheCourante() {
		return rechercheCourante;
	}

	@Override
	public boolean enRecherche() {
		return rechercheCourante > 0;
	}

	@Override
	public boolean rechercheFinie() {
		return rechercheCourante == tempsDeRecherche;
	}

	@Override
	public ICentreNationalRechercheSpecialeService init(
			int tempsDeConstruction, int tempsDeRecherche,
			int prixConstruction, int prixRecherche) {
			CentreNationalRechercheSpeciale cnrs = new CentreNationalRechercheSpeciale();
			cnrs.tempsDeConstruction = tempsDeConstruction;
			cnrs.tempsDeRecherche = tempsDeRecherche;
			cnrs.prixConstruction = prixConstruction;
			cnrs.prixRecherche = prixRecherche;
			cnrs.tempsCourant = 0;
			cnrs.rechercheCourante = 0;
			return cnrs;
	}

	@Override
	public ICentreNationalRechercheSpecialeService commencerConstruction(
			int prix) {
		tempsCourant = 1;
		return this;
	}

	@Override
	public ICentreNationalRechercheSpecialeService construire() {
		tempsCourant += 1;
		return this;
	}

	@Override
	public ICentreNationalRechercheSpecialeService commencerRecherche(int prix) {
		rechercheCourante = 1;
		return this;
	}

	@Override
	public ICentreNationalRechercheSpecialeService recherche() {
		rechercheCourante += 1;
		return this;
	}

}
