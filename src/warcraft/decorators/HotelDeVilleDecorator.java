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

	public ERace appartenance() throws Exception {
		return delegate.appartenance();
	}

	public void init(int largeur, int hauteur, ERace race) throws Exception{
		delegate.init(largeur, hauteur, race);
	}

	public void retrait(int s)throws Exception {
		delegate.retrait(s);
	}

	public void depot(int d)throws Exception {
		delegate.depot(d);
	}

	public void accueil(ERace race)throws Exception {
		delegate.accueil(race);
	}

	public void abandoned()throws Exception {
		delegate.abandoned();
	}
	
	
}
