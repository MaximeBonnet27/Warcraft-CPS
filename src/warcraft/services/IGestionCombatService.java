/**
 * 
 */
package warcraft.services;

public interface IGestionCombatService {

	// Observators
	public IVillageoisService attaquant();
	public IVillageoisService defenseur();

	// Invariants

	// Constructors
	/**
	 * \pre : !attaquant.estMort()
	 * \pre : !defenseur.estMort()
	 * 
	 * \post : attaquant() == attaquant
	 * \post : defenseur() == defenseur
	 */
	public IGestionCombatService init(IVillageoisService attaquant, IVillageoisService defenseur);

	// Operators

	/**
	 * \pre : !attaquant.estMort()
	 * \pre : !defenseur.estMort()
	 * 
	 * \post : attaquant.pointsDeVie() == attaquant.pointsDeVie()@pre
	 * \post : defenseur.pointsDeVie() == defenseur.pointsDeVie()@pre - attaquant.force()@pre
	 */
	public IGestionCombatService combat();
}
