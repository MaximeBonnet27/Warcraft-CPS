package warcraft.services;

public interface IRoute {

  // Observators

  public int largeur();

  public int hauteur();

  public double multiplicateur();

  // Constructors

  /**
   * \pre : init(largeur, hauteur, mult) require largeur > 0 ^ hauteur > 0 ^ mult
   * >= 1.0
   * 
   * \post : largeur() = largeur
   * hauteur() = hauteur
   * multiplicateur() = mult
   * 
   */

  public IRoute init(int largeur, int hauteur, double mult);

  // Operators

}
