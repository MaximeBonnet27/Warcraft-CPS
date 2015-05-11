package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warcraft.services.ICentreNationalRechercheSpecialeService;

public abstract class AbstractCentreNationalRechercheSpecialeTests extends AssertionTests {

	protected ICentreNationalRechercheSpecialeService cnrs;

	@Before
	public abstract void before();

	@After
	public void after() {
		cnrs = null;
	}

	public void checkInvariants(String obj){
		// \inv :  0 < tempsCourant() && tempsCourant() <= tempsDeConstruction()
		assertion(obj+"\\inv :  0 <= tempsCourant() && tempsCourant() <= tempsDeConstruction()",  0 <= cnrs.tempsCourant() && cnrs.tempsCourant() <= cnrs.tempsDeConstruction());
		// \inv : enConstruction() == (tempsCourant() > 0)
		assertion(obj+"\\inv : enConstruction() == (tempsCourant() > 0)", cnrs.enConstruction() == (cnrs.tempsCourant() > 0));
		// \inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())
		assertion(obj+"\\inv : constructionFinie() == (tempsCourant() == tempsDeConstruction())", cnrs.constructionFinie() == (cnrs.tempsCourant() == cnrs.tempsDeConstruction()));
		// \inv : 0 < rechercheCourante() && rechercheCourante() <= tempsDeRecherche()
		assertion(obj+"\\inv : 0 <= rechercheCourante() && rechercheCourante() <= tempsDeRecherche()", 0 <= cnrs.rechercheCourante() && cnrs.rechercheCourante() <= cnrs.tempsDeRecherche());
		// \inv : enRecherche() == (rechercheCourante() > 0)
		assertion(obj+"\\inv : enRecherche() == (rechercheCourante() > 0)", cnrs.enRecherche() == (cnrs.rechercheCourante() > 0));
		// \inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())
		assertion(obj+"\\inv : rechercheFinie() == (rechercheCourante() == tempsDeRecherche())", cnrs.rechercheFinie() == (cnrs.rechercheCourante() == cnrs.tempsDeRecherche()));
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
		String obj="CNRS Test Objectif 1.0";
		int tempsDeConstruction=10;
		int tempsDeRecherche=20;
		int prixConstruction=200;
		int prixRecherche=50;

		//Condition initial

		try {
			//Operation
			cnrs.init(tempsDeConstruction, tempsDeRecherche, prixConstruction, prixRecherche);

			//Oracle
			checkInvariants(obj);

			// \post : tempsDeConstruction() == tempsDeConstruction
			assertion(obj+": \\post : tempsDeConstruction() == tempsDeConstruction", cnrs.tempsDeConstruction() == tempsDeConstruction);
			// \post : tempsDeRecherche() == tempsDeRecherche
			assertion(obj+": \\post : tempsDeRecherche() == tempsDeRecherche",cnrs.tempsDeRecherche() == tempsDeRecherche);
			// \post : prixConstruction() == prixConstruction
			assertion(obj+": \\post : prixConstruction() == prixConstruction", cnrs.prixConstruction() == prixConstruction);
			// \post : prixRecherche() == prixRecherche
			assertion(obj+": \\post : prixRecherche() == prixRecherche", cnrs.prixRecherche() == prixRecherche);
			// \post : tempsCourant() == 0
			assertion(obj+": \\post : tempsCourant() == 0", cnrs.tempsCourant() == 0);
			// \post : rechercheCourante() == 0
			assertion(obj+": \\post : rechercheCourante() == 0", cnrs.rechercheCourante() == 0);

		} catch (Exception e) {
			assertion(obj+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_1: init: error tempsConstruction<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(-10,20,200,50)
	 * 
	 * Oracle:
	 * Exception pour init
	 * 
	 */
	@Test
	public void testInit1_1(){
		String obj="CNRS Test Objectif 1.1";
		//Condition initial

		try {
			//Operation
			cnrs.init(-10, 20, 200, 50);

			//Oracle
			assertion(obj+": init devrait échouer",false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error tempsDeRecherche<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(10,-20,200,50)
	 * 
	 * Oracle:
	 * Exception pour init
	 * 
	 */
	@Test	public void testInit1_2(){
		String obj="CNRS Test Objectif 1.2";

		//Condition initial

		try {
			//Operation
			cnrs.init(10, -20, 200, 50);

			//Oracle
			assertion(obj+": init devrait échouer",false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_3: init: error prixConstruction<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(10,20,-200,50)
	 * 
	 * Oracle:
	 * Exception pour init
	 * 
	 */
	@Test
	public void testInit1_3(){
		String obj="CNRS Test Objectif 1.3";
		//Condition initial

		try {
			//Operation
			cnrs.init(10, 20, -200, 50);

			//Oracle
			assertion(obj+": init devrait échouer",false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_4: init: error prixRecherche<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(10,20,200,-50)
	 * 
	 * Oracle:
	 * Exception pour init
	 * 
	 */
	@Test
	public void testInit1_4(){
		String obj="CNRS Test Objectif 1.4";

		//Condition initial

		try {
			//Operation
			cnrs.init(10, 20, 200, -50);

			//Oracle
			assertion(obj+": init devrait échouer",false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 2: commencerConstruction
	 * 
	 * Cas 2_0: commencerConstruction positif
	 * 
	 * Condition initial:
	 * init(10,20,200,50)
	 * 
	 * Operation:
	 * commencerConstruction()
	 * 
	 * Oracle:
	 * 0 <= tempsCourant() &&
	 *  tempsCourant() <= tempsDeConstruction() &&
	 *  enConstruction() == (tempsCourant() > 0) &&
	 *  constructionFinie() == (tempsCourant() == tempsDeConstruction()) &&
	 *  0 < rechercheCourante() &&
	 *  rechercheCourante() <= tempsDeRecherche() &&
	 *   enRecherche() == (rechercheCourante() > 0)  &&
	 *   rechercheFinie() == (rechercheCourante() == tempsDeRecherche()) &&
	 *   tempsCourant() == 1 &&
	 *   rechercheCourante() == rechercheCourante()@pre
	 * 
	 */
	@Test
	public void testCommencerConstruction2_0(){
		String obj="CNRS Test Objectif 2.0";

		try {
			//Condition initial
			cnrs.init(10,20,200,50);

			//Capture
			int oldRechercheCourante=cnrs.rechercheCourante();

			try{
				//Operation
				cnrs.commencerConstruction();

				//Oracle
				checkInvariants(obj);

				// \post : tempsCourant() == 1
				assertion(obj+": \\post : tempsCourant() == 1", cnrs.tempsCourant() == 1);

				// \post : rechercheCourante() == rechercheCourante()@pre
				assertion(obj+": \\post : rechercheCourante() == rechercheCourante()@pre", cnrs.rechercheCourante()==oldRechercheCourante);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}

	}

	/**
	 * Objectif 2: commencerConstruction
	 * 
	 * Cas 2_1: commencerConstruction: error déja enConstruction
	 * 
	 * Condition initial:
	 * commencerConstruction(init(10,20,200,50))
	 * 
	 * Operation:
	 * commencerConstruction()
	 * 
	 * Oracle:
	 * Exception commencerConstuction
	 */
	@Test
	public void testCommencerConstruction2_1(){
		String obj="CNRS Test Objectif 2.1";


		try {
			//Condition initial
			cnrs.init(10,20,200,50);
			cnrs.commencerConstruction();

			//Operation
			try{
				cnrs.commencerConstruction();
				assertion(obj+": commencerConstruction devrait echouer",false);
			}catch(Exception e){
				assertion(obj,true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: construire
	 * 
	 * Cas 3_0: construire positif
	 * 
	 * Condition initial:
	 * commencerConstruction(init(10,20,200,50))
	 * 
	 * Operation:
	 * construire()
	 * 
	 * Oracle:
	 *  0 <= tempsCourant() &&
	 *  tempsCourant() <= tempsDeConstruction() &&
	 *  enConstruction() == (tempsCourant() > 0) &&
	 *  constructionFinie() == (tempsCourant() == tempsDeConstruction()) &&
	 *  0 < rechercheCourante() &&
	 *  rechercheCourante() <= tempsDeRecherche() &&
	 *   enRecherche() == (rechercheCourante() > 0)  &&
	 *   rechercheFinie() == (rechercheCourante() == tempsDeRecherche()) &&
	 *   tempsCourant() == tempsCourant()@pre + 1 &&
	 *   rechercheCourante() == rechercheCourante()@pre
	 * 
	 */
	@Test
	public void testConstruire3_0(){
		String obj="CNRS Test Objectif 3.0";

		try {
			//Condition initial
			cnrs.init(10,20,200,50);
			cnrs.commencerConstruction();

			//Capture
			int oldRechercheCourante=cnrs.rechercheCourante();
			int oldTempsCourant=cnrs.tempsCourant();

			//Operation
			try{
				cnrs.construire();

				//Oracle
				checkInvariants(obj);
				// \post : tempsCourant() == tempsCourant()@pre + 1
				assertion(obj+": \\post : tempsCourant() == tempsCourant()@pre + 1", cnrs.tempsCourant() == oldTempsCourant + 1);

				// \post : rechercheCourante() == rechercheCourante()@pre
				assertion(obj+": \\post : rechercheCourante() == rechercheCourante()@pre", cnrs.rechercheCourante()==oldRechercheCourante);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(),false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: construire
	 * 
	 * Cas 3_1: construire: error construction deja fini
	 * 
	 * Condition initial:
	 * constuire(commencerConstruction(init(2,20,200,50)))
	 * 
	 * Operation:
	 * constuire()
	 * 
	 * Oracle:
	 * Exception constuire
	 */
	@Test
	public void testConstruire3_1(){
		String obj="CNRS Test Objectif 3.1";

		try {
			//Condition initial
			cnrs.init(2,20,200,50);
			cnrs.commencerConstruction();
			cnrs.construire();

			//Operation
			try{
				cnrs.construire();

				//Oracle
				assertion(obj+": construire devrait échouer", false);
			}catch(Exception e){
				assertion(obj,true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: construire
	 * 
	 * Cas 3_2: construire: error construction pas commencer
	 * 
	 * Condition initial:
	 * init(10,20,200,50)
	 * 
	 * Operation:
	 * constuire()
	 * 
	 * Oracle:
	 * Exception constuire
	 */
	@Test
	public void testConstruire3_2(){
		String obj="CNRS Test Objectif 3.2";

		try {
			//Condition initial
			cnrs.init(10,20,200,50);

			//Operation
			try{
				cnrs.construire();

				//Oracle
				assertion(obj+": construire devrait échouer", false);
			}catch(Exception e){
				assertion(obj,true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 4: commencerRecherche
	 * 
	 * Cas 4_0: commencerRecherche positif
	 * 
	 * Condition initial:
	 * construire(commencerConstruction(init(2,20,200,50)))
	 * 
	 * Operation:
	 * commencerRecherche()
	 * 
	 * Oracle:
	 *  Pas d'exception &&
	 *  0 < tempsCourant() &&
	 *  tempsCourant() <= tempsDeConstruction() &&
	 *  enConstruction() == (tempsCourant() > 0) &&
	 *  constructionFinie() == (tempsCourant() == tempsDeConstruction()) &&
	 *  0 < rechercheCourante() &&
	 *  rechercheCourante() <= tempsDeRecherche() &&
	 *  enRecherche() == (rechercheCourante() > 0)  &&
	 *  rechercheFinie() == (rechercheCourante() == tempsDeRecherche()) &&
	 *  tempsCourant() == tempsCourant()@pre &&
	 *  rechercheCourante() == 1
	 * 
	 */
	@Test
	public void testCommencerRecherche4_0(){
		String obj="CNRS Test Objectif 4.0";

		try {
			//Condition initial
			cnrs.init(2, 20, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();

			//Capture
			int oldTempsCourant=cnrs.tempsCourant();

			try{
				//Operation
				cnrs.commencerRecherche();

				//Oracle
				checkInvariants(obj);
				// \post : tempsCourant() == tempsCourant()@pre
				assertion(obj+": \\post : tempsCourant() == tempsCourant()@pre", cnrs.tempsCourant() == oldTempsCourant);
				// \post : rechercheCourante() == 1
				assertion(obj+": \\post : rechercheCourante() == 1", cnrs.rechercheCourante() == 1);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}

	}

	/**
	 * Objectif 4: commencerRecherche
	 * 
	 * Cas 4_1: commencerRecherche: error construction pas fini
	 * 
	 * Condition initial:
	 * commencerConstruction(init(2,20,200,50))
	 * 
	 * Operation:
	 * commencerRecherche()
	 * 
	 * Oracle:
	 *  Exception pour commencerRecherche
	 * 
	 */
	@Test
	public void testCommencerRecherche4_1(){
		String obj="CNRS Test Objectif 4.1";

		try {
			//Condition initial
			cnrs.init(2, 20, 200, 50);
			cnrs.commencerConstruction();

			try{
				//Operation
				cnrs.commencerRecherche();

				//Oracle
				assertion(obj+": commencerRecherche devrait echouer", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}

	}

	/**
	 * Objectif 4: commencerRecherche
	 * 
	 * Cas 4_2: commencerRecherche: error recherche deja en cours
	 * 
	 * Condition initial:
	 * commencerRecherche(construire(commencerConstruction(init(2,20,200,50))))
	 * 
	 * Operation:
	 * commencerRecherche()
	 * 
	 * Oracle:
	 *  Exception pour commencerRecherche
	 * 
	 */
	@Test
	public void testCommencerRecherche4_2(){
		String obj="CNRS Test Objectif 4.2";

		try {
			//Condition initial
			cnrs.init(2, 20, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();
			cnrs.commencerRecherche();

			try{
				//Operation
				cnrs.commencerRecherche();

				//Oracle
				assertion(obj+": commencerRecherche devrait echouer", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}

	}

	/**
	 * Objectif 5: recherche
	 * 
	 * Cas 5_0: recherche positif
	 * 
	 * Condition initial:
	 * commencerRecherche(construire(commencerConstruction(init(2,20,200,50))))
	 * 
	 * Operation:
	 * rechercher()
	 * 
	 * Oracle:
	 *  Pas d'exception &&
	 *  0 < tempsCourant() &&
	 *  tempsCourant() <= tempsDeConstruction() &&
	 *  enConstruction() == (tempsCourant() > 0) &&
	 *  constructionFinie() == (tempsCourant() == tempsDeConstruction()) &&
	 *  0 < rechercheCourante() &&
	 *  rechercheCourante() <= tempsDeRecherche() &&
	 *  enRecherche() == (rechercheCourante() > 0)  &&
	 *  rechercheFinie() == (rechercheCourante() == tempsDeRecherche()) &&
	 *  rechercheCourante() == rechercheCourante()@pre + 1 &&
	 *  tempsCourant() == tempsCourant()@pre
	 */
	@Test
	public void testRecherche5_0(){
		String obj="CNRS Test Objectif 5.0";

		try {
			//Condition initial
			cnrs.init(2, 20, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();
			cnrs.commencerRecherche();

			//Capture
			int oldTempsCourant=cnrs.tempsCourant();
			int oldRechercheCourante=cnrs.rechercheCourante();

			try{
				//Operation
				cnrs.recherche();

				//Oracle
				checkInvariants(obj);
				// \post : rechercheCourante() == rechercheCourante()@pre + 1
				assertion(obj+": \\post : rechercheCourante() == rechercheCourante()@pre + 1", cnrs.rechercheCourante() == oldRechercheCourante + 1);
				// \post : tempsCourant() == tempsCourant()@pre
				assertion(obj+": \\post : tempsCourant() == tempsCourant()@pre", cnrs.tempsCourant() == oldTempsCourant);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 5: recherche
	 * 
	 * Cas 5_1: recherche: error pas en recherche
	 * 
	 * Condition initial:
	 * construire(commencerConstruction(init(2,20,200,50)))
	 * 
	 * Operation:
	 * rechercher()
	 * 
	 * Oracle:
	 * Exception pour recherche
	 */
	@Test
	public void testRecherche5_1(){
		String obj="CNRS Test Objectif 5.1";

		try {
			//Condition initial
			cnrs.init(2, 20, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();

			try{
				//Operation
				cnrs.recherche();
				
				//Oracle
				assertion(obj+": recherche devrait echouer",false);
			}catch(Exception e){
				assertion(obj,true);
			}
		}catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}

	}

	/**
	 * Objectif 5: recherche
	 * 
	 * Cas 5_2: recherche error recherche deja fini
	 * 
	 * Condition initial:
	 * recherche(commencerRecherche(construire(commencerConstruction(init(2,2,200,50)))))
	 * 
	 * Operation:
	 * rechercher()
	 * 
	 * Oracle:
	 *  Exception pour recherche
	 */
	@Test
	public void testRecherche5_2(){
		String obj="CNRS Test Objectif 5.2";

		try {
			//Condition initial
			cnrs.init(2, 2, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();
			cnrs.commencerRecherche();
			cnrs.recherche();

			try{
				//Operation
				cnrs.recherche();
				
				//Oracle
				assertion(obj+": recherche devrait echouer",false);
			}catch(Exception e){
				assertion(obj,true);
			}
		}catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}

	}

	/**
	 * Objectif 6: finirRecherche
	 * 
	 * Cas 6: finirRecherche positif
	 * 
	 * Condition initial:
	 * commencerRecherche(construire(commencerConstruction(init(2,2,200,50)))))
	 * 
	 * Operation:
	 * finirRecherche()
	 * 
	 * Oracle:
	 * Pas d'exception &&
	 *  0 < tempsCourant() &&
	 *  tempsCourant() <= tempsDeConstruction() &&
	 *  enConstruction() == (tempsCourant() > 0) &&
	 *  constructionFinie() == (tempsCourant() == tempsDeConstruction()) &&
	 *  0 < rechercheCourante() &&
	 *  rechercheCourante() <= tempsDeRecherche() &&
	 *  enRecherche() == (rechercheCourante() > 0)  &&
	 *  rechercheFinie() == (rechercheCourante() == tempsDeRecherche()) &&
	 *   rechercheCourante() == 0 &&
	 *  tempsCourant() == tempsCourant()@pre
	 */
	@Test
	public void testFinirRecherche6_0(){
		String obj="CNRS Test Objectif 6.0";

		try {
			//Condition initial
			cnrs.init(2, 2, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();
			cnrs.commencerRecherche();
			
			//Capture
			int oldTempsCourant=cnrs.tempsCourant();
			try{
				//Operation
				cnrs.finirRecherche();
				
				//Oracle
				checkInvariants(obj);
				
				// \post : rechercheCourante() == 0
				assertion(obj+": \\post : rechercheCourante() == 0", cnrs.rechercheCourante()==0);
				// \post : tempsCourant() == tempsCourant()@pre
				assertion(obj+": \\post : tempsCourant() == tempsCourant()@pre ", cnrs.tempsCourant() == oldTempsCourant);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		}catch(Exception e){
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 6: finirRecherche
	 * 
	 * Cas 6: finirRecherche: error pas en recherche
	 * 
	 * Condition initial:
	 * construire(commencerConstruction(init(2,2,200,50))))
	 * 
	 * Operation:
	 * finirRecherche()
	 * 
	 * Oracle:
	 * Exception pour finirRecherche
	 */
	@Test
	public void testFinirRecherche6_1(){
		String obj="CNRS Test Objectif 6.1";

		try {
			//Condition initial
			cnrs.init(2, 2, 200, 50);
			cnrs.commencerConstruction();
			cnrs.construire();
			try{
				//Operation
				cnrs.finirRecherche();
				
				//Oracle
				assertion(obj+": finirRecherche devrait echouer", false);				
			}catch(Exception e){
				assertion(obj, true);
			}
		}catch(Exception e){
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

}
