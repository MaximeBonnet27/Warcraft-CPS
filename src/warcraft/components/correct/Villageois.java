package warcraft.components.correct;

import warcraft.enums.ECompetence;
import warcraft.enums.ERace;
import warcraft.services.IVillageoisService;

public class Villageois implements IVillageoisService{

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
  public void init(ERace race, int largeur, int hauteur, int force,
      double vitesse, int pointsDeVie) {
    this.race = race;
    this.largeur = largeur;
    this.hauteur = hauteur;
    this.force = force;
    this.vitesse = vitesse;
    this.pointsDeVie = pointsDeVie;
  }

  @Override
  public void retraitPV(int degats) {
    pointsDeVie -= degats;
  }

  @Override
  public void ajouterOr(int somme) {
    quantiteOr += somme;
  }

  @Override
  public void retraitOr(int somme) {
    quantiteOr -= somme;
  }

  @Override
  public void commenceTravaille() {
    compteurCorvee = 1;
  }

  @Override
  public void travaille() {
    compteurCorvee += 1;
  }

@Override
public void amelioration(ECompetence competence, int val) {
	switch (competence) {
	case FORCE : force += val; break;
	case PV : pointsDeVie += val; break;
	case VITESSE : vitesse += val; break;
	default:
		break;
	}
}

}
