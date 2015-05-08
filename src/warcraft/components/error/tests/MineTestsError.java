package warcraft.components.error.tests;

import warcraft.components.error.MineError;
import warcraft.components.tests.AbstractMineTests;

public class MineTestsError extends AbstractMineTests{

	@Override
	public void before() {
		this.mine=new MineError();
	}


}
