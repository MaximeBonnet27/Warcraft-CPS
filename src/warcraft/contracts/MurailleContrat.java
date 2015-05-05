package warcraft.contracts;

import static org.junit.Assert.assertTrue;
import warcraft.decorators.MurailleDecorator;
import warcraft.services.IMurailleService;

public class MurailleContrat extends MurailleDecorator{

	public MurailleContrat(IMurailleService delegate) {
		super(delegate);
	}

	public void checkInvariants(){
		// \inv : estDetruite() == (pointsDeVie() <= 0)
		assertTrue("\\inv : estDetruite() == (pointsDeVie() <= 0)", super.estDetruite() == (super.pointsDeVie() <= 0));
		
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
	public int pointsDeVie() {
		return super.pointsDeVie();
	}

	@Override
	public boolean estDetruite() {
		return super.estDetruite();
	}

	@Override
	public void init(int largeur, int hauteur, int pv) {
		// Pre-Conditions
		
		// \pre : largeur > 0
		assertTrue("\\pre : largeur > 0", largeur > 0);
		// \pre : hauteur > 0
		assertTrue("\\pre : hauteur > 0", hauteur > 0);
		// \pre : pv > 0
		assertTrue("\\pre : pv > 0", pv > 0);
		
		// Pas d'invariants avant l'initialisation

		// Appel
		super.init(largeur, hauteur, pv);

		// Invariants
		checkInvariants();
		// Post-Conditions
		// \post : largeur() == largeur
		assertTrue("\\post : largeur() == largeur", super.largeur() == largeur);
		// \post : hauteur() == hauteur
		assertTrue("\\post : hauteur() == hauteur", super.hauteur() == hauteur);
		// \post : pointsDeVie() == pv
		assertTrue("\\post : pointsDeVie() == pv", super.pointsDeVie() == pv);
	}

	@Override
	public void taper(int degats) {
		// Capture
		int oldPointsDeVie = pointsDeVie();

		// Pre-Conditions
		// \pre : !estDetruite() 
		assertTrue("\\pre : !estDetruite() ", !super.estDetruite());
		// \pre : degats > 0
		assertTrue("\\pre : degats > 0", degats > 0);
		
		// Invariants
		checkInvariants();

		//Appel
		super.taper(degats);

		// Invariants
		checkInvariants();

		// Post-Conditions 
		// \post : pointsDeVie() == pointsDeVie()@pre - degats
		assertTrue("\\post : pointsDeVie(taper(x)) == pointsDeVie() - x", super.pointsDeVie() == oldPointsDeVie - degats);
	}



}
