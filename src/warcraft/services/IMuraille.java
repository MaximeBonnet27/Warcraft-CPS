package warcraft.services;

public interface IMuraille {

  // Observators

  public int largeur();
  public int hauteur();
  public int pointsDeVie();
  public boolean estDetruite();

  // Constructors
  /**
   * \pre : 
   * init(largeur, hauteur, pv)
   * require largeur > 0 ^ hauteur > 0 ^ pv > 0
   * 
   * \post :
   * largeur() = largeur
   * hauteur() = hauteur
   * pointsDeVie() = pv
   */
  public IMuraille init(int largeur, int hauteur, int pv);

  // Operators
  /**
   * \pre : 
   * taper(degat) 
   * require ¬estDetruite() ^ degat > 0  
   * 
   * \post : 
   * pointsDeVie() = pointsDeVie()@pre - x
   */

  public IMuraille taper(int degats);

}
