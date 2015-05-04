package warcraft.decorators;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IHotelDeVilleService;

public abstract class HotelDeVilleDecorator implements IHotelDeVilleService {

	private final IHotelDeVilleService delegate;

	public HotelDeVilleDecorator(IHotelDeVilleService delegate) {
		this.delegate = delegate;
	}

	public int largeur() {
		return delegate.largeur();
	}

	public int hauteur() {
		return delegate.hauteur();
	}

	public int orRestant() {
		return delegate.orRestant();
	}

	public boolean estLaminne() {
		return delegate.estLaminne();
	}

	public int compteurAbandon() {
		return delegate.compteurAbandon();
	}

	public EETAT etat_d_appartenance() {
		return delegate.etat_d_appartenance();
	}

	public ERace appartenance() {
		return delegate.appartenance();
	}

	public void init(int largeur, int hauteur, ERace race) {
		delegate.init(largeur, hauteur, race);
	}

	public void retrait(int s) {
		delegate.retrait(s);
	}

	public void depot(int d) {
		delegate.depot(d);
	}

	public void accueil(ERace race) {
		delegate.accueil(race);
	}

	public void abandoned() {
		delegate.abandoned();
	}
	
	
}
