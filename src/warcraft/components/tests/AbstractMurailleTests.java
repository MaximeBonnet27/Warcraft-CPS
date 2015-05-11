package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warcraft.services.IMineService;
import warcraft.services.IMurailleService;

public abstract class AbstractMurailleTests extends AssertionTests {
	protected IMurailleService mur;

	@Before
	public abstract void before();

	@After
	public void after() {
		mur = null;
	}
	
	public void checkInvariants(String obj){
		// \inv : estDetruite() == (pointsDeVie() <= 0)
		assertion(obj+"\\inv : estDetruite() == (pointsDeVie() <= 0)", mur.estDetruite() == (mur.pointsDeVie() <= 0));
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
	 *  init(2,2,10)
	 *  
	 *  Oracle:
	 *  Pas d'exception &&
	 *  estDetruite() == (pointsDeVie() <= 0) &&
	 *  largeur() == 2 &&
	 *  hauteur() == 2 &&
	 *  pointsDeVie() == pv
	 */
	@Test
	public void testInit1_0(){
		String obj="Muraille Test Objectif 1.0";
		
		// Condition initial
		
		
		try {
			// Operation
			mur.init(2, 2, 10);
			
			//Oracle
			checkInvariants(obj);
			
			// \post : largeur() == largeur
			assertion(obj+": \\post : largeur() == largeur", mur.largeur() == 2);
			// \post : hauteur() == hauteur
			assertion(obj+": \\post : hauteur() == hauteur", mur.hauteur() == 2);
			// \post : pointsDeVie() == pv
			assertion(obj+": \\post : pointsDeVie() == pv", mur.pointsDeVie() == 10);
		} catch (Exception e) {
			assertion(obj+": "+e.getMessage(), false);
		}
	}
	
	
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_1: init: error largeur<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(-2,2,10)
	 *  
	 *  Oracle:
	 *  Exception init
	 */
	@Test
	public void testInit1_1(){
		String obj="Muraille Test Objectif 1.1";
		
		// Condition initial
		
		
		try {
			// Operation
			mur.init(-2, 2, 10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_1: init: error hauteur<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(2,-2,10)
	 *  
	 *  Oracle:
	 *  Exception init
	 */
	@Test
	public void testInit1_2(){
		String obj="Muraille Test Objectif 1.2";
		
		// Condition initial
		
		
		try {
			// Operation
			mur.init(2,-2, 10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}
	
	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_3: init: error pv<=0
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 *  init(2,2,-10)
	 *  
	 *  Oracle:
	 *  Exception init
	 */
	@Test
	public void testInit1_3(){
		String obj="Muraille Test Objectif 1.3";
		
		// Condition initial
		
		
		try {
			// Operation
			mur.init(2, 2, -10);
			
			//Oracle
			assertion(obj+": init devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}
	
	/**
	 * Objectif 2: taper
	 * 
	 * Cas 2_0: taper positif
	 * 
	 * Condition initial:
	 * init(2,2,10)
	 * 
	 * Operation:
	 * taper(1)
	 * 
	 * Oracle:
	 *	Pas d'exception &&
	 *  estDetruite() == (pointsDeVie() <= 0) &&
	 *  pointsDeVie(taper(x)) == pointsDeVie() -1
	 */
	@Test
	public void testTaper2_0(){
		String obj="Muraille Test Objectif 2_0";
		
		try {
			//Condition initial
			mur.init(2, 2, 10);
			
			//Capture
			int oldPointsDeVie=mur.pointsDeVie();
			
			try{
				
				//Operation
				mur.taper(1);
				
				//Oracle
				checkInvariants(obj);
				
				// \post : pointsDeVie() == pointsDeVie()@pre - degats
				assertion(obj+": \\post : pointsDeVie(taper(x)) == pointsDeVie() - x", mur.pointsDeVie() == oldPointsDeVie - 1);
			}catch(Exception e){
				assertion(obj+": "+e.getMessage(), false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 2: taper
	 * 
	 * Cas 2_1: taper: error est deja detruite
	 * 
	 * Condition initial:
	 * taper(init(2,2,10),10)
	 * 
	 * Operation:
	 * taper(1)
	 * 
	 * Oracle:
	 *	Exception pour taper
	 */
	@Test
	public void testTaper2_1(){
		String obj="Muraille Test Objectif 2_1";
		
		try {
			//Condition initial
			mur.init(2, 2, 10);
			mur.taper(10);
			
			try{
				
				//Operation
				mur.taper(1);
				
				//Oracle
				assertion(obj+": taper devrait echouer", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
	
	/**
	 * Objectif 2: taper
	 * 
	 * Cas 2_2: taper: error degat<=0
	 * 
	 * Condition initial:
	 * init(2,2,10)
	 * 
	 * Operation:
	 * taper(-1)
	 * 
	 * Oracle:
	 *	Exception pour taper
	 */
	@Test
	public void testTaper2_2(){
		String obj="Muraille Test Objectif 2_2";
		
		try {
			//Condition initial
			mur.init(2, 2, 10);
			
			try{
				
				//Operation
				mur.taper(-1);
				
				//Oracle
				assertion(obj+": taper devrait echouer", false);
			}catch(Exception e){
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}
}
