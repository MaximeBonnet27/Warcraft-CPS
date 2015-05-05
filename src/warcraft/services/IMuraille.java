package warcraft.services;

public interface IMuraille {

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
	public IMuraille init(int largeur, int hauteur, int pv);

	// Operators
	/**
	 * \pre : !estDetruite() 
	 * \pre : degat > 0  
	 * \post : pointsDeVie() = pointsDeVie()@pre - x
	 */

	public IMuraille taper(int degats);

}
