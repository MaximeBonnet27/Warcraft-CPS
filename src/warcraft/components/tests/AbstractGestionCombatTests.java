package warcraft.components.tests;

import org.junit.After;
import org.junit.Before;

import warcraft.services.IGestionCombatService;
import warcraft.services.IHotelDeVilleService;

public abstract class AbstractGestionCombatTests extends AssertionTests {
	protected IGestionCombatService combat;

	@Before
	public abstract void before();

	@After
	public void after() {
		combat = null;
	}
}
