package warcraft.implem;

import warcraft.services.IMuraille;

public class Muraille implements IMuraille{

  private int largeur, hauteur;
  private int pointsDeVie;
  
  @Override
  public int largeur() {
    return largeur;
  }

  @Override
  public int hauteur() {
    return hauteur;
  }

  @Override
  public int pointsDeVie() {
    return pointsDeVie;
  }

  @Override
  public boolean estDetruite() {
    return pointsDeVie <= 0;
  }

  @Override
  public IMuraille init(int largeur, int hauteur, int pv) {

    Muraille m = new Muraille();
    m.largeur = largeur;
    m.hauteur = hauteur;
    m.pointsDeVie = pv;
    return m;
  
  }

  @Override
  public IMuraille taper(int degats) {
    pointsDeVie -= degats;
    return this;
  }

}
