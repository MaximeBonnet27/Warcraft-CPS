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
   * \pre : init(race,largeur,hauteur,force,vitesse,pointsDeVie) require
   * largeur>0 ^ hauteur>0 ^ force>0 ^ vitesse>0 ^ pointsDeVie>0
   * 
   * \post : race() = r largeur() = l
   * hauteur() = h force() = f
   * vitesse() = v pointsDeVie() = p
   * quantiteOr() = 0 compteurCorvee() = 0
   */
  public IVillageois init(ERace race, int largeur, int hauteur, int force,
      double vitesse, int pointsDeVie);

  // Operators
  /**
   * \pre : retraitPV(V, degats) require ¬estMort(V) ^ s>0
   * 
   * \post: pointsDeVie() = pointsDeVie()@pre - s
   * quantiteOr() = quantiteOr()@pre 
   * compteurCorvee() = compteurCorvee()@pre
   */
  public IVillageois retraitPV(int degats);

  /**
   * \pre: ajouteOr(n) require n≥0
   * 
   * \post : 
   * pointsDeVie() = pointsDeVie()@pre
   * quantiteOr() = quantiteOr()@pre + n 
   * compteurCorvee() = compteurCorvee()@pre
   */
  public IVillageois ajouterOr(int somme);
  
  /**
   * \pre: retraitOr(s) require quantiteOr()-s≥0 ^ s≥0
   * 
   * \post :
   * pointsDeVie() = pointsDeVie()@pre
   * quantiteOr() = quantiteOr()@pre - s 
   * compteurCorvee() = compteurCorvee()@pre
   */
  
  public IVillageois retraitOr(int somme);
  
  /**
   * \post: 
   * pointsDeVie() = pointsDeVie()@pre
   * quantiteOr() = quantiteOr()@pre
   * compteurCorvee() = 1
   */
  
  public IVillageois commenceTravaille();
  
  /**
   * \pre : travaille() require ¬corveeFini()
   * 
   * \post : pointsDeVie() = pointsDeVie(V)@pre 
   * quantiteOr() = quantiteOr(V)@pre 
   * compteurCorvee() = compteurCorvee(V)@pre + 1
   */
  
  public IVillageois travaille();
  
}
