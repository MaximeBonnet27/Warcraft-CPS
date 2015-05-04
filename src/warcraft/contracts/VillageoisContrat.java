package warcraft.contracts;

import warcraft.decorators.VillageoisDecorator;
import warcraft.enums.ERace;
import warcraft.exceptions.InvariantError;
import warcraft.exceptions.PostConditionError;
import warcraft.exceptions.PreConditionError;
import warcraft.services.IVillageois;

public class VillageoisContrat extends VillageoisDecorator{

  public VillageoisContrat(IVillageois delegate) {
    super(delegate);
  }

  public void checkInvariants(){
    // estMort(V) =(min)= pointsDeVie(V)≤0
    if(!(estMort() == (pointsDeVie() <= 0))){
      throw new InvariantError("estMort(V) =(min)= pointsDeVie(V)≤0");
    }
    // corveeFini(V) =(min)= (compteurCorvee(V)=16)
    if(!(corveeFinie() == (compteurCorvee() == 16))){
      throw new InvariantError("corveeFini(V) =(min)= (compteurCorvee(V)=16)");
    }
    // enCorvee(V) =(min)= (compteurCorvee>0)
    if(!(enCorvee() == (compteurCorvee() > 0))){
      throw new InvariantError("enCorvee(V) =(min)= (compteurCorvee>0)");
    }
    // enCorvee(V) =(min)= (compteurCorvee>0)
    if(!(enCorvee() == (compteurCorvee() > 0))){
      throw new InvariantError("enCorvee(V) =(min)= (compteurCorvee>0)");
    }
    // quantiteOr(V)≥0
    if(!(quantiteOr() >= 0)){
      throw new InvariantError("quantiteOr(V)≥0");
    }
    // 0≤compteurCorvee(V)≤16
    if(!(0 <= compteurCorvee() && compteurCorvee() >= 16)){
      throw new InvariantError("0≤compteurCorvee(V)≤16");
    }
  }

  @Override
  public ERace race() {
    return super.race();
  }

  @Override
  public int largeur() {
    return super.largeur();
  }

  @Override
  public int hauteur() {
    return super.hauteur();
  }

  @Override
  public int force() {
    return super.force();
  }

  @Override
  public double vitesse() {
    return super.vitesse();
  }

  @Override
  public int pointsDeVie() {
    return super.pointsDeVie();
  }

  @Override
  public boolean estMort() {
    return super.estMort();
  }

  @Override
  public int quantiteOr() {
    return super.quantiteOr();
  }

  @Override
  public int compteurCorvee() {
    return super.compteurCorvee();
  }

  @Override
  public boolean corveeFinie() {
    return super.corveeFinie();
  }

  @Override
  public boolean enCorvee() {
    return super.enCorvee();
  }

  @Override
  public IVillageois init(ERace race, int largeur, int hauteur, int force,
      double vitesse, int pointsDeVie) {
    // Pre-Conditions
    // largeur>0 
    if(!(largeur > 0)){
      throw new PreConditionError("largeur>0");
    }
    // hauteur>0 
    if(!(hauteur > 0)){
      throw new PreConditionError("hauteur>0");
    }
    // force>0 
    if(!(force > 0)){
      throw new PreConditionError("force>0");
    }
    // vitesse>0 
    if(!(vitesse > 0)){
      throw new PreConditionError("vitesse>0");
    }
    // pointsDeVie>0 
    if(!(pointsDeVie > 0)){
      throw new PreConditionError("pointsDeVie>0");
    }
    
    // Pas d'invariants avant initialisation
    
    // Appel
    super.init(race, largeur, hauteur, force, vitesse, pointsDeVie);
    
    // Invariants
    checkInvariants();
    // Post-Conditions
    // race(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = race
    if(!(race() == race)){
      throw new PostConditionError("race(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = race");
    }
    // largeur(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = largeur
    if(!(largeur() == largeur)){
      throw new PostConditionError("largeur(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = largeur");
    }
    // hauteur(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = hauteur
    if(!(hauteur() == hauteur)){
      throw new PostConditionError("hauteur(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = hauteur");
    }
    // force(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = force
    if(!(force() == force)){
      throw new PostConditionError("force(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = force");
    }
    // vitesse(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = vitesse
    if(!(vitesse() == vitesse)){
      throw new PostConditionError("vitesse(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = vitesse");
    }
    // pointsDeVie(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = pointsDeVie
    if(!(pointsDeVie() == pointsDeVie)){
      throw new PostConditionError("pointsDeVie(init(race,largeur,hauteur,force,vitesse,pointsDeVie)) = pointsDeVie");
    }
    return this;
  }

  @Override
  public IVillageois retraitPV(int degats) {
    // Capture
    int oldPointsDeVie = pointsDeVie();
    int oldQuantiteOr = quantiteOr();
    int oldCompteurCorvee = compteurCorvee();
    
    // Pré-Conditions
    // ¬estMort(V)
    if(!(!estMort())){
      throw new PreConditionError("¬estMort(V)");
    }
    // degats>0
    if(!(degats > 0)){
      throw new PreConditionError("degats > 0");
    }
    
    // Invariants
    checkInvariants();
    
    // Appel
    super.retraitPV(degats);
    
    // Invariants
    checkInvariants();
    
    // Post-Conditions
    // pointsDeVie(retraitPV(V,s)) = pointsDeVie(V) - s 
    if(!(pointsDeVie() == oldPointsDeVie - degats)){
      throw new PostConditionError("pointsDeVie(retraitPV(V,s)) = pointsDeVie(V) - s");
    }
    // quantiteOr(retraitPV(V,s)) = quantiteOr(V) 
    if(!(quantiteOr() == oldQuantiteOr)){
      throw new PostConditionError("quantiteOr(retraitPV(V,s)) = quantiteOr(V)");
    }
    // compteurCorvee(retraitPV(V,s)) = compteurCorvee(V)
    if(!(compteurCorvee() == oldCompteurCorvee)){
      throw new PostConditionError("compteurCorvee(retraitPV(V,s)) = compteurCorvee(V)");
    }
    return this;
  }

  @Override
  public IVillageois ajouterOr(int somme) {
    // Pré-Conditions
    
    // Invariants
    
    // Appel
    super.ajouterOr(somme);
    
    // Invariants
    
    // Post-Conditions

    return this;
  }

  @Override
  public IVillageois retraitOr(int somme) {
    // Pré-Conditions
    
    // Invariants
    
    // Appel
    super.retraitOr(somme);
    
    // Invariants
    
    // Post-Conditions

    return this;
  }

  @Override
  public IVillageois commenceTravaille() {
    // Pré-Conditions
    
    // Invariants
    
    // Appel
    super.commenceTravaille();
    
    // Invariants
    
    // Post-Conditions

    return this;
  }

  @Override
  public IVillageois travaille() {
    // Pré-Conditions
    
    // Invariants
    
    // Appel
    super.travaille();
    
    // Invariants
    
    // Post-Conditions

    return this;
  }

}
