/**
 * 
 */
package warcraft.contracts;

import static org.junit.Assert.assertTrue;
import warcraft.decorators.GestionCombatDecorator;
import warcraft.services.IGestionCombatService;
import warcraft.services.IVillageoisService;

public class GestionCombatContrat extends GestionCombatDecorator{

	public GestionCombatContrat(IGestionCombatService delegate) {
		super(delegate);

	}

	public void checkInvariants(){
		// pas d'invariants
	}

	@Override
	public IVillageoisService attaquant() {
		return super.attaquant();
	}

	@Override
	public IVillageoisService defenseur() {
		return super.defenseur();
	}


	@Override
	public void init(IVillageoisService attaquant,
			IVillageoisService defenseur) {
		// Pré-Conditions

		// \pre : !attaquant.estMort()
		assertTrue("\\pre : !attaquant.estMort()", !attaquant.estMort());
		// \pre : !defenseur.estMort()
		assertTrue("\\pre : !defenseur.estMort()", !defenseur.estMort());

		// Pas d'invariants avant initialisation

		// Appel
		super.init(attaquant, defenseur);

		// Invariants
		checkInvariants();

		// Post-Conditions

		// \post : attaquant() == attaquant
		assertTrue("\\post : attaquant() == attaquant", super.attaquant() == attaquant);
		// \post : defenseur() == defenseur
		assertTrue("\\post : defenseur() == defenseur", super.defenseur() == defenseur);

	}

	@Override
	public void combat() {

		// Captures
		int oldAttaquant_PointsDeVie = super.attaquant().pointsDeVie();
		int oldAttaquant_Force = super.attaquant().force();
		int oldDefenseur_PointsDeVie = super.defenseur().pointsDeVie();
		// Pré-Conditions

		// \pre : !attaquant.estMort()
		assertTrue("\\pre : !attaquant.estMort()", !super.attaquant().estMort());
		// \pre : !defenseur.estMort()
		assertTrue("\\pre : !defenseur.estMort()", !super.defenseur().estMort());

		// Invariants
		checkInvariants();
		
		// Appel
		super.combat();
		
		// Invariants
		checkInvariants();
		
		// Post-Conditions
		
		// \post : attaquant().pointsDeVie() == attaquant().pointsDeVie()@pre
		assertTrue("attaquant().pointsDeVie() == attaquant().pointsDeVie()@pre", super.attaquant().pointsDeVie() == oldAttaquant_PointsDeVie);
		// \post : defenseur().pointsDeVie() == defenseur().pointsDeVie()@pre - attaquant().force()@pre
		assertTrue("defenseur().pointsDeVie() == defenseur().pointsDeVie()@pre - attaquant().force()", super.defenseur().pointsDeVie() == oldDefenseur_PointsDeVie - oldAttaquant_Force);
		
	}



}
