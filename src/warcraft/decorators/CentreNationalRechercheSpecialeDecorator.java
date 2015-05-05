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

	public void init(int tempsDeConstruction, int tempsDeRecherche,
			int prixConstruction, int prixRecherche) {
		delegate.init(tempsDeConstruction, tempsDeRecherche, prixConstruction,
				prixRecherche);
	}

	public void commencerConstruction(int prix) {
		delegate.commencerConstruction(prix);
	}

	public void construire() {
		delegate.construire();
	}

	public void commencerRecherche(int prix) {
		delegate.commencerRecherche(prix);
	}

	public void recherche() {
		delegate.recherche();
	}
	
	
}
