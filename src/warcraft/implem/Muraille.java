package warcraft.implem;

import warcraft.services.IMurailleService;

public class Muraille implements IMurailleService{

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
  public IMurailleService init(int largeur, int hauteur, int pv) {

    Muraille m = new Muraille();
    m.largeur = largeur;
    m.hauteur = hauteur;
    m.pointsDeVie = pv;
    return m;
  
  }

  @Override
  public IMurailleService taper(int degats) {
    pointsDeVie -= degats;
    return this;
  }

}
