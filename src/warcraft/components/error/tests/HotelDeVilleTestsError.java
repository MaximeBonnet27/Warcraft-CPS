package warcraft.components.error.tests;

import warcraft.components.error.HotelDeVilleError;
import warcraft.components.tests.AbstractHotelDeVilleTests;

public class HotelDeVilleTestsError extends AbstractHotelDeVilleTests {

	@Override
	public void before() {
		hotel=new HotelDeVilleError();
	}

}
