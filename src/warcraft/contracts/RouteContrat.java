package warcraft.contracts;

import warcraft.decorators.RouteDecorator;
import warcraft.exceptions.PostConditionError;
import warcraft.exceptions.PreConditionError;
import warcraft.services.IRoute;

public class RouteContrat extends RouteDecorator{

  public RouteContrat(IRoute delegate) {
    super(delegate);
  }
  
  public void checkInvariants(){
    // Pas d'invariants
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
  public double multiplicateur() {
    return super.multiplicateur();
  }

  @Override
  public IRoute init(int largeur, int hauteur, double mult) {
    // Pre-Conditions
    // largeur > 0
    if(!(largeur > 0)){
      throw new PreConditionError("largeur > 0");
    }
    // hauteur > 0
    if(!(hauteur > 0)){
      throw new PreConditionError("hauteur > 0");
    }
    // mult >= 1.0
    if(!(mult >= 1.0)){
      throw new PreConditionError("mult >= 1.0");
    }

    // Pas d'invariants avant l'initialisation
    
    // Appel
    super.init(largeur, hauteur, mult);
    // Invariants
    checkInvariants();
    // Post-Conditions
    // largeur(init(largeur, hauteur, pv) = largeur
    if(!(largeur() == largeur)){
      throw new PostConditionError("largeur(init(largeur, hauteur, mult) = largeur");
    }
    // hauteur(init(largeur, hauteur, pv) = hauteur
    if(!(hauteur() == hauteur)){
      throw new PostConditionError("hauteur(init(largeur, hauteur, mult) = hauteur");
    }
    // pointsDeVie(init(largeur, hauteur, pv) = pv
    if(!(multiplicateur() == mult)){
      throw new PostConditionError("multiplicateur(init(largeur, hauteur, mult) = mult");
    }

    return this;
  }
  

}
