package warcraft.services;

import warcraft.enums.ECompetence;
import warcraft.enums.ERace;

public interface IVillageoisService {

	// Observators

	public ERace race();

	public int largeur();

	public int hauteur();

	public int force();

	public double vitesse();

	public int pointsDeVie();

	public boolean estMort();

	public int quantiteOr();

	public int compteurCorvee();

	public boolean corveeFinie();

	public boolean enCorvee();

	// Invariants

	/**
	 * \inv : estMort() == (pointsDeVie() <= 0)
	 * \inv : corveeFinie() == (compteurCorvee()==16)
	 * \inv : enCorvee() == (compteurCorvee()>0)
	 * \inv : quantiteOr() >= 0
	 * \inv : 0 <= compteurCorvee() && compteurCorvee() <= 16
	 * \inv : force() > 0
	 * \inv : vitesse() > 0
	 */

	// Constructors

	/**
	 * \pre : largeur>0 
	 * \pre : hauteur>0
	 * \pre : force>0 
	 * \pre : vitesse>0 
	 * \pre : pointsDeVie>0
	 * 
	 * \post : race() == r 
	 * \post : largeur() == l
	 * \post : hauteur() == h 
	 * \post : force() == f
	 * \post : vitesse() == v 
	 * \post : pointsDeVie() == p
	 * \post : quantiteOr() == 0 
	 * \post : compteurCorvee() == 0
	 */
	public void init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie);

	// Operators
	/**
	 * \pre : !estMort() 
	 * \pre : degats>0
	 * 
	 * \post: pointsDeVie() == pointsDeVie()@pre - degats
	 * \post : quantiteOr() == quantiteOr()@pre 
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 * \post : vitesse() == vitesse()@pre
	 * \post : force() == force()@pre
	 */
	public void retraitPV(int degats);

	/**
	 * \pre : !estMort() 
	 * \pre: somme >= 0
	 * 
	 * \post : pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre + somme 
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 * \post : vitesse() == vitesse()@pre
	 * \post : force() == force()@pre
	 */
	public void ajouterOr(int somme);

	/**
	 * \pre : !estMort() 
	 * \pre : quantiteOr()-somme >= 0
	 * \pre :  somme >= 0
	 * 
	 * \post : pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre - somme 
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 * \post : vitesse() == vitesse()@pre
	 * \post : force() == force()@pre
	 */
	
	public void retraitOr(int somme);

	/**
	 * \pre : !estMort() 
	 * 
	 * \post: pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre
	 * \post : compteurCorvee() == 1
	 * \post : vitesse() == vitesse()@pre
	 * \post : force() == force()@pre
	 */

	public void commenceTravaille();

	/**
	 * \pre : !estMort() 
	 * \pre : !corveeFinie()
	 * 
	 * \post : pointsDeVie() == pointsDeVie()@pre 
	 * \post : quantiteOr() == quantiteOr()@pre 
	 * \post : compteurCorvee() == compteurCorvee()@pre + 1
	 * \post : vitesse() == vitesse()@pre
	 * \post : force() == force()@pre
	 */

	public void travaille();
	/**
	 * \pre : !estMort() 
	 * \pre : val > 0
	 * 
	 * \post : if(competence == COMPETENCE.PV)
	 * then pointsDeVie() == pointsDeVie()@pre + val
	 * else pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 * \post : if(competence == COMPETENCE.FORCE)
	 * then force() == force()@pre + val
	 * else force() == force()@pre
	 * \post : if(comptence == COMPETENCE.VITESSE)
	 * then vitesse() == vitese()@pre + val
	 * else vitesse() == vitesse()@pre
	 */
	public void amelioration(ECompetence competence, int val);
	
}
