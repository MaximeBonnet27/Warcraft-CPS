package warcraft.decorators;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IMineService;

public abstract class MineDelegator implements IMineService {

	private final IMineService delegate;

	public MineDelegator(IMineService delegate) {
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

	public void init(int largeur, int hauteur) throws Exception {
		delegate.init(largeur, hauteur);
	}

	public void retrait(int s) throws Exception {
		delegate.retrait(s);
	}

	public void accueil(ERace race) throws Exception {
		delegate.accueil(race);
	}

	public void abandoned() throws Exception {
		delegate.abandoned();
	}
	
	
	
}
