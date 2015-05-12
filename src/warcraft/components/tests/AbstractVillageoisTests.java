package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warcraft.components.correct.Villageois;
import warcraft.enums.ECompetence;
import warcraft.enums.ERace;
import warcraft.services.IVillageoisService;

public abstract class AbstractVillageoisTests extends AssertionTests {
	protected IVillageoisService villageois;

	@Before
	public abstract void before();

	@After
	public void after() {
		villageois = null;
	}
	
	public void checkInvariants(String obj){
		// \inv : estMort() == (pointsDeVie() <= 0)
		assertion(obj+": \\inv : estMort() == (pointsDeVie() <= 0)", villageois.estMort() == (villageois.pointsDeVie() <= 0));
		// \inv : corveeFinie() == (compteurCorvee()==16)
		assertion(obj+": \\inv : corveeFini() == (compteurCorvee()==16)", villageois.corveeFinie() == (villageois.compteurCorvee()==16));
		// \inv : enCorvee() == (compteurCorvee()>0)
		assertion(obj+": \\inv : enCorvee() == (compteurCorvee()>0)", villageois.enCorvee() == (villageois.compteurCorvee()>0));
		// \inv : quantiteOr() >= 0
		assertion(obj+": \\inv : quantiteOr() >= 0",villageois.quantiteOr() >= 0);
		// \inv :  0 <= compteurCorvee() && compteurCorvee() <= 16
		assertion(obj+": \\inv : 0 <= compteurCorvee() && compteurCorvee() <= 16",  0 <= villageois.compteurCorvee() && villageois.compteurCorvee() <= 16);
		// \inv : force() > 0
		assertion(obj+": force() > 0", villageois.force() > 0);
		// \inv : vitesse() > 0
		assertion(obj+": vitesse() > 0", villageois.vitesse() > 0);
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
	 *  init(ERace.ORC,2,2,10,10,10)
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  race() == r &&
	 *  largeur() == largeur &&
	 *  hauteur() == hauteur &&
	 *  force() == force &&
	 *  vitesse() == vitesse &&
	 *  pointsDevie() == pointsDeVie &&
	 *  quantiteOr() == 0 &&
	 *   compteurCorvee() == 0
	 */
	@Test
	public void testInit1_0(){
		String obj="Villageois Test Objectif 1.0";
		
		// Condition initial
		
		
		try {
			// Operation
			villageois.init(ERace.ORC, 2, 2,10,10,10);
			
			//Oracle
			checkInvariants(obj);
			
			// \post : race() == race 
			assertion(obj+" :\\post : race() == r", villageois.race() == ERace.ORC );
			// \post : largeur() == largeur
			assertion(obj+" :\\post : largeur() == largeur", villageois.largeur() == 2);
			// \post : hauteur() == hauteur
			assertion(obj+" :\\post : hauteur() == hauteur", villageois.hauteur() == 2);
			// \post : force() == force
			assertion(obj+" :\\post : force() == force", villageois.force() == 10);
			// \post : vitesse() == vitesse
			assertion(obj+" :\\post : vitesse() == vitesse", villageois.vitesse() == 10);
			// \post : pointsDeVie() == pointsDeVie
			assertion(obj+" :\\post : pointsDevie() == pointsDeVie", villageois.pointsDeVie() == 10);
			// \post : quantiteOr() == 0
			assertion(obj+" :\\post : quantiteOr() == 0", villageois.quantiteOr() == 0);
			// \post : compteurCorvee() == 0
			assertion(obj+" :\\post : compteurCorvee() == 0", villageois.compteurCorvee() == 0);
		} catch (Exception e) {
			assertion(obj+": "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_1: init: error largeur <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(ERace.ORC,-2,2,10,10,10)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_1(){
		String obj="Villageois Test Objectif 1.1";
		
		//Condition initial
		
		try {
			//Operation
			villageois.init(ERace.ORC,-2, 2,10,10,10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error hauteur <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(ERace.ORC,2,-2,10,10,10)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_2(){
		String obj="Villageois Test Objectif 1.2";
		
		//Condition initial
		
		try {
			//Operation
			villageois.init(ERace.ORC,2, -2,10,10,10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_3: init: error force <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(ERace.ORC,2,2,-10,10,10)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_3(){
		String obj="Villageois Test Objectif 1.3";
		
		//Condition initial
		
		try {
			//Operation
			villageois.init(ERace.ORC,2, 2,-10,10,10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_4: init: error vitesse <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(ERace.ORC,2,2,10,-10,10)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_4(){
		String obj="Villageois Test Objectif 1.4";
		
		//Condition initial
		
		try {
			//Operation
			villageois.init(ERace.ORC,2, 2,10,-10,10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_5: init: error pointsDeVie <=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(ERace.ORC,2,2,10,10,-10)
	 *  
	 *  Oracle:
	 *  Exception pour init
	 */
	@Test
	public void testInit1_5(){
		String obj="Villageois Test Objectif 1.5";
		
		//Condition initial
		
		try {
			//Operation
			villageois.init(ERace.ORC,2, 2,10,10,-10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	
	}
	
	/**
	 * Objectif 2: retraitPV
	 * 
	 * Cas 2_0: retraitPV positif
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 *  retraitPV(1)
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre - 1 &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == compteurCorvee()@pre &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 * 
	 */
	@Test
	public void testRetraiPV2_0(){
		String obj="Villageois Test Objectif 2.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			//Capture
			int oldCompteurCorvee=villageois.compteurCorvee();
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.retraitPV(1);
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre - degats
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre - 1", villageois.pointsDeVie() == oldPointsDeVie - 1);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == compteurCorvee()@pre
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre", villageois.compteurCorvee() == oldCompteurCorvee);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 2: retraitPV
	 * 
	 * Cas 2_1: retraitPV error degat<=0
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 *  retraitPV(-1)
	 *  
	 *  Oracle:
	 * Exception pour retraitPV
	 */
	@Test
	public void testRetraiPV2_1(){
		String obj="Villageois Test Objectif 2.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			try{
				//Operation
				villageois.retraitPV(-1);
				
				//Oracle
				assertion(obj+" :retraitPV devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 2: retraitPV
	 * 
	 * Cas 2_2: retraitPV error deja mort
	 * 
	 * Condition initial:
	 * retrait(init(ERace.ORC,2,2,10,10,10),10)
	 * 
	 * Operation:
	 *  retraitPV(1)
	 *  
	 *  Oracle:
	 * Exception pour retraitPV
	 */
	@Test
	public void testRetraiPV2_2(){
		String obj="Villageois Test Objectif 2.2";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.retraitPV(10);
			
			try{
				//Operation
				villageois.retraitPV(1);
				
				//Oracle
				assertion(obj+" :retraitPV devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 3: ajouteOR
	 * 
	 * Cas 3_0: ajouteOR positif
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 *  ajouteOr(1)
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre + 1 &&
	 *  compteurCorvee() == compteurCorvee()@pre &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 * 
	 */
	@Test
	public void testAjouteOR3_0(){
		String obj="Villageois Test Objectif 3.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			//Capture
			int oldCompteurCorvee=villageois.compteurCorvee();
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.ajouterOr(1);
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre - degats
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre ", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre + 1", villageois.quantiteOr() == oldQuantiteOr+1);
				// \post : compteurCorvee() == compteurCorvee()@pre
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre", villageois.compteurCorvee() == oldCompteurCorvee);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 3: ajouteOr
	 * 
	 * Cas 3_1: ajouteOR error somme<0
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 *  ajouteOr(-1)
	 *  
	 *  Oracle:
	 * Exception pour ajouteOr
	 */
	@Test
	public void testAjouteOr3_1(){
		String obj="Villageois Test Objectif 3.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			try{
				//Operation
				villageois.ajouterOr(-1);
				
				//Oracle
				assertion(obj+" :ajouteOr devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	

	/**
	 * Objectif 3: ajouteOr
	 * 
	 * Cas 3_2: ajouteOR error villageois mort
	 * 
	 * Condition initial:
	 * retrait(init(ERace.ORC,2,2,10,10,10),10)
	 * 
	 * Operation:
	 *  ajouteOr(1)
	 *  
	 *  Oracle:
	 * Exception pour ajouteOr
	 */
	@Test
	public void testAjouteOr3_2(){
		String obj="Villageois Test Objectif 3.2";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.retraitPV(10);
			try{
				//Operation
				villageois.ajouterOr(1);
				
				//Oracle
				assertion(obj+" :ajouteOr devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	
	/**
	 * Objectif 4: retraitOR
	 * 
	 * Cas 4_0: retraitOR positif
	 * 
	 * Condition initial:
	 * ajouteOr(init(ERace.ORC,2,2,10,10,10),1)
	 * 
	 * Operation:
	 *  retraitOr(1)
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre - 1 &&
	 *  compteurCorvee() == compteurCorvee()@pre &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 * 
	 */
	@Test
	public void testRetraitOR4_0(){
		String obj="Villageois Test Objectif 4.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.ajouterOr(1);
			//Capture
			
			int oldCompteurCorvee=villageois.compteurCorvee();
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.retraitOr(1);
				
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre ", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre -1
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre - 1", villageois.quantiteOr() == oldQuantiteOr-1);
				// \post : compteurCorvee() == compteurCorvee()@pre
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre", villageois.compteurCorvee() == oldCompteurCorvee);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 4: retraitOr
	 * 
	 * Cas 4_1: ajouteOR error somme<0
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 *  retraitOr(-1)
	 *  
	 *  Oracle:
	 * Exception pour retraitOr
	 */
	@Test
	public void testRetraitOr4_1(){
		String obj="Villageois Test Objectif 4.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			try{
				//Operation
				villageois.retraitOr(-1);
				
				//Oracle
				assertion(obj+" :retraitOr devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	

	/**
	 * Objectif 4: retraitOr
	 * 
	 * Cas 4_2: retraitOR error villageois mort
	 * 
	 * Condition initial:
	 * retrait(init(ERace.ORC,2,2,10,10,10),10)
	 * 
	 * Operation:
	 *  retraitOr(1)
	 *  
	 *  Oracle:
	 * Exception pour retraitOr
	 */
	@Test
	public void testRetraitOr4_2(){
		String obj="Villageois Test Objectif 4.2";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.retraitPV(10);
			try{
				//Operation
				villageois.retraitOr(1);
				
				//Oracle
				assertion(obj+" :retraitOr devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 4: retraitOr
	 * 
	 * Cas 4_3: retraitOR error pas assez d'or
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * 
	 * Operation:
	 *  retraitOr(1)
	 *  
	 *  Oracle:
	 * Exception pour retraitOr
	 */
	@Test
	public void testRetraitOr4_3(){
		String obj="Villageois Test Objectif 4.3";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			try{
				//Operation
				villageois.retraitOr(1);
				
				//Oracle
				assertion(obj+" :retraitOr devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 5: commencerTravail
	 * 
	 * Cas 5_0: commencerTravail positif
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 *  commencerTravail()
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == 1 &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 * 
	 */
	@Test
	public void testCommencerTravail5_0(){
		String obj="Villageois Test Objectif 5.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			//Capture
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.commenceTravaille();
				
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre ", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre ", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == 1
				assertion(obj+" :\\post : compteurCorvee() == 1", villageois.compteurCorvee() == 1);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 5: commencerTravail
	 * 
	 * Cas 5_1: commencerTravail error villageois mort
	 * 
	 * Condition initial:
	 * retraitPV(init(ERace.ORC,2,2,10,10,10),10)
	 * 
	 * Operation:
	 *  commencerTravail()
	 *  
	 *  Oracle:
	 * Exception pour commencerTravail
	 */
	@Test
	public void testCommencerTravail5_1(){
		String obj="Villageois Test Objectif 5.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.retraitPV(10);
			try{
				//Operation
				villageois.commenceTravaille();
				
				//Oracle
				assertion(obj+" :commencerTravail devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 6: finirTravail
	 * 
	 * Cas 6_0: finirTravail positif
	 * 
	 * Condition initial:
	 * commencerTravail(init(ERace.ORC,2,2,10,10,10))
	 * 
	 * Operation:
	 *  finirTravail()
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == 0 &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 * 
	 */
	@Test
	public void testFinirTravail6_0(){
		String obj="Villageois Test Objectif 6.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.commenceTravaille();
			
			//Capture
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.finirTravail();
				
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre ", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre ", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == 0
				assertion(obj+" :\\post : compteurCorvee() == 0", villageois.compteurCorvee() == 0);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 6: finirTravail
	 * 
	 * Cas 6_1: finirTravail error villageois mort
	 * 
	 * Condition initial:
	 * retraitPV(commencerTravail(init(ERace.ORC,2,2,10,10,10)),10)
	 * 
	 * Operation:
	 *  finirTravail()
	 *  
	 *  Oracle:
	 * Exception pour finirTravail
	 */
	@Test
	public void testFinirTravail6_1(){
		String obj="Villageois Test Objectif 6.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.commenceTravaille();
			villageois.retraitPV(10);
			try{
				//Operation
				villageois.finirTravail();
				
				//Oracle
				assertion(obj+" :finirTravail devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 6: finirTravail
	 * 
	 * Cas 6_2: finirTravail error villageois pas en corvee
	 * 
	 * Condition initial:
	 * commencertravail(init(ERace.ORC,2,2,10,10,10))
	 * 
	 * Operation:
	 *  finirTravail()
	 *  
	 *  Oracle:
	 * Exception pour finirTravail
	 */
	@Test
	public void testFinirTravail6_2(){
		String obj="Villageois Test Objectif 6.2";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			try{
				//Operation
				villageois.finirTravail();
				
				//Oracle
				assertion(obj+" :finirTravail devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 7: travail
	 * 
	 * Cas 7_0: travail positif
	 * 
	 * Condition initial:
	 * commencerTravail(init(ERace.ORC,2,2,10,10,10))
	 * 
	 * Operation:
	 *  travaille()
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == compteurCorvee()@pre+1 &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 * 
	 */
	@Test
	public void testTravail7_0(){
		String obj="Villageois Test Objectif 7.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.commenceTravaille();
			
			//Capture
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			int oldCompteurCorvee=villageois.compteurCorvee();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.travaille();
				
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre ", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre ", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == compteurCorvee()@pre +1
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre+1", villageois.compteurCorvee() == oldCompteurCorvee+1);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 7: travail
	 * 
	 * Cas 7_1: travail error villageois mort
	 * 
	 * Condition initial:
	 * retraitPV(commencerTravail(init(ERace.ORC,2,2,10,10,10)),10)
	 * 
	 * Operation:
	 *  travail()
	 *  
	 *  Oracle:
	 * Exception pour travail
	 */
	@Test
	public void testTravail7_1(){
		String obj="Villageois Test Objectif 7.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.commenceTravaille();
			villageois.retraitPV(10);
			try{
				//Operation
				villageois.travaille();
				
				//Oracle
				assertion(obj+" :travail devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 7: travail
	 * 
	 * Cas 7_2: travail error corvee deja fini
	 * 
	 * Condition initial:
	 * commmencerTravail((init(ERace.ORC,2,2,10,10,10))
	 * for i in 1 to 15
	 * travaille()
	 * 
	 * Operation:
	 *  travail()
	 *  
	 *  Oracle:
	 * Exception pour travail
	 */
	@Test
	public void testTravail7_2(){
		String obj="Villageois Test Objectif 7.2";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.commenceTravaille();
			for(int i=1;i<16;i++)
				villageois.travaille();
			
			try{
				//Operation
				villageois.travaille();
				
				//Oracle
				assertion(obj+" :finirTravail devrait echoué", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 8: amelioration
	 * 
	 * Cas 8_0: amelioration positif ECompetance.PV
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 * amelioration(ECompetence.PV,10)
	 *  
	 *  Oracle:
	 * Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre+10 &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == compteurCorvee()@pre &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre
	 */
	@Test
	public void testAmelioration8_0(){
		String obj="Villageois Test Objectif 8.0";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			//Capture
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			int oldCompteurCorvee=villageois.compteurCorvee();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.amelioration(ECompetence.PV, 10);
				
				//Oracle
				checkInvariants(obj);
				// \post : pointsDeVie() == pointsDeVie()@pre + 10
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre +10", villageois.pointsDeVie() == oldPointsDeVie+10);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre ", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == compteurCorvee()@pre
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre", villageois.compteurCorvee() == oldCompteurCorvee);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
				
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	
	/**
	 * Objectif 8: amelioration
	 * 
	 * Cas 8_1: amelioration positif ECompetance.FORCE
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 * amelioration(ECompetence.FORCE,10)
	 *  
	 *  Oracle:
	 * Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == compteurCorvee()@pre &&
	 *  force() == force()@pre+10 &&
	 *  vitesse() == vitesse()@pre
	 */
	@Test
	public void testAmelioration8_1(){
		String obj="Villageois Test Objectif 8.1";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			//Capture
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			int oldCompteurCorvee=villageois.compteurCorvee();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.amelioration(ECompetence.FORCE, 10);
				
				//Oracle
				checkInvariants(obj);
				// \post : pointsDeVie() == pointsDeVie()@pre
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre ", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == compteurCorvee()@pre
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre", villageois.compteurCorvee() == oldCompteurCorvee);
				// \post : force() == force()@pre+10
				assertion(obj+" :\\post : force() == force()@pre+10", villageois.force() == oldForce+10);
				// \post : vitesse() == vitesse()@pre
				assertion(obj+" :\\post : vitesse() == vitesse()@pre", villageois.vitesse() == oldVitesse);
				
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 8: amelioration
	 * 
	 * Cas 8_2: amelioration positif ECompetance.VITESSE
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 * amelioration(ECompetence.VITESSE,10)
	 *  
	 *  Oracle:
	 * Pas d'exception &&
	 *  estMort() == (pointsDeVie() <= 0 &&
	 *  corveeFini() == (compteurCorvee()==16 &&
	 *  enCorvee() == (compteurCorvee()>0 &&
	 *  quantiteOr() >= 0 &&
	 *  0 <= compteurCorvee() && compteurCorvee() <= 16 &&
	 *  force() > 0 &&
	 *  vitesse() > 0 &&
	 *  pointsDeVie() == pointsDeVie()@pre &&
	 *  quantiteOr() == quantiteOr()@pre &&
	 *  compteurCorvee() == compteurCorvee()@pre &&
	 *  force() == force()@pre &&
	 *  vitesse() == vitesse()@pre+10
	 */
	@Test
	public void testAmelioration8_2(){
		String obj="Villageois Test Objectif 8.2";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			//Capture
			int oldForce=villageois.force();
			int oldPointsDeVie=villageois.pointsDeVie();
			int oldQuantiteOr=villageois.quantiteOr();
			int oldCompteurCorvee=villageois.compteurCorvee();
			double oldVitesse=villageois.vitesse();
			
			try{
				//Operation
				villageois.amelioration(ECompetence.VITESSE, 10);
				
				//Oracle
				checkInvariants(obj);
				// \post : pointsDeVie() == pointsDeVie()@pre
				assertion(obj+" :\\post : pointsDeVie() == pointsDeVie()@pre", villageois.pointsDeVie() == oldPointsDeVie);
				// \post : quantiteOr() == quantiteOr()@pre 
				assertion(obj+" :\\post : quantiteOr() == quantiteOr()@pre ", villageois.quantiteOr() == oldQuantiteOr);
				// \post : compteurCorvee() == compteurCorvee()@pre
				assertion(obj+" :\\post : compteurCorvee() == compteurCorvee()@pre", villageois.compteurCorvee() == oldCompteurCorvee);
				// \post : force() == force()@pre
				assertion(obj+" :\\post : force() == force()@pre", villageois.force() == oldForce);
				// \post : vitesse() == vitesse()@pre+10
				assertion(obj+" :\\post : vitesse() == vitesse()@pre+10", villageois.vitesse() == oldVitesse+10);
				
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 8: amelioration
	 * 
	 * Cas 8_3: amelioration error villageois mort
	 * 
	 * Condition initial:
	 * retraitPV(init(ERace.ORC,2,2,10,10,10),10)
	 * 
	 * Operation:
	 * amelioration(ECompetence.PV,10)
	 *  
	 *  Oracle:
	 * Exception pour amelioration
	 */
	@Test
	public void testAmelioration8_3(){
		String obj="Villageois Test Objectif 8.3";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			villageois.retraitPV(10);
			
			try{
				//Operation
				villageois.amelioration(ECompetence.PV, 10);
				
				//Oracle
				assertion(obj+" :amelioration devrait echouer", false);
				
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 8: amelioration
	 * 
	 * Cas 8_4: amelioration error val<=0
	 * 
	 * Condition initial:
	 * init(ERace.ORC,2,2,10,10,10)
	 * 
	 * Operation:
	 * amelioration(ECompetence.PV,0)
	 *  
	 *  Oracle:
	 * Exception pour amelioration
	 */
	@Test
	public void testAmelioration8_4(){
		String obj="Villageois Test Objectif 8.4";
		
		//Condition initial
		try {
			villageois.init(ERace.ORC,2, 2,10,10,10);
			
			try{
				//Operation
				villageois.amelioration(ECompetence.PV, 0);
				
				//Oracle
				assertion(obj+" :amelioration devrait echouer", false);
				
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
}
