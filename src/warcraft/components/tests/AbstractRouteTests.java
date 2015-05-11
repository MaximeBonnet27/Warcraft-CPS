package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warcraft.services.IRouteService;

public abstract class AbstractRouteTests extends AssertionTests {

	protected IRouteService route;

	@Before
	public abstract void before();

	@After
	public void after() {
		route = null;
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_0: init positif
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(2,2,2)
	 * 
	 * Oracle:
	 * Pas d'exception &&
	 * largeur() == 2 &&
	 * hauteur() == 2 &&
	 * multiplicateur() == 2
	 */
	@Test
	public void testInit1_0(){
		String obj="Route Test Objectif 1.0";

		//Condition initial

		try {
			//Operation
			route.init(2, 2, 2);

			//Oracle
			// \post: largeur() == largeur
			assertion("\\post : largeur() == 2", route.largeur() == 2);
			// \post : hauteur() == hauteur
			assertion("\\post : hauteur() == 2", route.hauteur() == 2);
			// \post : multiplicateur() == mult
			assertion("\\post : multiplicateur() == 2", route.multiplicateur() == 2);

		} catch (Exception e) {
			assertion(obj+": "+e.getMessage(),false);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error largeur<=0 
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(0,2,2)
	 * 
	 * Oracle:
	 * Exception pour init
	 */
	@Test
	public void testInit1_1(){
		String obj="Route Test Objectif 1.1";

		//Condition initial

		try {
			//Operation
			route.init(0, 2, 2);

			//Oracle
			assertion(obj+": init devrait echouer",false);
		} catch (Exception e) {
			assertion(obj,true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error hauteur<=0 
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(2,-2,2)
	 * 
	 * Oracle:
	 * Exception pour init
	 */
	@Test
	public void testInit1_2(){
		String obj="Route Test Objectif 1.2";

		//Condition initial

		try {
			//Operation
			route.init(2, -2, 2);

			//Oracle
			assertion(obj+": init devrait echouer",false);
		} catch (Exception e) {
			assertion(obj,true);
		}
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1_2: init: error multiplicateur<1 
	 * 
	 * Condition initial:
	 * aucune
	 * 
	 * Operation:
	 * init(2,2,-2)
	 * 
	 * Oracle:
	 * Exception pour init
	 */
	@Test
	public void testInit1_3(){
		String obj="Route Test Objectif 1.1";

		//Condition initial

		try {
			//Operation
			route.init(2, 2, -2);

			//Oracle
			assertion(obj+": init devrait echouer",false);
		} catch (Exception e) {
			assertion(obj,true);
		}
	}
}
