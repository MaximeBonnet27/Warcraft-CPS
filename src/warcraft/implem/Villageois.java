package warcraft.implem;

import warcraft.enums.ERace;
import warcraft.services.IVillageois;

public class Villageois implements IVillageois{

  private ERace race;
  private int largeur, hauteur;
  private int force;
  private double vitesse;
  private int pointsDeVie;
  private int quantiteOr;
  private int compteurCorvee;
  
  @Override
  public ERace race() {
    return race;
  }

  @Override
  public int largeur() {
    return largeur;
  }

  @Override
  public int hauteur() {
    return hauteur;
  }

  @Override
  public int force() {
    return force;
  }

  @Override
  public double vitesse() {
    return vitesse;
  }

  @Override
  public int pointsDeVie() {
    return pointsDeVie;
  }

  @Override
  public boolean estMort() {
    return pointsDeVie <= 0;
  }

  @Override
  public int quantiteOr() {
    return quantiteOr;
  }

  @Override
  public int compteurCorvee() {
    return compteurCorvee;
  }

  @Override
  public boolean corveeFinie() {
    return compteurCorvee == 16;
  }

  @Override
  public boolean enCorvee() {
    return compteurCorvee > 0;
  }

  @Override
  public IVillageois init(ERace race, int largeur, int hauteur, int force,
      double vitesse, int pointsDeVie) {
    Villageois v = new Villageois();
    v.race = race;
    v.largeur = largeur;
    v.hauteur = hauteur;
    v.force = force;
    v.vitesse = vitesse;
    v.pointsDeVie = pointsDeVie;
    return v;
  }

  @Override
  public IVillageois retraitPV(int degats) {
    pointsDeVie -= degats;
    return this;
  }

  @Override
  public IVillageois ajouterOr(int somme) {
    quantiteOr += somme;
    return this;
  }

  @Override
  public IVillageois retraitOr(int somme) {
    quantiteOr -= somme;
    return this;
  }

  @Override
  public IVillageois commenceTravaille() {
    compteurCorvee = 1;
    return this;
  }

  @Override
  public IVillageois travaille() {
    compteurCorvee += 1;
    return this;
  }

}
