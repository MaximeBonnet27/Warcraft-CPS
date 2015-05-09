package warcraft.components.correct;

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
  public void init(int largeur, int hauteur, double mult) {
    this.largeur = largeur;
    this.hauteur = hauteur;
    this.multiplicateur = mult;
  }

}