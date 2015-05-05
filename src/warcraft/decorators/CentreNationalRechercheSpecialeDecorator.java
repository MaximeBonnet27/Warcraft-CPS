/**
 * 
 */
package warcraft.decorators;

import warcraft.services.ICentreNationalRechercheSpecialeService;

/**
 * @author 3100381
 *
 */
public abstract class CentreNationalRechercheSpecialeDecorator implements ICentreNationalRechercheSpecialeService{

	private ICentreNationalRechercheSpecialeService delegate;

	
	public CentreNationalRechercheSpecialeDecorator(
			ICentreNationalRechercheSpecialeService delegate) {
		super();
		this.delegate = delegate;
	}

	public int prixConstruction() {
		return delegate.prixConstruction();
	}

	public int prixRecherche() {
		return delegate.prixRecherche();
	}

	public int tempsDeConstruction() {
		return delegate.tempsDeConstruction();
	}

	public int tempsDeRecherche() {
		return delegate.tempsDeRecherche();
	}

	public int tempsCourant() {
		return delegate.tempsCourant();
	}

	public boolean enConstruction() {
		return delegate.enConstruction();
	}

	public boolean constructionFinie() {
		return delegate.constructionFinie();
	}

	public int rechercheCourante() {
		return delegate.rechercheCourante();
	}

	public boolean enRecherche() {
		return delegate.enRecherche();
	}

	public boolean rechercheFinie() {
		return delegate.rechercheFinie();
	}

	public ICentreNationalRechercheSpecialeService init(
			int tempsDeConstruction, int tempsDeRecherche,
			int prixConstruction, int prixRecherche) {
		return delegate.init(tempsDeConstruction, tempsDeRecherche,
				prixConstruction, prixRecherche);
	}

	public ICentreNationalRechercheSpecialeService commencerConstruction(
			int prix) {
		return delegate.commencerConstruction(prix);
	}

	public ICentreNationalRechercheSpecialeService construire() {
		return delegate.construire();
	}

	public ICentreNationalRechercheSpecialeService commencerRecherche(int prix) {
		return delegate.commencerRecherche(prix);
	}

	public ICentreNationalRechercheSpecialeService recherche() {
		return delegate.recherche();
	}
	
	
}
