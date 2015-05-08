package warcraft.components.correct;

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
  public void init(int largeur, int hauteur, int pv) {

    this.largeur = largeur;
    this.hauteur = hauteur;
    this.pointsDeVie = pv;
  
  }

  @Override
  public void taper(int degats) {
    pointsDeVie -= degats;
  }

}
