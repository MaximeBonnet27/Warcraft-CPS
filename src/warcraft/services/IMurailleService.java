package warcraft.services;

public interface IMurailleService {

	// Observators

	public int largeur();
	public int hauteur();
	public int pointsDeVie();
	public boolean estDetruite();

	// Invariants
	/**
	 * \inv : estDetruite() == (pointsDeVie() <= 0)
	 */

	// Constructors
	/** \pre :  largeur > 0
	 * \pre :  hauteur > 0
	 * \pre :  pv > 0
	 * \post :largeur() == largeur
	 * \post :hauteur() == hauteur
	 * \post :pointsDeVie() == pv
	 */
	public void init(int largeur, int hauteur, int pv) throws Exception;

	// Operators
	/**
	 * \pre : !estDetruite() 
	 * \pre : degats > 0  
	 * \post : pointsDeVie() = pointsDeVie()@pre - degats
	 */

	public void taper(int degats) throws Exception;

}
