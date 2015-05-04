package warcraft.decorators;

import warcraft.enums.ERace;
import warcraft.services.IVillageois;

public class VillageoisDecorator implements IVillageois{

  private IVillageois delegate;

  public VillageoisDecorator(IVillageois delegate) {
    super();
    this.delegate = delegate;
  }

  public ERace race() {
    return delegate.race();
  }

  public int largeur() {
    return delegate.largeur();
  }

  public int hauteur() {
    return delegate.hauteur();
  }

  public int force() {
    return delegate.force();
  }

  public double vitesse() {
    return delegate.vitesse();
  }

  public int pointsDeVie() {
    return delegate.pointsDeVie();
  }

  public boolean estMort() {
    return delegate.estMort();
  }

  public int quantiteOr() {
    return delegate.quantiteOr();
  }

  public int compteurCorvee() {
    return delegate.compteurCorvee();
  }

  public boolean corveeFinie() {
    return delegate.corveeFinie();
  }

  public boolean enCorvee() {
    return delegate.enCorvee();
  }

  public IVillageois init(ERace race, int largeur, int hauteur, int force,
      double vitesse, int pointsDeVie) {
    return delegate.init(race, largeur, hauteur, force, vitesse, pointsDeVie);
  }

  public IVillageois retraitPV(int degats) {
    return delegate.retraitPV(degats);
  }

  public IVillageois ajouterOr(int somme) {
    return delegate.ajouterOr(somme);
  }

  public IVillageois retraitOr(int somme) {
    return delegate.retraitOr(somme);
  }

  public IVillageois commenceTravaille() {
    return delegate.commenceTravaille();
  }

  public IVillageois travaille() {
    return delegate.travaille();
  }
  
  
  
}
