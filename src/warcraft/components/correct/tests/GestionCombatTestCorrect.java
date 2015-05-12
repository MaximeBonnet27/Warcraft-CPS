package warcraft.components.correct.tests;

import warcraft.components.correct.GestionCombat;
import warcraft.components.tests.AbstractGestionCombatTests;

public class GestionCombatTestCorrect extends AbstractGestionCombatTests {

	@Override
	public void before() {
		combat=new GestionCombat();
	}

}
