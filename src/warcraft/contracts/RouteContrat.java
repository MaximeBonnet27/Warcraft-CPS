package warcraft.contracts;

import static org.junit.Assert.assertTrue;
import warcraft.decorators.RouteDecorator;
import warcraft.services.IRouteService;

public class RouteContrat extends RouteDecorator {

	public RouteContrat(IRouteService delegate) {
		super(delegate);
	}

	public void checkInvariants() {
		// Pas d'invariants
	}

	@Override
	public int largeur() {
		return super.largeur();
	}

	@Override
	public int hauteur() {
		return super.hauteur();
	}

	@Override
	public double multiplicateur() {
		return super.multiplicateur();
	}

	@Override
	public void init(int largeur, int hauteur, double mult) throws Exception {
		// Pre-Conditions

		// \pre : largeur > 0
		assertTrue("\\pre : largeur > 0", largeur > 0);
		// \pre : hauteur > 0
		assertTrue("\\pre : hauteur > 0", hauteur > 0);
		// \pre : mult >= 1.0
		assertTrue("\\pre : mult >= 1.0", mult >= 1.0);

		// Pas d'invariants avant l'initialisation

		// Appel
		super.init(largeur, hauteur, mult);

		// Invariants
		checkInvariants();

		// Post-Conditions

		// \post: largeur() == largeur
		assertTrue("\\post : largeur() == largeur", super.largeur() == largeur);
		// \post : hauteur() == hauteur
		assertTrue("\\post : hauteur() == hauteur", super.hauteur() == hauteur);
		// \post : multiplicateur() == mult
		assertTrue("\\post : multiplicateur() == mult", super.multiplicateur() == mult);
		
	}

}
