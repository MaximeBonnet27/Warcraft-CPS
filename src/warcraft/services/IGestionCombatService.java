/**
 * 
 */
package warcraft.services;

import java.util.Set;

public interface IGestionCombatService {

	// Observators
	public IVillageoisService attaquant();
	public IVillageoisService defenseur();
	/**
	 * \pre : estCombatMultiple()
	 */
	public Set<IVillageoisService> setAttaquant();
	/**
	 * \pre : estCombatMuraille()
	 */
	public IMurailleService muraille();
	public boolean estCombatMultiple();
	public boolean estCombatMuraille();

	// Invariants

	// Constructors
	/**
	 * \post : attaquant() == attaquant
	 * \post : defenseur() == defenseur
	 * \post : estCombatMultiple() == false
	 * \post : estCombatMuraille() == false
	 */
	public void init(IVillageoisService attaquant, IVillageoisService defenseur);
	
	 /**
   * \post : setAttaquant() == attaquants
   * \post : defenseur() == defenseur
   * \post : estCombatMultiple() == true
   * \post : estCombatMuraille() == false
   */
  public void initMultiple(Set<IVillageoisService> attaquants, IVillageoisService defenseur);

  /**
   * \post : attaquant() == attaquant
   * \post : muraille() == muraille
   * \post : estCombatMultiple() == false
   * \post : estCombatMuraille() == true
   */
  public void initMuraille(IVillageoisService attaquant, IMurailleService muraille);
  
  /**
  * \post : setAttaquant() == attaquants
  * \post : muraille() == muraille
  * \post : estCombatMultiple() == true
  * \post : estCombatMuraille() == true
  */
 public void initMurailleMultiple(Set<IVillageoisService> attaquants, IMurailleService muraille);

  
	// Operators

	/**
	 * \pre : !estCombatMultiple()
   * \pre : !estCombatMuraille()
	 * \pre : !attaquant().estMort()
	 * \pre : !defenseur().estMort()
	 * 
	 * \post : attaquant().pointsDeVie() == attaquant().pointsDeVie()@pre
	 * \post : defenseur().pointsDeVie() == defenseur()@pre.retraitPV(attaquant()@pre.force())
	 */
	public void combat();
	
	 /**
   * \pre : estCombatMultiple()
   * \pre : !estCombatMuraille()
   * \pre : \forall attaquant \in setAttaquant() : !attaquant.estMort()
   * \pre : !defenseur().estMort()
   * 
   * \post : \forall attaquant \in setAttaquant() : attaquant.pointsDeVie() == attaquant.pointsDeVie()@pre
   * \post : defenseur() ==  defenseur()@pre.retraitPV(\sum \forall attaquant in setAttaquant()@pre : attaquant.force())
   */
  public void combatMultiple();
  
  /**
   * \pre : !estCombatMultiple()
   * \pre : estCombatMuraille()
   * \pre : !attaquant().estMort()
   * \pre : !muraille().estDetruite()
   * 
   * \post : attaquant().pointsDeVie() == attaquant().pointsDeVie()@pre
   * \post : muraille().pointsDeVie() == muraille()@pre.taper(attaquant()@pre.force())
   */
  public void combatMuraille();
  
  /**
   * \pre : estCombatMultiple()
   * \pre : estCombatMuraille()
   * \pre : \forall attaquant \in setAttaquant() : !attaquant.estMort()
   * \pre : !muraille().estDetruite()
   * 
   * \post : \forall attaquant \in setAttaquant() : attaquant.pointsDeVie() == attaquant.pointsDeVie()@pre
   * \post : muraille().pointsDeVie() == muraille()@pre.taper(\sum \forall attaquant in setAttaquant()@pre : attaquant.force())
   */
  public void combatMurailleMultiple();
}
