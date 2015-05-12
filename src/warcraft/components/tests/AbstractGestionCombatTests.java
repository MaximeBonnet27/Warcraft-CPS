package warcraft.components.tests;

import org.junit.After;
import org.junit.Before;

import warcraft.services.IGestionCombatService;
import warcraft.services.IHotelDeVilleService;
import warcraft.services.IVillageoisService;

public abstract class AbstractGestionCombatTests extends AssertionTests {
	protected IGestionCombatService combat;

	@Before
	public abstract void before();

	@After
	public void after() {
		combat = null;
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_0: init: positif
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(10,20,200,50)
	 * 
	 * Oracle:
	 * Pas d'exception &&
	 *  0 <= tempsCourant() &&
	 *  tempsCourant() <= tempsDeConstruction() &&
	 *  enConstruction() == (tempsCourant() > 0) &&
	 *  constructionFinie() == (tempsCourant() == tempsDeConstruction()) &&
	 *  0 < rechercheCourante() &&
	 *  rechercheCourante() <= tempsDeRecherche() &&
	 *   enRecherche() == (rechercheCourante() > 0)  &&
	 *   rechercheFinie() == (rechercheCourante() == tempsDeRecherche()) &&
	 *   tempsDeConstruction() == 10 &&
	 *    tempsDeRecherche() == 20 &&
	 *    prixConstruction() ==200 &&
	 *    prixRecherche() ==50 &&
	 *    tempsCourant() == 0 &&
	 *    rechercheCourante() == 0
	 *  
	 */
	
	@Test
	public void testInit1_0(){
		String obj="GestionCombat Test Objectif 1.0";
		IVillageoisService attaquant = new Villageois
		combat.init(attaquant, defenseur);

	}
}
