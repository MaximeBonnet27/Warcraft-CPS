package warcraft.services;

import warcraft.enums.ERace;

public interface IVillageois {

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
	public IVillageois init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie);

	// Operators
	/**
	 * \pre : !estMort() 
	 * \pre : degats>0
	 * 
	 * \post: pointsDeVie() == pointsDeVie()@pre - degats
	 * \post : quantiteOr() == quantiteOr()@pre 
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 */
	public IVillageois retraitPV(int degats);

	/**
	 * \pre: somme >= 0
	 * 
	 * \post : pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre + somme 
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 */
	public IVillageois ajouterOr(int somme);

	/**
	 * \pre : quantiteOr()-somme >= 0
	 * \pre :  somme >= 0
	 * 
	 * \post : pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre - somme 
	 * \post : compteurCorvee() == compteurCorvee()@pre
	 */

	public IVillageois retraitOr(int somme);

	/**
	 * \post: pointsDeVie() == pointsDeVie()@pre
	 * \post : quantiteOr() == quantiteOr()@pre
	 * \post : compteurCorvee() == 1
	 */

	public IVillageois commenceTravaille();

	/**
	 * \pre : !corveeFinie()
	 * 
	 * \post : pointsDeVie() == pointsDeVie()@pre 
	 * \post : quantiteOr() == quantiteOr()@pre 
	 * \post : compteurCorvee() == compteurCorvee()@pre + 1
	 */

	public IVillageois travaille();

}
