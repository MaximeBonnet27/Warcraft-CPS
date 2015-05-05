package warcraft.implem;

import warcraft.services.IRouteService;

public class Route implements IRouteService{

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
  public IRouteService init(int largeur, int hauteur, double mult) {
    Route r = new Route();
    r.largeur = largeur;
    r.hauteur = hauteur;
    r.multiplicateur = mult;
    return r;
  }

}
