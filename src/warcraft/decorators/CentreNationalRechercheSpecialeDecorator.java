/**
 * 
 */
package warcraft.decorators;

import warcraft.enums.ECompetence;
import warcraft.services.ICentreNationalRechercheSpecialeService;

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

	public ECompetence competenceAugmente() throws Exception {
		return delegate.competenceAugmente();
	}


	public int boost() throws Exception {
		return delegate.boost();
	}


	public void finirRecherche() throws Exception {
		delegate.finirRecherche();
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
			int prixConstruction, int prixRecherche) throws Exception {
		delegate.init(tempsDeConstruction, tempsDeRecherche, prixConstruction,
				prixRecherche);
	}

	public void commencerConstruction() throws Exception {
		delegate.commencerConstruction();
	}

	public void construire() throws Exception {
		delegate.construire();
	}

	public void commencerRecherche() throws Exception {
		delegate.commencerRecherche();
	}

	public void recherche() throws Exception {
		delegate.recherche();
	}
	
	
}
