package warcraft.implem;

import warcraft.services.IRoute;

public class Route implements IRoute{

  private int largeur, hauteur;
  private double multiplicateur;
  
  @Override
  public int largeur() {
    return largeur;
  }

  @Override
  public int hauteur() {
    return hauteur;
  }

  @Override
  public double multiplicateur() {
    return multiplicateur;
  }

  @Override
  public IRoute init(int largeur, int hauteur, double mult) {
    Route r = new Route();
    r.largeur = largeur;
    r.hauteur = hauteur;
    r.multiplicateur = mult;
    return r;
  }

}
