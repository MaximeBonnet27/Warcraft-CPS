package warcraft.contracts;

import warcraft.decorators.MurailleDecorator;
import warcraft.exceptions.InvariantError;
import warcraft.exceptions.PostConditionError;
import warcraft.exceptions.PreConditionError;
import warcraft.services.IMuraille;

public class MurailleContrat extends MurailleDecorator{

  public MurailleContrat(IMuraille delegate) {
    super(delegate);
  }

  public void checkInvariants(){
    //  estDetruite(M) =(min)= (pointsDeVie(M) ≤ 0)
    if(!(estDetruite() == (pointsDeVie() <= 0))){
      throw new InvariantError("estDetruite(M) =(min)= (pointsDeVie ≤ 0)");
    }

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
  public int pointsDeVie() {
    return super.pointsDeVie();
  }

  @Override
  public boolean estDetruite() {
    return super.estDetruite();
  }

  @Override
  public IMuraille init(int largeur, int hauteur, int pv) {
    // Pre-Conditions
    // largeur > 0
    if(!(largeur > 0)){
      throw new PreConditionError("largeur > 0");
    }
    // hauteur > 0
    if(!(hauteur > 0)){
      throw new PreConditionError("hauteur > 0");
    }
    // pv > 0
    if(!(pv > 0)){
      throw new PreConditionError("pv > 0");
    }
    // Pas d'invariants avant l'initialisation
    
    // Appel
    super.init(largeur, hauteur, pv);

    // Invariants
    this.checkInvariants();
    // Post-Conditions
    // largeur(init(largeur, hauteur, pv) = largeur
    if(!(largeur() == largeur)){
      throw new PostConditionError("largeur(init(largeur, hauteur, pv) = largeur");
    }
    // hauteur(init(largeur, hauteur, pv) = hauteur
    if(!(hauteur() == hauteur)){
      throw new PostConditionError("hauteur(init(largeur, hauteur, pv) = hauteur");
    }
    // pointsDeVie(init(largeur, hauteur, pv) = pv
    if(!(pointsDeVie() == pv)){
      throw new PostConditionError("pointsDeVie(init(largeur, hauteur, pv) = pv");
    }
    return this;
  }

  @Override
  public IMuraille taper(int degats) {
    // Capture
    int oldPointsDeVie = pointsDeVie();
    
    // Pre-Conditions
    // ¬estDetruite() 
    if(!(!estDetruite())){
      throw new PreConditionError("¬estDetruite()");
    }
    // degats > 0
    if(!(degats > 0)){
      throw new PreConditionError("degats > 0");
    }
    // Invariants
    checkInvariants();
    
    //Appel
    super.taper(degats);
    
    // Invariants
    checkInvariants();
    
    // Post-Conditions 
    // pointsDeVie(taper(x)) = pointsDeVie() - x
    if(!(pointsDeVie() == oldPointsDeVie - degats)){
      throw new PostConditionError("pointsDeVie(taper(x)) = pointsDeVie() - x");
    }
    return this;
  }



}
