/**
 * 
 */
package warcraft.services;

import warcraft.enums.ECompetence;

/**
 * @author 3100381
 *
 */
public interface ICentreNationalRechercheSpecialeService {

  // Observators

  public int prixConstruction();
  public int prixRecherche();
  public int tempsDeConstruction();
  public int tempsDeRecherche();
  public int tempsCourant();
  public boolean enConstruction();
  public boolean constructionFinie();
  public int rechercheCourante();
  public boolean enRecherche();
  public boolean rechercheFinie();
  /**
   * \pre rechercheFinie(); 
   */
  public ECompetence competenceAugmente();
  /**
   * \pre rechercheFinie(); 
   */
  public int boost();

  // Invariants

  /**
   * \inv : 0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()
   * \inv : enConstruction() == (tempsCourant() > 0)
   * \inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())
   * \inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()
   * \inv : enRecherche() == (rechercheCourante() > 0)
   * \inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())
   * \inv : competenceAugmentee() == ECompetence.random()
   * \inv : boost() == Integer.random(10)
   */

  // Constructors
  /**
   * \pre : tempsDeConstruction > 0
   * \pre : tempsDeRecherche > 0
   * \pre : prixConstruction > 0
   * \pre : prixRecherche > 0
   * 
   * \post : tempsDeConstruction() == tempsDeConstruction
   * \post : tempsDeRecherche() == tempsDeRecherche
   * \post : prixConstruction() == prixConstruction
   * \post : prixRecherche() == prixRecherche
   * \post : tempsCourant() == 0
   * \post : rechercheCourante() == 0
   */
  public void init(int tempsDeConstruction, int tempsDeRecherche, int prixConstruction, int prixRecherche);

  // Operators

  /**
   * \pre : !enConstruction()
   * 
   * \post : tempsCourant() == 1
   * \post : rechercheCourante() == rechercheCourante()@pre
   */
  public void commencerConstruction();

  /**
   * \pre : !constructionFinie()
   * 
   * \post : tempsCourant() == tempsCourant()@pre + 1
   * \post : rechercheCourante() == rechercheCourante()@pre
   */
  public void construire();

  /**
   * \pre : constructionFinie()
   * 
   * \post : tempsCourant() == tempsCourant()@pre
   * \post : rechercheCourante() == 1
   */
  public void commencerRecherche();

  /**
   * \pre : !rechercheFinie()
   * 
   * \post : rechercheCourante() == rechercheCourante()@pre + 1
   * \post : tempsCourant() == tempsCourant()@pre
   */
  public void recherche();
  
  /**
   * \pre : enRecherche()
   * 
   * rechercheCourante() == 0
   * \post : tempsCourant() == tempsCourant()@pre
   */
   public void finirRecherche();

}
