package warcraft.components.error.tests;

import warcraft.components.error.GestionCombatError;
import warcraft.components.tests.AbstractGestionCombatTests;

public class GestionCombatTestsError extends AbstractGestionCombatTests {

	@Override
	public void before() {
		combat=new GestionCombatError();
	}

}
