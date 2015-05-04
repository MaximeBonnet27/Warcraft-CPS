package warcraft.services;

import warcraft.enums.ERace;

public interface IVillageois {

  // Observators

  public ERace race();

  public int largeur();

  public int hauteur();

  public int force();

  public double vitesse();

  public int pointsDeVie();

  public boolean estMort();

  public int quantiteOr();

  public int compteurCorvee();

  public boolean corveeFinie();

  public boolean enCorvee();

  // Constructors

  /**
   * pre : init(race,largeur,hauteur,force,vitesse,pointsDeVie) require
   * largeur>0 ^ hauteur>0 ^ force>0 ^ vitesse>0 ^ pointsDeVie>0
   * 
   * post : race(init(r,l,h,f,v,p)) = r largeur(init(r,l,h,f,v,p)) = l
   * hauteur(init(r,l,h,f,v,p)) = h force(init(r,l,h,f,v,p)) = f
   * vitesse(init(r,l,h,f,v,p)) = v pointsDeVie(init(r,l,h,f,v,p)) = p
   * quantiteOr(init(r,l,h,f,v,p)) = 0 compteurCorvee(init(r,l,h,f,v,p)) = 0
   */
  public IVillageois init(ERace race, int largeur, int hauteur, int force,
      double vitesse, int pointsDeVie);

  // Operators
  /**
   * pre : retraitPV(V, degats) require ¬estMort(V) ^ s>0
   * 
   * post: pointsDeVie(retraitPV(V,s)) = pointsDeVie(V) - s
   * quantiteOr(retraitPV(V,s)) = quantiteOr(V) 
   * compteurCorvee(retraitPV(V,s)) = compteurCorvee(V)
   */
  public IVillageois retraitPV(int degats);

  /**
   * pre: ajouteOr(n) require n≥0
   * 
   * post : 
   * pointsDeVie(ajouteOr(n)) = pointsDeVie()
   * quantiteOr(ajouteOr(n)) = quantiteOr() + n 
   * compteurCorvee(ajouteOr(n)) = compteurCorvee()
   */
  public IVillageois ajouterOr(int somme);
  
  /**
   * pre: retraitOr(s) require quantiteOr()-s≥0 ^ s≥0
   * 
   * post :
   * pointsDeVie(retraitOr(s)) = pointsDeVie()
   * quantiteOr(retraitOr(s)) = quantiteOr() - s 
   * compteurCorvee(retraitOr(s)) = compteurCorvee()
   */
  
  public IVillageois retraitOr(int somme);
  
  /**
   * post: 
   * pointsDeVie(commenceTravaille()) = pointsDeVie()
   * quantiteOr(commenceTravaille()) = quantiteOr()
   * compteurCorvee(commenceTravaille()) = (compteurCorvee()=1)
   */
  
  public IVillageois commenceTravaille();
  
  /**
   * pre : travaille() require ¬corveeFini()
   * 
   * post : pointsDeVie(travaille(V)) = pointsDeVie(V) 
   * quantiteOr(travaille(V)) = quantiteOr(V) 
   * compteurCorvee(travaille(V)) = compteurCorvee(V) + 1
   */
  
  public IVillageois travaille();
  
}
