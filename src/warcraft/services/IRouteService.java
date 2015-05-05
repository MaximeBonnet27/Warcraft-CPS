package warcraft.services;

public interface IRouteService {

  // Observators

  public int largeur();

  public int hauteur();

  public double multiplicateur();

  // Constructors

  /**
   * \pre : largeur > 0
   * \pre :  hauteur > 0 
   * \pre : mult >= 1.0
   * 
   * \post : largeur() == largeur
   * \post : hauteur() == hauteur
   * \post : multiplicateur() == mult
   * 
   */

  public void init(int largeur, int hauteur, double mult);

  // Operators

}
