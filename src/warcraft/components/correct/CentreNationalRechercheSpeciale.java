/**
 * 
 */
package warcraft.components.correct;

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
	public void init(
			int tempsDeConstruction, int tempsDeRecherche,
			int prixConstruction, int prixRecherche) {
			this.tempsDeConstruction = tempsDeConstruction;
			this.tempsDeRecherche = tempsDeRecherche;
			this.prixConstruction = prixConstruction;
			this.prixRecherche = prixRecherche;
			this.tempsCourant = 0;
			this.rechercheCourante = 0;
	}

	@Override
	public void commencerConstruction(
			int prix) {
		tempsCourant = 1;
	}

	@Override
	public void construire() {
		tempsCourant += 1;
	}

	@Override
	public void commencerRecherche(int prix) {
		rechercheCourante = 1;
	}

	@Override
	public void recherche() {
		rechercheCourante += 1;
	}

}
